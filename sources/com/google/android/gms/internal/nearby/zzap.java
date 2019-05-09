package com.google.android.gms.internal.nearby;

import com.google.android.gms.nearby.connection.Connections.EndpointDiscoveryListener;

final class zzap extends zzau<EndpointDiscoveryListener> {
    private final /* synthetic */ zzer zzbr;

    zzap(zzao zzao, zzer zzer) {
        this.zzbr = zzer;
        super();
    }

    public final /* synthetic */ void notifyListener(Object obj) {
        ((EndpointDiscoveryListener) obj).onEndpointFound(this.zzbr.zze(), this.zzbr.getServiceId(), this.zzbr.getEndpointName());
    }
}
