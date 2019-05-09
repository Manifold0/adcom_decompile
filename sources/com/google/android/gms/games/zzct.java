package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.internal.games.zzah;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzct extends zzah<Integer> {
    zzct(TurnBasedMultiplayerClient turnBasedMultiplayerClient) {
    }

    protected final void zza(zze zze, TaskCompletionSource<Integer> taskCompletionSource) throws RemoteException {
        taskCompletionSource.setResult(Integer.valueOf(zze.zzao()));
    }
}
