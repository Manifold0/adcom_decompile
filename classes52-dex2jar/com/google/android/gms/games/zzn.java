// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.internal.games.zzah;

final class zzn extends zzah<Void>
{
    private final /* synthetic */ int zzbd;
    
    zzn(final GamesClient gamesClient, final int zzbd) {
        this.zzbd = zzbd;
    }
    
    @Override
    protected final void zza(final zze zze, final TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        zze.zzj(this.zzbd);
        taskCompletionSource.setResult((Object)null);
    }
}
