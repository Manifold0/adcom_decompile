package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.video.CaptureState;
import com.google.android.gms.games.video.Videos.CaptureStateResult;

final class zzeh implements CaptureStateResult {
    private final /* synthetic */ Status zzbc;

    zzeh(zzeg zzeg, Status status) {
        this.zzbc = status;
    }

    public final CaptureState getCaptureState() {
        return null;
    }

    public final Status getStatus() {
        return this.zzbc;
    }
}
