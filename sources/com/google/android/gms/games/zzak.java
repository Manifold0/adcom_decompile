package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.internal.games.zzah;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzak extends zzah<Void> {
    private final /* synthetic */ String zzbq;
    private final /* synthetic */ long zzbt;
    private final /* synthetic */ String zzbu;

    zzak(LeaderboardsClient leaderboardsClient, String str, long j, String str2) {
        this.zzbq = str;
        this.zzbt = j;
        this.zzbu = str2;
    }

    protected final void zza(zze zze, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        zze.zza(null, this.zzbq, this.zzbt, this.zzbu);
    }
}
