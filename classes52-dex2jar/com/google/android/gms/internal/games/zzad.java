// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.Game;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.GamesMetadata;

public final class zzad implements GamesMetadata
{
    @Override
    public final Game getCurrentGame(final GoogleApiClient googleApiClient) {
        return Games.zza(googleApiClient).zzu();
    }
    
    @Override
    public final PendingResult<LoadGamesResult> loadGame(final GoogleApiClient googleApiClient) {
        return (PendingResult<LoadGamesResult>)googleApiClient.enqueue((BaseImplementation$ApiMethodImpl)new zzae(this, googleApiClient));
    }
}
