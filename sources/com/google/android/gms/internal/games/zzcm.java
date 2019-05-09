package com.google.android.gms.internal.games;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.games.snapshot.SnapshotMetadata;

final class zzcm extends zzcq {
    private final /* synthetic */ SnapshotMetadata zzke;

    zzcm(zzci zzci, GoogleApiClient googleApiClient, SnapshotMetadata snapshotMetadata) {
        this.zzke = snapshotMetadata;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zze) anyClient).zzi(this, this.zzke.getSnapshotId());
    }
}
