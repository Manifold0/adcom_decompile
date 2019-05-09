package com.ironsource.mediationsdk;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.text.TextUtils;
import com.ironsource.environment.DeviceStatus;
import com.ironsource.environment.NetworkStateReceiver;
import com.ironsource.environment.NetworkStateReceiver.NetworkStateReceiverListener;
import com.ironsource.mediationsdk.IronSource.AD_UNIT;
import com.ironsource.mediationsdk.IronSourceObject.IResponseListener;
import com.ironsource.mediationsdk.config.ConfigValidationResult;
import com.ironsource.mediationsdk.integration.IntegrationHelper;
import com.ironsource.mediationsdk.logger.IronSourceLogger.IronSourceTag;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.model.ServerSegmetData;
import com.ironsource.mediationsdk.sdk.GeneralProperties;
import com.ironsource.mediationsdk.sdk.SegmentListener;
import com.ironsource.mediationsdk.utils.ErrorBuilder;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import com.ironsource.mediationsdk.utils.IronSourceUtils;
import com.ironsource.mediationsdk.utils.ServerResponseWrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

class MediationInitializer implements NetworkStateReceiverListener {
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

    interface OnMediationInitializationListener {
        void onInitFailed(String str);

        void onInitSuccess(List<AD_UNIT> list, boolean z);

        void onStillInProgressAfter15Secs();
    }

    abstract class InitRunnable implements Runnable {
        boolean isRecoverable = true;
        protected IResponseListener listener = new C06891();
        String reason;

        /* renamed from: com.ironsource.mediationsdk.MediationInitializer$InitRunnable$1 */
        class C06891 implements IResponseListener {
            C06891() {
            }

            public void onUnrecoverableError(String errorMessage) {
                InitRunnable.this.isRecoverable = false;
                InitRunnable.this.reason = errorMessage;
            }
        }

        InitRunnable() {
        }
    }

    /* renamed from: com.ironsource.mediationsdk.MediationInitializer$1 */
    class C06861 extends InitRunnable {
        C06861() {
            super();
        }

        public void run() {
            try {
                IronSourceObject ironSourceObject = IronSourceObject.getInstance();
                if (MediationInitializer.this.validateUserId(MediationInitializer.this.mUserId).isValid()) {
                    MediationInitializer.this.mUserIdType = IronSourceConstants.TYPE_USER_GENERATED;
                } else {
                    MediationInitializer.this.mUserId = ironSourceObject.getAdvertiserId(MediationInitializer.this.mActivity);
                    if (TextUtils.isEmpty(MediationInitializer.this.mUserId)) {
                        MediationInitializer.this.mUserId = DeviceStatus.getOrGenerateOnceUniqueIdentifier(MediationInitializer.this.mActivity);
                        if (TextUtils.isEmpty(MediationInitializer.this.mUserId)) {
                            MediationInitializer.this.mUserId = "";
                        } else {
                            MediationInitializer.this.mUserIdType = IronSourceConstants.TYPE_UUID;
                        }
                    } else {
                        MediationInitializer.this.mUserIdType = IronSourceConstants.TYPE_GAID;
                    }
                    ironSourceObject.setIronSourceUserId(MediationInitializer.this.mUserId);
                }
                GeneralProperties.getProperties().putKey(GeneralProperties.USER_ID_TYPE, MediationInitializer.this.mUserIdType);
                if (!TextUtils.isEmpty(MediationInitializer.this.mUserId)) {
                    GeneralProperties.getProperties().putKey(ServerResponseWrapper.USER_ID_FIELD, MediationInitializer.this.mUserId);
                }
                if (!TextUtils.isEmpty(MediationInitializer.this.mAppKey)) {
                    GeneralProperties.getProperties().putKey(ServerResponseWrapper.APP_KEY_FIELD, MediationInitializer.this.mAppKey);
                }
                MediationInitializer.this.mServerResponseWrapper = ironSourceObject.getServerResponse(MediationInitializer.this.mActivity, MediationInitializer.this.mUserId, this.listener);
                if (MediationInitializer.this.mServerResponseWrapper != null) {
                    MediationInitializer.this.mHandler.removeCallbacks(this);
                    if (MediationInitializer.this.mServerResponseWrapper.isValidResponse()) {
                        MediationInitializer.this.setInitStatus(EInitStatus.INITIATED);
                        if (MediationInitializer.this.mServerResponseWrapper.getConfigurations().getApplicationConfigurations().getIntegration()) {
                            IntegrationHelper.validateIntegration(MediationInitializer.this.mActivity);
                        }
                        List<AD_UNIT> adUnits = MediationInitializer.this.mServerResponseWrapper.getInitiatedAdUnits();
                        for (OnMediationInitializationListener listener : MediationInitializer.this.mOnMediationInitializationListeners) {
                            listener.onInitSuccess(adUnits, MediationInitializer.this.wasInitRevived());
                        }
                        if (MediationInitializer.this.mSegmentListener != null) {
                            ServerSegmetData data = MediationInitializer.this.mServerResponseWrapper.getConfigurations().getApplicationConfigurations().getSegmetData();
                            if (data != null && !TextUtils.isEmpty(data.getSegmentName())) {
                                MediationInitializer.this.mSegmentListener.onSegmentReceived(data.getSegmentName());
                                return;
                            }
                            return;
                        }
                        return;
                    } else if (!MediationInitializer.this.mDidReportInitialAvailability) {
                        MediationInitializer.this.setInitStatus(EInitStatus.INIT_FAILED);
                        MediationInitializer.this.mDidReportInitialAvailability = true;
                        for (OnMediationInitializationListener listener2 : MediationInitializer.this.mOnMediationInitializationListeners) {
                            listener2.onInitFailed(IronSourceConstants.FALSE_AVAILABILITY_REASON_SERVER_RESPONSE_IS_NOT_VALID);
                        }
                        return;
                    } else {
                        return;
                    }
                }
                if (MediationInitializer.this.mRetryCounter == 3) {
                    MediationInitializer.this.mIsInProgressMoreThan15Secs = true;
                    for (OnMediationInitializationListener listener22 : MediationInitializer.this.mOnMediationInitializationListeners) {
                        listener22.onStillInProgressAfter15Secs();
                    }
                }
                if (this.isRecoverable && MediationInitializer.this.mRetryCounter < MediationInitializer.this.mRetryLimit) {
                    MediationInitializer.this.mIsRevived = true;
                    MediationInitializer.this.mHandler.postDelayed(this, (long) (MediationInitializer.this.mRetryDelay * 1000));
                    if (MediationInitializer.this.mRetryCounter < MediationInitializer.this.mRetryGrowLimit) {
                        MediationInitializer.this.mRetryDelay = MediationInitializer.this.mRetryDelay * 2;
                    }
                }
                if ((!this.isRecoverable || MediationInitializer.this.mRetryCounter == MediationInitializer.this.mRetryAvailabilityLimit) && !MediationInitializer.this.mDidReportInitialAvailability) {
                    MediationInitializer.this.mDidReportInitialAvailability = true;
                    if (TextUtils.isEmpty(this.reason)) {
                        this.reason = IronSourceConstants.FALSE_AVAILABILITY_REASON_NO_SERVER_RESPONSE;
                    }
                    for (OnMediationInitializationListener listener222 : MediationInitializer.this.mOnMediationInitializationListeners) {
                        listener222.onInitFailed(this.reason);
                    }
                    MediationInitializer.this.setInitStatus(EInitStatus.INIT_FAILED);
                    IronSourceLoggerManager.getLogger().log(IronSourceTag.API, "Mediation availability false reason: No server response", 1);
                }
                MediationInitializer.this.mRetryCounter = MediationInitializer.this.mRetryCounter + 1;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: com.ironsource.mediationsdk.MediationInitializer$2 */
    class C06882 implements Runnable {
        C06882() {
        }

        public void run() {
            MediationInitializer.this.mCountDownTimer = new CountDownTimer(60000, 15000) {
                public void onTick(long millisUntilFinished) {
                    if (millisUntilFinished <= 45000) {
                        MediationInitializer.this.mIsInProgressMoreThan15Secs = true;
                        for (OnMediationInitializationListener listener : MediationInitializer.this.mOnMediationInitializationListeners) {
                            listener.onStillInProgressAfter15Secs();
                        }
                    }
                }

                public void onFinish() {
                    if (!MediationInitializer.this.mDidReportInitialAvailability) {
                        MediationInitializer.this.mDidReportInitialAvailability = true;
                        for (OnMediationInitializationListener listener : MediationInitializer.this.mOnMediationInitializationListeners) {
                            listener.onInitFailed(IronSourceConstants.FALSE_AVAILABILITY_REASON_NO_INTERNET);
                        }
                        IronSourceLoggerManager.getLogger().log(IronSourceTag.API, "Mediation availability false reason: No internet connection", 1);
                    }
                }
            }.start();
        }
    }

    enum EInitStatus {
        NOT_INIT,
        INIT_IN_PROGRESS,
        INIT_FAILED,
        INITIATED
    }

    public static synchronized MediationInitializer getInstance() {
        MediationInitializer mediationInitializer;
        synchronized (MediationInitializer.class) {
            if (sInstance == null) {
                sInstance = new MediationInitializer();
            }
            mediationInitializer = sInstance;
        }
        return mediationInitializer;
    }

    private MediationInitializer() {
        this.GENERAL_PROPERTIES_USER_ID = ServerResponseWrapper.USER_ID_FIELD;
        this.GENERAL_PROPERTIES_APP_KEY = ServerResponseWrapper.APP_KEY_FIELD;
        this.TAG = getClass().getSimpleName();
        this.mDidReportInitialAvailability = false;
        this.mHandlerThread = null;
        this.mListenForInit = false;
        this.mOnMediationInitializationListeners = new ArrayList();
        this.initRunnable = new C06861();
        this.mInitStatus = EInitStatus.NOT_INIT;
        this.mHandlerThread = new HandlerThread("IronSourceInitiatorHandler");
        this.mHandlerThread.start();
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

    private synchronized void setInitStatus(EInitStatus status) {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "setInitStatus(old status: " + this.mInitStatus + ", new status: " + status + ")", 0);
        this.mInitStatus = status;
    }

    public synchronized void init(Activity activity, String appKey, String userId, AD_UNIT... adUnits) {
        try {
            if (this.mAtomicShouldPerformInit == null || !this.mAtomicShouldPerformInit.compareAndSet(true, false)) {
                IronSourceLoggerManager.getLogger().log(IronSourceTag.API, this.TAG + ": Multiple calls to init are not allowed", 2);
            } else {
                setInitStatus(EInitStatus.INIT_IN_PROGRESS);
                this.mActivity = activity;
                this.mUserId = userId;
                this.mAppKey = appKey;
                if (IronSourceUtils.isNetworkConnected(activity)) {
                    this.mHandler.post(this.initRunnable);
                } else {
                    this.mListenForInit = true;
                    if (this.mNetworkStateReceiver == null) {
                        this.mNetworkStateReceiver = new NetworkStateReceiver(activity, this);
                    }
                    activity.getApplicationContext().registerReceiver(this.mNetworkStateReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
                    new Handler(Looper.getMainLooper()).post(new C06882());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onNetworkAvailabilityChanged(boolean connected) {
        if (this.mListenForInit && connected) {
            if (this.mCountDownTimer != null) {
                this.mCountDownTimer.cancel();
            }
            this.mListenForInit = false;
            this.mIsRevived = true;
            this.mHandler.post(this.initRunnable);
        }
    }

    private boolean wasInitRevived() {
        return this.mIsRevived;
    }

    public synchronized EInitStatus getCurrentInitStatus() {
        return this.mInitStatus;
    }

    public synchronized boolean isInProgressMoreThan15Secs() {
        return this.mIsInProgressMoreThan15Secs;
    }

    public void addMediationInitializationListener(OnMediationInitializationListener listener) {
        if (listener != null) {
            this.mOnMediationInitializationListeners.add(listener);
        }
    }

    public void removeMediationInitializationListener(OnMediationInitializationListener listener) {
        if (listener != null && this.mOnMediationInitializationListeners.size() != 0) {
            this.mOnMediationInitializationListeners.remove(listener);
        }
    }

    void setSegmentListener(SegmentListener listener) {
        this.mSegmentListener = listener;
    }

    private ConfigValidationResult validateUserId(String userId) {
        ConfigValidationResult result = new ConfigValidationResult();
        if (userId == null) {
            result.setInvalid(ErrorBuilder.buildInvalidCredentialsError(ServerResponseWrapper.USER_ID_FIELD, userId, "it's missing"));
        } else if (!validateLength(userId, 1, 64)) {
            result.setInvalid(ErrorBuilder.buildInvalidCredentialsError(ServerResponseWrapper.USER_ID_FIELD, userId, null));
        }
        return result;
    }

    private boolean validateLength(String key, int minLength, int maxLength) {
        if (key != null && key.length() >= minLength && key.length() <= maxLength) {
            return true;
        }
        return false;
    }
}
