// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.snapshot.Snapshots;

final class zzcr implements DeleteSnapshotResult
{
    private final /* synthetic */ Status zzbc;
    
    zzcr(final zzcq zzcq, final Status zzbc) {
        this.zzbc = zzbc;
    }
    
    @Override
    public final String getSnapshotId() {
        return null;
    }
    
    public final Status getStatus() {
        return this.zzbc;
    }
}
