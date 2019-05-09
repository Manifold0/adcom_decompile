package com.google.android.gms.games;

import android.content.Intent;
import android.os.RemoteException;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.internal.games.zzah;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzai extends zzah<Intent> {
    private final /* synthetic */ String zzbq;
    private final /* synthetic */ int zzbr;
    private final /* synthetic */ int zzbs;

    zzai(LeaderboardsClient leaderboardsClient, String str, int i, int i2) {
        this.zzbq = str;
        this.zzbr = i;
        this.zzbs = i2;
    }

    protected final void zza(zze zze, TaskCompletionSource<Intent> taskCompletionSource) throws RemoteException {
        taskCompletionSource.setResult(zze.zza(this.zzbq, this.zzbr, this.zzbs));
    }
}
