package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;

public final class LeaderboardScoreBuffer extends AbstractDataBuffer<LeaderboardScore> {
    private final zza zzna;

    public LeaderboardScoreBuffer(DataHolder dataHolder) {
        super(dataHolder);
        this.zzna = new zza(dataHolder.getMetadata());
    }

    public final LeaderboardScore get(int i) {
        return new LeaderboardScoreRef(this.mDataHolder, i);
    }

    public final zza zzcb() {
        return this.zzna;
    }
}
