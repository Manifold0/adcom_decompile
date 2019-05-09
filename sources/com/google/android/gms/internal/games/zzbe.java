package com.google.android.gms.internal.games;

import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.Players;
import com.google.android.gms.games.Players.LoadPlayersResult;

public final class zzbe implements Players {
    public final Intent getCompareProfileIntent(GoogleApiClient googleApiClient, Player player) {
        return Games.zza(googleApiClient).zzb(new PlayerEntity(player));
    }

    public final Player getCurrentPlayer(GoogleApiClient googleApiClient) {
        return Games.zza(googleApiClient).zzs();
    }

    public final String getCurrentPlayerId(GoogleApiClient googleApiClient) {
        return Games.zza(googleApiClient).zzb(true);
    }

    public final Intent getPlayerSearchIntent(GoogleApiClient googleApiClient) {
        return Games.zza(googleApiClient).zzah();
    }

    public final PendingResult<LoadPlayersResult> loadConnectedPlayers(GoogleApiClient googleApiClient, boolean z) {
        return googleApiClient.enqueue(new zzbl(this, googleApiClient, z));
    }

    public final PendingResult<LoadPlayersResult> loadInvitablePlayers(GoogleApiClient googleApiClient, int i, boolean z) {
        return googleApiClient.enqueue(new zzbh(this, googleApiClient, i, z));
    }

    public final PendingResult<LoadPlayersResult> loadMoreInvitablePlayers(GoogleApiClient googleApiClient, int i) {
        return googleApiClient.enqueue(new zzbi(this, googleApiClient, i));
    }

    public final PendingResult<LoadPlayersResult> loadMoreRecentlyPlayedWithPlayers(GoogleApiClient googleApiClient, int i) {
        return googleApiClient.enqueue(new zzbk(this, googleApiClient, i));
    }

    public final PendingResult<LoadPlayersResult> loadPlayer(GoogleApiClient googleApiClient, String str) {
        return googleApiClient.enqueue(new zzbf(this, googleApiClient, str));
    }

    public final PendingResult<LoadPlayersResult> loadPlayer(GoogleApiClient googleApiClient, String str, boolean z) {
        return googleApiClient.enqueue(new zzbg(this, googleApiClient, str, z));
    }

    public final PendingResult<LoadPlayersResult> loadRecentlyPlayedWithPlayers(GoogleApiClient googleApiClient, int i, boolean z) {
        return googleApiClient.enqueue(new zzbj(this, googleApiClient, i, z));
    }
}
