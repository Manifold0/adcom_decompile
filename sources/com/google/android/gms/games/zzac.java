package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil.ResultConverter;
import com.google.android.gms.games.leaderboard.LeaderboardScore;
import com.google.android.gms.games.leaderboard.Leaderboards.LoadPlayerScoreResult;

final class zzac implements ResultConverter<LoadPlayerScoreResult, LeaderboardScore> {
    zzac() {
    }

    public final /* synthetic */ Object convert(Result result) {
        LoadPlayerScoreResult loadPlayerScoreResult = (LoadPlayerScoreResult) result;
        if (loadPlayerScoreResult != null) {
            LeaderboardScore score = loadPlayerScoreResult.getScore();
            if (score != null) {
                return (LeaderboardScore) score.freeze();
            }
        }
        return null;
    }
}
