// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.List;

public final class zzbed extends RuntimeException
{
    private final List<String> zzdyw;
    
    public zzbed(final zzbcu zzbcu) {
        super("Message was missing required fields.  (Lite runtime could not determine which fields were missing).");
        this.zzdyw = null;
    }
    
    public final zzbbu zzaga() {
        return new zzbbu(this.getMessage());
    }
}
