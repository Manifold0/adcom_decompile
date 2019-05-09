package com.google.android.gms.games;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.PendingResultUtil.ResultConverter;
import com.google.android.gms.games.Games.GamesOptions;
import com.google.android.gms.games.internal.zzi;
import com.google.android.gms.games.stats.PlayerStats;
import com.google.android.gms.games.stats.Stats.LoadPlayerStatsResult;
import com.google.android.gms.internal.games.zzu;
import com.google.android.gms.tasks.Task;

public class PlayerStatsClient extends zzu {
    private static final ResultConverter<LoadPlayerStatsResult, PlayerStats> zzcy = new zzas();

    PlayerStatsClient(@NonNull Activity activity, @NonNull GamesOptions gamesOptions) {
        super(activity, gamesOptions);
    }

    PlayerStatsClient(@NonNull Context context, @NonNull GamesOptions gamesOptions) {
        super(context, gamesOptions);
    }

    public Task<AnnotatedData<PlayerStats>> loadPlayerStats(boolean z) {
        return zzi.zza(Games.Stats.loadPlayerStats(asGoogleApiClient(), z), zzcy);
    }
}
