// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.games.internal.zze;
import android.content.Intent;
import com.google.android.gms.internal.games.zzah;

final class zzcd extends zzah<Intent>
{
    zzcd(final TurnBasedMultiplayerClient turnBasedMultiplayerClient) {
    }
    
    @Override
    protected final void zza(final zze zze, final TaskCompletionSource<Intent> taskCompletionSource) throws RemoteException {
        taskCompletionSource.setResult((Object)zze.zzy());
    }
}
