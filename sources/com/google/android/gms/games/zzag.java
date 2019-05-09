package com.google.android.gms.games;

import android.content.Intent;
import android.os.RemoteException;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.internal.games.zzah;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzag extends zzah<Intent> {
    private final /* synthetic */ String zzbq;

    zzag(LeaderboardsClient leaderboardsClient, String str) {
        this.zzbq = str;
    }

    protected final void zza(zze zze, TaskCompletionSource<Intent> taskCompletionSource) throws RemoteException {
        taskCompletionSource.setResult(zze.zza(this.zzbq, -1, -1));
    }
}
