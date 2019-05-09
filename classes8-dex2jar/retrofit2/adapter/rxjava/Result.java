// 
// Decompiled by Procyon v0.5.34
// 

package retrofit2.adapter.rxjava;

import retrofit2.Response;

public final class Result<T>
{
    private final Throwable error;
    private final Response<T> response;
    
    private Result(final Response<T> response, final Throwable error) {
        this.response = response;
        this.error = error;
    }
    
    public static <T> Result<T> error(final Throwable t) {
        if (t == null) {
            throw new NullPointerException("error == null");
        }
        return new Result<T>(null, t);
    }
    
    public static <T> Result<T> response(final Response<T> response) {
        if (response == null) {
            throw new NullPointerException("response == null");
        }
        return new Result<T>(response, null);
    }
    
    public Throwable error() {
        return this.error;
    }
    
    public boolean isError() {
        return this.error != null;
    }
    
    public Response<T> response() {
        return this.response;
    }
    
    @Override
    public String toString() {
        if (this.error != null) {
            return "Result{isError=true, error=\"" + this.error + "\"}";
        }
        return "Result{isError=false, response=" + this.response + '}';
    }
}
