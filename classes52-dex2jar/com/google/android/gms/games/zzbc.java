// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.common.internal.ApiExceptionUtil;
import com.google.android.gms.games.internal.zzy;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.games.internal.zze;
import java.util.List;
import com.google.android.gms.internal.games.zzah;

final class zzbc extends zzah<Void>
{
    private final /* synthetic */ byte[] zzdf;
    private final /* synthetic */ String zzdg;
    private final /* synthetic */ List zzdi;
    
    zzbc(final RealTimeMultiplayerClient realTimeMultiplayerClient, final List zzdi, final byte[] zzdf, final String zzdg) {
        this.zzdi = zzdi;
        this.zzdf = zzdf;
        this.zzdg = zzdg;
    }
    
    @Override
    protected final void zza(final zze zze, final TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        Preconditions.checkNotNull((Object)this.zzdi, (Object)"Participant IDs must not be null");
        if (((zzy)zze.getService()).zzb(this.zzdf, this.zzdg, this.zzdi.toArray(new String[this.zzdi.size()])) == 0) {
            taskCompletionSource.setResult((Object)null);
            return;
        }
        taskCompletionSource.trySetException((Exception)ApiExceptionUtil.fromStatus(GamesClientStatusCodes.zza(26601)));
    }
}
