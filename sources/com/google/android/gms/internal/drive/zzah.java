package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.DriveFile;

final class zzah extends zzam {
    private final /* synthetic */ int zzdt = DriveFile.MODE_WRITE_ONLY;

    zzah(zzaf zzaf, GoogleApiClient googleApiClient, int i) {
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzeo) ((zzaw) anyClient).getService()).zza(new zzr(this.zzdt), new zzak(this));
    }
}
