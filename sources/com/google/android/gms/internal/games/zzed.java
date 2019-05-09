package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.video.Videos.CaptureAvailableResult;

final class zzed implements CaptureAvailableResult {
    private final /* synthetic */ Status zzbc;

    zzed(zzec zzec, Status status) {
        this.zzbc = status;
    }

    public final Status getStatus() {
        return this.zzbc;
    }

    public final boolean isAvailable() {
        return false;
    }
}
