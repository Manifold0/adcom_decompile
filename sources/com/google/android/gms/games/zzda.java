package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil.ResultConverter;
import com.google.android.gms.games.video.Videos.CaptureAvailableResult;

final class zzda implements ResultConverter<CaptureAvailableResult, Boolean> {
    zzda() {
    }

    public final /* synthetic */ Object convert(Result result) {
        CaptureAvailableResult captureAvailableResult = (CaptureAvailableResult) result;
        return captureAvailableResult == null ? Boolean.valueOf(false) : Boolean.valueOf(captureAvailableResult.isAvailable());
    }
}
