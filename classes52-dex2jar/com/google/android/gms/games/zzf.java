// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.internal.games.zzah;

final class zzf extends zzah<Void>
{
    private final /* synthetic */ String zzk;
    private final /* synthetic */ int zzl;
    
    zzf(final EventsClient eventsClient, final String zzk, final int zzl) {
        this.zzk = zzk;
        this.zzl = zzl;
    }
    
    @Override
    protected final void zza(final zze zze, final TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        zze.zza(this.zzk, this.zzl);
    }
}
