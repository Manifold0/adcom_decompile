package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder.ListenerKey;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.games.internal.zzt;
import com.google.android.gms.games.multiplayer.realtime.zzh;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzbm extends zzt<zzh> {
    zzbm(RealTimeMultiplayerClient realTimeMultiplayerClient, ListenerKey listenerKey) {
        super(listenerKey);
    }

    protected final void zzc(zze zze, TaskCompletionSource<Boolean> taskCompletionSource) throws RemoteException, SecurityException {
        taskCompletionSource.setResult(Boolean.valueOf(true));
    }
}
