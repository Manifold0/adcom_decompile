package com.adjust.sdk;

import android.content.Context;
import com.kongregate.android.internal.sdk.C0498e;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class InstallReferrer implements InvocationHandler {
    public static final String PACKAGE_BASE_NAME = "com.android.installreferrer.";
    private static final int STATUS_DEVELOPER_ERROR = 3;
    private static final int STATUS_FEATURE_NOT_SUPPORTED = 2;
    private static final int STATUS_OK = 0;
    private static final int STATUS_SERVICE_DISCONNECTED = -1;
    private static final int STATUS_SERVICE_UNAVAILABLE = 1;
    private WeakReference<IActivityHandler> activityHandlerWeakRef;
    private Context context;
    private Object flagLock;
    private boolean hasInstallReferrerBeenRead;
    private ILogger logger = AdjustFactory.getLogger();
    private Object referrerClient;
    private int retries;
    private TimerOnce retryTimer;
    private int retryWaitTime = 3000;

    /* renamed from: com.adjust.sdk.InstallReferrer$1 */
    class C00881 implements Runnable {
        C00881() {
        }

        public void run() {
            InstallReferrer.this.startConnection();
        }
    }

    public InstallReferrer(Context context, IActivityHandler activityHandler) {
        this.context = context;
        this.flagLock = new Object();
        this.hasInstallReferrerBeenRead = false;
        this.retries = 0;
        this.retryTimer = new TimerOnce(new C00881(), "InstallReferrer");
        this.activityHandlerWeakRef = new WeakReference(activityHandler);
        startConnection();
    }

    public void startConnection() {
        closeReferrerClient();
        synchronized (this.flagLock) {
            if (this.hasInstallReferrerBeenRead) {
                this.logger.debug("Install referrer has already been read", new Object[0]);
                return;
            }
            this.referrerClient = createInstallReferrerClient(this.context);
            Class listenerClass = getInstallReferrerStateListenerClass();
            startConnection(listenerClass, createProxyInstallReferrerStateListener(listenerClass));
        }
    }

    public Object createInstallReferrerClient(Context context) {
        Object obj = null;
        if (context != null) {
            try {
                obj = Reflection.invokeInstanceMethod(Reflection.invokeStaticMethod("com.android.installreferrer.api.InstallReferrerClient", "newBuilder", new Class[]{Context.class}, context), C0498e.f496m, null, new Object[0]);
            } catch (Exception e) {
                this.logger.warn("Couldn't create instance of referrer client (%s)", e.getMessage());
            }
        }
        return obj;
    }

    public Class getInstallReferrerStateListenerClass() {
        try {
            return Class.forName("com.android.installreferrer.api.InstallReferrerStateListener");
        } catch (Exception e) {
            this.logger.error("getInstallReferrerStateListenerClass error (%s)", e.getMessage());
            return null;
        }
    }

    public Object createProxyInstallReferrerStateListener(Class installReferrerStateListenerClass) {
        if (installReferrerStateListenerClass == null) {
            return null;
        }
        return Proxy.newProxyInstance(installReferrerStateListenerClass.getClassLoader(), new Class[]{installReferrerStateListenerClass}, this);
    }

    public void startConnection(Class listenerClass, Object listenerProxy) {
        if (this.referrerClient != null && listenerClass != null && listenerProxy != null) {
            try {
                Reflection.invokeInstanceMethod(this.referrerClient, "startConnection", new Class[]{listenerClass}, listenerProxy);
            } catch (Exception e) {
                this.logger.error("startConnection error (%s)", e.getMessage());
            }
        }
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            String methodName = method.getName();
            this.logger.debug("InstallReferrer invoke method name: %s", methodName);
            for (Object arg : args) {
                this.logger.debug("InstallReferrer invoke arg: %s", arg);
            }
            if (methodName.equals("onInstallReferrerSetupFinished")) {
                onInstallReferrerSetupFinishedInt(((Integer) args[0]).intValue());
            } else if (methodName.equals("onInstallReferrerServiceDisconnected")) {
                this.logger.debug("InstallReferrer onInstallReferrerServiceDisconnected", new Object[0]);
            }
        } catch (Exception e) {
            this.logger.error("InstallReferrer invoke error (%s)", e.getMessage());
        }
        return null;
    }

    public void onInstallReferrerSetupFinishedInt(int responseCode) {
        switch (responseCode) {
            case -1:
                this.logger.debug("Play Store service is not connected now. Retrying ...", new Object[0]);
                retry();
                break;
            case 0:
                try {
                    Object referrerDetails = getInstallReferrer();
                    this.logger.debug("installReferrer: %s, clickTime: %d, installBeginTime: %d", getStringInstallReferrer(referrerDetails), Long.valueOf(getReferrerClickTimestampSeconds(referrerDetails)), Long.valueOf(getInstallBeginTimestampSeconds(referrerDetails)));
                    IActivityHandler activityHandler = (IActivityHandler) this.activityHandlerWeakRef.get();
                    if (activityHandler != null) {
                        activityHandler.sendInstallReferrer(clickTime, installBegin, installReferrer);
                    }
                    synchronized (this.flagLock) {
                        this.hasInstallReferrerBeenRead = true;
                    }
                    break;
                } catch (Exception e) {
                    this.logger.debug("Couldn't get install referrer from client (%s). Retrying ...", e.getMessage());
                    retry();
                    break;
                }
            case 1:
                this.logger.debug("Could not initiate connection to the Install Referrer service. Retrying ...", new Object[0]);
                retry();
                break;
            case 2:
                this.logger.debug("Install referrer not available on the current Play Store app.", new Object[0]);
                break;
            case 3:
                this.logger.debug("Install referrer general errors caused by incorrect usage. Retrying ...", new Object[0]);
                retry();
                break;
            default:
                this.logger.debug("Unexpected response code of install referrer response: %d", Integer.valueOf(responseCode));
                break;
        }
        closeReferrerClient();
    }

    private Object getInstallReferrer() {
        Object obj = null;
        if (this.referrerClient != null) {
            try {
                obj = Reflection.invokeInstanceMethod(this.referrerClient, "getInstallReferrer", null, new Object[0]);
            } catch (Exception e) {
                this.logger.error("getInstallReferrer error (%s)", e.getMessage());
            }
        }
        return obj;
    }

    private String getStringInstallReferrer(Object referrerDetails) {
        if (referrerDetails == null) {
            return null;
        }
        try {
            return (String) Reflection.invokeInstanceMethod(referrerDetails, "getInstallReferrer", null, new Object[0]);
        } catch (Exception e) {
            this.logger.error("getStringInstallReferrer error (%s)", e.getMessage());
            return null;
        }
    }

    private long getReferrerClickTimestampSeconds(Object referrerDetails) {
        long j = -1;
        if (referrerDetails != null) {
            try {
                j = ((Long) Reflection.invokeInstanceMethod(referrerDetails, "getReferrerClickTimestampSeconds", null, new Object[0])).longValue();
            } catch (Exception e) {
                this.logger.error("getReferrerClickTimestampSeconds error (%s)", e.getMessage());
            }
        }
        return j;
    }

    private long getInstallBeginTimestampSeconds(Object referrerDetails) {
        long j = -1;
        if (referrerDetails != null) {
            try {
                j = ((Long) Reflection.invokeInstanceMethod(referrerDetails, "getInstallBeginTimestampSeconds", null, new Object[0])).longValue();
            } catch (Exception e) {
                this.logger.error("getInstallBeginTimestampSeconds error (%s)", e.getMessage());
            }
        }
        return j;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void retry() {
        /*
        r7 = this;
        r6 = 0;
        r3 = r7.flagLock;
        monitor-enter(r3);
        r2 = r7.hasInstallReferrerBeenRead;	 Catch:{ all -> 0x002a }
        if (r2 == 0) goto L_0x0014;
    L_0x0008:
        r2 = r7.logger;	 Catch:{ all -> 0x002a }
        r4 = "Install referrer has already been read";
        r5 = 0;
        r5 = new java.lang.Object[r5];	 Catch:{ all -> 0x002a }
        r2.debug(r4, r5);	 Catch:{ all -> 0x002a }
        monitor-exit(r3);	 Catch:{ all -> 0x002a }
    L_0x0013:
        return;
    L_0x0014:
        monitor-exit(r3);	 Catch:{ all -> 0x002a }
        r2 = r7.retries;
        r2 = r2 + 1;
        r7.retries = r2;
        r2 = r7.retries;
        r3 = 2;
        if (r2 <= r3) goto L_0x002d;
    L_0x0020:
        r2 = r7.logger;
        r3 = "Limit number of retry for install referrer surpassed";
        r4 = new java.lang.Object[r6];
        r2.debug(r3, r4);
        goto L_0x0013;
    L_0x002a:
        r2 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x002a }
        throw r2;
    L_0x002d:
        r2 = r7.retryTimer;
        r0 = r2.getFireIn();
        r2 = 0;
        r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r2 <= 0) goto L_0x004a;
    L_0x0039:
        r2 = r7.logger;
        r3 = "Already waiting to retry to read install referrer in %d milliseconds";
        r4 = 1;
        r4 = new java.lang.Object[r4];
        r5 = java.lang.Long.valueOf(r0);
        r4[r6] = r5;
        r2.debug(r3, r4);
        goto L_0x0013;
    L_0x004a:
        r2 = r7.retryTimer;
        r3 = r7.retryWaitTime;
        r4 = (long) r3;
        r2.startIn(r4);
        goto L_0x0013;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adjust.sdk.InstallReferrer.retry():void");
    }

    private void closeReferrerClient() {
        if (this.referrerClient != null) {
            try {
                Reflection.invokeInstanceMethod(this.referrerClient, "endConnection", null, new Object[0]);
            } catch (Exception e) {
                this.logger.error("closeReferrerClient error (%s)", e.getMessage());
            }
            this.referrerClient = null;
        }
    }
}
