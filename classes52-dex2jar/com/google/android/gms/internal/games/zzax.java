// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.games;

import com.google.android.gms.games.leaderboard.LeaderboardScore;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.leaderboard.Leaderboards;

final class zzax implements LoadPlayerScoreResult
{
    private final /* synthetic */ Status zzbc;
    
    zzax(final zzaw zzaw, final Status zzbc) {
        this.zzbc = zzbc;
    }
    
    @Override
    public final LeaderboardScore getScore() {
        return null;
    }
    
    public final Status getStatus() {
        return this.zzbc;
    }
}
