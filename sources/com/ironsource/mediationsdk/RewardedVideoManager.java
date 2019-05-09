package com.ironsource.mediationsdk;

import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.util.Log;
import com.ironsource.environment.NetworkStateReceiver;
import com.ironsource.environment.NetworkStateReceiver.NetworkStateReceiverListener;
import com.ironsource.eventsmodule.EventData;
import com.ironsource.mediationsdk.AbstractSmash.MEDIATION_STATE;
import com.ironsource.mediationsdk.events.RewardedVideoEventsManager;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.logger.IronSourceLogger.IronSourceTag;
import com.ironsource.mediationsdk.model.Placement;
import com.ironsource.mediationsdk.sdk.ISDemandOnlyRewardedVideoListener;
import com.ironsource.mediationsdk.sdk.RewardedVideoApi;
import com.ironsource.mediationsdk.sdk.RewardedVideoListener;
import com.ironsource.mediationsdk.sdk.RewardedVideoManagerListener;
import com.ironsource.mediationsdk.server.Server;
import com.ironsource.mediationsdk.utils.CappingManager;
import com.ironsource.mediationsdk.utils.DailyCappingListener;
import com.ironsource.mediationsdk.utils.DailyCappingManager;
import com.ironsource.mediationsdk.utils.ErrorBuilder;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import com.ironsource.mediationsdk.utils.IronSourceUtils;
import com.vungle.warren.ui.VungleActivity;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONException;
import org.json.JSONObject;

class RewardedVideoManager extends AbstractAdUnitManager implements RewardedVideoApi, RewardedVideoManagerListener, NetworkStateReceiverListener, DailyCappingListener {
    private final int CAPPED_PER_DAY_REASON;
    private final int CAPPED_PER_SESSION_REASON;
    private final String TAG;
    private Placement mCurrentPlacement;
    private ISDemandOnlyRewardedVideoListener mISDemandOnlyListenersWrapper;
    private boolean mIsUltraEventsEnabled;
    private RewardedVideoListener mListenersWrapper;
    private NetworkStateReceiver mNetworkStateReceiver;
    private boolean mPauseSmartLoadDueToNetworkUnavailability;
    private List<MEDIATION_STATE> mStatesToIgnore;

    RewardedVideoManager() {
        this.TAG = getClass().getSimpleName();
        this.CAPPED_PER_SESSION_REASON = 2;
        this.CAPPED_PER_DAY_REASON = 6;
        this.mPauseSmartLoadDueToNetworkUnavailability = false;
        this.mIsUltraEventsEnabled = false;
        this.mStatesToIgnore = Arrays.asList(new MEDIATION_STATE[]{MEDIATION_STATE.INIT_FAILED, MEDIATION_STATE.CAPPED_PER_SESSION, MEDIATION_STATE.EXHAUSTED, MEDIATION_STATE.CAPPED_PER_DAY});
        this.mDailyCappingManager = new DailyCappingManager("rewarded_video", this);
    }

    public void setRewardedVideoListener(RewardedVideoListener listener) {
        this.mListenersWrapper = listener;
    }

    public synchronized void initRewardedVideo(Activity activity, String appKey, String userId) {
        this.mLoggerManager.log(IronSourceTag.API, this.TAG + ":initRewardedVideo(appKey: " + appKey + ", userId: " + userId + ")", 1);
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
                    this.mLoggerManager.log(IronSourceTag.INTERNAL, smash.getAdSourceNameForEvents() + " has been removed from the RV waterfall due to demand only mode", 1);
                } else if (startAdapter((RewardedVideoSmash) smash) == null) {
                    smash.setMediationState(MEDIATION_STATE.INIT_FAILED);
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
                    logProviderEvent(IronSourceConstants.REWARDED_VIDEO_DAILY_CAPPED, smash, objArr);
                }
                if (this.mDailyCappingManager.isCapped(smash)) {
                    smash.setMediationState(MEDIATION_STATE.CAPPED_PER_DAY);
                    dailyCappedCount++;
                }
            }
            if (dailyCappedCount == this.mSmashArray.size()) {
                this.mListenersWrapper.onRewardedVideoAvailabilityChanged(false);
            } else {
                int i = 0;
                while (i < this.mSmartLoadAmount && i < this.mSmashArray.size() && loadNextAdapter() != null) {
                    i++;
                }
            }
        }
    }

    public synchronized void showRewardedVideo(String placementName) {
        this.mLoggerManager.log(IronSourceTag.API, this.TAG + ":showRewardedVideo(placementName: " + placementName + ")", 1);
        if (IronSourceUtils.isNetworkConnected(this.mActivity)) {
            sendShowCheckAvailabilityEvents(placementName);
            int capped = 0;
            int notAvailable = 0;
            for (int i = 0; i < this.mSmashArray.size(); i++) {
                AbstractSmash smash = (AbstractSmash) this.mSmashArray.get(i);
                if (smash.getMediationState() == MEDIATION_STATE.AVAILABLE) {
                    if (((RewardedVideoSmash) smash).isRewardedVideoAvailable()) {
                        showAdapter(smash, i);
                        if (this.mCanShowPremium && !smash.equals(getPremiumSmash())) {
                            disablePremiumForCurrentSession();
                        }
                        Object[][] objArr;
                        if (smash.isCappedPerSession()) {
                            smash.setMediationState(MEDIATION_STATE.CAPPED_PER_SESSION);
                            objArr = new Object[2][];
                            objArr[0] = new Object[]{"status", "false"};
                            objArr[1] = new Object[]{"reason", Integer.valueOf(2)};
                            logProviderEvent(7, smash, objArr);
                            completeAdapterCap();
                        } else if (this.mDailyCappingManager.isCapped(smash)) {
                            smash.setMediationState(MEDIATION_STATE.CAPPED_PER_DAY);
                            objArr = new Object[2][];
                            objArr[0] = new Object[]{"status", "false"};
                            objArr[1] = new Object[]{"reason", Integer.valueOf(6)};
                            logProviderEvent(7, smash, objArr);
                            objArr = new Object[1][];
                            objArr[0] = new Object[]{"status", "true"};
                            logProviderEvent(IronSourceConstants.REWARDED_VIDEO_DAILY_CAPPED, smash, objArr);
                            completeAdapterCap();
                        } else if (smash.isExhausted()) {
                            loadNextAdapter();
                            completeIterationRound();
                        }
                    } else {
                        onRewardedVideoAvailabilityChanged(false, (RewardedVideoSmash) smash);
                        this.mLoggerManager.logException(IronSourceTag.INTERNAL, smash.getInstanceName() + " Failed to show video", new Exception("FailedToShowVideoException"));
                    }
                } else if (smash.getMediationState() == MEDIATION_STATE.CAPPED_PER_SESSION || smash.getMediationState() == MEDIATION_STATE.CAPPED_PER_DAY) {
                    capped++;
                } else if (smash.getMediationState() == MEDIATION_STATE.NOT_AVAILABLE) {
                    notAvailable++;
                }
            }
            if (isBackFillAvailable()) {
                showAdapter(getBackfillSmash(), this.mSmashArray.size());
            } else if (capped + notAvailable == this.mSmashArray.size()) {
                this.mListenersWrapper.onRewardedVideoAdShowFailed(ErrorBuilder.buildNoAdsToShowError(IronSourceConstants.REWARDED_VIDEO_AD_UNIT));
            }
        } else {
            this.mListenersWrapper.onRewardedVideoAdShowFailed(ErrorBuilder.buildNoInternetConnectionShowFailError(IronSourceConstants.REWARDED_VIDEO_AD_UNIT));
        }
    }

    public synchronized boolean isRewardedVideoAvailable() {
        boolean z = false;
        synchronized (this) {
            this.mLoggerManager.log(IronSourceTag.API, this.TAG + ":isRewardedVideoAvailable()", 1);
            if (!this.mPauseSmartLoadDueToNetworkUnavailability) {
                Iterator it = this.mSmashArray.iterator();
                while (it.hasNext()) {
                    AbstractSmash smash = (AbstractSmash) it.next();
                    if (smash.isMediationAvailable() && ((RewardedVideoSmash) smash).isRewardedVideoAvailable()) {
                        z = true;
                        break;
                    }
                }
            }
        }
        return z;
    }

    public void setISDemandOnlyRewardedVideoListener(ISDemandOnlyRewardedVideoListener listener) {
        this.mISDemandOnlyListenersWrapper = listener;
    }

    public synchronized void showRewardedVideo(String instanceId, String placementName) {
        this.mLoggerManager.log(IronSourceTag.API, this.TAG + ":showRewardedVideo(instanceId: " + instanceId + ", placementName: " + placementName + ")", 1);
        if (IronSourceUtils.isNetworkConnected(this.mActivity)) {
            sendShowCheckAvailabilityEvents(placementName);
            boolean existingInstanceId = false;
            int i = 0;
            while (i < this.mSmashArray.size()) {
                AbstractSmash smash = (AbstractSmash) this.mSmashArray.get(i);
                if (smash.getSubProviderId().equals(instanceId)) {
                    existingInstanceId = true;
                    if (smash.getMediationState() != MEDIATION_STATE.AVAILABLE) {
                        if (smash.getMediationState() == MEDIATION_STATE.CAPPED_PER_SESSION) {
                            this.mListenersWrapper.onRewardedVideoAdShowFailed(new IronSourceError(IronSourceError.ERROR_CAPPED_PER_SESSION, "Instance has reached its cap per session"));
                        }
                        if (existingInstanceId) {
                            this.mISDemandOnlyListenersWrapper.onRewardedVideoAdShowFailed(instanceId, ErrorBuilder.buildNonExistentInstanceError(IronSourceConstants.REWARDED_VIDEO_AD_UNIT));
                        } else {
                            this.mISDemandOnlyListenersWrapper.onRewardedVideoAdShowFailed(instanceId, ErrorBuilder.buildNoAdsToShowError(IronSourceConstants.REWARDED_VIDEO_AD_UNIT));
                        }
                    } else if (((RewardedVideoSmash) smash).isRewardedVideoAvailable()) {
                        CappingManager.incrementShowCounter(this.mActivity, this.mCurrentPlacement);
                        Object[][] objArr = new Object[1][];
                        objArr[0] = new Object[]{VungleActivity.PLACEMENT_EXTRA, this.mCurrentPlacement.getPlacementName()};
                        logProviderEvent(2, smash, objArr);
                        sendShowChanceEvents(smash, i, this.mCurrentPlacement.getPlacementName());
                        ((RewardedVideoSmash) smash).showRewardedVideo();
                        if (smash.isCappedPerSession()) {
                            objArr = new Object[2][];
                            objArr[0] = new Object[]{"status", "false"};
                            objArr[1] = new Object[]{"reason", Integer.valueOf(2)};
                            logProviderEvent(7, smash, objArr);
                            onRewardedVideoAvailabilityChanged(false, (RewardedVideoSmash) smash);
                        } else if (this.mDailyCappingManager.isCapped(smash)) {
                            smash.setMediationState(MEDIATION_STATE.CAPPED_PER_DAY);
                            objArr = new Object[1][];
                            objArr[0] = new Object[]{"status", "true"};
                            logProviderEvent(IronSourceConstants.REWARDED_VIDEO_DAILY_CAPPED, smash, objArr);
                            onRewardedVideoAvailabilityChanged(false, (RewardedVideoSmash) smash);
                        }
                    } else {
                        onRewardedVideoAvailabilityChanged(false, (RewardedVideoSmash) smash);
                        this.mLoggerManager.logException(IronSourceTag.INTERNAL, smash.getInstanceName() + " Failed to show video", new Exception("FailedToShowVideoException"));
                    }
                } else {
                    i++;
                }
            }
            if (existingInstanceId) {
                this.mISDemandOnlyListenersWrapper.onRewardedVideoAdShowFailed(instanceId, ErrorBuilder.buildNoAdsToShowError(IronSourceConstants.REWARDED_VIDEO_AD_UNIT));
            } else {
                this.mISDemandOnlyListenersWrapper.onRewardedVideoAdShowFailed(instanceId, ErrorBuilder.buildNonExistentInstanceError(IronSourceConstants.REWARDED_VIDEO_AD_UNIT));
            }
        } else {
            this.mISDemandOnlyListenersWrapper.onRewardedVideoAdShowFailed(instanceId, ErrorBuilder.buildNoInternetConnectionShowFailError(IronSourceConstants.REWARDED_VIDEO_AD_UNIT));
        }
    }

    public synchronized boolean isRewardedVideoAvailable(String instanceId) {
        boolean z = false;
        synchronized (this) {
            this.mLoggerManager.log(IronSourceTag.API, this.TAG + ":isRewardedVideoAvailable(instanceId: " + instanceId + ")", 1);
            if (!this.mPauseSmartLoadDueToNetworkUnavailability) {
                Iterator it = this.mSmashArray.iterator();
                while (it.hasNext()) {
                    AbstractSmash smash = (AbstractSmash) it.next();
                    if (smash.getSubProviderId().equals(instanceId)) {
                        z = ((RewardedVideoSmash) smash).isRewardedVideoAvailable();
                        break;
                    }
                }
            }
        }
        return z;
    }

    public void onRewardedVideoAdShowFailed(IronSourceError error, RewardedVideoSmash smash) {
        this.mLoggerManager.log(IronSourceTag.ADAPTER_CALLBACK, smash.getInstanceName() + ":onRewardedVideoAdShowFailed(" + error + ")", 1);
        if (this.mIsInISDemandOnlyMode) {
            this.mISDemandOnlyListenersWrapper.onRewardedVideoAdShowFailed(smash.getSubProviderId(), error);
        } else {
            this.mListenersWrapper.onRewardedVideoAdShowFailed(error);
        }
    }

    public void onRewardedVideoAdOpened(RewardedVideoSmash smash) {
        this.mLoggerManager.log(IronSourceTag.ADAPTER_CALLBACK, smash.getInstanceName() + ":onRewardedVideoAdOpened()", 1);
        logProviderEvent(5, smash, (Object[][]) null);
        if (this.mIsInISDemandOnlyMode) {
            this.mISDemandOnlyListenersWrapper.onRewardedVideoAdOpened(smash.getSubProviderId());
        } else {
            this.mListenersWrapper.onRewardedVideoAdOpened();
        }
    }

    public void onRewardedVideoAdClosed(RewardedVideoSmash smash) {
        this.mLoggerManager.log(IronSourceTag.ADAPTER_CALLBACK, smash.getInstanceName() + ":onRewardedVideoAdClosed()", 1);
        verifyOnPauseOnResume();
        logProviderEvent(6, smash, (Object[][]) null);
        notifyIsAdAvailableForStatistics();
        if (this.mIsInISDemandOnlyMode) {
            this.mISDemandOnlyListenersWrapper.onRewardedVideoAdClosed(smash.getSubProviderId());
            return;
        }
        this.mListenersWrapper.onRewardedVideoAdClosed();
        Iterator it = this.mSmashArray.iterator();
        while (it.hasNext()) {
            AbstractSmash asmash = (AbstractSmash) it.next();
            if (asmash.getMediationState() == MEDIATION_STATE.NOT_AVAILABLE) {
                try {
                    if (!asmash.getInstanceName().equals(smash.getInstanceName())) {
                        this.mLoggerManager.log(IronSourceTag.INTERNAL, asmash.getInstanceName() + ":reload smash", 1);
                        ((RewardedVideoSmash) asmash).fetchRewardedVideo();
                    }
                } catch (Throwable t) {
                    this.mLoggerManager.log(IronSourceTag.NATIVE, asmash.getInstanceName() + " Failed to call fetchVideo(), " + t.getLocalizedMessage(), 1);
                }
            }
        }
    }

    public synchronized void onRewardedVideoAvailabilityChanged(boolean available, RewardedVideoSmash smash) {
        if (!this.mPauseSmartLoadDueToNetworkUnavailability) {
            try {
                this.mLoggerManager.log(IronSourceTag.ADAPTER_CALLBACK, smash.getInstanceName() + ":onRewardedVideoAvailabilityChanged(available:" + available + ")", 1);
                Object[][] objArr = new Object[1][];
                objArr[0] = new Object[]{"status", String.valueOf(available)};
                logProviderEvent(7, smash, objArr);
                if (this.mIsInISDemandOnlyMode) {
                    this.mISDemandOnlyListenersWrapper.onRewardedVideoAvailabilityChanged(smash.getSubProviderId(), available);
                    if (shouldNotifyAvailabilityChanged(available)) {
                        objArr = new Object[1][];
                        objArr[0] = new Object[]{"status", String.valueOf(available)};
                        logMediationEvent(7, objArr);
                    }
                } else if (!smash.equals(getBackfillSmash())) {
                    if (smash.equals(getPremiumSmash())) {
                        this.mLoggerManager.log(IronSourceTag.ADAPTER_CALLBACK, smash.getInstanceName() + " is a Premium adapter, canShowPremium: " + canShowPremium(), 1);
                        if (!canShowPremium()) {
                            smash.setMediationState(MEDIATION_STATE.CAPPED_PER_SESSION);
                            if (shouldNotifyAvailabilityChanged(false)) {
                                this.mListenersWrapper.onRewardedVideoAvailabilityChanged(this.mLastMediationAvailabilityState.booleanValue());
                            }
                        }
                    }
                    if (smash.isMediationAvailable() && !this.mDailyCappingManager.isCapped(smash)) {
                        if (!available) {
                            if (shouldNotifyAvailabilityChanged(false)) {
                                notifyAvailabilityChange();
                            }
                            loadNextAdapter();
                            completeIterationRound();
                        } else if (shouldNotifyAvailabilityChanged(true)) {
                            this.mListenersWrapper.onRewardedVideoAvailabilityChanged(this.mLastMediationAvailabilityState.booleanValue());
                        }
                    }
                } else if (shouldNotifyAvailabilityChanged(available)) {
                    this.mListenersWrapper.onRewardedVideoAvailabilityChanged(this.mLastMediationAvailabilityState.booleanValue());
                }
            } catch (Throwable e) {
                this.mLoggerManager.logException(IronSourceTag.ADAPTER_CALLBACK, "onRewardedVideoAvailabilityChanged(available:" + available + ", " + "provider:" + smash.getName() + ")", e);
            }
        }
    }

    public void onRewardedVideoAdStarted(RewardedVideoSmash smash) {
        this.mLoggerManager.log(IronSourceTag.ADAPTER_CALLBACK, smash.getInstanceName() + ":onRewardedVideoAdStarted()", 1);
        logProviderEvent(8, smash, (Object[][]) null);
        this.mListenersWrapper.onRewardedVideoAdStarted();
    }

    public void onRewardedVideoAdEnded(RewardedVideoSmash smash) {
        this.mLoggerManager.log(IronSourceTag.ADAPTER_CALLBACK, smash.getInstanceName() + ":onRewardedVideoAdEnded()", 1);
        logProviderEvent(9, smash, (Object[][]) null);
        this.mListenersWrapper.onRewardedVideoAdEnded();
    }

    public void onRewardedVideoAdRewarded(RewardedVideoSmash smash) {
        this.mLoggerManager.log(IronSourceTag.ADAPTER_CALLBACK, smash.getInstanceName() + ":onRewardedVideoAdRewarded()", 1);
        JSONObject data = IronSourceUtils.getProviderAdditionalData(smash, this.mIsInISDemandOnlyMode);
        try {
            data.put(VungleActivity.PLACEMENT_EXTRA, this.mCurrentPlacement.getPlacementName());
            data.put("rewardName", this.mCurrentPlacement.getRewardName());
            data.put("rewardAmount", this.mCurrentPlacement.getRewardAmount());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        EventData event = new EventData(10, data);
        if (!TextUtils.isEmpty(this.mAppKey)) {
            event.addToAdditionalData("transId", IronSourceUtils.getTransId("" + Long.toString(event.getTimeStamp()) + this.mAppKey + smash.getName()));
            if (!TextUtils.isEmpty(IronSourceObject.getInstance().getDynamicUserId())) {
                event.addToAdditionalData("dynamicUserId", IronSourceObject.getInstance().getDynamicUserId());
            }
            Map<String, String> rvServerParams = IronSourceObject.getInstance().getRvServerParams();
            if (rvServerParams != null) {
                for (String key : rvServerParams.keySet()) {
                    event.addToAdditionalData("custom_" + key, rvServerParams.get(key));
                }
            }
        }
        RewardedVideoEventsManager.getInstance().log(event);
        if (this.mIsInISDemandOnlyMode) {
            this.mISDemandOnlyListenersWrapper.onRewardedVideoAdRewarded(smash.getSubProviderId(), this.mCurrentPlacement);
        } else {
            this.mListenersWrapper.onRewardedVideoAdRewarded(this.mCurrentPlacement);
        }
    }

    public void onRewardedVideoAdClicked(RewardedVideoSmash smash) {
        this.mLoggerManager.log(IronSourceTag.ADAPTER_CALLBACK, smash.getInstanceName() + ":onRewardedVideoAdClicked()", 1);
        Object[][] objArr = new Object[1][];
        objArr[0] = new Object[]{VungleActivity.PLACEMENT_EXTRA, this.mCurrentPlacement.getPlacementName()};
        logProviderEvent(128, smash, objArr);
        if (this.mIsInISDemandOnlyMode) {
            this.mISDemandOnlyListenersWrapper.onRewardedVideoAdClicked(smash.getSubProviderId(), this.mCurrentPlacement);
        } else {
            this.mListenersWrapper.onRewardedVideoAdClicked(this.mCurrentPlacement);
        }
    }

    public void onRewardedVideoAdVisible(RewardedVideoSmash smash) {
        this.mLoggerManager.log(IronSourceTag.ADAPTER_CALLBACK, smash.getInstanceName() + ":onRewardedVideoAdVisible()", 1);
        Object[][] objArr = new Object[1][];
        objArr[0] = new Object[]{VungleActivity.PLACEMENT_EXTRA, this.mCurrentPlacement.getPlacementName()};
        logProviderEvent(11, smash, objArr);
    }

    public void onNetworkAvailabilityChanged(boolean connected) {
        boolean z = false;
        if (this.mShouldTrackNetworkState) {
            this.mLoggerManager.log(IronSourceTag.INTERNAL, "Network Availability Changed To: " + connected, 0);
            if (shouldNotifyNetworkAvailabilityChanged(connected)) {
                if (!connected) {
                    z = true;
                }
                this.mPauseSmartLoadDueToNetworkUnavailability = z;
                this.mListenersWrapper.onRewardedVideoAvailabilityChanged(connected);
            }
        }
    }

    void shouldTrackNetworkState(Context context, boolean track) {
        this.mLoggerManager.log(IronSourceTag.INTERNAL, this.TAG + " Should Track Network State: " + track, 0);
        this.mShouldTrackNetworkState = track;
        if (this.mShouldTrackNetworkState) {
            if (this.mNetworkStateReceiver == null) {
                this.mNetworkStateReceiver = new NetworkStateReceiver(context, this);
            }
            context.registerReceiver(this.mNetworkStateReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        } else if (this.mNetworkStateReceiver != null) {
            context.unregisterReceiver(this.mNetworkStateReceiver);
        }
    }

    private boolean shouldNotifyNetworkAvailabilityChanged(boolean networkState) {
        boolean shouldNotify = false;
        if (this.mLastMediationAvailabilityState == null) {
            return false;
        }
        if (networkState && !this.mLastMediationAvailabilityState.booleanValue() && hasAvailableSmash()) {
            this.mLastMediationAvailabilityState = Boolean.valueOf(true);
            shouldNotify = true;
        } else if (!networkState && this.mLastMediationAvailabilityState.booleanValue()) {
            this.mLastMediationAvailabilityState = Boolean.valueOf(false);
            shouldNotify = true;
        }
        return shouldNotify;
    }

    void setIsUltraEventsEnabled(boolean enabled) {
        this.mIsUltraEventsEnabled = enabled;
    }

    private void reportFalseImpressionsOnHigherPriority(int priority, int placementId) {
        int i = 0;
        while (i < priority && i < this.mSmashArray.size()) {
            if (!this.mStatesToIgnore.contains(((AbstractSmash) this.mSmashArray.get(i)).getMediationState())) {
                reportImpression(((RewardedVideoSmash) this.mSmashArray.get(i)).getRequestUrl(), false, placementId);
            }
            i++;
        }
    }

    private synchronized void reportImpression(String adapterUrl, boolean hit, int placementId) {
        String url;
        try {
            url = ("" + adapterUrl) + "&sdkVersion=" + IronSourceUtils.getSDKVersion();
            Server.callAsyncRequestURL(url, hit, placementId);
        } catch (Throwable e) {
            this.mLoggerManager.logException(IronSourceTag.NETWORK, "reportImpression:(providerURL:" + url + ", " + "hit:" + hit + ")", e);
        }
    }

    void setCurrentPlacement(Placement currentPlacement) {
        this.mCurrentPlacement = currentPlacement;
    }

    protected synchronized void disablePremiumForCurrentSession() {
        super.disablePremiumForCurrentSession();
        Iterator it = this.mSmashArray.iterator();
        while (it.hasNext()) {
            AbstractSmash smash = (AbstractSmash) it.next();
            if (smash.equals(getPremiumSmash())) {
                smash.setMediationState(MEDIATION_STATE.CAPPED_PER_SESSION);
                loadNextAdapter();
                break;
            }
        }
    }

    private synchronized AbstractAdapter startAdapter(RewardedVideoSmash smash) {
        AbstractAdapter providerAdapter;
        this.mLoggerManager.log(IronSourceTag.NATIVE, this.TAG + ":startAdapter(" + smash.getInstanceName() + ")", 1);
        try {
            providerAdapter = getLoadedAdapterOrFetchByReflection(smash);
            if (providerAdapter == null) {
                providerAdapter = null;
            } else {
                IronSourceObject.getInstance().addToRVAdaptersList(providerAdapter);
                providerAdapter.setLogListener(this.mLoggerManager);
                smash.setAdapterForSmash(providerAdapter);
                smash.setMediationState(MEDIATION_STATE.INITIATED);
                setCustomParams(smash);
                smash.initRewardedVideo(this.mActivity, this.mAppKey, this.mUserId);
            }
        } catch (Throwable e) {
            this.mLoggerManager.logException(IronSourceTag.API, this.TAG + ":startAdapter(" + smash.getName() + ")", e);
            smash.setMediationState(MEDIATION_STATE.INIT_FAILED);
            if (shouldNotifyAvailabilityChanged(false)) {
                this.mListenersWrapper.onRewardedVideoAvailabilityChanged(this.mLastMediationAvailabilityState.booleanValue());
            }
            this.mLoggerManager.log(IronSourceTag.API, ErrorBuilder.buildInitFailedError(smash.getName() + " initialization failed - please verify that required dependencies are in you build path.", IronSourceConstants.REWARDED_VIDEO_AD_UNIT).toString(), 2);
            providerAdapter = null;
        }
        return providerAdapter;
    }

    private AbstractAdapter loadNextAdapter() {
        AbstractAdapter initiatedAdapter = null;
        int activeAdapters = 0;
        int i = 0;
        while (i < this.mSmashArray.size() && initiatedAdapter == null) {
            if (((AbstractSmash) this.mSmashArray.get(i)).getMediationState() == MEDIATION_STATE.AVAILABLE || ((AbstractSmash) this.mSmashArray.get(i)).getMediationState() == MEDIATION_STATE.INITIATED) {
                activeAdapters++;
                if (activeAdapters >= this.mSmartLoadAmount) {
                    break;
                }
            } else if (((AbstractSmash) this.mSmashArray.get(i)).getMediationState() == MEDIATION_STATE.NOT_INITIATED) {
                initiatedAdapter = startAdapter((RewardedVideoSmash) this.mSmashArray.get(i));
                if (initiatedAdapter == null) {
                    ((AbstractSmash) this.mSmashArray.get(i)).setMediationState(MEDIATION_STATE.INIT_FAILED);
                }
            }
            i++;
        }
        return initiatedAdapter;
    }

    private synchronized void showAdapter(AbstractSmash smash, int index) {
        CappingManager.incrementShowCounter(this.mActivity, this.mCurrentPlacement);
        this.mDailyCappingManager.increaseShowCounter(smash);
        if (this.mIsUltraEventsEnabled) {
            reportImpression(((RewardedVideoSmash) smash).getRequestUrl(), true, this.mCurrentPlacement.getPlacementId());
            reportFalseImpressionsOnHigherPriority(index, this.mCurrentPlacement.getPlacementId());
        }
        Object[][] objArr = new Object[1][];
        objArr[0] = new Object[]{VungleActivity.PLACEMENT_EXTRA, this.mCurrentPlacement.getPlacementName()};
        logProviderEvent(2, smash, objArr);
        sendShowChanceEvents(smash, index, this.mCurrentPlacement.getPlacementName());
        ((RewardedVideoSmash) smash).showRewardedVideo();
    }

    private synchronized void notifyIsAdAvailableForStatistics() {
        boolean mediationStatus = false;
        Iterator it = this.mSmashArray.iterator();
        while (it.hasNext()) {
            if (((AbstractSmash) it.next()).getMediationState() == MEDIATION_STATE.AVAILABLE) {
                mediationStatus = true;
                break;
            }
        }
        Object[][] objArr = new Object[1][];
        objArr[0] = new Object[]{"status", String.valueOf(mediationStatus)};
        logMediationEvent(3, objArr);
        it = this.mSmashArray.iterator();
        while (it.hasNext()) {
            AbstractSmash smash = (AbstractSmash) it.next();
            Object[][] objArr2;
            if (smash.getMediationState() == MEDIATION_STATE.AVAILABLE) {
                objArr2 = new Object[1][];
                objArr2[0] = new Object[]{"status", "true"};
                logProviderEvent(3, smash, objArr2);
            } else if (smash.getMediationState() == MEDIATION_STATE.NOT_AVAILABLE || smash.getMediationState() == MEDIATION_STATE.INITIATED) {
                objArr2 = new Object[1][];
                objArr2[0] = new Object[]{"status", "false"};
                logProviderEvent(3, smash, objArr2);
            }
        }
        if (!(getBackfillSmash() == null || getBackfillSmash().getAdapter() == null)) {
            AbstractSmash backfillSmash = getBackfillSmash();
            Object[][] objArr3 = new Object[1][];
            Object[] objArr4 = new Object[2];
            objArr4[0] = "status";
            objArr4[1] = isBackFillAvailable() ? "true" : "false";
            objArr3[0] = objArr4;
            logProviderEvent(3, backfillSmash, objArr3);
        }
    }

    private synchronized boolean shouldNotifyAvailabilityChanged(boolean adapterAvailability) {
        boolean shouldNotify;
        shouldNotify = false;
        if (this.mLastMediationAvailabilityState == null) {
            if (adapterAvailability) {
                this.mLastMediationAvailabilityState = Boolean.valueOf(true);
                shouldNotify = true;
            } else if (!isBackFillAvailable() && isAllAdaptersInactive()) {
                this.mLastMediationAvailabilityState = Boolean.valueOf(false);
                shouldNotify = true;
            }
        } else if (adapterAvailability && !this.mLastMediationAvailabilityState.booleanValue()) {
            this.mLastMediationAvailabilityState = Boolean.valueOf(true);
            shouldNotify = true;
        } else if (!(adapterAvailability || !this.mLastMediationAvailabilityState.booleanValue() || hasAvailableSmash() || isBackFillAvailable())) {
            this.mLastMediationAvailabilityState = Boolean.valueOf(false);
            shouldNotify = true;
        }
        return shouldNotify;
    }

    private synchronized boolean isAllAdaptersInactive() {
        int countInactive;
        countInactive = 0;
        Iterator it = this.mSmashArray.iterator();
        while (it.hasNext()) {
            AbstractSmash smash = (AbstractSmash) it.next();
            if (smash.getMediationState() == MEDIATION_STATE.INIT_FAILED || smash.getMediationState() == MEDIATION_STATE.CAPPED_PER_DAY || smash.getMediationState() == MEDIATION_STATE.CAPPED_PER_SESSION || smash.getMediationState() == MEDIATION_STATE.NOT_AVAILABLE || smash.getMediationState() == MEDIATION_STATE.EXHAUSTED) {
                countInactive++;
            }
        }
        return this.mSmashArray.size() == countInactive;
    }

    private synchronized boolean hasAvailableSmash() {
        boolean hasAvailableSmash;
        hasAvailableSmash = false;
        Iterator it = this.mSmashArray.iterator();
        while (it.hasNext()) {
            if (((AbstractSmash) it.next()).getMediationState() == MEDIATION_STATE.AVAILABLE) {
                hasAvailableSmash = true;
                break;
            }
        }
        return hasAvailableSmash;
    }

    private synchronized boolean isBackFillAvailable() {
        boolean isRewardedVideoAvailable;
        if (getBackfillSmash() != null) {
            isRewardedVideoAvailable = ((RewardedVideoSmash) getBackfillSmash()).isRewardedVideoAvailable();
        } else {
            isRewardedVideoAvailable = false;
        }
        return isRewardedVideoAvailable;
    }

    private void sendShowCheckAvailabilityEvents(String placementName) {
        for (int i = 0; i < this.mSmashArray.size(); i++) {
            if (((AbstractSmash) this.mSmashArray.get(i)).getMediationState() == MEDIATION_STATE.AVAILABLE) {
                createAndSendShowCheckAvailabilityEvent((AbstractSmash) this.mSmashArray.get(i), placementName, true);
            } else if (((AbstractSmash) this.mSmashArray.get(i)).getMediationState() == MEDIATION_STATE.NOT_AVAILABLE) {
                createAndSendShowCheckAvailabilityEvent((AbstractSmash) this.mSmashArray.get(i), placementName, false);
            }
        }
        if (getBackfillSmash() != null && getBackfillSmash().getAdapter() != null) {
            createAndSendShowCheckAvailabilityEvent(getBackfillSmash(), placementName, isBackFillAvailable());
        }
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

    private void createAndSendShowCheckAvailabilityEvent(AbstractSmash smash, String placementName, boolean status) {
        r2 = new Object[2][];
        r2[0] = new Object[]{VungleActivity.PLACEMENT_EXTRA, placementName};
        Object[] objArr = new Object[2];
        objArr[0] = "status";
        objArr[1] = status ? "true" : "false";
        r2[1] = objArr;
        logProviderEvent(19, smash, r2);
    }

    private void sendShowChanceEvent(AbstractSmash smash, String placementName, boolean status) {
        r2 = new Object[2][];
        r2[0] = new Object[]{VungleActivity.PLACEMENT_EXTRA, placementName};
        Object[] objArr = new Object[2];
        objArr[0] = "status";
        objArr[1] = status ? "true" : "false";
        r2[1] = objArr;
        logProviderEvent(IronSourceConstants.REWARDED_VIDEO_SHOW_CHANCE, smash, r2);
    }

    private synchronized void notifyAvailabilityChange() {
        if (getBackfillSmash() != null && !this.mBackFillInitStarted) {
            this.mBackFillInitStarted = true;
            if (startAdapter((RewardedVideoSmash) getBackfillSmash()) == null) {
                this.mListenersWrapper.onRewardedVideoAvailabilityChanged(this.mLastMediationAvailabilityState.booleanValue());
            }
        } else if (!isBackFillAvailable()) {
            this.mListenersWrapper.onRewardedVideoAvailabilityChanged(this.mLastMediationAvailabilityState.booleanValue());
        } else if (shouldNotifyAvailabilityChanged(true)) {
            this.mListenersWrapper.onRewardedVideoAvailabilityChanged(this.mLastMediationAvailabilityState.booleanValue());
        }
    }

    private synchronized void completeAdapterCap() {
        if (loadNextAdapter() == null) {
            if (smashesCount(MEDIATION_STATE.NOT_AVAILABLE, MEDIATION_STATE.CAPPED_PER_SESSION, MEDIATION_STATE.CAPPED_PER_DAY) < this.mSmashArray.size()) {
                completeIterationRound();
            } else if (shouldNotifyAvailabilityChanged(false)) {
                notifyAvailabilityChange();
            }
        }
    }

    private synchronized void completeIterationRound() {
        if (isIterationRoundComplete()) {
            this.mLoggerManager.log(IronSourceTag.INTERNAL, "Reset Iteration", 0);
            boolean isAvailable = false;
            Iterator it = this.mSmashArray.iterator();
            while (it.hasNext()) {
                AbstractSmash smash = (AbstractSmash) it.next();
                if (smash.getMediationState() == MEDIATION_STATE.EXHAUSTED) {
                    smash.completeIteration();
                }
                if (smash.getMediationState() == MEDIATION_STATE.AVAILABLE) {
                    isAvailable = true;
                }
            }
            this.mLoggerManager.log(IronSourceTag.INTERNAL, "End of Reset Iteration", 0);
            if (shouldNotifyAvailabilityChanged(isAvailable)) {
                this.mListenersWrapper.onRewardedVideoAvailabilityChanged(this.mLastMediationAvailabilityState.booleanValue());
            }
        }
    }

    private synchronized boolean isIterationRoundComplete() {
        boolean z;
        Iterator it = this.mSmashArray.iterator();
        while (it.hasNext()) {
            AbstractSmash smash = (AbstractSmash) it.next();
            if (smash.getMediationState() != MEDIATION_STATE.NOT_INITIATED && smash.getMediationState() != MEDIATION_STATE.INITIATED) {
                if (smash.getMediationState() == MEDIATION_STATE.AVAILABLE) {
                    z = false;
                    break;
                }
            }
            z = false;
            break;
        }
        z = true;
        return z;
    }

    private void logMediationEvent(int eventId, Object[][] keyVals) {
        JSONObject data = IronSourceUtils.getMediationAdditionalData(this.mIsInISDemandOnlyMode);
        if (keyVals != null) {
            try {
                for (Object[] pair : keyVals) {
                    data.put(pair[0].toString(), pair[1]);
                }
            } catch (Exception e) {
                this.mLoggerManager.log(IronSourceTag.INTERNAL, "RewardedVideoManager logMediationEvent " + Log.getStackTraceString(e), 3);
            }
        }
        RewardedVideoEventsManager.getInstance().log(new EventData(eventId, data));
    }

    private void logProviderEvent(int eventId, AbstractSmash smash, Object[][] keyVals) {
        JSONObject data = IronSourceUtils.getProviderAdditionalData(smash, this.mIsInISDemandOnlyMode);
        if (keyVals != null) {
            try {
                for (Object[] pair : keyVals) {
                    data.put(pair[0].toString(), pair[1]);
                }
            } catch (Exception e) {
                this.mLoggerManager.log(IronSourceTag.INTERNAL, "RewardedVideoManager logProviderEvent " + Log.getStackTraceString(e), 3);
            }
        }
        RewardedVideoEventsManager.getInstance().log(new EventData(eventId, data));
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
        boolean atLeastOneSmashBecameAvailable = false;
        Iterator it = this.mSmashArray.iterator();
        while (it.hasNext()) {
            AbstractSmash smash = (AbstractSmash) it.next();
            if (smash.getMediationState() == MEDIATION_STATE.CAPPED_PER_DAY) {
                Object[][] objArr = new Object[1][];
                objArr[0] = new Object[]{"status", "false"};
                logProviderEvent(IronSourceConstants.REWARDED_VIDEO_DAILY_CAPPED, smash, objArr);
                smash.setMediationState(MEDIATION_STATE.NOT_AVAILABLE);
                if (((RewardedVideoSmash) smash).isRewardedVideoAvailable() && smash.isMediationAvailable()) {
                    smash.setMediationState(MEDIATION_STATE.AVAILABLE);
                    atLeastOneSmashBecameAvailable = true;
                }
            }
        }
        if (atLeastOneSmashBecameAvailable && shouldNotifyAvailabilityChanged(true)) {
            this.mListenersWrapper.onRewardedVideoAvailabilityChanged(true);
        }
    }
}
