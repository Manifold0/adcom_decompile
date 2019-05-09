package com.google.android.gms.games;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.PendingResultUtil.ResultConverter;
import com.google.android.gms.games.Games.GamesOptions;
import com.google.android.gms.games.Players.LoadPlayersResult;
import com.google.android.gms.games.internal.zzi;
import com.google.android.gms.games.internal.zzq;
import com.google.android.gms.internal.games.zzu;
import com.google.android.gms.tasks.Task;

public class PlayersClient extends zzu {
    public static final String EXTRA_PLAYER_SEARCH_RESULTS = "player_search_results";
    private static final ResultConverter<LoadPlayersResult, PlayerBuffer> zzcz = new zzax();
    private static final zzq<LoadPlayersResult> zzda = new zzay();
    private static final ResultConverter<LoadPlayersResult, Player> zzdb = new zzaz();

    PlayersClient(@NonNull Activity activity, @NonNull GamesOptions gamesOptions) {
        super(activity, gamesOptions);
    }

    PlayersClient(@NonNull Context context, @NonNull GamesOptions gamesOptions) {
        super(context, gamesOptions);
    }

    public Task<Intent> getCompareProfileIntent(@NonNull Player player) {
        return doRead(new zzav(this, player));
    }

    public Task<Player> getCurrentPlayer() {
        return doRead(new zzau(this));
    }

    public Task<String> getCurrentPlayerId() {
        return doRead(new zzat(this));
    }

    public Task<Intent> getPlayerSearchIntent() {
        return doRead(new zzaw(this));
    }

    public Task<AnnotatedData<PlayerBuffer>> loadMoreRecentlyPlayedWithPlayers(@IntRange(from = 1, to = 25) int i) {
        return zzi.zzb(Games.Players.loadMoreRecentlyPlayedWithPlayers(asGoogleApiClient(), i), zzcz);
    }

    public Task<AnnotatedData<Player>> loadPlayer(@NonNull String str) {
        return loadPlayer(str, false);
    }

    public Task<AnnotatedData<Player>> loadPlayer(@NonNull String str, boolean z) {
        return zzi.zza(Games.Players.loadPlayer(asGoogleApiClient(), str, z), zzdb, zzda);
    }

    public Task<AnnotatedData<PlayerBuffer>> loadRecentlyPlayedWithPlayers(@IntRange(from = 1, to = 25) int i, boolean z) {
        return zzi.zzb(Games.Players.loadRecentlyPlayedWithPlayers(asGoogleApiClient(), i, z), zzcz);
    }
}
