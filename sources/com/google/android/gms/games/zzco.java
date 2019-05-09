package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.games.internal.zzs;
import com.google.android.gms.games.multiplayer.turnbased.OnTurnBasedMatchUpdateReceivedListener;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzco extends zzs<OnTurnBasedMatchUpdateReceivedListener> {
    private final /* synthetic */ ListenerHolder zzbi;

    zzco(TurnBasedMultiplayerClient turnBasedMultiplayerClient, ListenerHolder listenerHolder, ListenerHolder listenerHolder2) {
        this.zzbi = listenerHolder2;
        super(listenerHolder);
    }

    protected final void zzb(zze zze, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException, SecurityException {
        zze.zzc(this.zzbi);
        taskCompletionSource.setResult(null);
    }
}
