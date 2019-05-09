package com.vungle.warren.presenter;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.webkit.WebViewClient;
import android.widget.VideoView;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.moat.analytics.mobile.vng.MoatAdEvent;
import com.moat.analytics.mobile.vng.MoatAdEventType;
import com.moat.analytics.mobile.vng.MoatFactory;
import com.moat.analytics.mobile.vng.MoatOptions;
import com.moat.analytics.mobile.vng.ReactiveVideoTracker;
import com.moat.analytics.mobile.vng.ReactiveVideoTrackerPlugin;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyConstants;
import com.vungle.warren.DirectDownloadAdapter;
import com.vungle.warren.Storage;
import com.vungle.warren.download.APKDirectDownloadManager;
import com.vungle.warren.error.VungleException;
import com.vungle.warren.model.Advertisement;
import com.vungle.warren.model.Advertisement.Checkpoint;
import com.vungle.warren.model.Cookie;
import com.vungle.warren.model.Placement;
import com.vungle.warren.model.Report;
import com.vungle.warren.network.VungleApiClient;
import com.vungle.warren.presenter.AdvertisementPresenter.EventListener;
import com.vungle.warren.ui.AdView;
import com.vungle.warren.ui.VungleWebClient;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocalAdPresenter implements AdvertisementPresenter {
    private static final String EXTRA_INCENTIVIZED_SENT = "incentivized_sent";
    private static final String EXTRA_IN_POST = "in_post_roll";
    private static final String EXTRA_REPORT = "saved_report";
    private static final String TAG = "LocalAdPresenter";
    private HashMap<String, String> adIds;
    private AdView adView;
    private Advertisement advertisement;
    private File assetDir;
    private EventListener bus;
    private Checkpoint checkpoint;
    private byte checkpointReached;
    private String dialogBody = "If you exit now, you will not get your reward";
    private String dialogClose = "Close";
    private String dialogContinue = "Continue";
    private String dialogTitle = "Are you sure?";
    private boolean directDownloadApkEnabled;
    private int duration;
    private Handler handler = new Handler(Looper.getMainLooper());
    private boolean inPost;
    private AtomicBoolean isDestroying = new AtomicBoolean(false);
    private Queue<Pair<Integer, MoatAdEventType>> moatQuartileTrackers;
    private boolean muted;
    private Placement placement;
    private Report report;
    private AtomicBoolean sendReportIncentivized = new AtomicBoolean(false);
    private Storage storage;
    private boolean userExitEnabled;
    private String userID;
    private ReactiveVideoTracker videoTracker;
    private VideoView videoView;
    private VungleWebClient webClient;

    /* renamed from: com.vungle.warren.presenter.LocalAdPresenter$1 */
    class C01451 implements Runnable {
        C01451() {
        }

        public void run() {
            LocalAdPresenter.this.adView.showCTAOverlay(LocalAdPresenter.this.advertisement.isCtaShowOnClick(), false, LocalAdPresenter.this.advertisement.getCtaClickArea());
        }
    }

    /* renamed from: com.vungle.warren.presenter.LocalAdPresenter$2 */
    class C01462 implements Runnable {
        C01462() {
        }

        public void run() {
            LocalAdPresenter.this.adView.showCTAOverlay(LocalAdPresenter.this.advertisement.isCtaShowOnClick(), true, LocalAdPresenter.this.advertisement.getCtaClickArea());
        }
    }

    /* renamed from: com.vungle.warren.presenter.LocalAdPresenter$4 */
    class C01484 implements OnClickListener {
        C01484() {
        }

        public void onClick(DialogInterface dialog, int which) {
            if (which == -2) {
                LocalAdPresenter.this.reportAction("video_close", null);
                if (!LocalAdPresenter.this.advertisement.hasPostroll()) {
                    LocalAdPresenter.this.closeAndReport();
                } else if (LocalAdPresenter.this.isPostRollPresent()) {
                    LocalAdPresenter.this.playPost();
                } else {
                    LocalAdPresenter.this.closeAndReport();
                }
            }
        }
    }

    /* renamed from: com.vungle.warren.presenter.LocalAdPresenter$5 */
    class C01495 implements Runnable {
        C01495() {
        }

        public void run() {
            LocalAdPresenter.this.userExitEnabled = true;
            if (!LocalAdPresenter.this.inPost) {
                LocalAdPresenter.this.adView.showCloseButton();
            }
        }
    }

    /* renamed from: com.vungle.warren.presenter.LocalAdPresenter$6 */
    class C01506 implements Callback<JsonObject> {
        C01506() {
        }

        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
            Log.d(LocalAdPresenter.TAG, "send RI success");
        }

        public void onFailure(Call<JsonObject> call, Throwable t) {
            Log.d(LocalAdPresenter.TAG, "send RI Failure");
        }
    }

    public LocalAdPresenter(Advertisement advertisement, Placement placement, Storage storage, File assetDir, String userId) {
        this.advertisement = advertisement;
        this.placement = placement;
        this.storage = storage;
        this.userID = userId;
        this.assetDir = assetDir;
    }

    public void setEventListener(EventListener listener) {
        this.bus = listener;
    }

    public void setDirectDownloadAdapter(DirectDownloadAdapter directDownloadAdapter) {
    }

    public void reportError(String error) {
        this.report.recordError(error);
        this.storage.save(this.report);
        if (this.inPost || !this.advertisement.hasPostroll()) {
            if (this.bus != null) {
                this.bus.onError(new Throwable(error));
            }
            this.adView.close();
            return;
        }
        playPost();
    }

    public void reportAction(String action, @Nullable String value) {
        int i = 0;
        if (action.equals(String.VIDEO_LENGTH)) {
            this.duration = Integer.parseInt(value);
            this.report.setAdDuration(this.duration);
            this.storage.save(this.report);
            return;
        }
        if (action.equals("mute")) {
            for (String url : this.advertisement.getTpatUrls("mute")) {
                VungleApiClient.pingTPAT(url);
            }
        }
        if (action.equals("unmute")) {
            for (String url2 : this.advertisement.getTpatUrls("unmute")) {
                VungleApiClient.pingTPAT(url2);
            }
        }
        if (action.equals("video_close")) {
            String[] tpatUrls = this.advertisement.getTpatUrls("video_close");
            int length = tpatUrls.length;
            while (i < length) {
                VungleApiClient.pingTPAT(tpatUrls[i]);
                i++;
            }
        }
        this.report.recordAction(action, value, System.currentTimeMillis());
        this.storage.save(this.report);
    }

    public WebViewClient getWebViewClient() {
        if (this.webClient == null) {
            this.webClient = new VungleWebClient(this.advertisement, this.placement, null);
        }
        return this.webClient;
    }

    public void notifyPropertiesChanged() {
    }

    public void initializeViewabilityTracker(int videoDuration, VideoView videoView) {
        if (this.advertisement.getMoatEnabled() && VungleApiClient.getMoatEnabled() && this.videoTracker != null && this.adIds != null) {
            this.videoView = videoView;
            Log.d(TAG, "initializeViewabilityTracker");
            this.videoTracker.trackVideoAd(this.adIds, Integer.valueOf(videoDuration), videoView);
        }
    }

    public void stopViewabilityTracker() {
        if (this.advertisement.getMoatEnabled() && VungleApiClient.getMoatEnabled() && this.videoTracker != null) {
            int currentVideoPosition = this.videoView != null ? this.videoView.getCurrentPosition() : 0;
            Log.d(TAG, "stopViewabilityTracker: " + currentVideoPosition);
            this.videoTracker.dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_STOPPED, Integer.valueOf(currentVideoPosition)));
            this.videoTracker.stopTracking();
            Log.d(TAG, "stopViewabilityTracker: Success !!");
        }
    }

    public void attach(AdView adView) {
        boolean z = true;
        this.isDestroying.set(false);
        this.adView = adView;
        int settings = this.advertisement.getAdConfig().getSettings();
        if (settings > 0) {
            boolean z2;
            if ((settings & 1) == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            this.muted = z2;
            if ((settings & 2) == 2) {
                z2 = true;
            } else {
                z2 = false;
            }
            this.userExitEnabled = z2;
            if ((settings & 32) != 32) {
                z = false;
            }
            this.directDownloadApkEnabled = z;
        }
        int requestedOrientation = -1;
        if ((this.advertisement.getAdConfig().getSettings() & 16) != 16) {
            switch (this.advertisement.getOrientation()) {
                case 0:
                    requestedOrientation = 1;
                    break;
                case 1:
                    requestedOrientation = 0;
                    break;
                case 2:
                    requestedOrientation = 4;
                    break;
            }
        }
        requestedOrientation = 4;
        Log.d(TAG, "requested orientation " + requestedOrientation);
        adView.setOrientation(requestedOrientation);
    }

    private void playPost() {
        if (isPostRollPresent()) {
            File indexHtml = new File(new File(this.assetDir.getPath() + File.separator + Advertisement.POSTROLL_UNZIP).getPath() + File.separator + "index.html");
            if (indexHtml.exists()) {
                this.adView.showWebsite("file://" + indexHtml.getPath());
            } else {
                if (this.bus != null) {
                    this.bus.onError(new VungleException(10));
                }
                this.adView.close();
                return;
            }
        }
        for (String url : this.advertisement.getTpatUrls("postroll_view")) {
            VungleApiClient.pingTPAT(url);
        }
        this.inPost = true;
    }

    private boolean isPostRollPresent() {
        return new File(this.assetDir.getPath() + File.separator + Advertisement.POSTROLL_UNZIP).exists();
    }

    public void prepare(Bundle savedState) {
        String userIdFromCookie;
        if (this.bus != null) {
            this.bus.onNext(String.VIDEO_START, null);
        }
        Cookie incentivizedCookie = (Cookie) this.storage.load(Cookie.INCENTIVIZED_TEXT_COOKIE, Cookie.class);
        if (incentivizedCookie == null) {
            userIdFromCookie = null;
        } else {
            userIdFromCookie = incentivizedCookie.getString("userID");
        }
        this.report = new Report(this.advertisement, this.placement, System.currentTimeMillis(), userIdFromCookie);
        if (!(this.advertisement.getCheckpoints() == null || this.advertisement.getCheckpoints().isEmpty())) {
            this.checkpoint = (Checkpoint) this.advertisement.getCheckpoints().get(0);
        }
        if (this.advertisement.getMoatEnabled() && VungleApiClient.getMoatEnabled()) {
            MoatOptions options = new MoatOptions();
            options.disableAdIdCollection = true;
            options.disableLocationServices = true;
            options.loggingEnabled = false;
            this.moatQuartileTrackers = new LinkedList();
            this.moatQuartileTrackers.add(new Pair(Integer.valueOf(0), MoatAdEventType.AD_EVT_START));
            this.moatQuartileTrackers.add(new Pair(Integer.valueOf(25), MoatAdEventType.AD_EVT_FIRST_QUARTILE));
            this.moatQuartileTrackers.add(new Pair(Integer.valueOf(50), MoatAdEventType.AD_EVT_MID_POINT));
            this.moatQuartileTrackers.add(new Pair(Integer.valueOf(75), MoatAdEventType.AD_EVT_THIRD_QUARTILE));
            this.adIds = new HashMap();
            if (!this.advertisement.getMoatVastExtra().isEmpty()) {
                this.adIds.put("zMoatVASTIDs", this.advertisement.getMoatVastExtra());
            }
            String advertiserAppId = this.advertisement.getAppID();
            String rawAppIdJson = this.advertisement.getAppID();
            if (rawAppIdJson != null) {
                try {
                    JSONObject appIdJson = new JSONObject(rawAppIdJson.substring(3));
                    advertiserAppId = appIdJson.isNull("app_id") ? null : appIdJson.optString("app_id", null);
                } catch (JSONException e) {
                    Log.e(TAG, "JsonException : ", e);
                }
            }
            String campaignId = null;
            String creativeId = null;
            String campaign = this.advertisement.getCampaign();
            int firstBar = campaign.indexOf(MetadataChangeSet.CUSTOM_PROPERTY_SIZE_LIMIT_BYTES);
            if (firstBar != -1) {
                int secondBar = campaign.indexOf(MetadataChangeSet.CUSTOM_PROPERTY_SIZE_LIMIT_BYTES, firstBar + 1);
                campaignId = campaign.substring(0, firstBar);
                if (secondBar != -1) {
                    creativeId = campaign.substring(firstBar + 1, secondBar);
                }
            }
            HashMap hashMap = this.adIds;
            String str = "level1";
            if (advertiserAppId == null || advertiserAppId.isEmpty()) {
                advertiserAppId = this.advertisement.getId();
            }
            hashMap.put(str, advertiserAppId);
            hashMap = this.adIds;
            str = "level2";
            if (campaignId == null || campaignId.isEmpty()) {
                campaignId = this.advertisement.getId();
            }
            hashMap.put(str, campaignId);
            hashMap = this.adIds;
            str = "level3";
            if (creativeId == null || creativeId.isEmpty()) {
                creativeId = this.advertisement.getId();
            }
            hashMap.put(str, creativeId);
            this.adIds.put("level4", this.placement.getId());
            Cookie appIdCookie = (Cookie) this.storage.load(Cookie.APP_ID, Cookie.class);
            if (!(appIdCookie == null || TextUtils.isEmpty(appIdCookie.getString(Cookie.APP_ID)))) {
                this.adIds.put("slicer1", appIdCookie.getString(Cookie.APP_ID));
            }
            this.videoTracker = (ReactiveVideoTracker) MoatFactory.create().createCustomTracker(new ReactiveVideoTrackerPlugin("vunglenativevideo893259554489"));
        }
        this.adView.updateWindow(false);
        if (this.advertisement.isCtaOverlayEnabled()) {
            if (this.advertisement.isCtaShowOnClick()) {
                this.adView.showCTAOverlay(this.advertisement.isCtaShowOnClick(), true, this.advertisement.getCtaClickArea());
            } else {
                this.handler.postDelayed(new C01451(), (long) this.advertisement.getCtaTimeShow());
                this.handler.postDelayed(new C01462(), (long) this.advertisement.getCtaTimeEnabled());
            }
        }
        Cookie gdprConsent = (Cookie) this.storage.load(Cookie.CONSENT_COOKIE, Cookie.class);
        if (gdprConsent != null) {
            if (gdprConsent.getBoolean("is_country_data_protected").booleanValue()) {
                if ("unknown".equals(gdprConsent.getString("consent_status"))) {
                    Cookie finalGdpr = gdprConsent;
                    final Cookie cookie = finalGdpr;
                    OnClickListener listener = new OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String consented = "opted_out_by_timeout";
                            switch (i) {
                                case -2:
                                    consented = "opted_out";
                                    break;
                                case -1:
                                    consented = "opted_in";
                                    break;
                            }
                            cookie.putValue("consent_status", consented);
                            cookie.putValue("timestamp", Long.valueOf(System.currentTimeMillis() / 1000));
                            cookie.putValue("consent_source", "vungle_modal");
                            LocalAdPresenter.this.storage.save(cookie);
                            LocalAdPresenter.this.play();
                        }
                    };
                    gdprConsent.putValue("consent_status", "opted_out_by_timeout");
                    finalGdpr.putValue("timestamp", Long.valueOf(System.currentTimeMillis() / 1000));
                    finalGdpr.putValue("consent_source", "vungle_modal");
                    this.storage.save(finalGdpr);
                    this.adView.showDialog(gdprConsent.getString("consent_title"), gdprConsent.getString("consent_message"), gdprConsent.getString("button_accept"), gdprConsent.getString("button_deny"), listener);
                    return;
                }
            }
        }
        play();
    }

    public boolean handleExit(String flexViewCloseApiPlacementID) {
        if (this.inPost) {
            closeAndReport();
            return true;
        } else if (!this.userExitEnabled) {
            return false;
        } else {
            if (this.placement.isIncentivized()) {
                Cookie incentivizedText = (Cookie) this.storage.load(Cookie.INCENTIVIZED_TEXT_COOKIE, Cookie.class);
                String titleText = this.dialogTitle;
                String bodyText = this.dialogBody;
                String continueText = this.dialogContinue;
                String closeText = this.dialogClose;
                if (incentivizedText != null) {
                    titleText = incentivizedText.getString("title") == null ? this.dialogTitle : incentivizedText.getString("title");
                    bodyText = incentivizedText.getString("body") == null ? this.dialogBody : incentivizedText.getString("body");
                    continueText = incentivizedText.getString("continue") == null ? this.dialogContinue : incentivizedText.getString("continue");
                    if (incentivizedText.getString(String.CLOSE) == null) {
                        closeText = this.dialogClose;
                    } else {
                        closeText = incentivizedText.getString(String.CLOSE);
                    }
                }
                this.adView.showDialog(titleText, bodyText, continueText, closeText, new C01484());
                return false;
            }
            reportAction("video_close", null);
            if (!this.advertisement.hasPostroll()) {
                closeAndReport();
                return true;
            } else if (!isPostRollPresent()) {
                return false;
            } else {
                playPost();
                return false;
            }
        }
    }

    private boolean isWebPageBlank() {
        String url = this.adView.getWebsiteUrl();
        return TextUtils.isEmpty(url) || "about:blank".equalsIgnoreCase(url);
    }

    public void play() {
        if (!this.inPost) {
            this.adView.playVideo(Uri.fromFile(new File(this.assetDir.getPath() + File.separator + "video")), this.muted);
            int delayInMillis = this.advertisement.getShowCloseDelay(this.placement.isIncentivized());
            if (delayInMillis > 0) {
                this.handler.postDelayed(new C01495(), (long) delayInMillis);
                return;
            }
            this.userExitEnabled = true;
            this.adView.showCloseButton();
        } else if (isWebPageBlank()) {
            playPost();
        }
    }

    public void stop(boolean isChangingConfigurations, boolean isFinishing) {
        String str = null;
        if (isChangingConfigurations || !isFinishing) {
            if (this.inPost || isFinishing) {
                this.adView.showWebsite("about:blank");
            }
        } else if (!this.isDestroying.getAndSet(true)) {
            reportAction(String.CLOSE, null);
            this.handler.removeCallbacksAndMessages(null);
            if (this.bus != null) {
                EventListener eventListener = this.bus;
                String str2 = "end";
                if (this.report.isCTAClicked()) {
                    str = "isCTAClicked";
                }
                eventListener.onNext(str2, str);
            }
            this.adView.close();
        }
    }

    public void setAdVisibility(boolean isViewable) {
    }

    public void reportMute(boolean muted) {
        if (muted) {
            if (this.advertisement.getMoatEnabled() && VungleApiClient.getMoatEnabled()) {
                this.videoTracker.setPlayerVolume(MoatAdEvent.VOLUME_UNMUTED);
            }
        } else if (this.advertisement.getMoatEnabled() && VungleApiClient.getMoatEnabled()) {
            this.videoTracker.setPlayerVolume(MoatAdEvent.VOLUME_MUTED);
        }
    }

    public void onProgressUpdate(int progress) {
        int i = 0;
        if (this.bus != null) {
            this.bus.onNext("percentViewed:" + progress, null);
        }
        List<Checkpoint> checkpoints = this.advertisement.getCheckpoints();
        if (progress == 100) {
            int checkPointIndex = checkpoints.size() - 1;
            if (checkPointIndex > 0) {
                Checkpoint checkpoint = (Checkpoint) checkpoints.get(checkPointIndex);
                if (checkpoint.getPercentage() == (byte) 100) {
                    for (String url : checkpoint.getUrls()) {
                        VungleApiClient.pingTPAT(url);
                    }
                }
            }
            if (this.advertisement.getMoatEnabled() && VungleApiClient.getMoatEnabled()) {
                if (this.videoView != null) {
                    this.videoTracker.dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_COMPLETE, Integer.valueOf(this.videoView.getCurrentPosition())));
                }
                this.videoTracker.stopTracking();
            }
            if (this.advertisement.hasPostroll()) {
                if (isPostRollPresent()) {
                    playPost();
                } else {
                    closeAndReport();
                }
            }
        }
        this.report.recordProgress((int) (((float) this.duration) * (((float) progress) / 100.0f)));
        this.storage.save(this.report);
        if (this.checkpoint != null && progress > this.checkpoint.getPercentage()) {
            String[] urls = this.checkpoint.getUrls();
            int length = urls.length;
            while (i < length) {
                VungleApiClient.pingTPAT(urls[i]);
                i++;
            }
            this.checkpoint = null;
            if (this.checkpointReached < checkpoints.size() - 1) {
                byte b = (byte) (this.checkpointReached + 1);
                this.checkpointReached = b;
                this.checkpoint = (Checkpoint) checkpoints.get(b);
            }
        }
        if (this.advertisement.getMoatEnabled() && VungleApiClient.getMoatEnabled() && !this.moatQuartileTrackers.isEmpty() && progress >= ((Integer) ((Pair) this.moatQuartileTrackers.peek()).first).intValue()) {
            this.videoTracker.dispatchEvent(new MoatAdEvent((MoatAdEventType) ((Pair) this.moatQuartileTrackers.poll()).second, Integer.valueOf(progress)));
        }
        Cookie configCookie = (Cookie) this.storage.load(Cookie.CONFIG_COOKIE, Cookie.class);
        if (this.placement.isIncentivized() && progress > 75 && configCookie != null && configCookie.getBoolean("isReportIncentivizedEnabled").booleanValue() && !this.sendReportIncentivized.getAndSet(true)) {
            JsonObject body = new JsonObject();
            body.add("placement_reference_id", new JsonPrimitive(this.placement.getId()));
            body.add("app_id", new JsonPrimitive(this.advertisement.getAppID()));
            body.add("adStartTime", new JsonPrimitive(Long.valueOf(this.report.getAdStartTime())));
            body.add("user", new JsonPrimitive(this.report.getUserID()));
            VungleApiClient.ri(body).enqueue(new C01506());
        }
    }

    public void generateSaveState(Bundle savedState) {
        if (savedState != null) {
            this.storage.save(this.report);
            savedState.putString(EXTRA_REPORT, this.report.getId());
            savedState.putBoolean(EXTRA_INCENTIVIZED_SENT, this.sendReportIncentivized.get());
            savedState.putBoolean(EXTRA_IN_POST, this.inPost);
        }
    }

    public void restoreFromSave(Bundle savedState) {
        if (savedState != null) {
            if (savedState.getBoolean(EXTRA_INCENTIVIZED_SENT, false)) {
                this.sendReportIncentivized.set(true);
            }
            String reportId = savedState.getString(EXTRA_REPORT);
            this.report = TextUtils.isEmpty(reportId) ? null : (Report) this.storage.load(reportId, Report.class);
            if (this.report == null) {
                this.adView.close();
            } else {
                this.inPost = savedState.getBoolean(EXTRA_IN_POST, false);
            }
        }
    }

    public void handleAction(String action) {
        int i = 0;
        int i2 = -1;
        switch (action.hashCode()) {
            case -314498168:
                if (action.equals("privacy")) {
                    i2 = 2;
                    break;
                }
                break;
            case 94756344:
                if (action.equals(String.CLOSE)) {
                    i2 = 0;
                    break;
                }
                break;
            case 1427818632:
                if (action.equals("download")) {
                    i2 = 1;
                    break;
                }
                break;
        }
        switch (i2) {
            case 0:
                closeAndReport();
                return;
            case 1:
                try {
                    for (String url : this.advertisement.getTpatUrls("postroll_click")) {
                        VungleApiClient.pingTPAT(url);
                    }
                    String[] tpatUrls = this.advertisement.getTpatUrls(TapjoyConstants.TJC_CLICK_URL);
                    int length = tpatUrls.length;
                    while (i < length) {
                        VungleApiClient.pingTPAT(tpatUrls[i]);
                        i++;
                    }
                    reportAction("download", null);
                    closeAndReport();
                    String url2 = this.advertisement.getCTAURL(false);
                    String tpatURL = this.advertisement.getCTAURL(true);
                    if (!TextUtils.isEmpty(tpatURL)) {
                        VungleApiClient.pingTPAT(tpatURL);
                    }
                    if (APKDirectDownloadManager.isDirectDownloadEnabled(this.directDownloadApkEnabled, this.advertisement.isRequiresNonMarketInstall())) {
                        APKDirectDownloadManager.download(url2);
                        return;
                    }
                    Intent i3 = new Intent("android.intent.action.VIEW");
                    i3.setData(Uri.parse(url2));
                    this.adView.open(i3.toUri(0));
                    return;
                } catch (ActivityNotFoundException e) {
                    this.handler.removeCallbacksAndMessages(null);
                    this.adView.close();
                    return;
                }
            case 2:
                return;
            default:
                throw new IllegalArgumentException("Unknown action " + action);
        }
    }

    private void closeAndReport() {
        reportAction(String.CLOSE, null);
        this.handler.removeCallbacksAndMessages(null);
        this.adView.close();
    }
}
