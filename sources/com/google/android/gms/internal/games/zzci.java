package com.google.android.gms.internal.games;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.snapshot.Snapshot;
import com.google.android.gms.games.snapshot.SnapshotContents;
import com.google.android.gms.games.snapshot.SnapshotMetadata;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange.Builder;
import com.google.android.gms.games.snapshot.Snapshots;
import com.google.android.gms.games.snapshot.Snapshots.CommitSnapshotResult;
import com.google.android.gms.games.snapshot.Snapshots.DeleteSnapshotResult;
import com.google.android.gms.games.snapshot.Snapshots.LoadSnapshotsResult;
import com.google.android.gms.games.snapshot.Snapshots.OpenSnapshotResult;

public final class zzci implements Snapshots {
    public final PendingResult<CommitSnapshotResult> commitAndClose(GoogleApiClient googleApiClient, Snapshot snapshot, SnapshotMetadataChange snapshotMetadataChange) {
        return googleApiClient.execute(new zzcl(this, googleApiClient, snapshot, snapshotMetadataChange));
    }

    public final PendingResult<DeleteSnapshotResult> delete(GoogleApiClient googleApiClient, SnapshotMetadata snapshotMetadata) {
        return googleApiClient.execute(new zzcm(this, googleApiClient, snapshotMetadata));
    }

    public final void discardAndClose(GoogleApiClient googleApiClient, Snapshot snapshot) {
        Games.zza(googleApiClient).zzb(snapshot);
    }

    public final int getMaxCoverImageSize(GoogleApiClient googleApiClient) {
        return Games.zza(googleApiClient).zzaw();
    }

    public final int getMaxDataSize(GoogleApiClient googleApiClient) {
        return Games.zza(googleApiClient).zzau();
    }

    public final Intent getSelectSnapshotIntent(GoogleApiClient googleApiClient, String str, boolean z, boolean z2, int i) {
        return Games.zza(googleApiClient).zzb(str, z, z2, i);
    }

    public final SnapshotMetadata getSnapshotFromBundle(Bundle bundle) {
        return (bundle == null || !bundle.containsKey("com.google.android.gms.games.SNAPSHOT_METADATA")) ? null : (SnapshotMetadata) bundle.getParcelable("com.google.android.gms.games.SNAPSHOT_METADATA");
    }

    public final PendingResult<LoadSnapshotsResult> load(GoogleApiClient googleApiClient, boolean z) {
        return googleApiClient.enqueue(new zzcj(this, googleApiClient, z));
    }

    public final PendingResult<OpenSnapshotResult> open(GoogleApiClient googleApiClient, SnapshotMetadata snapshotMetadata) {
        return open(googleApiClient, snapshotMetadata.getUniqueName(), false);
    }

    public final PendingResult<OpenSnapshotResult> open(GoogleApiClient googleApiClient, SnapshotMetadata snapshotMetadata, int i) {
        return open(googleApiClient, snapshotMetadata.getUniqueName(), false, i);
    }

    public final PendingResult<OpenSnapshotResult> open(GoogleApiClient googleApiClient, String str, boolean z) {
        return open(googleApiClient, str, z, -1);
    }

    public final PendingResult<OpenSnapshotResult> open(GoogleApiClient googleApiClient, String str, boolean z, int i) {
        return googleApiClient.execute(new zzck(this, googleApiClient, str, z, i));
    }

    public final PendingResult<OpenSnapshotResult> resolveConflict(GoogleApiClient googleApiClient, String str, Snapshot snapshot) {
        SnapshotMetadata metadata = snapshot.getMetadata();
        SnapshotMetadataChange build = new Builder().fromMetadata(metadata).build();
        return resolveConflict(googleApiClient, str, metadata.getSnapshotId(), build, snapshot.getSnapshotContents());
    }

    public final PendingResult<OpenSnapshotResult> resolveConflict(GoogleApiClient googleApiClient, String str, String str2, SnapshotMetadataChange snapshotMetadataChange, SnapshotContents snapshotContents) {
        return googleApiClient.execute(new zzcn(this, googleApiClient, str, str2, snapshotMetadataChange, snapshotContents));
    }
}
