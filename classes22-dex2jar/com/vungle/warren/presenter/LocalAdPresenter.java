// 
// Decompiled by Procyon v0.5.34
// 

package com.vungle.warren.presenter;

import android.support.annotation.Nullable;
import java.util.LinkedList;
import com.moat.analytics.mobile.vng.MoatOptions;
import org.json.JSONException;
import com.moat.analytics.mobile.vng.MoatPlugin;
import com.moat.analytics.mobile.vng.ReactiveVideoTrackerPlugin;
import com.moat.analytics.mobile.vng.MoatFactory;
import org.json.JSONObject;
import java.util.List;
import retrofit2.Response;
import retrofit2.Call;
import retrofit2.Callback;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonObject;
import com.moat.analytics.mobile.vng.MoatAdEvent;
import android.view.View;
import java.util.Map;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;
import com.vungle.warren.model.Cookie;
import android.net.Uri;
import android.content.Intent;
import android.content.ActivityNotFoundException;
import com.vungle.warren.download.APKDirectDownloadManager;
import com.vungle.warren.DirectDownloadAdapter;
import android.webkit.WebViewClient;
import com.vungle.warren.persistence.Memorable;
import android.os.Bundle;
import android.util.Log;
import com.vungle.warren.network.VungleApiClient;
import com.vungle.warren.error.VungleException;
import android.text.TextUtils;
import android.os.Looper;
import com.vungle.warren.ui.VungleWebClient;
import android.widget.VideoView;
import com.moat.analytics.mobile.vng.ReactiveVideoTracker;
import com.vungle.warren.Storage;
import com.vungle.warren.model.Report;
import com.vungle.warren.model.Placement;
import com.moat.analytics.mobile.vng.MoatAdEventType;
import android.util.Pair;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import android.os.Handler;
import java.io.File;
import com.vungle.warren.model.Advertisement;
import com.vungle.warren.ui.AdView;
import java.util.HashMap;

public class LocalAdPresenter implements AdvertisementPresenter
{
    private static final String EXTRA_INCENTIVIZED_SENT = "incentivized_sent";
    private static final String EXTRA_IN_POST = "in_post_roll";
    private static final String EXTRA_REPORT = "saved_report";
    private static final String TAG = "LocalAdPresenter";
    private HashMap<String, String> adIds;
    private AdView adView;
    private Advertisement advertisement;
    private File assetDir;
    private EventListener bus;
    private Advertisement.Checkpoint checkpoint;
    private byte checkpointReached;
    private String dialogBody;
    private String dialogClose;
    private String dialogContinue;
    private String dialogTitle;
    private boolean directDownloadApkEnabled;
    private int duration;
    private Handler handler;
    private boolean inPost;
    private AtomicBoolean isDestroying;
    private Queue<Pair<Integer, MoatAdEventType>> moatQuartileTrackers;
    private boolean muted;
    private Placement placement;
    private Report report;
    private AtomicBoolean sendReportIncentivized;
    private Storage storage;
    private boolean userExitEnabled;
    private String userID;
    private ReactiveVideoTracker videoTracker;
    private VideoView videoView;
    private VungleWebClient webClient;
    
    public LocalAdPresenter(final Advertisement advertisement, final Placement placement, final Storage storage, final File assetDir, final String userID) {
        this.handler = new Handler(Looper.getMainLooper());
        this.dialogTitle = "Are you sure?";
        this.dialogBody = "If you exit now, you will not get your reward";
        this.dialogContinue = "Continue";
        this.dialogClose = "Close";
        this.sendReportIncentivized = new AtomicBoolean(false);
        this.isDestroying = new AtomicBoolean(false);
        this.advertisement = advertisement;
        this.placement = placement;
        this.storage = storage;
        this.userID = userID;
        this.assetDir = assetDir;
    }
    
    private void closeAndReport() {
        this.reportAction("close", null);
        this.handler.removeCallbacksAndMessages((Object)null);
        this.adView.close();
    }
    
    private boolean isPostRollPresent() {
        return new File(this.assetDir.getPath() + File.separator + "postrollUnzip").exists();
    }
    
    private boolean isWebPageBlank() {
        final String websiteUrl = this.adView.getWebsiteUrl();
        return TextUtils.isEmpty((CharSequence)websiteUrl) || "about:blank".equalsIgnoreCase(websiteUrl);
    }
    
    private void playPost() {
        if (this.isPostRollPresent()) {
            final File file = new File(new File(this.assetDir.getPath() + File.separator + "postrollUnzip").getPath() + File.separator + "index.html");
            if (!file.exists()) {
                if (this.bus != null) {
                    this.bus.onError(new VungleException(10));
                }
                this.adView.close();
                return;
            }
            this.adView.showWebsite("file://" + file.getPath());
        }
        final String[] tpatUrls = this.advertisement.getTpatUrls("postroll_view");
        for (int length = tpatUrls.length, i = 0; i < length; ++i) {
            VungleApiClient.pingTPAT(tpatUrls[i]);
        }
        this.inPost = true;
    }
    
    @Override
    public void attach(final AdView adView) {
        final boolean b = true;
        this.isDestroying.set(false);
        this.adView = adView;
        final int settings = this.advertisement.getAdConfig().getSettings();
        if (settings > 0) {
            this.muted = ((settings & 0x1) == 0x1);
            this.userExitEnabled = ((settings & 0x2) == 0x2);
            this.directDownloadApkEnabled = ((settings & 0x20) == 0x20 && b);
        }
        int orientation = -1;
        if ((this.advertisement.getAdConfig().getSettings() & 0x10) != 0x10) {
            switch (this.advertisement.getOrientation()) {
                case 1: {
                    orientation = 0;
                    break;
                }
                case 0: {
                    orientation = 1;
                    break;
                }
                case 2: {
                    orientation = 4;
                    break;
                }
            }
        }
        else {
            orientation = 4;
        }
        Log.d("LocalAdPresenter", "requested orientation " + orientation);
        adView.setOrientation(orientation);
    }
    
    @Override
    public void generateSaveState(final Bundle bundle) {
        if (bundle == null) {
            return;
        }
        this.storage.save(this.report);
        bundle.putString("saved_report", this.report.getId());
        bundle.putBoolean("incentivized_sent", this.sendReportIncentivized.get());
        bundle.putBoolean("in_post_roll", this.inPost);
    }
    
    @Override
    public WebViewClient getWebViewClient() {
        if (this.webClient == null) {
            this.webClient = new VungleWebClient(this.advertisement, this.placement, null);
        }
        return this.webClient;
    }
    
    @Override
    public void handleAction(String ctaurl) {
        final int n = 0;
        switch (ctaurl) {
            default: {
                throw new IllegalArgumentException("Unknown action " + ctaurl);
            }
            case "close": {
                this.closeAndReport();
            }
            case "privacy": {}
            case "download": {
                try {
                    final String[] tpatUrls = this.advertisement.getTpatUrls("postroll_click");
                    for (int length = tpatUrls.length, i = 0; i < length; ++i) {
                        VungleApiClient.pingTPAT(tpatUrls[i]);
                    }
                    final String[] tpatUrls2 = this.advertisement.getTpatUrls("click_url");
                    for (int length2 = tpatUrls2.length, j = n; j < length2; ++j) {
                        VungleApiClient.pingTPAT(tpatUrls2[j]);
                    }
                    this.reportAction("download", null);
                    this.closeAndReport();
                    ctaurl = this.advertisement.getCTAURL(false);
                    final String ctaurl2 = this.advertisement.getCTAURL(true);
                    if (!TextUtils.isEmpty((CharSequence)ctaurl2)) {
                        VungleApiClient.pingTPAT(ctaurl2);
                    }
                    if (APKDirectDownloadManager.isDirectDownloadEnabled(this.directDownloadApkEnabled, this.advertisement.isRequiresNonMarketInstall())) {
                        APKDirectDownloadManager.download(ctaurl);
                        return;
                    }
                }
                catch (ActivityNotFoundException ex) {
                    this.handler.removeCallbacksAndMessages((Object)null);
                    this.adView.close();
                    return;
                }
                final Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse(ctaurl));
                this.adView.open(intent.toUri(0));
            }
        }
    }
    
    @Override
    public boolean handleExit(String s) {
        if (this.inPost) {
            this.closeAndReport();
            return true;
        }
        if (!this.userExitEnabled) {
            return false;
        }
        if (this.placement.isIncentivized()) {
            final Cookie cookie = this.storage.load("incentivizedTextSetByPub", Cookie.class);
            s = this.dialogTitle;
            String s2 = this.dialogBody;
            String s3 = this.dialogContinue;
            String s4 = this.dialogClose;
            if (cookie != null) {
                if (cookie.getString("title") == null) {
                    s = this.dialogTitle;
                }
                else {
                    s = cookie.getString("title");
                }
                if (cookie.getString("body") == null) {
                    s2 = this.dialogBody;
                }
                else {
                    s2 = cookie.getString("body");
                }
                if (cookie.getString("continue") == null) {
                    s3 = this.dialogContinue;
                }
                else {
                    s3 = cookie.getString("continue");
                }
                if (cookie.getString("close") == null) {
                    s4 = this.dialogClose;
                }
                else {
                    s4 = cookie.getString("close");
                }
            }
            this.adView.showDialog(s, s2, s3, s4, (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                public void onClick(final DialogInterface dialogInterface, final int n) {
                    if (n == -2) {
                        LocalAdPresenter.this.reportAction("video_close", null);
                        if (!LocalAdPresenter.this.advertisement.hasPostroll()) {
                            LocalAdPresenter.this.closeAndReport();
                            return;
                        }
                        if (!LocalAdPresenter.this.isPostRollPresent()) {
                            LocalAdPresenter.this.closeAndReport();
                            return;
                        }
                        LocalAdPresenter.this.playPost();
                    }
                }
            });
            return false;
        }
        this.reportAction("video_close", null);
        if (!this.advertisement.hasPostroll()) {
            this.closeAndReport();
            return true;
        }
        if (this.isPostRollPresent()) {
            this.playPost();
            return false;
        }
        return false;
    }
    
    @Override
    public void initializeViewabilityTracker(final int n, final VideoView videoView) {
        if (this.advertisement.getMoatEnabled() && VungleApiClient.getMoatEnabled() && this.videoTracker != null && this.adIds != null) {
            this.videoView = videoView;
            Log.d("LocalAdPresenter", "initializeViewabilityTracker");
            this.videoTracker.trackVideoAd((Map)this.adIds, Integer.valueOf(n), (View)videoView);
        }
    }
    
    @Override
    public void notifyPropertiesChanged() {
    }
    
    @Override
    public void onProgressUpdate(final int n) {
        final int n2 = 0;
        if (this.bus != null) {
            this.bus.onNext("percentViewed:" + n, null);
        }
        final List<Advertisement.Checkpoint> checkpoints = this.advertisement.getCheckpoints();
        if (n == 100) {
            final int n3 = checkpoints.size() - 1;
            if (n3 > 0) {
                final Advertisement.Checkpoint checkpoint = checkpoints.get(n3);
                if (checkpoint.getPercentage() == 100) {
                    final String[] urls = checkpoint.getUrls();
                    for (int length = urls.length, i = 0; i < length; ++i) {
                        VungleApiClient.pingTPAT(urls[i]);
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
                if (this.isPostRollPresent()) {
                    this.playPost();
                }
                else {
                    this.closeAndReport();
                }
            }
        }
        this.report.recordProgress((int)(this.duration * (n / 100.0f)));
        this.storage.save(this.report);
        if (this.checkpoint != null && n > this.checkpoint.getPercentage()) {
            final String[] urls2 = this.checkpoint.getUrls();
            for (int length2 = urls2.length, j = n2; j < length2; ++j) {
                VungleApiClient.pingTPAT(urls2[j]);
            }
            this.checkpoint = null;
            if (this.checkpointReached < checkpoints.size() - 1) {
                final byte checkpointReached = (byte)(this.checkpointReached + 1);
                this.checkpointReached = checkpointReached;
                this.checkpoint = (Advertisement.Checkpoint)checkpoints.get(checkpointReached);
            }
        }
        if (this.advertisement.getMoatEnabled() && VungleApiClient.getMoatEnabled() && !this.moatQuartileTrackers.isEmpty() && n >= (int)this.moatQuartileTrackers.peek().first) {
            this.videoTracker.dispatchEvent(new MoatAdEvent((MoatAdEventType)this.moatQuartileTrackers.poll().second, Integer.valueOf(n)));
        }
        final Cookie cookie = this.storage.load("configSettings", Cookie.class);
        if (this.placement.isIncentivized() && n > 75 && cookie != null && cookie.getBoolean("isReportIncentivizedEnabled") && !this.sendReportIncentivized.getAndSet(true)) {
            final JsonObject jsonObject = new JsonObject();
            jsonObject.add("placement_reference_id", (JsonElement)new JsonPrimitive(this.placement.getId()));
            jsonObject.add("app_id", (JsonElement)new JsonPrimitive(this.advertisement.getAppID()));
            jsonObject.add("adStartTime", (JsonElement)new JsonPrimitive((Number)this.report.getAdStartTime()));
            jsonObject.add("user", (JsonElement)new JsonPrimitive(this.report.getUserID()));
            VungleApiClient.ri(jsonObject).enqueue((Callback)new Callback<JsonObject>() {
                public void onFailure(final Call<JsonObject> call, final Throwable t) {
                    Log.d("LocalAdPresenter", "send RI Failure");
                }
                
                public void onResponse(final Call<JsonObject> call, final Response<JsonObject> response) {
                    Log.d("LocalAdPresenter", "send RI success");
                }
            });
        }
    }
    
    @Override
    public void play() {
        if (this.inPost) {
            if (this.isWebPageBlank()) {
                this.playPost();
            }
            return;
        }
        this.adView.playVideo(Uri.fromFile(new File(this.assetDir.getPath() + File.separator + "video")), this.muted);
        final int showCloseDelay = this.advertisement.getShowCloseDelay(this.placement.isIncentivized());
        if (showCloseDelay > 0) {
            this.handler.postDelayed((Runnable)new Runnable() {
                @Override
                public void run() {
                    LocalAdPresenter.this.userExitEnabled = true;
                    if (!LocalAdPresenter.this.inPost) {
                        LocalAdPresenter.this.adView.showCloseButton();
                    }
                }
            }, (long)showCloseDelay);
            return;
        }
        this.userExitEnabled = true;
        this.adView.showCloseButton();
    }
    
    @Override
    public void prepare(final Bundle bundle) {
        if (this.bus != null) {
            this.bus.onNext("start", null);
        }
        final Cookie cookie = this.storage.load("incentivizedTextSetByPub", Cookie.class);
    Label_0358:
        while (true) {
        Label_0614_Outer:
            while (true) {
                while (true) {
                    String string = null;
                    Label_0043: {
                        if (cookie == null) {
                            string = null;
                            break Label_0043;
                        }
                        Label_0814: {
                            break Label_0814;
                            final String appID;
                            JSONObject jsonObject;
                            Object o;
                            String s;
                            DialogInterface$OnClickListener dialogInterface$OnClickListener;
                            String campaign;
                            int index;
                            Object o2 = null;
                            int index2;
                            String s2;
                            HashMap<String, String> adIds;
                            HashMap<String, String> adIds2;
                            HashMap<String, String> adIds3;
                            Cookie cookie2;
                            Cookie cookie3;
                            Label_0483_Outer:Label_0511_Outer:
                            while (true) {
                                while (true) {
                                Label_0889:
                                    while (true) {
                                    Label_0877:
                                        while (true) {
                                        Label_0865:
                                            while (true) {
                                                Label_0854: {
                                                    try {
                                                        jsonObject = new JSONObject(appID.substring(3));
                                                        if (jsonObject.isNull("app_id")) {
                                                            o = null;
                                                        }
                                                        else {
                                                            o = jsonObject.optString("app_id", (String)null);
                                                        }
                                                        s = null;
                                                        dialogInterface$OnClickListener = null;
                                                        campaign = this.advertisement.getCampaign();
                                                        index = campaign.indexOf(124);
                                                        o2 = dialogInterface$OnClickListener;
                                                        if (index != -1) {
                                                            index2 = campaign.indexOf(124, index + 1);
                                                            s2 = (s = campaign.substring(0, index));
                                                            o2 = dialogInterface$OnClickListener;
                                                            if (index2 != -1) {
                                                                o2 = campaign.substring(index + 1, index2);
                                                                s = s2;
                                                            }
                                                        }
                                                        adIds = this.adIds;
                                                        if (o == null || ((String)o).isEmpty()) {
                                                            break Label_0854;
                                                        }
                                                        adIds.put("level1", (String)o);
                                                        adIds2 = this.adIds;
                                                        if (s == null || s.isEmpty()) {
                                                            break Label_0865;
                                                        }
                                                        adIds2.put("level2", s);
                                                        adIds3 = this.adIds;
                                                        if (o2 == null || ((String)o2).isEmpty()) {
                                                            break Label_0877;
                                                        }
                                                        adIds3.put("level3", (String)o2);
                                                        this.adIds.put("level4", this.placement.getId());
                                                        cookie2 = this.storage.load("appId", Cookie.class);
                                                        if (cookie2 != null && !TextUtils.isEmpty((CharSequence)cookie2.getString("appId"))) {
                                                            this.adIds.put("slicer1", cookie2.getString("appId"));
                                                        }
                                                        this.videoTracker = (ReactiveVideoTracker)MoatFactory.create().createCustomTracker((MoatPlugin)new ReactiveVideoTrackerPlugin("vunglenativevideo893259554489"));
                                                        this.adView.updateWindow(false);
                                                        if (this.advertisement.isCtaOverlayEnabled()) {
                                                            if (!this.advertisement.isCtaShowOnClick()) {
                                                                break Label_0889;
                                                            }
                                                            this.adView.showCTAOverlay(this.advertisement.isCtaShowOnClick(), true, this.advertisement.getCtaClickArea());
                                                        }
                                                        cookie3 = this.storage.load("consentIsImportantToVungle", Cookie.class);
                                                        if (cookie3 != null && cookie3.getBoolean("is_country_data_protected") && "unknown".equals(cookie3.getString("consent_status"))) {
                                                            o2 = new DialogInterface$OnClickListener() {
                                                                public void onClick(final DialogInterface dialogInterface, final int n) {
                                                                    String s = "opted_out_by_timeout";
                                                                    switch (n) {
                                                                        case -2: {
                                                                            s = "opted_out";
                                                                            break;
                                                                        }
                                                                        case -1: {
                                                                            s = "opted_in";
                                                                            break;
                                                                        }
                                                                    }
                                                                    cookie3.putValue("consent_status", s);
                                                                    cookie3.putValue("timestamp", System.currentTimeMillis() / 1000L);
                                                                    cookie3.putValue("consent_source", "vungle_modal");
                                                                    LocalAdPresenter.this.storage.save(cookie3);
                                                                    LocalAdPresenter.this.play();
                                                                }
                                                            };
                                                            cookie3.putValue("consent_status", "opted_out_by_timeout");
                                                            cookie3.putValue("timestamp", System.currentTimeMillis() / 1000L);
                                                            cookie3.putValue("consent_source", "vungle_modal");
                                                            this.storage.save(cookie3);
                                                            this.adView.showDialog(cookie3.getString("consent_title"), cookie3.getString("consent_message"), cookie3.getString("button_accept"), cookie3.getString("button_deny"), (DialogInterface$OnClickListener)o2);
                                                            return;
                                                        }
                                                        break;
                                                        string = cookie.getString("userID");
                                                        break Label_0043;
                                                    }
                                                    catch (JSONException ex) {
                                                        Log.e("LocalAdPresenter", "JsonException : ", (Throwable)ex);
                                                        o = o2;
                                                        continue Label_0358;
                                                    }
                                                    continue Label_0358;
                                                }
                                                o = this.advertisement.getId();
                                                continue Label_0483_Outer;
                                            }
                                            s = this.advertisement.getId();
                                            continue Label_0511_Outer;
                                        }
                                        o2 = this.advertisement.getId();
                                        continue Label_0614_Outer;
                                    }
                                    this.handler.postDelayed((Runnable)new Runnable() {
                                        @Override
                                        public void run() {
                                            LocalAdPresenter.this.adView.showCTAOverlay(LocalAdPresenter.this.advertisement.isCtaShowOnClick(), false, LocalAdPresenter.this.advertisement.getCtaClickArea());
                                        }
                                    }, (long)this.advertisement.getCtaTimeShow());
                                    this.handler.postDelayed((Runnable)new Runnable() {
                                        @Override
                                        public void run() {
                                            LocalAdPresenter.this.adView.showCTAOverlay(LocalAdPresenter.this.advertisement.isCtaShowOnClick(), true, LocalAdPresenter.this.advertisement.getCtaClickArea());
                                        }
                                    }, (long)this.advertisement.getCtaTimeEnabled());
                                    continue;
                                }
                            }
                        }
                        this.play();
                        return;
                    }
                    this.report = new Report(this.advertisement, this.placement, System.currentTimeMillis(), string);
                    if (this.advertisement.getCheckpoints() != null && !this.advertisement.getCheckpoints().isEmpty()) {
                        this.checkpoint = (Advertisement.Checkpoint)this.advertisement.getCheckpoints().get(0);
                    }
                    if (!this.advertisement.getMoatEnabled() || !VungleApiClient.getMoatEnabled()) {
                        continue;
                    }
                    break;
                }
                final MoatOptions moatOptions = new MoatOptions();
                moatOptions.disableAdIdCollection = true;
                moatOptions.disableLocationServices = true;
                moatOptions.loggingEnabled = false;
                (this.moatQuartileTrackers = new LinkedList<Pair<Integer, MoatAdEventType>>()).add((Pair<Integer, MoatAdEventType>)new Pair((Object)0, (Object)MoatAdEventType.AD_EVT_START));
                this.moatQuartileTrackers.add((Pair<Integer, MoatAdEventType>)new Pair((Object)25, (Object)MoatAdEventType.AD_EVT_FIRST_QUARTILE));
                this.moatQuartileTrackers.add((Pair<Integer, MoatAdEventType>)new Pair((Object)50, (Object)MoatAdEventType.AD_EVT_MID_POINT));
                this.moatQuartileTrackers.add((Pair<Integer, MoatAdEventType>)new Pair((Object)75, (Object)MoatAdEventType.AD_EVT_THIRD_QUARTILE));
                this.adIds = new HashMap<String, String>();
                if (!this.advertisement.getMoatVastExtra().isEmpty()) {
                    this.adIds.put("zMoatVASTIDs", this.advertisement.getMoatVastExtra());
                }
                Object o2 = this.advertisement.getAppID();
                final String appID = this.advertisement.getAppID();
                Object o = o2;
                if (appID != null) {
                    continue Label_0614_Outer;
                }
                break;
            }
            continue Label_0358;
        }
    }
    
    @Override
    public void reportAction(final String s, @Nullable final String s2) {
        final int n = 0;
        if (s.equals("videoLength")) {
            this.duration = Integer.parseInt(s2);
            this.report.setAdDuration(this.duration);
            this.storage.save(this.report);
            return;
        }
        if (s.equals("mute")) {
            final String[] tpatUrls = this.advertisement.getTpatUrls("mute");
            for (int length = tpatUrls.length, i = 0; i < length; ++i) {
                VungleApiClient.pingTPAT(tpatUrls[i]);
            }
        }
        if (s.equals("unmute")) {
            final String[] tpatUrls2 = this.advertisement.getTpatUrls("unmute");
            for (int length2 = tpatUrls2.length, j = 0; j < length2; ++j) {
                VungleApiClient.pingTPAT(tpatUrls2[j]);
            }
        }
        if (s.equals("video_close")) {
            final String[] tpatUrls3 = this.advertisement.getTpatUrls("video_close");
            for (int length3 = tpatUrls3.length, k = n; k < length3; ++k) {
                VungleApiClient.pingTPAT(tpatUrls3[k]);
            }
        }
        this.report.recordAction(s, s2, System.currentTimeMillis());
        this.storage.save(this.report);
    }
    
    @Override
    public void reportError(final String s) {
        this.report.recordError(s);
        this.storage.save(this.report);
        if (!this.inPost && this.advertisement.hasPostroll()) {
            this.playPost();
            return;
        }
        if (this.bus != null) {
            this.bus.onError(new Throwable(s));
        }
        this.adView.close();
    }
    
    public void reportMute(final boolean b) {
        if (b) {
            if (this.advertisement.getMoatEnabled() && VungleApiClient.getMoatEnabled()) {
                this.videoTracker.setPlayerVolume(MoatAdEvent.VOLUME_UNMUTED);
            }
        }
        else if (this.advertisement.getMoatEnabled() && VungleApiClient.getMoatEnabled()) {
            this.videoTracker.setPlayerVolume(MoatAdEvent.VOLUME_MUTED);
        }
    }
    
    @Override
    public void restoreFromSave(final Bundle bundle) {
        if (bundle == null) {
            return;
        }
        if (bundle.getBoolean("incentivized_sent", false)) {
            this.sendReportIncentivized.set(true);
        }
        final String string = bundle.getString("saved_report");
        Report report;
        if (TextUtils.isEmpty((CharSequence)string)) {
            report = null;
        }
        else {
            report = this.storage.load(string, Report.class);
        }
        this.report = report;
        if (this.report == null) {
            this.adView.close();
            return;
        }
        this.inPost = bundle.getBoolean("in_post_roll", false);
    }
    
    @Override
    public void setAdVisibility(final boolean b) {
    }
    
    @Override
    public void setDirectDownloadAdapter(final DirectDownloadAdapter directDownloadAdapter) {
    }
    
    @Override
    public void setEventListener(final EventListener bus) {
        this.bus = bus;
    }
    
    @Override
    public void stop(final boolean b, final boolean b2) {
        String s = null;
        if (!b && b2) {
            if (!this.isDestroying.getAndSet(true)) {
                this.reportAction("close", null);
                this.handler.removeCallbacksAndMessages((Object)null);
                if (this.bus != null) {
                    final EventListener bus = this.bus;
                    if (this.report.isCTAClicked()) {
                        s = "isCTAClicked";
                    }
                    bus.onNext("end", s);
                }
                this.adView.close();
            }
        }
        else if (this.inPost || b2) {
            this.adView.showWebsite("about:blank");
        }
    }
    
    @Override
    public void stopViewabilityTracker() {
        if (this.advertisement.getMoatEnabled() && VungleApiClient.getMoatEnabled() && this.videoTracker != null) {
            int currentPosition;
            if (this.videoView != null) {
                currentPosition = this.videoView.getCurrentPosition();
            }
            else {
                currentPosition = 0;
            }
            Log.d("LocalAdPresenter", "stopViewabilityTracker: " + currentPosition);
            this.videoTracker.dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_STOPPED, Integer.valueOf(currentPosition)));
            this.videoTracker.stopTracking();
            Log.d("LocalAdPresenter", "stopViewabilityTracker: Success !!");
        }
    }
}
