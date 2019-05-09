package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.drive.DriveResource.MetadataResult;

final class zzdy extends zzl {
    private final ResultHolder<MetadataResult> zzdv;

    public zzdy(ResultHolder<MetadataResult> resultHolder) {
        this.zzdv = resultHolder;
    }

    public final void zza(Status status) throws RemoteException {
        this.zzdv.setResult(new zzdz(status, null));
    }

    public final void zza(zzfs zzfs) throws RemoteException {
        this.zzdv.setResult(new zzdz(Status.RESULT_SUCCESS, new zzaa(zzfs.zzdl)));
    }
}
