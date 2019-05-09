// 
// Decompiled by Procyon v0.5.34
// 

package retrofit2;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public interface CallAdapter<R, T>
{
    T adapt(final Call<R> p0);
    
    Type responseType();
    
    public abstract static class Factory
    {
        protected static Type getParameterUpperBound(final int n, final ParameterizedType parameterizedType) {
            return Utils.getParameterUpperBound(n, parameterizedType);
        }
        
        protected static Class<?> getRawType(final Type type) {
            return Utils.getRawType(type);
        }
        
        public abstract CallAdapter<?, ?> get(final Type p0, final Annotation[] p1, final Retrofit p2);
    }
}
