// 
// Decompiled by Procyon v0.5.34
// 

package dagger.internal;

import javax.inject.Provider;

public final class DelegateFactory<T> implements Factory<T>
{
    private Provider<T> delegate;
    
    public T get() {
        if (this.delegate == null) {
            throw new IllegalStateException();
        }
        return (T)this.delegate.get();
    }
    
    public void setDelegatedProvider(final Provider<T> delegate) {
        if (delegate == null) {
            throw new IllegalArgumentException();
        }
        if (this.delegate != null) {
            throw new IllegalStateException();
        }
        this.delegate = delegate;
    }
}
