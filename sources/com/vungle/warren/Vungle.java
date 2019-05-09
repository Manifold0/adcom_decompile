package com.vungle.warren;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.URLUtil;
import com.google.android.gms.drive.DriveFile;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.tapjoy.TJAdUnitConstants.String;
import com.vungle.warren.DownloadStrategy.VerificationCallback;
import com.vungle.warren.download.APKDirectDownloadManager;
import com.vungle.warren.error.VungleError;
import com.vungle.warren.error.VungleException;
import com.vungle.warren.model.Advertisement;
import com.vungle.warren.model.Cookie;
import com.vungle.warren.model.JsonUtil;
import com.vungle.warren.model.Placement;
import com.vungle.warren.network.Downloader;
import com.vungle.warren.network.Downloader.Listener;
import com.vungle.warren.network.FetchDownloader;
import com.vungle.warren.network.VungleApiClient;
import com.vungle.warren.persistence.FilePersistor;
import com.vungle.warren.persistence.GraphicDesigner;
import com.vungle.warren.presenter.AdvertisementPresenter.EventListener;
import com.vungle.warren.tasks.CleanupJob;
import com.vungle.warren.tasks.DownloadJob;
import com.vungle.warren.tasks.ReconfigJob;
import com.vungle.warren.tasks.ReconfigJob.ReconfigCall;
import com.vungle.warren.tasks.SendReportsJob;
import com.vungle.warren.tasks.VungleJobCreator;
import com.vungle.warren.ui.VungleActivity;
import com.vungle.warren.ui.VungleFlexViewActivity;
import com.vungle.warren.ui.VungleNativeView;
import com.vungle.warren.utility.FileUtility;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

public class Vungle {
    private static final String COM_VUNGLE_SDK = "com.vungle.sdk";
    private static final String TAG = Vungle.class.getCanonicalName();
    static final Vungle _instance = new Vungle();
    private static Handler handler = new Handler(Looper.getMainLooper());
    private static AtomicBoolean isDepInit = new AtomicBoolean(false);
    private static boolean isInitialized = false;
    private static AtomicBoolean isInitializing = new AtomicBoolean(false);
    private String appID;
    private Context context;
    private Downloader downloader;
    private HeaderBiddingCallback headerBiddingCallback;
    private InitCallback initCallback;
    private VungleJobRunner jobRunner;
    private Map<String, Boolean> loadOperations = new ConcurrentHashMap();
    private Map<String, Boolean> playOperations = new ConcurrentHashMap();
    private PublisherDirectDownload publisherDirectDownload;
    private boolean shouldTransmitIMEI;
    Storage storage;
    private Consent tempConsent;
    private String tempConsentVersion;
    private String userIMEI;

    /* renamed from: com.vungle.warren.Vungle$1 */
    static class C01231 implements ReconfigCall {
        C01231() {
        }

        public void reConfigVungle() {
            Vungle.reConfigure();
        }
    }

    interface DownloadCallback {
        void onDownloadCompleted(File file, String str);

        void onDownloadFailed(Throwable th, String str);
    }

    public enum Consent {
        OPTED_IN,
        OPTED_OUT
    }

    private Vungle() {
    }

    @Deprecated
    public static void init(@NonNull Collection<String> collection, @NonNull String appId, @NonNull Context context, @NonNull InitCallback callback, PublisherDirectDownload publisherDirectDownload) throws IllegalArgumentException {
        init(appId, context, callback, publisherDirectDownload);
    }

    public static void init(@NonNull String appId, @NonNull Context context, @NonNull InitCallback callback, PublisherDirectDownload publisherDirectDownload) throws IllegalArgumentException {
        String VUNGLE_VERSION_STRING = "!SDK-VERSION-STRING!:com.vungle:publisher-sdk-android:6.3.17";
        if (callback == null) {
            throw new IllegalArgumentException("A valid InitCallback required to ensure API calls are being made after initialize is successful");
        } else if (context == null || appId == null || appId.isEmpty()) {
            callback.onError(new VungleException(6));
        } else if (!(context instanceof Application)) {
            callback.onError(new VungleException(7));
        } else if (isInitialized()) {
            Log.d(TAG, "init already complete");
            callback.onSuccess();
        } else if (isInitializing.getAndSet(true)) {
            Log.d(TAG, "init ongoing");
            callback.onError(new VungleException(8));
        } else {
            if (!isDepInit.getAndSet(true)) {
                GraphicDesigner designer = new GraphicDesigner(context.getCacheDir());
                Storage storage = Storage.makeInstance(new FilePersistor(context.getFilesDir()), designer);
                _instance.context = context;
                _instance.appID = appId;
                _instance.downloader = new FetchDownloader(context);
                _instance.storage = storage;
                _instance.jobRunner = new VungleJobRunner(new VungleJobCreator(_instance.storage, designer, new C01231()));
                storage.init(1);
                VungleApiClient.init(context, appId, designer.getCacheDirectory().getPath(), storage);
                if (!TextUtils.isEmpty(_instance.userIMEI)) {
                    VungleApiClient.updateIMEI(_instance.userIMEI, _instance.shouldTransmitIMEI);
                }
                if (!(_instance.tempConsent == null || TextUtils.isEmpty(_instance.tempConsentVersion))) {
                    updateConsentStatus(_instance.tempConsent, _instance.tempConsentVersion);
                }
                Cookie appIdCookie = (Cookie) _instance.storage.load(Cookie.APP_ID, Cookie.class);
                if (appIdCookie == null) {
                    appIdCookie = new Cookie(Cookie.APP_ID);
                }
                appIdCookie.putValue(Cookie.APP_ID, appId);
                _instance.storage.save(appIdCookie);
            }
            _instance.initCallback = callback;
            _instance.publisherDirectDownload = publisherDirectDownload;
            _instance.configure(callback);
        }
    }

    static void reConfigure() {
        if (isInitialized()) {
            _instance.configure(_instance.initCallback);
        } else {
            init(_instance.appID, _instance.context, _instance.initCallback, _instance.publisherDirectDownload);
        }
    }

    @Deprecated
    public static void init(@NonNull Collection<String> collection, @NonNull String appId, @NonNull Context context, @NonNull InitCallback callback) throws IllegalArgumentException {
        init(appId, context, callback, null);
    }

    public static void init(@NonNull String appId, @NonNull Context context, @NonNull InitCallback callback) throws IllegalArgumentException {
        init(appId, context, callback, null);
    }

    private void configure(@NonNull final InitCallback callback) {
        VungleApiClient.config(new Callback<JsonObject>() {

            /* renamed from: com.vungle.warren.Vungle$2$1 */
            class C01241 implements Callback<JsonObject> {
                C01241() {
                }

                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    if (response.isSuccessful()) {
                        Editor editor = Vungle.this.context.getSharedPreferences(Vungle.COM_VUNGLE_SDK, 0).edit();
                        editor.putBoolean("reported", true);
                        editor.apply();
                        Log.d(Vungle.TAG, "Saving reported state to shared preferences");
                    }
                }

                public void onFailure(Call<JsonObject> call, Throwable throwable) {
                }
            }

            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response == null) {
                    callback.onError(new VungleException(2));
                    Vungle.isInitializing.set(false);
                } else if (response.isSuccessful()) {
                    if (!Vungle.this.context.getSharedPreferences(Vungle.COM_VUNGLE_SDK, 0).getBoolean("reported", false)) {
                        VungleApiClient.reportNew().enqueue(new C01241());
                    }
                    JsonObject jsonObject = (JsonObject) response.body();
                    JsonArray placementsData = jsonObject.getAsJsonArray("placements");
                    if (placementsData.size() == 0) {
                        callback.onError(new VungleException(0));
                        Vungle.isInitializing.set(false);
                        return;
                    }
                    Vungle.this.playOperations.clear();
                    Vungle.this.loadOperations.clear();
                    List<Placement> newPlacements = new ArrayList();
                    Iterator it = placementsData.iterator();
                    while (it.hasNext()) {
                        newPlacements.add(new Placement(((JsonElement) it.next()).getAsJsonObject()));
                    }
                    Vungle._instance.storage.setValidPlacements(newPlacements);
                    if (jsonObject.has("gdpr")) {
                        Cookie gdprConsent = (Cookie) Vungle._instance.storage.load(Cookie.CONSENT_COOKIE, Cookie.class);
                        if (gdprConsent == null) {
                            gdprConsent = new Cookie(Cookie.CONSENT_COOKIE);
                            gdprConsent.putValue("consent_status", "unknown");
                            gdprConsent.putValue("consent_source", "no_interaction");
                            gdprConsent.putValue("timestamp", Long.valueOf(0));
                        }
                        JsonObject gdprJsonObject = jsonObject.getAsJsonObject("gdpr");
                        boolean isCountryDataProtected = JsonUtil.hasNonNull(gdprJsonObject, "is_country_data_protected") && gdprJsonObject.get("is_country_data_protected").getAsBoolean();
                        Object consentTitle = JsonUtil.hasNonNull(gdprJsonObject, "consent_title") ? gdprJsonObject.get("consent_title").getAsString() : "";
                        Object consentMessage = JsonUtil.hasNonNull(gdprJsonObject, "consent_message") ? gdprJsonObject.get("consent_message").getAsString() : "";
                        Object consentMessageVersion = JsonUtil.hasNonNull(gdprJsonObject, "consent_message_version") ? gdprJsonObject.get("consent_message_version").getAsString() : "";
                        Object acceptText = JsonUtil.hasNonNull(gdprJsonObject, "button_accept") ? gdprJsonObject.get("button_accept").getAsString() : "";
                        Object denyText = JsonUtil.hasNonNull(gdprJsonObject, "button_deny") ? gdprJsonObject.get("button_deny").getAsString() : "";
                        gdprConsent.putValue("is_country_data_protected", Boolean.valueOf(isCountryDataProtected));
                        String str = "consent_title";
                        if (TextUtils.isEmpty(consentTitle)) {
                            consentTitle = "Targeted Ads";
                        }
                        gdprConsent.putValue(str, consentTitle);
                        str = "consent_message";
                        if (TextUtils.isEmpty(consentMessage)) {
                            consentMessage = "To receive more relevant ad content based on your interactions with our ads, click \"I Consent\" below. Either way, you will see the same amount of ads.";
                        }
                        gdprConsent.putValue(str, consentMessage);
                        if (!"publisher".equalsIgnoreCase(gdprConsent.getString("consent_source"))) {
                            str = "consent_message_version";
                            if (TextUtils.isEmpty(consentMessageVersion)) {
                                consentMessageVersion = "";
                            }
                            gdprConsent.putValue(str, consentMessageVersion);
                        }
                        str = "button_accept";
                        if (TextUtils.isEmpty(acceptText)) {
                            acceptText = "I Consent";
                        }
                        gdprConsent.putValue(str, acceptText);
                        str = "button_deny";
                        if (TextUtils.isEmpty(denyText)) {
                            denyText = "I Do Not Consent";
                        }
                        gdprConsent.putValue(str, denyText);
                        Vungle._instance.storage.save(gdprConsent);
                    }
                    if (jsonObject.has("apk_direct_download") && jsonObject.getAsJsonObject("apk_direct_download").has(String.ENABLED)) {
                        boolean isDirectDownloadEnabled = jsonObject.getAsJsonObject("apk_direct_download").get(String.ENABLED).getAsBoolean();
                        if (isDirectDownloadEnabled) {
                            APKDirectDownloadManager.init(Vungle.this.context);
                            APKDirectDownloadManager.setDirectDownloadStatus(isDirectDownloadEnabled ? 1 : 0);
                        }
                    } else {
                        APKDirectDownloadManager.init(Vungle.this.context);
                        APKDirectDownloadManager.setDirectDownloadStatus(-1);
                    }
                    if (jsonObject.has("ri")) {
                        Cookie configCookie = (Cookie) Vungle._instance.storage.load(Cookie.CONFIG_COOKIE, Cookie.class);
                        if (configCookie == null) {
                            configCookie = new Cookie(Cookie.CONFIG_COOKIE);
                        }
                        configCookie.putValue("isReportIncentivizedEnabled", Boolean.valueOf(jsonObject.getAsJsonObject("ri").get(String.ENABLED).getAsBoolean()));
                        Vungle._instance.storage.save(configCookie);
                    }
                    if (jsonObject.has("attribution_reporting")) {
                        JsonObject attributionReporting = jsonObject.getAsJsonObject("attribution_reporting");
                        if (attributionReporting.has("should_transmit_imei")) {
                            Vungle.this.shouldTransmitIMEI = attributionReporting.get("should_transmit_imei").getAsBoolean();
                        } else {
                            Vungle.this.shouldTransmitIMEI = false;
                        }
                    } else {
                        Vungle.this.shouldTransmitIMEI = false;
                    }
                    if (jsonObject.has("config")) {
                        Vungle.this.jobRunner.execute(ReconfigJob.makeJobInfo(Vungle.this.appID).setDelay(jsonObject.getAsJsonObject("config").get("refresh_time").getAsLong()));
                    }
                    Vungle.isInitialized = true;
                    callback.onSuccess();
                    Vungle.isInitializing.set(false);
                    Collection<Placement> placements = Vungle.this.storage.loadValidPlacements();
                    Vungle.this.jobRunner.execute(CleanupJob.makeJobInfo());
                    if (placements != null) {
                        for (Placement placement : placements) {
                            if (placement.isAutoCached()) {
                                Log.d(Vungle.TAG, "starting jobs for autocached advs");
                                Vungle.this.jobRunner.execute(DownloadJob.makeJobInfo(placement.getId(), true));
                            }
                        }
                    }
                    Vungle.this.jobRunner.execute(SendReportsJob.makeJobInfo());
                } else {
                    long retryAfterHeaderValue = VungleApiClient.getRetryAfterHeaderValue(response);
                    if (retryAfterHeaderValue > 0) {
                        Vungle.this.jobRunner.execute(ReconfigJob.makeJobInfo(Vungle._instance.appID).setDelay(retryAfterHeaderValue));
                        callback.onError(new VungleException(14));
                        Vungle.isInitializing.set(false);
                        return;
                    }
                    callback.onError(new VungleException(3));
                    Vungle.isInitializing.set(false);
                }
            }

            public void onFailure(Call<JsonObject> call, Throwable throwable) {
                Vungle.isInitialized = false;
                Vungle.isInitializing.set(false);
                Log.e(Vungle.TAG, Log.getStackTraceString(throwable));
                if (throwable instanceof HttpException) {
                    callback.onError(new VungleException(3));
                }
                callback.onError(new VungleException(2));
            }
        });
    }

    public static boolean isInitialized() {
        return (!isInitialized || _instance.storage == null || _instance.storage.getValidPlacements() == null || _instance.storage.getValidPlacements().size() <= 0 || _instance.context == null) ? false : true;
    }

    public static void setIncentivizedFields(@Nullable String userID, @Nullable String title, @Nullable String body, @Nullable String keepWatching, @Nullable String close) {
        if (isInitialized()) {
            Cookie incentivizedCookie = (Cookie) _instance.storage.load(Cookie.INCENTIVIZED_TEXT_COOKIE, Cookie.class);
            if (incentivizedCookie == null) {
                incentivizedCookie = new Cookie(Cookie.INCENTIVIZED_TEXT_COOKIE);
            }
            boolean changed = false;
            if (!TextUtils.isEmpty(title)) {
                changed = true;
                incentivizedCookie.putValue("title", title);
            }
            if (!TextUtils.isEmpty(body)) {
                changed = true;
                incentivizedCookie.putValue("body", body);
            }
            if (!TextUtils.isEmpty(keepWatching)) {
                changed = true;
                incentivizedCookie.putValue("continue", keepWatching);
            }
            if (!TextUtils.isEmpty(close)) {
                changed = true;
                incentivizedCookie.putValue(String.CLOSE, close);
            }
            if (!TextUtils.isEmpty(userID)) {
                changed = true;
                incentivizedCookie.putValue("userID", userID);
            }
            if (changed) {
                _instance.storage.save(incentivizedCookie);
                return;
            }
            return;
        }
        Log.e(TAG, "Vungle is not initialized");
    }

    public static boolean canPlayAd(@NonNull String id) {
        if (isInitialized()) {
            return canPlayAd(_instance.storage.findValidAdvertisementForPlacement(id));
        }
        Log.e(TAG, "Vungle is not initialized");
        return false;
    }

    static boolean canPlayAd(Advertisement advertisement) {
        boolean canPlay = true;
        if (advertisement == null) {
            return false;
        }
        if (!(advertisement.getState() == 1 && _instance.storage.hasValidAssets(advertisement))) {
            canPlay = false;
        }
        return canPlay;
    }

    public static void playAd(@NonNull String id, AdConfig settings, @Nullable PlayAdCallback listener) {
        if (isInitialized()) {
            Placement placement = (Placement) _instance.storage.load(id, Placement.class);
            Exception exception = null;
            if (Boolean.TRUE.equals(_instance.playOperations.get(id)) || Boolean.TRUE.equals(_instance.loadOperations.get(id))) {
                exception = new VungleException(8);
            }
            if (placement == null) {
                exception = new VungleException(13);
            }
            if (exception == null) {
                boolean streamingOnly = false;
                Advertisement advertisement = _instance.storage.findValidAdvertisementForPlacement(id);
                if (canPlayAd(advertisement)) {
                    advertisement.configure(settings);
                    _instance.storage.save(advertisement);
                } else {
                    streamingOnly = true;
                    if (advertisement != null && advertisement.getState() == 1) {
                        _instance.storage.saveAndApplyState(advertisement, id, 4);
                        if (placement.isAutoCached()) {
                            _instance.jobRunner.execute(DownloadJob.makeJobInfo(placement.getId(), true));
                        }
                    }
                    if (listener != null) {
                        listener.onError(id, new VungleException(10));
                    }
                }
                if (_instance.context != null) {
                    String str;
                    final boolean finalStreamingOnly = streamingOnly;
                    final Advertisement finalAdvertisement = advertisement;
                    String id2 = placement.getId();
                    boolean isAutoCached = placement.isAutoCached();
                    if (streamingOnly) {
                        str = "";
                    } else {
                        str = advertisement.getAdToken();
                    }
                    Call willPlayAd = VungleApiClient.willPlayAd(id2, isAutoCached, str);
                    final AdConfig adConfig = settings;
                    final String str2 = id;
                    final PlayAdCallback playAdCallback = listener;
                    willPlayAd.enqueue(new Callback<JsonObject>() {
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                            Exception e;
                            VungleError vungleError;
                            Advertisement advReplace = null;
                            if (response.isSuccessful()) {
                                JsonObject responseBody = (JsonObject) response.body();
                                if (responseBody.has("ad")) {
                                    try {
                                        Advertisement advReplace2 = new Advertisement(responseBody.getAsJsonObject("ad"));
                                        try {
                                            advReplace2.configure(adConfig);
                                            Vungle._instance.storage.saveAndApplyState(advReplace2, str2, 0);
                                            advReplace = advReplace2;
                                        } catch (IllegalArgumentException e2) {
                                            advReplace = advReplace2;
                                            Log.v("Vungle", "Will Play Ad did not respond with a replacement. Move on.");
                                            if (finalStreamingOnly) {
                                                Vungle.renderAd(str2, playAdCallback, str2, finalAdvertisement);
                                            } else if (advReplace == null) {
                                                Vungle.renderAd(str2, playAdCallback, str2, advReplace);
                                            } else if (playAdCallback == null) {
                                                playAdCallback.onError(str2, new VungleException(1));
                                            }
                                        } catch (Exception e3) {
                                            e = e3;
                                            advReplace = advReplace2;
                                            Log.e("Vungle", "Error using will_play_ad!", e);
                                            if (finalStreamingOnly) {
                                                Vungle.renderAd(str2, playAdCallback, str2, finalAdvertisement);
                                            } else if (advReplace == null) {
                                                Vungle.renderAd(str2, playAdCallback, str2, advReplace);
                                            } else if (playAdCallback == null) {
                                                playAdCallback.onError(str2, new VungleException(1));
                                            }
                                        } catch (VungleError e4) {
                                            vungleError = e4;
                                            advReplace = advReplace2;
                                            if (vungleError.getErrorCode() == 6) {
                                                Log.e("Vungle", "Error using will_play_ad!", vungleError);
                                            } else {
                                                Log.e(Vungle.TAG, "will_play_ad was disabled by the configuration settings. This is expected.");
                                            }
                                            if (finalStreamingOnly) {
                                                Vungle.renderAd(str2, playAdCallback, str2, finalAdvertisement);
                                            } else if (advReplace == null) {
                                                Vungle.renderAd(str2, playAdCallback, str2, advReplace);
                                            } else if (playAdCallback == null) {
                                                playAdCallback.onError(str2, new VungleException(1));
                                            }
                                        }
                                    } catch (IllegalArgumentException e5) {
                                        Log.v("Vungle", "Will Play Ad did not respond with a replacement. Move on.");
                                        if (finalStreamingOnly) {
                                            Vungle.renderAd(str2, playAdCallback, str2, finalAdvertisement);
                                        } else if (advReplace == null) {
                                            Vungle.renderAd(str2, playAdCallback, str2, advReplace);
                                        } else if (playAdCallback == null) {
                                            playAdCallback.onError(str2, new VungleException(1));
                                        }
                                    } catch (Exception e6) {
                                        e = e6;
                                        Log.e("Vungle", "Error using will_play_ad!", e);
                                        if (finalStreamingOnly) {
                                            Vungle.renderAd(str2, playAdCallback, str2, finalAdvertisement);
                                        } else if (advReplace == null) {
                                            Vungle.renderAd(str2, playAdCallback, str2, advReplace);
                                        } else if (playAdCallback == null) {
                                            playAdCallback.onError(str2, new VungleException(1));
                                        }
                                    } catch (VungleError e7) {
                                        vungleError = e7;
                                        if (vungleError.getErrorCode() == 6) {
                                            Log.e(Vungle.TAG, "will_play_ad was disabled by the configuration settings. This is expected.");
                                        } else {
                                            Log.e("Vungle", "Error using will_play_ad!", vungleError);
                                        }
                                        if (finalStreamingOnly) {
                                            Vungle.renderAd(str2, playAdCallback, str2, finalAdvertisement);
                                        } else if (advReplace == null) {
                                            Vungle.renderAd(str2, playAdCallback, str2, advReplace);
                                        } else if (playAdCallback == null) {
                                            playAdCallback.onError(str2, new VungleException(1));
                                        }
                                    }
                                }
                            }
                            if (finalStreamingOnly) {
                                Vungle.renderAd(str2, playAdCallback, str2, finalAdvertisement);
                            } else if (advReplace == null) {
                                Vungle.renderAd(str2, playAdCallback, str2, advReplace);
                            } else if (playAdCallback == null) {
                                playAdCallback.onError(str2, new VungleException(1));
                            }
                        }

                        public void onFailure(Call<JsonObject> call, Throwable throwable) {
                            if (!finalStreamingOnly) {
                                Vungle.renderAd(str2, playAdCallback, str2, finalAdvertisement);
                            } else if (playAdCallback != null) {
                                playAdCallback.onError(str2, new VungleException(1));
                            }
                        }
                    });
                }
            } else if (listener != null) {
                listener.onError(id, exception);
            }
        } else if (listener != null) {
            listener.onError(id, new VungleException(9));
        }
    }

    private static void renderAd(@NonNull final String id, @Nullable final PlayAdCallback listener, final String placementId, final Advertisement advertisement) {
        boolean isFlex = true;
        _instance.playOperations.put(id, Boolean.valueOf(true));
        VungleActivity.setEventListener(new EventListener() {
            int percentViewed = -1;
            boolean succesfulView = false;

            public void onNext(String event, String value) {
                boolean z = true;
                if (event.equals(String.VIDEO_START)) {
                    Vungle._instance.storage.saveAndApplyState(advertisement, id, 2);
                    if (listener != null) {
                        listener.onAdStart(id);
                    }
                    this.percentViewed = 0;
                    Placement placement = (Placement) Vungle._instance.storage.load(placementId, Placement.class);
                    if (placement != null && placement.isAutoCached()) {
                        Vungle._instance.jobRunner.execute(DownloadJob.makeJobInfo(placementId, true));
                    }
                } else if (event.equals("end")) {
                    Log.d("Vungle", "Cleaning up metadata and assets for placement " + id + " and advertisement " + advertisement.getId());
                    Vungle._instance.storage.saveAndApplyState(advertisement, id, 3);
                    Vungle._instance.playOperations.put(id, Boolean.valueOf(false));
                    Vungle._instance.jobRunner.execute(SendReportsJob.makeJobInfo());
                    if (listener != null) {
                        PlayAdCallback playAdCallback = listener;
                        String str = id;
                        boolean z2 = this.succesfulView || this.percentViewed >= 80;
                        if (value == null || !value.equals("isCTAClicked")) {
                            z = false;
                        }
                        playAdCallback.onAdEnd(str, z2, z);
                    }
                } else if (event.equals("successfulView")) {
                    this.succesfulView = true;
                } else if (event.startsWith("percentViewed")) {
                    String[] tokens = event.split(":");
                    if (tokens.length == 2) {
                        this.percentViewed = Integer.parseInt(tokens[1]);
                    }
                }
            }

            public void onError(Throwable throwable) {
                Vungle._instance.storage.saveAndApplyState(advertisement, id, 4);
                Vungle._instance.playOperations.put(id, Boolean.valueOf(false));
                if (listener != null) {
                    listener.onError(id, throwable);
                }
            }
        });
        if (advertisement == null || !"flexview".equals(advertisement.getTemplateType())) {
            isFlex = false;
        }
        Intent intent = new Intent(_instance.context, isFlex ? VungleFlexViewActivity.class : VungleActivity.class);
        intent.addFlags(DriveFile.MODE_READ_ONLY);
        intent.putExtra(VungleActivity.PLACEMENT_EXTRA, id);
        _instance.context.startActivity(intent);
    }

    public static void loadAd(@NonNull String id, @Nullable LoadAdCallback callback) {
        loadAd(id, callback, _instance.publisherDirectDownload);
    }

    public static void loadAd(@NonNull final String id, @Nullable final LoadAdCallback callback, final PublisherDirectDownload pubsDownloadClient) {
        if (isInitialized()) {
            Placement placement = (Placement) _instance.storage.load(id, Placement.class);
            if (placement == null) {
                if (callback != null) {
                    callback.onError(id, new IllegalArgumentException("Placement ID " + id + " is not valid. Please check your configuration on the vungle dashboard."));
                }
            } else if (!Boolean.TRUE.equals(_instance.loadOperations.get(id))) {
                final Advertisement advertisement = _instance.storage.findValidAdvertisementForPlacement(placement.getId());
                if (canPlayAd(advertisement)) {
                    Log.i(TAG, "found already cached valid adv, calling onAdLoad " + id + " callback ");
                    if (placement.isAutoCached()) {
                        _instance.initCallback.onAutoCacheAdAvailable(id);
                    }
                    if (callback != null) {
                        callback.onAdLoad(id);
                        return;
                    }
                    return;
                }
                Log.i(TAG, "didn't find cached adv for " + id + " downloading ");
                if (placement.getWakeupTime() > System.currentTimeMillis()) {
                    if (callback != null) {
                        callback.onError(id, new VungleException(1));
                        Log.w(TAG, "Placement " + placement.getId() + " is  snoozed");
                    }
                    if (placement.isAutoCached()) {
                        Log.d(TAG, "Placement " + placement.getId() + " is sleeping rescheduling it ");
                        _instance.jobRunner.execute(DownloadJob.makeJobInfo(placement.getId(), true).setDelay(placement.getWakeupTime() - System.currentTimeMillis()));
                        return;
                    }
                    return;
                }
                _instance.loadOperations.put(id, Boolean.valueOf(true));
                final DownloadCallback downloadCallback = new DownloadCallback() {
                    public void onDownloadCompleted(File file, String advertisementId) {
                        Advertisement advertisement;
                        Log.d(Vungle.TAG, "download completed " + id);
                        if (advertisementId == null) {
                            advertisement = null;
                        } else {
                            advertisement = (Advertisement) Vungle._instance.storage.load(advertisementId, Advertisement.class);
                        }
                        Placement placement = (Placement) Vungle._instance.storage.load(id, Placement.class);
                        if (advertisement == null || placement == null) {
                            onDownloadFailed(new IllegalStateException("Didn't find adv"), id);
                            return;
                        }
                        Vungle._instance.storage.saveAndApplyState(advertisement, id, 1);
                        Log.d("Vungle", String.format(Locale.ENGLISH, "Downloaded assets for %s to %s", new Object[]{id, file.getAbsolutePath()}));
                        FileUtility.printDirectoryTree(file);
                        Vungle._instance.loadOperations.put(id, Boolean.valueOf(false));
                        if (Vungle._instance.headerBiddingCallback != null) {
                            Vungle._instance.headerBiddingCallback.adAvailableForBidToken(id, advertisement.getBidToken());
                        }
                        if (placement.isAutoCached()) {
                            Vungle._instance.initCallback.onAutoCacheAdAvailable(id);
                        }
                        if (callback != null) {
                            callback.onAdLoad(id);
                        }
                    }

                    public void onDownloadFailed(Throwable throwable, String advertisementId) {
                        Advertisement advertisement;
                        if (advertisementId == null) {
                            advertisement = null;
                        } else {
                            advertisement = (Advertisement) Vungle._instance.storage.load(advertisementId, Advertisement.class);
                        }
                        Placement placement = (Placement) Vungle._instance.storage.load(id, Placement.class);
                        if (!(advertisement == null || placement == null)) {
                            Vungle._instance.storage.saveAndApplyState(advertisement, id, 4);
                        }
                        Log.e("Vungle", "Failed to download assets for " + id + ". Cause:", throwable);
                        Vungle._instance.loadOperations.put(id, Boolean.valueOf(false));
                        if (callback != null) {
                            callback.onError(id, throwable);
                        }
                    }
                };
                if (advertisement == null || advertisement.getState() != 0) {
                    if (advertisement != null && advertisement.getState() == 1) {
                        _instance.storage.saveAndApplyState(advertisement, id, 4);
                    }
                    Log.d(TAG, "No adv for placement " + placement.getId() + " getting new data ");
                    _instance.fetchAdMetadata(id, pubsDownloadClient, downloadCallback, _instance.headerBiddingCallback);
                    return;
                }
                Log.d(TAG, "Found valid adv but not ready - downloading content");
                handler.post(new Runnable() {
                    public void run() {
                        Vungle._instance.downloadAdContent(id, advertisement, pubsDownloadClient, downloadCallback);
                    }
                });
            } else if (callback != null) {
                callback.onError(id, new VungleException(8));
            }
        } else if (callback != null) {
            callback.onError(id, new VungleException(9));
        }
    }

    private static void clearCache() {
        if (isInitialized()) {
            _instance.storage.clearAllData();
            _instance.configure(_instance.initCallback);
            return;
        }
        Log.e(TAG, "Vungle not initialized");
    }

    private void fetchAdMetadata(String id, PublisherDirectDownload pubsDownloadClient, @NonNull DownloadCallback downloadCallback, HeaderBiddingCallback bidTokenCallBack) {
        final DownloadCallback downloadCallback2 = downloadCallback;
        final String str = id;
        final HeaderBiddingCallback headerBiddingCallback = bidTokenCallBack;
        final PublisherDirectDownload publisherDirectDownload = pubsDownloadClient;
        VungleApiClient.requestAd(id, bidTokenCallBack != null).enqueue(new Callback<JsonObject>() {
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response == null) {
                    downloadCallback2.onDownloadFailed(new VungleException(1), null);
                } else if (response.isSuccessful()) {
                    Placement placement = (Placement) Vungle.this.storage.load(str, Placement.class);
                    if (placement == null) {
                        Log.e(Vungle.TAG, "Placement metadata not found for requested advertisement.");
                        downloadCallback2.onDownloadFailed(new VungleException(2), null);
                        return;
                    }
                    JsonObject jsonObject = (JsonObject) response.body();
                    if (jsonObject == null || !jsonObject.has("ads") || jsonObject.get("ads").isJsonNull()) {
                        downloadCallback2.onDownloadFailed(new VungleError(0), null);
                        return;
                    }
                    JsonArray ads = jsonObject.getAsJsonArray("ads");
                    if (ads == null || ads.size() == 0) {
                        downloadCallback2.onDownloadFailed(new VungleException(1), null);
                        return;
                    }
                    JsonObject ad = ads.get(0).getAsJsonObject();
                    try {
                        Advertisement advertisement = new Advertisement(ad);
                        if (headerBiddingCallback != null) {
                            headerBiddingCallback.onBidTokenAvailable(str, advertisement.getBidToken());
                        }
                        Vungle._instance.storage.saveAndApplyState(advertisement, str, 0);
                        Vungle.this.downloadAdContent(str, advertisement, publisherDirectDownload, downloadCallback2);
                    } catch (IllegalArgumentException e) {
                        JsonObject admarkup = ad.getAsJsonObject("ad_markup");
                        if (admarkup.has("sleep")) {
                            int sleep = admarkup.get("sleep").getAsInt();
                            placement.snooze((long) sleep);
                            Vungle.this.storage.save(placement);
                            if (placement.isAutoCached()) {
                                Vungle.this.jobRunner.execute(DownloadJob.makeJobInfo(str, true).setDelay((long) (sleep * 1000)));
                            }
                        }
                        downloadCallback2.onDownloadFailed(new VungleException(1), null);
                    }
                } else {
                    long retryAfterHeaderValue = VungleApiClient.getRetryAfterHeaderValue(response);
                    Placement tempPlacement = (Placement) Vungle._instance.storage.load(str, Placement.class);
                    if (retryAfterHeaderValue <= 0 || tempPlacement == null || !tempPlacement.isAutoCached()) {
                        Log.e(Vungle.TAG, "Failed to retrieve advertisement information");
                        downloadCallback2.onDownloadFailed(new VungleException(2), null);
                        return;
                    }
                    Vungle.this.jobRunner.execute(DownloadJob.makeJobInfo(str, true).setDelay(retryAfterHeaderValue));
                    downloadCallback2.onDownloadFailed(new VungleException(14), null);
                }
            }

            public void onFailure(Call<JsonObject> call, Throwable throwable) {
                downloadCallback2.onDownloadFailed(throwable, null);
            }
        });
    }

    private void downloadAdContent(final String placementId, final Advertisement advertisement, PublisherDirectDownload pubsDownloadClient, @NonNull final DownloadCallback downloadCallback) {
        DownloadStrategy downloadStrategy = null;
        if (!(pubsDownloadClient == null || TextUtils.isEmpty(advertisement.getAdMarketId()))) {
            downloadStrategy = new DirectDownloadStrategy(pubsDownloadClient);
        }
        if (downloadStrategy != null) {
            downloadStrategy.isApplicationAvailable(advertisement.getAdMarketId(), new VerificationCallback() {
                public void onResult(boolean result) {
                    if (result) {
                        Log.d(Vungle.TAG, "fetchAdMetadata: downloading assets ");
                        Vungle.this.downloadAdAssets(advertisement, downloadCallback, placementId);
                        return;
                    }
                    downloadCallback.onDownloadFailed(new VungleException(5), null);
                }
            });
        } else {
            downloadAdAssets(advertisement, downloadCallback, placementId);
        }
    }

    private void downloadAdAssets(Advertisement advertisement, DownloadCallback downloadCallback, String placementId) {
        for (Entry<String, String> entry : advertisement.getDownloadableUrls().entrySet()) {
            if (TextUtils.isEmpty((CharSequence) entry.getKey()) || TextUtils.isEmpty((CharSequence) entry.getValue())) {
                downloadCallback.onDownloadFailed(new VungleException(11), null);
                Log.e(TAG, "Aborting, Failed to download Ad assets for: " + advertisement.getId());
                return;
            } else if (!URLUtil.isValidUrl((String) entry.getValue())) {
                downloadCallback.onDownloadFailed(new VungleException(11), null);
                Log.e(TAG, "Aborting, Failed to download Ad assets for: " + advertisement.getId());
                return;
            }
        }
        File destinationDir = this.storage.getAdvertisementAssetDirectory(advertisement.getId());
        final int downloadCount = advertisement.getDownloadableUrls().size();
        final Advertisement advertisement2 = advertisement;
        final DownloadCallback downloadCallback2 = downloadCallback;
        final String str = placementId;
        Listener downloadListener = new Listener() {
            private AtomicInteger downloadsComplete = new AtomicInteger(0);

            public void onComplete(File file) {
                if (this.downloadsComplete.incrementAndGet() == downloadCount) {
                    if (advertisement2.getAdType() == 1) {
                        Log.d(Vungle.TAG, "saving MRAID for " + advertisement2.getId());
                        advertisement2.setMraidAssetDir(file);
                        Vungle._instance.storage.save(advertisement2);
                    }
                    downloadCallback2.onDownloadCompleted(file.getParentFile(), advertisement2.getId());
                }
            }

            public void onProgress(int progress) {
            }

            public void onError(Throwable throwable) {
                Log.e(Vungle.TAG, "Download Failed");
                Vungle._instance.storage.saveAndApplyState(advertisement2, str, 4);
                downloadCallback2.onDownloadFailed(throwable, advertisement2.getId());
            }
        };
        try {
            for (Entry<String, String> entry2 : advertisement.getDownloadableUrls().entrySet()) {
                File dest = new File(destinationDir.getPath() + File.separator + ((String) entry2.getKey()));
                if (URLUtil.isHttpsUrl((String) entry2.getValue()) || URLUtil.isHttpUrl((String) entry2.getValue())) {
                    this.downloader.download((String) entry2.getValue(), dest, downloadListener);
                } else {
                    downloadCallback.onDownloadFailed(new VungleError(10), advertisement.getId());
                }
            }
        } catch (IOException e) {
            downloadCallback.onDownloadFailed(new VungleError(8), advertisement.getId());
            Log.e(TAG, Log.getStackTraceString(e));
        } catch (IllegalStateException e2) {
            downloadCallback.onDownloadFailed(new VungleError(8), advertisement.getId());
            Log.e(TAG, Log.getStackTraceString(e2));
        }
    }

    public static VungleNativeAd getNativeAd(String placementId, PlayAdCallback playAdCallback) {
        return getNativeAd(placementId, playAdCallback, null);
    }

    public static VungleNativeAd getNativeAd(final String placementId, final PlayAdCallback playAdCallback, PublisherDirectDownload pubsDownloadClient) {
        if (isInitialized()) {
            Placement placement = (Placement) _instance.storage.load(placementId, Placement.class);
            if (placement == null) {
                playAdCallback.onError(placementId, new Throwable("No placement for ID " + placementId + " found. Please use a valid placement ID"));
                return null;
            }
            final Advertisement advertisement = _instance.storage.findValidAdvertisementForPlacement(placementId);
            if (advertisement == null) {
                Log.e(TAG, "No Advertisement for ID");
                return null;
            } else if (canPlayAd(advertisement)) {
                if (Boolean.TRUE.equals(_instance.playOperations.get(placementId)) || Boolean.TRUE.equals(_instance.loadOperations.get(placementId))) {
                    playAdCallback.onError(placementId, new VungleException(8));
                    return null;
                } else if (advertisement.getAdType() != 1) {
                    playAdCallback.onError(placementId, new Throwable(placementId + " is not an MRAID compatible placement. Please use a valid placement ID"));
                    return null;
                } else {
                    DirectDownloadAdapter directDownloadAdapter = null;
                    if (pubsDownloadClient != null) {
                        directDownloadAdapter = new DirectDownloadAdapter(pubsDownloadClient, advertisement.getAdMarketId());
                    }
                    _instance.playOperations.put(placementId, Boolean.valueOf(true));
                    return new VungleNativeView(_instance.context.getApplicationContext(), placementId, directDownloadAdapter, new EventListener() {
                        int percentViewed = -1;
                        boolean succesfulView = false;

                        public void onNext(String s, String value) {
                            boolean z = true;
                            if (s.equals(String.VIDEO_START)) {
                                Vungle._instance.storage.saveAndApplyState(advertisement, placementId, 2);
                                if (playAdCallback != null) {
                                    playAdCallback.onAdStart(placementId);
                                }
                                this.percentViewed = 0;
                                Placement placement = (Placement) Vungle._instance.storage.load(placementId, Placement.class);
                                if (placement != null && placement.isAutoCached()) {
                                    Vungle._instance.jobRunner.execute(DownloadJob.makeJobInfo(placementId, true));
                                }
                            } else if (s.equals("end")) {
                                Log.d("Vungle", "Cleaning up metadata and assets for placement " + placementId + " and advertisement " + advertisement.getId());
                                Vungle._instance.storage.saveAndApplyState(advertisement, placementId, 3);
                                Vungle._instance.jobRunner.execute(SendReportsJob.makeJobInfo());
                                Vungle._instance.playOperations.put(placementId, Boolean.valueOf(false));
                                if (playAdCallback != null) {
                                    PlayAdCallback playAdCallback = playAdCallback;
                                    String str = placementId;
                                    boolean z2 = this.succesfulView || this.percentViewed >= 80;
                                    if (value == null || !value.equals("isCTAClicked")) {
                                        z = false;
                                    }
                                    playAdCallback.onAdEnd(str, z2, z);
                                }
                            } else if (s.equals("successfulView")) {
                                this.succesfulView = true;
                            } else if (s.startsWith("percentViewed")) {
                                String[] tokens = s.split(":");
                                if (tokens.length == 2) {
                                    this.percentViewed = Integer.parseInt(tokens[1]);
                                }
                            }
                        }

                        public void onError(Throwable throwable) {
                            Vungle._instance.playOperations.put(placementId, Boolean.valueOf(false));
                            Vungle._instance.storage.saveAndApplyState(advertisement, placementId, 4);
                            if (playAdCallback != null) {
                                playAdCallback.onError(placementId, throwable);
                            }
                        }
                    });
                }
            } else if (advertisement == null || advertisement.getState() != 1) {
                return null;
            } else {
                _instance.storage.saveAndApplyState(advertisement, placementId, 4);
                if (!placement.isAutoCached()) {
                    return null;
                }
                _instance.jobRunner.execute(DownloadJob.makeJobInfo(placement.getId(), true));
                return null;
            }
        }
        Log.e(TAG, "Vungle is not initialized, returned VungleNativeAd = null");
        return null;
    }

    public static Collection<String> getValidPlacements() {
        if (isInitialized()) {
            return _instance.storage.getValidPlacements();
        }
        Log.e(TAG, "Vungle is not initialized return empty placemetns list");
        return Collections.emptyList();
    }

    static void handleApkDirectDownloads(Context context) {
        APKDirectDownloadManager.handleDownload(context);
    }

    public static void updateConsentStatus(@NonNull Consent status, @NonNull String consentMessageVersion) {
        if (isDepInit.get()) {
            Cookie gdprConsent = (Cookie) _instance.storage.load(Cookie.CONSENT_COOKIE, Cookie.class);
            if (gdprConsent == null) {
                gdprConsent = new Cookie(Cookie.CONSENT_COOKIE);
            }
            gdprConsent.putValue("consent_status", status == Consent.OPTED_IN ? "opted_in" : "opted_out");
            gdprConsent.putValue("timestamp", Long.valueOf(System.currentTimeMillis() / 1000));
            gdprConsent.putValue("consent_source", "publisher");
            String str = "consent_message_version";
            if (consentMessageVersion == null) {
                consentMessageVersion = "";
            }
            gdprConsent.putValue(str, consentMessageVersion);
            _instance.storage.save(gdprConsent);
            _instance.tempConsent = null;
            _instance.tempConsentVersion = null;
            return;
        }
        _instance.tempConsent = status;
        _instance.tempConsentVersion = consentMessageVersion;
    }

    public static Consent getConsentStatus() {
        if (isInitialized()) {
            Cookie gdprConsent = (Cookie) _instance.storage.load(Cookie.CONSENT_COOKIE, Cookie.class);
            if (gdprConsent != null) {
                return "opted_in".equals(gdprConsent.getString("consent_status")) ? Consent.OPTED_IN : Consent.OPTED_OUT;
            } else {
                return null;
            }
        }
        Log.e(TAG, "Vungle is not initialized, consent is null");
        return null;
    }

    public static String getConsentMessageVersion() {
        if (isInitialized()) {
            Cookie gdprConsent = (Cookie) _instance.storage.load(Cookie.CONSENT_COOKIE, Cookie.class);
            if (gdprConsent != null) {
                return gdprConsent.getString("consent_message_version");
            }
            return null;
        }
        Log.e(TAG, "Vungle is not initialized, please wait initialize or wait until Vungle is intialized to get Consent Message Version");
        return null;
    }

    public static boolean closeFlexViewAd(@NonNull String placementReferenceId) {
        if (isInitialized()) {
            Intent broadcast = new Intent("AdvertisementBus");
            broadcast.putExtra(VungleActivity.PLACEMENT_EXTRA, placementReferenceId);
            broadcast.putExtra(String.COMMAND, "closeFlex");
            _instance.context.sendBroadcast(broadcast);
            return true;
        }
        Log.e(TAG, "Vungle is not initialized, can't close flex view ad");
        return false;
    }

    public static void setUserLegacyID(String legacyId) {
        if (isInitialized() || isInitializing.get()) {
            VungleApiClient.updateIMEI(legacyId, _instance.shouldTransmitIMEI);
        } else {
            _instance.userIMEI = legacyId;
        }
    }

    public static void setHeaderBiddingCallback(HeaderBiddingCallback headerBiddingCallback) {
        _instance.headerBiddingCallback = headerBiddingCallback;
    }
}
