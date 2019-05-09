// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.stats;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.util.VisibleForTesting;

@Deprecated
@VisibleForTesting
public interface Stats
{
    PendingResult<LoadPlayerStatsResult> loadPlayerStats(final GoogleApiClient p0, final boolean p1);
    
    @Deprecated
    public interface LoadPlayerStatsResult extends Releasable, Result
    {
        PlayerStats getPlayerStats();
    }
}
