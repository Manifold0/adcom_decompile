package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzbc extends TaskApiCall<zzaw, DriveId> {
    private final /* synthetic */ String zzdu;

    zzbc(zzbb zzbb, String str) {
        this.zzdu = str;
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        ((zzeo) ((zzaw) anyClient).getService()).zza(new zzek(DriveId.zza(this.zzdu), false), new zzhf(taskCompletionSource));
    }
}
