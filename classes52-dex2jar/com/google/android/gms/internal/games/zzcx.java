// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.stats.Stats;

public final class zzcx implements Stats
{
    @Override
    public final PendingResult<LoadPlayerStatsResult> loadPlayerStats(final GoogleApiClient googleApiClient, final boolean b) {
        return (PendingResult<LoadPlayerStatsResult>)googleApiClient.enqueue((BaseImplementation$ApiMethodImpl)new zzcy(this, googleApiClient, b));
    }
}
