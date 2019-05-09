// 
// Decompiled by Procyon v0.5.34
// 

package com.adjust.sdk;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import android.content.Context;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;

public class InstallReferrer implements InvocationHandler
{
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
    private ILogger logger;
    private Object referrerClient;
    private int retries;
    private TimerOnce retryTimer;
    private int retryWaitTime;
    
    public InstallReferrer(final Context context, final IActivityHandler activityHandler) {
        this.retryWaitTime = 3000;
        this.logger = AdjustFactory.getLogger();
        this.context = context;
        this.flagLock = new Object();
        this.hasInstallReferrerBeenRead = false;
        this.retries = 0;
        this.retryTimer = new TimerOnce(new Runnable() {
            @Override
            public void run() {
                InstallReferrer.this.startConnection();
            }
        }, "InstallReferrer");
        this.activityHandlerWeakRef = new WeakReference<IActivityHandler>(activityHandler);
        this.startConnection();
    }
    
    private void closeReferrerClient() {
        if (this.referrerClient == null) {
            return;
        }
        while (true) {
            try {
                Reflection.invokeInstanceMethod(this.referrerClient, "endConnection", null, new Object[0]);
                this.referrerClient = null;
            }
            catch (Exception ex) {
                this.logger.error("closeReferrerClient error (%s)", ex.getMessage());
                continue;
            }
            break;
        }
    }
    
    private long getInstallBeginTimestampSeconds(final Object o) {
        if (o == null) {
            return -1L;
        }
        try {
            return (long)Reflection.invokeInstanceMethod(o, "getInstallBeginTimestampSeconds", null, new Object[0]);
        }
        catch (Exception ex) {
            this.logger.error("getInstallBeginTimestampSeconds error (%s)", ex.getMessage());
            return -1L;
        }
    }
    
    private Object getInstallReferrer() {
        if (this.referrerClient == null) {
            return null;
        }
        try {
            return Reflection.invokeInstanceMethod(this.referrerClient, "getInstallReferrer", null, new Object[0]);
        }
        catch (Exception ex) {
            this.logger.error("getInstallReferrer error (%s)", ex.getMessage());
            return null;
        }
    }
    
    private long getReferrerClickTimestampSeconds(final Object o) {
        if (o == null) {
            return -1L;
        }
        try {
            return (long)Reflection.invokeInstanceMethod(o, "getReferrerClickTimestampSeconds", null, new Object[0]);
        }
        catch (Exception ex) {
            this.logger.error("getReferrerClickTimestampSeconds error (%s)", ex.getMessage());
            return -1L;
        }
    }
    
    private String getStringInstallReferrer(final Object o) {
        if (o == null) {
            return null;
        }
        try {
            return (String)Reflection.invokeInstanceMethod(o, "getInstallReferrer", null, new Object[0]);
        }
        catch (Exception ex) {
            this.logger.error("getStringInstallReferrer error (%s)", ex.getMessage());
            return null;
        }
    }
    
    private void retry() {
        synchronized (this.flagLock) {
            if (this.hasInstallReferrerBeenRead) {
                this.logger.debug("Install referrer has already been read", new Object[0]);
                return;
            }
            // monitorexit(this.flagLock)
            ++this.retries;
            if (this.retries > 2) {
                this.logger.debug("Limit number of retry for install referrer surpassed", new Object[0]);
                return;
            }
        }
        final long fireIn = this.retryTimer.getFireIn();
        if (fireIn > 0L) {
            this.logger.debug("Already waiting to retry to read install referrer in %d milliseconds", fireIn);
            return;
        }
        this.retryTimer.startIn(this.retryWaitTime);
    }
    
    public Object createInstallReferrerClient(final Context context) {
        if (context == null) {
            return null;
        }
        try {
            return Reflection.invokeInstanceMethod(Reflection.invokeStaticMethod("com.android.installreferrer.api.InstallReferrerClient", "newBuilder", new Class[] { Context.class }, context), "build", null, new Object[0]);
        }
        catch (Exception ex) {
            this.logger.warn("Couldn't create instance of referrer client (%s)", ex.getMessage());
            return null;
        }
    }
    
    public Object createProxyInstallReferrerStateListener(final Class clazz) {
        if (clazz == null) {
            return null;
        }
        return Proxy.newProxyInstance(clazz.getClassLoader(), new Class[] { clazz }, this);
    }
    
    public Class getInstallReferrerStateListenerClass() {
        try {
            return Class.forName("com.android.installreferrer.api.InstallReferrerStateListener");
        }
        catch (Exception ex) {
            this.logger.error("getInstallReferrerStateListenerClass error (%s)", ex.getMessage());
            return null;
        }
    }
    
    @Override
    public Object invoke(final Object o, final Method method, final Object[] array) throws Throwable {
        try {
            final String name = method.getName();
            this.logger.debug("InstallReferrer invoke method name: %s", name);
            for (int length = array.length, i = 0; i < length; ++i) {
                this.logger.debug("InstallReferrer invoke arg: %s", array[i]);
            }
            if (name.equals("onInstallReferrerSetupFinished")) {
                this.onInstallReferrerSetupFinishedInt((int)array[0]);
            }
            else if (name.equals("onInstallReferrerServiceDisconnected")) {
                this.logger.debug("InstallReferrer onInstallReferrerServiceDisconnected", new Object[0]);
            }
        }
        catch (Exception ex) {
            this.logger.error("InstallReferrer invoke error (%s)", ex.getMessage());
        }
        return null;
    }
    
    public void onInstallReferrerSetupFinishedInt(final int n) {
        switch (n) {
            default: {
                this.logger.debug("Unexpected response code of install referrer response: %d", n);
                break;
            }
            case 0: {
                try {
                    final Object installReferrer = this.getInstallReferrer();
                    final String stringInstallReferrer = this.getStringInstallReferrer(installReferrer);
                    final long referrerClickTimestampSeconds = this.getReferrerClickTimestampSeconds(installReferrer);
                    final long installBeginTimestampSeconds = this.getInstallBeginTimestampSeconds(installReferrer);
                    this.logger.debug("installReferrer: %s, clickTime: %d, installBeginTime: %d", stringInstallReferrer, referrerClickTimestampSeconds, installBeginTimestampSeconds);
                    final IActivityHandler activityHandler = this.activityHandlerWeakRef.get();
                    if (activityHandler != null) {
                        activityHandler.sendInstallReferrer(referrerClickTimestampSeconds, installBeginTimestampSeconds, stringInstallReferrer);
                    }
                    synchronized (this.flagLock) {
                        this.hasInstallReferrerBeenRead = true;
                    }
                }
                catch (Exception ex) {
                    this.logger.debug("Couldn't get install referrer from client (%s). Retrying ...", ex.getMessage());
                    this.retry();
                    break;
                }
            }
            case 2: {
                this.logger.debug("Install referrer not available on the current Play Store app.", new Object[0]);
                break;
            }
            case 1: {
                this.logger.debug("Could not initiate connection to the Install Referrer service. Retrying ...", new Object[0]);
                this.retry();
                break;
            }
            case 3: {
                this.logger.debug("Install referrer general errors caused by incorrect usage. Retrying ...", new Object[0]);
                this.retry();
                break;
            }
            case -1: {
                this.logger.debug("Play Store service is not connected now. Retrying ...", new Object[0]);
                this.retry();
                break;
            }
        }
        this.closeReferrerClient();
    }
    
    public void startConnection() {
        this.closeReferrerClient();
        Object o = this.flagLock;
        synchronized (o) {
            if (this.hasInstallReferrerBeenRead) {
                this.logger.debug("Install referrer has already been read", new Object[0]);
                return;
            }
            // monitorexit(o)
            this.referrerClient = this.createInstallReferrerClient(this.context);
            o = this.getInstallReferrerStateListenerClass();
            this.startConnection((Class)o, this.createProxyInstallReferrerStateListener((Class)o));
        }
    }
    
    public void startConnection(final Class clazz, final Object o) {
        if (this.referrerClient == null || clazz == null || o == null) {
            return;
        }
        try {
            Reflection.invokeInstanceMethod(this.referrerClient, "startConnection", new Class[] { clazz }, o);
        }
        catch (Exception ex) {
            this.logger.error("startConnection error (%s)", ex.getMessage());
        }
    }
}
