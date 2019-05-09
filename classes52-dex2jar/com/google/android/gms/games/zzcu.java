// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.internal.games.zzah;

final class zzcu extends zzah<Void>
{
    private final /* synthetic */ String zzew;
    
    zzcu(final TurnBasedMultiplayerClient turnBasedMultiplayerClient, final String zzew) {
        this.zzew = zzew;
    }
    
    @Override
    protected final void zza(final zze zze, final TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        zze.zzb(this.zzew);
        taskCompletionSource.setResult((Object)null);
    }
}
