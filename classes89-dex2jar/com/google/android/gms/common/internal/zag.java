// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

final class zag implements BaseGmsClient$BaseOnConnectionFailedListener
{
    private final /* synthetic */ GoogleApiClient.OnConnectionFailedListener zaok;
    
    zag(final GoogleApiClient.OnConnectionFailedListener zaok) {
        this.zaok = zaok;
    }
    
    public final void onConnectionFailed(@NonNull final ConnectionResult connectionResult) {
        this.zaok.onConnectionFailed(connectionResult);
    }
}
