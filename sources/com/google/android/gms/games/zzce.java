package com.google.android.gms.games;

import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil.ResultConverter;
import com.google.android.gms.games.multiplayer.turnbased.LoadMatchesResponse;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.LoadMatchesResult;

final class zzce implements ResultConverter<LoadMatchesResult, LoadMatchesResponse> {
    zzce() {
    }

    public final /* synthetic */ Object convert(@Nullable Result result) {
        LoadMatchesResult loadMatchesResult = (LoadMatchesResult) result;
        return loadMatchesResult == null ? null : loadMatchesResult.getMatches();
    }
}
