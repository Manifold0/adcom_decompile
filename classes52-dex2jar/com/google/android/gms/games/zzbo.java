// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder$Notifier;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.internal.games.zzah;

final class zzbo extends zzah<Void>
{
    final /* synthetic */ zzbn zzdr;
    
    zzbo(final zzbn zzdr) {
        this.zzdr = zzdr;
    }
    
    @Override
    protected final void zza(final zze zze, final TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        this.zzdr.zzdp.notifyListener((ListenerHolder$Notifier)new zzbp(this));
        taskCompletionSource.setResult((Object)null);
    }
}
