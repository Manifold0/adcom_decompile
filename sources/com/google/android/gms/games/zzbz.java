package com.google.android.gms.games;

import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil.ResultConverter;
import com.google.android.gms.games.snapshot.Snapshots.DeleteSnapshotResult;

final class zzbz implements ResultConverter<DeleteSnapshotResult, String> {
    zzbz() {
    }

    public final /* synthetic */ Object convert(@Nullable Result result) {
        DeleteSnapshotResult deleteSnapshotResult = (DeleteSnapshotResult) result;
        return deleteSnapshotResult == null ? null : deleteSnapshotResult.getSnapshotId();
    }
}
