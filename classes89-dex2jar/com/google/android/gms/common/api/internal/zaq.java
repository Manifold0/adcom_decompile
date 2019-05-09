// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import android.support.annotation.Nullable;
import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

public final class zaq implements ConnectionCallbacks, OnConnectionFailedListener
{
    public final Api<?> mApi;
    private final boolean zaec;
    private zar zaed;
    
    public zaq(final Api<?> mApi, final boolean zaec) {
        this.mApi = mApi;
        this.zaec = zaec;
    }
    
    private final void zav() {
        Preconditions.checkNotNull((Object)this.zaed, (Object)"Callbacks must be attached to a ClientConnectionHelper instance before connecting the client.");
    }
    
    @Override
    public final void onConnected(@Nullable final Bundle bundle) {
        this.zav();
        ((GoogleApiClient.ConnectionCallbacks)this.zaed).onConnected(bundle);
    }
    
    @Override
    public final void onConnectionFailed(@NonNull final ConnectionResult connectionResult) {
        this.zav();
        this.zaed.zaa(connectionResult, this.mApi, this.zaec);
    }
    
    @Override
    public final void onConnectionSuspended(final int n) {
        this.zav();
        ((GoogleApiClient.ConnectionCallbacks)this.zaed).onConnectionSuspended(n);
    }
    
    public final void zaa(final zar zaed) {
        this.zaed = zaed;
    }
}
