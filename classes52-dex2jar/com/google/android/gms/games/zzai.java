// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.games.internal.zze;
import android.content.Intent;
import com.google.android.gms.internal.games.zzah;

final class zzai extends zzah<Intent>
{
    private final /* synthetic */ String zzbq;
    private final /* synthetic */ int zzbr;
    private final /* synthetic */ int zzbs;
    
    zzai(final LeaderboardsClient leaderboardsClient, final String zzbq, final int zzbr, final int zzbs) {
        this.zzbq = zzbq;
        this.zzbr = zzbr;
        this.zzbs = zzbs;
    }
    
    @Override
    protected final void zza(final zze zze, final TaskCompletionSource<Intent> taskCompletionSource) throws RemoteException {
        taskCompletionSource.setResult((Object)zze.zza(this.zzbq, this.zzbr, this.zzbs));
    }
}
