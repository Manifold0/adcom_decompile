package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.internal.games.zzah;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzcu extends zzah<Void> {
    private final /* synthetic */ String zzew;

    zzcu(TurnBasedMultiplayerClient turnBasedMultiplayerClient, String str) {
        this.zzew = str;
    }

    protected final void zza(zze zze, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        zze.zzb(this.zzew);
        taskCompletionSource.setResult(null);
    }
}
