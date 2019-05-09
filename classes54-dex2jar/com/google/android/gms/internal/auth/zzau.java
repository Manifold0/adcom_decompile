// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.auth;

import android.os.RemoteException;
import android.content.Context;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzau extends zzaq
{
    zzau(final zzar zzar, final GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }
    
    @Override
    protected final void zza(final Context context, final zzan zzan) throws RemoteException {
        zzan.zza(new zzav(this));
    }
}
