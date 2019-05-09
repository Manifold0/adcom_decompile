package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.common.internal.ApiExceptionUtil;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.games.internal.zzy;
import com.google.android.gms.internal.games.zzah;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzbb extends zzah<Void> {
    private final /* synthetic */ byte[] zzdf;
    private final /* synthetic */ String zzdg;
    private final /* synthetic */ String zzdh;

    zzbb(RealTimeMultiplayerClient realTimeMultiplayerClient, byte[] bArr, String str, String str2) {
        this.zzdf = bArr;
        this.zzdg = str;
        this.zzdh = str2;
    }

    protected final void zza(zze zze, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        if (((zzy) zze.getService()).zzb(this.zzdf, this.zzdg, new String[]{this.zzdh}) == 0) {
            taskCompletionSource.setResult(null);
        } else {
            taskCompletionSource.trySetException(ApiExceptionUtil.fromStatus(GamesClientStatusCodes.zza(GamesClientStatusCodes.REAL_TIME_MESSAGE_SEND_FAILED)));
        }
    }
}
