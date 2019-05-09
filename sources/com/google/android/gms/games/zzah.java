package com.google.android.gms.games;

import android.content.Intent;
import android.os.RemoteException;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzah extends com.google.android.gms.internal.games.zzah<Intent> {
    private final /* synthetic */ String zzbq;
    private final /* synthetic */ int zzbr;

    zzah(LeaderboardsClient leaderboardsClient, String str, int i) {
        this.zzbq = str;
        this.zzbr = i;
    }

    protected final void zza(zze zze, TaskCompletionSource<Intent> taskCompletionSource) throws RemoteException {
        taskCompletionSource.setResult(zze.zza(this.zzbq, this.zzbr, -1));
    }
}
