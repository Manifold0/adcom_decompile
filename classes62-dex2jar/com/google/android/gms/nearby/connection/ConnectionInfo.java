// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.connection;

import android.support.annotation.NonNull;

public final class ConnectionInfo
{
    private final String zzq;
    private final String zzr;
    private final boolean zzs;
    
    @Deprecated
    public ConnectionInfo(final String zzq, final String zzr, final boolean zzs) {
        this.zzq = zzq;
        this.zzr = zzr;
        this.zzs = zzs;
    }
    
    @NonNull
    public final String getAuthenticationToken() {
        return this.zzr;
    }
    
    @NonNull
    public final String getEndpointName() {
        return this.zzq;
    }
    
    public final boolean isIncomingConnection() {
        return this.zzs;
    }
}
