// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.games;

import com.google.android.gms.games.stats.PlayerStats;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.stats.Stats;

final class zzda implements LoadPlayerStatsResult
{
    private final /* synthetic */ Status zzbc;
    
    zzda(final zzcz zzcz, final Status zzbc) {
        this.zzbc = zzbc;
    }
    
    @Override
    public final PlayerStats getPlayerStats() {
        return null;
    }
    
    public final Status getStatus() {
        return this.zzbc;
    }
    
    public final void release() {
    }
}
