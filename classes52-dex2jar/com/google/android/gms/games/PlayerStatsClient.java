// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import com.google.android.gms.games.internal.zzi;
import com.google.android.gms.tasks.Task;
import android.content.Context;
import android.support.annotation.NonNull;
import android.app.Activity;
import com.google.android.gms.games.stats.PlayerStats;
import com.google.android.gms.games.stats.Stats;
import com.google.android.gms.common.internal.PendingResultUtil$ResultConverter;
import com.google.android.gms.internal.games.zzu;

public class PlayerStatsClient extends zzu
{
    private static final PendingResultUtil$ResultConverter<Stats.LoadPlayerStatsResult, PlayerStats> zzcy;
    
    static {
        zzcy = (PendingResultUtil$ResultConverter)new zzas();
    }
    
    PlayerStatsClient(@NonNull final Activity activity, @NonNull final Games.GamesOptions gamesOptions) {
        super(activity, gamesOptions);
    }
    
    PlayerStatsClient(@NonNull final Context context, @NonNull final Games.GamesOptions gamesOptions) {
        super(context, gamesOptions);
    }
    
    public Task<AnnotatedData<PlayerStats>> loadPlayerStats(final boolean b) {
        return zzi.zza(Games.Stats.loadPlayerStats(this.asGoogleApiClient(), b), PlayerStatsClient.zzcy);
    }
}
