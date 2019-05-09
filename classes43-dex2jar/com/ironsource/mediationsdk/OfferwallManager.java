// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk;

import com.ironsource.mediationsdk.model.OfferwallPlacement;
import android.content.Context;
import com.ironsource.mediationsdk.sdk.OfferwallListener;
import org.json.JSONException;
import com.ironsource.mediationsdk.events.RewardedVideoEventsManager;
import org.json.JSONObject;
import com.ironsource.eventsmodule.EventData;
import android.text.TextUtils;
import com.ironsource.mediationsdk.utils.IronSourceUtils;
import com.ironsource.mediationsdk.logger.LogListener;
import com.ironsource.mediationsdk.utils.ErrorBuilder;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.utils.ServerResponseWrapper;
import com.ironsource.mediationsdk.model.ProviderSettings;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import java.util.concurrent.atomic.AtomicBoolean;
import com.ironsource.mediationsdk.sdk.OfferwallAdapterApi;
import android.app.Activity;
import com.ironsource.mediationsdk.sdk.InternalOfferwallListener;
import com.ironsource.mediationsdk.sdk.InternalOfferwallApi;

class OfferwallManager implements InternalOfferwallApi, InternalOfferwallListener
{
    private final String TAG;
    private Activity mActivity;
    private OfferwallAdapterApi mAdapter;
    private AtomicBoolean mAtomicShouldPerformInit;
    private String mCurrentPlacementName;
    private AtomicBoolean mIsOfferwallAvailable;
    private InternalOfferwallListener mListenersWrapper;
    private IronSourceLoggerManager mLoggerManager;
    private ProviderSettings mProviderSettings;
    private ServerResponseWrapper mServerResponseWrapper;
    
    OfferwallManager() {
        this.TAG = this.getClass().getName();
        this.mAtomicShouldPerformInit = new AtomicBoolean(true);
        this.mIsOfferwallAvailable = new AtomicBoolean(false);
        this.mLoggerManager = IronSourceLoggerManager.getLogger();
    }
    
    private void reportInitFail(final IronSourceError ironSourceError) {
        synchronized (this) {
            if (this.mIsOfferwallAvailable != null) {
                this.mIsOfferwallAvailable.set(false);
            }
            if (this.mAtomicShouldPerformInit != null) {
                this.mAtomicShouldPerformInit.set(true);
            }
            if (this.mListenersWrapper != null) {
                this.mListenersWrapper.onOfferwallAvailable(false, ironSourceError);
            }
        }
    }
    
    private void setCustomParams(final AbstractAdapter abstractAdapter) {
        try {
            final Integer age = IronSourceObject.getInstance().getAge();
            if (age != null) {
                abstractAdapter.setAge(age);
            }
            final String gender = IronSourceObject.getInstance().getGender();
            if (gender != null) {
                abstractAdapter.setGender(gender);
            }
            final String mediationSegment = IronSourceObject.getInstance().getMediationSegment();
            if (mediationSegment != null) {
                abstractAdapter.setMediationSegment(mediationSegment);
            }
            final Boolean consent = IronSourceObject.getInstance().getConsent();
            if (consent != null) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_API, "Offerwall | setConsent(consent:" + consent + ")", 1);
                abstractAdapter.setConsent(consent);
            }
        }
        catch (Exception ex) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, ":setCustomParams():" + ex.toString(), 3);
        }
    }
    
    private AbstractAdapter startOfferwallAdapter() {
        try {
            final IronSourceObject instance = IronSourceObject.getInstance();
            AbstractAdapter existingAdapter;
            if ((existingAdapter = instance.getExistingAdapter("SupersonicAds")) == null) {
                final Class<?> forName = Class.forName("com.ironsource.adapters." + "SupersonicAds".toLowerCase() + "." + "SupersonicAds" + "Adapter");
                if ((existingAdapter = (AbstractAdapter)forName.getMethod("startAdapter", String.class).invoke(forName, "SupersonicAds")) == null) {
                    return null;
                }
            }
            instance.addOWAdapter(existingAdapter);
            return existingAdapter;
        }
        catch (Throwable t) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "SupersonicAds initialization failed - please verify that required dependencies are in you build path.", 2);
            this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.API, this.TAG + ":startOfferwallAdapter", t);
            return null;
        }
    }
    
    @Override
    public void getOfferwallCredits() {
        if (this.mAdapter != null) {
            this.mAdapter.getOfferwallCredits();
        }
    }
    
    @Override
    public void initOfferwall(final Activity mActivity, final String s, final String s2) {
        while (true) {
            Label_0131: {
                synchronized (this) {
                    this.mLoggerManager.log(IronSourceLogger.IronSourceTag.NATIVE, this.TAG + ":initOfferwall(appKey: " + s + ", userId: " + s2 + ")", 1);
                    this.mActivity = mActivity;
                    this.mServerResponseWrapper = IronSourceObject.getInstance().getCurrentServerResponse();
                    if (this.mServerResponseWrapper == null) {
                        this.reportInitFail(ErrorBuilder.buildInitFailedError("Please check configurations for Offerwall adapters", "Offerwall"));
                    }
                    else {
                        this.mProviderSettings = this.mServerResponseWrapper.getProviderSettingsHolder().getProviderSettings("SupersonicAds");
                        if (this.mProviderSettings != null) {
                            break Label_0131;
                        }
                        this.reportInitFail(ErrorBuilder.buildInitFailedError("Please check configurations for Offerwall adapters", "Offerwall"));
                    }
                    return;
                }
            }
            final AbstractAdapter startOfferwallAdapter = this.startOfferwallAdapter();
            if (startOfferwallAdapter == null) {
                this.reportInitFail(ErrorBuilder.buildInitFailedError("Please check configurations for Offerwall adapters", "Offerwall"));
                return;
            }
            this.setCustomParams(startOfferwallAdapter);
            startOfferwallAdapter.setLogListener(this.mLoggerManager);
            (this.mAdapter = (OfferwallAdapterApi)startOfferwallAdapter).setInternalOfferwallListener(this);
            final Activity activity;
            this.mAdapter.initOfferwall(activity, s, s2, this.mProviderSettings.getRewardedVideoSettings());
        }
    }
    
    @Override
    public boolean isOfferwallAvailable() {
        // monitorenter(this)
        boolean value = false;
        try {
            if (this.mIsOfferwallAvailable != null) {
                value = this.mIsOfferwallAvailable.get();
            }
            return value;
        }
        finally {
        }
        // monitorexit(this)
    }
    
    @Override
    public void onGetOfferwallCreditsFailed(final IronSourceError ironSourceError) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, "onGetOfferwallCreditsFailed(" + ironSourceError + ")", 1);
        if (this.mListenersWrapper != null) {
            this.mListenersWrapper.onGetOfferwallCreditsFailed(ironSourceError);
        }
    }
    
    @Override
    public boolean onOfferwallAdCredited(final int n, final int n2, final boolean b) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, "onOfferwallAdCredited()", 1);
        return this.mListenersWrapper != null && this.mListenersWrapper.onOfferwallAdCredited(n, n2, b);
    }
    
    @Override
    public void onOfferwallAvailable(final boolean b) {
        this.onOfferwallAvailable(b, null);
    }
    
    @Override
    public void onOfferwallAvailable(final boolean b, final IronSourceError ironSourceError) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, "onOfferwallAvailable(isAvailable: " + b + ")", 1);
        if (b) {
            this.mIsOfferwallAvailable.set(true);
            if (this.mListenersWrapper != null) {
                this.mListenersWrapper.onOfferwallAvailable(true);
            }
            return;
        }
        this.reportInitFail(ironSourceError);
    }
    
    @Override
    public void onOfferwallClosed() {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, "onOfferwallClosed()", 1);
        if (this.mListenersWrapper != null) {
            this.mListenersWrapper.onOfferwallClosed();
        }
    }
    
    @Override
    public void onOfferwallOpened() {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, "onOfferwallOpened()", 1);
        Object mediationAdditionalData = IronSourceUtils.getMediationAdditionalData(false);
        while (true) {
            try {
                if (!TextUtils.isEmpty((CharSequence)this.mCurrentPlacementName)) {
                    ((JSONObject)mediationAdditionalData).put("placement", (Object)this.mCurrentPlacementName);
                }
                mediationAdditionalData = new EventData(305, (JSONObject)mediationAdditionalData);
                RewardedVideoEventsManager.getInstance().log((EventData)mediationAdditionalData);
                if (this.mListenersWrapper != null) {
                    this.mListenersWrapper.onOfferwallOpened();
                }
            }
            catch (JSONException ex) {
                ex.printStackTrace();
                continue;
            }
            break;
        }
    }
    
    @Override
    public void onOfferwallShowFailed(final IronSourceError ironSourceError) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, "onOfferwallShowFailed(" + ironSourceError + ")", 1);
        if (this.mListenersWrapper != null) {
            this.mListenersWrapper.onOfferwallShowFailed(ironSourceError);
        }
    }
    
    @Override
    public void setInternalOfferwallListener(final InternalOfferwallListener mListenersWrapper) {
        this.mListenersWrapper = mListenersWrapper;
    }
    
    @Override
    public void setOfferwallListener(final OfferwallListener offerwallListener) {
    }
    
    @Override
    public void showOfferwall() {
    }
    
    @Override
    public void showOfferwall(final String mCurrentPlacementName) {
        final String string = "OWManager:showOfferwall(" + mCurrentPlacementName + ")";
        OfferwallPlacement offerwallPlacement;
        try {
            if (!IronSourceUtils.isNetworkConnected((Context)this.mActivity)) {
                this.mListenersWrapper.onOfferwallShowFailed(ErrorBuilder.buildNoInternetConnectionShowFailError("Offerwall"));
                return;
            }
            this.mCurrentPlacementName = mCurrentPlacementName;
            if ((offerwallPlacement = this.mServerResponseWrapper.getConfigurations().getOfferwallConfigurations().getOfferwallPlacement(mCurrentPlacementName)) == null) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, "Placement is not valid, please make sure you are using the right placements, using the default placement.", 3);
                if ((offerwallPlacement = this.mServerResponseWrapper.getConfigurations().getOfferwallConfigurations().getDefaultOfferwallPlacement()) == null) {
                    this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, "Default placement was not found, please make sure you are using the right placements.", 3);
                    return;
                }
            }
        }
        catch (Exception ex) {
            this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.INTERNAL, string, ex);
            return;
        }
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, string, 1);
        if (this.mIsOfferwallAvailable != null && this.mIsOfferwallAvailable.get() && this.mAdapter != null) {
            this.mAdapter.showOfferwall(String.valueOf(offerwallPlacement.getPlacementId()), this.mProviderSettings.getRewardedVideoSettings());
        }
    }
}
