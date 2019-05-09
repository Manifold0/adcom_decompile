package com.google.android.gms.games;

import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil.ResultConverter;
import com.google.android.gms.games.LeaderboardsClient.LeaderboardScores;
import com.google.android.gms.games.leaderboard.Leaderboard;
import com.google.android.gms.games.leaderboard.Leaderboards.LoadScoresResult;

final class zzaf implements ResultConverter<LoadScoresResult, LeaderboardScores> {
    zzaf() {
    }

    public final /* synthetic */ Object convert(@Nullable Result result) {
        Leaderboard leaderboard = null;
        LoadScoresResult loadScoresResult = (LoadScoresResult) result;
        if (loadScoresResult == null) {
            return null;
        }
        if (loadScoresResult.getLeaderboard() != null) {
            leaderboard = (Leaderboard) loadScoresResult.getLeaderboard().freeze();
        }
        return new LeaderboardScores(leaderboard, loadScoresResult.getScores());
    }
}
