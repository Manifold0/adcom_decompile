// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.nearby.connection.Connections;

final class zzbb implements StartAdvertisingResult
{
    private final String zzcc;
    private final Status zzt;
    
    zzbb(final Status zzt, final String zzcc) {
        this.zzt = zzt;
        this.zzcc = zzcc;
    }
    
    @Override
    public final String getLocalEndpointName() {
        return this.zzcc;
    }
    
    public final Status getStatus() {
        return this.zzt;
    }
}
