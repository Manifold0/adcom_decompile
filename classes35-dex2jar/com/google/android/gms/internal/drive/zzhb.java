// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;

public class zzhb<T> extends zzl
{
    private TaskCompletionSource<T> zzif;
    
    zzhb(final TaskCompletionSource<T> zzif) {
        this.zzif = zzif;
    }
    
    @Override
    public final void zza(final Status status) throws RemoteException {
        this.zzif.setException((Exception)new ApiException(status));
    }
    
    public final TaskCompletionSource<T> zzap() {
        return this.zzif;
    }
}
