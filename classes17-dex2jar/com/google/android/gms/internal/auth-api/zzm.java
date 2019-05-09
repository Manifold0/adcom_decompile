// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.auth-api;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import android.content.Context;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.common.api.Status;

final class zzm extends zzp<Status>
{
    private final /* synthetic */ Credential zzao;
    
    zzm(final zzi zzi, final GoogleApiClient googleApiClient, final Credential zzao) {
        this.zzao = zzao;
        super(googleApiClient);
    }
    
    @Override
    protected final void zzc(final Context context, final zzw zzw) throws RemoteException {
        zzw.zzc(new zzo((BaseImplementation$ResultHolder<Status>)this), new zzs(this.zzao));
    }
}
