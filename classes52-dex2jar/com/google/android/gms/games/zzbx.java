// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.games.snapshot.Snapshot;
import com.google.android.gms.internal.games.zzah;

final class zzbx extends zzah<Void>
{
    private final /* synthetic */ Snapshot zzef;
    
    zzbx(final SnapshotsClient snapshotsClient, final Snapshot zzef) {
        this.zzef = zzef;
    }
    
    @Override
    protected final void zza(final zze zze, final TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        zze.zza(this.zzef);
        taskCompletionSource.setResult((Object)null);
    }
}
