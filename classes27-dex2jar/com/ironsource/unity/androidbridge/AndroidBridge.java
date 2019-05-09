// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.unity.androidbridge;

import com.ironsource.mediationsdk.integration.IntegrationHelper;
import com.ironsource.mediationsdk.IronSourceSegment;
import com.ironsource.mediationsdk.config.ConfigFile;
import com.ironsource.adapters.supersonicads.SupersonicConfig;
import java.util.Arrays;
import com.ironsource.mediationsdk.IronSource$AD_UNIT;
import java.util.ArrayList;
import android.app.Activity;
import com.ironsource.mediationsdk.model.Placement;
import java.util.Map;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.FutureTask;
import java.util.concurrent.Callable;
import android.text.TextUtils;
import com.ironsource.mediationsdk.sdk.BannerListener;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.widget.FrameLayout$LayoutParams;
import android.content.Context;
import com.unity3d.player.UnityPlayer;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.ISBannerSize;
import android.os.Looper;
import com.ironsource.mediationsdk.IronSource;
import android.os.Handler;
import android.widget.FrameLayout;
import com.ironsource.mediationsdk.IronSourceBannerLayout;
import com.ironsource.mediationsdk.sdk.SegmentListener;
import com.ironsource.mediationsdk.sdk.RewardedInterstitialListener;
import com.ironsource.mediationsdk.sdk.ISDemandOnlyRewardedVideoListener;
import com.ironsource.mediationsdk.sdk.RewardedVideoListener;
import com.ironsource.mediationsdk.sdk.OfferwallListener;
import com.ironsource.mediationsdk.sdk.ISDemandOnlyInterstitialListener;
import com.ironsource.mediationsdk.sdk.InterstitialListener;

public class AndroidBridge implements InterstitialListener, ISDemandOnlyInterstitialListener, OfferwallListener, RewardedVideoListener, ISDemandOnlyRewardedVideoListener, RewardedInterstitialListener, SegmentListener
{
    private static final AndroidBridge mInstance;
    private final int BANNER_POSITION_TOP;
    private final String ERROR_CODE;
    private final String ERROR_DESCRIPTION;
    private final String IRONSOURCE_EVENT_GAMEOBJECT;
    private final String PLACEMENT_NAME;
    private final String REWARD_AMOUNT;
    private final String REWARD_NAME;
    private IronSourceBannerLayout mBanner;
    private FrameLayout mBannerContainer;
    private boolean mIsBannerLoaded;
    private boolean mIsInitBannerCalled;
    private Handler mUIHandler;
    
    static {
        mInstance = new AndroidBridge();
    }
    
    private AndroidBridge() {
        this.IRONSOURCE_EVENT_GAMEOBJECT = "IronSourceEvents";
        this.ERROR_CODE = "error_code";
        this.ERROR_DESCRIPTION = "error_description";
        this.PLACEMENT_NAME = "placement_name";
        this.REWARD_NAME = "reward_name";
        this.REWARD_AMOUNT = "reward_amount";
        this.BANNER_POSITION_TOP = 1;
        IronSource.setRewardedVideoListener((RewardedVideoListener)this);
        IronSource.setInterstitialListener((InterstitialListener)this);
        IronSource.setRewardedInterstitialListener((RewardedInterstitialListener)this);
        IronSource.setOfferwallListener((OfferwallListener)this);
        IronSource.setISDemandOnlyInterstitialListener((ISDemandOnlyInterstitialListener)this);
        IronSource.setISDemandOnlyRewardedVideoListener((ISDemandOnlyRewardedVideoListener)this);
        this.mUIHandler = new Handler(Looper.getMainLooper());
        this.mBannerContainer = null;
        this.mBanner = null;
        this.mIsBannerLoaded = false;
        this.mIsInitBannerCalled = false;
    }
    
    private ISBannerSize getBannerSize(final String s, final int n, final int n2) {
        if (s.equals("CUSTOM")) {
            return new ISBannerSize(n, n2);
        }
        return new ISBannerSize(s);
    }
    
    public static AndroidBridge getInstance() {
        synchronized (AndroidBridge.class) {
            return AndroidBridge.mInstance;
        }
    }
    
    private void loadAndShowBanner(final String s, final int n, final int n2, final int n3, final String s2) {
        this.mUIHandler.post((Runnable)new Runnable() {
            @Override
            public void run() {
                synchronized (AndroidBridge.mInstance) {
                    try {
                        if (AndroidBridge.this.mBannerContainer == null) {
                            AndroidBridge.this.mBannerContainer = new FrameLayout((Context)UnityPlayer.currentActivity);
                            UnityPlayer.currentActivity.addContentView((View)AndroidBridge.this.mBannerContainer, (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-1, -1));
                        }
                        AndroidBridge.this.mBanner = IronSource.createBanner(AndroidBridge.this.getUnityActivity(), AndroidBridge.this.getBannerSize(s, n, n2));
                        final FrameLayout$LayoutParams frameLayout$LayoutParams = new FrameLayout$LayoutParams(-1, -2);
                        int gravity;
                        if (n3 == 1) {
                            gravity = 48;
                        }
                        else {
                            gravity = 80;
                        }
                        frameLayout$LayoutParams.gravity = gravity;
                        AndroidBridge.this.mBannerContainer.addView((View)AndroidBridge.this.mBanner, (ViewGroup$LayoutParams)frameLayout$LayoutParams);
                        AndroidBridge.this.mBanner.setBannerListener((BannerListener)new BannerListener() {
                            public void onBannerAdClicked() {
                                AndroidBridge.this.sendUnityEvent("onBannerAdClicked");
                            }
                            
                            public void onBannerAdLeftApplication() {
                                AndroidBridge.this.sendUnityEvent("onBannerAdLeftApplication");
                            }
                            
                            public void onBannerAdLoadFailed(final IronSourceError ironSourceError) {
                                AndroidBridge.this.mUIHandler.post((Runnable)new Runnable() {
                                    @Override
                                    public void run() {
                                        synchronized (AndroidBridge.mInstance) {
                                            AndroidBridge.this.mBannerContainer.removeAllViews();
                                            if (AndroidBridge.this.mBanner != null) {
                                                AndroidBridge.this.mBanner = null;
                                            }
                                            AndroidBridge.this.mIsBannerLoaded = false;
                                        }
                                    }
                                });
                                AndroidBridge.this.sendUnityEvent("onBannerAdLoadFailed", AndroidBridge.this.parseErrorToEvent(ironSourceError));
                            }
                            
                            public void onBannerAdLoaded() {
                                AndroidBridge.this.mUIHandler.post((Runnable)new Runnable() {
                                    @Override
                                    public void run() {
                                        synchronized (AndroidBridge.mInstance) {
                                            AndroidBridge.this.mBannerContainer.requestLayout();
                                        }
                                    }
                                });
                                AndroidBridge.this.sendUnityEvent("onBannerAdLoaded");
                            }
                            
                            public void onBannerAdScreenDismissed() {
                                AndroidBridge.this.sendUnityEvent("onBannerAdScreenDismissed");
                            }
                            
                            public void onBannerAdScreenPresented() {
                                AndroidBridge.this.sendUnityEvent("onBannerAdScreenPresented");
                            }
                        });
                        if (s2 != null) {
                            IronSource.loadBanner(AndroidBridge.this.mBanner, s2);
                        }
                        else {
                            IronSource.loadBanner(AndroidBridge.this.mBanner);
                        }
                    }
                    catch (Throwable t) {
                        AndroidBridge.this.sendUnityEvent("onBannerAdLoadFailed", AndroidBridge.this.parseErrorToEvent(509, t.getMessage()));
                    }
                }
            }
        });
    }
    
    private String parseErrorToEvent(final IronSourceError ironSourceError) {
        if (ironSourceError == null) {
            return "";
        }
        return this.parseErrorToEvent(ironSourceError.getErrorCode(), ironSourceError.getErrorMessage());
    }
    
    private void sendUnityEvent(final String s) {
        this.sendUnityEvent(s, "");
    }
    
    private void sendUnityEvent(final String s, String s2) {
        while (true) {
            while (true) {
                Label_0031: {
                    try {
                        if (UnityPlayer.currentActivity == null) {
                            return;
                        }
                        if (TextUtils.isEmpty((CharSequence)s2)) {
                            s2 = "";
                            UnityPlayer.UnitySendMessage("IronSourceEvents", s, s2);
                            return;
                        }
                        break Label_0031;
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                        return;
                    }
                }
                continue;
            }
        }
    }
    
    public void clearRewardedVideoServerParams() {
        IronSource.clearRewardedVideoServerParameters();
    }
    
    public void destroyBanner() {
        synchronized (AndroidBridge.mInstance) {
            this.mUIHandler.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    final AndroidBridge access$000 = AndroidBridge.mInstance;
                    // monitorenter(access$000)
                    while (true) {
                        try {
                            try {
                                AndroidBridge.this.mBannerContainer.removeAllViews();
                                if (AndroidBridge.this.mBanner != null) {
                                    IronSource.destroyBanner(AndroidBridge.this.mBanner);
                                    AndroidBridge.this.mBanner = null;
                                }
                                AndroidBridge.this.mIsBannerLoaded = false;
                            }
                            finally {
                            }
                            // monitorexit(access$000)
                        }
                        catch (Exception ex) {
                            continue;
                        }
                        break;
                    }
                }
            });
        }
    }
    
    public void displayBanner() {
        synchronized (AndroidBridge.mInstance) {
            this.mUIHandler.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    final AndroidBridge access$000 = AndroidBridge.mInstance;
                    // monitorenter(access$000)
                    try {
                        try {
                            if (AndroidBridge.this.mBanner != null) {
                                AndroidBridge.this.mBanner.setVisibility(0);
                            }
                        }
                        finally {
                        }
                        // monitorexit(access$000)
                    }
                    catch (Exception ex) {}
                }
            });
        }
    }
    
    public String getAdvertiserId() {
        final FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return IronSource.getAdvertiserId((Context)AndroidBridge.this.getUnityActivity());
            }
        });
        futureTask.run();
        try {
            return futureTask.get(1L, TimeUnit.SECONDS);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }
    
    public HashMap<String, String> getHashMapFromJsonString(final String s) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        try {
            final JSONObject jsonObject = new JSONObject(s);
            final Iterator keys = jsonObject.keys();
            while (keys.hasNext()) {
                final String s2 = keys.next();
                hashMap.put(s2, jsonObject.getString(s2));
            }
        }
        catch (JSONException ex) {
            ex.printStackTrace();
        }
        return hashMap;
    }
    
    public void getOfferwallCredits() {
        IronSource.getOfferwallCredits();
    }
    
    public String getPlacementInfo(String string) {
        final Placement rewardedVideoPlacementInfo = IronSource.getRewardedVideoPlacementInfo(string);
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        try {
            hashMap.put("placement_name", rewardedVideoPlacementInfo.getPlacementName());
            hashMap.put("reward_name", rewardedVideoPlacementInfo.getRewardName());
            hashMap.put("reward_amount", (String)rewardedVideoPlacementInfo.getRewardAmount());
            string = new JSONObject((Map)hashMap).toString();
            return string;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public Activity getUnityActivity() {
        return UnityPlayer.currentActivity;
    }
    
    public void hideBanner() {
        synchronized (AndroidBridge.mInstance) {
            this.mUIHandler.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    final AndroidBridge access$000 = AndroidBridge.mInstance;
                    // monitorenter(access$000)
                    try {
                        try {
                            if (AndroidBridge.this.mBanner != null) {
                                AndroidBridge.this.mBanner.setVisibility(8);
                            }
                        }
                        finally {
                        }
                        // monitorexit(access$000)
                    }
                    catch (Exception ex) {}
                }
            });
        }
    }
    
    public void init(final String s) {
        this.mIsInitBannerCalled = true;
        IronSource.init(this.getUnityActivity(), s);
    }
    
    public void init(final String s, final String[] array) {
        final ArrayList<IronSource$AD_UNIT> list = new ArrayList<IronSource$AD_UNIT>();
        for (int length = array.length, i = 0; i < length; ++i) {
            final String s2 = array[i];
            if (IronSource$AD_UNIT.REWARDED_VIDEO.toString().equalsIgnoreCase(s2)) {
                list.add(IronSource$AD_UNIT.REWARDED_VIDEO);
            }
            else if (IronSource$AD_UNIT.INTERSTITIAL.toString().equalsIgnoreCase(s2)) {
                list.add(IronSource$AD_UNIT.INTERSTITIAL);
            }
            else if (IronSource$AD_UNIT.OFFERWALL.toString().equalsIgnoreCase(s2)) {
                list.add(IronSource$AD_UNIT.OFFERWALL);
            }
            else if (IronSource$AD_UNIT.BANNER.toString().equalsIgnoreCase(s2)) {
                this.mIsInitBannerCalled = true;
                list.add(IronSource$AD_UNIT.BANNER);
            }
        }
        IronSource.init(this.getUnityActivity(), s, (IronSource$AD_UNIT[])list.toArray(new IronSource$AD_UNIT[list.size()]));
    }
    
    public void initISDemandOnly(final String s, final String[] array) {
        final ArrayList<IronSource$AD_UNIT> list = new ArrayList<IronSource$AD_UNIT>();
        for (int length = array.length, i = 0; i < length; ++i) {
            final String s2 = array[i];
            if (IronSource$AD_UNIT.REWARDED_VIDEO.toString().equalsIgnoreCase(s2)) {
                list.add(IronSource$AD_UNIT.REWARDED_VIDEO);
            }
            else if (IronSource$AD_UNIT.INTERSTITIAL.toString().equalsIgnoreCase(s2)) {
                list.add(IronSource$AD_UNIT.INTERSTITIAL);
            }
            else if (IronSource$AD_UNIT.OFFERWALL.toString().equalsIgnoreCase(s2)) {
                list.add(IronSource$AD_UNIT.OFFERWALL);
            }
            else if (IronSource$AD_UNIT.BANNER.toString().equalsIgnoreCase(s2)) {
                list.add(IronSource$AD_UNIT.BANNER);
            }
        }
        IronSource.initISDemandOnly(this.getUnityActivity(), s, (IronSource$AD_UNIT[])list.toArray(new IronSource$AD_UNIT[list.size()]));
    }
    
    public boolean isBannerPlacementCapped(final String s) {
        return IronSource.isBannerPlacementCapped(s);
    }
    
    public boolean isISDemandOnlyInterstitialReady(final String s) {
        return IronSource.isISDemandOnlyInterstitialReady(s);
    }
    
    public boolean isISDemandOnlyRewardedVideoAvailable(final String s) {
        return IronSource.isISDemandOnlyRewardedVideoAvailable(s);
    }
    
    public boolean isInterstitialPlacementCapped(final String s) {
        return IronSource.isInterstitialPlacementCapped(s);
    }
    
    public boolean isInterstitialReady() {
        return IronSource.isInterstitialReady();
    }
    
    public boolean isOfferwallAvailable() {
        return IronSource.isOfferwallAvailable();
    }
    
    public boolean isRewardedVideoAvailable() {
        return IronSource.isRewardedVideoAvailable();
    }
    
    public boolean isRewardedVideoPlacementCapped(final String s) {
        return IronSource.isRewardedVideoPlacementCapped(s);
    }
    
    public void loadBanner(final String s, final int n, final int n2, final int n3, final String s2) {
        synchronized (AndroidBridge.mInstance) {
            if (!this.mIsInitBannerCalled || this.mIsBannerLoaded) {
                return;
            }
            this.mIsBannerLoaded = true;
            this.loadAndShowBanner(s, n, n2, n3, s2);
        }
    }
    
    public void loadISDemandOnlyInterstitial(final String s) {
        IronSource.loadISDemandOnlyInterstitial(s);
    }
    
    public void loadInterstitial() {
        IronSource.loadInterstitial();
    }
    
    public void onGetOfferwallCreditsFailed(final IronSourceError ironSourceError) {
        if (ironSourceError != null) {
            this.sendUnityEvent("onGetOfferwallCreditsFailed", this.parseErrorToEvent(ironSourceError.getErrorCode(), ironSourceError.getErrorMessage()));
            return;
        }
        this.sendUnityEvent("onGetOfferwallCreditsFailed", "");
    }
    
    public void onInterstitialAdClicked() {
        this.sendUnityEvent("onInterstitialAdClicked", "");
    }
    
    public void onInterstitialAdClicked(final String s) {
        this.sendUnityEvent("onInterstitialAdClickedDemandOnly", s);
    }
    
    public void onInterstitialAdClosed() {
        this.sendUnityEvent("onInterstitialAdClosed", "");
    }
    
    public void onInterstitialAdClosed(final String s) {
        this.sendUnityEvent("onInterstitialAdClosedDemandOnly", s);
    }
    
    public void onInterstitialAdLoadFailed(final IronSourceError ironSourceError) {
        if (ironSourceError != null) {
            this.sendUnityEvent("onInterstitialAdLoadFailed", this.parseErrorToEvent(ironSourceError.getErrorCode(), ironSourceError.getErrorMessage()));
            return;
        }
        this.sendUnityEvent("onInterstitialAdLoadFailed", "");
    }
    
    public void onInterstitialAdLoadFailed(final String s, final IronSourceError ironSourceError) {
        String[] array;
        if (ironSourceError != null) {
            array = new String[] { s, this.parseErrorToEvent(ironSourceError.getErrorCode(), ironSourceError.getErrorMessage()) };
        }
        else {
            array = new String[] { s, "" };
        }
        this.sendUnityEvent("onInterstitialAdLoadFailedDemandOnly", Arrays.toString(array));
    }
    
    public void onInterstitialAdOpened() {
        this.sendUnityEvent("onInterstitialAdOpened", "");
    }
    
    public void onInterstitialAdOpened(final String s) {
        this.sendUnityEvent("onInterstitialAdOpenedDemandOnly", s);
    }
    
    public void onInterstitialAdReady() {
        this.sendUnityEvent("onInterstitialAdReady", "");
    }
    
    public void onInterstitialAdReady(final String s) {
        this.sendUnityEvent("onInterstitialAdReadyDemandOnly", s);
    }
    
    public void onInterstitialAdRewarded() {
        this.sendUnityEvent("onInterstitialAdRewarded", "");
    }
    
    public void onInterstitialAdShowFailed(final IronSourceError ironSourceError) {
        if (ironSourceError != null) {
            this.sendUnityEvent("onInterstitialAdShowFailed", this.parseErrorToEvent(ironSourceError.getErrorCode(), ironSourceError.getErrorMessage()));
            return;
        }
        this.sendUnityEvent("onInterstitialAdShowFailed", "");
    }
    
    public void onInterstitialAdShowFailed(final String s, final IronSourceError ironSourceError) {
        String[] array;
        if (ironSourceError != null) {
            array = new String[] { s, this.parseErrorToEvent(ironSourceError.getErrorCode(), ironSourceError.getErrorMessage()) };
        }
        else {
            array = new String[] { s, "" };
        }
        this.sendUnityEvent("onInterstitialAdShowFailedDemandOnly", Arrays.toString(array));
    }
    
    public void onInterstitialAdShowSucceeded() {
        this.sendUnityEvent("onInterstitialAdShowSucceeded", "");
    }
    
    public void onInterstitialAdShowSucceeded(final String s) {
        this.sendUnityEvent("onInterstitialAdShowSucceededDemandOnly", s);
    }
    
    public boolean onOfferwallAdCredited(final int n, final int n2, final boolean b) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        try {
            hashMap.put("credits", String.valueOf(n));
            hashMap.put("totalCredits", String.valueOf(n2));
            hashMap.put("totalCreditsFlag", String.valueOf(b));
            this.sendUnityEvent("onOfferwallAdCredited", new JSONObject((Map)hashMap).toString());
            return true;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return true;
        }
    }
    
    public void onOfferwallAvailable(final boolean b) {
        this.sendUnityEvent("onOfferwallAvailable", String.valueOf(b));
    }
    
    public void onOfferwallClosed() {
        this.sendUnityEvent("onOfferwallClosed", "");
    }
    
    public void onOfferwallOpened() {
        this.sendUnityEvent("onOfferwallOpened", "");
    }
    
    public void onOfferwallShowFailed(final IronSourceError ironSourceError) {
        if (ironSourceError != null) {
            this.sendUnityEvent("onOfferwallShowFailed", this.parseErrorToEvent(ironSourceError.getErrorCode(), ironSourceError.getErrorMessage()));
            return;
        }
        this.sendUnityEvent("onOfferwallShowFailed", "");
    }
    
    public void onPause() {
        IronSource.onPause(this.getUnityActivity());
    }
    
    public void onResume() {
        IronSource.onResume(this.getUnityActivity());
    }
    
    public void onRewardedVideoAdClicked(final Placement placement) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        try {
            hashMap.put("placement_id", String.valueOf(placement.getPlacementId()));
            hashMap.put("placement_name", placement.getPlacementName());
            hashMap.put("placement_reward_amount", String.valueOf(placement.getRewardAmount()));
            hashMap.put("placement_reward_name", placement.getRewardName());
            this.sendUnityEvent("onRewardedVideoAdClicked", new JSONObject((Map)hashMap).toString());
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void onRewardedVideoAdClicked(final String s, final Placement placement) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        try {
            hashMap.put("placement_id", String.valueOf(placement.getPlacementId()));
            hashMap.put("placement_name", placement.getPlacementName());
            hashMap.put("placement_reward_amount", String.valueOf(placement.getRewardAmount()));
            hashMap.put("placement_reward_name", placement.getRewardName());
            this.sendUnityEvent("onRewardedVideoAdClickedDemandOnly", Arrays.toString(new String[] { s, new JSONObject((Map)hashMap).toString() }));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void onRewardedVideoAdClosed() {
        this.sendUnityEvent("onRewardedVideoAdClosed", "");
    }
    
    public void onRewardedVideoAdClosed(final String s) {
        this.sendUnityEvent("onRewardedVideoAdClosedDemandOnly", s);
    }
    
    public void onRewardedVideoAdEnded() {
        this.sendUnityEvent("onRewardedVideoAdEnded", "");
    }
    
    public void onRewardedVideoAdOpened() {
        this.sendUnityEvent("onRewardedVideoAdOpened", "");
    }
    
    public void onRewardedVideoAdOpened(final String s) {
        this.sendUnityEvent("onRewardedVideoAdOpenedDemandOnly", s);
    }
    
    public void onRewardedVideoAdRewarded(final Placement placement) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        try {
            hashMap.put("placement_id", String.valueOf(placement.getPlacementId()));
            hashMap.put("placement_name", placement.getPlacementName());
            hashMap.put("placement_reward_amount", String.valueOf(placement.getRewardAmount()));
            hashMap.put("placement_reward_name", placement.getRewardName());
            this.sendUnityEvent("onRewardedVideoAdRewarded", new JSONObject((Map)hashMap).toString());
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void onRewardedVideoAdRewarded(final String s, final Placement placement) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        try {
            hashMap.put("placement_id", String.valueOf(placement.getPlacementId()));
            hashMap.put("placement_name", placement.getPlacementName());
            hashMap.put("placement_reward_amount", String.valueOf(placement.getRewardAmount()));
            hashMap.put("placement_reward_name", placement.getRewardName());
            this.sendUnityEvent("onRewardedVideoAdRewardedDemandOnly", Arrays.toString(new String[] { s, new JSONObject((Map)hashMap).toString() }));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void onRewardedVideoAdShowFailed(final IronSourceError ironSourceError) {
        if (ironSourceError != null) {
            this.sendUnityEvent("onRewardedVideoAdShowFailed", this.parseErrorToEvent(ironSourceError.getErrorCode(), ironSourceError.getErrorMessage()));
            return;
        }
        this.sendUnityEvent("onRewardedVideoAdShowFailed", "");
    }
    
    public void onRewardedVideoAdShowFailed(final String s, final IronSourceError ironSourceError) {
        String[] array;
        if (ironSourceError != null) {
            array = new String[] { s, this.parseErrorToEvent(ironSourceError.getErrorCode(), ironSourceError.getErrorMessage()) };
        }
        else {
            array = new String[] { s, "" };
        }
        this.sendUnityEvent("onRewardedVideoAdShowFailedDemandOnly", Arrays.toString(array));
    }
    
    public void onRewardedVideoAdStarted() {
        this.sendUnityEvent("onRewardedVideoAdStarted", "");
    }
    
    public void onRewardedVideoAvailabilityChanged(final String s, final boolean b) {
        this.sendUnityEvent("onRewardedVideoAvailabilityChangedDemandOnly", Arrays.toString(new String[] { s, String.valueOf(b) }));
    }
    
    public void onRewardedVideoAvailabilityChanged(final boolean b) {
        this.sendUnityEvent("onRewardedVideoAvailabilityChanged", String.valueOf(b));
    }
    
    public void onSegmentReceived(final String s) {
        this.sendUnityEvent("onSegmentReceived", s);
    }
    
    public String parseErrorToEvent(final int n, String string) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        try {
            hashMap.put("error_code", String.valueOf(n));
            hashMap.put("error_description", string);
            string = new JSONObject((Map)hashMap).toString();
            return string;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }
    
    public void setAdaptersDebug(final boolean adaptersDebug) {
        IronSource.setAdaptersDebug(adaptersDebug);
    }
    
    public void setAge(final int age) {
        IronSource.setAge(age);
    }
    
    public void setClientSideCallbacks(final boolean clientSideCallbacks) {
        SupersonicConfig.getConfigObj().setClientSideCallbacks(clientSideCallbacks);
    }
    
    public void setConsent(final boolean consent) {
        IronSource.setConsent(consent);
    }
    
    public boolean setDynamicUserId(final String dynamicUserId) {
        return IronSource.setDynamicUserId(dynamicUserId);
    }
    
    public void setGender(final String gender) {
        IronSource.setGender(gender);
    }
    
    public void setLanguage(final String language) {
        SupersonicConfig.getConfigObj().setLanguage(language);
    }
    
    public void setMediationSegment(final String mediationSegment) {
        IronSource.setMediationSegment(mediationSegment);
    }
    
    public void setOfferwallCustomParams(final String s) {
        SupersonicConfig.getConfigObj().setOfferwallCustomParams((Map)this.getHashMapFromJsonString(s));
    }
    
    public void setPluginData(final String s, final String s2, final String s3) {
        ConfigFile.getConfigFile().setPluginData(s, s2, s3);
    }
    
    public void setRewardedVideoCustomParams(final String s) {
        SupersonicConfig.getConfigObj().setRewardedVideoCustomParams((Map)this.getHashMapFromJsonString(s));
    }
    
    public void setRewardedVideoServerParams(final String s) {
        IronSource.setRewardedVideoServerParameters((Map)this.getHashMapFromJsonString(s));
    }
    
    public void setSegment(final String s) {
        IronSourceSegment segment = null;
        while (true) {
            while (true) {
                JSONObject jsonObject = null;
                String s2 = null;
                Label_0079: {
                    try {
                        IronSource.setSegmentListener((SegmentListener)this);
                        jsonObject = new JSONObject(s);
                        segment = new IronSourceSegment();
                        final Iterator keys = jsonObject.keys();
                        while (keys.hasNext()) {
                            s2 = keys.next();
                            if (!s2.equals("age")) {
                                break Label_0079;
                            }
                            segment.setAge(jsonObject.optInt(s2));
                        }
                        break;
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                        return;
                    }
                }
                if (s2.equals("gender")) {
                    segment.setGender(jsonObject.optString(s2));
                    continue;
                }
                if (s2.equals("level")) {
                    segment.setLevel(jsonObject.optInt(s2));
                    continue;
                }
                if (s2.equals("isPaying")) {
                    segment.setIsPaying(jsonObject.optInt(s2) != 0);
                    continue;
                }
                if (s2.equals("userCreationDate")) {
                    segment.setUserCreationDate(jsonObject.optLong(s2));
                    continue;
                }
                if (s2.equals("segmentName")) {
                    segment.setSegmentName(jsonObject.optString(s2));
                    continue;
                }
                if (s2.equals("iapt")) {
                    segment.setIAPTotal(jsonObject.optDouble(s2));
                    continue;
                }
                segment.setCustom(s2, jsonObject.optString(s2));
                continue;
            }
        }
        IronSource.setSegment(segment);
    }
    
    public void setUserId(final String userId) {
        IronSource.setUserId(userId);
    }
    
    public void shouldTrackNetworkState(final boolean b) {
        IronSource.shouldTrackNetworkState((Context)this.getUnityActivity(), b);
    }
    
    public void showISDemandOnlyInterstitial(final String s) {
        IronSource.showISDemandOnlyInterstitial(s);
    }
    
    public void showISDemandOnlyInterstitial(final String s, final String s2) {
        IronSource.showISDemandOnlyInterstitial(s, s2);
    }
    
    public void showISDemandOnlyRewardedVideo(final String s) {
        IronSource.showISDemandOnlyRewardedVideo(s);
    }
    
    public void showISDemandOnlyRewardedVideo(final String s, final String s2) {
        IronSource.showISDemandOnlyRewardedVideo(s, s2);
    }
    
    public void showInterstitial() {
        IronSource.showInterstitial();
    }
    
    public void showInterstitial(final String s) {
        IronSource.showInterstitial(s);
    }
    
    public void showOfferwall() {
        IronSource.showOfferwall();
    }
    
    public void showOfferwall(final String s) {
        IronSource.showOfferwall(s);
    }
    
    public void showRewardedVideo() {
        IronSource.showRewardedVideo();
    }
    
    public void showRewardedVideo(final String s) {
        IronSource.showRewardedVideo(s);
    }
    
    public void validateIntegration() {
        IntegrationHelper.validateIntegration(this.getUnityActivity());
    }
}
