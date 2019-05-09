package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.drive.TransferPreferences;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzbe extends TaskApiCall<zzaw, Void> {
    private final /* synthetic */ TransferPreferences zzen;

    zzbe(zzbb zzbb, TransferPreferences transferPreferences) {
        this.zzen = transferPreferences;
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        ((zzeo) ((zzaw) anyClient).getService()).zza(new zzgo(new zzei(this.zzen)), new zzhl(taskCompletionSource));
    }
}
