package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.internal.games.zzah;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzu extends zzah<Game> {
    zzu(GamesMetadataClient gamesMetadataClient) {
    }

    protected final void zza(zze zze, TaskCompletionSource<Game> taskCompletionSource) throws RemoteException {
        taskCompletionSource.setResult(zze.zzt());
    }
}
