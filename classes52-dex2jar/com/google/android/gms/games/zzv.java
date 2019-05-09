// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.PendingResultUtil$ResultConverter;

final class zzv implements PendingResultUtil$ResultConverter<GamesMetadata.LoadGamesResult, Game>
{
    private static Game zza(@Nullable GamesMetadata.LoadGamesResult games) {
        if (games != null) {
            games = (GamesMetadata.LoadGamesResult)games.getGames();
            if (games != null) {
                try {
                    if (((GameBuffer)games).getCount() > 0) {
                        return (Game)((Game)((GameBuffer)games).get(0)).freeze();
                    }
                    return null;
                }
                finally {
                    ((GameBuffer)games).release();
                }
            }
        }
        return null;
    }
}
