// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.games;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.leaderboard.LeaderboardScoreBuffer;
import com.google.android.gms.games.leaderboard.Leaderboard;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.leaderboard.Leaderboards;

final class zzaz implements LoadScoresResult
{
    private final /* synthetic */ Status zzbc;
    
    zzaz(final zzay zzay, final Status zzbc) {
        this.zzbc = zzbc;
    }
    
    @Override
    public final Leaderboard getLeaderboard() {
        return null;
    }
    
    @Override
    public final LeaderboardScoreBuffer getScores() {
        return new LeaderboardScoreBuffer(DataHolder.empty(14));
    }
    
    public final Status getStatus() {
        return this.zzbc;
    }
    
    public final void release() {
    }
}
