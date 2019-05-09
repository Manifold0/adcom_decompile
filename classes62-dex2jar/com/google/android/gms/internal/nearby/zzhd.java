// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import java.util.WeakHashMap;
import java.lang.ref.WeakReference;
import java.util.Map;

public final class zzhd<K, V>
{
    private final Map<K, WeakReference<V>> map;
    
    public zzhd() {
        this.map = new WeakHashMap<K, WeakReference<V>>();
    }
    
    public final void clear() {
        this.map.clear();
    }
    
    public final boolean containsKey(final K k) {
        return this.get(k) != null;
    }
    
    public final V get(final K k) {
        final WeakReference<V> weakReference = this.map.get(k);
        if (weakReference == null) {
            return null;
        }
        return (V)weakReference.get();
    }
    
    public final void remove(final K k) {
        this.map.remove(k);
    }
    
    public final void zza(final K k, final V v) {
        this.map.put(k, new WeakReference<V>(v));
    }
}
