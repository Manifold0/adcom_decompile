package com.google.android.gms.games;

import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil.ResultConverter;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.LeaveMatchResult;

final class zzck implements ResultConverter<LeaveMatchResult, TurnBasedMatch> {
    zzck() {
    }

    public final /* synthetic */ Object convert(@Nullable Result result) {
        LeaveMatchResult leaveMatchResult = (LeaveMatchResult) result;
        if (leaveMatchResult != null) {
            TurnBasedMatch match = leaveMatchResult.getMatch();
            if (match != null) {
                return (TurnBasedMatch) match.freeze();
            }
        }
        return null;
    }
}
