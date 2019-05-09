// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk;

import android.widget.FrameLayout$LayoutParams;
import android.view.View;
import android.app.Activity;
import com.ironsource.mediationsdk.logger.IronSourceError;
import java.util.TimerTask;
import com.ironsource.mediationsdk.config.ConfigFile;
import android.text.TextUtils;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import java.util.Timer;
import com.ironsource.mediationsdk.sdk.BannerManagerListener;
import com.ironsource.mediationsdk.model.ProviderSettings;
import com.ironsource.mediationsdk.sdk.BannerSmashListener;

public class BannerSmash implements BannerSmashListener
{
    private AbstractAdapter mAdapter;
    private ProviderSettings mAdapterConfigs;
    private IronSourceBannerLayout mBannerLayout;
    private boolean mIsReadyToLoad;
    private BannerManagerListener mListener;
    private long mLoadTimeoutMilisecs;
    private int mProviderPriority;
    private BANNER_SMASH_STATE mState;
    private Timer mTimer;
    
    BannerSmash(final BannerManagerListener mListener, final ProviderSettings mAdapterConfigs, final AbstractAdapter mAdapter, final long mLoadTimeoutMilisecs, final int mProviderPriority) {
        this.mState = BANNER_SMASH_STATE.NO_INIT;
        this.mProviderPriority = mProviderPriority;
        this.mListener = mListener;
        this.mAdapter = mAdapter;
        this.mAdapterConfigs = mAdapterConfigs;
        this.mLoadTimeoutMilisecs = mLoadTimeoutMilisecs;
        this.mAdapter.addBannerListener(this);
    }
    
    private void log(final String s) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.ADAPTER_API, "BannerSmash " + this.getName() + " " + s, 1);
    }
    
    private void logException(final String s, final String s2) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, s + " Banner exception: " + this.getName() + " | " + s2, 3);
    }
    
    private void setCustomParams() {
        if (this.mAdapter != null) {
            try {
                final Integer age = IronSourceObject.getInstance().getAge();
                if (age != null) {
                    this.mAdapter.setAge(age);
                }
                final String gender = IronSourceObject.getInstance().getGender();
                if (!TextUtils.isEmpty((CharSequence)gender)) {
                    this.mAdapter.setGender(gender);
                }
                final String mediationSegment = IronSourceObject.getInstance().getMediationSegment();
                if (!TextUtils.isEmpty((CharSequence)mediationSegment)) {
                    this.mAdapter.setMediationSegment(mediationSegment);
                }
                final String pluginType = ConfigFile.getConfigFile().getPluginType();
                if (!TextUtils.isEmpty((CharSequence)pluginType)) {
                    this.mAdapter.setPluginData(pluginType, ConfigFile.getConfigFile().getPluginFrameworkVersion());
                }
                final Boolean consent = IronSourceObject.getInstance().getConsent();
                if (consent != null) {
                    this.log("setConsent(" + consent + ")");
                    this.mAdapter.setConsent(consent);
                }
            }
            catch (Exception ex) {
                this.log(":setCustomParams():" + ex.toString());
            }
        }
    }
    
    private void setState(final BANNER_SMASH_STATE mState) {
        this.mState = mState;
        this.log("state=" + mState.name());
    }
    
    private void startLoadTimer() {
        try {
            this.stopLoadTimer();
            (this.mTimer = new Timer()).schedule(new TimerTask() {
                @Override
                public void run() {
                    this.cancel();
                    if (BannerSmash.this.mState == BANNER_SMASH_STATE.INIT_IN_PROGRESS) {
                        BannerSmash.this.log("init timed out");
                        BannerSmash.this.mListener.onBannerAdLoadFailed(new IronSourceError(607, "Timed out"), BannerSmash.this);
                    }
                    else if (BannerSmash.this.mState == BANNER_SMASH_STATE.LOAD_IN_PROGRESS) {
                        BannerSmash.this.log("load timed out");
                        BannerSmash.this.mListener.onBannerAdLoadFailed(new IronSourceError(608, "Timed out"), BannerSmash.this);
                    }
                    else if (BannerSmash.this.mState == BANNER_SMASH_STATE.LOADED) {
                        BannerSmash.this.log("reload timed out");
                        BannerSmash.this.mListener.onBannerAdReloadFailed(new IronSourceError(609, "Timed out"), BannerSmash.this);
                    }
                    BannerSmash.this.setState(BANNER_SMASH_STATE.LOAD_FAILED);
                }
            }, this.mLoadTimeoutMilisecs);
        }
        catch (Exception ex) {
            this.logException("startLoadTimer", ex.getLocalizedMessage());
        }
    }
    
    private void stopLoadTimer() {
        try {
            if (this.mTimer != null) {
                this.mTimer.cancel();
            }
        }
        catch (Exception ex) {
            this.logException("stopLoadTimer", ex.getLocalizedMessage());
        }
        finally {
            this.mTimer = null;
        }
    }
    
    public void destroyBanner() {
        this.log("destroyBanner()");
        if (this.mAdapter == null) {
            this.log("destroyBanner() mAdapter == null");
            return;
        }
        this.mAdapter.destroyBanner(this.mAdapterConfigs.getBannerSettings());
        this.setState(BANNER_SMASH_STATE.DESTROYED);
    }
    
    public String getAdSourceNameForEvents() {
        if (!TextUtils.isEmpty((CharSequence)this.mAdapterConfigs.getAdSourceNameForEvents())) {
            return this.mAdapterConfigs.getAdSourceNameForEvents();
        }
        return this.getName();
    }
    
    public AbstractAdapter getAdapter() {
        return this.mAdapter;
    }
    
    public String getName() {
        if (this.mAdapterConfigs.isMultipleInstances()) {
            return this.mAdapterConfigs.getProviderTypeForReflection();
        }
        return this.mAdapterConfigs.getProviderName();
    }
    
    public int getProviderPriority() {
        return this.mProviderPriority;
    }
    
    public String getSubProviderId() {
        return this.mAdapterConfigs.getSubProviderId();
    }
    
    public boolean isReadyToLoad() {
        return this.mIsReadyToLoad;
    }
    
    public void loadBanner(final IronSourceBannerLayout mBannerLayout, final Activity activity, final String s, final String s2) {
        this.log("loadBanner()");
        this.mIsReadyToLoad = false;
        if (mBannerLayout == null) {
            this.mListener.onBannerAdLoadFailed(new IronSourceError(610, "banner==null"), this);
            return;
        }
        if (this.mAdapter == null) {
            this.mListener.onBannerAdLoadFailed(new IronSourceError(611, "adapter==null"), this);
            return;
        }
        this.mBannerLayout = mBannerLayout;
        this.startLoadTimer();
        if (this.mState == BANNER_SMASH_STATE.NO_INIT) {
            this.setState(BANNER_SMASH_STATE.INIT_IN_PROGRESS);
            this.setCustomParams();
            this.mAdapter.initBanners(activity, s, s2, this.mAdapterConfigs.getBannerSettings(), this);
            return;
        }
        this.setState(BANNER_SMASH_STATE.LOAD_IN_PROGRESS);
        this.mAdapter.loadBanner(mBannerLayout, this.mAdapterConfigs.getBannerSettings(), this);
    }
    
    @Override
    public void onBannerAdClicked() {
        if (this.mListener != null) {
            this.mListener.onBannerAdClicked(this);
        }
    }
    
    @Override
    public void onBannerAdLeftApplication() {
        if (this.mListener != null) {
            this.mListener.onBannerAdLeftApplication(this);
        }
    }
    
    @Override
    public void onBannerAdLoadFailed(final IronSourceError ironSourceError) {
        this.log("onBannerAdLoadFailed()");
        this.stopLoadTimer();
        if (this.mState == BANNER_SMASH_STATE.LOAD_IN_PROGRESS) {
            this.setState(BANNER_SMASH_STATE.LOAD_FAILED);
            this.mListener.onBannerAdLoadFailed(ironSourceError, this);
        }
        else if (this.mState == BANNER_SMASH_STATE.LOADED) {
            this.mListener.onBannerAdReloadFailed(ironSourceError, this);
        }
    }
    
    @Override
    public void onBannerAdLoaded(final View view, final FrameLayout$LayoutParams frameLayout$LayoutParams) {
        this.log("onBannerAdLoaded()");
        this.stopLoadTimer();
        if (this.mState == BANNER_SMASH_STATE.LOAD_IN_PROGRESS) {
            this.setState(BANNER_SMASH_STATE.LOADED);
            this.mListener.onBannerAdLoaded(this, view, frameLayout$LayoutParams);
        }
        else if (this.mState == BANNER_SMASH_STATE.LOADED) {
            this.mListener.onBannerAdReloaded(this);
        }
    }
    
    @Override
    public void onBannerAdScreenDismissed() {
        if (this.mListener != null) {
            this.mListener.onBannerAdScreenDismissed(this);
        }
    }
    
    @Override
    public void onBannerAdScreenPresented() {
        if (this.mListener != null) {
            this.mListener.onBannerAdScreenPresented(this);
        }
    }
    
    @Override
    public void onBannerInitFailed(final IronSourceError ironSourceError) {
        this.stopLoadTimer();
        if (this.mState == BANNER_SMASH_STATE.INIT_IN_PROGRESS) {
            this.mListener.onBannerAdLoadFailed(new IronSourceError(612, "Banner init failed"), this);
            this.setState(BANNER_SMASH_STATE.NO_INIT);
        }
    }
    
    @Override
    public void onBannerInitSuccess() {
        this.stopLoadTimer();
        if (this.mState == BANNER_SMASH_STATE.INIT_IN_PROGRESS) {
            this.startLoadTimer();
            this.setState(BANNER_SMASH_STATE.LOAD_IN_PROGRESS);
            this.mAdapter.loadBanner(this.mBannerLayout, this.mAdapterConfigs.getBannerSettings(), this);
        }
    }
    
    public void onPause(final Activity activity) {
        if (this.mAdapter != null) {
            this.mAdapter.onPause(activity);
        }
    }
    
    public void onResume(final Activity activity) {
        if (this.mAdapter != null) {
            this.mAdapter.onResume(activity);
        }
    }
    
    public void reloadBanner() {
        this.log("reloadBanner()");
        this.startLoadTimer();
        this.mAdapter.reloadBanner(this.mAdapterConfigs.getBannerSettings());
    }
    
    public void setConsent(final boolean consent) {
        if (this.mAdapter != null) {
            this.log("setConsent(" + consent + ")");
            this.mAdapter.setConsent(consent);
        }
    }
    
    public void setReadyToLoad(final boolean mIsReadyToLoad) {
        this.mIsReadyToLoad = mIsReadyToLoad;
    }
    
    protected enum BANNER_SMASH_STATE
    {
        DESTROYED, 
        INIT_IN_PROGRESS, 
        LOADED, 
        LOAD_FAILED, 
        LOAD_IN_PROGRESS, 
        NO_INIT;
    }
}
