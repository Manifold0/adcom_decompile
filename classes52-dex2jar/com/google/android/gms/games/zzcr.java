// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.internal.games.zzah;

final class zzcr extends zzah<Void>
{
    private final /* synthetic */ String zzdj;
    
    zzcr(final TurnBasedMultiplayerClient turnBasedMultiplayerClient, final String zzdj) {
        this.zzdj = zzdj;
    }
    
    @Override
    protected final void zza(final zze zze, final TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        zze.zzd(this.zzdj, 1);
        taskCompletionSource.setResult((Object)null);
    }
}
