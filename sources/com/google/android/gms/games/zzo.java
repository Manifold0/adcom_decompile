package com.google.android.gms.games;

import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.internal.games.zzah;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzo extends zzah<Void> {
    private final /* synthetic */ View zzbe;

    zzo(GamesClient gamesClient, View view) {
        this.zzbe = view;
    }

    protected final void zza(zze zze, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        zze.zza(this.zzbe);
        taskCompletionSource.setResult(null);
    }
}
