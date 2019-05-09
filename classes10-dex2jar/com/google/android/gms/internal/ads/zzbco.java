// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.Map;
import java.util.LinkedHashMap;

public final class zzbco<K, V> extends LinkedHashMap<K, V>
{
    private static final zzbco zzdwc;
    private boolean zzdpi;
    
    static {
        (zzdwc = new zzbco()).zzdpi = false;
    }
    
    private zzbco() {
        this.zzdpi = true;
    }
    
    private zzbco(final Map<K, V> map) {
        super(map);
        this.zzdpi = true;
    }
    
    public static <K, V> zzbco<K, V> zzaeb() {
        return (zzbco<K, V>)zzbco.zzdwc;
    }
    
    private final void zzaed() {
        if (!this.zzdpi) {
            throw new UnsupportedOperationException();
        }
    }
    
    private static int zzr(final Object o) {
        if (o instanceof byte[]) {
            return zzbbq.hashCode((byte[])o);
        }
        if (o instanceof zzbbr) {
            throw new UnsupportedOperationException();
        }
        return o.hashCode();
    }
    
    @Override
    public final void clear() {
        this.zzaed();
        super.clear();
    }
    
    @Override
    public final Set<Map.Entry<K, V>> entrySet() {
        if (this.isEmpty()) {
            return Collections.emptySet();
        }
        return super.entrySet();
    }
    
    @Override
    public final boolean equals(final Object o) {
        if (o instanceof Map) {
            final Map map = (Map)o;
        Label_0034:
            while (true) {
                Label_0170: {
                    if (this == map) {
                        break Label_0170;
                    }
                    if (this.size() == map.size()) {
                        for (final Map.Entry<K, V> entry : this.entrySet()) {
                            if (!map.containsKey(entry.getKey())) {
                                final int n = 0;
                                break Label_0034;
                            }
                            final V value = entry.getValue();
                            final byte[] value2 = map.get(entry.getKey());
                            boolean b;
                            if (value instanceof byte[] && value2 instanceof byte[]) {
                                b = Arrays.equals((byte[])(Object)value, value2);
                            }
                            else {
                                b = value.equals(value2);
                            }
                            if (!b) {
                                final int n = 0;
                                break Label_0034;
                            }
                        }
                        break Label_0170;
                    }
                    final int n = 0;
                    if (n != 0) {
                        return true;
                    }
                    return false;
                }
                final int n = 1;
                continue Label_0034;
            }
        }
        return false;
    }
    
    @Override
    public final int hashCode() {
        final Iterator<Map.Entry<K, V>> iterator = this.entrySet().iterator();
        int n = 0;
        while (iterator.hasNext()) {
            final Map.Entry<K, V> entry = iterator.next();
            n += (zzr(entry.getValue()) ^ zzr(entry.getKey()));
        }
        return n;
    }
    
    public final boolean isMutable() {
        return this.zzdpi;
    }
    
    @Override
    public final V put(final K k, final V v) {
        this.zzaed();
        zzbbq.checkNotNull(k);
        zzbbq.checkNotNull(v);
        return super.put(k, v);
    }
    
    @Override
    public final void putAll(final Map<? extends K, ? extends V> map) {
        this.zzaed();
        for (final K next : map.keySet()) {
            zzbbq.checkNotNull((Object)next);
            zzbbq.checkNotNull(map.get(next));
        }
        super.putAll(map);
    }
    
    @Override
    public final V remove(final Object o) {
        this.zzaed();
        return super.remove(o);
    }
    
    public final void zza(final zzbco<K, V> zzbco) {
        this.zzaed();
        if (!zzbco.isEmpty()) {
            this.putAll((Map<? extends K, ? extends V>)zzbco);
        }
    }
    
    public final void zzaaz() {
        this.zzdpi = false;
    }
    
    public final zzbco<K, V> zzaec() {
        if (this.isEmpty()) {
            return new zzbco<K, V>();
        }
        return new zzbco<K, V>(this);
    }
}
