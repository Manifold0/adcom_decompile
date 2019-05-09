// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.util;

import rx.functions.Func1;

public final class UtilityFunctions
{
    private UtilityFunctions() {
        throw new IllegalStateException("No instances!");
    }
    
    public static <T> Func1<? super T, Boolean> alwaysFalse() {
        return AlwaysFalse.INSTANCE;
    }
    
    public static <T> Func1<? super T, Boolean> alwaysTrue() {
        return AlwaysTrue.INSTANCE;
    }
    
    public static <T> Func1<T, T> identity() {
        return new Func1<T, T>() {
            @Override
            public T call(final T t) {
                return t;
            }
        };
    }
    
    enum AlwaysFalse implements Func1<Object, Boolean>
    {
        INSTANCE;
        
        @Override
        public Boolean call(final Object o) {
            return false;
        }
    }
    
    enum AlwaysTrue implements Func1<Object, Boolean>
    {
        INSTANCE;
        
        @Override
        public Boolean call(final Object o) {
            return true;
        }
    }
}
