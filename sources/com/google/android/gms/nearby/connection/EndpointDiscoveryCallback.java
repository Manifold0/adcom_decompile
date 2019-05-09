package com.google.android.gms.nearby.connection;

import android.support.annotation.NonNull;

public abstract class EndpointDiscoveryCallback {
    public abstract void onEndpointFound(@NonNull String str, @NonNull DiscoveredEndpointInfo discoveredEndpointInfo);

    public abstract void onEndpointLost(@NonNull String str);
}
