// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal;

import android.os.IBinder;
import com.google.android.gms.common.api.GoogleApiClient;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.common.api.Api;
import android.os.IInterface;

public class SimpleClientAdapter<T extends IInterface> extends GmsClient<T>
{
    private final SimpleClient<T> zapg;
    
    public SimpleClientAdapter(final Context context, final Looper looper, final int n, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, final ClientSettings clientSettings, final SimpleClient<T> zapg) {
        super(context, looper, n, clientSettings, connectionCallbacks, onConnectionFailedListener);
        this.zapg = zapg;
    }
    
    protected T createServiceInterface(final IBinder binder) {
        return this.zapg.createServiceInterface(binder);
    }
    
    public SimpleClient<T> getClient() {
        return this.zapg;
    }
    
    @Override
    public int getMinApkVersion() {
        return super.getMinApkVersion();
    }
    
    protected String getServiceDescriptor() {
        return this.zapg.getServiceDescriptor();
    }
    
    protected String getStartServiceAction() {
        return this.zapg.getStartServiceAction();
    }
    
    protected void onSetConnectState(final int n, final T t) {
        this.zapg.setState(n, t);
    }
}
