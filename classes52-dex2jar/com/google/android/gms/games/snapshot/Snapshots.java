// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.snapshot;

import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import android.os.Bundle;
import android.content.Intent;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;

@Deprecated
public interface Snapshots
{
    public static final int DISPLAY_LIMIT_NONE = -1;
    public static final String EXTRA_SNAPSHOT_METADATA = "com.google.android.gms.games.SNAPSHOT_METADATA";
    public static final String EXTRA_SNAPSHOT_NEW = "com.google.android.gms.games.SNAPSHOT_NEW";
    public static final int RESOLUTION_POLICY_HIGHEST_PROGRESS = 4;
    public static final int RESOLUTION_POLICY_LAST_KNOWN_GOOD = 2;
    public static final int RESOLUTION_POLICY_LONGEST_PLAYTIME = 1;
    public static final int RESOLUTION_POLICY_MANUAL = -1;
    public static final int RESOLUTION_POLICY_MOST_RECENTLY_MODIFIED = 3;
    
    PendingResult<CommitSnapshotResult> commitAndClose(final GoogleApiClient p0, final Snapshot p1, final SnapshotMetadataChange p2);
    
    PendingResult<DeleteSnapshotResult> delete(final GoogleApiClient p0, final SnapshotMetadata p1);
    
    void discardAndClose(final GoogleApiClient p0, final Snapshot p1);
    
    int getMaxCoverImageSize(final GoogleApiClient p0);
    
    int getMaxDataSize(final GoogleApiClient p0);
    
    Intent getSelectSnapshotIntent(final GoogleApiClient p0, final String p1, final boolean p2, final boolean p3, final int p4);
    
    SnapshotMetadata getSnapshotFromBundle(final Bundle p0);
    
    PendingResult<LoadSnapshotsResult> load(final GoogleApiClient p0, final boolean p1);
    
    PendingResult<OpenSnapshotResult> open(final GoogleApiClient p0, final SnapshotMetadata p1);
    
    PendingResult<OpenSnapshotResult> open(final GoogleApiClient p0, final SnapshotMetadata p1, final int p2);
    
    PendingResult<OpenSnapshotResult> open(final GoogleApiClient p0, final String p1, final boolean p2);
    
    PendingResult<OpenSnapshotResult> open(final GoogleApiClient p0, final String p1, final boolean p2, final int p3);
    
    PendingResult<OpenSnapshotResult> resolveConflict(final GoogleApiClient p0, final String p1, final Snapshot p2);
    
    PendingResult<OpenSnapshotResult> resolveConflict(final GoogleApiClient p0, final String p1, final String p2, final SnapshotMetadataChange p3, final SnapshotContents p4);
    
    @Deprecated
    public interface CommitSnapshotResult extends Result
    {
        SnapshotMetadata getSnapshotMetadata();
    }
    
    @Deprecated
    public interface DeleteSnapshotResult extends Result
    {
        String getSnapshotId();
    }
    
    @Deprecated
    public interface LoadSnapshotsResult extends Releasable, Result
    {
        SnapshotMetadataBuffer getSnapshots();
    }
    
    @Deprecated
    public interface OpenSnapshotResult extends Result
    {
        String getConflictId();
        
        Snapshot getConflictingSnapshot();
        
        SnapshotContents getResolutionSnapshotContents();
        
        Snapshot getSnapshot();
    }
}
