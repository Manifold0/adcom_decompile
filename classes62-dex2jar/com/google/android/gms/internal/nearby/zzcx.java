// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.nearby.connection.Connections;

final class zzcx implements StartAdvertisingResult
{
    private final /* synthetic */ Status zzbj;
    
    zzcx(final zzcw zzcw, final Status zzbj) {
        this.zzbj = zzbj;
    }
    
    @Override
    public final String getLocalEndpointName() {
        return null;
    }
    
    public final Status getStatus() {
        return this.zzbj;
    }
}
