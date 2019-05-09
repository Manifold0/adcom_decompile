// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.games.internal.zze;
import android.content.Intent;
import com.google.android.gms.internal.games.zzah;

final class zzav extends zzah<Intent>
{
    private final /* synthetic */ Player zzdc;
    
    zzav(final PlayersClient playersClient, final Player zzdc) {
        this.zzdc = zzdc;
    }
    
    @Override
    protected final void zza(final zze zze, final TaskCompletionSource<Intent> taskCompletionSource) throws RemoteException {
        taskCompletionSource.setResult((Object)zze.zza(new PlayerEntity(this.zzdc)));
    }
}
