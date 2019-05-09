// 
// Decompiled by Procyon v0.5.34
// 

package dagger.internal;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Collections;
import javax.inject.Provider;
import java.util.Map;

public final class MapFactory<K, V> implements Factory<Map<K, V>>
{
    private final Map<K, Provider<V>> contributingMap;
    
    private MapFactory(final Map<K, Provider<V>> map) {
        this.contributingMap = Collections.unmodifiableMap((Map<? extends K, ? extends Provider<V>>)map);
    }
    
    public static <K, V> MapFactory<K, V> create(final Provider<Map<K, Provider<V>>> provider) {
        return new MapFactory<K, V>((Map<K, javax.inject.Provider<V>>)provider.get());
    }
    
    public Map<K, V> get() {
        final LinkedHashMap<Object, Object> linkedHashMapWithExpectedSize = DaggerCollections.newLinkedHashMapWithExpectedSize(this.contributingMap.size());
        for (final Map.Entry<K, Provider<V>> entry : this.contributingMap.entrySet()) {
            linkedHashMapWithExpectedSize.put(entry.getKey(), entry.getValue().get());
        }
        return Collections.unmodifiableMap((Map<? extends K, ? extends V>)linkedHashMapWithExpectedSize);
    }
}
