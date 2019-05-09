package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;

public final class zzgs extends zzl {
    private final ResultHolder<Status> zzdv;

    public zzgs(ResultHolder<Status> resultHolder) {
        this.zzdv = resultHolder;
    }

    public final void onSuccess() throws RemoteException {
        this.zzdv.setResult(Status.RESULT_SUCCESS);
    }

    public final void zza(Status status) throws RemoteException {
        this.zzdv.setResult(status);
    }
}
