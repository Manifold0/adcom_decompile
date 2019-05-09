package com.google.android.gms.internal.nearby;

import com.google.android.gms.nearby.connection.EndpointDiscoveryCallback;

final class zzam extends zzau<EndpointDiscoveryCallback> {
    private final /* synthetic */ zzet zzbs;

    zzam(zzak zzak, zzet zzet) {
        this.zzbs = zzet;
        super();
    }

    public final /* synthetic */ void notifyListener(Object obj) {
        ((EndpointDiscoveryCallback) obj).onEndpointLost(this.zzbs.zze());
    }
}
