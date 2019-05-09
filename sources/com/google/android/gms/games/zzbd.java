package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.common.internal.ApiExceptionUtil;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.internal.games.zzah;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzbd extends zzah<Void> {
    private final /* synthetic */ byte[] zzdf;
    private final /* synthetic */ String zzdg;

    zzbd(RealTimeMultiplayerClient realTimeMultiplayerClient, byte[] bArr, String str) {
        this.zzdf = bArr;
        this.zzdg = str;
    }

    protected final void zza(zze zze, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        if (zze.zza(this.zzdf, this.zzdg) == 0) {
            taskCompletionSource.setResult(null);
        } else {
            taskCompletionSource.trySetException(ApiExceptionUtil.fromStatus(GamesClientStatusCodes.zza(GamesClientStatusCodes.REAL_TIME_MESSAGE_SEND_FAILED)));
        }
    }
}
