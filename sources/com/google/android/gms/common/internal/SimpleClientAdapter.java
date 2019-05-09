package com.google.android.gms.common.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.Api.SimpleClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;

public class SimpleClientAdapter<T extends IInterface> extends GmsClient<T> {
    private final SimpleClient<T> zapg;

    public SimpleClientAdapter(Context context, Looper looper, int i, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener, ClientSettings clientSettings, SimpleClient<T> simpleClient) {
        super(context, looper, i, clientSettings, connectionCallbacks, onConnectionFailedListener);
        this.zapg = simpleClient;
    }

    protected String getStartServiceAction() {
        return this.zapg.getStartServiceAction();
    }

    protected String getServiceDescriptor() {
        return this.zapg.getServiceDescriptor();
    }

    protected T createServiceInterface(IBinder iBinder) {
        return this.zapg.createServiceInterface(iBinder);
    }

    protected void onSetConnectState(int i, T t) {
        this.zapg.setState(i, t);
    }

    public SimpleClient<T> getClient() {
        return this.zapg;
    }

    public int getMinApkVersion() {
        return super.getMinApkVersion();
    }
}
