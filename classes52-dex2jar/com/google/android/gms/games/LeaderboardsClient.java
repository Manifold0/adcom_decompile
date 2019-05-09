// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Releasable;
import android.support.annotation.IntRange;
import com.google.android.gms.games.leaderboard.LeaderboardScoreBuffer;
import com.google.android.gms.games.internal.zzi;
import com.google.android.gms.common.api.internal.TaskApiCall;
import android.content.Intent;
import com.google.android.gms.tasks.Task;
import android.content.Context;
import android.support.annotation.NonNull;
import android.app.Activity;
import com.google.android.gms.games.leaderboard.ScoreSubmissionData;
import com.google.android.gms.games.internal.zzr;
import com.google.android.gms.games.leaderboard.LeaderboardScore;
import com.google.android.gms.games.internal.zzq;
import com.google.android.gms.games.leaderboard.Leaderboard;
import com.google.android.gms.games.leaderboard.LeaderboardBuffer;
import com.google.android.gms.games.leaderboard.Leaderboards;
import com.google.android.gms.common.internal.PendingResultUtil$ResultConverter;
import com.google.android.gms.internal.games.zzu;

public class LeaderboardsClient extends zzu
{
    private static final PendingResultUtil$ResultConverter<Leaderboards.LeaderboardMetadataResult, LeaderboardBuffer> zzbj;
    private static final PendingResultUtil$ResultConverter<Leaderboards.LeaderboardMetadataResult, Leaderboard> zzbk;
    private static final zzq<Leaderboards.LeaderboardMetadataResult> zzbl;
    private static final PendingResultUtil$ResultConverter<Leaderboards.LoadPlayerScoreResult, LeaderboardScore> zzbm;
    private static final zzr zzbn;
    private static final PendingResultUtil$ResultConverter<Leaderboards.SubmitScoreResult, ScoreSubmissionData> zzbo;
    private static final PendingResultUtil$ResultConverter<Leaderboards.LoadScoresResult, LeaderboardScores> zzbp;
    
    static {
        zzbj = (PendingResultUtil$ResultConverter)new zzal();
        zzbk = (PendingResultUtil$ResultConverter)new zzam();
        zzbl = new zzan();
        zzbm = (PendingResultUtil$ResultConverter)new zzac();
        zzbn = new zzad();
        zzbo = (PendingResultUtil$ResultConverter)new zzae();
        zzbp = (PendingResultUtil$ResultConverter)new zzaf();
    }
    
    LeaderboardsClient(@NonNull final Activity activity, @NonNull final Games.GamesOptions gamesOptions) {
        super(activity, gamesOptions);
    }
    
    LeaderboardsClient(@NonNull final Context context, @NonNull final Games.GamesOptions gamesOptions) {
        super(context, gamesOptions);
    }
    
    public Task<Intent> getAllLeaderboardsIntent() {
        return (Task<Intent>)this.doRead((TaskApiCall)new zzab(this));
    }
    
    public Task<Intent> getLeaderboardIntent(@NonNull final String s) {
        return (Task<Intent>)this.doRead((TaskApiCall)new zzag(this, s));
    }
    
    public Task<Intent> getLeaderboardIntent(@NonNull final String s, final int n) {
        return (Task<Intent>)this.doRead((TaskApiCall)new zzah(this, s, n));
    }
    
    public Task<Intent> getLeaderboardIntent(@NonNull final String s, final int n, final int n2) {
        return (Task<Intent>)this.doRead((TaskApiCall)new zzai(this, s, n, n2));
    }
    
    public Task<AnnotatedData<LeaderboardScore>> loadCurrentPlayerLeaderboardScore(@NonNull final String s, final int n, final int n2) {
        return zzi.zza(Games.Leaderboards.loadCurrentPlayerLeaderboardScore(this.asGoogleApiClient(), s, n, n2), LeaderboardsClient.zzbm);
    }
    
    public Task<AnnotatedData<Leaderboard>> loadLeaderboardMetadata(@NonNull final String s, final boolean b) {
        return zzi.zza(Games.Leaderboards.loadLeaderboardMetadata(this.asGoogleApiClient(), s, b), LeaderboardsClient.zzbk, LeaderboardsClient.zzbl);
    }
    
    public Task<AnnotatedData<LeaderboardBuffer>> loadLeaderboardMetadata(final boolean b) {
        return zzi.zzb(Games.Leaderboards.loadLeaderboardMetadata(this.asGoogleApiClient(), b), LeaderboardsClient.zzbj);
    }
    
    public Task<AnnotatedData<LeaderboardScores>> loadMoreScores(@NonNull final LeaderboardScoreBuffer leaderboardScoreBuffer, @IntRange(from = 1L, to = 25L) final int n, final int n2) {
        return zzi.zzb(Games.Leaderboards.loadMoreScores(this.asGoogleApiClient(), leaderboardScoreBuffer, n, n2), LeaderboardsClient.zzbp);
    }
    
    public Task<AnnotatedData<LeaderboardScores>> loadPlayerCenteredScores(@NonNull final String s, final int n, final int n2, @IntRange(from = 1L, to = 25L) final int n3) {
        return zzi.zzb(Games.Leaderboards.loadPlayerCenteredScores(this.asGoogleApiClient(), s, n, n2, n3), LeaderboardsClient.zzbp);
    }
    
    public Task<AnnotatedData<LeaderboardScores>> loadPlayerCenteredScores(@NonNull final String s, final int n, final int n2, @IntRange(from = 1L, to = 25L) final int n3, final boolean b) {
        return zzi.zzb(Games.Leaderboards.loadPlayerCenteredScores(this.asGoogleApiClient(), s, n, n2, n3, b), LeaderboardsClient.zzbp);
    }
    
    public Task<AnnotatedData<LeaderboardScores>> loadTopScores(@NonNull final String s, final int n, final int n2, @IntRange(from = 1L, to = 25L) final int n3) {
        return zzi.zzb(Games.Leaderboards.loadTopScores(this.asGoogleApiClient(), s, n, n2, n3), LeaderboardsClient.zzbp);
    }
    
    public Task<AnnotatedData<LeaderboardScores>> loadTopScores(@NonNull final String s, final int n, final int n2, @IntRange(from = 1L, to = 25L) final int n3, final boolean b) {
        return zzi.zzb(Games.Leaderboards.loadTopScores(this.asGoogleApiClient(), s, n, n2, n3, b), LeaderboardsClient.zzbp);
    }
    
    public void submitScore(@NonNull final String s, final long n) {
        this.doWrite((TaskApiCall)new zzaj(this, s, n));
    }
    
    public void submitScore(@NonNull final String s, final long n, @NonNull final String s2) {
        this.doWrite((TaskApiCall)new zzak(this, s, n, s2));
    }
    
    public Task<ScoreSubmissionData> submitScoreImmediate(@NonNull final String s, final long n) {
        return zzi.zza(Games.Leaderboards.submitScoreImmediate(this.asGoogleApiClient(), s, n), LeaderboardsClient.zzbn, LeaderboardsClient.zzbo);
    }
    
    public Task<ScoreSubmissionData> submitScoreImmediate(@NonNull final String s, final long n, @NonNull final String s2) {
        return zzi.zza(Games.Leaderboards.submitScoreImmediate(this.asGoogleApiClient(), s, n, s2), LeaderboardsClient.zzbn, LeaderboardsClient.zzbo);
    }
    
    public static class LeaderboardScores implements Releasable
    {
        private final Leaderboard zzbv;
        private final LeaderboardScoreBuffer zzbw;
        
        LeaderboardScores(@Nullable final Leaderboard zzbv, @NonNull final LeaderboardScoreBuffer zzbw) {
            this.zzbv = zzbv;
            this.zzbw = zzbw;
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
}
