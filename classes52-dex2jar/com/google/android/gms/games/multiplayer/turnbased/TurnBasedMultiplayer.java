// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.multiplayer.turnbased;

import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import android.content.Intent;
import com.google.android.gms.games.multiplayer.ParticipantResult;
import java.util.List;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.util.VisibleForTesting;

@Deprecated
@VisibleForTesting
public interface TurnBasedMultiplayer
{
    PendingResult<InitiateMatchResult> acceptInvitation(final GoogleApiClient p0, final String p1);
    
    PendingResult<CancelMatchResult> cancelMatch(final GoogleApiClient p0, final String p1);
    
    PendingResult<InitiateMatchResult> createMatch(final GoogleApiClient p0, final TurnBasedMatchConfig p1);
    
    void declineInvitation(final GoogleApiClient p0, final String p1);
    
    void dismissInvitation(final GoogleApiClient p0, final String p1);
    
    void dismissMatch(final GoogleApiClient p0, final String p1);
    
    PendingResult<UpdateMatchResult> finishMatch(final GoogleApiClient p0, final String p1);
    
    PendingResult<UpdateMatchResult> finishMatch(final GoogleApiClient p0, final String p1, final byte[] p2, final List<ParticipantResult> p3);
    
    PendingResult<UpdateMatchResult> finishMatch(final GoogleApiClient p0, final String p1, final byte[] p2, final ParticipantResult... p3);
    
    Intent getInboxIntent(final GoogleApiClient p0);
    
    int getMaxMatchDataSize(final GoogleApiClient p0);
    
    Intent getSelectOpponentsIntent(final GoogleApiClient p0, final int p1, final int p2);
    
    Intent getSelectOpponentsIntent(final GoogleApiClient p0, final int p1, final int p2, final boolean p3);
    
    PendingResult<LeaveMatchResult> leaveMatch(final GoogleApiClient p0, final String p1);
    
    PendingResult<LeaveMatchResult> leaveMatchDuringTurn(final GoogleApiClient p0, final String p1, final String p2);
    
    PendingResult<LoadMatchResult> loadMatch(final GoogleApiClient p0, final String p1);
    
    PendingResult<LoadMatchesResult> loadMatchesByStatus(final GoogleApiClient p0, final int p1, final int[] p2);
    
    PendingResult<LoadMatchesResult> loadMatchesByStatus(final GoogleApiClient p0, final int[] p1);
    
    void registerMatchUpdateListener(final GoogleApiClient p0, final OnTurnBasedMatchUpdateReceivedListener p1);
    
    PendingResult<InitiateMatchResult> rematch(final GoogleApiClient p0, final String p1);
    
    PendingResult<UpdateMatchResult> takeTurn(final GoogleApiClient p0, final String p1, final byte[] p2, final String p3);
    
    PendingResult<UpdateMatchResult> takeTurn(final GoogleApiClient p0, final String p1, final byte[] p2, final String p3, final List<ParticipantResult> p4);
    
    PendingResult<UpdateMatchResult> takeTurn(final GoogleApiClient p0, final String p1, final byte[] p2, final String p3, final ParticipantResult... p4);
    
    void unregisterMatchUpdateListener(final GoogleApiClient p0);
    
    @Deprecated
    public interface CancelMatchResult extends Result
    {
        String getMatchId();
    }
    
    @Deprecated
    public interface InitiateMatchResult extends Result
    {
        TurnBasedMatch getMatch();
    }
    
    @Deprecated
    public interface LeaveMatchResult extends Result
    {
        TurnBasedMatch getMatch();
    }
    
    @Deprecated
    public interface LoadMatchResult extends Result
    {
        TurnBasedMatch getMatch();
    }
    
    @Deprecated
    public interface LoadMatchesResult extends Releasable, Result
    {
        LoadMatchesResponse getMatches();
    }
    
    @Deprecated
    public interface UpdateMatchResult extends Result
    {
        TurnBasedMatch getMatch();
    }
}
