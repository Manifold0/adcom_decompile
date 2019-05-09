// 
// Decompiled by Procyon v0.5.34
// 

package dagger.internal;

import dagger.Lazy;
import javax.inject.Provider;

public final class ProviderOfLazy<T> implements Provider<Lazy<T>>
{
    private final Provider<T> provider;
    
    private ProviderOfLazy(final Provider<T> provider) {
        assert provider != null;
        this.provider = provider;
    }
    
    public static <T> Provider<Lazy<T>> create(final Provider<T> provider) {
        return (Provider<Lazy<T>>)new ProviderOfLazy((javax.inject.Provider<Object>)Preconditions.checkNotNull(provider));
    }
    
    public Lazy<T> get() {
        return DoubleCheck.lazy(this.provider);
    }
}
