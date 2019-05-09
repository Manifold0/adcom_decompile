// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.PendingResultUtil$ResultConverter;

final class zzaz implements PendingResultUtil$ResultConverter<Players.LoadPlayersResult, Player>
{
    private static Player zza(@Nullable final Players.LoadPlayersResult loadPlayersResult) {
        final Player player = null;
        if (loadPlayersResult == null) {
            return player;
        }
        final PlayerBuffer players = loadPlayersResult.getPlayers();
        Label_0057: {
            if (players == null) {
                break Label_0057;
            }
            try {
                if (players.getCount() > 0) {
                    return (Player)((Player)players.get(0)).freeze();
                }
                return null;
            }
            finally {
                if (players != null) {
                    players.release();
                }
            }
        }
    }
}
