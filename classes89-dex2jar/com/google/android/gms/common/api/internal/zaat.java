// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.signin.internal.zad;
import android.os.Bundle;
import com.google.android.gms.common.api.GoogleApiClient;

final class zaat implements ConnectionCallbacks, OnConnectionFailedListener
{
    private final /* synthetic */ zaak zagj;
    
    private zaat(final zaak zagj) {
        this.zagj = zagj;
    }
    
    @Override
    public final void onConnected(final Bundle bundle) {
        if (this.zagj.zaet.isSignInClientDisconnectFixEnabled()) {
            this.zagj.zaeo.lock();
            try {
                if (this.zagj.zagb == null) {
                    return;
                }
                this.zagj.zagb.zaa(new zaar(this.zagj));
                return;
            }
            finally {
                this.zagj.zaeo.unlock();
            }
        }
        this.zagj.zagb.zaa(new zaar(this.zagj));
    }
    
    @Override
    public final void onConnectionFailed(@NonNull final ConnectionResult connectionResult) {
        this.zagj.zaeo.lock();
        try {
            if (this.zagj.zad(connectionResult)) {
                this.zagj.zaar();
                this.zagj.zaap();
            }
            else {
                this.zagj.zae(connectionResult);
            }
        }
        finally {
            this.zagj.zaeo.unlock();
        }
    }
    
    @Override
    public final void onConnectionSuspended(final int n) {
    }
}
