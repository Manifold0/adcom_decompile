// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.connection;

import android.support.annotation.NonNull;

public abstract class ConnectionLifecycleCallback
{
    public abstract void onConnectionInitiated(@NonNull final String p0, @NonNull final ConnectionInfo p1);
    
    public abstract void onConnectionResult(@NonNull final String p0, @NonNull final ConnectionResolution p1);
    
    public abstract void onDisconnected(@NonNull final String p0);
}
