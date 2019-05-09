// 
// Decompiled by Procyon v0.5.34
// 

package dagger.internal;

import java.util.LinkedHashMap;
import java.util.Collections;
import javax.inject.Provider;
import java.util.Map;

public final class MapProviderFactory<K, V> implements Factory<Map<K, Provider<V>>>
{
    private static final MapProviderFactory<Object, Object> EMPTY;
    private final Map<K, Provider<V>> contributingMap;
    
    static {
        EMPTY = new MapProviderFactory<Object, Object>(Collections.emptyMap());
    }
    
    private MapProviderFactory(final Map<K, Provider<V>> map) {
        this.contributingMap = Collections.unmodifiableMap((Map<? extends K, ? extends Provider<V>>)map);
    }
    
    public static <K, V> Builder<K, V> builder(final int n) {
        return new Builder<K, V>(n);
    }
    
    public static <K, V> MapProviderFactory<K, V> empty() {
        return (MapProviderFactory<K, V>)MapProviderFactory.EMPTY;
    }
    
    public Map<K, Provider<V>> get() {
        return this.contributingMap;
    }
    
    public static final class Builder<K, V>
    {
        private final LinkedHashMap<K, Provider<V>> mapBuilder;
        
        private Builder(final int n) {
            this.mapBuilder = DaggerCollections.newLinkedHashMapWithExpectedSize(n);
        }
        
        public MapProviderFactory<K, V> build() {
            return new MapProviderFactory<K, V>(this.mapBuilder, null);
        }
        
        public Builder<K, V> put(final K k, final Provider<V> provider) {
            if (k == null) {
                throw new NullPointerException("The key is null");
            }
            if (provider == null) {
                throw new NullPointerException("The provider of the value is null");
            }
            this.mapBuilder.put(k, provider);
            return this;
        }
    }
}
