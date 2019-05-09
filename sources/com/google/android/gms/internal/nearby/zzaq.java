package com.google.android.gms.internal.nearby;

import com.google.android.gms.nearby.connection.Connections.EndpointDiscoveryListener;

final class zzaq extends zzau<EndpointDiscoveryListener> {
    private final /* synthetic */ zzet zzbs;

    zzaq(zzao zzao, zzet zzet) {
        this.zzbs = zzet;
        super();
    }

    public final /* synthetic */ void notifyListener(Object obj) {
        ((EndpointDiscoveryListener) obj).onEndpointLost(this.zzbs.zze());
    }
}
