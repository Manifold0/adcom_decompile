package com.ironsource.mediationsdk;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.ironsource.mediationsdk.config.ConfigFile;
import com.ironsource.mediationsdk.logger.IronSourceLogger.IronSourceTag;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.sdk.BaseApi;
import com.ironsource.mediationsdk.utils.DailyCappingManager;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import com.vungle.warren.ui.VungleActivity;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

abstract class AbstractAdUnitManager implements BaseApi {
    final String KEY_PLACEMENT = VungleActivity.PLACEMENT_EXTRA;
    final String KEY_PROVIDER_PRIORITY = "providerPriority";
    final String KEY_REASON = "reason";
    final String KEY_REWARD_AMOUNT = "rewardAmount";
    final String KEY_REWARD_NAME = "rewardName";
    final String KEY_STATUS = "status";
    Activity mActivity;
    String mAppKey;
    boolean mBackFillInitStarted;
    private AbstractSmash mBackfillSmash;
    boolean mCanShowPremium = true;
    DailyCappingManager mDailyCappingManager = null;
    AtomicBoolean mDidImplementOnPause = new AtomicBoolean();
    AtomicBoolean mDidImplementOnResume = new AtomicBoolean();
    boolean mIsInISDemandOnlyMode = false;
    Boolean mLastMediationAvailabilityState;
    IronSourceLoggerManager mLoggerManager = IronSourceLoggerManager.getLogger();
    private AbstractSmash mPremiumSmash;
    boolean mShouldTrackNetworkState = false;
    int mSmartLoadAmount;
    final CopyOnWriteArrayList<AbstractSmash> mSmashArray = new CopyOnWriteArrayList();
    String mUserId;

    abstract void shouldTrackNetworkState(Context context, boolean z);

    AbstractAdUnitManager() {
    }

    public void onResume(Activity activity) {
        this.mDidImplementOnResume.set(true);
        if (activity != null) {
            this.mActivity = activity;
        }
        synchronized (this.mSmashArray) {
            if (this.mSmashArray != null) {
                Iterator it = this.mSmashArray.iterator();
                while (it.hasNext()) {
                    ((AbstractSmash) it.next()).onResume(activity);
                }
            }
        }
    }

    public void onPause(Activity activity) {
        this.mDidImplementOnPause.set(true);
        synchronized (this.mSmashArray) {
            if (this.mSmashArray != null) {
                Iterator it = this.mSmashArray.iterator();
                while (it.hasNext()) {
                    ((AbstractSmash) it.next()).onPause(activity);
                }
            }
        }
    }

    public void setAge(int age) {
    }

    public void setGender(String gender) {
    }

    public void setMediationSegment(String segment) {
    }

    void setSmartLoadAmount(int numberOfAdaptersToLoad) {
        this.mSmartLoadAmount = numberOfAdaptersToLoad;
    }

    void addSmashToArray(AbstractSmash smash) {
        this.mSmashArray.add(smash);
        if (this.mDailyCappingManager != null) {
            this.mDailyCappingManager.addSmash(smash);
        }
    }

    void setBackfillSmash(AbstractSmash backfill) {
        this.mLoggerManager.log(IronSourceTag.INTERNAL, backfill.getInstanceName() + " is set as backfill", 0);
        this.mBackfillSmash = backfill;
    }

    void setPremiumSmash(AbstractSmash premium) {
        this.mLoggerManager.log(IronSourceTag.INTERNAL, premium.getInstanceName() + " is set as premium", 0);
        this.mPremiumSmash = premium;
    }

    AbstractSmash getBackfillSmash() {
        return this.mBackfillSmash;
    }

    AbstractSmash getPremiumSmash() {
        return this.mPremiumSmash;
    }

    void setCustomParams(AbstractSmash smash) {
        try {
            Integer age = IronSourceObject.getInstance().getAge();
            if (age != null) {
                smash.setAge(age.intValue());
            }
            String gender = IronSourceObject.getInstance().getGender();
            if (!TextUtils.isEmpty(gender)) {
                smash.setGender(gender);
            }
            String segment = IronSourceObject.getInstance().getMediationSegment();
            if (!TextUtils.isEmpty(segment)) {
                smash.setMediationSegment(segment);
            }
            String pluginType = ConfigFile.getConfigFile().getPluginType();
            if (!TextUtils.isEmpty(pluginType)) {
                smash.setPluginData(pluginType, ConfigFile.getConfigFile().getPluginFrameworkVersion());
            }
            Boolean consent = IronSourceObject.getInstance().getConsent();
            if (consent != null) {
                smash.setConsent(consent.booleanValue());
            }
        } catch (Exception e) {
            this.mLoggerManager.log(IronSourceTag.INTERNAL, ":setCustomParams():" + e.toString(), 3);
        }
    }

    synchronized boolean canShowPremium() {
        return this.mCanShowPremium;
    }

    synchronized void disablePremiumForCurrentSession() {
        this.mCanShowPremium = false;
    }

    synchronized AbstractAdapter getLoadedAdapterOrFetchByReflection(AbstractSmash smash) {
        AbstractAdapter existingAdapter;
        try {
            existingAdapter = IronSourceObject.getInstance().getExistingAdapter(smash.getName());
            if (existingAdapter == null) {
                this.mLoggerManager.log(IronSourceTag.INTERNAL, "loading " + smash.getName() + " with reflection", 0);
                Class<?> mAdapterClass = Class.forName("com.ironsource.adapters." + smash.getNameForReflection().toLowerCase() + "." + smash.getNameForReflection() + "Adapter");
                existingAdapter = (AbstractAdapter) mAdapterClass.getMethod(IronSourceConstants.START_ADAPTER, new Class[]{String.class}).invoke(mAdapterClass, new Object[]{smash.getName()});
            } else {
                this.mLoggerManager.log(IronSourceTag.INTERNAL, "using previously loaded " + smash.getName(), 0);
            }
        } catch (Exception e) {
            existingAdapter = null;
        }
        return existingAdapter;
    }

    void setConsent(boolean consent) {
        Iterator it = this.mSmashArray.iterator();
        while (it.hasNext()) {
            AbstractSmash smash = (AbstractSmash) it.next();
            if (smash != null) {
                smash.setConsent(consent);
            }
        }
    }

    void verifyOnPauseOnResume() {
        if (!this.mDidImplementOnPause.get()) {
            this.mLoggerManager.log(IronSourceTag.NATIVE, "IronSource.onPause() wasn't overridden in your activity lifecycle!", 3);
        }
        if (!this.mDidImplementOnResume.get()) {
            this.mLoggerManager.log(IronSourceTag.NATIVE, "IronSource.onResume() wasn't overridden in your activity lifecycle!", 3);
        }
    }
}
