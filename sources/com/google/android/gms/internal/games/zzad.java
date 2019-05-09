package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.GamesMetadata;
import com.google.android.gms.games.GamesMetadata.LoadGamesResult;

public final class zzad implements GamesMetadata {
    public final Game getCurrentGame(GoogleApiClient googleApiClient) {
        return Games.zza(googleApiClient).zzu();
    }

    public final PendingResult<LoadGamesResult> loadGame(GoogleApiClient googleApiClient) {
        return googleApiClient.enqueue(new zzae(this, googleApiClient));
    }
}
