// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import android.support.v4.app.FragmentActivity;
import com.google.android.gms.common.api.Api;
import java.io.PrintWriter;
import java.io.FileDescriptor;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import android.support.annotation.NonNull;
import java.util.concurrent.TimeUnit;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

public class zaag extends GoogleApiClient
{
    private final String zafs;
    
    public zaag(final String zafs) {
        this.zafs = zafs;
    }
    
    @Override
    public ConnectionResult blockingConnect() {
        throw new UnsupportedOperationException(this.zafs);
    }
    
    @Override
    public ConnectionResult blockingConnect(final long n, @NonNull final TimeUnit timeUnit) {
        throw new UnsupportedOperationException(this.zafs);
    }
    
    @Override
    public PendingResult<Status> clearDefaultAccountAndReconnect() {
        throw new UnsupportedOperationException(this.zafs);
    }
    
    @Override
    public void connect() {
        throw new UnsupportedOperationException(this.zafs);
    }
    
    @Override
    public void disconnect() {
        throw new UnsupportedOperationException(this.zafs);
    }
    
    @Override
    public void dump(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        throw new UnsupportedOperationException(this.zafs);
    }
    
    @NonNull
    @Override
    public ConnectionResult getConnectionResult(@NonNull final Api<?> api) {
        throw new UnsupportedOperationException(this.zafs);
    }
    
    @Override
    public boolean hasConnectedApi(@NonNull final Api<?> api) {
        throw new UnsupportedOperationException(this.zafs);
    }
    
    @Override
    public boolean isConnected() {
        throw new UnsupportedOperationException(this.zafs);
    }
    
    @Override
    public boolean isConnecting() {
        throw new UnsupportedOperationException(this.zafs);
    }
    
    @Override
    public boolean isConnectionCallbacksRegistered(@NonNull final ConnectionCallbacks connectionCallbacks) {
        throw new UnsupportedOperationException(this.zafs);
    }
    
    @Override
    public boolean isConnectionFailedListenerRegistered(@NonNull final OnConnectionFailedListener onConnectionFailedListener) {
        throw new UnsupportedOperationException(this.zafs);
    }
    
    @Override
    public void reconnect() {
        throw new UnsupportedOperationException(this.zafs);
    }
    
    @Override
    public void registerConnectionCallbacks(@NonNull final ConnectionCallbacks connectionCallbacks) {
        throw new UnsupportedOperationException(this.zafs);
    }
    
    @Override
    public void registerConnectionFailedListener(@NonNull final OnConnectionFailedListener onConnectionFailedListener) {
        throw new UnsupportedOperationException(this.zafs);
    }
    
    @Override
    public void stopAutoManage(@NonNull final FragmentActivity fragmentActivity) {
        throw new UnsupportedOperationException(this.zafs);
    }
    
    @Override
    public void unregisterConnectionCallbacks(@NonNull final ConnectionCallbacks connectionCallbacks) {
        throw new UnsupportedOperationException(this.zafs);
    }
    
    @Override
    public void unregisterConnectionFailedListener(@NonNull final OnConnectionFailedListener onConnectionFailedListener) {
        throw new UnsupportedOperationException(this.zafs);
    }
}
