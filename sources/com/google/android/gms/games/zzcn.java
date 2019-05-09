package com.google.android.gms.games;

import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil.ResultConverter;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.InitiateMatchResult;

final class zzcn implements ResultConverter<InitiateMatchResult, TurnBasedMatch> {
    zzcn() {
    }

    public final /* synthetic */ Object convert(@Nullable Result result) {
        InitiateMatchResult initiateMatchResult = (InitiateMatchResult) result;
        if (initiateMatchResult != null) {
            TurnBasedMatch match = initiateMatchResult.getMatch();
            if (match != null) {
                return (TurnBasedMatch) match.freeze();
            }
        }
        return null;
    }
}
