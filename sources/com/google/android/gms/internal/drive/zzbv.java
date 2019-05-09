package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.drive.DriveFolder.DriveFileResult;

final class zzbv extends zzl {
    private final ResultHolder<DriveFileResult> zzdv;

    public zzbv(ResultHolder<DriveFileResult> resultHolder) {
        this.zzdv = resultHolder;
    }

    public final void zza(Status status) throws RemoteException {
        this.zzdv.setResult(new zzbx(status, null));
    }

    public final void zza(zzfh zzfh) throws RemoteException {
        this.zzdv.setResult(new zzbx(Status.RESULT_SUCCESS, new zzbn(zzfh.zzdb)));
    }
}
