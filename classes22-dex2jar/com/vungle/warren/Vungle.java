// 
// Decompiled by Procyon v0.5.34
// 

package com.vungle.warren;

import java.io.Serializable;
import com.vungle.warren.ui.VungleFlexViewActivity;
import com.vungle.warren.ui.VungleActivity;
import com.vungle.warren.utility.FileUtility;
import java.util.Locale;
import android.support.annotation.Nullable;
import com.vungle.warren.tasks.JobCreator;
import com.vungle.warren.tasks.VungleJobCreator;
import com.vungle.warren.network.FetchDownloader;
import com.vungle.warren.persistence.Designer;
import com.vungle.warren.persistence.Persistor;
import com.vungle.warren.persistence.FilePersistor;
import com.vungle.warren.persistence.GraphicDesigner;
import android.app.Application;
import java.util.Collections;
import com.vungle.warren.ui.VungleNativeView;
import com.vungle.warren.presenter.AdvertisementPresenter;
import java.io.IOException;
import com.vungle.warren.error.VungleError;
import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;
import android.webkit.URLUtil;
import java.util.Collection;
import java.util.Iterator;
import com.google.gson.JsonArray;
import com.vungle.warren.tasks.SendReportsJob;
import com.vungle.warren.tasks.DownloadJob;
import com.vungle.warren.tasks.CleanupJob;
import com.vungle.warren.download.APKDirectDownloadManager;
import com.vungle.warren.persistence.Memorable;
import android.text.TextUtils;
import com.vungle.warren.model.JsonUtil;
import com.vungle.warren.model.Cookie;
import java.util.List;
import com.google.gson.JsonElement;
import com.vungle.warren.model.Placement;
import java.util.ArrayList;
import android.content.SharedPreferences$Editor;
import com.vungle.warren.tasks.ReconfigJob;
import com.vungle.warren.network.VungleApiClient;
import retrofit2.Response;
import com.vungle.warren.error.VungleException;
import retrofit2.HttpException;
import retrofit2.Call;
import com.google.gson.JsonObject;
import retrofit2.Callback;
import android.content.Intent;
import android.util.Log;
import android.support.annotation.NonNull;
import com.vungle.warren.model.Advertisement;
import java.util.concurrent.ConcurrentHashMap;
import android.os.Looper;
import java.util.Map;
import com.vungle.warren.network.Downloader;
import android.content.Context;
import java.util.concurrent.atomic.AtomicBoolean;
import android.os.Handler;

public class Vungle
{
    private static final String COM_VUNGLE_SDK = "com.vungle.sdk";
    private static final String TAG;
    static final Vungle _instance;
    private static Handler handler;
    private static AtomicBoolean isDepInit;
    private static boolean isInitialized;
    private static AtomicBoolean isInitializing;
    private String appID;
    private Context context;
    private Downloader downloader;
    private HeaderBiddingCallback headerBiddingCallback;
    private InitCallback initCallback;
    private VungleJobRunner jobRunner;
    private Map<String, Boolean> loadOperations;
    private Map<String, Boolean> playOperations;
    private PublisherDirectDownload publisherDirectDownload;
    private boolean shouldTransmitIMEI;
    Storage storage;
    private Consent tempConsent;
    private String tempConsentVersion;
    private String userIMEI;
    
    static {
        TAG = Vungle.class.getCanonicalName();
        _instance = new Vungle();
        Vungle.handler = new Handler(Looper.getMainLooper());
        Vungle.isInitialized = false;
        Vungle.isInitializing = new AtomicBoolean(false);
        Vungle.isDepInit = new AtomicBoolean(false);
    }
    
    private Vungle() {
        this.loadOperations = new ConcurrentHashMap<String, Boolean>();
        this.playOperations = new ConcurrentHashMap<String, Boolean>();
    }
    
    static boolean canPlayAd(final Advertisement advertisement) {
        boolean b = true;
        if (advertisement == null) {
            return false;
        }
        if (advertisement.getState() != 1 || !Vungle._instance.storage.hasValidAssets(advertisement)) {
            b = false;
        }
        return b;
    }
    
    public static boolean canPlayAd(@NonNull final String s) {
        if (!isInitialized()) {
            Log.e(Vungle.TAG, "Vungle is not initialized");
            return false;
        }
        return canPlayAd(Vungle._instance.storage.findValidAdvertisementForPlacement(s));
    }
    
    private static void clearCache() {
        if (isInitialized()) {
            Vungle._instance.storage.clearAllData();
            Vungle._instance.configure(Vungle._instance.initCallback);
            return;
        }
        Log.e(Vungle.TAG, "Vungle not initialized");
    }
    
    public static boolean closeFlexViewAd(@NonNull final String s) {
        if (!isInitialized()) {
            Log.e(Vungle.TAG, "Vungle is not initialized, can't close flex view ad");
            return false;
        }
        final Intent intent = new Intent("AdvertisementBus");
        intent.putExtra("placement", s);
        intent.putExtra("command", "closeFlex");
        Vungle._instance.context.sendBroadcast(intent);
        return true;
    }
    
    private void configure(@NonNull final InitCallback initCallback) {
        VungleApiClient.config((Callback<JsonObject>)new Callback<JsonObject>() {
            public void onFailure(final Call<JsonObject> call, final Throwable t) {
                Vungle.isInitialized = false;
                Vungle.isInitializing.set(false);
                Log.e(Vungle.TAG, Log.getStackTraceString(t));
                if (t instanceof HttpException) {
                    initCallback.onError(new VungleException(3));
                }
                initCallback.onError(new VungleException(2));
            }
            
            public void onResponse(final Call<JsonObject> call, final Response<JsonObject> response) {
                if (response == null) {
                    initCallback.onError(new VungleException(2));
                    Vungle.isInitializing.set(false);
                    return;
                }
                if (!response.isSuccessful()) {
                    final long retryAfterHeaderValue = VungleApiClient.getRetryAfterHeaderValue(response);
                    if (retryAfterHeaderValue > 0L) {
                        Vungle.this.jobRunner.execute(ReconfigJob.makeJobInfo(Vungle._instance.appID).setDelay(retryAfterHeaderValue));
                        initCallback.onError(new VungleException(14));
                        Vungle.isInitializing.set(false);
                        return;
                    }
                    initCallback.onError(new VungleException(3));
                    Vungle.isInitializing.set(false);
                }
                else {
                    if (!Vungle.this.context.getSharedPreferences("com.vungle.sdk", 0).getBoolean("reported", false)) {
                        VungleApiClient.reportNew().enqueue((Callback)new Callback<JsonObject>() {
                            public void onFailure(final Call<JsonObject> call, final Throwable t) {
                            }
                            
                            public void onResponse(final Call<JsonObject> call, final Response<JsonObject> response) {
                                if (response.isSuccessful()) {
                                    final SharedPreferences$Editor edit = Vungle.this.context.getSharedPreferences("com.vungle.sdk", 0).edit();
                                    edit.putBoolean("reported", true);
                                    edit.apply();
                                    Log.d(Vungle.TAG, "Saving reported state to shared preferences");
                                }
                            }
                        });
                    }
                    final JsonObject jsonObject = (JsonObject)response.body();
                    final JsonArray asJsonArray = jsonObject.getAsJsonArray("placements");
                    if (asJsonArray.size() == 0) {
                        initCallback.onError(new VungleException(0));
                        Vungle.isInitializing.set(false);
                        return;
                    }
                    Vungle.this.playOperations.clear();
                    Vungle.this.loadOperations.clear();
                    final ArrayList<Placement> validPlacements = new ArrayList<Placement>();
                    final Iterator iterator = asJsonArray.iterator();
                    while (iterator.hasNext()) {
                        validPlacements.add(new Placement(iterator.next().getAsJsonObject()));
                    }
                    Vungle._instance.storage.setValidPlacements(validPlacements);
                    if (jsonObject.has("gdpr")) {
                        Cookie cookie;
                        if ((cookie = Vungle._instance.storage.load("consentIsImportantToVungle", Cookie.class)) == null) {
                            cookie = new Cookie("consentIsImportantToVungle");
                            cookie.putValue("consent_status", "unknown");
                            cookie.putValue("consent_source", "no_interaction");
                            cookie.putValue("timestamp", 0L);
                        }
                        final JsonObject asJsonObject = jsonObject.getAsJsonObject("gdpr");
                        final boolean b = JsonUtil.hasNonNull((JsonElement)asJsonObject, "is_country_data_protected") && asJsonObject.get("is_country_data_protected").getAsBoolean();
                        String asString;
                        if (JsonUtil.hasNonNull((JsonElement)asJsonObject, "consent_title")) {
                            asString = asJsonObject.get("consent_title").getAsString();
                        }
                        else {
                            asString = "";
                        }
                        String asString2;
                        if (JsonUtil.hasNonNull((JsonElement)asJsonObject, "consent_message")) {
                            asString2 = asJsonObject.get("consent_message").getAsString();
                        }
                        else {
                            asString2 = "";
                        }
                        String asString3;
                        if (JsonUtil.hasNonNull((JsonElement)asJsonObject, "consent_message_version")) {
                            asString3 = asJsonObject.get("consent_message_version").getAsString();
                        }
                        else {
                            asString3 = "";
                        }
                        String asString4;
                        if (JsonUtil.hasNonNull((JsonElement)asJsonObject, "button_accept")) {
                            asString4 = asJsonObject.get("button_accept").getAsString();
                        }
                        else {
                            asString4 = "";
                        }
                        String asString5;
                        if (JsonUtil.hasNonNull((JsonElement)asJsonObject, "button_deny")) {
                            asString5 = asJsonObject.get("button_deny").getAsString();
                        }
                        else {
                            asString5 = "";
                        }
                        cookie.putValue("is_country_data_protected", b);
                        String s = asString;
                        if (TextUtils.isEmpty((CharSequence)asString)) {
                            s = "Targeted Ads";
                        }
                        cookie.putValue("consent_title", s);
                        String s2 = asString2;
                        if (TextUtils.isEmpty((CharSequence)asString2)) {
                            s2 = "To receive more relevant ad content based on your interactions with our ads, click \"I Consent\" below. Either way, you will see the same amount of ads.";
                        }
                        cookie.putValue("consent_message", s2);
                        if (!"publisher".equalsIgnoreCase(cookie.getString("consent_source"))) {
                            String s3 = asString3;
                            if (TextUtils.isEmpty((CharSequence)asString3)) {
                                s3 = "";
                            }
                            cookie.putValue("consent_message_version", s3);
                        }
                        String s4 = asString4;
                        if (TextUtils.isEmpty((CharSequence)asString4)) {
                            s4 = "I Consent";
                        }
                        cookie.putValue("button_accept", s4);
                        String s5 = asString5;
                        if (TextUtils.isEmpty((CharSequence)asString5)) {
                            s5 = "I Do Not Consent";
                        }
                        cookie.putValue("button_deny", s5);
                        Vungle._instance.storage.save(cookie);
                    }
                    if (jsonObject.has("apk_direct_download") && jsonObject.getAsJsonObject("apk_direct_download").has("enabled")) {
                        final boolean asBoolean = jsonObject.getAsJsonObject("apk_direct_download").get("enabled").getAsBoolean();
                        if (asBoolean) {
                            APKDirectDownloadManager.init(Vungle.this.context);
                            int directDownloadStatus;
                            if (asBoolean) {
                                directDownloadStatus = 1;
                            }
                            else {
                                directDownloadStatus = 0;
                            }
                            APKDirectDownloadManager.setDirectDownloadStatus(directDownloadStatus);
                        }
                    }
                    else {
                        APKDirectDownloadManager.init(Vungle.this.context);
                        APKDirectDownloadManager.setDirectDownloadStatus(-1);
                    }
                    if (jsonObject.has("ri")) {
                        Cookie cookie2;
                        if ((cookie2 = Vungle._instance.storage.load("configSettings", Cookie.class)) == null) {
                            cookie2 = new Cookie("configSettings");
                        }
                        cookie2.putValue("isReportIncentivizedEnabled", jsonObject.getAsJsonObject("ri").get("enabled").getAsBoolean());
                        Vungle._instance.storage.save(cookie2);
                    }
                    if (jsonObject.has("attribution_reporting")) {
                        final JsonObject asJsonObject2 = jsonObject.getAsJsonObject("attribution_reporting");
                        if (asJsonObject2.has("should_transmit_imei")) {
                            Vungle.this.shouldTransmitIMEI = asJsonObject2.get("should_transmit_imei").getAsBoolean();
                        }
                        else {
                            Vungle.this.shouldTransmitIMEI = false;
                        }
                    }
                    else {
                        Vungle.this.shouldTransmitIMEI = false;
                    }
                    if (jsonObject.has("config")) {
                        Vungle.this.jobRunner.execute(ReconfigJob.makeJobInfo(Vungle.this.appID).setDelay(jsonObject.getAsJsonObject("config").get("refresh_time").getAsLong()));
                    }
                    Vungle.isInitialized = true;
                    initCallback.onSuccess();
                    Vungle.isInitializing.set(false);
                    final Collection<Placement> loadValidPlacements = Vungle.this.storage.loadValidPlacements();
                    Vungle.this.jobRunner.execute(CleanupJob.makeJobInfo());
                    if (loadValidPlacements != null) {
                        for (final Placement placement : loadValidPlacements) {
                            if (placement.isAutoCached()) {
                                Log.d(Vungle.TAG, "starting jobs for autocached advs");
                                Vungle.this.jobRunner.execute(DownloadJob.makeJobInfo(placement.getId(), true));
                            }
                        }
                    }
                    Vungle.this.jobRunner.execute(SendReportsJob.makeJobInfo());
                }
            }
        });
    }
    
    private void downloadAdAssets(final Advertisement advertisement, final DownloadCallback downloadCallback, final String s) {
        for (final Map.Entry<String, String> entry : advertisement.getDownloadableUrls().entrySet()) {
            if (TextUtils.isEmpty((CharSequence)entry.getKey()) || TextUtils.isEmpty((CharSequence)entry.getValue()) || !URLUtil.isValidUrl((String)entry.getValue())) {
                downloadCallback.onDownloadFailed(new VungleException(11), null);
                Log.e(Vungle.TAG, "Aborting, Failed to download Ad assets for: " + advertisement.getId());
                return;
            }
        }
        final File advertisementAssetDirectory = this.storage.getAdvertisementAssetDirectory(advertisement.getId());
        final Downloader.Listener listener = new Downloader.Listener() {
            private AtomicInteger downloadsComplete = new AtomicInteger(0);
            final /* synthetic */ int val$downloadCount = advertisement.getDownloadableUrls().size();
            
            @Override
            public void onComplete(final File mraidAssetDir) {
                if (this.downloadsComplete.incrementAndGet() == this.val$downloadCount) {
                    if (advertisement.getAdType() == 1) {
                        Log.d(Vungle.TAG, "saving MRAID for " + advertisement.getId());
                        advertisement.setMraidAssetDir(mraidAssetDir);
                        Vungle._instance.storage.save(advertisement);
                    }
                    downloadCallback.onDownloadCompleted(mraidAssetDir.getParentFile(), advertisement.getId());
                }
            }
            
            @Override
            public void onError(final Throwable t) {
                Log.e(Vungle.TAG, "Download Failed");
                Vungle._instance.storage.saveAndApplyState(advertisement, s, 4);
                downloadCallback.onDownloadFailed(t, advertisement.getId());
            }
            
            @Override
            public void onProgress(final int n) {
            }
        };
        try {
            for (final Map.Entry<String, String> entry2 : advertisement.getDownloadableUrls().entrySet()) {
                final File file = new File(advertisementAssetDirectory.getPath() + File.separator + entry2.getKey());
                if (!URLUtil.isHttpsUrl((String)entry2.getValue()) && !URLUtil.isHttpUrl((String)entry2.getValue())) {
                    goto Label_0341;
                }
                this.downloader.download(entry2.getValue(), file, (Downloader.Listener)listener);
            }
        }
        catch (IOException ex) {
            downloadCallback.onDownloadFailed(new VungleError(8), advertisement.getId());
            Log.e(Vungle.TAG, Log.getStackTraceString((Throwable)ex));
        }
        catch (IllegalStateException ex2) {
            downloadCallback.onDownloadFailed(new VungleError(8), advertisement.getId());
            Log.e(Vungle.TAG, Log.getStackTraceString((Throwable)ex2));
        }
    }
    
    private void downloadAdContent(final String s, final Advertisement advertisement, final PublisherDirectDownload publisherDirectDownload, @NonNull final DownloadCallback downloadCallback) {
        DownloadStrategy downloadStrategy2;
        final DownloadStrategy downloadStrategy = downloadStrategy2 = null;
        if (publisherDirectDownload != null) {
            downloadStrategy2 = downloadStrategy;
            if (!TextUtils.isEmpty((CharSequence)advertisement.getAdMarketId())) {
                downloadStrategy2 = new DirectDownloadStrategy(publisherDirectDownload);
            }
        }
        if (downloadStrategy2 != null) {
            downloadStrategy2.isApplicationAvailable(advertisement.getAdMarketId(), (DownloadStrategy.VerificationCallback)new DownloadStrategy.VerificationCallback() {
                @Override
                public void onResult(final boolean b) {
                    if (b) {
                        Log.d(Vungle.TAG, "fetchAdMetadata: downloading assets ");
                        Vungle.this.downloadAdAssets(advertisement, downloadCallback, s);
                        return;
                    }
                    downloadCallback.onDownloadFailed(new VungleException(5), null);
                }
            });
            return;
        }
        this.downloadAdAssets(advertisement, downloadCallback, s);
    }
    
    private void fetchAdMetadata(final String s, final PublisherDirectDownload publisherDirectDownload, @NonNull final DownloadCallback downloadCallback, final HeaderBiddingCallback headerBiddingCallback) {
        VungleApiClient.requestAd(s, headerBiddingCallback != null).enqueue((Callback)new Callback<JsonObject>() {
            public void onFailure(final Call<JsonObject> call, final Throwable t) {
                downloadCallback.onDownloadFailed(t, null);
            }
            
            public void onResponse(Call<JsonObject> placement, Response<JsonObject> asJsonObject) {
                if (asJsonObject == null) {
                    downloadCallback.onDownloadFailed(new VungleException(1), null);
                    return;
                }
                if (!((Response)asJsonObject).isSuccessful()) {
                    final long retryAfterHeaderValue = VungleApiClient.getRetryAfterHeaderValue((Response<JsonObject>)asJsonObject);
                    final Placement placement2 = Vungle._instance.storage.load(s, Placement.class);
                    if (retryAfterHeaderValue > 0L && placement2 != null && placement2.isAutoCached()) {
                        Vungle.this.jobRunner.execute(DownloadJob.makeJobInfo(s, true).setDelay(retryAfterHeaderValue));
                        downloadCallback.onDownloadFailed(new VungleException(14), null);
                        return;
                    }
                    Log.e(Vungle.TAG, "Failed to retrieve advertisement information");
                    downloadCallback.onDownloadFailed(new VungleException(2), null);
                }
                else {
                    placement = Vungle.this.storage.load(s, Placement.class);
                    if (placement == null) {
                        Log.e(Vungle.TAG, "Placement metadata not found for requested advertisement.");
                        downloadCallback.onDownloadFailed(new VungleException(2), null);
                        return;
                    }
                    final JsonObject jsonObject = (JsonObject)((Response)asJsonObject).body();
                    if (jsonObject != null && jsonObject.has("ads") && !jsonObject.get("ads").isJsonNull()) {
                        final JsonArray asJsonArray = jsonObject.getAsJsonArray("ads");
                        if (asJsonArray == null || asJsonArray.size() == 0) {
                            downloadCallback.onDownloadFailed(new VungleException(1), null);
                            return;
                        }
                        asJsonObject = asJsonArray.get(0).getAsJsonObject();
                        try {
                            final Advertisement advertisement = new Advertisement(asJsonObject);
                            if (headerBiddingCallback != null) {
                                headerBiddingCallback.onBidTokenAvailable(s, advertisement.getBidToken());
                            }
                            Vungle._instance.storage.saveAndApplyState(advertisement, s, 0);
                            Vungle.this.downloadAdContent(s, advertisement, publisherDirectDownload, downloadCallback);
                            return;
                        }
                        catch (IllegalArgumentException ex) {
                            final JsonObject asJsonObject2 = asJsonObject.getAsJsonObject("ad_markup");
                            if (asJsonObject2.has("sleep")) {
                                final int asInt = asJsonObject2.get("sleep").getAsInt();
                                placement.snooze(asInt);
                                Vungle.this.storage.save(placement);
                                if (placement.isAutoCached()) {
                                    Vungle.this.jobRunner.execute(DownloadJob.makeJobInfo(s, true).setDelay(asInt * 1000));
                                }
                            }
                            downloadCallback.onDownloadFailed(new VungleException(1), null);
                            return;
                        }
                    }
                    downloadCallback.onDownloadFailed(new VungleError(0), null);
                }
            }
        });
    }
    
    public static String getConsentMessageVersion() {
        if (!isInitialized()) {
            Log.e(Vungle.TAG, "Vungle is not initialized, please wait initialize or wait until Vungle is intialized to get Consent Message Version");
        }
        else {
            final Cookie cookie = Vungle._instance.storage.load("consentIsImportantToVungle", Cookie.class);
            if (cookie != null) {
                return cookie.getString("consent_message_version");
            }
        }
        return null;
    }
    
    public static Consent getConsentStatus() {
        if (!isInitialized()) {
            Log.e(Vungle.TAG, "Vungle is not initialized, consent is null");
        }
        else {
            final Cookie cookie = Vungle._instance.storage.load("consentIsImportantToVungle", Cookie.class);
            if (cookie != null) {
                if ("opted_in".equals(cookie.getString("consent_status"))) {
                    return Consent.OPTED_IN;
                }
                return Consent.OPTED_OUT;
            }
        }
        return null;
    }
    
    public static VungleNativeAd getNativeAd(final String s, final PlayAdCallback playAdCallback) {
        return getNativeAd(s, playAdCallback, null);
    }
    
    public static VungleNativeAd getNativeAd(final String s, final PlayAdCallback playAdCallback, final PublisherDirectDownload publisherDirectDownload) {
        if (!isInitialized()) {
            Log.e(Vungle.TAG, "Vungle is not initialized, returned VungleNativeAd = null");
        }
        else {
            final Placement placement = Vungle._instance.storage.load(s, Placement.class);
            if (placement == null) {
                playAdCallback.onError(s, new Throwable("No placement for ID " + s + " found. Please use a valid placement ID"));
                return null;
            }
            final Advertisement validAdvertisementForPlacement = Vungle._instance.storage.findValidAdvertisementForPlacement(s);
            if (validAdvertisementForPlacement == null) {
                Log.e(Vungle.TAG, "No Advertisement for ID");
                return null;
            }
            if (!canPlayAd(validAdvertisementForPlacement)) {
                if (validAdvertisementForPlacement != null && validAdvertisementForPlacement.getState() == 1) {
                    Vungle._instance.storage.saveAndApplyState(validAdvertisementForPlacement, s, 4);
                    if (placement.isAutoCached()) {
                        Vungle._instance.jobRunner.execute(DownloadJob.makeJobInfo(placement.getId(), true));
                        return null;
                    }
                }
            }
            else {
                if (Boolean.TRUE.equals(Vungle._instance.playOperations.get(s)) || Boolean.TRUE.equals(Vungle._instance.loadOperations.get(s))) {
                    playAdCallback.onError(s, new VungleException(8));
                    return null;
                }
                if (validAdvertisementForPlacement.getAdType() != 1) {
                    playAdCallback.onError(s, new Throwable(s + " is not an MRAID compatible placement. Please use a valid placement ID"));
                    return null;
                }
                DirectDownloadAdapter directDownloadAdapter = null;
                if (publisherDirectDownload != null) {
                    directDownloadAdapter = new DirectDownloadAdapter(publisherDirectDownload, validAdvertisementForPlacement.getAdMarketId());
                }
                Vungle._instance.playOperations.put(s, true);
                return new VungleNativeView(Vungle._instance.context.getApplicationContext(), s, directDownloadAdapter, new AdvertisementPresenter.EventListener() {
                    int percentViewed = -1;
                    boolean succesfulView = false;
                    
                    @Override
                    public void onError(final Throwable t) {
                        Vungle._instance.playOperations.put(s, false);
                        Vungle._instance.storage.saveAndApplyState(validAdvertisementForPlacement, s, 4);
                        if (playAdCallback != null) {
                            playAdCallback.onError(s, t);
                        }
                    }
                    
                    @Override
                    public void onNext(final String s, final String s2) {
                        boolean b = true;
                        if (s.equals("start")) {
                            Vungle._instance.storage.saveAndApplyState(validAdvertisementForPlacement, s, 2);
                            if (playAdCallback != null) {
                                playAdCallback.onAdStart(s);
                            }
                            this.percentViewed = 0;
                            final Placement placement = Vungle._instance.storage.load(s, Placement.class);
                            if (placement != null && placement.isAutoCached()) {
                                Vungle._instance.jobRunner.execute(DownloadJob.makeJobInfo(s, true));
                            }
                        }
                        else if (s.equals("end")) {
                            Log.d("Vungle", "Cleaning up metadata and assets for placement " + s + " and advertisement " + validAdvertisementForPlacement.getId());
                            Vungle._instance.storage.saveAndApplyState(validAdvertisementForPlacement, s, 3);
                            Vungle._instance.jobRunner.execute(SendReportsJob.makeJobInfo());
                            Vungle._instance.playOperations.put(s, false);
                            if (playAdCallback != null) {
                                final PlayAdCallback val$playAdCallback = playAdCallback;
                                final String val$placementId = s;
                                final boolean b2 = this.succesfulView || this.percentViewed >= 80;
                                if (s2 == null || !s2.equals("isCTAClicked")) {
                                    b = false;
                                }
                                val$playAdCallback.onAdEnd(val$placementId, b2, b);
                            }
                        }
                        else {
                            if (s.equals("successfulView")) {
                                this.succesfulView = true;
                                return;
                            }
                            if (s.startsWith("percentViewed")) {
                                final String[] split = s.split(":");
                                if (split.length == 2) {
                                    this.percentViewed = Integer.parseInt(split[1]);
                                }
                            }
                        }
                    }
                });
            }
        }
        return null;
    }
    
    public static Collection<String> getValidPlacements() {
        if (!isInitialized()) {
            Log.e(Vungle.TAG, "Vungle is not initialized return empty placemetns list");
            return (Collection<String>)Collections.emptyList();
        }
        return Vungle._instance.storage.getValidPlacements();
    }
    
    static void handleApkDirectDownloads(final Context context) {
        APKDirectDownloadManager.handleDownload(context);
    }
    
    public static void init(@NonNull final String s, @NonNull final Context context, @NonNull final InitCallback initCallback) throws IllegalArgumentException {
        init(s, context, initCallback, null);
    }
    
    public static void init(@NonNull final String appID, @NonNull final Context context, @NonNull final InitCallback initCallback, final PublisherDirectDownload publisherDirectDownload) throws IllegalArgumentException {
        if (initCallback == null) {
            throw new IllegalArgumentException("A valid InitCallback required to ensure API calls are being made after initialize is successful");
        }
        if (context == null || appID == null || appID.isEmpty()) {
            initCallback.onError(new VungleException(6));
            return;
        }
        if (!(context instanceof Application)) {
            initCallback.onError(new VungleException(7));
            return;
        }
        if (isInitialized()) {
            Log.d(Vungle.TAG, "init already complete");
            initCallback.onSuccess();
            return;
        }
        if (Vungle.isInitializing.getAndSet(true)) {
            Log.d(Vungle.TAG, "init ongoing");
            initCallback.onError(new VungleException(8));
            return;
        }
        if (!Vungle.isDepInit.getAndSet(true)) {
            final GraphicDesigner graphicDesigner = new GraphicDesigner(context.getCacheDir());
            final Storage instance = Storage.makeInstance(new FilePersistor(context.getFilesDir()), graphicDesigner);
            Vungle._instance.context = context;
            Vungle._instance.appID = appID;
            Vungle._instance.downloader = new FetchDownloader(context);
            Vungle._instance.storage = instance;
            Vungle._instance.jobRunner = new VungleJobRunner(new VungleJobCreator(Vungle._instance.storage, graphicDesigner, new ReconfigJob.ReconfigCall() {
                @Override
                public void reConfigVungle() {
                    Vungle.reConfigure();
                }
            }));
            instance.init(1);
            VungleApiClient.init(context, appID, graphicDesigner.getCacheDirectory().getPath(), instance);
            if (!TextUtils.isEmpty((CharSequence)Vungle._instance.userIMEI)) {
                VungleApiClient.updateIMEI(Vungle._instance.userIMEI, Vungle._instance.shouldTransmitIMEI);
            }
            if (Vungle._instance.tempConsent != null && !TextUtils.isEmpty((CharSequence)Vungle._instance.tempConsentVersion)) {
                updateConsentStatus(Vungle._instance.tempConsent, Vungle._instance.tempConsentVersion);
            }
            Cookie cookie;
            if ((cookie = Vungle._instance.storage.load("appId", Cookie.class)) == null) {
                cookie = new Cookie("appId");
            }
            cookie.putValue("appId", appID);
            Vungle._instance.storage.save(cookie);
        }
        Vungle._instance.initCallback = initCallback;
        Vungle._instance.publisherDirectDownload = publisherDirectDownload;
        Vungle._instance.configure(initCallback);
    }
    
    @Deprecated
    public static void init(@NonNull final Collection<String> collection, @NonNull final String s, @NonNull final Context context, @NonNull final InitCallback initCallback) throws IllegalArgumentException {
        init(s, context, initCallback, null);
    }
    
    @Deprecated
    public static void init(@NonNull final Collection<String> collection, @NonNull final String s, @NonNull final Context context, @NonNull final InitCallback initCallback, final PublisherDirectDownload publisherDirectDownload) throws IllegalArgumentException {
        init(s, context, initCallback, publisherDirectDownload);
    }
    
    public static boolean isInitialized() {
        return Vungle.isInitialized && Vungle._instance.storage != null && Vungle._instance.storage.getValidPlacements() != null && Vungle._instance.storage.getValidPlacements().size() > 0 && Vungle._instance.context != null;
    }
    
    public static void loadAd(@NonNull final String s, @Nullable final LoadAdCallback loadAdCallback) {
        loadAd(s, loadAdCallback, Vungle._instance.publisherDirectDownload);
    }
    
    public static void loadAd(@NonNull final String s, @Nullable final LoadAdCallback loadAdCallback, final PublisherDirectDownload publisherDirectDownload) {
        if (!isInitialized()) {
            if (loadAdCallback != null) {
                loadAdCallback.onError(s, new VungleException(9));
            }
        }
        else {
            final Placement placement = Vungle._instance.storage.load(s, Placement.class);
            if (placement == null) {
                if (loadAdCallback != null) {
                    loadAdCallback.onError(s, new IllegalArgumentException("Placement ID " + s + " is not valid. Please check your configuration on the vungle dashboard."));
                }
            }
            else if (Boolean.TRUE.equals(Vungle._instance.loadOperations.get(s))) {
                if (loadAdCallback != null) {
                    loadAdCallback.onError(s, new VungleException(8));
                }
            }
            else {
                final Advertisement validAdvertisementForPlacement = Vungle._instance.storage.findValidAdvertisementForPlacement(placement.getId());
                if (canPlayAd(validAdvertisementForPlacement)) {
                    Log.i(Vungle.TAG, "found already cached valid adv, calling onAdLoad " + s + " callback ");
                    if (placement.isAutoCached()) {
                        Vungle._instance.initCallback.onAutoCacheAdAvailable(s);
                    }
                    if (loadAdCallback != null) {
                        loadAdCallback.onAdLoad(s);
                    }
                }
                else {
                    Log.i(Vungle.TAG, "didn't find cached adv for " + s + " downloading ");
                    if (placement.getWakeupTime() > System.currentTimeMillis()) {
                        if (loadAdCallback != null) {
                            loadAdCallback.onError(s, new VungleException(1));
                            Log.w(Vungle.TAG, "Placement " + placement.getId() + " is  snoozed");
                        }
                        if (placement.isAutoCached()) {
                            Log.d(Vungle.TAG, "Placement " + placement.getId() + " is sleeping rescheduling it ");
                            Vungle._instance.jobRunner.execute(DownloadJob.makeJobInfo(placement.getId(), true).setDelay(placement.getWakeupTime() - System.currentTimeMillis()));
                        }
                    }
                    else {
                        Vungle._instance.loadOperations.put(s, true);
                        final DownloadCallback downloadCallback = new DownloadCallback() {
                            @Override
                            public void onDownloadCompleted(final File file, final String s) {
                                Log.d(Vungle.TAG, "download completed " + s);
                                Advertisement advertisement;
                                if (s == null) {
                                    advertisement = null;
                                }
                                else {
                                    advertisement = Vungle._instance.storage.load(s, Advertisement.class);
                                }
                                final Placement placement = Vungle._instance.storage.load(s, Placement.class);
                                if (advertisement == null || placement == null) {
                                    this.onDownloadFailed(new IllegalStateException("Didn't find adv"), s);
                                }
                                else {
                                    Vungle._instance.storage.saveAndApplyState(advertisement, s, 1);
                                    Log.d("Vungle", String.format(Locale.ENGLISH, "Downloaded assets for %s to %s", s, file.getAbsolutePath()));
                                    FileUtility.printDirectoryTree(file);
                                    Vungle._instance.loadOperations.put(s, false);
                                    if (Vungle._instance.headerBiddingCallback != null) {
                                        Vungle._instance.headerBiddingCallback.adAvailableForBidToken(s, advertisement.getBidToken());
                                    }
                                    if (placement.isAutoCached()) {
                                        Vungle._instance.initCallback.onAutoCacheAdAvailable(s);
                                    }
                                    if (loadAdCallback != null) {
                                        loadAdCallback.onAdLoad(s);
                                    }
                                }
                            }
                            
                            @Override
                            public void onDownloadFailed(final Throwable t, final String s) {
                                Advertisement advertisement;
                                if (s == null) {
                                    advertisement = null;
                                }
                                else {
                                    advertisement = Vungle._instance.storage.load(s, Advertisement.class);
                                }
                                final Placement placement = Vungle._instance.storage.load(s, Placement.class);
                                if (advertisement != null && placement != null) {
                                    Vungle._instance.storage.saveAndApplyState(advertisement, s, 4);
                                }
                                Log.e("Vungle", "Failed to download assets for " + s + ". Cause:", t);
                                Vungle._instance.loadOperations.put(s, false);
                                if (loadAdCallback != null) {
                                    loadAdCallback.onError(s, t);
                                }
                            }
                        };
                        if (validAdvertisementForPlacement != null && validAdvertisementForPlacement.getState() == 0) {
                            Log.d(Vungle.TAG, "Found valid adv but not ready - downloading content");
                            Vungle.handler.post((Runnable)new Runnable() {
                                @Override
                                public void run() {
                                    Vungle._instance.downloadAdContent(s, validAdvertisementForPlacement, publisherDirectDownload, downloadCallback);
                                }
                            });
                            return;
                        }
                        if (validAdvertisementForPlacement != null && validAdvertisementForPlacement.getState() == 1) {
                            Vungle._instance.storage.saveAndApplyState(validAdvertisementForPlacement, s, 4);
                        }
                        Log.d(Vungle.TAG, "No adv for placement " + placement.getId() + " getting new data ");
                        Vungle._instance.fetchAdMetadata(s, publisherDirectDownload, (DownloadCallback)downloadCallback, Vungle._instance.headerBiddingCallback);
                    }
                }
            }
        }
    }
    
    public static void playAd(@NonNull final String s, final AdConfig adConfig, @Nullable final PlayAdCallback playAdCallback) {
        if (!isInitialized()) {
            if (playAdCallback != null) {
                playAdCallback.onError(s, new VungleException(9));
            }
        }
        else {
            final Placement placement = Vungle._instance.storage.load(s, Placement.class);
            Throwable t = null;
            if (Boolean.TRUE.equals(Vungle._instance.playOperations.get(s)) || Boolean.TRUE.equals(Vungle._instance.loadOperations.get(s))) {
                t = new VungleException(8);
            }
            if (placement == null) {
                t = new VungleException(13);
            }
            if (t != null) {
                if (playAdCallback != null) {
                    playAdCallback.onError(s, t);
                }
            }
            else {
                boolean b = false;
                final Advertisement validAdvertisementForPlacement = Vungle._instance.storage.findValidAdvertisementForPlacement(s);
                if (!canPlayAd(validAdvertisementForPlacement)) {
                    final boolean b2 = true;
                    if (validAdvertisementForPlacement != null && validAdvertisementForPlacement.getState() == 1) {
                        Vungle._instance.storage.saveAndApplyState(validAdvertisementForPlacement, s, 4);
                        if (placement.isAutoCached()) {
                            Vungle._instance.jobRunner.execute(DownloadJob.makeJobInfo(placement.getId(), true));
                        }
                    }
                    b = b2;
                    if (playAdCallback != null) {
                        playAdCallback.onError(s, new VungleException(10));
                        b = b2;
                    }
                }
                else {
                    validAdvertisementForPlacement.configure(adConfig);
                    Vungle._instance.storage.save(validAdvertisementForPlacement);
                }
                if (Vungle._instance.context != null) {
                    final String id = placement.getId();
                    final boolean autoCached = placement.isAutoCached();
                    String adToken;
                    if (b) {
                        adToken = "";
                    }
                    else {
                        adToken = validAdvertisementForPlacement.getAdToken();
                    }
                    VungleApiClient.willPlayAd(id, autoCached, adToken).enqueue((Callback)new Callback<JsonObject>() {
                        public void onFailure(final Call<JsonObject> call, final Throwable t) {
                            if (b) {
                                if (playAdCallback != null) {
                                    playAdCallback.onError(s, new VungleException(1));
                                }
                                return;
                            }
                            renderAd(s, playAdCallback, s, validAdvertisementForPlacement);
                        }
                        
                        public void onResponse(final Call<JsonObject> p0, final Response<JsonObject> p1) {
                            // 
                            // This method could not be decompiled.
                            // 
                            // Original Bytecode:
                            // 
                            //     1: astore          5
                            //     3: aconst_null    
                            //     4: astore_3       
                            //     5: aconst_null    
                            //     6: astore          4
                            //     8: aconst_null    
                            //     9: astore          6
                            //    11: aload           6
                            //    13: astore_1       
                            //    14: aload_2        
                            //    15: invokevirtual   retrofit2/Response.isSuccessful:()Z
                            //    18: ifeq            78
                            //    21: aload_2        
                            //    22: invokevirtual   retrofit2/Response.body:()Ljava/lang/Object;
                            //    25: checkcast       Lcom/google/gson/JsonObject;
                            //    28: astore_2       
                            //    29: aload           6
                            //    31: astore_1       
                            //    32: aload_2        
                            //    33: ldc             "ad"
                            //    35: invokevirtual   com/google/gson/JsonObject.has:(Ljava/lang/String;)Z
                            //    38: ifeq            78
                            //    41: new             Lcom/vungle/warren/model/Advertisement;
                            //    44: dup            
                            //    45: aload_2        
                            //    46: ldc             "ad"
                            //    48: invokevirtual   com/google/gson/JsonObject.getAsJsonObject:(Ljava/lang/String;)Lcom/google/gson/JsonObject;
                            //    51: invokespecial   com/vungle/warren/model/Advertisement.<init>:(Lcom/google/gson/JsonObject;)V
                            //    54: astore_1       
                            //    55: aload_1        
                            //    56: aload_0        
                            //    57: getfield        com/vungle/warren/Vungle$3.val$settings:Lcom/vungle/warren/AdConfig;
                            //    60: invokevirtual   com/vungle/warren/model/Advertisement.configure:(Lcom/vungle/warren/AdConfig;)V
                            //    63: getstatic       com/vungle/warren/Vungle._instance:Lcom/vungle/warren/Vungle;
                            //    66: getfield        com/vungle/warren/Vungle.storage:Lcom/vungle/warren/Storage;
                            //    69: aload_1        
                            //    70: aload_0        
                            //    71: getfield        com/vungle/warren/Vungle$3.val$id:Ljava/lang/String;
                            //    74: iconst_0       
                            //    75: invokevirtual   com/vungle/warren/Storage.saveAndApplyState:(Lcom/vungle/warren/model/Advertisement;Ljava/lang/String;I)V
                            //    78: aload_0        
                            //    79: getfield        com/vungle/warren/Vungle$3.val$finalStreamingOnly:Z
                            //    82: ifeq            202
                            //    85: aload_1        
                            //    86: ifnonnull       185
                            //    89: aload_0        
                            //    90: getfield        com/vungle/warren/Vungle$3.val$listener:Lcom/vungle/warren/PlayAdCallback;
                            //    93: ifnull          117
                            //    96: aload_0        
                            //    97: getfield        com/vungle/warren/Vungle$3.val$listener:Lcom/vungle/warren/PlayAdCallback;
                            //   100: aload_0        
                            //   101: getfield        com/vungle/warren/Vungle$3.val$id:Ljava/lang/String;
                            //   104: new             Lcom/vungle/warren/error/VungleException;
                            //   107: dup            
                            //   108: iconst_1       
                            //   109: invokespecial   com/vungle/warren/error/VungleException.<init>:(I)V
                            //   112: invokeinterface com/vungle/warren/PlayAdCallback.onError:(Ljava/lang/String;Ljava/lang/Throwable;)V
                            //   117: return         
                            //   118: astore_1       
                            //   119: aload           5
                            //   121: astore_1       
                            //   122: ldc             "Vungle"
                            //   124: ldc             "Will Play Ad did not respond with a replacement. Move on."
                            //   126: invokestatic    android/util/Log.v:(Ljava/lang/String;Ljava/lang/String;)I
                            //   129: pop            
                            //   130: goto            78
                            //   133: astore_2       
                            //   134: aload_3        
                            //   135: astore_1       
                            //   136: ldc             "Vungle"
                            //   138: ldc             "Error using will_play_ad!"
                            //   140: aload_2        
                            //   141: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
                            //   144: pop            
                            //   145: goto            78
                            //   148: astore_2       
                            //   149: aload           4
                            //   151: astore_1       
                            //   152: aload_2        
                            //   153: invokevirtual   com/vungle/warren/error/VungleError.getErrorCode:()I
                            //   156: bipush          6
                            //   158: if_icmpeq       173
                            //   161: ldc             "Vungle"
                            //   163: ldc             "Error using will_play_ad!"
                            //   165: aload_2        
                            //   166: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
                            //   169: pop            
                            //   170: goto            78
                            //   173: invokestatic    com/vungle/warren/Vungle.access$400:()Ljava/lang/String;
                            //   176: ldc             "will_play_ad was disabled by the configuration settings. This is expected."
                            //   178: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
                            //   181: pop            
                            //   182: goto            78
                            //   185: aload_0        
                            //   186: getfield        com/vungle/warren/Vungle$3.val$id:Ljava/lang/String;
                            //   189: aload_0        
                            //   190: getfield        com/vungle/warren/Vungle$3.val$listener:Lcom/vungle/warren/PlayAdCallback;
                            //   193: aload_0        
                            //   194: getfield        com/vungle/warren/Vungle$3.val$id:Ljava/lang/String;
                            //   197: aload_1        
                            //   198: invokestatic    com/vungle/warren/Vungle.access$900:(Ljava/lang/String;Lcom/vungle/warren/PlayAdCallback;Ljava/lang/String;Lcom/vungle/warren/model/Advertisement;)V
                            //   201: return         
                            //   202: aload_0        
                            //   203: getfield        com/vungle/warren/Vungle$3.val$id:Ljava/lang/String;
                            //   206: aload_0        
                            //   207: getfield        com/vungle/warren/Vungle$3.val$listener:Lcom/vungle/warren/PlayAdCallback;
                            //   210: aload_0        
                            //   211: getfield        com/vungle/warren/Vungle$3.val$id:Ljava/lang/String;
                            //   214: aload_0        
                            //   215: getfield        com/vungle/warren/Vungle$3.val$finalAdvertisement:Lcom/vungle/warren/model/Advertisement;
                            //   218: invokestatic    com/vungle/warren/Vungle.access$900:(Ljava/lang/String;Lcom/vungle/warren/PlayAdCallback;Ljava/lang/String;Lcom/vungle/warren/model/Advertisement;)V
                            //   221: return         
                            //   222: astore_2       
                            //   223: goto            152
                            //   226: astore_2       
                            //   227: goto            136
                            //   230: astore_2       
                            //   231: goto            122
                            //    Signature:
                            //  (Lretrofit2/Call<Lcom/google/gson/JsonObject;>;Lretrofit2/Response<Lcom/google/gson/JsonObject;>;)V
                            //    Exceptions:
                            //  Try           Handler
                            //  Start  End    Start  End    Type                                 
                            //  -----  -----  -----  -----  -------------------------------------
                            //  41     55     118    122    Ljava/lang/IllegalArgumentException;
                            //  41     55     133    136    Ljava/lang/Exception;
                            //  41     55     148    152    Lcom/vungle/warren/error/VungleError;
                            //  55     78     230    234    Ljava/lang/IllegalArgumentException;
                            //  55     78     226    230    Ljava/lang/Exception;
                            //  55     78     222    226    Lcom/vungle/warren/error/VungleError;
                            // 
                            // The error that occurred was:
                            // 
                            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0078:
                            //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
                            //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2596)
                            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
                            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
                            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
                            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
                            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
                            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
                            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
                            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
                            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
                            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
                            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1164)
                            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1009)
                            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
                            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:440)
                            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:441)
                            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:441)
                            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
                            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
                            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
                            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
                            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
                            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
                            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
                            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
                            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
                            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
                            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
                            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
                            //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
                            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
                            // 
                            throw new IllegalStateException("An error occurred while decompiling this method.");
                        }
                    });
                }
            }
        }
    }
    
    static void reConfigure() {
        if (isInitialized()) {
            Vungle._instance.configure(Vungle._instance.initCallback);
            return;
        }
        init(Vungle._instance.appID, Vungle._instance.context, Vungle._instance.initCallback, Vungle._instance.publisherDirectDownload);
    }
    
    private static void renderAd(@NonNull final String s, @Nullable final PlayAdCallback playAdCallback, final String s2, final Advertisement advertisement) {
        int n = 1;
        Vungle._instance.playOperations.put(s, true);
        VungleActivity.setEventListener(new AdvertisementPresenter.EventListener() {
            int percentViewed = -1;
            boolean succesfulView = false;
            
            @Override
            public void onError(final Throwable t) {
                Vungle._instance.storage.saveAndApplyState(advertisement, s, 4);
                Vungle._instance.playOperations.put(s, false);
                if (playAdCallback != null) {
                    playAdCallback.onError(s, t);
                }
            }
            
            @Override
            public void onNext(final String s, final String s2) {
                boolean b = true;
                if (s.equals("start")) {
                    Vungle._instance.storage.saveAndApplyState(advertisement, s, 2);
                    if (playAdCallback != null) {
                        playAdCallback.onAdStart(s);
                    }
                    this.percentViewed = 0;
                    final Placement placement = Vungle._instance.storage.load(s2, Placement.class);
                    if (placement != null && placement.isAutoCached()) {
                        Vungle._instance.jobRunner.execute(DownloadJob.makeJobInfo(s2, true));
                    }
                }
                else if (s.equals("end")) {
                    Log.d("Vungle", "Cleaning up metadata and assets for placement " + s + " and advertisement " + advertisement.getId());
                    Vungle._instance.storage.saveAndApplyState(advertisement, s, 3);
                    Vungle._instance.playOperations.put(s, false);
                    Vungle._instance.jobRunner.execute(SendReportsJob.makeJobInfo());
                    if (playAdCallback != null) {
                        final PlayAdCallback val$listener = playAdCallback;
                        final String val$id = s;
                        final boolean b2 = this.succesfulView || this.percentViewed >= 80;
                        if (s2 == null || !s2.equals("isCTAClicked")) {
                            b = false;
                        }
                        val$listener.onAdEnd(val$id, b2, b);
                    }
                }
                else {
                    if (s.equals("successfulView")) {
                        this.succesfulView = true;
                        return;
                    }
                    if (s.startsWith("percentViewed")) {
                        final String[] split = s.split(":");
                        if (split.length == 2) {
                            this.percentViewed = Integer.parseInt(split[1]);
                        }
                    }
                }
            }
        });
        if (advertisement == null || !"flexview".equals(advertisement.getTemplateType())) {
            n = 0;
        }
        final Context context = Vungle._instance.context;
        Serializable s3;
        if (n != 0) {
            s3 = VungleFlexViewActivity.class;
        }
        else {
            s3 = VungleActivity.class;
        }
        final Intent intent = new Intent(context, (Class)s3);
        intent.addFlags(268435456);
        intent.putExtra("placement", s);
        Vungle._instance.context.startActivity(intent);
    }
    
    public static void setHeaderBiddingCallback(final HeaderBiddingCallback headerBiddingCallback) {
        Vungle._instance.headerBiddingCallback = headerBiddingCallback;
    }
    
    public static void setIncentivizedFields(@Nullable final String s, @Nullable final String s2, @Nullable final String s3, @Nullable final String s4, @Nullable final String s5) {
        if (!isInitialized()) {
            Log.e(Vungle.TAG, "Vungle is not initialized");
        }
        else {
            Cookie cookie;
            if ((cookie = Vungle._instance.storage.load("incentivizedTextSetByPub", Cookie.class)) == null) {
                cookie = new Cookie("incentivizedTextSetByPub");
            }
            boolean b = false;
            if (!TextUtils.isEmpty((CharSequence)s2)) {
                b = true;
                cookie.putValue("title", s2);
            }
            if (!TextUtils.isEmpty((CharSequence)s3)) {
                b = true;
                cookie.putValue("body", s3);
            }
            if (!TextUtils.isEmpty((CharSequence)s4)) {
                b = true;
                cookie.putValue("continue", s4);
            }
            if (!TextUtils.isEmpty((CharSequence)s5)) {
                b = true;
                cookie.putValue("close", s5);
            }
            if (!TextUtils.isEmpty((CharSequence)s)) {
                b = true;
                cookie.putValue("userID", s);
            }
            if (b) {
                Vungle._instance.storage.save(cookie);
            }
        }
    }
    
    public static void setUserLegacyID(final String userIMEI) {
        if (isInitialized() || Vungle.isInitializing.get()) {
            VungleApiClient.updateIMEI(userIMEI, Vungle._instance.shouldTransmitIMEI);
            return;
        }
        Vungle._instance.userIMEI = userIMEI;
    }
    
    public static void updateConsentStatus(@NonNull final Consent tempConsent, @NonNull final String tempConsentVersion) {
        if (!Vungle.isDepInit.get()) {
            Vungle._instance.tempConsent = tempConsent;
            Vungle._instance.tempConsentVersion = tempConsentVersion;
            return;
        }
        Cookie cookie;
        if ((cookie = Vungle._instance.storage.load("consentIsImportantToVungle", Cookie.class)) == null) {
            cookie = new Cookie("consentIsImportantToVungle");
        }
        String s;
        if (tempConsent == Consent.OPTED_IN) {
            s = "opted_in";
        }
        else {
            s = "opted_out";
        }
        cookie.putValue("consent_status", s);
        cookie.putValue("timestamp", System.currentTimeMillis() / 1000L);
        cookie.putValue("consent_source", "publisher");
        String s2 = tempConsentVersion;
        if (tempConsentVersion == null) {
            s2 = "";
        }
        cookie.putValue("consent_message_version", s2);
        Vungle._instance.storage.save(cookie);
        Vungle._instance.tempConsent = null;
        Vungle._instance.tempConsentVersion = null;
    }
    
    public enum Consent
    {
        OPTED_IN, 
        OPTED_OUT;
    }
    
    interface DownloadCallback
    {
        void onDownloadCompleted(final File p0, final String p1);
        
        void onDownloadFailed(final Throwable p0, final String p1);
    }
}
