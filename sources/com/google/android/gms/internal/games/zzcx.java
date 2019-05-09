package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.stats.Stats;
import com.google.android.gms.games.stats.Stats.LoadPlayerStatsResult;

public final class zzcx implements Stats {
    public final PendingResult<LoadPlayerStatsResult> loadPlayerStats(GoogleApiClient googleApiClient, boolean z) {
        return googleApiClient.enqueue(new zzcy(this, googleApiClient, z));
    }
}
