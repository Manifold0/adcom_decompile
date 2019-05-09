package com.google.android.gms.games;

import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil.ResultConverter;
import com.google.android.gms.games.Players.LoadPlayersResult;

final class zzaz implements ResultConverter<LoadPlayersResult, Player> {
    zzaz() {
    }

    private static Player zza(@Nullable LoadPlayersResult loadPlayersResult) {
        Player player = null;
        if (loadPlayersResult != null) {
            PlayerBuffer players = loadPlayersResult.getPlayers();
            if (players != null) {
                try {
                    if (players.getCount() > 0) {
                        player = (Player) ((Player) players.get(0)).freeze();
                        if (players != null) {
                            players.release();
                        }
                    }
                } catch (Throwable th) {
                    if (players != null) {
                        players.release();
                    }
                }
            }
            if (players != null) {
                players.release();
            }
        }
        return player;
    }

    public final /* synthetic */ Object convert(@Nullable Result result) {
        return zza((LoadPlayersResult) result);
    }
}
