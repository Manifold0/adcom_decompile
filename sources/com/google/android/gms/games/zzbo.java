package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.internal.games.zzah;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzbo extends zzah<Void> {
    final /* synthetic */ zzbn zzdr;

    zzbo(zzbn zzbn) {
        this.zzdr = zzbn;
    }

    protected final void zza(zze zze, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        this.zzdr.zzdp.notifyListener(new zzbp(this));
        taskCompletionSource.setResult(null);
    }
}
