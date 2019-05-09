// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.UnregisterListenerMethod;
import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchUpdateCallback;
import android.support.annotation.IntRange;
import android.content.Intent;
import com.google.android.gms.games.multiplayer.ParticipantResult;
import java.util.List;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchConfig;
import com.google.android.gms.games.internal.zzi;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.common.api.PendingResult;
import android.content.Context;
import android.support.annotation.NonNull;
import android.app.Activity;
import com.google.android.gms.games.internal.zzr;
import com.google.android.gms.games.internal.zzq;
import com.google.android.gms.games.multiplayer.turnbased.LoadMatchesResponse;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;
import com.google.android.gms.common.internal.PendingResultUtil$ResultConverter;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;
import com.google.android.gms.games.internal.zzp;
import com.google.android.gms.internal.games.zzu;

public class TurnBasedMultiplayerClient extends zzu
{
    private static final zzp<TurnBasedMatch> zzel;
    private static final PendingResultUtil$ResultConverter<TurnBasedMultiplayer.LoadMatchesResult, LoadMatchesResponse> zzem;
    private static final zzq<TurnBasedMultiplayer.LoadMatchesResult> zzen;
    private static final PendingResultUtil$ResultConverter<TurnBasedMultiplayer.LoadMatchResult, TurnBasedMatch> zzeo;
    private static final PendingResultUtil$ResultConverter<TurnBasedMultiplayer.CancelMatchResult, String> zzep;
    private static final zzr zzeq;
    private static final PendingResultUtil$ResultConverter<TurnBasedMultiplayer.LeaveMatchResult, Void> zzer;
    private static final PendingResultUtil$ResultConverter<TurnBasedMultiplayer.LeaveMatchResult, TurnBasedMatch> zzes;
    private static final zzr zzet;
    private static final PendingResultUtil$ResultConverter<TurnBasedMultiplayer.UpdateMatchResult, TurnBasedMatch> zzeu;
    private static final PendingResultUtil$ResultConverter<TurnBasedMultiplayer.InitiateMatchResult, TurnBasedMatch> zzev;
    
    static {
        zzel = new zzcv();
        zzem = (PendingResultUtil$ResultConverter)new zzce();
        zzen = new zzcf();
        zzeo = (PendingResultUtil$ResultConverter)new zzcg();
        zzep = (PendingResultUtil$ResultConverter)new zzch();
        zzeq = new zzci();
        zzer = (PendingResultUtil$ResultConverter)new zzcj();
        zzes = (PendingResultUtil$ResultConverter)new zzck();
        zzet = new zzcl();
        zzeu = (PendingResultUtil$ResultConverter)new zzcm();
        zzev = (PendingResultUtil$ResultConverter)new zzcn();
    }
    
    TurnBasedMultiplayerClient(@NonNull final Activity activity, @NonNull final Games.GamesOptions gamesOptions) {
        super(activity, gamesOptions);
    }
    
    TurnBasedMultiplayerClient(@NonNull final Context context, @NonNull final Games.GamesOptions gamesOptions) {
        super(context, gamesOptions);
    }
    
    private static Task<Void> zzd(@NonNull final PendingResult<TurnBasedMultiplayer.LeaveMatchResult> pendingResult) {
        return zzi.zza(pendingResult, TurnBasedMultiplayerClient.zzeq, TurnBasedMultiplayerClient.zzer, TurnBasedMultiplayerClient.zzes, TurnBasedMultiplayerClient.zzel);
    }
    
    private static Task<TurnBasedMatch> zze(@NonNull final PendingResult<TurnBasedMultiplayer.UpdateMatchResult> pendingResult) {
        return zzi.zza(pendingResult, TurnBasedMultiplayerClient.zzet, TurnBasedMultiplayerClient.zzeu, TurnBasedMultiplayerClient.zzeu, TurnBasedMultiplayerClient.zzel);
    }
    
    public Task<TurnBasedMatch> acceptInvitation(@NonNull final String s) {
        return zzi.toTask(Games.TurnBasedMultiplayer.acceptInvitation(this.asGoogleApiClient(), s), TurnBasedMultiplayerClient.zzev);
    }
    
    public Task<String> cancelMatch(@NonNull final String s) {
        return zzi.toTask(Games.TurnBasedMultiplayer.cancelMatch(this.asGoogleApiClient(), s), TurnBasedMultiplayerClient.zzep);
    }
    
    public Task<TurnBasedMatch> createMatch(@NonNull final TurnBasedMatchConfig turnBasedMatchConfig) {
        return zzi.toTask(Games.TurnBasedMultiplayer.createMatch(this.asGoogleApiClient(), turnBasedMatchConfig), TurnBasedMultiplayerClient.zzev);
    }
    
    public Task<Void> declineInvitation(@NonNull final String s) {
        return (Task<Void>)this.doWrite((TaskApiCall)new zzcr(this, s));
    }
    
    public Task<Void> dismissInvitation(@NonNull final String s) {
        return (Task<Void>)this.doWrite((TaskApiCall)new zzcs(this, s));
    }
    
    public Task<Void> dismissMatch(@NonNull final String s) {
        return (Task<Void>)this.doWrite((TaskApiCall)new zzcu(this, s));
    }
    
    public Task<TurnBasedMatch> finishMatch(@NonNull final String s) {
        return zze(Games.TurnBasedMultiplayer.finishMatch(this.asGoogleApiClient(), s));
    }
    
    public Task<TurnBasedMatch> finishMatch(@NonNull final String s, @Nullable final byte[] array, @Nullable final List<ParticipantResult> list) {
        return zze(Games.TurnBasedMultiplayer.finishMatch(this.asGoogleApiClient(), s, array, list));
    }
    
    public Task<TurnBasedMatch> finishMatch(@NonNull final String s, @Nullable final byte[] array, @Nullable final ParticipantResult... array2) {
        return zze(Games.TurnBasedMultiplayer.finishMatch(this.asGoogleApiClient(), s, array, array2));
    }
    
    public Task<Intent> getInboxIntent() {
        return (Task<Intent>)this.doRead((TaskApiCall)new zzcd(this));
    }
    
    public Task<Integer> getMaxMatchDataSize() {
        return (Task<Integer>)this.doRead((TaskApiCall)new zzct(this));
    }
    
    public Task<Intent> getSelectOpponentsIntent(@IntRange(from = 1L) final int n, @IntRange(from = 1L) final int n2) {
        return this.getSelectOpponentsIntent(n, n2, true);
    }
    
    public Task<Intent> getSelectOpponentsIntent(@IntRange(from = 1L) final int n, @IntRange(from = 1L) final int n2, final boolean b) {
        return (Task<Intent>)this.doRead((TaskApiCall)new zzcq(this, n, n2, b));
    }
    
    public Task<Void> leaveMatch(@NonNull final String s) {
        return zzd(Games.TurnBasedMultiplayer.leaveMatch(this.asGoogleApiClient(), s));
    }
    
    public Task<Void> leaveMatchDuringTurn(@NonNull final String s, @Nullable final String s2) {
        return zzd(Games.TurnBasedMultiplayer.leaveMatchDuringTurn(this.asGoogleApiClient(), s, s2));
    }
    
    public Task<AnnotatedData<TurnBasedMatch>> loadMatch(@NonNull final String s) {
        return zzi.zza(Games.TurnBasedMultiplayer.loadMatch(this.asGoogleApiClient(), s), TurnBasedMultiplayerClient.zzeo);
    }
    
    public Task<AnnotatedData<LoadMatchesResponse>> loadMatchesByStatus(final int n, @NonNull final int[] array) {
        return zzi.zza(Games.TurnBasedMultiplayer.loadMatchesByStatus(this.asGoogleApiClient(), n, array), TurnBasedMultiplayerClient.zzem, TurnBasedMultiplayerClient.zzen);
    }
    
    public Task<AnnotatedData<LoadMatchesResponse>> loadMatchesByStatus(@NonNull final int[] array) {
        return zzi.zza(Games.TurnBasedMultiplayer.loadMatchesByStatus(this.asGoogleApiClient(), array), TurnBasedMultiplayerClient.zzem, TurnBasedMultiplayerClient.zzen);
    }
    
    public Task<Void> registerTurnBasedMatchUpdateCallback(@NonNull final TurnBasedMatchUpdateCallback turnBasedMatchUpdateCallback) {
        final ListenerHolder registerListener = this.registerListener((Object)turnBasedMatchUpdateCallback, TurnBasedMatchUpdateCallback.class.getSimpleName());
        return (Task<Void>)this.doRegisterEventListener((RegisterListenerMethod)new zzco(this, registerListener, registerListener), (UnregisterListenerMethod)new zzcp(this, registerListener.getListenerKey()));
    }
    
    public Task<TurnBasedMatch> rematch(@NonNull final String s) {
        return zzi.toTask(Games.TurnBasedMultiplayer.rematch(this.asGoogleApiClient(), s), TurnBasedMultiplayerClient.zzev);
    }
    
    public Task<TurnBasedMatch> takeTurn(@NonNull final String s, @Nullable final byte[] array, @Nullable final String s2) {
        return zze(Games.TurnBasedMultiplayer.takeTurn(this.asGoogleApiClient(), s, array, s2));
    }
    
    public Task<TurnBasedMatch> takeTurn(@NonNull final String s, @Nullable final byte[] array, @Nullable final String s2, @Nullable final List<ParticipantResult> list) {
        return zze(Games.TurnBasedMultiplayer.takeTurn(this.asGoogleApiClient(), s, array, s2, list));
    }
    
    public Task<TurnBasedMatch> takeTurn(@NonNull final String s, @Nullable final byte[] array, @Nullable final String s2, @Nullable final ParticipantResult... array2) {
        return zze(Games.TurnBasedMultiplayer.takeTurn(this.asGoogleApiClient(), s, array, s2, array2));
    }
    
    public Task<Boolean> unregisterTurnBasedMatchUpdateCallback(@NonNull final TurnBasedMatchUpdateCallback turnBasedMatchUpdateCallback) {
        return (Task<Boolean>)this.doUnregisterEventListener(ListenerHolders.createListenerKey((Object)turnBasedMatchUpdateCallback, TurnBasedMatchUpdateCallback.class.getSimpleName()));
    }
    
    public static class MatchOutOfDateApiException extends ApiException
    {
        protected final TurnBasedMatch match;
        
        MatchOutOfDateApiException(@NonNull final Status status, @NonNull final TurnBasedMatch match) {
            super(status);
            this.match = match;
        }
        
        public TurnBasedMatch getMatch() {
            return this.match;
        }
    }
}
