package com.google.android.gms.internal.nearby;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzbn extends TaskApiCall<zzx, Void> {
    private final /* synthetic */ zzbz zzcm;

    zzbn(zzbd zzbd, zzbz zzbz) {
        this.zzcm = zzbz;
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        this.zzcm.zzb((zzx) anyClient);
        taskCompletionSource.setResult(null);
    }
}
