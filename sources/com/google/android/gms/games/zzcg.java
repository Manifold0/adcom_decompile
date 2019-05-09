package com.google.android.gms.games;

import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil.ResultConverter;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.LoadMatchResult;

final class zzcg implements ResultConverter<LoadMatchResult, TurnBasedMatch> {
    zzcg() {
    }

    public final /* synthetic */ Object convert(@Nullable Result result) {
        LoadMatchResult loadMatchResult = (LoadMatchResult) result;
        if (loadMatchResult != null) {
            TurnBasedMatch match = loadMatchResult.getMatch();
            if (match != null) {
                return (TurnBasedMatch) match.freeze();
            }
        }
        return null;
    }
}
