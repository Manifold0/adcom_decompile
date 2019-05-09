// 
// Decompiled by Procyon v0.5.34
// 

package okhttp3.internal.http;

import java.io.IOException;
import okio.BufferedSink;
import okhttp3.Request;
import okhttp3.internal.connection.StreamAllocation;
import java.net.ProtocolException;
import okhttp3.internal.Util;
import okio.Okio;
import okhttp3.Response;
import okhttp3.Interceptor;

public final class CallServerInterceptor implements Interceptor
{
    private final boolean forWebSocket;
    
    public CallServerInterceptor(final boolean forWebSocket) {
        this.forWebSocket = forWebSocket;
    }
    
    @Override
    public Response intercept(final Chain chain) throws IOException {
        final HttpCodec httpStream = ((RealInterceptorChain)chain).httpStream();
        final StreamAllocation streamAllocation = ((RealInterceptorChain)chain).streamAllocation();
        final Request request = chain.request();
        final long currentTimeMillis = System.currentTimeMillis();
        httpStream.writeRequestHeaders(request);
        final Response.Builder builder = null;
        Response.Builder responseHeaders = null;
        Response.Builder builder2 = builder;
        if (HttpMethod.permitsRequestBody(request.method())) {
            builder2 = builder;
            if (request.body() != null) {
                if ("100-continue".equalsIgnoreCase(request.header("Expect"))) {
                    httpStream.flushRequest();
                    responseHeaders = httpStream.readResponseHeaders(true);
                }
                if ((builder2 = responseHeaders) == null) {
                    final BufferedSink buffer = Okio.buffer(httpStream.createRequestBody(request, request.body().contentLength()));
                    request.body().writeTo(buffer);
                    buffer.close();
                    builder2 = responseHeaders;
                }
            }
        }
        httpStream.finishRequest();
        Object responseHeaders2;
        if ((responseHeaders2 = builder2) == null) {
            responseHeaders2 = httpStream.readResponseHeaders(false);
        }
        final Response build = ((Response.Builder)responseHeaders2).request(request).handshake(streamAllocation.connection().handshake()).sentRequestAtMillis(currentTimeMillis).receivedResponseAtMillis(System.currentTimeMillis()).build();
        final int code = build.code();
        Response response;
        if (this.forWebSocket && code == 101) {
            response = build.newBuilder().body(Util.EMPTY_RESPONSE).build();
        }
        else {
            response = build.newBuilder().body(httpStream.openResponseBody(build)).build();
        }
        if ("close".equalsIgnoreCase(response.request().header("Connection")) || "close".equalsIgnoreCase(response.header("Connection"))) {
            streamAllocation.noNewStreams();
        }
        if ((code == 204 || code == 205) && response.body().contentLength() > 0L) {
            throw new ProtocolException("HTTP " + code + " had non-zero Content-Length: " + response.body().contentLength());
        }
        return response;
    }
}
