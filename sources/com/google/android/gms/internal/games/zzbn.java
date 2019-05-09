package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.PlayerBuffer;
import com.google.android.gms.games.Players.LoadPlayersResult;

final class zzbn implements LoadPlayersResult {
    private final /* synthetic */ Status zzbc;

    zzbn(zzbm zzbm, Status status) {
        this.zzbc = status;
    }

    public final PlayerBuffer getPlayers() {
        return new PlayerBuffer(DataHolder.empty(14));
    }

    public final Status getStatus() {
        return this.zzbc;
    }

    public final void release() {
    }
}
