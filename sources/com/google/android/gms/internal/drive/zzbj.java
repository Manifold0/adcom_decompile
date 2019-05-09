package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.DriveFile;

final class zzbj extends zzam {
    private final /* synthetic */ zzbi zzet;

    zzbj(zzbi zzbi, GoogleApiClient googleApiClient) {
        this.zzet = zzbi;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzeo) ((zzaw) anyClient).getService()).zza(new zzgd(this.zzet.getDriveId(), DriveFile.MODE_WRITE_ONLY, this.zzet.zzeq.getRequestId()), new zzgf(this, null));
    }
}
