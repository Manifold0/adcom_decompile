package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.internal.games.zzah;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzn extends zzah<Void> {
    private final /* synthetic */ int zzbd;

    zzn(GamesClient gamesClient, int i) {
        this.zzbd = i;
    }

    protected final void zza(zze zze, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        zze.zzj(this.zzbd);
        taskCompletionSource.setResult(null);
    }
}
