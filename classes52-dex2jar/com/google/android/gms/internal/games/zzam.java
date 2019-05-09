// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.games;

import com.google.android.gms.games.internal.zze;
import android.os.RemoteException;
import com.google.android.gms.games.internal.zzh;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.games.leaderboard.LeaderboardScoreBuffer;
import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.Games;
import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.leaderboard.Leaderboards;

public final class zzam implements Leaderboards
{
    @Override
    public final Intent getAllLeaderboardsIntent(final GoogleApiClient googleApiClient) {
        return Games.zza(googleApiClient).zzw();
    }
    
    @Override
    public final Intent getLeaderboardIntent(final GoogleApiClient googleApiClient, final String s) {
        return this.getLeaderboardIntent(googleApiClient, s, -1);
    }
    
    @Override
    public final Intent getLeaderboardIntent(final GoogleApiClient googleApiClient, final String s, final int n) {
        return this.getLeaderboardIntent(googleApiClient, s, n, -1);
    }
    
    @Override
    public final Intent getLeaderboardIntent(final GoogleApiClient googleApiClient, final String s, final int n, final int n2) {
        return Games.zza(googleApiClient).zza(s, n, n2);
    }
    
    @Override
    public final PendingResult<LoadPlayerScoreResult> loadCurrentPlayerLeaderboardScore(final GoogleApiClient googleApiClient, final String s, final int n, final int n2) {
        return (PendingResult<LoadPlayerScoreResult>)googleApiClient.enqueue((BaseImplementation$ApiMethodImpl)new zzap(this, googleApiClient, s, n, n2));
    }
    
    @Override
    public final PendingResult<LeaderboardMetadataResult> loadLeaderboardMetadata(final GoogleApiClient googleApiClient, final String s, final boolean b) {
        return (PendingResult<LeaderboardMetadataResult>)googleApiClient.enqueue((BaseImplementation$ApiMethodImpl)new zzao(this, googleApiClient, s, b));
    }
    
    @Override
    public final PendingResult<LeaderboardMetadataResult> loadLeaderboardMetadata(final GoogleApiClient googleApiClient, final boolean b) {
        return (PendingResult<LeaderboardMetadataResult>)googleApiClient.enqueue((BaseImplementation$ApiMethodImpl)new zzan(this, googleApiClient, b));
    }
    
    @Override
    public final PendingResult<LoadScoresResult> loadMoreScores(final GoogleApiClient googleApiClient, final LeaderboardScoreBuffer leaderboardScoreBuffer, final int n, final int n2) {
        return (PendingResult<LoadScoresResult>)googleApiClient.enqueue((BaseImplementation$ApiMethodImpl)new zzas(this, googleApiClient, leaderboardScoreBuffer, n, n2));
    }
    
    @Override
    public final PendingResult<LoadScoresResult> loadPlayerCenteredScores(final GoogleApiClient googleApiClient, final String s, final int n, final int n2, final int n3) {
        return this.loadPlayerCenteredScores(googleApiClient, s, n, n2, n3, false);
    }
    
    @Override
    public final PendingResult<LoadScoresResult> loadPlayerCenteredScores(final GoogleApiClient googleApiClient, final String s, final int n, final int n2, final int n3, final boolean b) {
        return (PendingResult<LoadScoresResult>)googleApiClient.enqueue((BaseImplementation$ApiMethodImpl)new zzar(this, googleApiClient, s, n, n2, n3, b));
    }
    
    @Override
    public final PendingResult<LoadScoresResult> loadTopScores(final GoogleApiClient googleApiClient, final String s, final int n, final int n2, final int n3) {
        return this.loadTopScores(googleApiClient, s, n, n2, n3, false);
    }
    
    @Override
    public final PendingResult<LoadScoresResult> loadTopScores(final GoogleApiClient googleApiClient, final String s, final int n, final int n2, final int n3, final boolean b) {
        return (PendingResult<LoadScoresResult>)googleApiClient.enqueue((BaseImplementation$ApiMethodImpl)new zzaq(this, googleApiClient, s, n, n2, n3, b));
    }
    
    @Override
    public final void submitScore(final GoogleApiClient googleApiClient, final String s, final long n) {
        this.submitScore(googleApiClient, s, n, null);
    }
    
    @Override
    public final void submitScore(final GoogleApiClient googleApiClient, final String s, final long n, final String s2) {
        final zze zza = Games.zza(googleApiClient, false);
        if (zza == null) {
            return;
        }
        try {
            zza.zza(null, s, n, s2);
        }
        catch (RemoteException ex) {
            zzh.w("LeaderboardsImpl", "service died");
        }
    }
    
    @Override
    public final PendingResult<SubmitScoreResult> submitScoreImmediate(final GoogleApiClient googleApiClient, final String s, final long n) {
        return this.submitScoreImmediate(googleApiClient, s, n, null);
    }
    
    @Override
    public final PendingResult<SubmitScoreResult> submitScoreImmediate(final GoogleApiClient googleApiClient, final String s, final long n, final String s2) {
        return (PendingResult<SubmitScoreResult>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzat(this, googleApiClient, s, n, s2));
    }
}
