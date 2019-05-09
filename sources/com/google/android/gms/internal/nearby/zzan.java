package com.google.android.gms.internal.nearby;

import com.google.android.gms.nearby.connection.EndpointDiscoveryCallback;

final class zzan extends zzau<EndpointDiscoveryCallback> {
    private final /* synthetic */ String zzbm;

    zzan(zzak zzak, String str) {
        this.zzbm = str;
        super();
    }

    public final /* synthetic */ void notifyListener(Object obj) {
        ((EndpointDiscoveryCallback) obj).onEndpointLost(this.zzbm);
    }
}
