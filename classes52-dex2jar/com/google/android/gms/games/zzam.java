// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.games.leaderboard.LeaderboardBuffer;
import com.google.android.gms.games.leaderboard.Leaderboard;
import com.google.android.gms.games.leaderboard.Leaderboards;
import com.google.android.gms.common.internal.PendingResultUtil$ResultConverter;

final class zzam implements PendingResultUtil$ResultConverter<Leaderboards.LeaderboardMetadataResult, Leaderboard>
{
    private static Leaderboard zza(final Leaderboards.LeaderboardMetadataResult leaderboardMetadataResult) {
        final Leaderboard leaderboard = null;
        if (leaderboardMetadataResult == null) {
            return leaderboard;
        }
        final LeaderboardBuffer leaderboards = leaderboardMetadataResult.getLeaderboards();
        Label_0057: {
            if (leaderboards == null) {
                break Label_0057;
            }
            try {
                if (leaderboards.getCount() > 0) {
                    return (Leaderboard)((Leaderboard)leaderboards.get(0)).freeze();
                }
                return null;
            }
            finally {
                if (leaderboards != null) {
                    leaderboards.release();
                }
            }
        }
    }
}
