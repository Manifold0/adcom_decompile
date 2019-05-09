// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.games;

import com.google.android.gms.games.snapshot.SnapshotContents;
import com.google.android.gms.games.snapshot.Snapshot;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.snapshot.Snapshots;

final class zzcv implements OpenSnapshotResult
{
    private final /* synthetic */ Status zzbc;
    
    zzcv(final zzcu zzcu, final Status zzbc) {
        this.zzbc = zzbc;
    }
    
    @Override
    public final String getConflictId() {
        return null;
    }
    
    @Override
    public final Snapshot getConflictingSnapshot() {
        return null;
    }
    
    @Override
    public final SnapshotContents getResolutionSnapshotContents() {
        return null;
    }
    
    @Override
    public final Snapshot getSnapshot() {
        return null;
    }
    
    public final Status getStatus() {
        return this.zzbc;
    }
}
