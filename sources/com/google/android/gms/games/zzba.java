package com.google.android.gms.games;

import android.content.Intent;
import android.os.RemoteException;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.games.multiplayer.realtime.Room;
import com.google.android.gms.internal.games.zzah;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzba extends zzah<Intent> {
    private final /* synthetic */ Room zzdd;
    private final /* synthetic */ int zzde;

    zzba(RealTimeMultiplayerClient realTimeMultiplayerClient, Room room, int i) {
        this.zzdd = room;
        this.zzde = i;
    }

    protected final void zza(zze zze, TaskCompletionSource<Intent> taskCompletionSource) throws RemoteException {
        taskCompletionSource.setResult(zze.zza(this.zzdd, this.zzde));
    }
}
