package com.google.android.gms.games;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.internal.PendingResultUtil.ResultConverter;
import com.google.android.gms.games.Games.GamesOptions;
import com.google.android.gms.games.internal.zzi;
import com.google.android.gms.games.internal.zzq;
import com.google.android.gms.games.internal.zzr;
import com.google.android.gms.games.leaderboard.Leaderboard;
import com.google.android.gms.games.leaderboard.LeaderboardBuffer;
import com.google.android.gms.games.leaderboard.LeaderboardScore;
import com.google.android.gms.games.leaderboard.LeaderboardScoreBuffer;
import com.google.android.gms.games.leaderboard.Leaderboards.LeaderboardMetadataResult;
import com.google.android.gms.games.leaderboard.Leaderboards.LoadPlayerScoreResult;
import com.google.android.gms.games.leaderboard.Leaderboards.LoadScoresResult;
import com.google.android.gms.games.leaderboard.Leaderboards.SubmitScoreResult;
import com.google.android.gms.games.leaderboard.ScoreSubmissionData;
import com.google.android.gms.internal.games.zzu;
import com.google.android.gms.tasks.Task;

public class LeaderboardsClient extends zzu {
    private static final ResultConverter<LeaderboardMetadataResult, LeaderboardBuffer> zzbj = new zzal();
    private static final ResultConverter<LeaderboardMetadataResult, Leaderboard> zzbk = new zzam();
    private static final zzq<LeaderboardMetadataResult> zzbl = new zzan();
    private static final ResultConverter<LoadPlayerScoreResult, LeaderboardScore> zzbm = new zzac();
    private static final zzr zzbn = new zzad();
    private static final ResultConverter<SubmitScoreResult, ScoreSubmissionData> zzbo = new zzae();
    private static final ResultConverter<LoadScoresResult, LeaderboardScores> zzbp = new zzaf();

    public static class LeaderboardScores implements Releasable {
        private final Leaderboard zzbv;
        private final LeaderboardScoreBuffer zzbw;

        LeaderboardScores(@Nullable Leaderboard leaderboard, @NonNull LeaderboardScoreBuffer leaderboardScoreBuffer) {
            this.zzbv = leaderboard;
            this.zzbw = leaderboardScoreBuffer;
        }

        @Nullable
        public Leaderboard getLeaderboard() {
            return this.zzbv;
        }

        @NonNull
        public LeaderboardScoreBuffer getScores() {
            return this.zzbw;
        }

        public void release() {
            if (this.zzbw != null) {
                this.zzbw.release();
            }
        }
    }

    LeaderboardsClient(@NonNull Activity activity, @NonNull GamesOptions gamesOptions) {
        super(activity, gamesOptions);
    }

    LeaderboardsClient(@NonNull Context context, @NonNull GamesOptions gamesOptions) {
        super(context, gamesOptions);
    }

    public Task<Intent> getAllLeaderboardsIntent() {
        return doRead(new zzab(this));
    }

    public Task<Intent> getLeaderboardIntent(@NonNull String str) {
        return doRead(new zzag(this, str));
    }

    public Task<Intent> getLeaderboardIntent(@NonNull String str, int i) {
        return doRead(new zzah(this, str, i));
    }

    public Task<Intent> getLeaderboardIntent(@NonNull String str, int i, int i2) {
        return doRead(new zzai(this, str, i, i2));
    }

    public Task<AnnotatedData<LeaderboardScore>> loadCurrentPlayerLeaderboardScore(@NonNull String str, int i, int i2) {
        return zzi.zza(Games.Leaderboards.loadCurrentPlayerLeaderboardScore(asGoogleApiClient(), str, i, i2), zzbm);
    }

    public Task<AnnotatedData<Leaderboard>> loadLeaderboardMetadata(@NonNull String str, boolean z) {
        return zzi.zza(Games.Leaderboards.loadLeaderboardMetadata(asGoogleApiClient(), str, z), zzbk, zzbl);
    }

    public Task<AnnotatedData<LeaderboardBuffer>> loadLeaderboardMetadata(boolean z) {
        return zzi.zzb(Games.Leaderboards.loadLeaderboardMetadata(asGoogleApiClient(), z), zzbj);
    }

    public Task<AnnotatedData<LeaderboardScores>> loadMoreScores(@NonNull LeaderboardScoreBuffer leaderboardScoreBuffer, @IntRange(from = 1, to = 25) int i, int i2) {
        return zzi.zzb(Games.Leaderboards.loadMoreScores(asGoogleApiClient(), leaderboardScoreBuffer, i, i2), zzbp);
    }

    public Task<AnnotatedData<LeaderboardScores>> loadPlayerCenteredScores(@NonNull String str, int i, int i2, @IntRange(from = 1, to = 25) int i3) {
        return zzi.zzb(Games.Leaderboards.loadPlayerCenteredScores(asGoogleApiClient(), str, i, i2, i3), zzbp);
    }

    public Task<AnnotatedData<LeaderboardScores>> loadPlayerCenteredScores(@NonNull String str, int i, int i2, @IntRange(from = 1, to = 25) int i3, boolean z) {
        return zzi.zzb(Games.Leaderboards.loadPlayerCenteredScores(asGoogleApiClient(), str, i, i2, i3, z), zzbp);
    }

    public Task<AnnotatedData<LeaderboardScores>> loadTopScores(@NonNull String str, int i, int i2, @IntRange(from = 1, to = 25) int i3) {
        return zzi.zzb(Games.Leaderboards.loadTopScores(asGoogleApiClient(), str, i, i2, i3), zzbp);
    }

    public Task<AnnotatedData<LeaderboardScores>> loadTopScores(@NonNull String str, int i, int i2, @IntRange(from = 1, to = 25) int i3, boolean z) {
        return zzi.zzb(Games.Leaderboards.loadTopScores(asGoogleApiClient(), str, i, i2, i3, z), zzbp);
    }

    public void submitScore(@NonNull String str, long j) {
        doWrite(new zzaj(this, str, j));
    }

    public void submitScore(@NonNull String str, long j, @NonNull String str2) {
        doWrite(new zzak(this, str, j, str2));
    }

    public Task<ScoreSubmissionData> submitScoreImmediate(@NonNull String str, long j) {
        return zzi.zza(Games.Leaderboards.submitScoreImmediate(asGoogleApiClient(), str, j), zzbn, zzbo);
    }

    public Task<ScoreSubmissionData> submitScoreImmediate(@NonNull String str, long j, @NonNull String str2) {
        return zzi.zza(Games.Leaderboards.submitScoreImmediate(asGoogleApiClient(), str, j, str2), zzbn, zzbo);
    }
}
