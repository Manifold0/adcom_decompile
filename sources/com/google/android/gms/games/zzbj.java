package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.games.internal.zzs;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import com.google.android.gms.games.multiplayer.realtime.zzh;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzbj extends zzs<zzh> {
    private final /* synthetic */ ListenerHolder zzbi;
    private final /* synthetic */ RoomConfig zzdo;

    zzbj(RealTimeMultiplayerClient realTimeMultiplayerClient, ListenerHolder listenerHolder, ListenerHolder listenerHolder2, RoomConfig roomConfig) {
        this.zzbi = listenerHolder2;
        this.zzdo = roomConfig;
        super(listenerHolder);
    }

    protected final void zzb(zze zze, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException, SecurityException {
        zze.zza(this.zzbi, this.zzbi, this.zzbi, this.zzdo);
        taskCompletionSource.setResult(null);
    }
}
