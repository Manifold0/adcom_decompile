package com.google.android.gms.internal.nearby;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzbv extends TaskApiCall<zzx, Void> {
    private final /* synthetic */ zzbd zzcq;
    private final /* synthetic */ zzbw zzcs;

    zzbv(zzbd zzbd, zzbw zzbw) {
        this.zzcq = zzbd;
        this.zzcs = zzbw;
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        this.zzcs.zza((zzx) anyClient, new zzby(this.zzcq, taskCompletionSource));
    }
}
