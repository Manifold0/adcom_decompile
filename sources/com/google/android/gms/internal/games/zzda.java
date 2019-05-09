package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.stats.PlayerStats;
import com.google.android.gms.games.stats.Stats.LoadPlayerStatsResult;

final class zzda implements LoadPlayerStatsResult {
    private final /* synthetic */ Status zzbc;

    zzda(zzcz zzcz, Status status) {
        this.zzbc = status;
    }

    public final PlayerStats getPlayerStats() {
        return null;
    }

    public final Status getStatus() {
        return this.zzbc;
    }

    public final void release() {
    }
}
