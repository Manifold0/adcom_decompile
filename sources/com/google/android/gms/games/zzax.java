package com.google.android.gms.games;

import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil.ResultConverter;
import com.google.android.gms.games.Players.LoadPlayersResult;

final class zzax implements ResultConverter<LoadPlayersResult, PlayerBuffer> {
    zzax() {
    }

    public final /* synthetic */ Object convert(@Nullable Result result) {
        LoadPlayersResult loadPlayersResult = (LoadPlayersResult) result;
        return loadPlayersResult == null ? null : loadPlayersResult.getPlayers();
    }
}
