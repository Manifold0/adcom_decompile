package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil.ResultConverter;
import com.google.android.gms.games.leaderboard.Leaderboards.SubmitScoreResult;
import com.google.android.gms.games.leaderboard.ScoreSubmissionData;

final class zzae implements ResultConverter<SubmitScoreResult, ScoreSubmissionData> {
    zzae() {
    }

    public final /* synthetic */ Object convert(Result result) {
        SubmitScoreResult submitScoreResult = (SubmitScoreResult) result;
        return submitScoreResult == null ? null : submitScoreResult.getScoreData();
    }
}
