package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.leaderboard.Leaderboard;
import com.google.android.gms.games.leaderboard.LeaderboardScoreBuffer;
import com.google.android.gms.games.leaderboard.Leaderboards.LoadScoresResult;

final class zzaz implements LoadScoresResult {
    private final /* synthetic */ Status zzbc;

    zzaz(zzay zzay, Status status) {
        this.zzbc = status;
    }

    public final Leaderboard getLeaderboard() {
        return null;
    }

    public final LeaderboardScoreBuffer getScores() {
        return new LeaderboardScoreBuffer(DataHolder.empty(14));
    }

    public final Status getStatus() {
        return this.zzbc;
    }

    public final void release() {
    }
}
