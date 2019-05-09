// 
// Decompiled by Procyon v0.5.34
// 

package dagger.internal;

import dagger.Lazy;
import javax.inject.Provider;

public final class DoubleCheck<T> implements Provider<T>, Lazy<T>
{
    private static final Object UNINITIALIZED;
    private volatile Object instance;
    private volatile Provider<T> provider;
    
    static {
        UNINITIALIZED = new Object();
    }
    
    private DoubleCheck(final Provider<T> provider) {
        this.instance = DoubleCheck.UNINITIALIZED;
        assert provider != null;
        this.provider = provider;
    }
    
    public static <T> Lazy<T> lazy(final Provider<T> provider) {
        if (provider instanceof Lazy) {
            return (Lazy<T>)provider;
        }
        return new DoubleCheck<T>(Preconditions.checkNotNull(provider));
    }
    
    public static <T> Provider<T> provider(final Provider<T> provider) {
        Preconditions.checkNotNull(provider);
        if (provider instanceof DoubleCheck) {
            return provider;
        }
        return (Provider<T>)new DoubleCheck((javax.inject.Provider<Object>)provider);
    }
    
    public T get() {
        final Object instance;
        if ((instance = this.instance) == DoubleCheck.UNINITIALIZED) {
            Label_0108: {
                synchronized (this) {
                    if (this.instance != DoubleCheck.UNINITIALIZED) {
                        break Label_0108;
                    }
                    final Object value = this.provider.get();
                    final Object instance2 = this.instance;
                    if (instance2 != DoubleCheck.UNINITIALIZED && instance2 != value) {
                        throw new IllegalStateException("Scoped provider was invoked recursively returning different results: " + instance2 + " & " + value);
                    }
                }
                this.instance = instance;
                this.provider = null;
            }
        }
        // monitorexit(this)
        return (T)instance;
    }
}
