package com.google.android.gms.internal.nearby;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.nearby.connection.Connections.StartAdvertisingResult;

abstract class zzcw extends zzcv<StartAdvertisingResult> {
    private zzcw(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    public /* synthetic */ Result createFailedResult(Status status) {
        return new zzcx(this, status);
    }
}
