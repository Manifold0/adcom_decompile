package com.google.android.gms.internal.nearby;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.nearby.connection.Connections.StartAdvertisingResult;

final class zzcx implements StartAdvertisingResult {
    private final /* synthetic */ Status zzbj;

    zzcx(zzcw zzcw, Status status) {
        this.zzbj = status;
    }

    public final String getLocalEndpointName() {
        return null;
    }

    public final Status getStatus() {
        return this.zzbj;
    }
}
