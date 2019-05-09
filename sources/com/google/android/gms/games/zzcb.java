package com.google.android.gms.games;

import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil.ResultConverter;
import com.google.android.gms.games.snapshot.Snapshots.OpenSnapshotResult;

final class zzcb implements ResultConverter<OpenSnapshotResult, OpenSnapshotResult> {
    zzcb() {
    }

    public final /* synthetic */ Object convert(@Nullable Result result) {
        return (OpenSnapshotResult) result;
    }
}
