// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Status;
import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

final class zaaz implements OnConnectionFailedListener
{
    private final /* synthetic */ StatusPendingResult zahj;
    
    zaaz(final zaaw zaaw, final StatusPendingResult zahj) {
        this.zahj = zahj;
    }
    
    @Override
    public final void onConnectionFailed(@NonNull final ConnectionResult connectionResult) {
        this.zahj.setResult(new Status(8));
    }
}
