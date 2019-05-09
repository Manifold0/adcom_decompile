package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.leaderboard.LeaderboardScore;
import com.google.android.gms.games.leaderboard.Leaderboards.LoadPlayerScoreResult;

final class zzax implements LoadPlayerScoreResult {
    private final /* synthetic */ Status zzbc;

    zzax(zzaw zzaw, Status status) {
        this.zzbc = status;
    }

    public final LeaderboardScore getScore() {
        return null;
    }

    public final Status getStatus() {
        return this.zzbc;
    }
}
