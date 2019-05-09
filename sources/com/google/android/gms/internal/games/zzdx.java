package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.UpdateMatchResult;

final class zzdx implements UpdateMatchResult {
    private final /* synthetic */ Status zzbc;

    zzdx(zzdw zzdw, Status status) {
        this.zzbc = status;
    }

    public final TurnBasedMatch getMatch() {
        return null;
    }

    public final Status getStatus() {
        return this.zzbc;
    }
}
