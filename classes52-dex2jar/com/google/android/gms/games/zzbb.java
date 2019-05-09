// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.common.internal.ApiExceptionUtil;
import com.google.android.gms.games.internal.zzy;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.internal.games.zzah;

final class zzbb extends zzah<Void>
{
    private final /* synthetic */ byte[] zzdf;
    private final /* synthetic */ String zzdg;
    private final /* synthetic */ String zzdh;
    
    zzbb(final RealTimeMultiplayerClient realTimeMultiplayerClient, final byte[] zzdf, final String zzdg, final String zzdh) {
        this.zzdf = zzdf;
        this.zzdg = zzdg;
        this.zzdh = zzdh;
    }
    
    @Override
    protected final void zza(final zze zze, final TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        if (((zzy)zze.getService()).zzb(this.zzdf, this.zzdg, new String[] { this.zzdh }) == 0) {
            taskCompletionSource.setResult((Object)null);
            return;
        }
        taskCompletionSource.trySetException((Exception)ApiExceptionUtil.fromStatus(GamesClientStatusCodes.zza(26601)));
    }
}
