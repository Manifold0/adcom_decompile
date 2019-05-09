// 
// Decompiled by Procyon v0.5.34
// 

package dagger.internal;

public final class InstanceFactory<T> implements Factory<T>
{
    private final T instance;
    
    private InstanceFactory(final T instance) {
        this.instance = instance;
    }
    
    public static <T> Factory<T> create(final T t) {
        if (t == null) {
            throw new NullPointerException();
        }
        return new InstanceFactory<T>(t);
    }
    
    public T get() {
        return this.instance;
    }
}
