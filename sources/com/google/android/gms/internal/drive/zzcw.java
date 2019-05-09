package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzcw extends TaskApiCall<zzaw, DriveContents> {
    private final /* synthetic */ int zzdt = DriveFile.MODE_WRITE_ONLY;

    zzcw(zzch zzch, int i) {
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        ((zzeo) ((zzaw) anyClient).getService()).zza(new zzr(this.zzdt), new zzhc(taskCompletionSource));
    }
}
