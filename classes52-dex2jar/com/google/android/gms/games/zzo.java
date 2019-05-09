// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.games.internal.zze;
import android.view.View;
import com.google.android.gms.internal.games.zzah;

final class zzo extends zzah<Void>
{
    private final /* synthetic */ View zzbe;
    
    zzo(final GamesClient gamesClient, final View zzbe) {
        this.zzbe = zzbe;
    }
    
    @Override
    protected final void zza(final zze zze, final TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        zze.zza(this.zzbe);
        taskCompletionSource.setResult((Object)null);
    }
}
