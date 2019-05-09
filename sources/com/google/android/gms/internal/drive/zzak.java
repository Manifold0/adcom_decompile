package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.drive.DriveApi.DriveContentsResult;

final class zzak extends zzl {
    private final ResultHolder<DriveContentsResult> zzdv;

    zzak(ResultHolder<DriveContentsResult> resultHolder) {
        this.zzdv = resultHolder;
    }

    public final void zza(Status status) throws RemoteException {
        this.zzdv.setResult(new zzal(status, null));
    }

    public final void zza(zzfb zzfb) throws RemoteException {
        this.zzdv.setResult(new zzal(Status.RESULT_SUCCESS, new zzbi(zzfb.zzeq)));
    }
}
