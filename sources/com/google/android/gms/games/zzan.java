package com.google.android.gms.games;

import android.support.annotation.NonNull;
import com.google.android.gms.games.internal.zzq;
import com.google.android.gms.games.leaderboard.Leaderboards.LeaderboardMetadataResult;

final class zzan implements zzq<LeaderboardMetadataResult> {
    zzan() {
    }

    public final /* synthetic */ void release(@NonNull Object obj) {
        LeaderboardMetadataResult leaderboardMetadataResult = (LeaderboardMetadataResult) obj;
        if (leaderboardMetadataResult.getLeaderboards() != null) {
            leaderboardMetadataResult.getLeaderboards().release();
        }
    }
}
