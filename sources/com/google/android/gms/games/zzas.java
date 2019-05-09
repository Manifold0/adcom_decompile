package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil.ResultConverter;
import com.google.android.gms.games.stats.PlayerStats;
import com.google.android.gms.games.stats.Stats.LoadPlayerStatsResult;

final class zzas implements ResultConverter<LoadPlayerStatsResult, PlayerStats> {
    zzas() {
    }

    public final /* synthetic */ Object convert(Result result) {
        LoadPlayerStatsResult loadPlayerStatsResult = (LoadPlayerStatsResult) result;
        if (loadPlayerStatsResult != null) {
            PlayerStats playerStats = loadPlayerStatsResult.getPlayerStats();
            if (playerStats != null) {
                return (PlayerStats) playerStats.freeze();
            }
        }
        return null;
    }
}
