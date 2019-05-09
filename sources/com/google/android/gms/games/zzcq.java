package com.google.android.gms.games;

import android.content.Intent;
import android.os.RemoteException;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.internal.games.zzah;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzcq extends zzah<Intent> {
    private final /* synthetic */ int zzdl;
    private final /* synthetic */ int zzdm;
    private final /* synthetic */ boolean zzdn;

    zzcq(TurnBasedMultiplayerClient turnBasedMultiplayerClient, int i, int i2, boolean z) {
        this.zzdl = i;
        this.zzdm = i2;
        this.zzdn = z;
    }

    protected final void zza(zze zze, TaskCompletionSource<Intent> taskCompletionSource) throws RemoteException {
        taskCompletionSource.setResult(zze.zza(this.zzdl, this.zzdm, this.zzdn));
    }
}
