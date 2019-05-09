package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.CancelMatchResult;

final class zzdn implements CancelMatchResult {
    private final /* synthetic */ Status zzbc;
    private final /* synthetic */ zzdm zzko;

    zzdn(zzdm zzdm, Status status) {
        this.zzko = zzdm;
        this.zzbc = status;
    }

    public final String getMatchId() {
        return this.zzko.zzji;
    }

    public final Status getStatus() {
        return this.zzbc;
    }
}
