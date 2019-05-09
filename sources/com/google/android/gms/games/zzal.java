package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil.ResultConverter;
import com.google.android.gms.games.leaderboard.LeaderboardBuffer;
import com.google.android.gms.games.leaderboard.Leaderboards.LeaderboardMetadataResult;

final class zzal implements ResultConverter<LeaderboardMetadataResult, LeaderboardBuffer> {
    zzal() {
    }

    public final /* synthetic */ Object convert(Result result) {
        LeaderboardMetadataResult leaderboardMetadataResult = (LeaderboardMetadataResult) result;
        return leaderboardMetadataResult == null ? null : leaderboardMetadataResult.getLeaderboards();
    }
}
