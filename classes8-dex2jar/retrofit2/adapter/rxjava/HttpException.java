// 
// Decompiled by Procyon v0.5.34
// 

package retrofit2.adapter.rxjava;

import retrofit2.Response;

@Deprecated
public final class HttpException extends retrofit2.HttpException
{
    public HttpException(final Response<?> response) {
        super((Response)response);
    }
}
