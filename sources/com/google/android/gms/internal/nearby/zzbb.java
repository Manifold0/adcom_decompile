package com.google.android.gms.internal.nearby;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.nearby.connection.Connections.StartAdvertisingResult;

final class zzbb implements StartAdvertisingResult {
    private final String zzcc;
    private final Status zzt;

    zzbb(Status status, String str) {
        this.zzt = status;
        this.zzcc = str;
    }

    public final String getLocalEndpointName() {
        return this.zzcc;
    }

    public final Status getStatus() {
        return this.zzt;
    }
}
