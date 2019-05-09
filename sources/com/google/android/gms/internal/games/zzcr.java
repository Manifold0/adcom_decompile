package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.snapshot.Snapshots.DeleteSnapshotResult;

final class zzcr implements DeleteSnapshotResult {
    private final /* synthetic */ Status zzbc;

    zzcr(zzcq zzcq, Status status) {
        this.zzbc = status;
    }

    public final String getSnapshotId() {
        return null;
    }

    public final Status getStatus() {
        return this.zzbc;
    }
}
