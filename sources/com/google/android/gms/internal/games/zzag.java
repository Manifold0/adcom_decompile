package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.GameBuffer;
import com.google.android.gms.games.GamesMetadata.LoadGamesResult;

final class zzag implements LoadGamesResult {
    private final /* synthetic */ Status zzbc;

    zzag(zzaf zzaf, Status status) {
        this.zzbc = status;
    }

    public final GameBuffer getGames() {
        return new GameBuffer(DataHolder.empty(14));
    }

    public final Status getStatus() {
        return this.zzbc;
    }

    public final void release() {
    }
}
