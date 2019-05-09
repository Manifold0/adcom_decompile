package com.google.android.gms.games;

import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil.ResultConverter;
import com.google.android.gms.games.snapshot.SnapshotMetadata;
import com.google.android.gms.games.snapshot.Snapshots.CommitSnapshotResult;

final class zzca implements ResultConverter<CommitSnapshotResult, SnapshotMetadata> {
    zzca() {
    }

    public final /* synthetic */ Object convert(@Nullable Result result) {
        CommitSnapshotResult commitSnapshotResult = (CommitSnapshotResult) result;
        if (commitSnapshotResult != null) {
            SnapshotMetadata snapshotMetadata = commitSnapshotResult.getSnapshotMetadata();
            if (snapshotMetadata != null) {
                return (SnapshotMetadata) snapshotMetadata.freeze();
            }
        }
        return null;
    }
}
