package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.LeaveMatchResult;

final class zzdr implements LeaveMatchResult {
    private final /* synthetic */ Status zzbc;

    zzdr(zzdq zzdq, Status status) {
        this.zzbc = status;
    }

    public final TurnBasedMatch getMatch() {
        return null;
    }

    public final Status getStatus() {
        return this.zzbc;
    }
}
