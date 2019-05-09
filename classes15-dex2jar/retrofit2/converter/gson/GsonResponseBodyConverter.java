// 
// Decompiled by Procyon v0.5.34
// 

package retrofit2.converter.gson;

import com.google.gson.stream.JsonReader;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import okhttp3.ResponseBody;
import retrofit2.Converter;

final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T>
{
    private final TypeAdapter<T> adapter;
    private final Gson gson;
    
    GsonResponseBodyConverter(final Gson gson, final TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }
    
    public T convert(final ResponseBody responseBody) throws IOException {
        final JsonReader jsonReader = this.gson.newJsonReader(responseBody.charStream());
        try {
            return (T)this.adapter.read(jsonReader);
        }
        finally {
            responseBody.close();
        }
    }
}
