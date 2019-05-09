package com.google.android.gms.games;

import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil.ResultConverter;
import com.google.android.gms.games.GamesMetadata.LoadGamesResult;

final class zzv implements ResultConverter<LoadGamesResult, Game> {
    zzv() {
    }

    private static Game zza(@Nullable LoadGamesResult loadGamesResult) {
        Game game = null;
        if (loadGamesResult != null) {
            GameBuffer games = loadGamesResult.getGames();
            if (games != null) {
                try {
                    if (games.getCount() > 0) {
                        game = (Game) ((Game) games.get(0)).freeze();
                    } else {
                        games.release();
                    }
                } finally {
                    games.release();
                }
            }
        }
        return game;
    }

    public final /* synthetic */ Object convert(@Nullable Result result) {
        return zza((LoadGamesResult) result);
    }
}
