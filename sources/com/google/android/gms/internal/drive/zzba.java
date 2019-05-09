package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.DriveId;

final class zzba extends zzav {
    private final /* synthetic */ DriveId zzel;
    private final /* synthetic */ int zzem = 1;

    zzba(zzaw zzaw, GoogleApiClient googleApiClient, DriveId driveId, int i) {
        this.zzel = driveId;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzeo) ((zzaw) anyClient).getService()).zza(new zzgm(this.zzel, this.zzem), null, null, new zzgs(this));
    }
}
