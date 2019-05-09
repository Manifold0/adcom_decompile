package com.ironsource.mediationsdk;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;
import com.ironsource.environment.DeviceStatus;
import com.ironsource.eventsmodule.EventData;
import com.ironsource.mediationsdk.IronSource.AD_UNIT;
import com.ironsource.mediationsdk.config.ConfigValidationResult;
import com.ironsource.mediationsdk.events.InterstitialEventsManager;
import com.ironsource.mediationsdk.events.RewardedVideoEventsManager;
import com.ironsource.mediationsdk.events.SuperLooper;
import com.ironsource.mediationsdk.logger.ConsoleLogger;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.logger.IronSourceLogger.IronSourceTag;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.logger.LogListener;
import com.ironsource.mediationsdk.logger.PublisherLogger;
import com.ironsource.mediationsdk.model.ApplicationEvents;
import com.ironsource.mediationsdk.model.BannerPlacement;
import com.ironsource.mediationsdk.model.InterstitialPlacement;
import com.ironsource.mediationsdk.model.OfferwallPlacement;
import com.ironsource.mediationsdk.model.Placement;
import com.ironsource.mediationsdk.model.ProviderSettings;
import com.ironsource.mediationsdk.sdk.ISDemandOnlyInterstitialListener;
import com.ironsource.mediationsdk.sdk.ISDemandOnlyRewardedVideoListener;
import com.ironsource.mediationsdk.sdk.InterstitialListener;
import com.ironsource.mediationsdk.sdk.IronSourceInterface;
import com.ironsource.mediationsdk.sdk.ListenersWrapper;
import com.ironsource.mediationsdk.sdk.OfferwallListener;
import com.ironsource.mediationsdk.sdk.RewardedInterstitialListener;
import com.ironsource.mediationsdk.sdk.RewardedVideoListener;
import com.ironsource.mediationsdk.sdk.SegmentListener;
import com.ironsource.mediationsdk.server.HttpFunctions;
import com.ironsource.mediationsdk.server.ServerURL;
import com.ironsource.mediationsdk.utils.CappingManager;
import com.ironsource.mediationsdk.utils.CappingManager.ECappingStatus;
import com.ironsource.mediationsdk.utils.ErrorBuilder;
import com.ironsource.mediationsdk.utils.GeneralPropertiesWorker;
import com.ironsource.mediationsdk.utils.IronSourceAES;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import com.ironsource.mediationsdk.utils.IronSourceConstants.Gender;
import com.ironsource.mediationsdk.utils.IronSourceUtils;
import com.ironsource.mediationsdk.utils.ServerResponseWrapper;
import com.ironsource.sdk.constants.Constants.RequestParameters;
import com.kongregate.android.internal.browser.C0462b;
import com.vungle.warren.ui.VungleActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

public class IronSourceObject implements IronSourceInterface, OnMediationInitializationListener {
    private static IronSourceObject sInstance;
    private final String IRONSOURCE_VERSION_STRING = "!SDK-VERSION-STRING!:com.ironsource:mediationsdk:​6.8.0";
    private final String KEY_INIT_COUNTER = RequestParameters.SESSION_DEPTH;
    private final String TAG = getClass().getName();
    private boolean isDemandOnlyInterstitial;
    private boolean isDemandOnlyRewardedVideo;
    private Activity mActivity;
    private Set<AD_UNIT> mAdUnitsToInitialize;
    private String mAppKey = null;
    private AtomicBoolean mAtomicIsFirstInit;
    private ArrayList<AbstractAdapter> mBannerAdaptersList;
    private BannerManager mBannerManager;
    private IronSourceBannerLayout mBnLayoutToLoad;
    private String mBnPlacementToLoad;
    private Boolean mConsent = null;
    private ServerResponseWrapper mCurrentServerResponse = null;
    private boolean mDidInitBanner;
    private boolean mDidInitInterstitial;
    private boolean mDidInitRewardedVideo;
    private String mDynamicUserId = null;
    private AtomicBoolean mEventManagersInit;
    private int mInitCounter;
    private boolean mInitSucceeded = false;
    private List<AD_UNIT> mInitiatedAdUnits;
    private ArrayList<AbstractAdapter> mInterstitialAdaptersList;
    private InterstitialManager mInterstitialManager;
    private IronSourceSegment mIronSegment;
    private boolean mIsBnLoadBeforeInitCompleted;
    private ListenersWrapper mListenersWrapper;
    private IronSourceLoggerManager mLoggerManager;
    private String mMediationType = null;
    private AbstractAdapter mOfferwallAdapter;
    private OfferwallManager mOfferwallManager;
    private PublisherLogger mPublisherLogger;
    private Set<AD_UNIT> mRequestedAdUnits;
    private ArrayList<AbstractAdapter> mRewardedVideoAdaptersList;
    private RewardedVideoManager mRewardedVideoManager;
    private Map<String, String> mRvServerParams = null;
    private String mSegment = null;
    private final Object mServerResponseLocker = new Object();
    private String mSessionId = null;
    private boolean mShouldSendGetInstanceEvent = true;
    private Integer mUserAge = null;
    private String mUserGender = null;
    private String mUserId = null;

    public interface IResponseListener {
        void onUnrecoverableError(String str);
    }

    public static synchronized IronSourceObject getInstance() {
        IronSourceObject ironSourceObject;
        synchronized (IronSourceObject.class) {
            if (sInstance == null) {
                sInstance = new IronSourceObject();
            }
            ironSourceObject = sInstance;
        }
        return ironSourceObject;
    }

    private IronSourceObject() {
        initializeManagers();
        this.mEventManagersInit = new AtomicBoolean();
        this.mRewardedVideoAdaptersList = new ArrayList();
        this.mInterstitialAdaptersList = new ArrayList();
        this.mBannerAdaptersList = new ArrayList();
        this.mAdUnitsToInitialize = new HashSet();
        this.mRequestedAdUnits = new HashSet();
        this.isDemandOnlyInterstitial = false;
        this.isDemandOnlyRewardedVideo = false;
        this.mAtomicIsFirstInit = new AtomicBoolean(true);
        this.mInitCounter = 0;
        this.mDidInitRewardedVideo = false;
        this.mDidInitInterstitial = false;
        this.mDidInitBanner = false;
        this.mSessionId = UUID.randomUUID().toString();
        this.mIsBnLoadBeforeInitCompleted = false;
        this.mBnPlacementToLoad = null;
    }

    public synchronized void init(Activity activity, String appKey, boolean isDemandOnlyInit, AD_UNIT... adUnits) {
        int i = 0;
        synchronized (this) {
            if (this.mAtomicIsFirstInit != null && this.mAtomicIsFirstInit.compareAndSet(true, false)) {
                int length;
                if (adUnits == null || adUnits.length == 0) {
                    for (AD_UNIT adUnit : AD_UNIT.values()) {
                        this.mAdUnitsToInitialize.add(adUnit);
                    }
                    this.mDidInitRewardedVideo = true;
                    this.mDidInitInterstitial = true;
                    this.mDidInitBanner = true;
                } else {
                    for (AD_UNIT adUnit2 : adUnits) {
                        this.mAdUnitsToInitialize.add(adUnit2);
                        this.mRequestedAdUnits.add(adUnit2);
                        if (adUnit2.equals(AD_UNIT.INTERSTITIAL)) {
                            this.mDidInitInterstitial = true;
                        }
                        if (adUnit2.equals(AD_UNIT.BANNER)) {
                            this.mDidInitBanner = true;
                        }
                        if (adUnit2.equals(AD_UNIT.REWARDED_VIDEO)) {
                            this.mDidInitRewardedVideo = true;
                        }
                    }
                }
                this.mLoggerManager.log(IronSourceTag.API, "init(appKey:" + appKey + ")", 1);
                if (activity == null) {
                    this.mLoggerManager.log(IronSourceTag.API, "Init Fail - provided activity is null", 2);
                } else {
                    this.mActivity = activity;
                    prepareEventManagers(activity);
                    ConfigValidationResult validationResultAppKey = validateAppKey(appKey);
                    if (validationResultAppKey.isValid()) {
                        this.mAppKey = appKey;
                        if (this.mShouldSendGetInstanceEvent) {
                            JSONObject data = IronSourceUtils.getMediationAdditionalData(isDemandOnlyInit);
                            if (adUnits != null) {
                                try {
                                    length = adUnits.length;
                                    while (i < length) {
                                        data.put(adUnits[i].toString(), true);
                                        i++;
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            String str = RequestParameters.SESSION_DEPTH;
                            length = this.mInitCounter + 1;
                            this.mInitCounter = length;
                            data.put(str, length);
                            RewardedVideoEventsManager.getInstance().log(new EventData(14, data));
                            this.mShouldSendGetInstanceEvent = false;
                        }
                        if (this.mAdUnitsToInitialize.contains(AD_UNIT.INTERSTITIAL)) {
                            MediationInitializer.getInstance().addMediationInitializationListener(this.mInterstitialManager);
                        }
                        MediationInitializer.getInstance().addMediationInitializationListener(this);
                        MediationInitializer.getInstance().init(activity, appKey, this.mUserId, adUnits);
                    } else {
                        if (this.mAdUnitsToInitialize.contains(AD_UNIT.REWARDED_VIDEO)) {
                            this.mListenersWrapper.onRewardedVideoAvailabilityChanged(false);
                        }
                        if (this.mAdUnitsToInitialize.contains(AD_UNIT.OFFERWALL)) {
                            this.mListenersWrapper.onOfferwallAvailable(false, validationResultAppKey.getIronSourceError());
                        }
                        IronSourceLoggerManager.getLogger().log(IronSourceTag.API, validationResultAppKey.getIronSourceError().toString(), 1);
                    }
                }
            } else if (adUnits != null) {
                attachAdUnits(isDemandOnlyInit, adUnits);
            } else {
                this.mLoggerManager.log(IronSourceTag.API, "Multiple calls to init without ad units are not allowed", 3);
            }
        }
    }

    public synchronized void initISDemandOnly(Activity activity, String appKey, AD_UNIT... adUnits) {
        ArrayList<AD_UNIT> validAdUnitsList = new ArrayList();
        if (adUnits == null) {
            this.mLoggerManager.log(IronSourceTag.API, "Cannot initialized demand only mode: No ad units selected", 3);
        } else if (adUnits.length <= 0) {
            this.mLoggerManager.log(IronSourceTag.API, "Cannot initialized demand only mode: No ad units selected", 3);
        } else {
            for (AD_UNIT adUnit : adUnits) {
                if (adUnit.equals(AD_UNIT.BANNER) || adUnit.equals(AD_UNIT.OFFERWALL)) {
                    this.mLoggerManager.log(IronSourceTag.API, adUnit + " ad unit cannot be initialized in demand only mode", 3);
                } else {
                    if (adUnit.equals(AD_UNIT.INTERSTITIAL)) {
                        if (this.mDidInitInterstitial) {
                            this.mLoggerManager.log(IronSourceTag.API, adUnit + " ad unit has already been initialized", 3);
                        } else {
                            this.mDidInitInterstitial = true;
                            this.isDemandOnlyInterstitial = true;
                            this.mInterstitialManager.mIsInISDemandOnlyMode = true;
                            if (!validAdUnitsList.contains(adUnit)) {
                                validAdUnitsList.add(adUnit);
                            }
                        }
                    }
                    if (adUnit.equals(AD_UNIT.REWARDED_VIDEO)) {
                        if (this.mDidInitRewardedVideo) {
                            this.mLoggerManager.log(IronSourceTag.API, adUnit + " ad unit has already been initialized", 3);
                        } else {
                            this.mDidInitRewardedVideo = true;
                            this.isDemandOnlyRewardedVideo = true;
                            this.mRewardedVideoManager.mIsInISDemandOnlyMode = true;
                            if (!validAdUnitsList.contains(adUnit)) {
                                validAdUnitsList.add(adUnit);
                            }
                        }
                    }
                }
            }
            if (validAdUnitsList.size() > 0) {
                init(activity, appKey, true, (AD_UNIT[]) validAdUnitsList.toArray(new AD_UNIT[validAdUnitsList.size()]));
            }
        }
    }

    private synchronized void attachAdUnits(boolean isDemandOnlyInit, AD_UNIT... adUnits) {
        int i = 0;
        synchronized (this) {
            int length;
            AD_UNIT adUnit;
            for (AD_UNIT adUnit2 : adUnits) {
                if (adUnit2.equals(AD_UNIT.INTERSTITIAL)) {
                    this.mDidInitInterstitial = true;
                } else if (adUnit2.equals(AD_UNIT.BANNER)) {
                    this.mDidInitBanner = true;
                }
            }
            if (MediationInitializer.getInstance().getCurrentInitStatus() == EInitStatus.INIT_FAILED) {
                try {
                    if (this.mListenersWrapper != null) {
                        length = adUnits.length;
                        while (i < length) {
                            adUnit2 = adUnits[i];
                            if (!this.mAdUnitsToInitialize.contains(adUnit2)) {
                                notifyPublisherAboutInitFailed(adUnit2, true);
                            }
                            i++;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (!this.mInitSucceeded) {
                data = IronSourceUtils.getMediationAdditionalData(isDemandOnlyInit);
                shouldSentInstanceEvent = false;
                length = adUnits.length;
                while (i < length) {
                    adUnit2 = adUnits[i];
                    if (this.mAdUnitsToInitialize.contains(adUnit2)) {
                        this.mLoggerManager.log(IronSourceTag.API, adUnit2 + " ad unit has started initializing.", 3);
                    } else {
                        shouldSentInstanceEvent = true;
                        this.mAdUnitsToInitialize.add(adUnit2);
                        this.mRequestedAdUnits.add(adUnit2);
                        try {
                            data.put(adUnit2.toString(), true);
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                    i++;
                }
                if (shouldSentInstanceEvent) {
                    try {
                        r5 = RequestParameters.SESSION_DEPTH;
                        length = this.mInitCounter + 1;
                        this.mInitCounter = length;
                        data.put(r5, length);
                    } catch (Exception e22) {
                        e22.printStackTrace();
                    }
                    RewardedVideoEventsManager.getInstance().log(new EventData(14, data));
                }
            } else if (this.mInitiatedAdUnits != null) {
                data = IronSourceUtils.getMediationAdditionalData(isDemandOnlyInit);
                shouldSentInstanceEvent = false;
                length = adUnits.length;
                while (i < length) {
                    adUnit2 = adUnits[i];
                    if (this.mAdUnitsToInitialize.contains(adUnit2)) {
                        this.mLoggerManager.log(IronSourceTag.API, adUnit2 + " ad unit has already been initialized", 3);
                    } else {
                        shouldSentInstanceEvent = true;
                        this.mAdUnitsToInitialize.add(adUnit2);
                        this.mRequestedAdUnits.add(adUnit2);
                        try {
                            data.put(adUnit2.toString(), true);
                        } catch (Exception e222) {
                            e222.printStackTrace();
                        }
                        if (this.mInitiatedAdUnits == null || !this.mInitiatedAdUnits.contains(adUnit2)) {
                            notifyPublisherAboutInitFailed(adUnit2, false);
                        } else {
                            startAdUnit(adUnit2);
                        }
                    }
                    i++;
                }
                if (shouldSentInstanceEvent) {
                    try {
                        r5 = RequestParameters.SESSION_DEPTH;
                        length = this.mInitCounter + 1;
                        this.mInitCounter = length;
                        data.put(r5, length);
                    } catch (Exception e2222) {
                        e2222.printStackTrace();
                    }
                    RewardedVideoEventsManager.getInstance().log(new EventData(14, data));
                }
            }
        }
        return;
    }

    public void onInitSuccess(List<AD_UNIT> adUnits, boolean revived) {
        this.mInitiatedAdUnits = adUnits;
        this.mInitSucceeded = true;
        this.mLoggerManager.log(IronSourceTag.API, "onInitSuccess()", 1);
        IronSourceUtils.sendAutomationLog("init success");
        if (revived) {
            JSONObject data = IronSourceUtils.getMediationAdditionalData(false);
            try {
                data.put("revived", true);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                RewardedVideoEventsManager.getInstance().log(new EventData(114, data));
            } catch (Exception e2) {
                e2.printStackTrace();
                return;
            }
        }
        InterstitialEventsManager.getInstance().triggerEventsSend();
        RewardedVideoEventsManager.getInstance().triggerEventsSend();
        for (AD_UNIT adUnit : AD_UNIT.values()) {
            if (this.mAdUnitsToInitialize.contains(adUnit)) {
                if (adUnits.contains(adUnit)) {
                    startAdUnit(adUnit);
                } else {
                    notifyPublisherAboutInitFailed(adUnit, false);
                }
            }
        }
    }

    private void startAdUnit(AD_UNIT adUnit) {
        switch (adUnit) {
            case REWARDED_VIDEO:
                startRewardedVideo();
                return;
            case INTERSTITIAL:
                startInterstitial();
                return;
            case OFFERWALL:
                this.mOfferwallManager.initOfferwall(this.mActivity, getIronSourceAppKey(), getIronSourceUserId());
                return;
            case BANNER:
                startBanner();
                return;
            default:
                return;
        }
    }

    private void startRewardedVideo() {
        if (this.isDemandOnlyRewardedVideo) {
            this.mLoggerManager.log(IronSourceTag.INTERNAL, "Rewarded Video started in Demand Only mode", 0);
        }
        int rewardedVideoTimeout = this.mCurrentServerResponse.getConfigurations().getRewardedVideoConfigurations().getRewardedVideoAdaptersSmartLoadTimeout();
        for (int i = 0; i < this.mCurrentServerResponse.getProviderOrder().getRewardedVideoProviderOrder().size(); i++) {
            ProviderSettings providerSettings;
            String provider = (String) this.mCurrentServerResponse.getProviderOrder().getRewardedVideoProviderOrder().get(i);
            if (!TextUtils.isEmpty(provider)) {
                providerSettings = this.mCurrentServerResponse.getProviderSettingsHolder().getProviderSettings(provider);
                if (providerSettings != null) {
                    RewardedVideoSmash smash = new RewardedVideoSmash(providerSettings, rewardedVideoTimeout);
                    if (validateSmash(smash)) {
                        smash.setRewardedVideoManagerListener(this.mRewardedVideoManager);
                        smash.setProviderPriority(i + 1);
                        this.mRewardedVideoManager.addSmashToArray(smash);
                    }
                }
            }
        }
        if (this.mRewardedVideoManager.mSmashArray.size() > 0) {
            this.mRewardedVideoManager.setIsUltraEventsEnabled(this.mCurrentServerResponse.getConfigurations().getRewardedVideoConfigurations().getRewardedVideoEventsConfigurations().isUltraEventsEnabled());
            this.mRewardedVideoManager.setSmartLoadAmount(this.mCurrentServerResponse.getConfigurations().getRewardedVideoConfigurations().getRewardedVideoAdaptersSmartLoadAmount());
            String backfillProvider = this.mCurrentServerResponse.getRVBackFillProvider();
            if (!TextUtils.isEmpty(backfillProvider)) {
                providerSettings = this.mCurrentServerResponse.getProviderSettingsHolder().getProviderSettings(backfillProvider);
                if (providerSettings != null) {
                    RewardedVideoSmash backfillSmash = new RewardedVideoSmash(providerSettings, rewardedVideoTimeout);
                    if (validateSmash(backfillSmash)) {
                        backfillSmash.setRewardedVideoManagerListener(this.mRewardedVideoManager);
                        this.mRewardedVideoManager.setBackfillSmash(backfillSmash);
                    }
                }
            }
            String premiumProvider = this.mCurrentServerResponse.getRVPremiumProvider();
            if (!TextUtils.isEmpty(premiumProvider)) {
                providerSettings = this.mCurrentServerResponse.getProviderSettingsHolder().getProviderSettings(premiumProvider);
                if (providerSettings != null) {
                    RewardedVideoSmash premiumSmash = new RewardedVideoSmash(providerSettings, rewardedVideoTimeout);
                    if (validateSmash(premiumSmash)) {
                        premiumSmash.setRewardedVideoManagerListener(this.mRewardedVideoManager);
                        this.mRewardedVideoManager.setPremiumSmash(premiumSmash);
                    }
                }
            }
            this.mRewardedVideoManager.initRewardedVideo(this.mActivity, getIronSourceAppKey(), getIronSourceUserId());
            return;
        }
        notifyPublisherAboutInitFailed(AD_UNIT.REWARDED_VIDEO, false);
    }

    private void startInterstitial() {
        if (this.isDemandOnlyInterstitial) {
            this.mLoggerManager.log(IronSourceTag.INTERNAL, "Interstitial started in Demand Only mode", 0);
        }
        int interstitialTimeout = this.mCurrentServerResponse.getConfigurations().getInterstitialConfigurations().getInterstitialAdaptersSmartLoadTimeout();
        for (int i = 0; i < this.mCurrentServerResponse.getProviderOrder().getInterstitialProviderOrder().size(); i++) {
            String provider = (String) this.mCurrentServerResponse.getProviderOrder().getInterstitialProviderOrder().get(i);
            if (!TextUtils.isEmpty(provider)) {
                ProviderSettings providerSettings = this.mCurrentServerResponse.getProviderSettingsHolder().getProviderSettings(provider);
                if (providerSettings != null) {
                    InterstitialSmash smash = new InterstitialSmash(providerSettings, interstitialTimeout);
                    if (validateSmash(smash)) {
                        smash.setInterstitialManagerListener(this.mInterstitialManager);
                        smash.setProviderPriority(i + 1);
                        this.mInterstitialManager.addSmashToArray(smash);
                    }
                }
            }
        }
        if (this.mInterstitialManager.mSmashArray.size() > 0) {
            this.mInterstitialManager.setSmartLoadAmount(this.mCurrentServerResponse.getConfigurations().getInterstitialConfigurations().getInterstitialAdaptersSmartLoadAmount());
            this.mInterstitialManager.initInterstitial(this.mActivity, getIronSourceAppKey(), getIronSourceUserId());
            return;
        }
        notifyPublisherAboutInitFailed(AD_UNIT.INTERSTITIAL, false);
    }

    private void startBanner() {
        long bannerTimeout = this.mCurrentServerResponse.getConfigurations().getBannerConfigurations().getBannerAdaptersSmartLoadTimeout();
        int bannerInterval = this.mCurrentServerResponse.getConfigurations().getBannerConfigurations().getBannerRefreshInterval();
        ArrayList<ProviderSettings> adapterConfigs = new ArrayList();
        for (int i = 0; i < this.mCurrentServerResponse.getProviderOrder().getBannerProviderOrder().size(); i++) {
            String provider = (String) this.mCurrentServerResponse.getProviderOrder().getBannerProviderOrder().get(i);
            if (!TextUtils.isEmpty(provider)) {
                ProviderSettings providerSettings = this.mCurrentServerResponse.getProviderSettingsHolder().getProviderSettings(provider);
                if (providerSettings != null) {
                    adapterConfigs.add(providerSettings);
                }
            }
        }
        this.mBannerManager.initBannerManager(adapterConfigs, this.mActivity, getIronSourceAppKey(), getIronSourceUserId(), bannerTimeout, bannerInterval);
        if (this.mIsBnLoadBeforeInitCompleted) {
            this.mIsBnLoadBeforeInitCompleted = false;
            loadBanner(this.mBnLayoutToLoad, this.mBnPlacementToLoad);
            this.mBnLayoutToLoad = null;
            this.mBnPlacementToLoad = null;
        }
    }

    private boolean validateSmash(AbstractSmash smash) {
        return smash.getMaxAdsPerIteration() >= 1 && smash.getMaxAdsPerSession() >= 1;
    }

    public void onInitFailed(String reason) {
        try {
            this.mLoggerManager.log(IronSourceTag.API, "onInitFailed(reason:" + reason + ")", 1);
            if (this.mListenersWrapper != null) {
                for (AD_UNIT adUnit : this.mAdUnitsToInitialize) {
                    notifyPublisherAboutInitFailed(adUnit, true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onStillInProgressAfter15Secs() {
        if (this.mIsBnLoadBeforeInitCompleted) {
            this.mIsBnLoadBeforeInitCompleted = false;
            BannerCallbackThrottler.getInstance().sendBannerAdLoadFailed(this.mBnLayoutToLoad, new IronSourceError(IronSourceError.ERROR_BN_LOAD_WHILE_LONG_INITIATION, "init had failed"));
            this.mBnLayoutToLoad = null;
            this.mBnPlacementToLoad = null;
        }
    }

    private void notifyPublisherAboutInitFailed(AD_UNIT adUnit, boolean isInitFailed) {
        switch (adUnit) {
            case REWARDED_VIDEO:
                if (isInitFailed || isRewardedVideoConfigurationsReady() || this.mRequestedAdUnits.contains(adUnit)) {
                    this.mListenersWrapper.onRewardedVideoAvailabilityChanged(false);
                    return;
                }
                return;
            case OFFERWALL:
                if (isInitFailed || isOfferwallConfigurationsReady() || this.mRequestedAdUnits.contains(adUnit)) {
                    this.mListenersWrapper.onOfferwallAvailable(false);
                    return;
                }
                return;
            case BANNER:
                if (this.mIsBnLoadBeforeInitCompleted) {
                    this.mIsBnLoadBeforeInitCompleted = false;
                    BannerCallbackThrottler.getInstance().sendBannerAdLoadFailed(this.mBnLayoutToLoad, new IronSourceError(IronSourceError.ERROR_BN_INIT_FAILED_AFTER_LOAD, "Init had failed"));
                    this.mBnLayoutToLoad = null;
                    this.mBnPlacementToLoad = null;
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void prepareEventManagers(Activity activity) {
        if (this.mEventManagersInit != null && this.mEventManagersInit.compareAndSet(false, true)) {
            SuperLooper.getLooper().post(new GeneralPropertiesWorker(activity.getApplicationContext()));
            InterstitialEventsManager.getInstance().start(activity.getApplicationContext(), this.mIronSegment);
            RewardedVideoEventsManager.getInstance().start(activity.getApplicationContext(), this.mIronSegment);
        }
    }

    synchronized void addToRVAdaptersList(AbstractAdapter adapter) {
        if (!(this.mRewardedVideoAdaptersList == null || adapter == null || this.mRewardedVideoAdaptersList.contains(adapter))) {
            this.mRewardedVideoAdaptersList.add(adapter);
        }
    }

    synchronized void addToISAdaptersList(AbstractAdapter adapter) {
        if (!(this.mInterstitialAdaptersList == null || adapter == null || this.mInterstitialAdaptersList.contains(adapter))) {
            this.mInterstitialAdaptersList.add(adapter);
        }
    }

    synchronized void addToBannerAdaptersList(AbstractAdapter adapter) {
        if (!(this.mBannerAdaptersList == null || adapter == null || this.mBannerAdaptersList.contains(adapter))) {
            this.mBannerAdaptersList.add(adapter);
        }
    }

    synchronized void addOWAdapter(AbstractAdapter adapter) {
        this.mOfferwallAdapter = adapter;
    }

    synchronized AbstractAdapter getExistingAdapter(String providerName) {
        AbstractAdapter adapter;
        try {
            Iterator it;
            if (this.mRewardedVideoAdaptersList != null) {
                it = this.mRewardedVideoAdaptersList.iterator();
                while (it.hasNext()) {
                    adapter = (AbstractAdapter) it.next();
                    if (adapter.getProviderName().equals(providerName)) {
                        break;
                    }
                }
            }
            if (this.mInterstitialAdaptersList != null) {
                it = this.mInterstitialAdaptersList.iterator();
                while (it.hasNext()) {
                    adapter = (AbstractAdapter) it.next();
                    if (adapter.getProviderName().equals(providerName)) {
                        break;
                    }
                }
            }
            if (this.mBannerAdaptersList != null) {
                it = this.mBannerAdaptersList.iterator();
                while (it.hasNext()) {
                    adapter = (AbstractAdapter) it.next();
                    if (adapter.getProviderName().equals(providerName)) {
                        break;
                    }
                }
            }
            if (this.mOfferwallAdapter != null && this.mOfferwallAdapter.getProviderName().equals(providerName)) {
                adapter = this.mOfferwallAdapter;
            }
        } catch (Exception e) {
            this.mLoggerManager.log(IronSourceTag.INTERNAL, "getExistingAdapter exception: " + e, 1);
        }
        adapter = null;
        return adapter;
    }

    private void initializeManagers() {
        this.mLoggerManager = IronSourceLoggerManager.getLogger(0);
        this.mPublisherLogger = new PublisherLogger(null, 1);
        this.mLoggerManager.addLogger(this.mPublisherLogger);
        this.mListenersWrapper = new ListenersWrapper();
        this.mRewardedVideoManager = new RewardedVideoManager();
        this.mRewardedVideoManager.setRewardedVideoListener(this.mListenersWrapper);
        this.mRewardedVideoManager.setISDemandOnlyRewardedVideoListener(this.mListenersWrapper);
        this.mInterstitialManager = new InterstitialManager();
        this.mInterstitialManager.setInterstitialListener(this.mListenersWrapper);
        this.mInterstitialManager.setRewardedInterstitialListener(this.mListenersWrapper);
        this.mInterstitialManager.setISDemandOnlyInterstitialListener(this.mListenersWrapper);
        this.mOfferwallManager = new OfferwallManager();
        this.mOfferwallManager.setInternalOfferwallListener(this.mListenersWrapper);
        this.mBannerManager = new BannerManager();
    }

    public void onResume(Activity activity) {
        String logMessage = "onResume()";
        try {
            this.mActivity = activity;
            this.mLoggerManager.log(IronSourceTag.API, logMessage, 1);
            if (this.mRewardedVideoManager != null) {
                this.mRewardedVideoManager.onResume(activity);
            }
            if (this.mInterstitialManager != null) {
                this.mInterstitialManager.onResume(activity);
            }
            if (this.mBannerManager != null) {
                this.mBannerManager.onResume(activity);
            }
        } catch (Throwable e) {
            this.mLoggerManager.logException(IronSourceTag.API, logMessage, e);
        }
    }

    public void onPause(Activity activity) {
        String logMessage = "onPause()";
        try {
            this.mLoggerManager.log(IronSourceTag.API, logMessage, 1);
            if (this.mRewardedVideoManager != null) {
                this.mRewardedVideoManager.onPause(activity);
            }
            if (this.mInterstitialManager != null) {
                this.mInterstitialManager.onPause(activity);
            }
            if (this.mBannerManager != null) {
                this.mBannerManager.onPause(activity);
            }
        } catch (Throwable e) {
            this.mLoggerManager.logException(IronSourceTag.API, logMessage, e);
        }
    }

    public synchronized void setAge(int age) {
        try {
            this.mLoggerManager.log(IronSourceTag.API, this.TAG + ":setAge(age:" + age + ")", 1);
            ConfigValidationResult result = new ConfigValidationResult();
            validateAge(age, result);
            if (result.isValid()) {
                this.mUserAge = Integer.valueOf(age);
            } else {
                IronSourceLoggerManager.getLogger().log(IronSourceTag.API, result.getIronSourceError().toString(), 2);
            }
        } catch (Exception e) {
            this.mLoggerManager.logException(IronSourceTag.API, this.TAG + ":setAge(age:" + age + ")", e);
        }
    }

    public synchronized void setGender(String gender) {
        try {
            this.mLoggerManager.log(IronSourceTag.API, this.TAG + ":setGender(gender:" + gender + ")", 1);
            ConfigValidationResult result = new ConfigValidationResult();
            validateGender(gender, result);
            if (result.isValid()) {
                this.mUserGender = gender;
            } else {
                IronSourceLoggerManager.getLogger().log(IronSourceTag.API, result.getIronSourceError().toString(), 2);
            }
        } catch (Exception e) {
            this.mLoggerManager.logException(IronSourceTag.API, this.TAG + ":setGender(gender:" + gender + ")", e);
        }
    }

    public void setMediationSegment(String segment) {
        try {
            this.mLoggerManager.log(IronSourceTag.API, this.TAG + ":setMediationSegment(segment:" + segment + ")", 1);
            ConfigValidationResult result = new ConfigValidationResult();
            validateSegment(segment, result);
            if (result.isValid()) {
                this.mSegment = segment;
            } else {
                IronSourceLoggerManager.getLogger().log(IronSourceTag.API, result.getIronSourceError().toString(), 2);
            }
        } catch (Exception e) {
            this.mLoggerManager.logException(IronSourceTag.API, this.TAG + ":setMediationSegment(segment:" + segment + ")", e);
        }
    }

    public void setSegment(IronSourceSegment segment) {
        if (MediationInitializer.getInstance().getCurrentInitStatus() == EInitStatus.INIT_IN_PROGRESS || MediationInitializer.getInstance().getCurrentInitStatus() == EInitStatus.INITIATED) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.API, "Segments must be set prior to Init. Setting a segment after the init will be ignored", 0);
        } else {
            this.mIronSegment = segment;
        }
    }

    public boolean setDynamicUserId(String dynamicUserId) {
        try {
            this.mLoggerManager.log(IronSourceTag.API, this.TAG + ":setDynamicUserId(dynamicUserId:" + dynamicUserId + ")", 1);
            ConfigValidationResult result = new ConfigValidationResult();
            validateDynamicUserId(dynamicUserId, result);
            if (result.isValid()) {
                this.mDynamicUserId = dynamicUserId;
                return true;
            }
            IronSourceLoggerManager.getLogger().log(IronSourceTag.API, result.getIronSourceError().toString(), 2);
            return false;
        } catch (Exception e) {
            this.mLoggerManager.logException(IronSourceTag.API, this.TAG + ":setDynamicUserId(dynamicUserId:" + dynamicUserId + ")", e);
            return false;
        }
    }

    public void setAdaptersDebug(boolean enabled) {
        IronSourceLoggerManager.getLogger().setAdaptersDebug(enabled);
    }

    public void setMediationType(String mediationType) {
        try {
            this.mLoggerManager.log(IronSourceTag.INTERNAL, this.TAG + ":setMediationType(mediationType:" + mediationType + ")", 1);
            if (validateLength(mediationType, 1, 64) && validateAlphanumeric(mediationType)) {
                this.mMediationType = mediationType;
                return;
            }
            this.mLoggerManager.log(IronSourceTag.INTERNAL, " mediationType value is invalid - should be alphanumeric and 1-64 chars in length", 1);
        } catch (Exception e) {
            this.mLoggerManager.logException(IronSourceTag.API, this.TAG + ":setMediationType(mediationType:" + mediationType + ")", e);
        }
    }

    public synchronized Integer getAge() {
        return this.mUserAge;
    }

    public synchronized String getGender() {
        return this.mUserGender;
    }

    synchronized String getMediationSegment() {
        return this.mSegment;
    }

    synchronized String getDynamicUserId() {
        return this.mDynamicUserId;
    }

    synchronized Map<String, String> getRvServerParams() {
        return this.mRvServerParams;
    }

    public synchronized String getMediationType() {
        return this.mMediationType;
    }

    public void initRewardedVideo(Activity activity, String appKey, String userId) {
    }

    public void initInterstitial(Activity activity, String appKey, String userId) {
    }

    public void initOfferwall(Activity activity, String appKey, String userId) {
    }

    public void showRewardedVideo() {
        String logMessage = "showRewardedVideo()";
        this.mLoggerManager.log(IronSourceTag.API, logMessage, 1);
        try {
            if (this.isDemandOnlyRewardedVideo) {
                this.mLoggerManager.log(IronSourceTag.API, "Rewarded Video was initialized in demand only mode. Use showISDemandOnlyRewardedVideo instead", 3);
            } else if (isRewardedVideoConfigurationsReady()) {
                Placement defaultPlacement = this.mCurrentServerResponse.getConfigurations().getRewardedVideoConfigurations().getDefaultRewardedVideoPlacement();
                if (defaultPlacement != null) {
                    showRewardedVideo(defaultPlacement.getPlacementName());
                }
            } else {
                this.mListenersWrapper.onRewardedVideoAdShowFailed(ErrorBuilder.buildInitFailedError("showRewardedVideo can't be called before the Rewarded Video ad unit initialization completed successfully", IronSourceConstants.REWARDED_VIDEO_AD_UNIT));
            }
        } catch (Exception e) {
            this.mLoggerManager.logException(IronSourceTag.API, logMessage, e);
            this.mListenersWrapper.onRewardedVideoAdShowFailed(ErrorBuilder.buildInitFailedError("showRewardedVideo can't be called before the Rewarded Video ad unit initialization completed successfully", IronSourceConstants.REWARDED_VIDEO_AD_UNIT));
        }
    }

    public void showRewardedVideo(String placementName) {
        String logMessage = "showRewardedVideo(" + placementName + ")";
        this.mLoggerManager.log(IronSourceTag.API, logMessage, 1);
        try {
            if (this.isDemandOnlyRewardedVideo) {
                this.mLoggerManager.log(IronSourceTag.API, "Rewarded Video was initialized in demand only mode. Use showISDemandOnlyRewardedVideo instead", 3);
            } else if (isRewardedVideoConfigurationsReady()) {
                Placement placement = getPlacementToShowWithEvent(placementName);
                if (placement != null) {
                    JSONObject data = IronSourceUtils.getMediationAdditionalData(false);
                    try {
                        data.put(VungleActivity.PLACEMENT_EXTRA, placement.getPlacementName());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    RewardedVideoEventsManager.getInstance().log(new EventData(2, data));
                    this.mRewardedVideoManager.setCurrentPlacement(placement);
                    this.mRewardedVideoManager.showRewardedVideo(placement.getPlacementName());
                }
            } else {
                this.mListenersWrapper.onRewardedVideoAdShowFailed(ErrorBuilder.buildInitFailedError("showRewardedVideo can't be called before the Rewarded Video ad unit initialization completed successfully", IronSourceConstants.REWARDED_VIDEO_AD_UNIT));
            }
        } catch (Exception e2) {
            this.mLoggerManager.logException(IronSourceTag.API, logMessage, e2);
            this.mListenersWrapper.onRewardedVideoAdShowFailed(ErrorBuilder.buildInitFailedError("showRewardedVideo can't be called before the Rewarded Video ad unit initialization completed successfully", IronSourceConstants.REWARDED_VIDEO_AD_UNIT));
        }
    }

    public boolean isRewardedVideoAvailable() {
        boolean isAvailable = false;
        try {
            if (this.isDemandOnlyRewardedVideo) {
                this.mLoggerManager.log(IronSourceTag.API, "Rewarded Video was initialized in demand only mode. Use isISDemandOnlyRewardedVideoAvailable instead", 3);
                return false;
            }
            isAvailable = this.mRewardedVideoManager.isRewardedVideoAvailable();
            JSONObject data = IronSourceUtils.getMediationAdditionalData(false);
            data.put("status", String.valueOf(isAvailable));
            RewardedVideoEventsManager.getInstance().log(new EventData(18, data));
            this.mLoggerManager.log(IronSourceTag.API, "isRewardedVideoAvailable():" + isAvailable, 1);
            return isAvailable;
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Throwable e2) {
            this.mLoggerManager.log(IronSourceTag.API, "isRewardedVideoAvailable():" + isAvailable, 1);
            this.mLoggerManager.logException(IronSourceTag.API, "isRewardedVideoAvailable()", e2);
            isAvailable = false;
        }
    }

    public void setRewardedVideoListener(RewardedVideoListener listener) {
        if (listener == null) {
            this.mLoggerManager.log(IronSourceTag.API, "setRewardedVideoListener(RVListener:null)", 1);
        } else {
            this.mLoggerManager.log(IronSourceTag.API, "setRewardedVideoListener(RVListener)", 1);
        }
        this.mListenersWrapper.setRewardedVideoListener(listener);
    }

    public void setRewardedVideoServerParameters(Map<String, String> params) {
        if (params != null) {
            try {
                if (params.size() != 0) {
                    this.mLoggerManager.log(IronSourceTag.API, this.TAG + ":setRewardedVideoServerParameters(params:" + params.toString() + ")", 1);
                    this.mRvServerParams = new HashMap(params);
                }
            } catch (Exception e) {
                this.mLoggerManager.logException(IronSourceTag.API, this.TAG + ":setRewardedVideoServerParameters(params:" + params.toString() + ")", e);
            }
        }
    }

    public void clearRewardedVideoServerParameters() {
        this.mRvServerParams = null;
    }

    public void showISDemandOnlyRewardedVideo(String instanceId) {
        String logMessage = "showISDemandOnlyRewardedVideo(" + instanceId + ")";
        this.mLoggerManager.log(IronSourceTag.API, logMessage, 1);
        try {
            if (!this.isDemandOnlyRewardedVideo) {
                this.mLoggerManager.log(IronSourceTag.API, "Rewarded Video was initialized in mediation mode. Use showRewardedVideo instead", 3);
            } else if (isRewardedVideoConfigurationsReady()) {
                Placement defaultPlacement = this.mCurrentServerResponse.getConfigurations().getRewardedVideoConfigurations().getDefaultRewardedVideoPlacement();
                if (defaultPlacement != null) {
                    showISDemandOnlyRewardedVideo(instanceId, defaultPlacement.getPlacementName());
                }
            } else {
                this.mListenersWrapper.onRewardedVideoAdShowFailed(instanceId, ErrorBuilder.buildInitFailedError("showISDemandOnlyRewardedVideo can't be called before the Rewarded Video ad unit initialization completed successfully", IronSourceConstants.REWARDED_VIDEO_AD_UNIT));
            }
        } catch (Exception e) {
            this.mLoggerManager.logException(IronSourceTag.API, logMessage, e);
            this.mListenersWrapper.onRewardedVideoAdShowFailed(instanceId, ErrorBuilder.buildInitFailedError("showISDemandOnlyRewardedVideo can't be called before the Rewarded Video ad unit initialization completed successfully", IronSourceConstants.REWARDED_VIDEO_AD_UNIT));
        }
    }

    public void showISDemandOnlyRewardedVideo(String instanceId, String placementName) {
        String logMessage = "showISDemandOnlyRewardedVideo(" + instanceId + (placementName == null ? ")" : " , " + placementName + ")");
        this.mLoggerManager.log(IronSourceTag.API, logMessage, 1);
        try {
            if (!this.isDemandOnlyRewardedVideo) {
                this.mLoggerManager.log(IronSourceTag.API, "Rewarded Video was initialized in mediation mode. Use showRewardedVideo instead", 3);
            } else if (isRewardedVideoConfigurationsReady()) {
                Placement placement = getPlacementToShowWithEvent(placementName);
                if (placement != null) {
                    JSONObject data = IronSourceUtils.getMediationAdditionalData(true);
                    try {
                        data.put(VungleActivity.PLACEMENT_EXTRA, placement.getPlacementName());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    RewardedVideoEventsManager.getInstance().log(new EventData(2, data));
                    this.mRewardedVideoManager.setCurrentPlacement(placement);
                    this.mRewardedVideoManager.showRewardedVideo(instanceId, placement.getPlacementName());
                }
            } else {
                this.mListenersWrapper.onRewardedVideoAdShowFailed(instanceId, ErrorBuilder.buildInitFailedError("showISDemandOnlyRewardedVideo can't be called before the Rewarded Video ad unit initialization completed successfully", IronSourceConstants.REWARDED_VIDEO_AD_UNIT));
            }
        } catch (Exception e2) {
            this.mLoggerManager.logException(IronSourceTag.API, logMessage, e2);
            this.mListenersWrapper.onRewardedVideoAdShowFailed(instanceId, ErrorBuilder.buildInitFailedError("showISDemandOnlyRewardedVideo can't be called before the Rewarded Video ad unit initialization completed successfully", IronSourceConstants.REWARDED_VIDEO_AD_UNIT));
        }
    }

    public boolean isISDemandOnlyRewardedVideoAvailable(String instanceId) {
        boolean isAvailable = false;
        try {
            if (this.isDemandOnlyRewardedVideo) {
                isAvailable = this.mRewardedVideoManager.isRewardedVideoAvailable(instanceId);
                JSONObject data = IronSourceUtils.getMediationAdditionalData(true);
                data.put("status", String.valueOf(isAvailable));
                RewardedVideoEventsManager.getInstance().log(new EventData(18, data));
                this.mLoggerManager.log(IronSourceTag.API, "isISDemandOnlyRewardedVideoAvailable():" + isAvailable, 1);
                return isAvailable;
            }
            this.mLoggerManager.log(IronSourceTag.API, "Rewarded Video was initialized in mediation mode. Use isRewardedVideoAvailable instead", 3);
            return false;
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Throwable e2) {
            this.mLoggerManager.log(IronSourceTag.API, "isISDemandOnlyRewardedVideoAvailable():" + isAvailable, 1);
            this.mLoggerManager.logException(IronSourceTag.API, "isISDemandOnlyRewardedVideoAvailable()", e2);
            isAvailable = false;
        }
    }

    void setISDemandOnlyRewardedVideoListener(ISDemandOnlyRewardedVideoListener listener) {
        if (listener == null) {
            this.mLoggerManager.log(IronSourceTag.API, "setISDemandOnlyRewardedVideoListener(ISDemandOnlyRewardedVideoListener:null)", 1);
        } else {
            this.mLoggerManager.log(IronSourceTag.API, "setISDemandOnlyRewardedVideoListener(ISDemandOnlyRewardedVideoListener)", 1);
        }
        this.mListenersWrapper.setISDemandOnlyRewardedVideoListener(listener);
    }

    private boolean isRewardedVideoConfigurationsReady() {
        return (this.mCurrentServerResponse == null || this.mCurrentServerResponse.getConfigurations() == null || this.mCurrentServerResponse.getConfigurations().getRewardedVideoConfigurations() == null) ? false : true;
    }

    private Placement getPlacementToShowWithEvent(String placementName) {
        Placement placement = this.mCurrentServerResponse.getConfigurations().getRewardedVideoConfigurations().getRewardedVideoPlacement(placementName);
        if (placement == null) {
            this.mLoggerManager.log(IronSourceTag.API, "Placement is not valid, please make sure you are using the right placements, using the default placement.", 3);
            placement = this.mCurrentServerResponse.getConfigurations().getRewardedVideoConfigurations().getDefaultRewardedVideoPlacement();
            if (placement == null) {
                this.mLoggerManager.log(IronSourceTag.API, "Default placement was not found, please make sure you are using the right placements.", 3);
                return null;
            }
        }
        String cappedMessage = getCappingMessage(placement.getPlacementName(), CappingManager.isPlacementCapped(this.mActivity, placement));
        if (TextUtils.isEmpty(cappedMessage)) {
            return placement;
        }
        this.mLoggerManager.log(IronSourceTag.API, cappedMessage, 1);
        this.mListenersWrapper.onRewardedVideoAdShowFailed(ErrorBuilder.buildCappedPerPlacementError(IronSourceConstants.REWARDED_VIDEO_AD_UNIT, cappedMessage));
        return null;
    }

    public void loadInterstitial() {
        String logMessage = "loadInterstitial()";
        this.mLoggerManager.log(IronSourceTag.API, logMessage, 1);
        try {
            if (this.isDemandOnlyInterstitial) {
                this.mLoggerManager.log(IronSourceTag.API, "Interstitial was initialized in demand only mode. Use loadISDemandOnlyInterstitial instead", 3);
            } else if (this.mDidInitInterstitial) {
                this.mInterstitialManager.loadInterstitial();
            } else {
                this.mLoggerManager.log(IronSourceTag.API, "init() must be called before loadInterstitial()", 3);
            }
        } catch (Throwable e) {
            this.mLoggerManager.logException(IronSourceTag.API, logMessage, e);
        }
    }

    public void showInterstitial() {
        String logMessage = "showInterstitial()";
        this.mLoggerManager.log(IronSourceTag.API, logMessage, 1);
        try {
            if (this.isDemandOnlyInterstitial) {
                this.mLoggerManager.log(IronSourceTag.API, "Interstitial was initialized in demand only mode. Use showISDemandOnlyInterstitial instead", 3);
            } else if (isInterstitialConfigurationsReady()) {
                InterstitialPlacement defaultPlacement = this.mCurrentServerResponse.getConfigurations().getInterstitialConfigurations().getDefaultInterstitialPlacement();
                if (defaultPlacement != null) {
                    showInterstitial(defaultPlacement.getPlacementName());
                }
            } else {
                this.mListenersWrapper.onInterstitialAdShowFailed(ErrorBuilder.buildInitFailedError("showInterstitial can't be called before the Interstitial ad unit initialization completed successfully", "Interstitial"));
            }
        } catch (Exception e) {
            this.mLoggerManager.logException(IronSourceTag.API, logMessage, e);
            this.mListenersWrapper.onInterstitialAdShowFailed(ErrorBuilder.buildInitFailedError("showInterstitial can't be called before the Interstitial ad unit initialization completed successfully", "Interstitial"));
        }
    }

    public void showInterstitial(String placementName) {
        String logMessage = "showInterstitial(" + placementName + ")";
        this.mLoggerManager.log(IronSourceTag.API, logMessage, 1);
        try {
            if (this.isDemandOnlyInterstitial) {
                this.mLoggerManager.log(IronSourceTag.API, "Interstitial was initialized in demand only mode. Use showISDemandOnlyInterstitial instead", 3);
            } else if (isInterstitialConfigurationsReady()) {
                InterstitialPlacement placement = getInterstitialPlacementToShowWithEvent(placementName);
                if (placement != null) {
                    JSONObject data = IronSourceUtils.getMediationAdditionalData(false);
                    try {
                        data.put(VungleActivity.PLACEMENT_EXTRA, placement.getPlacementName());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    InterstitialEventsManager.getInstance().log(new EventData(23, data));
                    this.mInterstitialManager.setCurrentPlacement(placement);
                    this.mInterstitialManager.showInterstitial(placement.getPlacementName());
                }
            } else {
                this.mListenersWrapper.onInterstitialAdShowFailed(ErrorBuilder.buildInitFailedError("showInterstitial can't be called before the Interstitial ad unit initialization completed successfully", "Interstitial"));
            }
        } catch (Exception e2) {
            this.mLoggerManager.logException(IronSourceTag.API, logMessage, e2);
            this.mListenersWrapper.onInterstitialAdShowFailed(ErrorBuilder.buildInitFailedError("showInterstitial can't be called before the Interstitial ad unit initialization completed successfully", "Interstitial"));
        }
    }

    public boolean isInterstitialReady() {
        boolean isAvailable = false;
        try {
            if (this.isDemandOnlyInterstitial) {
                this.mLoggerManager.log(IronSourceTag.API, "Interstitial was initialized in demand only mode. Use isISDemandOnlyInterstitialReady instead", 3);
                return false;
            }
            isAvailable = this.mInterstitialManager.isInterstitialReady();
            JSONObject data = IronSourceUtils.getMediationAdditionalData(false);
            data.put("status", String.valueOf(isAvailable));
            InterstitialEventsManager.getInstance().log(new EventData(30, data));
            this.mLoggerManager.log(IronSourceTag.API, "isInterstitialReady():" + isAvailable, 1);
            return isAvailable;
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Throwable e2) {
            this.mLoggerManager.log(IronSourceTag.API, "isInterstitialReady():" + isAvailable, 1);
            this.mLoggerManager.logException(IronSourceTag.API, "isInterstitialReady()", e2);
            isAvailable = false;
        }
    }

    public void setInterstitialListener(InterstitialListener listener) {
        if (listener == null) {
            this.mLoggerManager.log(IronSourceTag.API, "setInterstitialListener(ISListener:null)", 1);
        } else {
            this.mLoggerManager.log(IronSourceTag.API, "setInterstitialListener(ISListener)", 1);
        }
        this.mListenersWrapper.setInterstitialListener(listener);
    }

    public void loadISDemandOnlyInterstitial(String instanceId) {
        String logMessage = "loadISDemandOnlyInterstitial(" + instanceId + ")";
        this.mLoggerManager.log(IronSourceTag.API, logMessage, 1);
        try {
            if (!this.isDemandOnlyInterstitial) {
                this.mLoggerManager.log(IronSourceTag.API, "Interstitial was initialized in mediation mode. Use loadInterstitial instead", 3);
            } else if (this.mDidInitInterstitial) {
                this.mInterstitialManager.loadInterstitial(instanceId);
            } else {
                this.mLoggerManager.log(IronSourceTag.API, "init() must be called before loadInterstitial()", 3);
            }
        } catch (Throwable e) {
            this.mLoggerManager.logException(IronSourceTag.API, logMessage, e);
        }
    }

    public void showISDemandOnlyInterstitial(String instanceId) {
        String logMessage = "showISDemandOnlyInterstitial(" + instanceId + ")";
        this.mLoggerManager.log(IronSourceTag.API, logMessage, 1);
        try {
            if (!this.isDemandOnlyInterstitial) {
                this.mLoggerManager.log(IronSourceTag.API, "Interstitial was initialized in mediation mode. Use showInterstitial instead", 3);
            } else if (isInterstitialConfigurationsReady()) {
                InterstitialPlacement defaultPlacement = this.mCurrentServerResponse.getConfigurations().getInterstitialConfigurations().getDefaultInterstitialPlacement();
                if (defaultPlacement != null) {
                    showISDemandOnlyInterstitial(instanceId, defaultPlacement.getPlacementName());
                }
            } else {
                this.mListenersWrapper.onInterstitialAdShowFailed(instanceId, ErrorBuilder.buildInitFailedError("showISDemandOnlyInterstitial can't be called before the Interstitial ad unit initialization completed successfully", "Interstitial"));
            }
        } catch (Exception e) {
            this.mLoggerManager.logException(IronSourceTag.API, logMessage, e);
            this.mListenersWrapper.onInterstitialAdShowFailed(instanceId, ErrorBuilder.buildInitFailedError("showISDemandOnlyInterstitial can't be called before the Interstitial ad unit initialization completed successfully", "Interstitial"));
        }
    }

    public void showISDemandOnlyInterstitial(String instanceId, String placementName) {
        String logMessage = "showISDemandOnlyInterstitial(" + instanceId + (placementName == null ? ")" : " , " + placementName + ")");
        this.mLoggerManager.log(IronSourceTag.API, logMessage, 1);
        try {
            if (!this.isDemandOnlyInterstitial) {
                this.mLoggerManager.log(IronSourceTag.API, "Interstitial was initialized in mediation mode. Use showInterstitial instead", 3);
            } else if (isInterstitialConfigurationsReady()) {
                InterstitialPlacement placement = getInterstitialPlacementToShowWithEvent(placementName);
                if (placement != null) {
                    JSONObject data = IronSourceUtils.getMediationAdditionalData(true);
                    try {
                        data.put(VungleActivity.PLACEMENT_EXTRA, placement.getPlacementName());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    InterstitialEventsManager.getInstance().log(new EventData(23, data));
                    this.mInterstitialManager.setCurrentPlacement(placement);
                    this.mInterstitialManager.showInterstitial(instanceId, placement.getPlacementName());
                }
            } else {
                this.mListenersWrapper.onInterstitialAdShowFailed(instanceId, ErrorBuilder.buildInitFailedError("showISDemandOnlyInterstitial can't be called before the Interstitial ad unit initialization completed successfully", "Interstitial"));
            }
        } catch (Exception e2) {
            this.mLoggerManager.logException(IronSourceTag.API, logMessage, e2);
            this.mListenersWrapper.onInterstitialAdShowFailed(instanceId, ErrorBuilder.buildInitFailedError("showISDemandOnlyInterstitial can't be called before the Interstitial ad unit initialization completed successfully", "Interstitial"));
        }
    }

    public boolean isISDemandOnlyInterstitialReady(String instanceId) {
        boolean isAvailable = false;
        try {
            if (this.isDemandOnlyInterstitial) {
                isAvailable = this.mInterstitialManager.isInterstitialReady(instanceId);
                JSONObject data = IronSourceUtils.getMediationAdditionalData(true);
                data.put("status", String.valueOf(isAvailable));
                InterstitialEventsManager.getInstance().log(new EventData(30, data));
                this.mLoggerManager.log(IronSourceTag.API, "isISDemandOnlyInterstitialReady(instanceId: " + instanceId + "):" + isAvailable, 1);
                return isAvailable;
            }
            this.mLoggerManager.log(IronSourceTag.API, "Interstitial was initialized in mediation mode. Use isInterstitialReady instead", 3);
            return false;
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Throwable e2) {
            this.mLoggerManager.log(IronSourceTag.API, "isISDemandOnlyInterstitialReady(instanceId: " + instanceId + "):" + isAvailable, 1);
            this.mLoggerManager.logException(IronSourceTag.API, "isISDemandOnlyInterstitialReady(instanceId: " + instanceId + ")", e2);
            isAvailable = false;
        }
    }

    public void setISDemandOnlyInterstitialListener(ISDemandOnlyInterstitialListener listener) {
        if (listener == null) {
            this.mLoggerManager.log(IronSourceTag.API, "setISDemandOnlyInterstitialListener(ISDemandOnlyInterstitialListener:null)", 1);
        } else {
            this.mLoggerManager.log(IronSourceTag.API, "setISDemandOnlyInterstitialListener(ISDemandOnlyInterstitialListener)", 1);
        }
        this.mListenersWrapper.setISDemandOnlyInterstitialListener(listener);
    }

    private boolean isInterstitialConfigurationsReady() {
        return (this.mCurrentServerResponse == null || this.mCurrentServerResponse.getConfigurations() == null || this.mCurrentServerResponse.getConfigurations().getInterstitialConfigurations() == null) ? false : true;
    }

    private InterstitialPlacement getInterstitialPlacementToShowWithEvent(String placementName) {
        InterstitialPlacement placement = this.mCurrentServerResponse.getConfigurations().getInterstitialConfigurations().getInterstitialPlacement(placementName);
        if (placement == null) {
            this.mLoggerManager.log(IronSourceTag.API, "Placement is not valid, please make sure you are using the right placements, using the default placement.", 3);
            placement = this.mCurrentServerResponse.getConfigurations().getInterstitialConfigurations().getDefaultInterstitialPlacement();
            if (placement == null) {
                this.mLoggerManager.log(IronSourceTag.API, "Default placement was not found, please make sure you are using the right placements.", 3);
                return null;
            }
        }
        String cappedMessage = getCappingMessage(placement.getPlacementName(), getInterstitialCappingStatus(placement.getPlacementName()));
        if (TextUtils.isEmpty(cappedMessage)) {
            return placement;
        }
        this.mLoggerManager.log(IronSourceTag.API, cappedMessage, 1);
        this.mListenersWrapper.onInterstitialAdShowFailed(ErrorBuilder.buildCappedPerPlacementError("Interstitial", cappedMessage));
        return null;
    }

    private boolean isOfferwallConfigurationsReady() {
        return (this.mCurrentServerResponse == null || this.mCurrentServerResponse.getConfigurations() == null || this.mCurrentServerResponse.getConfigurations().getOfferwallConfigurations() == null) ? false : true;
    }

    public void showOfferwall() {
        String logMessage = "showOfferwall()";
        try {
            this.mLoggerManager.log(IronSourceTag.API, logMessage, 1);
            if (isOfferwallConfigurationsReady()) {
                OfferwallPlacement defaultPlacement = this.mCurrentServerResponse.getConfigurations().getOfferwallConfigurations().getDefaultOfferwallPlacement();
                if (defaultPlacement != null) {
                    showOfferwall(defaultPlacement.getPlacementName());
                    return;
                }
                return;
            }
            this.mListenersWrapper.onOfferwallShowFailed(ErrorBuilder.buildInitFailedError("showOfferwall can't be called before the Offerwall ad unit initialization completed successfully", IronSourceConstants.OFFERWALL_AD_UNIT));
        } catch (Exception e) {
            this.mLoggerManager.logException(IronSourceTag.API, logMessage, e);
            this.mListenersWrapper.onOfferwallShowFailed(ErrorBuilder.buildInitFailedError("showOfferwall can't be called before the Offerwall ad unit initialization completed successfully", IronSourceConstants.OFFERWALL_AD_UNIT));
        }
    }

    public void showOfferwall(String placementName) {
        String logMessage = "showOfferwall(" + placementName + ")";
        this.mLoggerManager.log(IronSourceTag.API, logMessage, 1);
        try {
            if (isOfferwallConfigurationsReady()) {
                OfferwallPlacement placement = this.mCurrentServerResponse.getConfigurations().getOfferwallConfigurations().getOfferwallPlacement(placementName);
                if (placement == null) {
                    this.mLoggerManager.log(IronSourceTag.API, "Placement is not valid, please make sure you are using the right placements, using the default placement.", 3);
                    placement = this.mCurrentServerResponse.getConfigurations().getOfferwallConfigurations().getDefaultOfferwallPlacement();
                    if (placement == null) {
                        this.mLoggerManager.log(IronSourceTag.API, "Default placement was not found, please make sure you are using the right placements.", 3);
                        return;
                    }
                }
                this.mOfferwallManager.showOfferwall(placement.getPlacementName());
                return;
            }
            this.mListenersWrapper.onOfferwallShowFailed(ErrorBuilder.buildInitFailedError("showOfferwall can't be called before the Offerwall ad unit initialization completed successfully", IronSourceConstants.OFFERWALL_AD_UNIT));
        } catch (Exception e) {
            this.mLoggerManager.logException(IronSourceTag.API, logMessage, e);
            this.mListenersWrapper.onOfferwallShowFailed(ErrorBuilder.buildInitFailedError("showOfferwall can't be called before the Offerwall ad unit initialization completed successfully", IronSourceConstants.OFFERWALL_AD_UNIT));
        }
    }

    public boolean isOfferwallAvailable() {
        try {
            if (this.mOfferwallManager != null) {
                return this.mOfferwallManager.isOfferwallAvailable();
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public void getOfferwallCredits() {
        String logMessage = "getOfferwallCredits()";
        this.mLoggerManager.log(IronSourceTag.API, logMessage, 1);
        try {
            this.mOfferwallManager.getOfferwallCredits();
        } catch (Throwable e) {
            this.mLoggerManager.logException(IronSourceTag.API, logMessage, e);
        }
    }

    public void setOfferwallListener(OfferwallListener offerwallListener) {
        if (offerwallListener == null) {
            this.mLoggerManager.log(IronSourceTag.API, "setOfferwallListener(OWListener:null)", 1);
        } else {
            this.mLoggerManager.log(IronSourceTag.API, "setOfferwallListener(OWListener)", 1);
        }
        this.mListenersWrapper.setOfferwallListener(offerwallListener);
    }

    public void setLogListener(LogListener logListener) {
        if (logListener == null) {
            this.mLoggerManager.log(IronSourceTag.API, "setLogListener(LogListener:null)", 1);
            return;
        }
        this.mPublisherLogger.setLogListener(logListener);
        this.mLoggerManager.log(IronSourceTag.API, "setLogListener(LogListener:" + logListener.getClass().getSimpleName() + ")", 1);
    }

    public void setRewardedInterstitialListener(RewardedInterstitialListener listener) {
        this.mListenersWrapper.setRewardedInterstitialListener(listener);
    }

    private boolean isBannerConfigurationsReady() {
        return (this.mCurrentServerResponse == null || this.mCurrentServerResponse.getConfigurations() == null || this.mCurrentServerResponse.getConfigurations().getBannerConfigurations() == null) ? false : true;
    }

    public IronSourceBannerLayout createBanner(Activity activity, ISBannerSize size) {
        this.mLoggerManager.log(IronSourceTag.API, "createBanner()", 1);
        if (activity != null) {
            return this.mBannerManager.createBanner(activity, size);
        }
        this.mLoggerManager.log(IronSourceTag.API, "createBanner() : Activity cannot be null", 3);
        return null;
    }

    public void loadBanner(IronSourceBannerLayout banner, String placementName) {
        this.mLoggerManager.log(IronSourceTag.API, "loadBanner(" + placementName + ")", 1);
        if (banner == null) {
            this.mLoggerManager.log(IronSourceTag.API, "loadBanner can't be called with a null parameter", 1);
        } else if (!this.mDidInitBanner) {
            this.mLoggerManager.log(IronSourceTag.API, "init() must be called before loadBanner()", 3);
        } else if (!banner.getSize().getDescription().equals("CUSTOM") || (banner.getSize().getWidth() > 0 && banner.getSize().getHeight() > 0)) {
            EInitStatus initStatus = MediationInitializer.getInstance().getCurrentInitStatus();
            if (initStatus == EInitStatus.INIT_FAILED) {
                this.mLoggerManager.log(IronSourceTag.API, "init() had failed", 3);
                BannerCallbackThrottler.getInstance().sendBannerAdLoadFailed(banner, new IronSourceError(600, "Init had failed"));
            } else if (initStatus == EInitStatus.INIT_IN_PROGRESS) {
                if (MediationInitializer.getInstance().isInProgressMoreThan15Secs()) {
                    this.mLoggerManager.log(IronSourceTag.API, "init() had failed", 3);
                    BannerCallbackThrottler.getInstance().sendBannerAdLoadFailed(banner, new IronSourceError(601, "Init had failed"));
                    return;
                }
                this.mBnLayoutToLoad = banner;
                this.mIsBnLoadBeforeInitCompleted = true;
                this.mBnPlacementToLoad = placementName;
            } else if (this.mCurrentServerResponse == null || this.mCurrentServerResponse.getConfigurations() == null || this.mCurrentServerResponse.getConfigurations().getBannerConfigurations() == null) {
                this.mLoggerManager.log(IronSourceTag.API, "No banner configurations found", 3);
                BannerCallbackThrottler.getInstance().sendBannerAdLoadFailed(banner, new IronSourceError(IronSourceError.ERROR_BN_LOAD_NO_CONFIG, "No banner configurations found"));
            } else {
                this.mBannerManager.loadBanner(banner, getBannerPlacement(placementName));
            }
        } else {
            this.mLoggerManager.log(IronSourceTag.API, "loadBanner: Unsupported banner size. Height and width must be bigger than 0", 3);
            BannerCallbackThrottler.getInstance().sendBannerAdLoadFailed(banner, ErrorBuilder.unsupportedBannerSize(""));
        }
    }

    public void loadBanner(IronSourceBannerLayout banner) {
        loadBanner(banner, "");
    }

    public void destroyBanner(IronSourceBannerLayout banner) {
        this.mLoggerManager.log(IronSourceTag.API, "destroyBanner()", 1);
        try {
            this.mBannerManager.destroyBanner(banner);
        } catch (Throwable e) {
            this.mLoggerManager.logException(IronSourceTag.API, "destroyBanner()", e);
        }
    }

    ServerResponseWrapper getServerResponse(Context context, String userId, IResponseListener listener) {
        ServerResponseWrapper serverResponseWrapper;
        synchronized (this.mServerResponseLocker) {
            if (this.mCurrentServerResponse != null) {
                serverResponseWrapper = new ServerResponseWrapper(this.mCurrentServerResponse);
            } else {
                serverResponseWrapper = connectAndGetServerResponse(context, userId, listener);
                if (serverResponseWrapper == null || !serverResponseWrapper.isValidResponse()) {
                    IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "Null or invalid response. Trying to get cached response", 0);
                    serverResponseWrapper = getCachedResponse(context, userId);
                }
                if (serverResponseWrapper != null) {
                    this.mCurrentServerResponse = serverResponseWrapper;
                    IronSourceUtils.saveLastResponse(context, serverResponseWrapper.toString());
                    initializeSettingsFromServerResponse(this.mCurrentServerResponse, context);
                }
                InterstitialEventsManager.getInstance().setHasServerResponse(true);
                RewardedVideoEventsManager.getInstance().setHasServerResponse(true);
            }
        }
        return serverResponseWrapper;
    }

    private ServerResponseWrapper getCachedResponse(Context context, String userId) {
        JSONObject cachedJsonObject;
        try {
            cachedJsonObject = new JSONObject(IronSourceUtils.getLastResponse(context));
        } catch (JSONException e) {
            cachedJsonObject = new JSONObject();
        }
        String cachedAppKey = cachedJsonObject.optString(ServerResponseWrapper.APP_KEY_FIELD);
        String cachedUserId = cachedJsonObject.optString(ServerResponseWrapper.USER_ID_FIELD);
        String cachedSettings = cachedJsonObject.optString(ServerResponseWrapper.RESPONSE_FIELD);
        if (TextUtils.isEmpty(cachedAppKey) || TextUtils.isEmpty(cachedUserId) || TextUtils.isEmpty(cachedSettings) || getIronSourceAppKey() == null || !cachedAppKey.equals(getIronSourceAppKey()) || !cachedUserId.equals(userId)) {
            return null;
        }
        ServerResponseWrapper response = new ServerResponseWrapper(context, cachedAppKey, cachedUserId, cachedSettings);
        IronSourceError sse = ErrorBuilder.buildUsingCachedConfigurationError(cachedAppKey, cachedUserId);
        this.mLoggerManager.log(IronSourceTag.INTERNAL, sse.toString(), 1);
        this.mLoggerManager.log(IronSourceTag.INTERNAL, sse.toString() + ": " + response.toString(), 1);
        RewardedVideoEventsManager.getInstance().log(new EventData(IronSourceConstants.USING_CACHE_FOR_INIT_EVENT, IronSourceUtils.getMediationAdditionalData(false)));
        return response;
    }

    private ServerResponseWrapper connectAndGetServerResponse(Context context, String userId, IResponseListener listener) {
        Exception e;
        if (!IronSourceUtils.isNetworkConnected(context)) {
            return null;
        }
        ServerResponseWrapper serverResponseWrapper = null;
        try {
            String gaid = getAdvertiserId(context);
            if (TextUtils.isEmpty(gaid)) {
                gaid = DeviceStatus.getOrGenerateOnceUniqueIdentifier(context);
                IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "using custom identifier", 1);
            }
            Vector<Pair<String, String>> additionParams = null;
            if (this.mIronSegment != null) {
                additionParams = this.mIronSegment.getSegmentData();
            }
            String serverResponseString = HttpFunctions.getStringFromURL(ServerURL.getCPVProvidersURL(context, getIronSourceAppKey(), userId, gaid, getMediationType(), additionParams), listener);
            if (serverResponseString == null) {
                return null;
            }
            if (IronSourceUtils.getSerr() == 1) {
                String encryptedResponse = new JSONObject(serverResponseString).optString(ServerResponseWrapper.RESPONSE_FIELD, null);
                if (TextUtils.isEmpty(encryptedResponse)) {
                    return null;
                }
                serverResponseString = IronSourceAES.decode(IronSourceUtils.KEY, encryptedResponse);
            }
            ServerResponseWrapper response = new ServerResponseWrapper(context, getIronSourceAppKey(), userId, serverResponseString);
            try {
                return !response.isValidResponse() ? null : response;
            } catch (Exception e2) {
                e = e2;
                serverResponseWrapper = response;
                e.printStackTrace();
                return serverResponseWrapper;
            }
        } catch (Exception e3) {
            e = e3;
            e.printStackTrace();
            return serverResponseWrapper;
        }
    }

    private void initializeSettingsFromServerResponse(ServerResponseWrapper response, Context context) {
        initializeLoggerManager(response);
        initializeEventsSettings(response, context);
    }

    private void initializeEventsSettings(ServerResponseWrapper response, Context context) {
        boolean isRVEventsEnabled = false;
        if (isRewardedVideoConfigurationsReady()) {
            isRVEventsEnabled = response.getConfigurations().getRewardedVideoConfigurations().getRewardedVideoEventsConfigurations().isEventsEnabled();
        }
        boolean isISEventsEnabled = false;
        if (isInterstitialConfigurationsReady()) {
            isISEventsEnabled = response.getConfigurations().getInterstitialConfigurations().getInterstitialEventsConfigurations().isEventsEnabled();
        }
        boolean isBNEventsEnabled = false;
        if (isBannerConfigurationsReady()) {
            isBNEventsEnabled = response.getConfigurations().getBannerConfigurations().getBannerEventsConfigurations().isEventsEnabled();
        }
        if (isRVEventsEnabled) {
            RewardedVideoEventsManager.getInstance().setFormatterType(response.getConfigurations().getRewardedVideoConfigurations().getRewardedVideoEventsConfigurations().getEventsType(), context);
            RewardedVideoEventsManager.getInstance().setEventsUrl(response.getConfigurations().getRewardedVideoConfigurations().getRewardedVideoEventsConfigurations().getEventsURL(), context);
            RewardedVideoEventsManager.getInstance().setMaxNumberOfEvents(response.getConfigurations().getRewardedVideoConfigurations().getRewardedVideoEventsConfigurations().getMaxNumberOfEvents());
            RewardedVideoEventsManager.getInstance().setMaxEventsPerBatch(response.getConfigurations().getRewardedVideoConfigurations().getRewardedVideoEventsConfigurations().getMaxEventsPerBatch());
            RewardedVideoEventsManager.getInstance().setBackupThreshold(response.getConfigurations().getRewardedVideoConfigurations().getRewardedVideoEventsConfigurations().getEventsBackupThreshold());
            RewardedVideoEventsManager.getInstance().setOptOutEvents(response.getConfigurations().getRewardedVideoConfigurations().getRewardedVideoEventsConfigurations().getOptOutEvents(), context);
            RewardedVideoEventsManager.getInstance().setServerSegmentData(response.getConfigurations().getApplicationConfigurations().getSegmetData());
        } else {
            RewardedVideoEventsManager.getInstance().setIsEventsEnabled(false);
        }
        if (isISEventsEnabled) {
            InterstitialEventsManager.getInstance().setFormatterType(response.getConfigurations().getInterstitialConfigurations().getInterstitialEventsConfigurations().getEventsType(), context);
            InterstitialEventsManager.getInstance().setEventsUrl(response.getConfigurations().getInterstitialConfigurations().getInterstitialEventsConfigurations().getEventsURL(), context);
            InterstitialEventsManager.getInstance().setMaxNumberOfEvents(response.getConfigurations().getInterstitialConfigurations().getInterstitialEventsConfigurations().getMaxNumberOfEvents());
            InterstitialEventsManager.getInstance().setMaxEventsPerBatch(response.getConfigurations().getInterstitialConfigurations().getInterstitialEventsConfigurations().getMaxEventsPerBatch());
            InterstitialEventsManager.getInstance().setBackupThreshold(response.getConfigurations().getInterstitialConfigurations().getInterstitialEventsConfigurations().getEventsBackupThreshold());
            InterstitialEventsManager.getInstance().setOptOutEvents(response.getConfigurations().getInterstitialConfigurations().getInterstitialEventsConfigurations().getOptOutEvents(), context);
            InterstitialEventsManager.getInstance().setServerSegmentData(response.getConfigurations().getApplicationConfigurations().getSegmetData());
        } else if (isBNEventsEnabled) {
            ApplicationEvents config = response.getConfigurations().getBannerConfigurations().getBannerEventsConfigurations();
            InterstitialEventsManager.getInstance().setFormatterType(config.getEventsType(), context);
            InterstitialEventsManager.getInstance().setEventsUrl(config.getEventsURL(), context);
            InterstitialEventsManager.getInstance().setMaxNumberOfEvents(config.getMaxNumberOfEvents());
            InterstitialEventsManager.getInstance().setMaxEventsPerBatch(config.getMaxEventsPerBatch());
            InterstitialEventsManager.getInstance().setBackupThreshold(config.getEventsBackupThreshold());
            InterstitialEventsManager.getInstance().setOptOutEvents(config.getOptOutEvents(), context);
            InterstitialEventsManager.getInstance().setServerSegmentData(response.getConfigurations().getApplicationConfigurations().getSegmetData());
        } else {
            InterstitialEventsManager.getInstance().setIsEventsEnabled(false);
        }
    }

    private void initializeLoggerManager(ServerResponseWrapper response) {
        this.mPublisherLogger.setDebugLevel(response.getConfigurations().getApplicationConfigurations().getLoggerConfigurations().getPublisherLoggerLevel());
        this.mLoggerManager.setLoggerDebugLevel(ConsoleLogger.NAME, response.getConfigurations().getApplicationConfigurations().getLoggerConfigurations().getConsoleLoggerLevel());
    }

    public void removeRewardedVideoListener() {
        this.mLoggerManager.log(IronSourceTag.API, "removeRewardedVideoListener()", 1);
        this.mListenersWrapper.setRewardedVideoListener(null);
    }

    public void removeInterstitialListener() {
        this.mLoggerManager.log(IronSourceTag.API, "removeInterstitialListener()", 1);
        this.mListenersWrapper.setInterstitialListener(null);
    }

    public void removeOfferwallListener() {
        this.mLoggerManager.log(IronSourceTag.API, "removeOfferwallListener()", 1);
        this.mListenersWrapper.setOfferwallListener(null);
    }

    synchronized void setIronSourceUserId(String userId) {
        this.mUserId = userId;
    }

    public synchronized String getIronSourceAppKey() {
        return this.mAppKey;
    }

    public synchronized String getIronSourceUserId() {
        return this.mUserId;
    }

    private ConfigValidationResult validateAppKey(String appKey) {
        ConfigValidationResult result = new ConfigValidationResult();
        if (appKey == null) {
            result.setInvalid(new IronSourceError(IronSourceError.ERROR_CODE_INVALID_KEY_VALUE, "Init Fail - appKey is missing"));
        } else if (!validateLength(appKey, 5, 10)) {
            result.setInvalid(ErrorBuilder.buildInvalidCredentialsError(ServerResponseWrapper.APP_KEY_FIELD, appKey, "length should be between 5-10 characters"));
        } else if (!validateAlphanumeric(appKey)) {
            result.setInvalid(ErrorBuilder.buildInvalidCredentialsError(ServerResponseWrapper.APP_KEY_FIELD, appKey, "should contain only english characters and numbers"));
        }
        return result;
    }

    private void validateGender(String gender, ConfigValidationResult result) {
        if (gender != null) {
            try {
                gender = gender.toLowerCase().trim();
                if (!Gender.MALE.equals(gender) && !Gender.FEMALE.equals(gender) && !"unknown".equals(gender)) {
                    result.setInvalid(ErrorBuilder.buildInvalidKeyValueError("gender", IronSourceConstants.IRONSOURCE_CONFIG_NAME, "gender value should be one of male/female/unknown."));
                }
            } catch (Exception e) {
                result.setInvalid(ErrorBuilder.buildInvalidKeyValueError("gender", IronSourceConstants.IRONSOURCE_CONFIG_NAME, "gender value should be one of male/female/unknown."));
            }
        }
    }

    private void validateAge(int age, ConfigValidationResult result) {
        if (age < 5 || age > C0462b.f362a) {
            try {
                result.setInvalid(ErrorBuilder.buildInvalidKeyValueError(IronSourceSegment.AGE, IronSourceConstants.IRONSOURCE_CONFIG_NAME, "age value should be between 5-120"));
            } catch (NumberFormatException e) {
                result.setInvalid(ErrorBuilder.buildInvalidKeyValueError(IronSourceSegment.AGE, IronSourceConstants.IRONSOURCE_CONFIG_NAME, "age value should be between 5-120"));
            }
        }
    }

    private void validateSegment(String segment, ConfigValidationResult result) {
        if (segment != null) {
            try {
                if (segment.length() > 64) {
                    result.setInvalid(ErrorBuilder.buildInvalidKeyValueError("segment", IronSourceConstants.IRONSOURCE_CONFIG_NAME, "segment value should not exceed 64 characters."));
                }
            } catch (Exception e) {
                result.setInvalid(ErrorBuilder.buildInvalidKeyValueError("segment", IronSourceConstants.IRONSOURCE_CONFIG_NAME, "segment value should not exceed 64 characters."));
            }
        }
    }

    private void validateDynamicUserId(String dynamicUserId, ConfigValidationResult result) {
        if (!validateLength(dynamicUserId, 1, 128)) {
            result.setInvalid(ErrorBuilder.buildInvalidKeyValueError("dynamicUserId", IronSourceConstants.IRONSOURCE_CONFIG_NAME, "dynamicUserId is invalid, should be between 1-128 chars in length."));
        }
    }

    private boolean validateLength(String key, int minLength, int maxLength) {
        return key != null && key.length() >= minLength && key.length() <= maxLength;
    }

    private boolean validateAlphanumeric(String key) {
        if (key == null) {
            return false;
        }
        return key.matches("^[a-zA-Z0-9]*$");
    }

    public InterstitialPlacement getInterstitialPlacementInfo(String placementName) {
        InterstitialPlacement result = null;
        try {
            result = this.mCurrentServerResponse.getConfigurations().getInterstitialConfigurations().getInterstitialPlacement(placementName);
            this.mLoggerManager.log(IronSourceTag.API, "getPlacementInfo(placement: " + placementName + "):" + result, 1);
            return result;
        } catch (Exception e) {
            return result;
        }
    }

    public Placement getRewardedVideoPlacementInfo(String placementName) {
        Placement result = null;
        try {
            result = this.mCurrentServerResponse.getConfigurations().getRewardedVideoConfigurations().getRewardedVideoPlacement(placementName);
            this.mLoggerManager.log(IronSourceTag.API, "getPlacementInfo(placement: " + placementName + "):" + result, 1);
            return result;
        } catch (Exception e) {
            return result;
        }
    }

    public String getAdvertiserId(Context context) {
        try {
            String[] deviceInfo = DeviceStatus.getAdvertisingIdInfo(context);
            if (deviceInfo.length <= 0 || deviceInfo[0] == null) {
                return "";
            }
            return deviceInfo[0];
        } catch (Exception e) {
            return "";
        }
    }

    public void shouldTrackNetworkState(Context context, boolean track) {
        if (this.mRewardedVideoManager != null) {
            this.mRewardedVideoManager.shouldTrackNetworkState(context, track);
        }
        if (this.mInterstitialManager != null) {
            this.mInterstitialManager.shouldTrackNetworkState(context, track);
        }
    }

    boolean isInterstitialPlacementCapped(String placementName) {
        boolean isCapped = false;
        ECappingStatus cappingStatus = getInterstitialCappingStatus(placementName);
        if (cappingStatus != null) {
            switch (cappingStatus) {
                case CAPPED_PER_DELIVERY:
                case CAPPED_PER_COUNT:
                case CAPPED_PER_PACE:
                    isCapped = true;
                    break;
            }
        }
        sendIsCappedEvent("Interstitial", isCapped);
        return isCapped;
    }

    boolean isRewardedVideoPlacementCapped(String placementName) {
        boolean isCapped = false;
        ECappingStatus cappingStatus = getRewardedVideoCappingStatus(placementName);
        if (cappingStatus != null) {
            switch (cappingStatus) {
                case CAPPED_PER_DELIVERY:
                case CAPPED_PER_COUNT:
                case CAPPED_PER_PACE:
                    isCapped = true;
                    break;
            }
        }
        sendIsCappedEvent(IronSourceConstants.REWARDED_VIDEO_AD_UNIT, isCapped);
        return isCapped;
    }

    boolean isBannerPlacementCapped(String placementName) {
        if (this.mCurrentServerResponse == null || this.mCurrentServerResponse.getConfigurations() == null || this.mCurrentServerResponse.getConfigurations().getBannerConfigurations() == null) {
            return false;
        }
        BannerPlacement placement = null;
        try {
            placement = this.mCurrentServerResponse.getConfigurations().getBannerConfigurations().getBannerPlacement(placementName);
            if (placement == null) {
                placement = this.mCurrentServerResponse.getConfigurations().getBannerConfigurations().getDefaultBannerPlacement();
                if (placement == null) {
                    this.mLoggerManager.log(IronSourceTag.API, "Banner default placement was not found", 3);
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (placement != null) {
            return CappingManager.isBnPlacementCapped(this.mActivity, placement.getPlacementName());
        }
        return false;
    }

    private ECappingStatus getInterstitialCappingStatus(String placementName) {
        if (this.mCurrentServerResponse == null || this.mCurrentServerResponse.getConfigurations() == null || this.mCurrentServerResponse.getConfigurations().getInterstitialConfigurations() == null) {
            return ECappingStatus.NOT_CAPPED;
        }
        InterstitialPlacement placement = null;
        try {
            placement = this.mCurrentServerResponse.getConfigurations().getInterstitialConfigurations().getInterstitialPlacement(placementName);
            if (placement == null) {
                placement = this.mCurrentServerResponse.getConfigurations().getInterstitialConfigurations().getDefaultInterstitialPlacement();
                if (placement == null) {
                    this.mLoggerManager.log(IronSourceTag.API, "Default placement was not found", 3);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (placement == null) {
            return ECappingStatus.NOT_CAPPED;
        }
        return CappingManager.isPlacementCapped(this.mActivity, placement);
    }

    private ECappingStatus getRewardedVideoCappingStatus(String placementName) {
        if (this.mCurrentServerResponse == null || this.mCurrentServerResponse.getConfigurations() == null || this.mCurrentServerResponse.getConfigurations().getRewardedVideoConfigurations() == null) {
            return ECappingStatus.NOT_CAPPED;
        }
        Placement placement = null;
        try {
            placement = this.mCurrentServerResponse.getConfigurations().getRewardedVideoConfigurations().getRewardedVideoPlacement(placementName);
            if (placement == null) {
                placement = this.mCurrentServerResponse.getConfigurations().getRewardedVideoConfigurations().getDefaultRewardedVideoPlacement();
                if (placement == null) {
                    this.mLoggerManager.log(IronSourceTag.API, "Default placement was not found", 3);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (placement == null) {
            return ECappingStatus.NOT_CAPPED;
        }
        return CappingManager.isPlacementCapped(this.mActivity, placement);
    }

    private void sendIsCappedEvent(String adUnit, boolean isCapped) {
        if (isCapped) {
            boolean isDemandOnly = false;
            if (adUnit.equals("Interstitial")) {
                isDemandOnly = this.isDemandOnlyInterstitial;
            } else if (adUnit.equals(IronSourceConstants.REWARDED_VIDEO_AD_UNIT)) {
                isDemandOnly = this.isDemandOnlyRewardedVideo;
            }
            JSONObject data = IronSourceUtils.getMediationAdditionalData(isDemandOnly);
            try {
                data.put("reason", 1);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if ("Interstitial".equals(adUnit)) {
                InterstitialEventsManager.getInstance().log(new EventData(34, data));
            } else if (IronSourceConstants.REWARDED_VIDEO_AD_UNIT.equals(adUnit)) {
                RewardedVideoEventsManager.getInstance().log(new EventData(20, data));
            }
        }
    }

    String getCappingMessage(String placementName, ECappingStatus cappingStatus) {
        if (cappingStatus == null) {
            return null;
        }
        switch (cappingStatus) {
            case CAPPED_PER_DELIVERY:
                return "Placement " + placementName + " is capped by disabled delivery";
            case CAPPED_PER_COUNT:
                return "Placement " + placementName + " has reached its capping limit";
            case CAPPED_PER_PACE:
                return "Placement " + placementName + " has reached its limit as defined per pace";
            default:
                return null;
        }
    }

    ServerResponseWrapper getCurrentServerResponse() {
        return this.mCurrentServerResponse;
    }

    void setSegmentListener(SegmentListener listener) {
        if (this.mListenersWrapper != null) {
            this.mListenersWrapper.setSegmentListener(listener);
            MediationInitializer.getInstance().setSegmentListener(this.mListenersWrapper);
        }
    }

    HashSet<String> getAllSettingsForProvider(String providerName, String fieldName) {
        if (this.mCurrentServerResponse == null) {
            return new HashSet();
        }
        return this.mCurrentServerResponse.getProviderSettingsHolder().getProviderSettingsByReflectionName(providerName, fieldName);
    }

    private BannerPlacement getBannerPlacement(String placementName) {
        if (TextUtils.isEmpty(placementName)) {
            return this.mCurrentServerResponse.getConfigurations().getBannerConfigurations().getDefaultBannerPlacement();
        }
        BannerPlacement placement = this.mCurrentServerResponse.getConfigurations().getBannerConfigurations().getBannerPlacement(placementName);
        return placement == null ? this.mCurrentServerResponse.getConfigurations().getBannerConfigurations().getDefaultBannerPlacement() : placement;
    }

    public synchronized String getSessionId() {
        return this.mSessionId;
    }

    public void setConsent(boolean consent) {
        this.mConsent = Boolean.valueOf(consent);
        IronSourceLoggerManager.getLogger().log(IronSourceTag.API, "setConsent : " + consent, 1);
        if (this.mRewardedVideoManager != null) {
            this.mRewardedVideoManager.setConsent(consent);
        }
        if (this.mInterstitialManager != null) {
            this.mInterstitialManager.setConsent(consent);
        }
        if (this.mBannerManager != null) {
            this.mBannerManager.setConsent(consent);
        }
        if (this.mOfferwallAdapter != null) {
            this.mLoggerManager.log(IronSourceTag.ADAPTER_API, "Offerwall | setConsent(consent:" + consent + ")", 1);
            this.mOfferwallAdapter.setConsent(consent);
        }
        int code = 40;
        if (!consent) {
            code = 41;
        }
        RewardedVideoEventsManager.getInstance().log(new EventData(code, IronSourceUtils.getMediationAdditionalData(false)));
    }

    Boolean getConsent() {
        return this.mConsent;
    }
}
