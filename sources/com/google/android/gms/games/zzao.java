package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.internal.games.zzah;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzao extends zzah<Void> {
    private final /* synthetic */ int zzbx;

    zzao(NotificationsClient notificationsClient, int i) {
        this.zzbx = i;
    }

    protected final void zza(zze zze, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        zze.zzk(this.zzbx);
        taskCompletionSource.setResult(null);
    }
}
