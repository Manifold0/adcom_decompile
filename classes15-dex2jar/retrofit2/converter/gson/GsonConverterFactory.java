// 
// Decompiled by Procyon v0.5.34
// 

package retrofit2.converter.gson;

import okhttp3.ResponseBody;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import okhttp3.RequestBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import com.google.gson.Gson;
import retrofit2.Converter$Factory;

public final class GsonConverterFactory extends Converter$Factory
{
    private final Gson gson;
    
    private GsonConverterFactory(final Gson gson) {
        if (gson == null) {
            throw new NullPointerException("gson == null");
        }
        this.gson = gson;
    }
    
    public static GsonConverterFactory create() {
        return create(new Gson());
    }
    
    public static GsonConverterFactory create(final Gson gson) {
        return new GsonConverterFactory(gson);
    }
    
    public Converter<?, RequestBody> requestBodyConverter(final Type type, final Annotation[] array, final Annotation[] array2, final Retrofit retrofit) {
        return (Converter<?, RequestBody>)new GsonRequestBodyConverter(this.gson, (com.google.gson.TypeAdapter<Object>)this.gson.getAdapter(TypeToken.get(type)));
    }
    
    public Converter<ResponseBody, ?> responseBodyConverter(final Type type, final Annotation[] array, final Retrofit retrofit) {
        return (Converter<ResponseBody, ?>)new GsonResponseBodyConverter(this.gson, (com.google.gson.TypeAdapter<Object>)this.gson.getAdapter(TypeToken.get(type)));
    }
}
