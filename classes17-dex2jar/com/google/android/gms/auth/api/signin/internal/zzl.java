// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.signin.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

final class zzl extends zzc
{
    private final /* synthetic */ zzk zzbl;
    
    zzl(final zzk zzbl) {
        this.zzbl = zzbl;
    }
    
    @Override
    public final void zze(final Status result) throws RemoteException {
        this.zzbl.setResult((Result)result);
    }
}
