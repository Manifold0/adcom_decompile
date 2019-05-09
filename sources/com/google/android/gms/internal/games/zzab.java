package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games.zza;

abstract class zzab extends zza<Result> {
    private zzab(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    public Result createFailedResult(Status status) {
        return new zzac(this, status);
    }
}
