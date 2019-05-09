package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

final class zzac implements Result {
    private final /* synthetic */ Status zzbc;

    zzac(zzab zzab, Status status) {
        this.zzbc = status;
    }

    public final Status getStatus() {
        return this.zzbc;
    }
}
