package com.ironsource.mediationsdk;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import com.ironsource.mediationsdk.config.ConfigFile;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.logger.IronSourceLogger.IronSourceTag;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.model.ProviderSettings;
import com.ironsource.mediationsdk.sdk.BannerManagerListener;
import com.ironsource.mediationsdk.sdk.BannerSmashListener;
import java.util.Timer;
import java.util.TimerTask;

public class BannerSmash implements BannerSmashListener {
    private AbstractAdapter mAdapter;
    private ProviderSettings mAdapterConfigs;
    private IronSourceBannerLayout mBannerLayout;
    private boolean mIsReadyToLoad;
    private BannerManagerListener mListener;
    private long mLoadTimeoutMilisecs;
    private int mProviderPriority;
    private BANNER_SMASH_STATE mState = BANNER_SMASH_STATE.NO_INIT;
    private Timer mTimer;

    /* renamed from: com.ironsource.mediationsdk.BannerSmash$1 */
    class C06791 extends TimerTask {
        C06791() {
        }

        public void run() {
            cancel();
            if (BannerSmash.this.mState == BANNER_SMASH_STATE.INIT_IN_PROGRESS) {
                BannerSmash.this.log("init timed out");
                BannerSmash.this.mListener.onBannerAdLoadFailed(new IronSourceError(IronSourceError.ERROR_BN_INSTANCE_INIT_TIMEOUT, "Timed out"), BannerSmash.this);
            } else if (BannerSmash.this.mState == BANNER_SMASH_STATE.LOAD_IN_PROGRESS) {
                BannerSmash.this.log("load timed out");
                BannerSmash.this.mListener.onBannerAdLoadFailed(new IronSourceError(IronSourceError.ERROR_BN_INSTANCE_LOAD_TIMEOUT, "Timed out"), BannerSmash.this);
            } else if (BannerSmash.this.mState == BANNER_SMASH_STATE.LOADED) {
                BannerSmash.this.log("reload timed out");
                BannerSmash.this.mListener.onBannerAdReloadFailed(new IronSourceError(IronSourceError.ERROR_BN_INSTANCE_RELOAD_TIMEOUT, "Timed out"), BannerSmash.this);
            }
            BannerSmash.this.setState(BANNER_SMASH_STATE.LOAD_FAILED);
        }
    }

    protected enum BANNER_SMASH_STATE {
        NO_INIT,
        INIT_IN_PROGRESS,
        LOAD_IN_PROGRESS,
        LOADED,
        LOAD_FAILED,
        DESTROYED
    }

    BannerSmash(BannerManagerListener listener, ProviderSettings adapterConfigs, AbstractAdapter adapter, long timeout, int providerPriority) {
        this.mProviderPriority = providerPriority;
        this.mListener = listener;
        this.mAdapter = adapter;
        this.mAdapterConfigs = adapterConfigs;
        this.mLoadTimeoutMilisecs = timeout;
        this.mAdapter.addBannerListener(this);
    }

    public boolean isReadyToLoad() {
        return this.mIsReadyToLoad;
    }

    public void setReadyToLoad(boolean isReadyToLoad) {
        this.mIsReadyToLoad = isReadyToLoad;
    }

    public int getProviderPriority() {
        return this.mProviderPriority;
    }

    public String getName() {
        if (this.mAdapterConfigs.isMultipleInstances()) {
            return this.mAdapterConfigs.getProviderTypeForReflection();
        }
        return this.mAdapterConfigs.getProviderName();
    }

    public String getAdSourceNameForEvents() {
        if (TextUtils.isEmpty(this.mAdapterConfigs.getAdSourceNameForEvents())) {
            return getName();
        }
        return this.mAdapterConfigs.getAdSourceNameForEvents();
    }

    public String getSubProviderId() {
        return this.mAdapterConfigs.getSubProviderId();
    }

    public AbstractAdapter getAdapter() {
        return this.mAdapter;
    }

    public void loadBanner(IronSourceBannerLayout bannerLayout, Activity activity, String appKey, String userId) {
        log("loadBanner()");
        this.mIsReadyToLoad = false;
        if (bannerLayout == null) {
            this.mListener.onBannerAdLoadFailed(new IronSourceError(IronSourceError.ERROR_BN_INSTANCE_LOAD_EMPTY_BANNER, "banner==null"), this);
        } else if (this.mAdapter == null) {
            this.mListener.onBannerAdLoadFailed(new IronSourceError(IronSourceError.ERROR_BN_INSTANCE_LOAD_EMPTY_ADAPTER, "adapter==null"), this);
        } else {
            this.mBannerLayout = bannerLayout;
            startLoadTimer();
            if (this.mState == BANNER_SMASH_STATE.NO_INIT) {
                setState(BANNER_SMASH_STATE.INIT_IN_PROGRESS);
                setCustomParams();
                this.mAdapter.initBanners(activity, appKey, userId, this.mAdapterConfigs.getBannerSettings(), this);
                return;
            }
            setState(BANNER_SMASH_STATE.LOAD_IN_PROGRESS);
            this.mAdapter.loadBanner(bannerLayout, this.mAdapterConfigs.getBannerSettings(), this);
        }
    }

    public void reloadBanner() {
        log("reloadBanner()");
        startLoadTimer();
        this.mAdapter.reloadBanner(this.mAdapterConfigs.getBannerSettings());
    }

    public void destroyBanner() {
        log("destroyBanner()");
        if (this.mAdapter == null) {
            log("destroyBanner() mAdapter == null");
            return;
        }
        this.mAdapter.destroyBanner(this.mAdapterConfigs.getBannerSettings());
        setState(BANNER_SMASH_STATE.DESTROYED);
    }

    private void setCustomParams() {
        if (this.mAdapter != null) {
            try {
                Integer age = IronSourceObject.getInstance().getAge();
                if (age != null) {
                    this.mAdapter.setAge(age.intValue());
                }
                String gender = IronSourceObject.getInstance().getGender();
                if (!TextUtils.isEmpty(gender)) {
                    this.mAdapter.setGender(gender);
                }
                String segment = IronSourceObject.getInstance().getMediationSegment();
                if (!TextUtils.isEmpty(segment)) {
                    this.mAdapter.setMediationSegment(segment);
                }
                String pluginType = ConfigFile.getConfigFile().getPluginType();
                if (!TextUtils.isEmpty(pluginType)) {
                    this.mAdapter.setPluginData(pluginType, ConfigFile.getConfigFile().getPluginFrameworkVersion());
                }
                Boolean consent = IronSourceObject.getInstance().getConsent();
                if (consent != null) {
                    log("setConsent(" + consent + ")");
                    this.mAdapter.setConsent(consent.booleanValue());
                }
            } catch (Exception e) {
                log(":setCustomParams():" + e.toString());
            }
        }
    }

    private void setState(BANNER_SMASH_STATE state) {
        this.mState = state;
        log("state=" + state.name());
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void stopLoadTimer() {
        /*
        r4 = this;
        r3 = 0;
        r1 = r4.mTimer;	 Catch:{ Exception -> 0x000d }
        if (r1 == 0) goto L_0x000a;
    L_0x0005:
        r1 = r4.mTimer;	 Catch:{ Exception -> 0x000d }
        r1.cancel();	 Catch:{ Exception -> 0x000d }
    L_0x000a:
        r4.mTimer = r3;
    L_0x000c:
        return;
    L_0x000d:
        r0 = move-exception;
        r1 = "stopLoadTimer";
        r2 = r0.getLocalizedMessage();	 Catch:{ all -> 0x001a }
        r4.logException(r1, r2);	 Catch:{ all -> 0x001a }
        r4.mTimer = r3;
        goto L_0x000c;
    L_0x001a:
        r1 = move-exception;
        r4.mTimer = r3;
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ironsource.mediationsdk.BannerSmash.stopLoadTimer():void");
    }

    private void startLoadTimer() {
        try {
            stopLoadTimer();
            this.mTimer = new Timer();
            this.mTimer.schedule(new C06791(), this.mLoadTimeoutMilisecs);
        } catch (Exception e) {
            logException("startLoadTimer", e.getLocalizedMessage());
        }
    }

    public void onBannerInitSuccess() {
        stopLoadTimer();
        if (this.mState == BANNER_SMASH_STATE.INIT_IN_PROGRESS) {
            startLoadTimer();
            setState(BANNER_SMASH_STATE.LOAD_IN_PROGRESS);
            this.mAdapter.loadBanner(this.mBannerLayout, this.mAdapterConfigs.getBannerSettings(), this);
        }
    }

    public void onBannerInitFailed(IronSourceError error) {
        stopLoadTimer();
        if (this.mState == BANNER_SMASH_STATE.INIT_IN_PROGRESS) {
            this.mListener.onBannerAdLoadFailed(new IronSourceError(IronSourceError.ERROR_BN_INSTANCE_INIT_ERROR, "Banner init failed"), this);
            setState(BANNER_SMASH_STATE.NO_INIT);
        }
    }

    public void onBannerAdLoaded(View adView, LayoutParams frameLayoutParams) {
        log("onBannerAdLoaded()");
        stopLoadTimer();
        if (this.mState == BANNER_SMASH_STATE.LOAD_IN_PROGRESS) {
            setState(BANNER_SMASH_STATE.LOADED);
            this.mListener.onBannerAdLoaded(this, adView, frameLayoutParams);
        } else if (this.mState == BANNER_SMASH_STATE.LOADED) {
            this.mListener.onBannerAdReloaded(this);
        }
    }

    public void onBannerAdLoadFailed(IronSourceError error) {
        log("onBannerAdLoadFailed()");
        stopLoadTimer();
        if (this.mState == BANNER_SMASH_STATE.LOAD_IN_PROGRESS) {
            setState(BANNER_SMASH_STATE.LOAD_FAILED);
            this.mListener.onBannerAdLoadFailed(error, this);
        } else if (this.mState == BANNER_SMASH_STATE.LOADED) {
            this.mListener.onBannerAdReloadFailed(error, this);
        }
    }

    public void onBannerAdClicked() {
        if (this.mListener != null) {
            this.mListener.onBannerAdClicked(this);
        }
    }

    public void onBannerAdScreenPresented() {
        if (this.mListener != null) {
            this.mListener.onBannerAdScreenPresented(this);
        }
    }

    public void onBannerAdScreenDismissed() {
        if (this.mListener != null) {
            this.mListener.onBannerAdScreenDismissed(this);
        }
    }

    public void onBannerAdLeftApplication() {
        if (this.mListener != null) {
            this.mListener.onBannerAdLeftApplication(this);
        }
    }

    public void setConsent(boolean consent) {
        if (this.mAdapter != null) {
            log("setConsent(" + consent + ")");
            this.mAdapter.setConsent(consent);
        }
    }

    public void onPause(Activity activity) {
        if (this.mAdapter != null) {
            this.mAdapter.onPause(activity);
        }
    }

    public void onResume(Activity activity) {
        if (this.mAdapter != null) {
            this.mAdapter.onResume(activity);
        }
    }

    private void log(String text) {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.ADAPTER_API, "BannerSmash " + getName() + " " + text, 1);
    }

    private void logException(String methodName, String errorMessage) {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, methodName + " Banner exception: " + getName() + " | " + errorMessage, 3);
    }
}
