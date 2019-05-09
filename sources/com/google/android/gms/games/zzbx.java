package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.games.snapshot.Snapshot;
import com.google.android.gms.internal.games.zzah;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzbx extends zzah<Void> {
    private final /* synthetic */ Snapshot zzef;

    zzbx(SnapshotsClient snapshotsClient, Snapshot snapshot) {
        this.zzef = snapshot;
    }

    protected final void zza(zze zze, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        zze.zza(this.zzef);
        taskCompletionSource.setResult(null);
    }
}
