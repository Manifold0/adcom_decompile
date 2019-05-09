// 
// Decompiled by Procyon v0.5.34
// 

package retrofit2;

import okhttp3.Headers;
import okhttp3.Request$Builder;
import okhttp3.Protocol;
import okhttp3.Response$Builder;
import okhttp3.ResponseBody;

public final class Response<T>
{
    private final T body;
    private final ResponseBody errorBody;
    private final okhttp3.Response rawResponse;
    
    private Response(final okhttp3.Response rawResponse, final T body, final ResponseBody errorBody) {
        this.rawResponse = rawResponse;
        this.body = body;
        this.errorBody = errorBody;
    }
    
    public static <T> Response<T> error(final int n, final ResponseBody responseBody) {
        if (n < 400) {
            throw new IllegalArgumentException("code < 400: " + n);
        }
        return error(responseBody, new Response$Builder().code(n).protocol(Protocol.HTTP_1_1).request(new Request$Builder().url("http://localhost/").build()).build());
    }
    
    public static <T> Response<T> error(final ResponseBody responseBody, final okhttp3.Response response) {
        if (responseBody == null) {
            throw new NullPointerException("body == null");
        }
        if (response == null) {
            throw new NullPointerException("rawResponse == null");
        }
        if (response.isSuccessful()) {
            throw new IllegalArgumentException("rawResponse should not be successful response");
        }
        return new Response<T>(response, null, responseBody);
    }
    
    public static <T> Response<T> success(final T t) {
        return success(t, new Response$Builder().code(200).message("OK").protocol(Protocol.HTTP_1_1).request(new Request$Builder().url("http://localhost/").build()).build());
    }
    
    public static <T> Response<T> success(final T t, final Headers headers) {
        if (headers == null) {
            throw new NullPointerException("headers == null");
        }
        return success(t, new Response$Builder().code(200).message("OK").protocol(Protocol.HTTP_1_1).headers(headers).request(new Request$Builder().url("http://localhost/").build()).build());
    }
    
    public static <T> Response<T> success(final T t, final okhttp3.Response response) {
        if (response == null) {
            throw new NullPointerException("rawResponse == null");
        }
        if (!response.isSuccessful()) {
            throw new IllegalArgumentException("rawResponse must be successful response");
        }
        return new Response<T>(response, t, null);
    }
    
    public T body() {
        return this.body;
    }
    
    public int code() {
        return this.rawResponse.code();
    }
    
    public ResponseBody errorBody() {
        return this.errorBody;
    }
    
    public Headers headers() {
        return this.rawResponse.headers();
    }
    
    public boolean isSuccessful() {
        return this.rawResponse.isSuccessful();
    }
    
    public String message() {
        return this.rawResponse.message();
    }
    
    public okhttp3.Response raw() {
        return this.rawResponse;
    }
    
    @Override
    public String toString() {
        return this.rawResponse.toString();
    }
}
