// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.games;

import com.google.android.gms.games.snapshot.SnapshotContents;
import android.os.Bundle;
import android.content.Intent;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.snapshot.SnapshotMetadata;
import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange;
import com.google.android.gms.games.snapshot.Snapshot;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.snapshot.Snapshots;

public final class zzci implements Snapshots
{
    @Override
    public final PendingResult<CommitSnapshotResult> commitAndClose(final GoogleApiClient googleApiClient, final Snapshot snapshot, final SnapshotMetadataChange snapshotMetadataChange) {
        return (PendingResult<CommitSnapshotResult>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzcl(this, googleApiClient, snapshot, snapshotMetadataChange));
    }
    
    @Override
    public final PendingResult<DeleteSnapshotResult> delete(final GoogleApiClient googleApiClient, final SnapshotMetadata snapshotMetadata) {
        return (PendingResult<DeleteSnapshotResult>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzcm(this, googleApiClient, snapshotMetadata));
    }
    
    @Override
    public final void discardAndClose(final GoogleApiClient googleApiClient, final Snapshot snapshot) {
        Games.zza(googleApiClient).zzb(snapshot);
    }
    
    @Override
    public final int getMaxCoverImageSize(final GoogleApiClient googleApiClient) {
        return Games.zza(googleApiClient).zzaw();
    }
    
    @Override
    public final int getMaxDataSize(final GoogleApiClient googleApiClient) {
        return Games.zza(googleApiClient).zzau();
    }
    
    @Override
    public final Intent getSelectSnapshotIntent(final GoogleApiClient googleApiClient, final String s, final boolean b, final boolean b2, final int n) {
        return Games.zza(googleApiClient).zzb(s, b, b2, n);
    }
    
    @Override
    public final SnapshotMetadata getSnapshotFromBundle(final Bundle bundle) {
        if (bundle == null || !bundle.containsKey("com.google.android.gms.games.SNAPSHOT_METADATA")) {
            return null;
        }
        return (SnapshotMetadata)bundle.getParcelable("com.google.android.gms.games.SNAPSHOT_METADATA");
    }
    
    @Override
    public final PendingResult<LoadSnapshotsResult> load(final GoogleApiClient googleApiClient, final boolean b) {
        return (PendingResult<LoadSnapshotsResult>)googleApiClient.enqueue((BaseImplementation$ApiMethodImpl)new zzcj(this, googleApiClient, b));
    }
    
    @Override
    public final PendingResult<OpenSnapshotResult> open(final GoogleApiClient googleApiClient, final SnapshotMetadata snapshotMetadata) {
        return this.open(googleApiClient, snapshotMetadata.getUniqueName(), false);
    }
    
    @Override
    public final PendingResult<OpenSnapshotResult> open(final GoogleApiClient googleApiClient, final SnapshotMetadata snapshotMetadata, final int n) {
        return this.open(googleApiClient, snapshotMetadata.getUniqueName(), false, n);
    }
    
    @Override
    public final PendingResult<OpenSnapshotResult> open(final GoogleApiClient googleApiClient, final String s, final boolean b) {
        return this.open(googleApiClient, s, b, -1);
    }
    
    @Override
    public final PendingResult<OpenSnapshotResult> open(final GoogleApiClient googleApiClient, final String s, final boolean b, final int n) {
        return (PendingResult<OpenSnapshotResult>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzck(this, googleApiClient, s, b, n));
    }
    
    @Override
    public final PendingResult<OpenSnapshotResult> resolveConflict(final GoogleApiClient googleApiClient, final String s, final Snapshot snapshot) {
        final SnapshotMetadata metadata = snapshot.getMetadata();
        return this.resolveConflict(googleApiClient, s, metadata.getSnapshotId(), new SnapshotMetadataChange.Builder().fromMetadata(metadata).build(), snapshot.getSnapshotContents());
    }
    
    @Override
    public final PendingResult<OpenSnapshotResult> resolveConflict(final GoogleApiClient googleApiClient, final String s, final String s2, final SnapshotMetadataChange snapshotMetadataChange, final SnapshotContents snapshotContents) {
        return (PendingResult<OpenSnapshotResult>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzcn(this, googleApiClient, s, s2, snapshotMetadataChange, snapshotContents));
    }
}
