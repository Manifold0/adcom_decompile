package com.google.android.gms.games;

import android.support.annotation.NonNull;
import com.google.android.gms.games.GamesMetadata.LoadGamesResult;
import com.google.android.gms.games.internal.zzq;

final class zzw implements zzq<LoadGamesResult> {
    zzw() {
    }

    public final /* synthetic */ void release(@NonNull Object obj) {
        LoadGamesResult loadGamesResult = (LoadGamesResult) obj;
        if (loadGamesResult.getGames() != null) {
            loadGamesResult.getGames().release();
        }
    }
}
