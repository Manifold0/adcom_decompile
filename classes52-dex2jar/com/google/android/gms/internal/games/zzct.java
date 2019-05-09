// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.games;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.snapshot.SnapshotMetadataBuffer;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.snapshot.Snapshots;

final class zzct implements LoadSnapshotsResult
{
    private final /* synthetic */ Status zzbc;
    
    zzct(final zzcs zzcs, final Status zzbc) {
        this.zzbc = zzbc;
    }
    
    @Override
    public final SnapshotMetadataBuffer getSnapshots() {
        return new SnapshotMetadataBuffer(DataHolder.empty(14));
    }
    
    public final Status getStatus() {
        return this.zzbc;
    }
    
    public final void release() {
    }
}
