package com.google.android.gms.games;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games.GetServerAuthCodeResult;

final class zzm implements GetServerAuthCodeResult {
    private final /* synthetic */ Status zzbc;

    zzm(zzc zzc, Status status) {
        this.zzbc = status;
    }

    public final String getCode() {
        return null;
    }

    public final Status getStatus() {
        return this.zzbc;
    }
}
