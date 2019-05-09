// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.games;

import com.google.android.gms.games.snapshot.SnapshotMetadata;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.snapshot.Snapshots;

final class zzcp implements CommitSnapshotResult
{
    private final /* synthetic */ Status zzbc;
    
    zzcp(final zzco zzco, final Status zzbc) {
        this.zzbc = zzbc;
    }
    
    @Override
    public final SnapshotMetadata getSnapshotMetadata() {
        return null;
    }
    
    public final Status getStatus() {
        return this.zzbc;
    }
}
