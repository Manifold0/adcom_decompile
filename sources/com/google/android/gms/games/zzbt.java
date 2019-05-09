package com.google.android.gms.games;

import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil.ResultConverter;
import com.google.android.gms.games.SnapshotsClient.DataOrConflict;
import com.google.android.gms.games.SnapshotsClient.SnapshotConflict;
import com.google.android.gms.games.snapshot.Snapshot;
import com.google.android.gms.games.snapshot.Snapshots.OpenSnapshotResult;

final class zzbt implements ResultConverter<OpenSnapshotResult, DataOrConflict<Snapshot>> {
    zzbt() {
    }

    public final /* synthetic */ Object convert(@Nullable Result result) {
        OpenSnapshotResult openSnapshotResult = (OpenSnapshotResult) result;
        if (openSnapshotResult == null) {
            return null;
        }
        Snapshot snapshot = openSnapshotResult.getSnapshot() != null ? (Snapshot) openSnapshotResult.getSnapshot().freeze() : null;
        if (openSnapshotResult.getStatus().getStatusCode() == 0) {
            return new DataOrConflict(snapshot, null);
        }
        if (openSnapshotResult.getStatus().getStatusCode() != GamesStatusCodes.STATUS_SNAPSHOT_CONFLICT) {
            return null;
        }
        SnapshotConflict snapshotConflict = (snapshot == null || openSnapshotResult.getConflictId() == null || openSnapshotResult.getConflictingSnapshot() == null || openSnapshotResult.getResolutionSnapshotContents() == null) ? null : new SnapshotConflict(snapshot, openSnapshotResult.getConflictId(), (Snapshot) openSnapshotResult.getConflictingSnapshot().freeze(), openSnapshotResult.getResolutionSnapshotContents());
        return snapshotConflict != null ? new DataOrConflict(null, snapshotConflict) : null;
    }
}
