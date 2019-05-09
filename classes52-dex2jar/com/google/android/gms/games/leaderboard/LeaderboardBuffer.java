// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.EntityBuffer;

public final class LeaderboardBuffer extends EntityBuffer<Leaderboard>
{
    public LeaderboardBuffer(final DataHolder dataHolder) {
        super(dataHolder);
    }
    
    protected final String getPrimaryDataMarkerColumn() {
        return "external_leaderboard_id";
    }
}
