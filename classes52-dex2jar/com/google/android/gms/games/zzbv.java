// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.internal.games.zzah;

final class zzbv extends zzah<Integer>
{
    zzbv(final SnapshotsClient snapshotsClient) {
    }
    
    @Override
    protected final void zza(final zze zze, final TaskCompletionSource<Integer> taskCompletionSource) throws RemoteException {
        taskCompletionSource.setResult((Object)zze.zzav());
    }
}
