// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.games.internal.zze;
import android.os.Bundle;
import com.google.android.gms.internal.games.zzah;

final class zzs extends zzah<Bundle>
{
    zzs(final GamesClient gamesClient) {
    }
    
    @Override
    protected final void zza(final zze zze, final TaskCompletionSource<Bundle> taskCompletionSource) throws RemoteException {
        taskCompletionSource.setResult((Object)zze.zzo());
    }
}
