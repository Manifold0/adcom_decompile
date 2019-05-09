package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder.ListenerKey;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.games.internal.zzt;
import com.google.android.gms.games.multiplayer.turnbased.OnTurnBasedMatchUpdateReceivedListener;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzcp extends zzt<OnTurnBasedMatchUpdateReceivedListener> {
    zzcp(TurnBasedMultiplayerClient turnBasedMultiplayerClient, ListenerKey listenerKey) {
        super(listenerKey);
    }

    protected final void zzc(zze zze, TaskCompletionSource<Boolean> taskCompletionSource) throws RemoteException, SecurityException {
        zze.zzac();
        taskCompletionSource.setResult(Boolean.valueOf(true));
    }
}
