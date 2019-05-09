package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil.ResultConverter;
import com.google.android.gms.games.video.CaptureState;
import com.google.android.gms.games.video.Videos.CaptureStateResult;

final class zzdb implements ResultConverter<CaptureStateResult, CaptureState> {
    zzdb() {
    }

    public final /* synthetic */ Object convert(Result result) {
        CaptureStateResult captureStateResult = (CaptureStateResult) result;
        return captureStateResult == null ? null : captureStateResult.getCaptureState();
    }
}
