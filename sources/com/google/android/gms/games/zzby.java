package com.google.android.gms.games;

import android.support.annotation.NonNull;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ApiExceptionUtil;
import com.google.android.gms.games.SnapshotsClient.SnapshotContentUnavailableApiException;
import com.google.android.gms.games.internal.zzp;
import com.google.android.gms.games.snapshot.SnapshotMetadata;
import com.google.android.gms.games.snapshot.Snapshots.OpenSnapshotResult;

final class zzby implements zzp<OpenSnapshotResult> {
    zzby() {
    }

    public final /* synthetic */ ApiException zza(@NonNull Status status, @NonNull Object obj) {
        OpenSnapshotResult openSnapshotResult = (OpenSnapshotResult) obj;
        return (status.getStatusCode() != GamesClientStatusCodes.SNAPSHOT_CONTENTS_UNAVAILABLE || openSnapshotResult.getSnapshot() == null || openSnapshotResult.getSnapshot().getMetadata() == null) ? ApiExceptionUtil.fromStatus(status) : new SnapshotContentUnavailableApiException(status, (SnapshotMetadata) openSnapshotResult.getSnapshot().getMetadata().freeze());
    }
}
