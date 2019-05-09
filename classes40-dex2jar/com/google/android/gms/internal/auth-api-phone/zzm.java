// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.auth-api-phone;

import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.common.api.Status;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.common.api.internal.TaskApiCall;

abstract class zzm extends TaskApiCall<zzi, Void>
{
    private TaskCompletionSource<Void> zzf;
    
    private zzm() {
    }
    
    protected abstract void zza(final zze p0) throws RemoteException;
    
    protected final void zzb(final Status status) {
        TaskUtil.setResultOrApiException(status, (TaskCompletionSource)this.zzf);
    }
}
