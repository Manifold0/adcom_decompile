// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.games;

import android.os.RemoteException;
import com.google.android.gms.games.leaderboard.Leaderboards;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.leaderboard.LeaderboardScoreBuffer;

final class zzas extends zzay
{
    private final /* synthetic */ int zzjo;
    private final /* synthetic */ LeaderboardScoreBuffer zzjp;
    private final /* synthetic */ int zzjq;
    
    zzas(final zzam zzam, final GoogleApiClient googleApiClient, final LeaderboardScoreBuffer zzjp, final int zzjo, final int zzjq) {
        this.zzjp = zzjp;
        this.zzjo = zzjo;
        this.zzjq = zzjq;
        super(googleApiClient, null);
    }
}
