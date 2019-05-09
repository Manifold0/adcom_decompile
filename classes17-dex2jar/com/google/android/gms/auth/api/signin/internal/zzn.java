// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.signin.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

final class zzn extends zzc
{
    private final /* synthetic */ zzm zzbm;
    
    zzn(final zzm zzbm) {
        this.zzbm = zzbm;
    }
    
    @Override
    public final void zzf(final Status result) throws RemoteException {
        this.zzbm.setResult((Result)result);
    }
}
