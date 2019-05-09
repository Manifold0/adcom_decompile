package com.google.android.gms.internal.games;

import android.content.Intent;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.games.internal.zzh;
import com.google.android.gms.games.leaderboard.LeaderboardScoreBuffer;
import com.google.android.gms.games.leaderboard.Leaderboards;
import com.google.android.gms.games.leaderboard.Leaderboards.LeaderboardMetadataResult;
import com.google.android.gms.games.leaderboard.Leaderboards.LoadPlayerScoreResult;
import com.google.android.gms.games.leaderboard.Leaderboards.LoadScoresResult;
import com.google.android.gms.games.leaderboard.Leaderboards.SubmitScoreResult;

public final class zzam implements Leaderboards {
    public final Intent getAllLeaderboardsIntent(GoogleApiClient googleApiClient) {
        return Games.zza(googleApiClient).zzw();
    }

    public final Intent getLeaderboardIntent(GoogleApiClient googleApiClient, String str) {
        return getLeaderboardIntent(googleApiClient, str, -1);
    }

    public final Intent getLeaderboardIntent(GoogleApiClient googleApiClient, String str, int i) {
        return getLeaderboardIntent(googleApiClient, str, i, -1);
    }

    public final Intent getLeaderboardIntent(GoogleApiClient googleApiClient, String str, int i, int i2) {
        return Games.zza(googleApiClient).zza(str, i, i2);
    }

    public final PendingResult<LoadPlayerScoreResult> loadCurrentPlayerLeaderboardScore(GoogleApiClient googleApiClient, String str, int i, int i2) {
        return googleApiClient.enqueue(new zzap(this, googleApiClient, str, i, i2));
    }

    public final PendingResult<LeaderboardMetadataResult> loadLeaderboardMetadata(GoogleApiClient googleApiClient, String str, boolean z) {
        return googleApiClient.enqueue(new zzao(this, googleApiClient, str, z));
    }

    public final PendingResult<LeaderboardMetadataResult> loadLeaderboardMetadata(GoogleApiClient googleApiClient, boolean z) {
        return googleApiClient.enqueue(new zzan(this, googleApiClient, z));
    }

    public final PendingResult<LoadScoresResult> loadMoreScores(GoogleApiClient googleApiClient, LeaderboardScoreBuffer leaderboardScoreBuffer, int i, int i2) {
        return googleApiClient.enqueue(new zzas(this, googleApiClient, leaderboardScoreBuffer, i, i2));
    }

    public final PendingResult<LoadScoresResult> loadPlayerCenteredScores(GoogleApiClient googleApiClient, String str, int i, int i2, int i3) {
        return loadPlayerCenteredScores(googleApiClient, str, i, i2, i3, false);
    }

    public final PendingResult<LoadScoresResult> loadPlayerCenteredScores(GoogleApiClient googleApiClient, String str, int i, int i2, int i3, boolean z) {
        return googleApiClient.enqueue(new zzar(this, googleApiClient, str, i, i2, i3, z));
    }

    public final PendingResult<LoadScoresResult> loadTopScores(GoogleApiClient googleApiClient, String str, int i, int i2, int i3) {
        return loadTopScores(googleApiClient, str, i, i2, i3, false);
    }

    public final PendingResult<LoadScoresResult> loadTopScores(GoogleApiClient googleApiClient, String str, int i, int i2, int i3, boolean z) {
        return googleApiClient.enqueue(new zzaq(this, googleApiClient, str, i, i2, i3, z));
    }

    public final void submitScore(GoogleApiClient googleApiClient, String str, long j) {
        submitScore(googleApiClient, str, j, null);
    }

    public final void submitScore(GoogleApiClient googleApiClient, String str, long j, String str2) {
        zze zza = Games.zza(googleApiClient, false);
        if (zza != null) {
            try {
                zza.zza(null, str, j, str2);
            } catch (RemoteException e) {
                zzh.m1663w("LeaderboardsImpl", "service died");
            }
        }
    }

    public final PendingResult<SubmitScoreResult> submitScoreImmediate(GoogleApiClient googleApiClient, String str, long j) {
        return submitScoreImmediate(googleApiClient, str, j, null);
    }

    public final PendingResult<SubmitScoreResult> submitScoreImmediate(GoogleApiClient googleApiClient, String str, long j, String str2) {
        return googleApiClient.execute(new zzat(this, googleApiClient, str, j, str2));
    }
}
