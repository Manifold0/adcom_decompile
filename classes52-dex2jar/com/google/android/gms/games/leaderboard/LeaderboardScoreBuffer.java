// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.AbstractDataBuffer;

public final class LeaderboardScoreBuffer extends AbstractDataBuffer<LeaderboardScore>
{
    private final zza zzna;
    
    public LeaderboardScoreBuffer(final DataHolder dataHolder) {
        super(dataHolder);
        this.zzna = new zza(dataHolder.getMetadata());
    }
    
    public final LeaderboardScore get(final int n) {
        return new LeaderboardScoreRef(this.mDataHolder, n);
    }
    
    public final zza zzcb() {
        return this.zzna;
    }
}
