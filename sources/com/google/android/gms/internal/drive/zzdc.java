package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.drive.DriveResource;
import com.google.android.gms.drive.Metadata;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzdc extends TaskApiCall<zzaw, Metadata> {
    private final /* synthetic */ DriveResource zzfo;
    private final /* synthetic */ boolean zzfy = false;

    zzdc(zzch zzch, DriveResource driveResource, boolean z) {
        this.zzfo = driveResource;
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        ((zzeo) ((zzaw) anyClient).getService()).zza(new zzek(this.zzfo.getDriveId(), this.zzfy), new zzhj(taskCompletionSource));
    }
}
