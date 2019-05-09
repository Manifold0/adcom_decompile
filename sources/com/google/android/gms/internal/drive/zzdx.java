package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.drive.DriveApi.MetadataBufferResult;
import com.google.android.gms.drive.MetadataBuffer;

final class zzdx extends zzl {
    private final ResultHolder<MetadataBufferResult> zzdv;

    public zzdx(ResultHolder<MetadataBufferResult> resultHolder) {
        this.zzdv = resultHolder;
    }

    public final void zza(Status status) throws RemoteException {
        this.zzdv.setResult(new zzaq(status, null, false));
    }

    public final void zza(zzfp zzfp) throws RemoteException {
        this.zzdv.setResult(new zzaq(Status.RESULT_SUCCESS, new MetadataBuffer(zzfp.zzht), false));
    }
}
