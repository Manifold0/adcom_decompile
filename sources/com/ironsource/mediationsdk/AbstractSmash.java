package com.ironsource.mediationsdk;

import android.app.Activity;
import android.text.TextUtils;
import com.ironsource.mediationsdk.logger.IronSourceLogger.IronSourceTag;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.model.ProviderSettings;
import com.ironsource.mediationsdk.sdk.BaseApi;
import java.util.HashSet;
import java.util.Timer;

public abstract class AbstractSmash implements BaseApi {
    public static final int MAX_ADS_PER_DAY_DEFAULT_VALUE = 99;
    final String MAX_ADS_PER_DAY_KEY = "maxAdsPerDay";
    final String MAX_ADS_PER_ITERATION_KEY = "maxAdsPerIteration";
    final String MAX_ADS_PER_SESSION_KEY = "maxAdsPerSession";
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

    public enum MEDIATION_STATE {
        NOT_INITIATED(0),
        INIT_FAILED(1),
        INITIATED(2),
        AVAILABLE(3),
        NOT_AVAILABLE(4),
        EXHAUSTED(5),
        CAPPED_PER_SESSION(6),
        INIT_PENDING(7),
        LOAD_PENDING(8),
        CAPPED_PER_DAY(9);
        
        private int mValue;

        private MEDIATION_STATE(int value) {
            this.mValue = value;
        }

        public int getValue() {
            return this.mValue;
        }
    }

    abstract void completeIteration();

    protected abstract String getAdUnitString();

    abstract void startInitTimer();

    abstract void startLoadTimer();

    AbstractSmash(ProviderSettings adapterConfigs) {
        this.mNameForReflection = adapterConfigs.getProviderTypeForReflection();
        this.mInstanceName = adapterConfigs.getProviderInstanceName();
        this.mIsMultipleInstances = adapterConfigs.isMultipleInstances();
        this.mAdapterConfigs = adapterConfigs;
        this.mSpId = adapterConfigs.getSubProviderId();
        this.mAdSourceNameForEvents = adapterConfigs.getAdSourceNameForEvents();
        this.mIterationShowCounter = 0;
        this.mSessionShowCounter = 0;
        this.mMediationState = MEDIATION_STATE.NOT_INITIATED;
        this.mLoggerManager = IronSourceLoggerManager.getLogger();
        this.mIsInForeground = true;
    }

    void setAdapterForSmash(AbstractAdapter adapter) {
        this.mAdapter = adapter;
    }

    boolean isExhausted() {
        return this.mIterationShowCounter >= this.mMaxAdsPerIteration;
    }

    boolean isCappedPerSession() {
        return this.mSessionShowCounter >= this.mMaxAdsPerSession;
    }

    boolean isCappedPerDay() {
        return this.mMediationState == MEDIATION_STATE.CAPPED_PER_DAY;
    }

    boolean isMediationAvailable() {
        return (isExhausted() || isCappedPerSession() || isCappedPerDay()) ? false : true;
    }

    void preShow() {
        this.mIterationShowCounter++;
        this.mSessionShowCounter++;
        if (isCappedPerSession()) {
            setMediationState(MEDIATION_STATE.CAPPED_PER_SESSION);
        } else if (isExhausted()) {
            setMediationState(MEDIATION_STATE.EXHAUSTED);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    void stopInitTimer() {
        /*
        r4 = this;
        r3 = 0;
        r1 = r4.mInitTimer;	 Catch:{ Exception -> 0x000d }
        if (r1 == 0) goto L_0x000a;
    L_0x0005:
        r1 = r4.mInitTimer;	 Catch:{ Exception -> 0x000d }
        r1.cancel();	 Catch:{ Exception -> 0x000d }
    L_0x000a:
        r4.mInitTimer = r3;
    L_0x000c:
        return;
    L_0x000d:
        r0 = move-exception;
        r1 = "stopInitTimer";
        r2 = r0.getLocalizedMessage();	 Catch:{ all -> 0x001a }
        r4.logException(r1, r2);	 Catch:{ all -> 0x001a }
        r4.mInitTimer = r3;
        goto L_0x000c;
    L_0x001a:
        r1 = move-exception;
        r4.mInitTimer = r3;
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ironsource.mediationsdk.AbstractSmash.stopInitTimer():void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    void stopLoadTimer() {
        /*
        r4 = this;
        r3 = 0;
        r1 = r4.mLoadTimer;	 Catch:{ Exception -> 0x000d }
        if (r1 == 0) goto L_0x000a;
    L_0x0005:
        r1 = r4.mLoadTimer;	 Catch:{ Exception -> 0x000d }
        r1.cancel();	 Catch:{ Exception -> 0x000d }
    L_0x000a:
        r4.mLoadTimer = r3;
    L_0x000c:
        return;
    L_0x000d:
        r0 = move-exception;
        r1 = "stopLoadTimer";
        r2 = r0.getLocalizedMessage();	 Catch:{ all -> 0x001a }
        r4.logException(r1, r2);	 Catch:{ all -> 0x001a }
        r4.mLoadTimer = r3;
        goto L_0x000c;
    L_0x001a:
        r1 = move-exception;
        r4.mLoadTimer = r3;
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ironsource.mediationsdk.AbstractSmash.stopLoadTimer():void");
    }

    void setPluginData(String pluginType, String pluginFrameworkVersion) {
        if (this.mAdapter != null) {
            this.mAdapter.setPluginData(pluginType, pluginFrameworkVersion);
        }
    }

    MEDIATION_STATE getMediationState() {
        return this.mMediationState;
    }

    String getNameForReflection() {
        return this.mNameForReflection;
    }

    String getInstanceName() {
        return this.mInstanceName;
    }

    public String getName() {
        if (this.mIsMultipleInstances) {
            return this.mNameForReflection;
        }
        return this.mInstanceName;
    }

    public String getSubProviderId() {
        return this.mSpId;
    }

    public String getAdSourceNameForEvents() {
        if (TextUtils.isEmpty(this.mAdSourceNameForEvents)) {
            return getName();
        }
        return this.mAdSourceNameForEvents;
    }

    int getMaxAdsPerSession() {
        return this.mMaxAdsPerSession;
    }

    int getMaxAdsPerIteration() {
        return this.mMaxAdsPerIteration;
    }

    public int getMaxAdsPerDay() {
        return this.mMaxAdsPerDay;
    }

    public AbstractAdapter getAdapter() {
        return this.mAdapter;
    }

    public int getProviderPriority() {
        return this.mProviderPriority;
    }

    synchronized void setMediationState(MEDIATION_STATE state) {
        if (this.mMediationState != state) {
            this.mMediationState = state;
            this.mLoggerManager.log(IronSourceTag.INTERNAL, "Smart Loading - " + getInstanceName() + " state changed to " + state.toString(), 0);
            if (this.mAdapter != null && (state == MEDIATION_STATE.CAPPED_PER_SESSION || state == MEDIATION_STATE.CAPPED_PER_DAY)) {
                this.mAdapter.setMediationState(state, getAdUnitString());
            }
        }
    }

    public void onResume(Activity activity) {
        if (this.mAdapter != null) {
            this.mAdapter.onResume(activity);
        }
        this.mIsInForeground = true;
    }

    public void onPause(Activity activity) {
        if (this.mAdapter != null) {
            this.mAdapter.onPause(activity);
        }
        this.mIsInForeground = false;
    }

    public void setAge(int age) {
        if (this.mAdapter != null) {
            this.mLoggerManager.log(IronSourceTag.ADAPTER_API, getName() + ":setAge(age:" + age + ")", 1);
            this.mAdapter.setAge(age);
        }
    }

    public void setGender(String gender) {
        if (this.mAdapter != null) {
            this.mLoggerManager.log(IronSourceTag.ADAPTER_API, getName() + ":setGender(gender:" + gender + ")", 1);
            this.mAdapter.setGender(gender);
        }
    }

    public void setMediationSegment(String segment) {
        if (this.mAdapter != null) {
            this.mLoggerManager.log(IronSourceTag.ADAPTER_API, getName() + ":setMediationSegment(segment:" + segment + ")", 1);
            this.mAdapter.setMediationSegment(segment);
        }
    }

    public HashSet<String> getAllSettingsForProvider(String fieldName) {
        return IronSourceObject.getInstance().getAllSettingsForProvider(this.mNameForReflection, fieldName);
    }

    void setProviderPriority(int providerPriority) {
        this.mProviderPriority = providerPriority;
    }

    void setConsent(boolean consent) {
        if (this.mAdapter != null) {
            this.mLoggerManager.log(IronSourceTag.ADAPTER_API, getName() + " | " + getAdUnitString() + "| setConsent(consent:" + consent + ")", 1);
            this.mAdapter.setConsent(consent);
        }
    }

    void logException(String methodName, String errorMessage) {
        this.mLoggerManager.log(IronSourceTag.INTERNAL, methodName + " exception: " + getInstanceName() + " | " + errorMessage, 3);
    }
}
