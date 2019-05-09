// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.games.multiplayer.turnbased.OnTurnBasedMatchUpdateReceivedListener;
import android.content.Intent;
import java.util.List;
import com.google.android.gms.games.multiplayer.ParticipantResult;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchConfig;
import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;

public final class zzdb implements TurnBasedMultiplayer
{
    @Override
    public final PendingResult<InitiateMatchResult> acceptInvitation(final GoogleApiClient googleApiClient, final String s) {
        return (PendingResult<InitiateMatchResult>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzdf(this, googleApiClient, s));
    }
    
    @Override
    public final PendingResult<CancelMatchResult> cancelMatch(final GoogleApiClient googleApiClient, final String s) {
        return (PendingResult<CancelMatchResult>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzdk(this, s, googleApiClient, s));
    }
    
    @Override
    public final PendingResult<InitiateMatchResult> createMatch(final GoogleApiClient googleApiClient, final TurnBasedMatchConfig turnBasedMatchConfig) {
        return (PendingResult<InitiateMatchResult>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzdc(this, googleApiClient, turnBasedMatchConfig));
    }
    
    @Override
    public final void declineInvitation(final GoogleApiClient googleApiClient, final String s) {
        final zze zza = Games.zza(googleApiClient, false);
        if (zza != null) {
            zza.zze(s, 1);
        }
    }
    
    @Override
    public final void dismissInvitation(final GoogleApiClient googleApiClient, final String s) {
        final zze zza = Games.zza(googleApiClient, false);
        if (zza != null) {
            zza.zzc(s, 1);
        }
    }
    
    @Override
    public final void dismissMatch(final GoogleApiClient googleApiClient, final String s) {
        final zze zza = Games.zza(googleApiClient, false);
        if (zza != null) {
            zza.zzc(s);
        }
    }
    
    @Override
    public final PendingResult<UpdateMatchResult> finishMatch(final GoogleApiClient googleApiClient, final String s) {
        return this.finishMatch(googleApiClient, s, null, (ParticipantResult[])null);
    }
    
    @Override
    public final PendingResult<UpdateMatchResult> finishMatch(final GoogleApiClient googleApiClient, final String s, final byte[] array, final List<ParticipantResult> list) {
        ParticipantResult[] array2;
        if (list == null) {
            array2 = null;
        }
        else {
            array2 = list.toArray(new ParticipantResult[list.size()]);
        }
        return this.finishMatch(googleApiClient, s, array, array2);
    }
    
    @Override
    public final PendingResult<UpdateMatchResult> finishMatch(final GoogleApiClient googleApiClient, final String s, final byte[] array, final ParticipantResult... array2) {
        return (PendingResult<UpdateMatchResult>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzdh(this, googleApiClient, s, array, array2));
    }
    
    @Override
    public final Intent getInboxIntent(final GoogleApiClient googleApiClient) {
        return Games.zza(googleApiClient).zzy();
    }
    
    @Override
    public final int getMaxMatchDataSize(final GoogleApiClient googleApiClient) {
        return Games.zza(googleApiClient).zzap();
    }
    
    @Override
    public final Intent getSelectOpponentsIntent(final GoogleApiClient googleApiClient, final int n, final int n2) {
        return Games.zza(googleApiClient).zzb(n, n2, true);
    }
    
    @Override
    public final Intent getSelectOpponentsIntent(final GoogleApiClient googleApiClient, final int n, final int n2, final boolean b) {
        return Games.zza(googleApiClient).zzb(n, n2, b);
    }
    
    @Override
    public final PendingResult<LeaveMatchResult> leaveMatch(final GoogleApiClient googleApiClient, final String s) {
        return (PendingResult<LeaveMatchResult>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzdi(this, googleApiClient, s));
    }
    
    @Override
    public final PendingResult<LeaveMatchResult> leaveMatchDuringTurn(final GoogleApiClient googleApiClient, final String s, final String s2) {
        return (PendingResult<LeaveMatchResult>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzdj(this, googleApiClient, s, s2));
    }
    
    @Override
    public final PendingResult<LoadMatchResult> loadMatch(final GoogleApiClient googleApiClient, final String s) {
        return (PendingResult<LoadMatchResult>)googleApiClient.enqueue((BaseImplementation$ApiMethodImpl)new zzdd(this, googleApiClient, s));
    }
    
    @Override
    public final PendingResult<LoadMatchesResult> loadMatchesByStatus(final GoogleApiClient googleApiClient, final int n, final int[] array) {
        return (PendingResult<LoadMatchesResult>)googleApiClient.enqueue((BaseImplementation$ApiMethodImpl)new zzdl(this, googleApiClient, n, array));
    }
    
    @Override
    public final PendingResult<LoadMatchesResult> loadMatchesByStatus(final GoogleApiClient googleApiClient, final int[] array) {
        return this.loadMatchesByStatus(googleApiClient, 0, array);
    }
    
    @Override
    public final void registerMatchUpdateListener(final GoogleApiClient googleApiClient, final OnTurnBasedMatchUpdateReceivedListener onTurnBasedMatchUpdateReceivedListener) {
        final zze zza = Games.zza(googleApiClient, false);
        if (zza != null) {
            zza.zzd((ListenerHolder<OnTurnBasedMatchUpdateReceivedListener>)googleApiClient.registerListener((Object)onTurnBasedMatchUpdateReceivedListener));
        }
    }
    
    @Override
    public final PendingResult<InitiateMatchResult> rematch(final GoogleApiClient googleApiClient, final String s) {
        return (PendingResult<InitiateMatchResult>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzde(this, googleApiClient, s));
    }
    
    @Override
    public final PendingResult<UpdateMatchResult> takeTurn(final GoogleApiClient googleApiClient, final String s, final byte[] array, final String s2) {
        return this.takeTurn(googleApiClient, s, array, s2, (ParticipantResult[])null);
    }
    
    @Override
    public final PendingResult<UpdateMatchResult> takeTurn(final GoogleApiClient googleApiClient, final String s, final byte[] array, final String s2, final List<ParticipantResult> list) {
        ParticipantResult[] array2;
        if (list == null) {
            array2 = null;
        }
        else {
            array2 = list.toArray(new ParticipantResult[list.size()]);
        }
        return this.takeTurn(googleApiClient, s, array, s2, array2);
    }
    
    @Override
    public final PendingResult<UpdateMatchResult> takeTurn(final GoogleApiClient googleApiClient, final String s, final byte[] array, final String s2, final ParticipantResult... array2) {
        return (PendingResult<UpdateMatchResult>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzdg(this, googleApiClient, s, array, s2, array2));
    }
    
    @Override
    public final void unregisterMatchUpdateListener(final GoogleApiClient googleApiClient) {
        final zze zza = Games.zza(googleApiClient, false);
        if (zza != null) {
            zza.zzad();
        }
    }
}
