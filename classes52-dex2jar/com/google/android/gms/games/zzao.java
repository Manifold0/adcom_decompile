// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.internal.games.zzah;

final class zzao extends zzah<Void>
{
    private final /* synthetic */ int zzbx;
    
    zzao(final NotificationsClient notificationsClient, final int zzbx) {
        this.zzbx = zzbx;
    }
    
    @Override
    protected final void zza(final zze zze, final TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        zze.zzk(this.zzbx);
        taskCompletionSource.setResult((Object)null);
    }
}
