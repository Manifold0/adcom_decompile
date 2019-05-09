package com.google.android.gms.games;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.internal.PendingResultUtil.ResultConverter;
import com.google.android.gms.games.Games.GamesOptions;
import com.google.android.gms.games.internal.zzi;
import com.google.android.gms.games.internal.zzp;
import com.google.android.gms.games.internal.zzq;
import com.google.android.gms.games.internal.zzr;
import com.google.android.gms.games.multiplayer.ParticipantResult;
import com.google.android.gms.games.multiplayer.turnbased.LoadMatchesResponse;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchConfig;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchUpdateCallback;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.CancelMatchResult;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.InitiateMatchResult;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.LeaveMatchResult;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.LoadMatchResult;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.LoadMatchesResult;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.UpdateMatchResult;
import com.google.android.gms.internal.games.zzu;
import com.google.android.gms.tasks.Task;
import java.util.List;

public class TurnBasedMultiplayerClient extends zzu {
    private static final zzp<TurnBasedMatch> zzel = new zzcv();
    private static final ResultConverter<LoadMatchesResult, LoadMatchesResponse> zzem = new zzce();
    private static final zzq<LoadMatchesResult> zzen = new zzcf();
    private static final ResultConverter<LoadMatchResult, TurnBasedMatch> zzeo = new zzcg();
    private static final ResultConverter<CancelMatchResult, String> zzep = new zzch();
    private static final zzr zzeq = new zzci();
    private static final ResultConverter<LeaveMatchResult, Void> zzer = new zzcj();
    private static final ResultConverter<LeaveMatchResult, TurnBasedMatch> zzes = new zzck();
    private static final zzr zzet = new zzcl();
    private static final ResultConverter<UpdateMatchResult, TurnBasedMatch> zzeu = new zzcm();
    private static final ResultConverter<InitiateMatchResult, TurnBasedMatch> zzev = new zzcn();

    public static class MatchOutOfDateApiException extends ApiException {
        protected final TurnBasedMatch match;

        MatchOutOfDateApiException(@NonNull Status status, @NonNull TurnBasedMatch turnBasedMatch) {
            super(status);
            this.match = turnBasedMatch;
        }

        public TurnBasedMatch getMatch() {
            return this.match;
        }
    }

    TurnBasedMultiplayerClient(@NonNull Activity activity, @NonNull GamesOptions gamesOptions) {
        super(activity, gamesOptions);
    }

    TurnBasedMultiplayerClient(@NonNull Context context, @NonNull GamesOptions gamesOptions) {
        super(context, gamesOptions);
    }

    private static Task<Void> zzd(@NonNull PendingResult<LeaveMatchResult> pendingResult) {
        return zzi.zza((PendingResult) pendingResult, zzeq, zzer, zzes, zzel);
    }

    private static Task<TurnBasedMatch> zze(@NonNull PendingResult<UpdateMatchResult> pendingResult) {
        return zzi.zza((PendingResult) pendingResult, zzet, zzeu, zzeu, zzel);
    }

    public Task<TurnBasedMatch> acceptInvitation(@NonNull String str) {
        return zzi.toTask(Games.TurnBasedMultiplayer.acceptInvitation(asGoogleApiClient(), str), zzev);
    }

    public Task<String> cancelMatch(@NonNull String str) {
        return zzi.toTask(Games.TurnBasedMultiplayer.cancelMatch(asGoogleApiClient(), str), zzep);
    }

    public Task<TurnBasedMatch> createMatch(@NonNull TurnBasedMatchConfig turnBasedMatchConfig) {
        return zzi.toTask(Games.TurnBasedMultiplayer.createMatch(asGoogleApiClient(), turnBasedMatchConfig), zzev);
    }

    public Task<Void> declineInvitation(@NonNull String str) {
        return doWrite(new zzcr(this, str));
    }

    public Task<Void> dismissInvitation(@NonNull String str) {
        return doWrite(new zzcs(this, str));
    }

    public Task<Void> dismissMatch(@NonNull String str) {
        return doWrite(new zzcu(this, str));
    }

    public Task<TurnBasedMatch> finishMatch(@NonNull String str) {
        return zze(Games.TurnBasedMultiplayer.finishMatch(asGoogleApiClient(), str));
    }

    public Task<TurnBasedMatch> finishMatch(@NonNull String str, @Nullable byte[] bArr, @Nullable List<ParticipantResult> list) {
        return zze(Games.TurnBasedMultiplayer.finishMatch(asGoogleApiClient(), str, bArr, (List) list));
    }

    public Task<TurnBasedMatch> finishMatch(@NonNull String str, @Nullable byte[] bArr, @Nullable ParticipantResult... participantResultArr) {
        return zze(Games.TurnBasedMultiplayer.finishMatch(asGoogleApiClient(), str, bArr, participantResultArr));
    }

    public Task<Intent> getInboxIntent() {
        return doRead(new zzcd(this));
    }

    public Task<Integer> getMaxMatchDataSize() {
        return doRead(new zzct(this));
    }

    public Task<Intent> getSelectOpponentsIntent(@IntRange(from = 1) int i, @IntRange(from = 1) int i2) {
        return getSelectOpponentsIntent(i, i2, true);
    }

    public Task<Intent> getSelectOpponentsIntent(@IntRange(from = 1) int i, @IntRange(from = 1) int i2, boolean z) {
        return doRead(new zzcq(this, i, i2, z));
    }

    public Task<Void> leaveMatch(@NonNull String str) {
        return zzd(Games.TurnBasedMultiplayer.leaveMatch(asGoogleApiClient(), str));
    }

    public Task<Void> leaveMatchDuringTurn(@NonNull String str, @Nullable String str2) {
        return zzd(Games.TurnBasedMultiplayer.leaveMatchDuringTurn(asGoogleApiClient(), str, str2));
    }

    public Task<AnnotatedData<TurnBasedMatch>> loadMatch(@NonNull String str) {
        return zzi.zza(Games.TurnBasedMultiplayer.loadMatch(asGoogleApiClient(), str), zzeo);
    }

    public Task<AnnotatedData<LoadMatchesResponse>> loadMatchesByStatus(int i, @NonNull int[] iArr) {
        return zzi.zza(Games.TurnBasedMultiplayer.loadMatchesByStatus(asGoogleApiClient(), i, iArr), zzem, zzen);
    }

    public Task<AnnotatedData<LoadMatchesResponse>> loadMatchesByStatus(@NonNull int[] iArr) {
        return zzi.zza(Games.TurnBasedMultiplayer.loadMatchesByStatus(asGoogleApiClient(), iArr), zzem, zzen);
    }

    public Task<Void> registerTurnBasedMatchUpdateCallback(@NonNull TurnBasedMatchUpdateCallback turnBasedMatchUpdateCallback) {
        ListenerHolder registerListener = registerListener(turnBasedMatchUpdateCallback, TurnBasedMatchUpdateCallback.class.getSimpleName());
        return doRegisterEventListener(new zzco(this, registerListener, registerListener), new zzcp(this, registerListener.getListenerKey()));
    }

    public Task<TurnBasedMatch> rematch(@NonNull String str) {
        return zzi.toTask(Games.TurnBasedMultiplayer.rematch(asGoogleApiClient(), str), zzev);
    }

    public Task<TurnBasedMatch> takeTurn(@NonNull String str, @Nullable byte[] bArr, @Nullable String str2) {
        return zze(Games.TurnBasedMultiplayer.takeTurn(asGoogleApiClient(), str, bArr, str2));
    }

    public Task<TurnBasedMatch> takeTurn(@NonNull String str, @Nullable byte[] bArr, @Nullable String str2, @Nullable List<ParticipantResult> list) {
        return zze(Games.TurnBasedMultiplayer.takeTurn(asGoogleApiClient(), str, bArr, str2, (List) list));
    }

    public Task<TurnBasedMatch> takeTurn(@NonNull String str, @Nullable byte[] bArr, @Nullable String str2, @Nullable ParticipantResult... participantResultArr) {
        return zze(Games.TurnBasedMultiplayer.takeTurn(asGoogleApiClient(), str, bArr, str2, participantResultArr));
    }

    public Task<Boolean> unregisterTurnBasedMatchUpdateCallback(@NonNull TurnBasedMatchUpdateCallback turnBasedMatchUpdateCallback) {
        return doUnregisterEventListener(ListenerHolders.createListenerKey(turnBasedMatchUpdateCallback, TurnBasedMatchUpdateCallback.class.getSimpleName()));
    }
}
