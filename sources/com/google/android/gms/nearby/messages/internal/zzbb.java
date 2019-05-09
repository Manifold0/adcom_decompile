package com.google.android.gms.nearby.messages.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzbb extends TaskApiCall<zzah, Void> {
    private final /* synthetic */ zzak zzia;
    private final /* synthetic */ zzbd zzif;

    zzbb(zzak zzak, zzbd zzbd) {
        this.zzia = zzak;
        this.zzif = zzbd;
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        this.zzif.zza((zzah) anyClient, this.zzia.zza(taskCompletionSource));
    }
}
