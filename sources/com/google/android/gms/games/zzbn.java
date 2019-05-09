package com.google.android.gms.games;

import android.support.annotation.NonNull;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

final class zzbn implements Continuation<Boolean, Task<Void>> {
    final /* synthetic */ String zzdg;
    final /* synthetic */ RoomConfig zzdo;
    final /* synthetic */ ListenerHolder zzdp;
    private final /* synthetic */ RealTimeMultiplayerClient zzdq;

    zzbn(RealTimeMultiplayerClient realTimeMultiplayerClient, ListenerHolder listenerHolder, String str, RoomConfig roomConfig) {
        this.zzdq = realTimeMultiplayerClient;
        this.zzdp = listenerHolder;
        this.zzdg = str;
        this.zzdo = roomConfig;
    }

    public final /* synthetic */ Object then(@NonNull Task task) throws Exception {
        return this.zzdq.doRead(new zzbo(this));
    }
}
