package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.internal.games.zzah;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzbf extends zzah<Void> {
    private final /* synthetic */ String zzdj;

    zzbf(RealTimeMultiplayerClient realTimeMultiplayerClient, String str) {
        this.zzdj = str;
    }

    protected final void zza(zze zze, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        zze.zzb(this.zzdj, 0);
        taskCompletionSource.setResult(null);
    }
}