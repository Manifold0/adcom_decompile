package com.google.android.gms.games;

import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil.ResultConverter;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.UpdateMatchResult;

final class zzcm implements ResultConverter<UpdateMatchResult, TurnBasedMatch> {
    zzcm() {
    }

    public final /* synthetic */ Object convert(@Nullable Result result) {
        UpdateMatchResult updateMatchResult = (UpdateMatchResult) result;
        if (updateMatchResult != null) {
            TurnBasedMatch match = updateMatchResult.getMatch();
            if (match != null) {
                return (TurnBasedMatch) match.freeze();
            }
        }
        return null;
    }
}
