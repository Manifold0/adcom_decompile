// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.common.internal.ApiExceptionUtil;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.internal.games.zzah;

final class zzbr extends zzah<Integer>
{
    private final /* synthetic */ byte[] zzdf;
    private final /* synthetic */ String zzdg;
    private final /* synthetic */ String zzdh;
    private final /* synthetic */ ListenerHolder zzdt;
    
    zzbr(final RealTimeMultiplayerClient realTimeMultiplayerClient, final ListenerHolder zzdt, final byte[] zzdf, final String zzdg, final String zzdh) {
        this.zzdt = zzdt;
        this.zzdf = zzdf;
        this.zzdg = zzdg;
        this.zzdh = zzdh;
    }
    
    @Override
    protected final void zza(final zze zze, final TaskCompletionSource<Integer> taskCompletionSource) throws RemoteException {
        final Integer value = zze.zza((ListenerHolder<RealTimeMultiplayer.ReliableMessageSentCallback>)this.zzdt, this.zzdf, this.zzdg, this.zzdh);
        if (value == -1) {
            taskCompletionSource.trySetException((Exception)ApiExceptionUtil.fromStatus(GamesClientStatusCodes.zza(26601)));
            return;
        }
        taskCompletionSource.setResult((Object)value);
    }
}
