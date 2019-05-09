package com.google.android.gms.games;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.internal.games.zzah;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzs extends zzah<Bundle> {
    zzs(GamesClient gamesClient) {
    }

    protected final void zza(zze zze, TaskCompletionSource<Bundle> taskCompletionSource) throws RemoteException {
        taskCompletionSource.setResult(zze.zzo());
    }
}
