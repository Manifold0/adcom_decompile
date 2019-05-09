package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games.zza;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.CancelMatchResult;

abstract class zzdm extends zza<CancelMatchResult> {
    private final String zzji;

    public zzdm(String str, GoogleApiClient googleApiClient) {
        super(googleApiClient);
        this.zzji = str;
    }

    public /* synthetic */ Result createFailedResult(Status status) {
        return new zzdn(this, status);
    }
}
