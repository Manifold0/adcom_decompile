// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.location;

import android.os.RemoteException;
import com.google.android.gms.common.internal.ApiExceptionUtil;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.location.zzad;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.internal.location.zzak;

final class zzp extends zzak
{
    private final /* synthetic */ TaskCompletionSource zzab;
    
    zzp(final FusedLocationProviderClient fusedLocationProviderClient, final TaskCompletionSource zzab) {
        this.zzab = zzab;
    }
    
    @Override
    public final void zza(final zzad zzad) throws RemoteException {
        final Status status = zzad.getStatus();
        if (status == null) {
            this.zzab.trySetException((Exception)new ApiException(new Status(8, "Got null status from location service")));
            return;
        }
        if (status.getStatusCode() == 0) {
            this.zzab.setResult((Object)true);
            return;
        }
        this.zzab.trySetException((Exception)ApiExceptionUtil.fromStatus(status));
    }
}
