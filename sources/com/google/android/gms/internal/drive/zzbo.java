package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ICancelToken.Stub;
import com.google.android.gms.drive.DriveFile.DownloadProgressListener;

final class zzbo extends zzam {
    private final /* synthetic */ int zzdt;
    private final /* synthetic */ DownloadProgressListener zzew;
    private final /* synthetic */ zzbn zzex;

    zzbo(zzbn zzbn, GoogleApiClient googleApiClient, int i, DownloadProgressListener downloadProgressListener) {
        this.zzex = zzbn;
        this.zzdt = i;
        this.zzew = downloadProgressListener;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        setCancelToken(Stub.asInterface(((zzeo) ((zzaw) anyClient).getService()).zza(new zzgd(this.zzex.getDriveId(), this.zzdt, 0), new zzgf(this, this.zzew)).zzgq));
    }
}
