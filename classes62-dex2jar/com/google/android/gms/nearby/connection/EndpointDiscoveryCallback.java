// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.connection;

import android.support.annotation.NonNull;

public abstract class EndpointDiscoveryCallback
{
    public abstract void onEndpointFound(@NonNull final String p0, @NonNull final DiscoveredEndpointInfo p1);
    
    public abstract void onEndpointLost(@NonNull final String p0);
}
