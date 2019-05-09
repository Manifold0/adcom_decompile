// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.games;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.leaderboard.LeaderboardBuffer;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.leaderboard.Leaderboards;

final class zzav implements LeaderboardMetadataResult
{
    private final /* synthetic */ Status zzbc;
    
    zzav(final zzau zzau, final Status zzbc) {
        this.zzbc = zzbc;
    }
    
    @Override
    public final LeaderboardBuffer getLeaderboards() {
        return new LeaderboardBuffer(DataHolder.empty(14));
    }
    
    public final Status getStatus() {
        return this.zzbc;
    }
    
    public final void release() {
    }
}
