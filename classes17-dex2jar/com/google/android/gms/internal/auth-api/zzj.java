// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.auth-api;

import android.os.RemoteException;
import android.content.Context;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.auth.api.credentials.CredentialRequest;
import com.google.android.gms.auth.api.credentials.CredentialRequestResult;

final class zzj extends zzp<CredentialRequestResult>
{
    private final /* synthetic */ CredentialRequest zzam;
    
    zzj(final zzi zzi, final GoogleApiClient googleApiClient, final CredentialRequest zzam) {
        this.zzam = zzam;
        super(googleApiClient);
    }
    
    @Override
    protected final void zzc(final Context context, final zzw zzw) throws RemoteException {
        zzw.zzc(new zzk(this), this.zzam);
    }
}
