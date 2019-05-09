// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import com.google.android.gms.games.internal.zzi;
import android.support.annotation.IntRange;
import com.google.android.gms.common.api.internal.TaskApiCall;
import android.content.Intent;
import com.google.android.gms.tasks.Task;
import android.content.Context;
import android.support.annotation.NonNull;
import android.app.Activity;
import com.google.android.gms.games.internal.zzq;
import com.google.android.gms.common.internal.PendingResultUtil$ResultConverter;
import com.google.android.gms.internal.games.zzu;

public class PlayersClient extends zzu
{
    public static final String EXTRA_PLAYER_SEARCH_RESULTS = "player_search_results";
    private static final PendingResultUtil$ResultConverter<Players.LoadPlayersResult, PlayerBuffer> zzcz;
    private static final zzq<Players.LoadPlayersResult> zzda;
    private static final PendingResultUtil$ResultConverter<Players.LoadPlayersResult, Player> zzdb;
    
    static {
        zzcz = (PendingResultUtil$ResultConverter)new zzax();
        zzda = new zzay();
        zzdb = (PendingResultUtil$ResultConverter)new zzaz();
    }
    
    PlayersClient(@NonNull final Activity activity, @NonNull final Games.GamesOptions gamesOptions) {
        super(activity, gamesOptions);
    }
    
    PlayersClient(@NonNull final Context context, @NonNull final Games.GamesOptions gamesOptions) {
        super(context, gamesOptions);
    }
    
    public Task<Intent> getCompareProfileIntent(@NonNull final Player player) {
        return (Task<Intent>)this.doRead((TaskApiCall)new zzav(this, player));
    }
    
    public Task<Player> getCurrentPlayer() {
        return (Task<Player>)this.doRead((TaskApiCall)new zzau(this));
    }
    
    public Task<String> getCurrentPlayerId() {
        return (Task<String>)this.doRead((TaskApiCall)new zzat(this));
    }
    
    public Task<Intent> getPlayerSearchIntent() {
        return (Task<Intent>)this.doRead((TaskApiCall)new zzaw(this));
    }
    
    public Task<AnnotatedData<PlayerBuffer>> loadMoreRecentlyPlayedWithPlayers(@IntRange(from = 1L, to = 25L) final int n) {
        return zzi.zzb(Games.Players.loadMoreRecentlyPlayedWithPlayers(this.asGoogleApiClient(), n), PlayersClient.zzcz);
    }
    
    public Task<AnnotatedData<Player>> loadPlayer(@NonNull final String s) {
        return this.loadPlayer(s, false);
    }
    
    public Task<AnnotatedData<Player>> loadPlayer(@NonNull final String s, final boolean b) {
        return zzi.zza(Games.Players.loadPlayer(this.asGoogleApiClient(), s, b), PlayersClient.zzdb, PlayersClient.zzda);
    }
    
    public Task<AnnotatedData<PlayerBuffer>> loadRecentlyPlayedWithPlayers(@IntRange(from = 1L, to = 25L) final int n, final boolean b) {
        return zzi.zzb(Games.Players.loadRecentlyPlayedWithPlayers(this.asGoogleApiClient(), n, b), PlayersClient.zzcz);
    }
}
