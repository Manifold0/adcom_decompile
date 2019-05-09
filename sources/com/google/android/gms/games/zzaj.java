package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.internal.games.zzah;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzaj extends zzah<Void> {
    private final /* synthetic */ String zzbq;
    private final /* synthetic */ long zzbt;

    zzaj(LeaderboardsClient leaderboardsClient, String str, long j) {
        this.zzbq = str;
        this.zzbt = j;
    }

    protected final void zza(zze zze, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        zze.zza(null, this.zzbq, this.zzbt, null);
    }
}
