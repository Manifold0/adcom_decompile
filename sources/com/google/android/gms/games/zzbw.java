package com.google.android.gms.games;

import android.content.Intent;
import android.os.RemoteException;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.internal.games.zzah;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzbw extends zzah<Intent> {
    private final /* synthetic */ String zzeb;
    private final /* synthetic */ boolean zzec;
    private final /* synthetic */ boolean zzed;
    private final /* synthetic */ int zzee;

    zzbw(SnapshotsClient snapshotsClient, String str, boolean z, boolean z2, int i) {
        this.zzeb = str;
        this.zzec = z;
        this.zzed = z2;
        this.zzee = i;
    }

    protected final void zza(zze zze, TaskCompletionSource<Intent> taskCompletionSource) throws RemoteException {
        taskCompletionSource.setResult(zze.zza(this.zzeb, this.zzec, this.zzed, this.zzee));
    }
}
