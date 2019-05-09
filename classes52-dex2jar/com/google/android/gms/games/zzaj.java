// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.games.leaderboard.Leaderboards;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.internal.games.zzah;

final class zzaj extends zzah<Void>
{
    private final /* synthetic */ String zzbq;
    private final /* synthetic */ long zzbt;
    
    zzaj(final LeaderboardsClient leaderboardsClient, final String zzbq, final long zzbt) {
        this.zzbq = zzbq;
        this.zzbt = zzbt;
    }
    
    @Override
    protected final void zza(final zze zze, final TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        zze.zza(null, this.zzbq, this.zzbt, null);
    }
}
