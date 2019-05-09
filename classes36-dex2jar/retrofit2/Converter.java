// 
// Decompiled by Procyon v0.5.34
// 

package retrofit2;

import okhttp3.ResponseBody;
import okhttp3.RequestBody;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.io.IOException;

public interface Converter<F, T>
{
    T convert(final F p0) throws IOException;
    
    public abstract static class Factory
    {
        public Converter<?, RequestBody> requestBodyConverter(final Type type, final Annotation[] array, final Annotation[] array2, final Retrofit retrofit) {
            return null;
        }
        
        public Converter<ResponseBody, ?> responseBodyConverter(final Type type, final Annotation[] array, final Retrofit retrofit) {
            return null;
        }
        
        public Converter<?, String> stringConverter(final Type type, final Annotation[] array, final Retrofit retrofit) {
            return null;
        }
    }
}
