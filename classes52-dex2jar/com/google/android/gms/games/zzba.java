// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.games.multiplayer.realtime.Room;
import android.content.Intent;
import com.google.android.gms.internal.games.zzah;

final class zzba extends zzah<Intent>
{
    private final /* synthetic */ Room zzdd;
    private final /* synthetic */ int zzde;
    
    zzba(final RealTimeMultiplayerClient realTimeMultiplayerClient, final Room zzdd, final int zzde) {
        this.zzdd = zzdd;
        this.zzde = zzde;
    }
    
    @Override
    protected final void zza(final zze zze, final TaskCompletionSource<Intent> taskCompletionSource) throws RemoteException {
        taskCompletionSource.setResult((Object)zze.zza(this.zzdd, this.zzde));
    }
}
