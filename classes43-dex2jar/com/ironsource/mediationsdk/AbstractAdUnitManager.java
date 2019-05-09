// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk;

import android.content.Context;
import com.ironsource.mediationsdk.config.ConfigFile;
import android.text.TextUtils;
import java.util.Iterator;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import java.util.concurrent.CopyOnWriteArrayList;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import java.util.concurrent.atomic.AtomicBoolean;
import com.ironsource.mediationsdk.utils.DailyCappingManager;
import android.app.Activity;
import com.ironsource.mediationsdk.sdk.BaseApi;

abstract class AbstractAdUnitManager implements BaseApi
{
    final String KEY_PLACEMENT;
    final String KEY_PROVIDER_PRIORITY;
    final String KEY_REASON;
    final String KEY_REWARD_AMOUNT;
    final String KEY_REWARD_NAME;
    final String KEY_STATUS;
    Activity mActivity;
    String mAppKey;
    boolean mBackFillInitStarted;
    private AbstractSmash mBackfillSmash;
    boolean mCanShowPremium;
    DailyCappingManager mDailyCappingManager;
    AtomicBoolean mDidImplementOnPause;
    AtomicBoolean mDidImplementOnResume;
    boolean mIsInISDemandOnlyMode;
    Boolean mLastMediationAvailabilityState;
    IronSourceLoggerManager mLoggerManager;
    private AbstractSmash mPremiumSmash;
    boolean mShouldTrackNetworkState;
    int mSmartLoadAmount;
    final CopyOnWriteArrayList<AbstractSmash> mSmashArray;
    String mUserId;
    
    AbstractAdUnitManager() {
        this.KEY_REASON = "reason";
        this.KEY_STATUS = "status";
        this.KEY_PLACEMENT = "placement";
        this.KEY_REWARD_NAME = "rewardName";
        this.KEY_REWARD_AMOUNT = "rewardAmount";
        this.KEY_PROVIDER_PRIORITY = "providerPriority";
        this.mShouldTrackNetworkState = false;
        this.mCanShowPremium = true;
        this.mIsInISDemandOnlyMode = false;
        this.mSmashArray = new CopyOnWriteArrayList<AbstractSmash>();
        this.mLoggerManager = IronSourceLoggerManager.getLogger();
        this.mDailyCappingManager = null;
        this.mDidImplementOnResume = new AtomicBoolean();
        this.mDidImplementOnPause = new AtomicBoolean();
    }
    
    void addSmashToArray(final AbstractSmash abstractSmash) {
        this.mSmashArray.add(abstractSmash);
        if (this.mDailyCappingManager != null) {
            this.mDailyCappingManager.addSmash(abstractSmash);
        }
    }
    
    boolean canShowPremium() {
        synchronized (this) {
            return this.mCanShowPremium;
        }
    }
    
    void disablePremiumForCurrentSession() {
        synchronized (this) {
            this.mCanShowPremium = false;
        }
    }
    
    AbstractSmash getBackfillSmash() {
        return this.mBackfillSmash;
    }
    
    AbstractAdapter getLoadedAdapterOrFetchByReflection(final AbstractSmash abstractSmash) {
        synchronized (this) {
            try {
                final AbstractAdapter existingAdapter = IronSourceObject.getInstance().getExistingAdapter(abstractSmash.getName());
                AbstractAdapter abstractAdapter;
                if (existingAdapter == null) {
                    this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, "loading " + abstractSmash.getName() + " with reflection", 0);
                    final Class<?> forName = Class.forName("com.ironsource.adapters." + abstractSmash.getNameForReflection().toLowerCase() + "." + abstractSmash.getNameForReflection() + "Adapter");
                    abstractAdapter = (AbstractAdapter)forName.getMethod("startAdapter", String.class).invoke(forName, abstractSmash.getName());
                }
                else {
                    this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, "using previously loaded " + abstractSmash.getName(), 0);
                    abstractAdapter = existingAdapter;
                }
                return abstractAdapter;
            }
            catch (Exception ex) {
                return null;
            }
        }
    }
    
    AbstractSmash getPremiumSmash() {
        return this.mPremiumSmash;
    }
    
    @Override
    public void onPause(final Activity activity) {
        this.mDidImplementOnPause.set(true);
        synchronized (this.mSmashArray) {
            if (this.mSmashArray != null) {
                final Iterator<AbstractSmash> iterator = this.mSmashArray.iterator();
                while (iterator.hasNext()) {
                    iterator.next().onPause(activity);
                }
            }
        }
    }
    // monitorexit(list)
    
    @Override
    public void onResume(final Activity mActivity) {
        this.mDidImplementOnResume.set(true);
        if (mActivity != null) {
            this.mActivity = mActivity;
        }
        synchronized (this.mSmashArray) {
            if (this.mSmashArray != null) {
                final Iterator<AbstractSmash> iterator = this.mSmashArray.iterator();
                while (iterator.hasNext()) {
                    iterator.next().onResume(mActivity);
                }
            }
        }
    }
    // monitorexit(list)
    
    @Override
    public void setAge(final int n) {
    }
    
    void setBackfillSmash(final AbstractSmash mBackfillSmash) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, mBackfillSmash.getInstanceName() + " is set as backfill", 0);
        this.mBackfillSmash = mBackfillSmash;
    }
    
    void setConsent(final boolean consent) {
        for (final AbstractSmash abstractSmash : this.mSmashArray) {
            if (abstractSmash != null) {
                abstractSmash.setConsent(consent);
            }
        }
    }
    
    void setCustomParams(final AbstractSmash abstractSmash) {
        try {
            final Integer age = IronSourceObject.getInstance().getAge();
            if (age != null) {
                abstractSmash.setAge(age);
            }
            final String gender = IronSourceObject.getInstance().getGender();
            if (!TextUtils.isEmpty((CharSequence)gender)) {
                abstractSmash.setGender(gender);
            }
            final String mediationSegment = IronSourceObject.getInstance().getMediationSegment();
            if (!TextUtils.isEmpty((CharSequence)mediationSegment)) {
                abstractSmash.setMediationSegment(mediationSegment);
            }
            final String pluginType = ConfigFile.getConfigFile().getPluginType();
            if (!TextUtils.isEmpty((CharSequence)pluginType)) {
                abstractSmash.setPluginData(pluginType, ConfigFile.getConfigFile().getPluginFrameworkVersion());
            }
            final Boolean consent = IronSourceObject.getInstance().getConsent();
            if (consent != null) {
                abstractSmash.setConsent(consent);
            }
        }
        catch (Exception ex) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, ":setCustomParams():" + ex.toString(), 3);
        }
    }
    
    @Override
    public void setGender(final String s) {
    }
    
    @Override
    public void setMediationSegment(final String s) {
    }
    
    void setPremiumSmash(final AbstractSmash mPremiumSmash) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, mPremiumSmash.getInstanceName() + " is set as premium", 0);
        this.mPremiumSmash = mPremiumSmash;
    }
    
    void setSmartLoadAmount(final int mSmartLoadAmount) {
        this.mSmartLoadAmount = mSmartLoadAmount;
    }
    
    abstract void shouldTrackNetworkState(final Context p0, final boolean p1);
    
    void verifyOnPauseOnResume() {
        if (!this.mDidImplementOnPause.get()) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.NATIVE, "IronSource.onPause() wasn't overridden in your activity lifecycle!", 3);
        }
        if (!this.mDidImplementOnResume.get()) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.NATIVE, "IronSource.onResume() wasn't overridden in your activity lifecycle!", 3);
        }
    }
}
