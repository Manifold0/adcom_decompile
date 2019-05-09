package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.video.VideoCapabilities;
import com.google.android.gms.games.video.Videos.CaptureCapabilitiesResult;

final class zzef implements CaptureCapabilitiesResult {
    private final /* synthetic */ Status zzbc;

    zzef(zzee zzee, Status status) {
        this.zzbc = status;
    }

    public final VideoCapabilities getCapabilities() {
        return null;
    }

    public final Status getStatus() {
        return this.zzbc;
    }
}
