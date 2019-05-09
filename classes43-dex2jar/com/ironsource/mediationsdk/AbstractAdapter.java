// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk;

import com.ironsource.mediationsdk.logger.LogListener;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import android.app.Activity;
import org.json.JSONObject;
import com.ironsource.mediationsdk.sdk.RewardedInterstitialListener;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import com.ironsource.mediationsdk.sdk.RewardedVideoSmashListener;
import com.ironsource.mediationsdk.sdk.InterstitialSmashListener;
import com.ironsource.mediationsdk.sdk.BannerSmashListener;
import com.ironsource.mediationsdk.sdk.RewardedInterstitialApi;
import com.ironsource.mediationsdk.logger.LoggingApi;
import com.ironsource.mediationsdk.sdk.BannerAdapterApi;
import com.ironsource.mediationsdk.sdk.RewardedVideoAdapterApi;
import com.ironsource.mediationsdk.sdk.InterstitialAdapterApi;
import com.ironsource.mediationsdk.sdk.BaseApi;

public abstract class AbstractAdapter implements BaseApi, InterstitialAdapterApi, RewardedVideoAdapterApi, BannerAdapterApi, LoggingApi, RewardedInterstitialApi
{
    protected BannerSmashListener mActiveBannerSmash;
    protected InterstitialSmashListener mActiveInterstitialSmash;
    protected RewardedVideoSmashListener mActiveRewardedVideoSmash;
    protected CopyOnWriteArrayList<BannerSmashListener> mAllBannerSmashes;
    protected CopyOnWriteArrayList<InterstitialSmashListener> mAllInterstitialSmashes;
    protected CopyOnWriteArrayList<RewardedVideoSmashListener> mAllRewardedVideoSmashes;
    protected ConcurrentHashMap<String, BannerSmashListener> mBannerPlacementToListenerMap;
    protected ConcurrentHashMap<String, InterstitialSmashListener> mInterstitialPlacementToListenerMap;
    private IronSourceLoggerManager mLoggerManager;
    private String mPluginFrameworkVersion;
    private String mPluginType;
    private String mProviderName;
    protected RewardedInterstitialListener mRewardedInterstitial;
    protected ConcurrentHashMap<String, RewardedVideoSmashListener> mRewardedVideoPlacementToListenerMap;
    
    public AbstractAdapter(final String mProviderName) {
        this.mLoggerManager = IronSourceLoggerManager.getLogger();
        this.mAllRewardedVideoSmashes = new CopyOnWriteArrayList<RewardedVideoSmashListener>();
        this.mAllInterstitialSmashes = new CopyOnWriteArrayList<InterstitialSmashListener>();
        this.mRewardedVideoPlacementToListenerMap = new ConcurrentHashMap<String, RewardedVideoSmashListener>();
        this.mInterstitialPlacementToListenerMap = new ConcurrentHashMap<String, InterstitialSmashListener>();
        this.mBannerPlacementToListenerMap = new ConcurrentHashMap<String, BannerSmashListener>();
        this.mProviderName = mProviderName;
    }
    
    protected void addBannerListener(final BannerSmashListener bannerSmashListener) {
    }
    
    @Override
    public void addInterstitialListener(final InterstitialSmashListener interstitialSmashListener) {
        this.mAllInterstitialSmashes.add(interstitialSmashListener);
    }
    
    @Override
    public void addRewardedVideoListener(final RewardedVideoSmashListener rewardedVideoSmashListener) {
        this.mAllRewardedVideoSmashes.add(rewardedVideoSmashListener);
    }
    
    @Override
    public void destroyBanner(final JSONObject jsonObject) {
    }
    
    public abstract String getCoreSDKVersion();
    
    protected String getDynamicUserId() {
        return IronSourceObject.getInstance().getDynamicUserId();
    }
    
    public String getPluginFrameworkVersion() {
        return this.mPluginFrameworkVersion;
    }
    
    public String getPluginType() {
        return this.mPluginType;
    }
    
    public String getProviderName() {
        return this.mProviderName;
    }
    
    public abstract String getVersion();
    
    public void initBanners(final Activity activity, final String s, final String s2, final JSONObject jsonObject, final BannerSmashListener bannerSmashListener) {
    }
    
    protected boolean isAdaptersDebugEnabled() {
        return this.mLoggerManager.isDebugEnabled();
    }
    
    @Override
    public void loadBanner(final IronSourceBannerLayout ironSourceBannerLayout, final JSONObject jsonObject, final BannerSmashListener bannerSmashListener) {
    }
    
    protected void log(final IronSourceLogger.IronSourceTag ironSourceTag, final String s, final int n) {
        this.mLoggerManager.onLog(ironSourceTag, s, n);
    }
    
    @Override
    public void onPause(final Activity activity) {
    }
    
    @Override
    public void onResume(final Activity activity) {
    }
    
    @Override
    public void reloadBanner(final JSONObject jsonObject) {
    }
    
    protected void removeBannerListener(final BannerSmashListener bannerSmashListener) {
    }
    
    @Override
    public void removeInterstitialListener(final InterstitialSmashListener interstitialSmashListener) {
        this.mAllInterstitialSmashes.remove(interstitialSmashListener);
    }
    
    @Override
    public void removeRewardedVideoListener(final RewardedVideoSmashListener rewardedVideoSmashListener) {
        this.mAllRewardedVideoSmashes.remove(rewardedVideoSmashListener);
    }
    
    @Override
    public void setAge(final int n) {
    }
    
    protected void setConsent(final boolean b) {
    }
    
    @Override
    public void setGender(final String s) {
    }
    
    @Override
    public void setLogListener(final LogListener logListener) {
    }
    
    @Override
    public void setMediationSegment(final String s) {
    }
    
    protected void setMediationState(final AbstractSmash.MEDIATION_STATE mediation_STATE, final String s) {
    }
    
    void setPluginData(final String mPluginType, final String mPluginFrameworkVersion) {
        this.mPluginType = mPluginType;
        this.mPluginFrameworkVersion = mPluginFrameworkVersion;
    }
    
    @Override
    public void setRewardedInterstitialListener(final RewardedInterstitialListener mRewardedInterstitial) {
        this.mRewardedInterstitial = mRewardedInterstitial;
    }
}
