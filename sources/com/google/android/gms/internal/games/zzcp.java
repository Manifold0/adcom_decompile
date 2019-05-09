package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.snapshot.SnapshotMetadata;
import com.google.android.gms.games.snapshot.Snapshots.CommitSnapshotResult;

final class zzcp implements CommitSnapshotResult {
    private final /* synthetic */ Status zzbc;

    zzcp(zzco zzco, Status status) {
        this.zzbc = status;
    }

    public final SnapshotMetadata getSnapshotMetadata() {
        return null;
    }

    public final Status getStatus() {
        return this.zzbc;
    }
}
