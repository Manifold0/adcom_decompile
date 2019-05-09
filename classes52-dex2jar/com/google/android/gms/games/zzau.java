// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.internal.games.zzah;

final class zzau extends zzah<Player>
{
    zzau(final PlayersClient playersClient) {
    }
    
    @Override
    protected final void zza(final zze zze, final TaskCompletionSource<Player> taskCompletionSource) throws RemoteException {
        taskCompletionSource.setResult((Object)zze.zzr());
    }
}
