package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.EntityBuffer;

public final class LeaderboardBuffer extends EntityBuffer<Leaderboard> {
    public LeaderboardBuffer(DataHolder dataHolder) {
        super(dataHolder);
    }

    protected final /* synthetic */ Object getEntry(int i, int i2) {
        return new LeaderboardRef(this.mDataHolder, i, i2);
    }

    protected final String getPrimaryDataMarkerColumn() {
        return "external_leaderboard_id";
    }
}
