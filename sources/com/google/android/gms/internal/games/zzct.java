package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.snapshot.SnapshotMetadataBuffer;
import com.google.android.gms.games.snapshot.Snapshots.LoadSnapshotsResult;

final class zzct implements LoadSnapshotsResult {
    private final /* synthetic */ Status zzbc;

    zzct(zzcs zzcs, Status status) {
        this.zzbc = status;
    }

    public final SnapshotMetadataBuffer getSnapshots() {
        return new SnapshotMetadataBuffer(DataHolder.empty(14));
    }

    public final Status getStatus() {
        return this.zzbc;
    }

    public final void release() {
    }
}
