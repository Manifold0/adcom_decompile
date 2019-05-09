package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil.ResultConverter;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.CancelMatchResult;

final class zzch implements ResultConverter<CancelMatchResult, String> {
    zzch() {
    }

    public final /* synthetic */ Object convert(Result result) {
        CancelMatchResult cancelMatchResult = (CancelMatchResult) result;
        return cancelMatchResult == null ? null : cancelMatchResult.getMatchId();
    }
}
