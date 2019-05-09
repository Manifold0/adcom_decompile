// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.connection;

import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Status;

public final class ConnectionResolution
{
    private final Status zzt;
    
    @Deprecated
    public ConnectionResolution(final Status zzt) {
        this.zzt = zzt;
    }
    
    @NonNull
    public final Status getStatus() {
        return this.zzt;
    }
}
