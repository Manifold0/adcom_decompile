package com.google.android.gms.internal.nearby;

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;

public final class zzhd<K, V> {
    private final Map<K, WeakReference<V>> map = new WeakHashMap();

    public final void clear() {
        this.map.clear();
    }

    public final boolean containsKey(K k) {
        return get(k) != null;
    }

    public final V get(K k) {
        WeakReference weakReference = (WeakReference) this.map.get(k);
        return weakReference == null ? null : weakReference.get();
    }

    public final void remove(K k) {
        this.map.remove(k);
    }

    public final void zza(K k, V v) {
        this.map.put(k, new WeakReference(v));
    }
}
