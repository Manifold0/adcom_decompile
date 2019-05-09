package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.common.internal.ApiExceptionUtil;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.games.internal.zzy;
import com.google.android.gms.internal.games.zzah;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.List;

final class zzbc extends zzah<Void> {
    private final /* synthetic */ byte[] zzdf;
    private final /* synthetic */ String zzdg;
    private final /* synthetic */ List zzdi;

    zzbc(RealTimeMultiplayerClient realTimeMultiplayerClient, List list, byte[] bArr, String str) {
        this.zzdi = list;
        this.zzdf = bArr;
        this.zzdg = str;
    }

    protected final void zza(zze zze, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        Preconditions.checkNotNull(this.zzdi, "Participant IDs must not be null");
        if (((zzy) zze.getService()).zzb(this.zzdf, this.zzdg, (String[]) this.zzdi.toArray(new String[this.zzdi.size()])) == 0) {
            taskCompletionSource.setResult(null);
        } else {
            taskCompletionSource.trySetException(ApiExceptionUtil.fromStatus(GamesClientStatusCodes.zza(GamesClientStatusCodes.REAL_TIME_MESSAGE_SEND_FAILED)));
        }
    }
}
