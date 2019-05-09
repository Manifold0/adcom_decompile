package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.internal.games.zzah;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzf extends zzah<Void> {
    private final /* synthetic */ String zzk;
    private final /* synthetic */ int zzl;

    zzf(EventsClient eventsClient, String str, int i) {
        this.zzk = str;
        this.zzl = i;
    }

    protected final void zza(zze zze, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        zze.zza(this.zzk, this.zzl);
    }
}
