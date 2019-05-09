package com.ironsource.mediationsdk;

import android.app.Activity;
import com.ironsource.mediationsdk.AbstractSmash.MEDIATION_STATE;
import com.ironsource.mediationsdk.logger.IronSourceLogger.IronSourceTag;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.logger.LogListener;
import com.ironsource.mediationsdk.logger.LoggingApi;
import com.ironsource.mediationsdk.sdk.BannerAdapterApi;
import com.ironsource.mediationsdk.sdk.BannerSmashListener;
import com.ironsource.mediationsdk.sdk.BaseApi;
import com.ironsource.mediationsdk.sdk.InterstitialAdapterApi;
import com.ironsource.mediationsdk.sdk.InterstitialSmashListener;
import com.ironsource.mediationsdk.sdk.RewardedInterstitialApi;
import com.ironsource.mediationsdk.sdk.RewardedInterstitialListener;
import com.ironsource.mediationsdk.sdk.RewardedVideoAdapterApi;
import com.ironsource.mediationsdk.sdk.RewardedVideoSmashListener;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONObject;

public abstract class AbstractAdapter implements BaseApi, InterstitialAdapterApi, RewardedVideoAdapterApi, BannerAdapterApi, LoggingApi, RewardedInterstitialApi {
    protected BannerSmashListener mActiveBannerSmash;
    protected InterstitialSmashListener mActiveInterstitialSmash;
    protected RewardedVideoSmashListener mActiveRewardedVideoSmash;
    protected CopyOnWriteArrayList<BannerSmashListener> mAllBannerSmashes;
    protected CopyOnWriteArrayList<InterstitialSmashListener> mAllInterstitialSmashes = new CopyOnWriteArrayList();
    protected CopyOnWriteArrayList<RewardedVideoSmashListener> mAllRewardedVideoSmashes = new CopyOnWriteArrayList();
    protected ConcurrentHashMap<String, BannerSmashListener> mBannerPlacementToListenerMap = new ConcurrentHashMap();
    protected ConcurrentHashMap<String, InterstitialSmashListener> mInterstitialPlacementToListenerMap = new ConcurrentHashMap();
    private IronSourceLoggerManager mLoggerManager = IronSourceLoggerManager.getLogger();
    private String mPluginFrameworkVersion;
    private String mPluginType;
    private String mProviderName;
    protected RewardedInterstitialListener mRewardedInterstitial;
    protected ConcurrentHashMap<String, RewardedVideoSmashListener> mRewardedVideoPlacementToListenerMap = new ConcurrentHashMap();

    public abstract String getCoreSDKVersion();

    public abstract String getVersion();

    public AbstractAdapter(String providerName) {
        this.mProviderName = providerName;
    }

    public String getProviderName() {
        return this.mProviderName;
    }

    protected String getDynamicUserId() {
        return IronSourceObject.getInstance().getDynamicUserId();
    }

    void setPluginData(String pluginType, String pluginFrameworkVersion) {
        this.mPluginType = pluginType;
        this.mPluginFrameworkVersion = pluginFrameworkVersion;
    }

    public String getPluginType() {
        return this.mPluginType;
    }

    public String getPluginFrameworkVersion() {
        return this.mPluginFrameworkVersion;
    }

    protected void log(IronSourceTag tag, String message, int logLevel) {
        this.mLoggerManager.onLog(tag, message, logLevel);
    }

    public void setLogListener(LogListener logListener) {
    }

    public void setRewardedInterstitialListener(RewardedInterstitialListener listener) {
        this.mRewardedInterstitial = listener;
    }

    protected boolean isAdaptersDebugEnabled() {
        return this.mLoggerManager.isDebugEnabled();
    }

    public void initBanners(Activity activity, String appKey, String userId, JSONObject config, BannerSmashListener listener) {
    }

    public void loadBanner(IronSourceBannerLayout banner, JSONObject config, BannerSmashListener listener) {
    }

    public void destroyBanner(JSONObject config) {
    }

    public void reloadBanner(JSONObject config) {
    }

    protected void addBannerListener(BannerSmashListener listener) {
    }

    protected void removeBannerListener(BannerSmashListener listener) {
    }

    public void addRewardedVideoListener(RewardedVideoSmashListener listener) {
        this.mAllRewardedVideoSmashes.add(listener);
    }

    public void removeRewardedVideoListener(RewardedVideoSmashListener listener) {
        this.mAllRewardedVideoSmashes.remove(listener);
    }

    public void addInterstitialListener(InterstitialSmashListener listener) {
        this.mAllInterstitialSmashes.add(listener);
    }

    public void removeInterstitialListener(InterstitialSmashListener listener) {
        this.mAllInterstitialSmashes.remove(listener);
    }

    public void onResume(Activity activity) {
    }

    public void onPause(Activity activity) {
    }

    public void setAge(int age) {
    }

    public void setGender(String gender) {
    }

    public void setMediationSegment(String segment) {
    }

    protected void setMediationState(MEDIATION_STATE state, String adUnit) {
    }

    protected void setConsent(boolean consent) {
    }
}
