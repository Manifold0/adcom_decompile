package com.ironsource.mediationsdk;

import android.app.Activity;
import android.text.TextUtils;
import com.ironsource.eventsmodule.EventData;
import com.ironsource.mediationsdk.events.RewardedVideoEventsManager;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.logger.IronSourceLogger.IronSourceTag;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.model.OfferwallPlacement;
import com.ironsource.mediationsdk.model.ProviderSettings;
import com.ironsource.mediationsdk.sdk.InternalOfferwallApi;
import com.ironsource.mediationsdk.sdk.InternalOfferwallListener;
import com.ironsource.mediationsdk.sdk.OfferwallAdapterApi;
import com.ironsource.mediationsdk.sdk.OfferwallListener;
import com.ironsource.mediationsdk.utils.ErrorBuilder;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import com.ironsource.mediationsdk.utils.IronSourceUtils;
import com.ironsource.mediationsdk.utils.ServerResponseWrapper;
import com.vungle.warren.ui.VungleActivity;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

class OfferwallManager implements InternalOfferwallApi, InternalOfferwallListener {
    private final String TAG = getClass().getName();
    private Activity mActivity;
    private OfferwallAdapterApi mAdapter;
    private AtomicBoolean mAtomicShouldPerformInit = new AtomicBoolean(true);
    private String mCurrentPlacementName;
    private AtomicBoolean mIsOfferwallAvailable = new AtomicBoolean(false);
    private InternalOfferwallListener mListenersWrapper;
    private IronSourceLoggerManager mLoggerManager = IronSourceLoggerManager.getLogger();
    private ProviderSettings mProviderSettings;
    private ServerResponseWrapper mServerResponseWrapper;

    OfferwallManager() {
    }

    public synchronized void initOfferwall(Activity activity, String appKey, String userId) {
        this.mLoggerManager.log(IronSourceTag.NATIVE, this.TAG + ":initOfferwall(appKey: " + appKey + ", userId: " + userId + ")", 1);
        this.mActivity = activity;
        this.mServerResponseWrapper = IronSourceObject.getInstance().getCurrentServerResponse();
        if (this.mServerResponseWrapper == null) {
            reportInitFail(ErrorBuilder.buildInitFailedError("Please check configurations for Offerwall adapters", IronSourceConstants.OFFERWALL_AD_UNIT));
        } else {
            this.mProviderSettings = this.mServerResponseWrapper.getProviderSettingsHolder().getProviderSettings(IronSourceConstants.IRONSOURCE_CONFIG_NAME);
            if (this.mProviderSettings == null) {
                reportInitFail(ErrorBuilder.buildInitFailedError("Please check configurations for Offerwall adapters", IronSourceConstants.OFFERWALL_AD_UNIT));
            } else {
                AbstractAdapter offerwallAdapter = startOfferwallAdapter();
                if (offerwallAdapter == null) {
                    reportInitFail(ErrorBuilder.buildInitFailedError("Please check configurations for Offerwall adapters", IronSourceConstants.OFFERWALL_AD_UNIT));
                } else {
                    setCustomParams(offerwallAdapter);
                    offerwallAdapter.setLogListener(this.mLoggerManager);
                    this.mAdapter = (OfferwallAdapterApi) offerwallAdapter;
                    this.mAdapter.setInternalOfferwallListener(this);
                    this.mAdapter.initOfferwall(activity, appKey, userId, this.mProviderSettings.getRewardedVideoSettings());
                }
            }
        }
    }

    public void showOfferwall() {
    }

    public void showOfferwall(String placementName) {
        String logMessage = "OWManager:showOfferwall(" + placementName + ")";
        try {
            if (IronSourceUtils.isNetworkConnected(this.mActivity)) {
                this.mCurrentPlacementName = placementName;
                OfferwallPlacement placement = this.mServerResponseWrapper.getConfigurations().getOfferwallConfigurations().getOfferwallPlacement(placementName);
                if (placement == null) {
                    this.mLoggerManager.log(IronSourceTag.INTERNAL, "Placement is not valid, please make sure you are using the right placements, using the default placement.", 3);
                    placement = this.mServerResponseWrapper.getConfigurations().getOfferwallConfigurations().getDefaultOfferwallPlacement();
                    if (placement == null) {
                        this.mLoggerManager.log(IronSourceTag.INTERNAL, "Default placement was not found, please make sure you are using the right placements.", 3);
                        return;
                    }
                }
                this.mLoggerManager.log(IronSourceTag.INTERNAL, logMessage, 1);
                if (this.mIsOfferwallAvailable != null && this.mIsOfferwallAvailable.get() && this.mAdapter != null) {
                    this.mAdapter.showOfferwall(String.valueOf(placement.getPlacementId()), this.mProviderSettings.getRewardedVideoSettings());
                    return;
                }
                return;
            }
            this.mListenersWrapper.onOfferwallShowFailed(ErrorBuilder.buildNoInternetConnectionShowFailError(IronSourceConstants.OFFERWALL_AD_UNIT));
        } catch (Exception e) {
            this.mLoggerManager.logException(IronSourceTag.INTERNAL, logMessage, e);
        }
    }

    public synchronized boolean isOfferwallAvailable() {
        boolean result;
        result = false;
        if (this.mIsOfferwallAvailable != null) {
            result = this.mIsOfferwallAvailable.get();
        }
        return result;
    }

    public void getOfferwallCredits() {
        if (this.mAdapter != null) {
            this.mAdapter.getOfferwallCredits();
        }
    }

    public void setOfferwallListener(OfferwallListener offerwallListener) {
    }

    public void setInternalOfferwallListener(InternalOfferwallListener listener) {
        this.mListenersWrapper = listener;
    }

    public void onOfferwallAvailable(boolean isAvailable) {
        onOfferwallAvailable(isAvailable, null);
    }

    public void onOfferwallAvailable(boolean isAvailable, IronSourceError error) {
        this.mLoggerManager.log(IronSourceTag.ADAPTER_CALLBACK, "onOfferwallAvailable(isAvailable: " + isAvailable + ")", 1);
        if (isAvailable) {
            this.mIsOfferwallAvailable.set(true);
            if (this.mListenersWrapper != null) {
                this.mListenersWrapper.onOfferwallAvailable(true);
                return;
            }
            return;
        }
        reportInitFail(error);
    }

    public void onOfferwallOpened() {
        this.mLoggerManager.log(IronSourceTag.ADAPTER_CALLBACK, "onOfferwallOpened()", 1);
        JSONObject data = IronSourceUtils.getMediationAdditionalData(false);
        try {
            if (!TextUtils.isEmpty(this.mCurrentPlacementName)) {
                data.put(VungleActivity.PLACEMENT_EXTRA, this.mCurrentPlacementName);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RewardedVideoEventsManager.getInstance().log(new EventData(IronSourceConstants.OFFERWALL_OPENED, data));
        if (this.mListenersWrapper != null) {
            this.mListenersWrapper.onOfferwallOpened();
        }
    }

    public void onOfferwallShowFailed(IronSourceError error) {
        this.mLoggerManager.log(IronSourceTag.ADAPTER_CALLBACK, "onOfferwallShowFailed(" + error + ")", 1);
        if (this.mListenersWrapper != null) {
            this.mListenersWrapper.onOfferwallShowFailed(error);
        }
    }

    public boolean onOfferwallAdCredited(int credits, int totalCredits, boolean totalCreditsFlag) {
        this.mLoggerManager.log(IronSourceTag.ADAPTER_CALLBACK, "onOfferwallAdCredited()", 1);
        if (this.mListenersWrapper != null) {
            return this.mListenersWrapper.onOfferwallAdCredited(credits, totalCredits, totalCreditsFlag);
        }
        return false;
    }

    public void onGetOfferwallCreditsFailed(IronSourceError error) {
        this.mLoggerManager.log(IronSourceTag.ADAPTER_CALLBACK, "onGetOfferwallCreditsFailed(" + error + ")", 1);
        if (this.mListenersWrapper != null) {
            this.mListenersWrapper.onGetOfferwallCreditsFailed(error);
        }
    }

    public void onOfferwallClosed() {
        this.mLoggerManager.log(IronSourceTag.ADAPTER_CALLBACK, "onOfferwallClosed()", 1);
        if (this.mListenersWrapper != null) {
            this.mListenersWrapper.onOfferwallClosed();
        }
    }

    private synchronized void reportInitFail(IronSourceError error) {
        if (this.mIsOfferwallAvailable != null) {
            this.mIsOfferwallAvailable.set(false);
        }
        if (this.mAtomicShouldPerformInit != null) {
            this.mAtomicShouldPerformInit.set(true);
        }
        if (this.mListenersWrapper != null) {
            this.mListenersWrapper.onOfferwallAvailable(false, error);
        }
    }

    private AbstractAdapter startOfferwallAdapter() {
        try {
            IronSourceObject sso = IronSourceObject.getInstance();
            AbstractAdapter providerAdapter = sso.getExistingAdapter(IronSourceConstants.IRONSOURCE_CONFIG_NAME);
            if (providerAdapter == null) {
                Class<?> mAdapterClass = Class.forName("com.ironsource.adapters." + IronSourceConstants.IRONSOURCE_CONFIG_NAME.toLowerCase() + "." + IronSourceConstants.IRONSOURCE_CONFIG_NAME + "Adapter");
                providerAdapter = (AbstractAdapter) mAdapterClass.getMethod(IronSourceConstants.START_ADAPTER, new Class[]{String.class}).invoke(mAdapterClass, new Object[]{IronSourceConstants.IRONSOURCE_CONFIG_NAME});
                if (providerAdapter == null) {
                    return null;
                }
            }
            sso.addOWAdapter(providerAdapter);
            return providerAdapter;
        } catch (Throwable e) {
            this.mLoggerManager.log(IronSourceTag.API, "SupersonicAds initialization failed - please verify that required dependencies are in you build path.", 2);
            this.mLoggerManager.logException(IronSourceTag.API, this.TAG + ":startOfferwallAdapter", e);
            return null;
        }
    }

    private void setCustomParams(AbstractAdapter providerAdapter) {
        try {
            Integer age = IronSourceObject.getInstance().getAge();
            if (age != null) {
                providerAdapter.setAge(age.intValue());
            }
            String gender = IronSourceObject.getInstance().getGender();
            if (gender != null) {
                providerAdapter.setGender(gender);
            }
            String segment = IronSourceObject.getInstance().getMediationSegment();
            if (segment != null) {
                providerAdapter.setMediationSegment(segment);
            }
            Boolean consent = IronSourceObject.getInstance().getConsent();
            if (consent != null) {
                this.mLoggerManager.log(IronSourceTag.ADAPTER_API, "Offerwall | setConsent(consent:" + consent + ")", 1);
                providerAdapter.setConsent(consent.booleanValue());
            }
        } catch (Exception e) {
            this.mLoggerManager.log(IronSourceTag.INTERNAL, ":setCustomParams():" + e.toString(), 3);
        }
    }
}
