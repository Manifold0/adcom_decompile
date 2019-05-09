package com.android.installreferrer.api;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.android.installreferrer.commons.InstallReferrerCommons;
import com.google.android.finsky.externalreferrer.IGetInstallReferrerService;
import com.google.android.finsky.externalreferrer.IGetInstallReferrerService.Stub;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

class InstallReferrerClientImpl extends InstallReferrerClient {
    private static final int PLAY_STORE_MIN_APP_VER = 80837300;
    private static final String SERVICE_ACTION_NAME = "com.google.android.finsky.BIND_GET_INSTALL_REFERRER_SERVICE";
    private static final String SERVICE_NAME = "com.google.android.finsky.externalreferrer.GetInstallReferrerService";
    private static final String SERVICE_PACKAGE_NAME = "com.android.vending";
    private static final String TAG = "InstallReferrerClient";
    private final Context mApplicationContext;
    private int mClientState = 0;
    private IGetInstallReferrerService mService;
    private ServiceConnection mServiceConnection;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ClientState {
        public static final int CLOSED = 3;
        public static final int CONNECTED = 2;
        public static final int CONNECTING = 1;
        public static final int DISCONNECTED = 0;
    }

    private final class InstallReferrerServiceConnection implements ServiceConnection {
        private final InstallReferrerStateListener mListener;

        private InstallReferrerServiceConnection(@NonNull InstallReferrerStateListener listener) {
            if (listener == null) {
                throw new RuntimeException("Please specify a listener to know when setup is done.");
            }
            this.mListener = listener;
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            InstallReferrerCommons.logVerbose(InstallReferrerClientImpl.TAG, "Install Referrer service connected.");
            InstallReferrerClientImpl.this.mService = Stub.asInterface(iBinder);
            InstallReferrerClientImpl.this.mClientState = 2;
            this.mListener.onInstallReferrerSetupFinished(0);
        }

        public void onServiceDisconnected(ComponentName componentName) {
            InstallReferrerCommons.logWarn(InstallReferrerClientImpl.TAG, "Install Referrer service disconnected.");
            InstallReferrerClientImpl.this.mService = null;
            InstallReferrerClientImpl.this.mClientState = 0;
            this.mListener.onInstallReferrerServiceDisconnected();
        }
    }

    public InstallReferrerClientImpl(@NonNull Context context) {
        this.mApplicationContext = context.getApplicationContext();
    }

    public boolean isReady() {
        return (this.mClientState != 2 || this.mService == null || this.mServiceConnection == null) ? false : true;
    }

    public void startConnection(@NonNull InstallReferrerStateListener listener) {
        if (isReady()) {
            InstallReferrerCommons.logVerbose(TAG, "Service connection is valid. No need to re-initialize.");
            listener.onInstallReferrerSetupFinished(0);
        } else if (this.mClientState == 1) {
            InstallReferrerCommons.logWarn(TAG, "Client is already in the process of connecting to the service.");
            listener.onInstallReferrerSetupFinished(3);
        } else if (this.mClientState == 3) {
            InstallReferrerCommons.logWarn(TAG, "Client was already closed and can't be reused. Please create another instance.");
            listener.onInstallReferrerSetupFinished(3);
        } else {
            InstallReferrerCommons.logVerbose(TAG, "Starting install referrer service setup.");
            this.mServiceConnection = new InstallReferrerServiceConnection(listener);
            Intent serviceIntent = new Intent(SERVICE_ACTION_NAME);
            serviceIntent.setComponent(new ComponentName("com.android.vending", SERVICE_NAME));
            List<ResolveInfo> intentServices = this.mApplicationContext.getPackageManager().queryIntentServices(serviceIntent, 0);
            if (!(intentServices == null || intentServices.isEmpty())) {
                ResolveInfo resolveInfo = (ResolveInfo) intentServices.get(0);
                if (resolveInfo.serviceInfo != null) {
                    String packageName = resolveInfo.serviceInfo.packageName;
                    String className = resolveInfo.serviceInfo.name;
                    if ("com.android.vending".equals(packageName) && className != null && isPlayStoreCompatible()) {
                        if (this.mApplicationContext.bindService(new Intent(serviceIntent), this.mServiceConnection, 1)) {
                            InstallReferrerCommons.logVerbose(TAG, "Service was bonded successfully.");
                            return;
                        }
                        InstallReferrerCommons.logWarn(TAG, "Connection to service is blocked.");
                        this.mClientState = 0;
                        listener.onInstallReferrerSetupFinished(1);
                        return;
                    }
                    InstallReferrerCommons.logWarn(TAG, "Play Store missing or incompatible. Version 8.3.73 or later required.");
                    this.mClientState = 0;
                    listener.onInstallReferrerSetupFinished(2);
                    return;
                }
            }
            this.mClientState = 0;
            InstallReferrerCommons.logVerbose(TAG, "Install Referrer service unavailable on device.");
            listener.onInstallReferrerSetupFinished(2);
        }
    }

    public void endConnection() {
        this.mClientState = 3;
        if (this.mServiceConnection != null) {
            InstallReferrerCommons.logVerbose(TAG, "Unbinding from service.");
            this.mApplicationContext.unbindService(this.mServiceConnection);
            this.mServiceConnection = null;
        }
        this.mService = null;
    }

    public ReferrerDetails getInstallReferrer() throws RemoteException {
        if (isReady()) {
            Bundle paramsBundle = new Bundle();
            paramsBundle.putString("package_name", this.mApplicationContext.getPackageName());
            try {
                return new ReferrerDetails(this.mService.getInstallReferrer(paramsBundle));
            } catch (RemoteException e) {
                InstallReferrerCommons.logWarn(TAG, "RemoteException getting install referrer information");
                this.mClientState = 0;
                throw e;
            }
        }
        throw new IllegalStateException("Service not connected. Please start a connection before using the service.");
    }

    private boolean isPlayStoreCompatible() {
        try {
            if (this.mApplicationContext.getPackageManager().getPackageInfo("com.android.vending", 128).versionCode >= PLAY_STORE_MIN_APP_VER) {
                return true;
            }
            return false;
        } catch (NameNotFoundException e) {
            return false;
        }
    }
}
