package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.internal.ApiExceptionUtil;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.internal.games.zzah;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzbr extends zzah<Integer> {
    private final /* synthetic */ byte[] zzdf;
    private final /* synthetic */ String zzdg;
    private final /* synthetic */ String zzdh;
    private final /* synthetic */ ListenerHolder zzdt;

    zzbr(RealTimeMultiplayerClient realTimeMultiplayerClient, ListenerHolder listenerHolder, byte[] bArr, String str, String str2) {
        this.zzdt = listenerHolder;
        this.zzdf = bArr;
        this.zzdg = str;
        this.zzdh = str2;
    }

    protected final void zza(zze zze, TaskCompletionSource<Integer> taskCompletionSource) throws RemoteException {
        Integer valueOf = Integer.valueOf(zze.zza(this.zzdt, this.zzdf, this.zzdg, this.zzdh));
        if (valueOf.intValue() == -1) {
            taskCompletionSource.trySetException(ApiExceptionUtil.fromStatus(GamesClientStatusCodes.zza(GamesClientStatusCodes.REAL_TIME_MESSAGE_SEND_FAILED)));
        } else {
            taskCompletionSource.setResult(valueOf);
        }
    }
}
