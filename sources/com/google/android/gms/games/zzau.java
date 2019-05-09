package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.internal.games.zzah;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzau extends zzah<Player> {
    zzau(PlayersClient playersClient) {
    }

    protected final void zza(zze zze, TaskCompletionSource<Player> taskCompletionSource) throws RemoteException {
        taskCompletionSource.setResult(zze.zzr());
    }
}
