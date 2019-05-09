package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.drive.DriveFolder.DriveFolderResult;

final class zzbw extends zzl {
    private final ResultHolder<DriveFolderResult> zzdv;

    public zzbw(ResultHolder<DriveFolderResult> resultHolder) {
        this.zzdv = resultHolder;
    }

    public final void zza(Status status) throws RemoteException {
        this.zzdv.setResult(new zzbz(status, null));
    }

    public final void zza(zzfh zzfh) throws RemoteException {
        this.zzdv.setResult(new zzbz(Status.RESULT_SUCCESS, new zzbs(zzfh.zzdb)));
    }
}
