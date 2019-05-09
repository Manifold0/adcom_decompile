package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.drive.DriveApi.DriveContentsResult;
import com.google.android.gms.drive.DriveFile.DownloadProgressListener;

final class zzgf extends zzl {
    private final ResultHolder<DriveContentsResult> zzdv;
    private final DownloadProgressListener zzia;

    zzgf(ResultHolder<DriveContentsResult> resultHolder, DownloadProgressListener downloadProgressListener) {
        this.zzdv = resultHolder;
        this.zzia = downloadProgressListener;
    }

    public final void zza(Status status) throws RemoteException {
        this.zzdv.setResult(new zzal(status, null));
    }

    public final void zza(zzfb zzfb) throws RemoteException {
        this.zzdv.setResult(new zzal(zzfb.zzhf ? new Status(-1) : Status.RESULT_SUCCESS, new zzbi(zzfb.zzeq)));
    }

    public final void zza(zzff zzff) throws RemoteException {
        if (this.zzia != null) {
            this.zzia.onProgress(zzff.zzhi, zzff.zzhj);
        }
    }
}
