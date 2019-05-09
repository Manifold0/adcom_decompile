package com.ironsource.mediationsdk;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import com.ironsource.eventsmodule.EventData;
import com.ironsource.mediationsdk.AbstractSmash.MEDIATION_STATE;
import com.ironsource.mediationsdk.IronSource.AD_UNIT;
import com.ironsource.mediationsdk.events.InterstitialEventsManager;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.logger.IronSourceLogger.IronSourceTag;
import com.ironsource.mediationsdk.model.InterstitialPlacement;
import com.ironsource.mediationsdk.sdk.ISDemandOnlyInterstitialListener;
import com.ironsource.mediationsdk.sdk.InterstitialApi;
import com.ironsource.mediationsdk.sdk.InterstitialListener;
import com.ironsource.mediationsdk.sdk.InterstitialManagerListener;
import com.ironsource.mediationsdk.sdk.ListenersWrapper;
import com.ironsource.mediationsdk.sdk.RewardedInterstitialApi;
import com.ironsource.mediationsdk.sdk.RewardedInterstitialListener;
import com.ironsource.mediationsdk.sdk.RewardedInterstitialManagerListener;
import com.ironsource.mediationsdk.utils.CappingManager;
import com.ironsource.mediationsdk.utils.DailyCappingListener;
import com.ironsource.mediationsdk.utils.DailyCappingManager;
import com.ironsource.mediationsdk.utils.ErrorBuilder;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import com.ironsource.mediationsdk.utils.IronSourceUtils;
import com.vungle.warren.ui.VungleActivity;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import org.json.JSONObject;

class InterstitialManager extends AbstractAdUnitManager implements InterstitialApi, InterstitialManagerListener, OnMediationInitializationListener, RewardedInterstitialManagerListener, RewardedInterstitialApi, DailyCappingListener {
    private final String TAG;
    private CallbackThrotteler mCallbackThrotteler;
    private InterstitialPlacement mCurrentPlacement;
    private boolean mDidCallLoadInterstitial;
    private boolean mDidFinishToInitInterstitial;
    private ISDemandOnlyInterstitialListener mISDemandOnlyInterstitialListener;
    private Map<String, InterstitialSmash> mInstanceIdToSmashMap;
    private CopyOnWriteArraySet<String> mInstancesToLoad;
    private ListenersWrapper mInterstitialListenersWrapper;
    private boolean mIsLoadInterstitialInProgress;
    private RewardedInterstitialListener mRewardedInterstitialListenerWrapper;
    private boolean mShouldSendAdReadyEvent;

    InterstitialManager() {
        this.TAG = getClass().getName();
        this.mInstancesToLoad = new CopyOnWriteArraySet();
        this.mInstanceIdToSmashMap = new ConcurrentHashMap();
        this.mCallbackThrotteler = new CallbackThrotteler();
        this.mShouldSendAdReadyEvent = false;
        this.mIsLoadInterstitialInProgress = false;
        this.mDidCallLoadInterstitial = false;
        this.mDailyCappingManager = new DailyCappingManager("interstitial", this);
    }

    public void setInterstitialListener(InterstitialListener listener) {
        this.mInterstitialListenersWrapper = (ListenersWrapper) listener;
        this.mCallbackThrotteler.setInterstitialListener(listener);
    }

    public void setRewardedInterstitialListener(RewardedInterstitialListener listener) {
        this.mRewardedInterstitialListenerWrapper = listener;
    }

    public synchronized void initInterstitial(Activity activity, String appKey, String userId) {
        this.mLoggerManager.log(IronSourceTag.NATIVE, this.TAG + ":initInterstitial(appKey: " + appKey + ", userId: " + userId + ")", 1);
        this.mAppKey = appKey;
        this.mUserId = userId;
        this.mActivity = activity;
        AbstractSmash smash;
        if (this.mIsInISDemandOnlyMode) {
            this.mSmartLoadAmount = this.mSmashArray.size();
            Iterator it = new CopyOnWriteArrayList(this.mSmashArray).iterator();
            while (it.hasNext()) {
                smash = (AbstractSmash) it.next();
                if (!smash.getNameForReflection().equals(IronSourceConstants.IRONSOURCE_CONFIG_NAME)) {
                    this.mSmashArray.remove(smash);
                    this.mLoggerManager.log(IronSourceTag.INTERNAL, smash.getAdSourceNameForEvents() + " has been removed from the IS waterfall due to demand only mode", 1);
                } else if (startAdapter((InterstitialSmash) smash) == null) {
                    smash.setMediationState(MEDIATION_STATE.INIT_FAILED);
                } else {
                    this.mInstanceIdToSmashMap.put(smash.getSubProviderId(), (InterstitialSmash) smash);
                }
            }
        } else {
            this.mDailyCappingManager.setContext(this.mActivity);
            int dailyCappedCount = 0;
            Iterator it2 = this.mSmashArray.iterator();
            while (it2.hasNext()) {
                smash = (AbstractSmash) it2.next();
                if (this.mDailyCappingManager.shouldSendCapReleasedEvent(smash)) {
                    Object[][] objArr = new Object[1][];
                    objArr[0] = new Object[]{"status", "false"};
                    logProviderEvent(250, smash, objArr);
                }
                if (this.mDailyCappingManager.isCapped(smash)) {
                    smash.setMediationState(MEDIATION_STATE.CAPPED_PER_DAY);
                    dailyCappedCount++;
                }
            }
            if (dailyCappedCount == this.mSmashArray.size()) {
                this.mDidFinishToInitInterstitial = true;
            }
            for (int i = 0; i < this.mSmartLoadAmount && startNextAdapter() != null; i++) {
            }
        }
    }

    public synchronized void loadInterstitial() {
        IronSourceError error;
        Object[][] objArr;
        try {
            if (this.mIsLoadInterstitialInProgress || this.mCallbackThrotteler.hasPendingInvocation()) {
                this.mLoggerManager.log(IronSourceTag.API, "Load Interstitial is already in progress", 1);
            } else {
                EInitStatus initStatus = MediationInitializer.getInstance().getCurrentInitStatus();
                if (initStatus == EInitStatus.NOT_INIT) {
                    this.mLoggerManager.log(IronSourceTag.API, "init() must be called before loadInterstitial()", 3);
                } else if (initStatus == EInitStatus.INIT_IN_PROGRESS) {
                    if (MediationInitializer.getInstance().isInProgressMoreThan15Secs()) {
                        this.mLoggerManager.log(IronSourceTag.API, "init() had failed", 3);
                        this.mCallbackThrotteler.onInterstitialAdLoadFailed(ErrorBuilder.buildInitFailedError("init() had failed", "Interstitial"));
                    } else {
                        logMediationEvent(22, (Object[][]) null);
                        this.mDidCallLoadInterstitial = true;
                        this.mShouldSendAdReadyEvent = true;
                    }
                } else if (initStatus == EInitStatus.INIT_FAILED) {
                    this.mLoggerManager.log(IronSourceTag.API, "init() had failed", 3);
                    this.mCallbackThrotteler.onInterstitialAdLoadFailed(ErrorBuilder.buildInitFailedError("init() had failed", "Interstitial"));
                } else if (this.mSmashArray.size() == 0) {
                    this.mLoggerManager.log(IronSourceTag.API, "the server response does not contain interstitial data", 3);
                    this.mCallbackThrotteler.onInterstitialAdLoadFailed(ErrorBuilder.buildInitFailedError("the server response does not contain interstitial data", "Interstitial"));
                } else {
                    logMediationEvent(22, (Object[][]) null);
                    this.mShouldSendAdReadyEvent = true;
                    changeStateToInitiated();
                    if (smashesCount(MEDIATION_STATE.INITIATED) != 0) {
                        this.mDidCallLoadInterstitial = true;
                        this.mIsLoadInterstitialInProgress = true;
                        int loading = 0;
                        Iterator it = this.mSmashArray.iterator();
                        while (it.hasNext()) {
                            AbstractSmash smash = (AbstractSmash) it.next();
                            if (smash.getMediationState() == MEDIATION_STATE.INITIATED) {
                                smash.setMediationState(MEDIATION_STATE.LOAD_PENDING);
                                loadAdapterAndSendEvent((InterstitialSmash) smash);
                                loading++;
                                if (loading >= this.mSmartLoadAmount) {
                                    break;
                                }
                            }
                        }
                    } else if (this.mDidFinishToInitInterstitial) {
                        error = ErrorBuilder.buildGenericError("no ads to load");
                        this.mLoggerManager.log(IronSourceTag.API, error.getErrorMessage(), 1);
                        this.mCallbackThrotteler.onInterstitialAdLoadFailed(error);
                        objArr = new Object[1][];
                        objArr[0] = new Object[]{IronSourceConstants.ERROR_CODE_KEY, Integer.valueOf(error.getErrorCode())};
                        logMediationEvent(IronSourceConstants.INTERSTITIAL_AD_LOAD_FAILED, objArr);
                        this.mShouldSendAdReadyEvent = false;
                    } else {
                        this.mDidCallLoadInterstitial = true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            error = ErrorBuilder.buildLoadFailedError("loadInterstitial exception " + e.getMessage());
            this.mLoggerManager.log(IronSourceTag.API, error.getErrorMessage(), 3);
            this.mCallbackThrotteler.onInterstitialAdLoadFailed(error);
            if (this.mShouldSendAdReadyEvent) {
                this.mShouldSendAdReadyEvent = false;
                objArr = new Object[1][];
                objArr[0] = new Object[]{IronSourceConstants.ERROR_CODE_KEY, Integer.valueOf(error.getErrorCode())};
                logMediationEvent(IronSourceConstants.INTERSTITIAL_AD_LOAD_FAILED, objArr);
            }
        }
    }

    public void showInterstitial(String placementName) {
        if (this.mShouldTrackNetworkState && this.mActivity != null && !IronSourceUtils.isNetworkConnected(this.mActivity)) {
            this.mInterstitialListenersWrapper.onInterstitialAdShowFailed(ErrorBuilder.buildNoInternetConnectionShowFailError("Interstitial"));
        } else if (this.mDidCallLoadInterstitial) {
            for (int i = 0; i < this.mSmashArray.size(); i++) {
                AbstractSmash smash = (AbstractSmash) this.mSmashArray.get(i);
                if (smash.getMediationState() == MEDIATION_STATE.AVAILABLE) {
                    CappingManager.incrementShowCounter(this.mActivity, this.mCurrentPlacement);
                    Object[][] objArr = new Object[1][];
                    objArr[0] = new Object[]{VungleActivity.PLACEMENT_EXTRA, placementName};
                    logProviderEvent(23, smash, objArr);
                    sendShowChanceEvents(smash, i, placementName);
                    ((InterstitialSmash) smash).showInterstitial();
                    this.mDailyCappingManager.increaseShowCounter(smash);
                    if (this.mDailyCappingManager.isCapped(smash)) {
                        smash.setMediationState(MEDIATION_STATE.CAPPED_PER_DAY);
                        objArr = new Object[1][];
                        objArr[0] = new Object[]{"status", "true"};
                        logProviderEvent(250, smash, objArr);
                    }
                    this.mDidCallLoadInterstitial = false;
                    if (!smash.isMediationAvailable()) {
                        startNextAdapter();
                        return;
                    }
                    return;
                }
            }
            this.mInterstitialListenersWrapper.onInterstitialAdShowFailed(ErrorBuilder.buildShowFailedError("Interstitial", "showInterstitial failed - No adapters ready to show"));
        } else {
            this.mInterstitialListenersWrapper.onInterstitialAdShowFailed(ErrorBuilder.buildShowFailedError("Interstitial", "showInterstitial failed - You need to load interstitial before showing it"));
        }
    }

    public synchronized boolean isInterstitialReady() {
        boolean z = false;
        synchronized (this) {
            if (!this.mShouldTrackNetworkState || this.mActivity == null || IronSourceUtils.isNetworkConnected(this.mActivity)) {
                Iterator it = this.mSmashArray.iterator();
                while (it.hasNext()) {
                    AbstractSmash smash = (AbstractSmash) it.next();
                    if (smash.getMediationState() == MEDIATION_STATE.AVAILABLE && ((InterstitialSmash) smash).isInterstitialReady()) {
                        z = true;
                        break;
                    }
                }
            }
        }
        return z;
    }

    public void setISDemandOnlyInterstitialListener(ISDemandOnlyInterstitialListener listener) {
        this.mISDemandOnlyInterstitialListener = listener;
        this.mCallbackThrotteler.setISDemandOnlyInterstitialListener(listener);
    }

    public synchronized void loadInterstitial(String instanceId) {
        IronSourceError error;
        try {
            if (this.mCallbackThrotteler.hasPendingInvocation(instanceId)) {
                this.mLoggerManager.log(IronSourceTag.API, "Load Interstitial for " + instanceId + " is already in progress", 1);
            } else {
                EInitStatus initStatus = MediationInitializer.getInstance().getCurrentInitStatus();
                if (initStatus == EInitStatus.NOT_INIT) {
                    this.mLoggerManager.log(IronSourceTag.API, "init() must be called before loadInterstitial()", 3);
                } else if (initStatus == EInitStatus.INIT_IN_PROGRESS) {
                    if (MediationInitializer.getInstance().isInProgressMoreThan15Secs()) {
                        this.mLoggerManager.log(IronSourceTag.API, "init() had failed", 3);
                        this.mCallbackThrotteler.onInterstitialAdLoadFailed(instanceId, ErrorBuilder.buildInitFailedError("init() had failed", "Interstitial"));
                    } else {
                        this.mInstancesToLoad.add(instanceId);
                    }
                } else if (initStatus == EInitStatus.INIT_FAILED) {
                    this.mLoggerManager.log(IronSourceTag.API, "init() had failed", 3);
                    this.mCallbackThrotteler.onInterstitialAdLoadFailed(instanceId, ErrorBuilder.buildInitFailedError("init() had failed", "Interstitial"));
                } else if (this.mInstanceIdToSmashMap.containsKey(instanceId)) {
                    InterstitialSmash smash = (InterstitialSmash) this.mInstanceIdToSmashMap.get(instanceId);
                    if (smash.getMediationState() == MEDIATION_STATE.INIT_PENDING) {
                        this.mInstancesToLoad.add(instanceId);
                    } else {
                        smash.setMediationState(MEDIATION_STATE.LOAD_PENDING);
                        loadAdapterAndSendEvent(smash);
                    }
                } else {
                    this.mCallbackThrotteler.onInterstitialAdLoadFailed(instanceId, ErrorBuilder.buildNonExistentInstanceError("Interstitial"));
                    logMediationEvent(22, (Object[][]) null);
                    Object[][] objArr = new Object[1][];
                    objArr[0] = new Object[]{IronSourceConstants.ERROR_CODE_KEY, Integer.valueOf(error.getErrorCode())};
                    logMediationEvent(IronSourceConstants.INTERSTITIAL_AD_LOAD_FAILED, objArr);
                }
            }
        } catch (Exception e) {
            error = ErrorBuilder.buildLoadFailedError("loadInterstitial exception");
            this.mLoggerManager.log(IronSourceTag.API, error.getErrorMessage(), 3);
            this.mCallbackThrotteler.onInterstitialAdLoadFailed(error);
        }
    }

    public void showInterstitial(String instanceId, String placementName) {
        if (!this.mShouldTrackNetworkState || this.mActivity == null || IronSourceUtils.isNetworkConnected(this.mActivity)) {
            boolean existingInstanceId = false;
            for (int i = 0; i < this.mSmashArray.size(); i++) {
                AbstractSmash smash = (AbstractSmash) this.mSmashArray.get(i);
                if (smash.getSubProviderId().equals(instanceId)) {
                    existingInstanceId = true;
                    if (smash.getMediationState() == MEDIATION_STATE.AVAILABLE) {
                        CappingManager.incrementShowCounter(this.mActivity, this.mCurrentPlacement);
                        Object[][] objArr = new Object[1][];
                        objArr[0] = new Object[]{VungleActivity.PLACEMENT_EXTRA, placementName};
                        logProviderEvent(23, smash, objArr);
                        sendShowChanceEvents(smash, i, placementName);
                        ((InterstitialSmash) smash).showInterstitial();
                        changeStateToInitiatedForInstanceId(instanceId);
                        return;
                    }
                }
            }
            if (existingInstanceId) {
                this.mISDemandOnlyInterstitialListener.onInterstitialAdShowFailed(instanceId, ErrorBuilder.buildShowFailedError("Interstitial", "no ads to show"));
                return;
            } else {
                this.mISDemandOnlyInterstitialListener.onInterstitialAdShowFailed(instanceId, ErrorBuilder.buildNonExistentInstanceError("no ads to show"));
                return;
            }
        }
        this.mISDemandOnlyInterstitialListener.onInterstitialAdShowFailed(instanceId, ErrorBuilder.buildNoInternetConnectionShowFailError("Interstitial"));
    }

    public synchronized boolean isInterstitialReady(String instanceId) {
        boolean z = false;
        synchronized (this) {
            if (!this.mShouldTrackNetworkState || this.mActivity == null || IronSourceUtils.isNetworkConnected(this.mActivity)) {
                Iterator it = this.mSmashArray.iterator();
                while (it.hasNext()) {
                    AbstractSmash smash = (AbstractSmash) it.next();
                    if (smash.getSubProviderId().equals(instanceId)) {
                        if (smash.getMediationState() == MEDIATION_STATE.AVAILABLE && ((InterstitialSmash) smash).isInterstitialReady()) {
                            z = true;
                        }
                    }
                }
            }
        }
        return z;
    }

    public synchronized void onInterstitialInitSuccess(InterstitialSmash smash) {
        this.mLoggerManager.log(IronSourceTag.ADAPTER_CALLBACK, smash.getInstanceName() + " :onInterstitialInitSuccess()", 1);
        this.mDidFinishToInitInterstitial = true;
        if (this.mIsInISDemandOnlyMode) {
            String instanceId = smash.getSubProviderId();
            if (this.mInstancesToLoad.contains(instanceId)) {
                this.mInstancesToLoad.remove(instanceId);
                loadInterstitial(instanceId);
            }
        } else if (this.mDidCallLoadInterstitial) {
            if (smashesCount(MEDIATION_STATE.AVAILABLE, MEDIATION_STATE.LOAD_PENDING) < this.mSmartLoadAmount) {
                smash.setMediationState(MEDIATION_STATE.LOAD_PENDING);
                loadAdapterAndSendEvent(smash);
            }
        }
    }

    public synchronized void onInterstitialInitFailed(IronSourceError error, InterstitialSmash smash) {
        try {
            this.mLoggerManager.log(IronSourceTag.ADAPTER_CALLBACK, smash.getInstanceName() + ":onInterstitialInitFailed(" + error + ")", 1);
            Object[][] objArr;
            if (this.mIsInISDemandOnlyMode) {
                String instanceId = smash.getSubProviderId();
                if (this.mInstancesToLoad.contains(instanceId)) {
                    this.mInstancesToLoad.remove(instanceId);
                    this.mCallbackThrotteler.onInterstitialAdLoadFailed(instanceId, ErrorBuilder.buildGenericError("no ads to show"));
                    objArr = new Object[1][];
                    objArr[0] = new Object[]{IronSourceConstants.ERROR_CODE_KEY, Integer.valueOf(IronSourceError.ERROR_CODE_GENERIC)};
                    logMediationEvent(IronSourceConstants.INTERSTITIAL_AD_LOAD_FAILED, objArr);
                    objArr = new Object[1][];
                    objArr[0] = new Object[]{IronSourceConstants.ERROR_CODE_KEY, Integer.valueOf(IronSourceError.ERROR_CODE_GENERIC)};
                    logProviderEvent(IronSourceConstants.INTERSTITIAL_AD_LOAD_FAILED, smash, objArr);
                }
            } else {
                if (smashesCount(MEDIATION_STATE.INIT_FAILED) >= this.mSmashArray.size()) {
                    this.mLoggerManager.log(IronSourceTag.NATIVE, "Smart Loading - initialization failed - no adapters are initiated and no more left to init, error: " + error.getErrorMessage(), 2);
                    if (this.mDidCallLoadInterstitial) {
                        this.mCallbackThrotteler.onInterstitialAdLoadFailed(ErrorBuilder.buildGenericError("no ads to show"));
                        objArr = new Object[1][];
                        objArr[0] = new Object[]{IronSourceConstants.ERROR_CODE_KEY, Integer.valueOf(IronSourceError.ERROR_CODE_GENERIC)};
                        logMediationEvent(IronSourceConstants.INTERSTITIAL_AD_LOAD_FAILED, objArr);
                        this.mShouldSendAdReadyEvent = false;
                    }
                    this.mDidFinishToInitInterstitial = true;
                } else {
                    if (startNextAdapter() == null && this.mDidCallLoadInterstitial) {
                        if (smashesCount(MEDIATION_STATE.INIT_FAILED, MEDIATION_STATE.NOT_AVAILABLE, MEDIATION_STATE.CAPPED_PER_SESSION, MEDIATION_STATE.CAPPED_PER_DAY, MEDIATION_STATE.EXHAUSTED) >= this.mSmashArray.size()) {
                            this.mCallbackThrotteler.onInterstitialAdLoadFailed(ErrorBuilder.buildGenericError("no ads to show"));
                            objArr = new Object[1][];
                            objArr[0] = new Object[]{IronSourceConstants.ERROR_CODE_KEY, Integer.valueOf(IronSourceError.ERROR_CODE_GENERIC)};
                            logMediationEvent(IronSourceConstants.INTERSTITIAL_AD_LOAD_FAILED, objArr);
                            this.mShouldSendAdReadyEvent = false;
                        }
                    }
                    completeIterationRound();
                }
            }
        } catch (Exception e) {
            this.mLoggerManager.logException(IronSourceTag.ADAPTER_CALLBACK, "onInterstitialInitFailed(error:" + error + ", " + "provider:" + smash.getName() + ")", e);
        }
    }

    public synchronized void onInterstitialAdReady(InterstitialSmash smash) {
        this.mLoggerManager.log(IronSourceTag.ADAPTER_CALLBACK, smash.getInstanceName() + ":onInterstitialAdReady()", 1);
        Object[][] objArr = new Object[1][];
        objArr[0] = new Object[]{"status", "true"};
        logProviderEvent(27, smash, objArr);
        if (this.mIsInISDemandOnlyMode) {
            smash.setMediationState(MEDIATION_STATE.AVAILABLE);
            this.mISDemandOnlyInterstitialListener.onInterstitialAdReady(smash.getSubProviderId());
            objArr = new Object[1][];
            objArr[0] = new Object[]{"status", "true"};
            logMediationEvent(27, objArr);
        } else {
            smash.setMediationState(MEDIATION_STATE.AVAILABLE);
            this.mIsLoadInterstitialInProgress = false;
            if (this.mShouldSendAdReadyEvent) {
                this.mShouldSendAdReadyEvent = false;
                this.mInterstitialListenersWrapper.onInterstitialAdReady();
                objArr = new Object[1][];
                objArr[0] = new Object[]{"status", "true"};
                logMediationEvent(27, objArr);
            }
        }
    }

    public synchronized void onInterstitialAdLoadFailed(IronSourceError error, InterstitialSmash smash) {
        this.mLoggerManager.log(IronSourceTag.ADAPTER_CALLBACK, smash.getInstanceName() + ":onInterstitialAdLoadFailed(" + error + ")", 1);
        Object[][] objArr = new Object[1][];
        objArr[0] = new Object[]{IronSourceConstants.ERROR_CODE_KEY, Integer.valueOf(error.getErrorCode())};
        logProviderEvent(IronSourceConstants.INTERSTITIAL_AD_LOAD_FAILED, smash, objArr);
        if (this.mIsInISDemandOnlyMode) {
            this.mCallbackThrotteler.onInterstitialAdLoadFailed(smash.getSubProviderId(), error);
            objArr = new Object[1][];
            objArr[0] = new Object[]{IronSourceConstants.ERROR_CODE_KEY, Integer.valueOf(error.getErrorCode())};
            logMediationEvent(IronSourceConstants.INTERSTITIAL_AD_LOAD_FAILED, objArr);
        } else {
            smash.setMediationState(MEDIATION_STATE.NOT_AVAILABLE);
            int availableOrLoadPending = smashesCount(MEDIATION_STATE.AVAILABLE, MEDIATION_STATE.LOAD_PENDING);
            if (availableOrLoadPending < this.mSmartLoadAmount) {
                Iterator it = this.mSmashArray.iterator();
                while (it.hasNext()) {
                    AbstractSmash asmash = (AbstractSmash) it.next();
                    if (asmash.getMediationState() == MEDIATION_STATE.INITIATED) {
                        asmash.setMediationState(MEDIATION_STATE.LOAD_PENDING);
                        loadAdapterAndSendEvent((InterstitialSmash) asmash);
                        break;
                    }
                }
                if (startNextAdapter() == null && this.mDidCallLoadInterstitial) {
                    if (smashesCount(MEDIATION_STATE.INIT_PENDING) + availableOrLoadPending == 0) {
                        completeIterationRound();
                        this.mIsLoadInterstitialInProgress = false;
                        this.mCallbackThrotteler.onInterstitialAdLoadFailed(error);
                        objArr = new Object[1][];
                        objArr[0] = new Object[]{IronSourceConstants.ERROR_CODE_KEY, Integer.valueOf(error.getErrorCode())};
                        logMediationEvent(IronSourceConstants.INTERSTITIAL_AD_LOAD_FAILED, objArr);
                    }
                }
            }
        }
    }

    public void onInterstitialAdOpened(InterstitialSmash smash) {
        this.mLoggerManager.log(IronSourceTag.ADAPTER_CALLBACK, smash.getInstanceName() + ":onInterstitialAdOpened()", 1);
        logProviderEvent(25, smash, (Object[][]) null);
        if (this.mIsInISDemandOnlyMode) {
            this.mISDemandOnlyInterstitialListener.onInterstitialAdOpened(smash.getSubProviderId());
        } else {
            this.mInterstitialListenersWrapper.onInterstitialAdOpened();
        }
    }

    public void onInterstitialAdClosed(InterstitialSmash smash) {
        this.mLoggerManager.log(IronSourceTag.ADAPTER_CALLBACK, smash.getInstanceName() + ":onInterstitialAdClosed()", 1);
        verifyOnPauseOnResume();
        logProviderEvent(26, smash, (Object[][]) null);
        if (this.mIsInISDemandOnlyMode) {
            this.mISDemandOnlyInterstitialListener.onInterstitialAdClosed(smash.getSubProviderId());
        } else {
            this.mInterstitialListenersWrapper.onInterstitialAdClosed();
        }
    }

    public void onInterstitialAdShowSucceeded(InterstitialSmash smash) {
        this.mLoggerManager.log(IronSourceTag.ADAPTER_CALLBACK, smash.getInstanceName() + ":onInterstitialAdShowSucceeded()", 1);
        if (this.mIsInISDemandOnlyMode) {
            this.mISDemandOnlyInterstitialListener.onInterstitialAdShowSucceeded(smash.getSubProviderId());
            return;
        }
        boolean hasAvailable = false;
        Iterator it = this.mSmashArray.iterator();
        while (it.hasNext()) {
            AbstractSmash asmash = (AbstractSmash) it.next();
            if (asmash.getMediationState() == MEDIATION_STATE.AVAILABLE) {
                hasAvailable = true;
                completeAdapterShow(asmash);
            }
        }
        if (!hasAvailable && (smash.getMediationState() == MEDIATION_STATE.CAPPED_PER_SESSION || smash.getMediationState() == MEDIATION_STATE.EXHAUSTED || smash.getMediationState() == MEDIATION_STATE.CAPPED_PER_DAY)) {
            completeIterationRound();
        }
        changeStateToInitiated();
        this.mInterstitialListenersWrapper.onInterstitialAdShowSucceeded();
    }

    public void onInterstitialAdShowFailed(IronSourceError error, InterstitialSmash smash) {
        this.mLoggerManager.log(IronSourceTag.ADAPTER_CALLBACK, smash.getInstanceName() + ":onInterstitialAdShowFailed(" + error + ")", 1);
        if (this.mIsInISDemandOnlyMode) {
            this.mISDemandOnlyInterstitialListener.onInterstitialAdShowFailed(smash.getSubProviderId(), error);
            return;
        }
        completeAdapterShow(smash);
        Iterator it = this.mSmashArray.iterator();
        while (it.hasNext()) {
            if (((AbstractSmash) it.next()).getMediationState() == MEDIATION_STATE.AVAILABLE) {
                this.mDidCallLoadInterstitial = true;
                showInterstitial(this.mCurrentPlacement.getPlacementName());
                return;
            }
        }
        this.mInterstitialListenersWrapper.onInterstitialAdShowFailed(error);
    }

    public void onInterstitialAdClicked(InterstitialSmash smash) {
        this.mLoggerManager.log(IronSourceTag.ADAPTER_CALLBACK, smash.getInstanceName() + ":onInterstitialAdClicked()", 1);
        logProviderEvent(28, smash, (Object[][]) null);
        if (this.mIsInISDemandOnlyMode) {
            this.mISDemandOnlyInterstitialListener.onInterstitialAdClicked(smash.getSubProviderId());
        } else {
            this.mInterstitialListenersWrapper.onInterstitialAdClicked();
        }
    }

    public void onInterstitialAdVisible(InterstitialSmash smash) {
        this.mLoggerManager.log(IronSourceTag.ADAPTER_CALLBACK, smash.getInstanceName() + ":onInterstitialAdVisible()", 1);
        Object[][] objArr = new Object[1][];
        objArr[0] = new Object[]{VungleActivity.PLACEMENT_EXTRA, this.mCurrentPlacement.getPlacementName()};
        logProviderEvent(31, smash, objArr);
    }

    public void onInterstitialAdRewarded(InterstitialSmash smash) {
        logProviderEvent(IronSourceConstants.INTERSTITIAL_AD_REWARDED, smash, (Object[][]) null);
        if (this.mRewardedInterstitialListenerWrapper != null) {
            this.mRewardedInterstitialListenerWrapper.onInterstitialAdRewarded();
        }
    }

    void shouldTrackNetworkState(Context context, boolean track) {
        this.mLoggerManager.log(IronSourceTag.INTERNAL, this.TAG + " Should Track Network State: " + track, 0);
        this.mShouldTrackNetworkState = track;
    }

    public void onInitSuccess(List<AD_UNIT> list, boolean revived) {
    }

    public void onInitFailed(String reason) {
        if (this.mIsInISDemandOnlyMode) {
            Iterator it = this.mInstancesToLoad.iterator();
            while (it.hasNext()) {
                this.mCallbackThrotteler.onInterstitialAdLoadFailed((String) it.next(), ErrorBuilder.buildInitFailedError("init() had failed", "Interstitial"));
            }
            this.mInstancesToLoad.clear();
        } else if (this.mDidCallLoadInterstitial) {
            this.mCallbackThrotteler.onInterstitialAdLoadFailed(ErrorBuilder.buildInitFailedError("init() had failed", "Interstitial"));
            this.mDidCallLoadInterstitial = false;
            this.mIsLoadInterstitialInProgress = false;
        }
    }

    public void onStillInProgressAfter15Secs() {
        if (this.mIsInISDemandOnlyMode) {
            Iterator it = this.mInstancesToLoad.iterator();
            while (it.hasNext()) {
                this.mCallbackThrotteler.onInterstitialAdLoadFailed((String) it.next(), ErrorBuilder.buildInitFailedError("init() had failed", "Interstitial"));
            }
            this.mInstancesToLoad.clear();
        } else if (this.mDidCallLoadInterstitial) {
            this.mCallbackThrotteler.onInterstitialAdLoadFailed(ErrorBuilder.buildInitFailedError("init() had failed", "Interstitial"));
            this.mDidCallLoadInterstitial = false;
            this.mIsLoadInterstitialInProgress = false;
            if (this.mShouldSendAdReadyEvent) {
                Object[][] objArr = new Object[1][];
                objArr[0] = new Object[]{IronSourceConstants.ERROR_CODE_KEY, Integer.valueOf(error.getErrorCode())};
                logMediationEvent(IronSourceConstants.INTERSTITIAL_AD_LOAD_FAILED, objArr);
                this.mShouldSendAdReadyEvent = false;
            }
        }
    }

    private boolean isIterationRoundComplete() {
        Iterator it = this.mSmashArray.iterator();
        while (it.hasNext()) {
            AbstractSmash smash = (AbstractSmash) it.next();
            if (smash.getMediationState() == MEDIATION_STATE.NOT_INITIATED || smash.getMediationState() == MEDIATION_STATE.INIT_PENDING || smash.getMediationState() == MEDIATION_STATE.INITIATED || smash.getMediationState() == MEDIATION_STATE.LOAD_PENDING) {
                return false;
            }
            if (smash.getMediationState() == MEDIATION_STATE.AVAILABLE) {
                return false;
            }
        }
        return true;
    }

    private void completeIterationRound() {
        if (isIterationRoundComplete()) {
            this.mLoggerManager.log(IronSourceTag.INTERNAL, "Reset Iteration", 0);
            Iterator it = this.mSmashArray.iterator();
            while (it.hasNext()) {
                AbstractSmash smash = (AbstractSmash) it.next();
                if (smash.getMediationState() == MEDIATION_STATE.EXHAUSTED) {
                    smash.completeIteration();
                }
            }
            this.mLoggerManager.log(IronSourceTag.INTERNAL, "End of Reset Iteration", 0);
        }
    }

    private void completeAdapterShow(AbstractSmash smash) {
        if (smash.isMediationAvailable()) {
            smash.setMediationState(MEDIATION_STATE.INITIATED);
            return;
        }
        startNextAdapter();
        completeIterationRound();
    }

    private AbstractAdapter startNextAdapter() {
        AbstractAdapter initiatedAdapter = null;
        int activeAdapters = 0;
        int i = 0;
        while (i < this.mSmashArray.size() && initiatedAdapter == null) {
            if (((AbstractSmash) this.mSmashArray.get(i)).getMediationState() == MEDIATION_STATE.AVAILABLE || ((AbstractSmash) this.mSmashArray.get(i)).getMediationState() == MEDIATION_STATE.INITIATED || ((AbstractSmash) this.mSmashArray.get(i)).getMediationState() == MEDIATION_STATE.INIT_PENDING || ((AbstractSmash) this.mSmashArray.get(i)).getMediationState() == MEDIATION_STATE.LOAD_PENDING) {
                activeAdapters++;
                if (activeAdapters >= this.mSmartLoadAmount) {
                    break;
                }
            } else if (((AbstractSmash) this.mSmashArray.get(i)).getMediationState() == MEDIATION_STATE.NOT_INITIATED) {
                initiatedAdapter = startAdapter((InterstitialSmash) this.mSmashArray.get(i));
                if (initiatedAdapter == null) {
                    ((AbstractSmash) this.mSmashArray.get(i)).setMediationState(MEDIATION_STATE.INIT_FAILED);
                }
            }
            i++;
        }
        return initiatedAdapter;
    }

    private synchronized AbstractAdapter startAdapter(InterstitialSmash smash) {
        AbstractAdapter providerAdapter;
        this.mLoggerManager.log(IronSourceTag.NATIVE, this.TAG + ":startAdapter(" + smash.getName() + ")", 1);
        try {
            providerAdapter = getLoadedAdapterOrFetchByReflection(smash);
            if (providerAdapter == null) {
                providerAdapter = null;
            } else {
                IronSourceObject.getInstance().addToISAdaptersList(providerAdapter);
                providerAdapter.setLogListener(this.mLoggerManager);
                smash.setAdapterForSmash(providerAdapter);
                smash.setMediationState(MEDIATION_STATE.INIT_PENDING);
                if (this.mRewardedInterstitialListenerWrapper != null) {
                    smash.setRewardedInterstitialManagerListener(this);
                }
                setCustomParams(smash);
                smash.initInterstitial(this.mActivity, this.mAppKey, this.mUserId);
            }
        } catch (Throwable e) {
            this.mLoggerManager.logException(IronSourceTag.API, this.TAG + ":startAdapter(" + smash.getName() + ")", e);
            smash.setMediationState(MEDIATION_STATE.INIT_FAILED);
            this.mLoggerManager.log(IronSourceTag.API, ErrorBuilder.buildInitFailedError(smash.getName() + " initialization failed - please verify that required dependencies are in you build path.", "Interstitial").toString(), 2);
            providerAdapter = null;
        }
        return providerAdapter;
    }

    void setCurrentPlacement(InterstitialPlacement currentPlacement) {
        this.mCurrentPlacement = currentPlacement;
    }

    private synchronized void loadAdapterAndSendEvent(InterstitialSmash smash) {
        if (this.mIsInISDemandOnlyMode) {
            logMediationEvent(22, (Object[][]) null);
        }
        logProviderEvent(22, smash, (Object[][]) null);
        smash.loadInterstitial();
    }

    private synchronized void changeStateToInitiatedForInstanceId(String instanceId) {
        Iterator it = this.mSmashArray.iterator();
        while (it.hasNext()) {
            AbstractSmash smash = (AbstractSmash) it.next();
            if (smash.getSubProviderId().equals(instanceId) && (smash.getMediationState() == MEDIATION_STATE.AVAILABLE || smash.getMediationState() == MEDIATION_STATE.LOAD_PENDING || smash.getMediationState() == MEDIATION_STATE.NOT_AVAILABLE)) {
                smash.setMediationState(MEDIATION_STATE.INITIATED);
                break;
            }
        }
    }

    private synchronized void changeStateToInitiated() {
        Iterator it = this.mSmashArray.iterator();
        while (it.hasNext()) {
            AbstractSmash smash = (AbstractSmash) it.next();
            if (smash.getMediationState() == MEDIATION_STATE.AVAILABLE || smash.getMediationState() == MEDIATION_STATE.LOAD_PENDING || smash.getMediationState() == MEDIATION_STATE.NOT_AVAILABLE) {
                smash.setMediationState(MEDIATION_STATE.INITIATED);
            }
        }
    }

    private void sendShowChanceEvent(AbstractSmash smash, String placementName, boolean status) {
        r2 = new Object[2][];
        r2[0] = new Object[]{VungleActivity.PLACEMENT_EXTRA, placementName};
        Object[] objArr = new Object[2];
        objArr[0] = "status";
        objArr[1] = status ? "true" : "false";
        r2[1] = objArr;
        logProviderEvent(IronSourceConstants.INTERSTITIAL_SHOW_CHANCE, smash, r2);
    }

    private void sendShowChanceEvents(AbstractSmash selectedSmash, int priority, String placementName) {
        sendShowChanceEvent(selectedSmash, placementName, true);
        if (!this.mIsInISDemandOnlyMode) {
            int i = 0;
            while (i < this.mSmashArray.size() && i < priority) {
                AbstractSmash smash = (AbstractSmash) this.mSmashArray.get(i);
                if (smash.getMediationState() == MEDIATION_STATE.NOT_AVAILABLE) {
                    sendShowChanceEvent(smash, placementName, false);
                }
                i++;
            }
        }
    }

    private void logMediationEvent(int eventId, Object[][] keyVals) {
        JSONObject data = IronSourceUtils.getMediationAdditionalData(this.mIsInISDemandOnlyMode);
        if (keyVals != null) {
            try {
                for (Object[] pair : keyVals) {
                    data.put(pair[0].toString(), pair[1]);
                }
            } catch (Exception e) {
                this.mLoggerManager.log(IronSourceTag.INTERNAL, "InterstitialManager logMediationEvent " + Log.getStackTraceString(e), 3);
            }
        }
        InterstitialEventsManager.getInstance().log(new EventData(eventId, data));
    }

    private void logProviderEvent(int eventId, AbstractSmash smash, Object[][] keyVals) {
        JSONObject data = IronSourceUtils.getProviderAdditionalData(smash, this.mIsInISDemandOnlyMode);
        if (keyVals != null) {
            try {
                for (Object[] pair : keyVals) {
                    data.put(pair[0].toString(), pair[1]);
                }
            } catch (Exception e) {
                this.mLoggerManager.log(IronSourceTag.INTERNAL, "InterstitialManager logProviderEvent " + Log.getStackTraceString(e), 3);
            }
        }
        InterstitialEventsManager.getInstance().log(new EventData(eventId, data));
    }

    private int smashesCount(MEDIATION_STATE... states) {
        int ret;
        synchronized (this.mSmashArray) {
            ret = 0;
            Iterator it = this.mSmashArray.iterator();
            while (it.hasNext()) {
                AbstractSmash smash = (AbstractSmash) it.next();
                for (MEDIATION_STATE state : states) {
                    if (smash.getMediationState() == state) {
                        ret++;
                    }
                }
            }
        }
        return ret;
    }

    public void onDailyCapReleased() {
        if (this.mSmashArray != null) {
            Iterator it = this.mSmashArray.iterator();
            while (it.hasNext()) {
                AbstractSmash smash = (AbstractSmash) it.next();
                if (smash.getMediationState() == MEDIATION_STATE.CAPPED_PER_DAY) {
                    Object[][] objArr = new Object[1][];
                    objArr[0] = new Object[]{"status", "false"};
                    logProviderEvent(250, smash, objArr);
                    if (smash.isCappedPerSession()) {
                        smash.setMediationState(MEDIATION_STATE.CAPPED_PER_SESSION);
                    } else if (smash.isExhausted()) {
                        smash.setMediationState(MEDIATION_STATE.EXHAUSTED);
                    } else {
                        smash.setMediationState(MEDIATION_STATE.INITIATED);
                    }
                }
            }
        }
    }
}
