package com.google.android.gms.internal.nearby;

import com.google.android.gms.nearby.connection.DiscoveredEndpointInfo;
import com.google.android.gms.nearby.connection.EndpointDiscoveryCallback;

final class zzal extends zzau<EndpointDiscoveryCallback> {
    private final /* synthetic */ zzer zzbr;

    zzal(zzak zzak, zzer zzer) {
        this.zzbr = zzer;
        super();
    }

    public final /* synthetic */ void notifyListener(Object obj) {
        EndpointDiscoveryCallback endpointDiscoveryCallback = (EndpointDiscoveryCallback) obj;
        if ("__UNRECOGNIZED_BLUETOOTH_DEVICE__".equals(this.zzbr.zze())) {
            endpointDiscoveryCallback.onEndpointFound(this.zzbr.zze(), new DiscoveredEndpointInfo(this.zzbr.getServiceId(), this.zzbr.zzk()));
        } else {
            endpointDiscoveryCallback.onEndpointFound(this.zzbr.zze(), new DiscoveredEndpointInfo(this.zzbr.getServiceId(), this.zzbr.getEndpointName()));
        }
    }
}
