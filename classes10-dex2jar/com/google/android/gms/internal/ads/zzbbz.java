// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Map;

final class zzbbz<K> implements Entry<K, Object>
{
    private Entry<K, zzbbx> zzdvi;
    
    private zzbbz(final Entry<K, zzbbx> zzdvi) {
        this.zzdvi = zzdvi;
    }
    
    @Override
    public final K getKey() {
        return this.zzdvi.getKey();
    }
    
    @Override
    public final Object getValue() {
        if (this.zzdvi.getValue() == null) {
            return null;
        }
        return zzbbx.zzadu();
    }
    
    @Override
    public final Object setValue(final Object o) {
        if (!(o instanceof zzbcu)) {
            throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
        }
        return this.zzdvi.getValue().zzl((zzbcu)o);
    }
    
    public final zzbbx zzadv() {
        return this.zzdvi.getValue();
    }
}
