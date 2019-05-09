// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.games.internal.zze;
import android.content.Intent;
import com.google.android.gms.internal.games.zzah;

final class zzbi extends zzah<Intent>
{
    private final /* synthetic */ int zzdl;
    private final /* synthetic */ int zzdm;
    private final /* synthetic */ boolean zzdn;
    
    zzbi(final RealTimeMultiplayerClient realTimeMultiplayerClient, final int zzdl, final int zzdm, final boolean zzdn) {
        this.zzdl = zzdl;
        this.zzdm = zzdm;
        this.zzdn = zzdn;
    }
    
    @Override
    protected final void zza(final zze zze, final TaskCompletionSource<Intent> taskCompletionSource) throws RemoteException {
        taskCompletionSource.setResult((Object)zze.zzc(this.zzdl, this.zzdm, this.zzdn));
    }
}
