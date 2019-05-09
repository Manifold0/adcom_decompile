package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.leaderboard.Leaderboards.SubmitScoreResult;
import com.google.android.gms.games.leaderboard.ScoreSubmissionData;

final class zzbb implements SubmitScoreResult {
    private final /* synthetic */ Status zzbc;

    zzbb(zzba zzba, Status status) {
        this.zzbc = status;
    }

    public final ScoreSubmissionData getScoreData() {
        return new ScoreSubmissionData(DataHolder.empty(14));
    }

    public final Status getStatus() {
        return this.zzbc;
    }

    public final void release() {
    }
}
