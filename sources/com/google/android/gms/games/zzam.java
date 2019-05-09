package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil.ResultConverter;
import com.google.android.gms.games.leaderboard.Leaderboard;
import com.google.android.gms.games.leaderboard.LeaderboardBuffer;
import com.google.android.gms.games.leaderboard.Leaderboards.LeaderboardMetadataResult;

final class zzam implements ResultConverter<LeaderboardMetadataResult, Leaderboard> {
    zzam() {
    }

    private static Leaderboard zza(LeaderboardMetadataResult leaderboardMetadataResult) {
        Leaderboard leaderboard = null;
        if (leaderboardMetadataResult != null) {
            LeaderboardBuffer leaderboards = leaderboardMetadataResult.getLeaderboards();
            if (leaderboards != null) {
                try {
                    if (leaderboards.getCount() > 0) {
                        leaderboard = (Leaderboard) ((Leaderboard) leaderboards.get(0)).freeze();
                        if (leaderboards != null) {
                            leaderboards.release();
                        }
                    }
                } catch (Throwable th) {
                    if (leaderboards != null) {
                        leaderboards.release();
                    }
                }
            }
            if (leaderboards != null) {
                leaderboards.release();
            }
        }
        return leaderboard;
    }

    public final /* synthetic */ Object convert(Result result) {
        return zza((LeaderboardMetadataResult) result);
    }
}
