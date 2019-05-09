package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.games.internal.zzy;
import com.google.android.gms.internal.games.zzah;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzbg extends zzah<String> {
    private final /* synthetic */ String zzdg;

    zzbg(RealTimeMultiplayerClient realTimeMultiplayerClient, String str) {
        this.zzdg = str;
    }

    protected final void zza(zze zze, TaskCompletionSource<String> taskCompletionSource) throws RemoteException {
        ((zzy) zze.getService()).zza(new zzbh(this, taskCompletionSource), this.zzdg);
    }
}
