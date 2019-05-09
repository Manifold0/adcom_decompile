// 
// Decompiled by Procyon v0.5.34
// 

package dagger.internal;

import dagger.Lazy;
import javax.inject.Provider;

public final class SingleCheck<T> implements Provider<T>, Lazy<T>
{
    private static final Object UNINITIALIZED;
    private volatile Object instance;
    private volatile Provider<T> provider;
    
    static {
        UNINITIALIZED = new Object();
    }
    
    private SingleCheck(final Provider<T> provider) {
        this.instance = SingleCheck.UNINITIALIZED;
        assert provider != null;
        this.provider = provider;
    }
    
    public static <T> Provider<T> provider(final Provider<T> provider) {
        if (provider instanceof SingleCheck || provider instanceof DoubleCheck) {
            return provider;
        }
        return (Provider<T>)new SingleCheck((javax.inject.Provider<Object>)Preconditions.checkNotNull(provider));
    }
    
    public T get() {
        final Provider<T> provider = this.provider;
        if (this.instance == SingleCheck.UNINITIALIZED) {
            this.instance = provider.get();
            this.provider = null;
        }
        return (T)this.instance;
    }
}
