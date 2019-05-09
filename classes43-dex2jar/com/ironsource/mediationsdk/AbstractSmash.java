// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk;

import android.app.Activity;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import java.util.HashSet;
import android.text.TextUtils;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import java.util.Timer;
import com.ironsource.mediationsdk.model.ProviderSettings;
import com.ironsource.mediationsdk.sdk.BaseApi;

public abstract class AbstractSmash implements BaseApi
{
    public static final int MAX_ADS_PER_DAY_DEFAULT_VALUE = 99;
    final String MAX_ADS_PER_DAY_KEY;
    final String MAX_ADS_PER_ITERATION_KEY;
    final String MAX_ADS_PER_SESSION_KEY;
    String mAdSourceNameForEvents;
    AbstractAdapter mAdapter;
    ProviderSettings mAdapterConfigs;
    Timer mInitTimer;
    String mInstanceName;
    boolean mIsInForeground;
    boolean mIsMultipleInstances;
    int mIterationShowCounter;
    Timer mLoadTimer;
    IronSourceLoggerManager mLoggerManager;
    int mMaxAdsPerDay;
    int mMaxAdsPerIteration;
    int mMaxAdsPerSession;
    MEDIATION_STATE mMediationState;
    String mNameForReflection;
    int mProviderPriority;
    int mSessionShowCounter;
    String mSpId;
    
    AbstractSmash(final ProviderSettings mAdapterConfigs) {
        this.MAX_ADS_PER_SESSION_KEY = "maxAdsPerSession";
        this.MAX_ADS_PER_ITERATION_KEY = "maxAdsPerIteration";
        this.MAX_ADS_PER_DAY_KEY = "maxAdsPerDay";
        this.mNameForReflection = mAdapterConfigs.getProviderTypeForReflection();
        this.mInstanceName = mAdapterConfigs.getProviderInstanceName();
        this.mIsMultipleInstances = mAdapterConfigs.isMultipleInstances();
        this.mAdapterConfigs = mAdapterConfigs;
        this.mSpId = mAdapterConfigs.getSubProviderId();
        this.mAdSourceNameForEvents = mAdapterConfigs.getAdSourceNameForEvents();
        this.mIterationShowCounter = 0;
        this.mSessionShowCounter = 0;
        this.mMediationState = MEDIATION_STATE.NOT_INITIATED;
        this.mLoggerManager = IronSourceLoggerManager.getLogger();
        this.mIsInForeground = true;
    }
    
    abstract void completeIteration();
    
    public String getAdSourceNameForEvents() {
        if (!TextUtils.isEmpty((CharSequence)this.mAdSourceNameForEvents)) {
            return this.mAdSourceNameForEvents;
        }
        return this.getName();
    }
    
    protected abstract String getAdUnitString();
    
    public AbstractAdapter getAdapter() {
        return this.mAdapter;
    }
    
    public HashSet<String> getAllSettingsForProvider(final String s) {
        return IronSourceObject.getInstance().getAllSettingsForProvider(this.mNameForReflection, s);
    }
    
    String getInstanceName() {
        return this.mInstanceName;
    }
    
    public int getMaxAdsPerDay() {
        return this.mMaxAdsPerDay;
    }
    
    int getMaxAdsPerIteration() {
        return this.mMaxAdsPerIteration;
    }
    
    int getMaxAdsPerSession() {
        return this.mMaxAdsPerSession;
    }
    
    MEDIATION_STATE getMediationState() {
        return this.mMediationState;
    }
    
    public String getName() {
        if (this.mIsMultipleInstances) {
            return this.mNameForReflection;
        }
        return this.mInstanceName;
    }
    
    String getNameForReflection() {
        return this.mNameForReflection;
    }
    
    public int getProviderPriority() {
        return this.mProviderPriority;
    }
    
    public String getSubProviderId() {
        return this.mSpId;
    }
    
    boolean isCappedPerDay() {
        return this.mMediationState == MEDIATION_STATE.CAPPED_PER_DAY;
    }
    
    boolean isCappedPerSession() {
        return this.mSessionShowCounter >= this.mMaxAdsPerSession;
    }
    
    boolean isExhausted() {
        return this.mIterationShowCounter >= this.mMaxAdsPerIteration;
    }
    
    boolean isMediationAvailable() {
        return !this.isExhausted() && !this.isCappedPerSession() && !this.isCappedPerDay();
    }
    
    void logException(final String s, final String s2) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, s + " exception: " + this.getInstanceName() + " | " + s2, 3);
    }
    
    @Override
    public void onPause(final Activity activity) {
        if (this.mAdapter != null) {
            this.mAdapter.onPause(activity);
        }
        this.mIsInForeground = false;
    }
    
    @Override
    public void onResume(final Activity activity) {
        if (this.mAdapter != null) {
            this.mAdapter.onResume(activity);
        }
        this.mIsInForeground = true;
    }
    
    void preShow() {
        ++this.mIterationShowCounter;
        ++this.mSessionShowCounter;
        if (this.isCappedPerSession()) {
            this.setMediationState(MEDIATION_STATE.CAPPED_PER_SESSION);
        }
        else if (this.isExhausted()) {
            this.setMediationState(MEDIATION_STATE.EXHAUSTED);
        }
    }
    
    void setAdapterForSmash(final AbstractAdapter mAdapter) {
        this.mAdapter = mAdapter;
    }
    
    @Override
    public void setAge(final int age) {
        if (this.mAdapter != null) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_API, this.getName() + ":setAge(age:" + age + ")", 1);
            this.mAdapter.setAge(age);
        }
    }
    
    void setConsent(final boolean consent) {
        if (this.mAdapter != null) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_API, this.getName() + " | " + this.getAdUnitString() + "| setConsent(consent:" + consent + ")", 1);
            this.mAdapter.setConsent(consent);
        }
    }
    
    @Override
    public void setGender(final String gender) {
        if (this.mAdapter != null) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_API, this.getName() + ":setGender(gender:" + gender + ")", 1);
            this.mAdapter.setGender(gender);
        }
    }
    
    @Override
    public void setMediationSegment(final String mediationSegment) {
        if (this.mAdapter != null) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_API, this.getName() + ":setMediationSegment(segment:" + mediationSegment + ")", 1);
            this.mAdapter.setMediationSegment(mediationSegment);
        }
    }
    
    void setMediationState(final MEDIATION_STATE mMediationState) {
        synchronized (this) {
            if (this.mMediationState != mMediationState) {
                this.mMediationState = mMediationState;
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, "Smart Loading - " + this.getInstanceName() + " state changed to " + mMediationState.toString(), 0);
                if (this.mAdapter != null && (mMediationState == MEDIATION_STATE.CAPPED_PER_SESSION || mMediationState == MEDIATION_STATE.CAPPED_PER_DAY)) {
                    this.mAdapter.setMediationState(mMediationState, this.getAdUnitString());
                }
            }
        }
    }
    
    void setPluginData(final String s, final String s2) {
        if (this.mAdapter != null) {
            this.mAdapter.setPluginData(s, s2);
        }
    }
    
    void setProviderPriority(final int mProviderPriority) {
        this.mProviderPriority = mProviderPriority;
    }
    
    abstract void startInitTimer();
    
    abstract void startLoadTimer();
    
    void stopInitTimer() {
        try {
            if (this.mInitTimer != null) {
                this.mInitTimer.cancel();
            }
        }
        catch (Exception ex) {
            this.logException("stopInitTimer", ex.getLocalizedMessage());
        }
        finally {
            this.mInitTimer = null;
        }
    }
    
    void stopLoadTimer() {
        try {
            if (this.mLoadTimer != null) {
                this.mLoadTimer.cancel();
            }
        }
        catch (Exception ex) {
            this.logException("stopLoadTimer", ex.getLocalizedMessage());
        }
        finally {
            this.mLoadTimer = null;
        }
    }
    
    public enum MEDIATION_STATE
    {
        AVAILABLE(3), 
        CAPPED_PER_DAY(9), 
        CAPPED_PER_SESSION(6), 
        EXHAUSTED(5), 
        INITIATED(2), 
        INIT_FAILED(1), 
        INIT_PENDING(7), 
        LOAD_PENDING(8), 
        NOT_AVAILABLE(4), 
        NOT_INITIATED(0);
        
        private int mValue;
        
        private MEDIATION_STATE(final int mValue) {
            this.mValue = mValue;
        }
        
        public int getValue() {
            return this.mValue;
        }
    }
}
