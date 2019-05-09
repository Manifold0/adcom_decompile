// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk;

import android.os.Looper;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import com.ironsource.mediationsdk.utils.IronSourceUtils;
import com.ironsource.mediationsdk.utils.ErrorBuilder;
import com.ironsource.mediationsdk.config.ConfigValidationResult;
import com.ironsource.mediationsdk.model.ServerSegmetData;
import java.util.Iterator;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.environment.DeviceStatus;
import com.ironsource.mediationsdk.integration.IntegrationHelper;
import android.content.Context;
import android.text.TextUtils;
import com.ironsource.mediationsdk.sdk.GeneralProperties;
import java.util.ArrayList;
import com.ironsource.mediationsdk.utils.ServerResponseWrapper;
import com.ironsource.mediationsdk.sdk.SegmentListener;
import java.util.List;
import android.os.HandlerThread;
import android.os.Handler;
import android.os.CountDownTimer;
import java.util.concurrent.atomic.AtomicBoolean;
import android.app.Activity;
import com.ironsource.environment.NetworkStateReceiver;

class MediationInitializer implements NetworkStateReceiverListener
{
    private static MediationInitializer sInstance;
    private final String GENERAL_PROPERTIES_APP_KEY;
    private final String GENERAL_PROPERTIES_USER_ID;
    private final String TAG;
    private InitRunnable initRunnable;
    private Activity mActivity;
    private String mAppKey;
    private AtomicBoolean mAtomicShouldPerformInit;
    private CountDownTimer mCountDownTimer;
    private boolean mDidReportInitialAvailability;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private EInitStatus mInitStatus;
    private boolean mIsInProgressMoreThan15Secs;
    private boolean mIsRevived;
    private boolean mListenForInit;
    private NetworkStateReceiver mNetworkStateReceiver;
    private List<OnMediationInitializationListener> mOnMediationInitializationListeners;
    private int mRetryAvailabilityLimit;
    private int mRetryCounter;
    private int mRetryDelay;
    private int mRetryGrowLimit;
    private int mRetryLimit;
    private SegmentListener mSegmentListener;
    private ServerResponseWrapper mServerResponseWrapper;
    private String mUserId;
    private String mUserIdType;
    
    private MediationInitializer() {
        this.GENERAL_PROPERTIES_USER_ID = "userId";
        this.GENERAL_PROPERTIES_APP_KEY = "appKey";
        this.TAG = this.getClass().getSimpleName();
        this.mDidReportInitialAvailability = false;
        this.mHandlerThread = null;
        this.mListenForInit = false;
        this.mOnMediationInitializationListeners = new ArrayList<OnMediationInitializationListener>();
        this.initRunnable = (InitRunnable)new InitRunnable() {
            @Override
            public void run() {
                Label_0513: {
                    Label_0443: {
                        Label_0385: {
                            while (true) {
                                while (true) {
                                    IronSourceObject instance = null;
                                    Label_0272: {
                                        try {
                                            instance = IronSourceObject.getInstance();
                                            if (!MediationInitializer.this.validateUserId(MediationInitializer.this.mUserId).isValid()) {
                                                break Label_0272;
                                            }
                                            MediationInitializer.this.mUserIdType = "userGenerated";
                                            GeneralProperties.getProperties().putKey("userIdType", MediationInitializer.this.mUserIdType);
                                            if (!TextUtils.isEmpty((CharSequence)MediationInitializer.this.mUserId)) {
                                                GeneralProperties.getProperties().putKey("userId", MediationInitializer.this.mUserId);
                                            }
                                            if (!TextUtils.isEmpty((CharSequence)MediationInitializer.this.mAppKey)) {
                                                GeneralProperties.getProperties().putKey("appKey", MediationInitializer.this.mAppKey);
                                            }
                                            MediationInitializer.this.mServerResponseWrapper = instance.getServerResponse((Context)MediationInitializer.this.mActivity, MediationInitializer.this.mUserId, this.listener);
                                            if (MediationInitializer.this.mServerResponseWrapper == null) {
                                                break Label_0513;
                                            }
                                            MediationInitializer.this.mHandler.removeCallbacks((Runnable)this);
                                            if (MediationInitializer.this.mServerResponseWrapper.isValidResponse()) {
                                                MediationInitializer.this.setInitStatus(EInitStatus.INITIATED);
                                                if (MediationInitializer.this.mServerResponseWrapper.getConfigurations().getApplicationConfigurations().getIntegration()) {
                                                    IntegrationHelper.validateIntegration(MediationInitializer.this.mActivity);
                                                }
                                                final List<IronSource.AD_UNIT> initiatedAdUnits = MediationInitializer.this.mServerResponseWrapper.getInitiatedAdUnits();
                                                final Iterator<OnMediationInitializationListener> iterator = (Iterator<OnMediationInitializationListener>)MediationInitializer.this.mOnMediationInitializationListeners.iterator();
                                                while (iterator.hasNext()) {
                                                    iterator.next().onInitSuccess(initiatedAdUnits, MediationInitializer.this.wasInitRevived());
                                                }
                                                break Label_0385;
                                            }
                                            break Label_0443;
                                        }
                                        catch (Exception ex) {
                                            ex.printStackTrace();
                                        }
                                        break;
                                    }
                                    MediationInitializer.this.mUserId = instance.getAdvertiserId((Context)MediationInitializer.this.mActivity);
                                    if (!TextUtils.isEmpty((CharSequence)MediationInitializer.this.mUserId)) {
                                        MediationInitializer.this.mUserIdType = "GAID";
                                    }
                                    else {
                                        MediationInitializer.this.mUserId = DeviceStatus.getOrGenerateOnceUniqueIdentifier((Context)MediationInitializer.this.mActivity);
                                        if (!TextUtils.isEmpty((CharSequence)MediationInitializer.this.mUserId)) {
                                            MediationInitializer.this.mUserIdType = "UUID";
                                        }
                                        else {
                                            MediationInitializer.this.mUserId = "";
                                        }
                                    }
                                    instance.setIronSourceUserId(MediationInitializer.this.mUserId);
                                    continue;
                                }
                            }
                            return;
                        }
                        if (MediationInitializer.this.mSegmentListener == null) {
                            return;
                        }
                        final ServerSegmetData segmetData = MediationInitializer.this.mServerResponseWrapper.getConfigurations().getApplicationConfigurations().getSegmetData();
                        if (segmetData != null && !TextUtils.isEmpty((CharSequence)segmetData.getSegmentName())) {
                            MediationInitializer.this.mSegmentListener.onSegmentReceived(segmetData.getSegmentName());
                            return;
                        }
                        return;
                    }
                    if (!MediationInitializer.this.mDidReportInitialAvailability) {
                        MediationInitializer.this.setInitStatus(EInitStatus.INIT_FAILED);
                        MediationInitializer.this.mDidReportInitialAvailability = true;
                        final Iterator<OnMediationInitializationListener> iterator2 = (Iterator<OnMediationInitializationListener>)MediationInitializer.this.mOnMediationInitializationListeners.iterator();
                        while (iterator2.hasNext()) {
                            iterator2.next().onInitFailed("serverResponseIsNotValid");
                        }
                        return;
                    }
                    return;
                }
                if (MediationInitializer.this.mRetryCounter == 3) {
                    MediationInitializer.this.mIsInProgressMoreThan15Secs = true;
                    final Iterator<OnMediationInitializationListener> iterator3 = (Iterator<OnMediationInitializationListener>)MediationInitializer.this.mOnMediationInitializationListeners.iterator();
                    while (iterator3.hasNext()) {
                        iterator3.next().onStillInProgressAfter15Secs();
                    }
                }
                if (this.isRecoverable && MediationInitializer.this.mRetryCounter < MediationInitializer.this.mRetryLimit) {
                    MediationInitializer.this.mIsRevived = true;
                    MediationInitializer.this.mHandler.postDelayed((Runnable)this, (long)(MediationInitializer.this.mRetryDelay * 1000));
                    if (MediationInitializer.this.mRetryCounter < MediationInitializer.this.mRetryGrowLimit) {
                        MediationInitializer.this.mRetryDelay *= 2;
                    }
                }
                if ((!this.isRecoverable || MediationInitializer.this.mRetryCounter == MediationInitializer.this.mRetryAvailabilityLimit) && !MediationInitializer.this.mDidReportInitialAvailability) {
                    MediationInitializer.this.mDidReportInitialAvailability = true;
                    if (TextUtils.isEmpty((CharSequence)this.reason)) {
                        this.reason = "noServerResponse";
                    }
                    final Iterator<OnMediationInitializationListener> iterator4 = (Iterator<OnMediationInitializationListener>)MediationInitializer.this.mOnMediationInitializationListeners.iterator();
                    while (iterator4.hasNext()) {
                        iterator4.next().onInitFailed(this.reason);
                    }
                    MediationInitializer.this.setInitStatus(EInitStatus.INIT_FAILED);
                    IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.API, "Mediation availability false reason: No server response", 1);
                }
                MediationInitializer.this.mRetryCounter++;
            }
        };
        this.mInitStatus = EInitStatus.NOT_INIT;
        (this.mHandlerThread = new HandlerThread("IronSourceInitiatorHandler")).start();
        this.mHandler = new Handler(this.mHandlerThread.getLooper());
        this.mRetryDelay = 1;
        this.mRetryCounter = 0;
        this.mRetryLimit = 62;
        this.mRetryGrowLimit = 12;
        this.mRetryAvailabilityLimit = 5;
        this.mAtomicShouldPerformInit = new AtomicBoolean(true);
        this.mIsRevived = false;
        this.mIsInProgressMoreThan15Secs = false;
    }
    
    public static MediationInitializer getInstance() {
        synchronized (MediationInitializer.class) {
            if (MediationInitializer.sInstance == null) {
                MediationInitializer.sInstance = new MediationInitializer();
            }
            return MediationInitializer.sInstance;
        }
    }
    
    private void setInitStatus(final EInitStatus mInitStatus) {
        synchronized (this) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, "setInitStatus(old status: " + this.mInitStatus + ", new status: " + mInitStatus + ")", 0);
            this.mInitStatus = mInitStatus;
        }
    }
    
    private boolean validateLength(final String s, final int n, final int n2) {
        return s != null && s.length() >= n && s.length() <= n2;
    }
    
    private ConfigValidationResult validateUserId(final String s) {
        final ConfigValidationResult configValidationResult = new ConfigValidationResult();
        if (s != null) {
            if (!this.validateLength(s, 1, 64)) {
                configValidationResult.setInvalid(ErrorBuilder.buildInvalidCredentialsError("userId", s, null));
            }
            return configValidationResult;
        }
        configValidationResult.setInvalid(ErrorBuilder.buildInvalidCredentialsError("userId", s, "it's missing"));
        return configValidationResult;
    }
    
    private boolean wasInitRevived() {
        return this.mIsRevived;
    }
    
    public void addMediationInitializationListener(final OnMediationInitializationListener onMediationInitializationListener) {
        if (onMediationInitializationListener == null) {
            return;
        }
        this.mOnMediationInitializationListeners.add(onMediationInitializationListener);
    }
    
    public EInitStatus getCurrentInitStatus() {
        synchronized (this) {
            return this.mInitStatus;
        }
    }
    
    public void init(final Activity mActivity, final String mAppKey, final String mUserId, final IronSource.AD_UNIT... array) {
        while (true) {
            synchronized (this) {
                try {
                    if (this.mAtomicShouldPerformInit != null && this.mAtomicShouldPerformInit.compareAndSet(true, false)) {
                        this.setInitStatus(EInitStatus.INIT_IN_PROGRESS);
                        this.mActivity = mActivity;
                        this.mUserId = mUserId;
                        this.mAppKey = mAppKey;
                        if (IronSourceUtils.isNetworkConnected((Context)mActivity)) {
                            this.mHandler.post((Runnable)this.initRunnable);
                        }
                        else {
                            this.mListenForInit = true;
                            if (this.mNetworkStateReceiver == null) {
                                this.mNetworkStateReceiver = new NetworkStateReceiver((Context)mActivity, (NetworkStateReceiver.NetworkStateReceiverListener)this);
                            }
                            mActivity.getApplicationContext().registerReceiver((BroadcastReceiver)this.mNetworkStateReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
                            new Handler(Looper.getMainLooper()).post((Runnable)new Runnable() {
                                @Override
                                public void run() {
                                    MediationInitializer.this.mCountDownTimer = new CountDownTimer(60000L, 15000L) {
                                        public void onFinish() {
                                            if (!MediationInitializer.this.mDidReportInitialAvailability) {
                                                MediationInitializer.this.mDidReportInitialAvailability = true;
                                                final Iterator<OnMediationInitializationListener> iterator = MediationInitializer.this.mOnMediationInitializationListeners.iterator();
                                                while (iterator.hasNext()) {
                                                    iterator.next().onInitFailed("noInternetConnection");
                                                }
                                                IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.API, "Mediation availability false reason: No internet connection", 1);
                                            }
                                        }
                                        
                                        public void onTick(final long n) {
                                            if (n <= 45000L) {
                                                MediationInitializer.this.mIsInProgressMoreThan15Secs = true;
                                                final Iterator<OnMediationInitializationListener> iterator = MediationInitializer.this.mOnMediationInitializationListeners.iterator();
                                                while (iterator.hasNext()) {
                                                    iterator.next().onStillInProgressAfter15Secs();
                                                }
                                            }
                                        }
                                    }.start();
                                }
                            });
                        }
                        return;
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    return;
                }
            }
            IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.API, this.TAG + ": Multiple calls to init are not allowed", 2);
        }
    }
    
    public boolean isInProgressMoreThan15Secs() {
        synchronized (this) {
            return this.mIsInProgressMoreThan15Secs;
        }
    }
    
    @Override
    public void onNetworkAvailabilityChanged(final boolean b) {
        if (this.mListenForInit && b) {
            if (this.mCountDownTimer != null) {
                this.mCountDownTimer.cancel();
            }
            this.mListenForInit = false;
            this.mIsRevived = true;
            this.mHandler.post((Runnable)this.initRunnable);
        }
    }
    
    public void removeMediationInitializationListener(final OnMediationInitializationListener onMediationInitializationListener) {
        if (onMediationInitializationListener == null || this.mOnMediationInitializationListeners.size() == 0) {
            return;
        }
        this.mOnMediationInitializationListeners.remove(onMediationInitializationListener);
    }
    
    void setSegmentListener(final SegmentListener mSegmentListener) {
        this.mSegmentListener = mSegmentListener;
    }
    
    enum EInitStatus
    {
        INITIATED, 
        INIT_FAILED, 
        INIT_IN_PROGRESS, 
        NOT_INIT;
    }
    
    abstract class InitRunnable implements Runnable
    {
        boolean isRecoverable;
        protected IronSourceObject.IResponseListener listener;
        String reason;
        
        InitRunnable() {
            this.isRecoverable = true;
            this.listener = new IronSourceObject.IResponseListener() {
                @Override
                public void onUnrecoverableError(final String reason) {
                    InitRunnable.this.isRecoverable = false;
                    InitRunnable.this.reason = reason;
                }
            };
        }
    }
    
    interface OnMediationInitializationListener
    {
        void onInitFailed(final String p0);
        
        void onInitSuccess(final List<IronSource.AD_UNIT> p0, final boolean p1);
        
        void onStillInProgressAfter15Secs();
    }
}
