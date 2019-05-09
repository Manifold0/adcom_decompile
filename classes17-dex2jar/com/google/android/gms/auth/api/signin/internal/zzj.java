// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.signin.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

final class zzj extends zzc
{
    private final /* synthetic */ zzi zzbk;
    
    zzj(final zzi zzbk) {
        this.zzbk = zzbk;
    }
    
    @Override
    public final void zzc(final GoogleSignInAccount googleSignInAccount, final Status status) throws RemoteException {
        if (googleSignInAccount != null) {
            zzp.zzd(this.zzbk.val$context).zzc(this.zzbk.zzbj, googleSignInAccount);
        }
        this.zzbk.setResult((Result)new GoogleSignInResult(googleSignInAccount, status));
    }
}
