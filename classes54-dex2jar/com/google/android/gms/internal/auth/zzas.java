// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.auth;

import android.os.RemoteException;
import android.content.Context;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.auth.api.proxy.ProxyRequest;

final class zzas extends zzap
{
    private final /* synthetic */ ProxyRequest zzce;
    
    zzas(final zzar zzar, final GoogleApiClient googleApiClient, final ProxyRequest zzce) {
        this.zzce = zzce;
        super(googleApiClient);
    }
    
    @Override
    protected final void zza(final Context context, final zzan zzan) throws RemoteException {
        zzan.zza(new zzat(this), this.zzce);
    }
}
