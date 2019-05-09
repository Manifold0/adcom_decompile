// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Map;
import java.util.Iterator;

final class zzbds extends zzbdy
{
    private final /* synthetic */ zzbdp zzdyq;
    
    private zzbds(final zzbdp zzdyq) {
        this.zzdyq = zzdyq;
        super(zzdyq, null);
    }
    
    @Override
    public final Iterator<Map.Entry<Object, Object>> iterator() {
        return new zzbdr(this.zzdyq, null);
    }
}
