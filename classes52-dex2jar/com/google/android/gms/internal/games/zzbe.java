// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.Games;
import android.content.Intent;
import com.google.android.gms.games.Player;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Players;

public final class zzbe implements Players
{
    @Override
    public final Intent getCompareProfileIntent(final GoogleApiClient googleApiClient, final Player player) {
        return Games.zza(googleApiClient).zzb(new PlayerEntity(player));
    }
    
    @Override
    public final Player getCurrentPlayer(final GoogleApiClient googleApiClient) {
        return Games.zza(googleApiClient).zzs();
    }
    
    @Override
    public final String getCurrentPlayerId(final GoogleApiClient googleApiClient) {
        return Games.zza(googleApiClient).zzb(true);
    }
    
    @Override
    public final Intent getPlayerSearchIntent(final GoogleApiClient googleApiClient) {
        return Games.zza(googleApiClient).zzah();
    }
    
    @Override
    public final PendingResult<LoadPlayersResult> loadConnectedPlayers(final GoogleApiClient googleApiClient, final boolean b) {
        return (PendingResult<LoadPlayersResult>)googleApiClient.enqueue((BaseImplementation$ApiMethodImpl)new zzbl(this, googleApiClient, b));
    }
    
    @Override
    public final PendingResult<LoadPlayersResult> loadInvitablePlayers(final GoogleApiClient googleApiClient, final int n, final boolean b) {
        return (PendingResult<LoadPlayersResult>)googleApiClient.enqueue((BaseImplementation$ApiMethodImpl)new zzbh(this, googleApiClient, n, b));
    }
    
    @Override
    public final PendingResult<LoadPlayersResult> loadMoreInvitablePlayers(final GoogleApiClient googleApiClient, final int n) {
        return (PendingResult<LoadPlayersResult>)googleApiClient.enqueue((BaseImplementation$ApiMethodImpl)new zzbi(this, googleApiClient, n));
    }
    
    @Override
    public final PendingResult<LoadPlayersResult> loadMoreRecentlyPlayedWithPlayers(final GoogleApiClient googleApiClient, final int n) {
        return (PendingResult<LoadPlayersResult>)googleApiClient.enqueue((BaseImplementation$ApiMethodImpl)new zzbk(this, googleApiClient, n));
    }
    
    @Override
    public final PendingResult<LoadPlayersResult> loadPlayer(final GoogleApiClient googleApiClient, final String s) {
        return (PendingResult<LoadPlayersResult>)googleApiClient.enqueue((BaseImplementation$ApiMethodImpl)new zzbf(this, googleApiClient, s));
    }
    
    @Override
    public final PendingResult<LoadPlayersResult> loadPlayer(final GoogleApiClient googleApiClient, final String s, final boolean b) {
        return (PendingResult<LoadPlayersResult>)googleApiClient.enqueue((BaseImplementation$ApiMethodImpl)new zzbg(this, googleApiClient, s, b));
    }
    
    @Override
    public final PendingResult<LoadPlayersResult> loadRecentlyPlayedWithPlayers(final GoogleApiClient googleApiClient, final int n, final boolean b) {
        return (PendingResult<LoadPlayersResult>)googleApiClient.enqueue((BaseImplementation$ApiMethodImpl)new zzbj(this, googleApiClient, n, b));
    }
}
