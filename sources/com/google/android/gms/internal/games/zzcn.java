package com.google.android.gms.internal.games;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.games.snapshot.SnapshotContents;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange;

final class zzcn extends zzcu {
    private final /* synthetic */ SnapshotMetadataChange zzkd;
    private final /* synthetic */ String zzkf;
    private final /* synthetic */ String zzkg;
    private final /* synthetic */ SnapshotContents zzkh;

    zzcn(zzci zzci, GoogleApiClient googleApiClient, String str, String str2, SnapshotMetadataChange snapshotMetadataChange, SnapshotContents snapshotContents) {
        this.zzkf = str;
        this.zzkg = str2;
        this.zzkd = snapshotMetadataChange;
        this.zzkh = snapshotContents;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zze) anyClient).zza((ResultHolder) this, this.zzkf, this.zzkg, this.zzkd, this.zzkh);
    }
}
