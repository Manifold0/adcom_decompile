package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil.ResultConverter;
import com.google.android.gms.games.video.VideoCapabilities;
import com.google.android.gms.games.video.Videos.CaptureCapabilitiesResult;

final class zzdc implements ResultConverter<CaptureCapabilitiesResult, VideoCapabilities> {
    zzdc() {
    }

    public final /* synthetic */ Object convert(Result result) {
        CaptureCapabilitiesResult captureCapabilitiesResult = (CaptureCapabilitiesResult) result;
        return captureCapabilitiesResult == null ? null : captureCapabilitiesResult.getCapabilities();
    }
}
