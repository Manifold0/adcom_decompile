// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.internal.games.zzah;

final class zzq extends zzah<String>
{
    zzq(final GamesClient gamesClient) {
    }
    
    @Override
    protected final void zza(final zze zze, final TaskCompletionSource<String> taskCompletionSource) throws RemoteException {
        taskCompletionSource.setResult((Object)zze.zzam());
    }
}
