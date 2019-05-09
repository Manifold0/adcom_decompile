package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.drive.DriveApi.DriveIdResult;

final class zzan extends zzl {
    private final ResultHolder<DriveIdResult> zzdv;

    public zzan(ResultHolder<DriveIdResult> resultHolder) {
        this.zzdv = resultHolder;
    }

    public final void zza(Status status) throws RemoteException {
        this.zzdv.setResult(new zzao(status, null));
    }

    public final void zza(zzfh zzfh) throws RemoteException {
        this.zzdv.setResult(new zzao(Status.RESULT_SUCCESS, zzfh.zzdb));
    }

    public final void zza(zzfs zzfs) throws RemoteException {
        this.zzdv.setResult(new zzao(Status.RESULT_SUCCESS, new zzaa(zzfs.zzdl).getDriveId()));
    }
}
