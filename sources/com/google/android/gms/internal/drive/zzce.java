package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.drive.DrivePreferencesApi.FileUploadPreferencesResult;

final class zzce extends zzl {
    private final ResultHolder<FileUploadPreferencesResult> zzdv;
    private final /* synthetic */ zzcb zzfi;

    private zzce(zzcb zzcb, ResultHolder<FileUploadPreferencesResult> resultHolder) {
        this.zzfi = zzcb;
        this.zzdv = resultHolder;
    }

    public final void zza(Status status) throws RemoteException {
        this.zzdv.setResult(new zzcf(this.zzfi, status, null));
    }

    public final void zza(zzfd zzfd) throws RemoteException {
        this.zzdv.setResult(new zzcf(this.zzfi, Status.RESULT_SUCCESS, zzfd.zzhg));
    }
}
