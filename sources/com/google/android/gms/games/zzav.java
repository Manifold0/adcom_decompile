package com.google.android.gms.games;

import android.content.Intent;
import android.os.RemoteException;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.internal.games.zzah;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzav extends zzah<Intent> {
    private final /* synthetic */ Player zzdc;

    zzav(PlayersClient playersClient, Player player) {
        this.zzdc = player;
    }

    protected final void zza(zze zze, TaskCompletionSource<Intent> taskCompletionSource) throws RemoteException {
        taskCompletionSource.setResult(zze.zza(new PlayerEntity(this.zzdc)));
    }
}
