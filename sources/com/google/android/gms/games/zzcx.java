package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.internal.games.zzah;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzcx extends zzah<Boolean> {
    zzcx(VideosClient videosClient) {
    }

    protected final void zza(zze zze, TaskCompletionSource<Boolean> taskCompletionSource) throws RemoteException {
        taskCompletionSource.setResult(Boolean.valueOf(zze.zzaz()));
    }
}
