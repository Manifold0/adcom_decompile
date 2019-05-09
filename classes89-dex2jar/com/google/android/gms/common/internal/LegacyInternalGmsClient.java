// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal;

import com.google.android.gms.common.ConnectionResult;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.GoogleApiClient;
import android.content.Context;
import android.os.IInterface;

@Deprecated
public abstract class LegacyInternalGmsClient<T extends IInterface> extends GmsClient<T>
{
    private final GmsClientEventManager zags;
    
    public LegacyInternalGmsClient(final Context context, final int n, final ClientSettings clientSettings, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, context.getMainLooper(), n, clientSettings);
        (this.zags = new GmsClientEventManager(context.getMainLooper(), (GmsClientEventManager.GmsClientEventState)this)).registerConnectionCallbacks(connectionCallbacks);
        this.zags.registerConnectionFailedListener(onConnectionFailedListener);
    }
    
    public void checkAvailabilityAndConnect() {
        this.zags.enableCallbacks();
        super.checkAvailabilityAndConnect();
    }
    
    public void disconnect() {
        this.zags.disableCallbacks();
        super.disconnect();
    }
    
    @Override
    public int getMinApkVersion() {
        return super.getMinApkVersion();
    }
    
    public boolean isConnectionCallbacksRegistered(final GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        return this.zags.isConnectionCallbacksRegistered(connectionCallbacks);
    }
    
    public boolean isConnectionFailedListenerRegistered(final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return this.zags.isConnectionFailedListenerRegistered(onConnectionFailedListener);
    }
    
    public void onConnectedLocked(@NonNull final T t) {
        super.onConnectedLocked((IInterface)t);
        this.zags.onConnectionSuccess(((GmsClientEventManager.GmsClientEventState)this).getConnectionHint());
    }
    
    public void onConnectionFailed(final ConnectionResult connectionResult) {
        super.onConnectionFailed(connectionResult);
        this.zags.onConnectionFailure(connectionResult);
    }
    
    public void onConnectionSuspended(final int n) {
        super.onConnectionSuspended(n);
        this.zags.onUnintentionalDisconnection(n);
    }
    
    public void registerConnectionCallbacks(final GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        this.zags.registerConnectionCallbacks(connectionCallbacks);
    }
    
    public void registerConnectionFailedListener(final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this.zags.registerConnectionFailedListener(onConnectionFailedListener);
    }
    
    public void unregisterConnectionCallbacks(final GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        this.zags.unregisterConnectionCallbacks(connectionCallbacks);
    }
    
    public void unregisterConnectionFailedListener(final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this.zags.unregisterConnectionFailedListener(onConnectionFailedListener);
    }
}
