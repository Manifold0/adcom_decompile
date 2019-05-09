// 
// Decompiled by Procyon v0.5.34
// 

package okhttp3.internal.http;

import java.net.HttpRetryException;
import java.io.Closeable;
import okhttp3.internal.Util;
import okhttp3.internal.http2.ConnectionShutdownException;
import okhttp3.internal.connection.RouteException;
import okhttp3.ResponseBody;
import okhttp3.Connection;
import javax.net.ssl.SSLPeerUnverifiedException;
import java.security.cert.CertificateException;
import javax.net.ssl.SSLHandshakeException;
import java.net.SocketTimeoutException;
import java.io.InterruptedIOException;
import java.io.IOException;
import okhttp3.Route;
import okhttp3.internal.connection.RealConnection;
import okhttp3.RequestBody;
import java.net.ProtocolException;
import java.net.Proxy;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.CertificatePinner;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.Address;
import okhttp3.HttpUrl;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.OkHttpClient;
import okhttp3.Interceptor;

public final class RetryAndFollowUpInterceptor implements Interceptor
{
    private static final int MAX_FOLLOW_UPS = 20;
    private Object callStackTrace;
    private volatile boolean canceled;
    private final OkHttpClient client;
    private final boolean forWebSocket;
    private StreamAllocation streamAllocation;
    
    public RetryAndFollowUpInterceptor(final OkHttpClient client, final boolean forWebSocket) {
        this.client = client;
        this.forWebSocket = forWebSocket;
    }
    
    private Address createAddress(final HttpUrl httpUrl) {
        SSLSocketFactory sslSocketFactory = null;
        HostnameVerifier hostnameVerifier = null;
        CertificatePinner certificatePinner = null;
        if (httpUrl.isHttps()) {
            sslSocketFactory = this.client.sslSocketFactory();
            hostnameVerifier = this.client.hostnameVerifier();
            certificatePinner = this.client.certificatePinner();
        }
        return new Address(httpUrl.host(), httpUrl.port(), this.client.dns(), this.client.socketFactory(), sslSocketFactory, hostnameVerifier, certificatePinner, this.client.proxyAuthenticator(), this.client.proxy(), this.client.protocols(), this.client.connectionSpecs(), this.client.proxySelector());
    }
    
    private Request followUpRequest(final Response response) throws IOException {
        if (response == null) {
            throw new IllegalStateException();
        }
        final RealConnection connection = this.streamAllocation.connection();
        Route route;
        if (connection != null) {
            route = connection.route();
        }
        else {
            route = null;
        }
        final int code = response.code();
        final String method = response.request().method();
        Label_0237: {
            switch (code) {
                case 407: {
                    Proxy proxy;
                    if (route != null) {
                        proxy = route.proxy();
                    }
                    else {
                        proxy = this.client.proxy();
                    }
                    if (proxy.type() != Proxy.Type.HTTP) {
                        throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
                    }
                    return this.client.proxyAuthenticator().authenticate(route, response);
                }
                case 401: {
                    return this.client.authenticator().authenticate(route, response);
                }
                case 307:
                case 308: {
                    if (method.equals("GET") || method.equals("HEAD")) {
                        break Label_0237;
                    }
                    break;
                }
                case 300:
                case 301:
                case 302:
                case 303: {
                    if (!this.client.followRedirects()) {
                        break;
                    }
                    final String header = response.header("Location");
                    if (header == null) {
                        break;
                    }
                    final HttpUrl resolve = response.request().url().resolve(header);
                    if (resolve != null && (resolve.scheme().equals(response.request().url().scheme()) || this.client.followSslRedirects())) {
                        final Request.Builder builder = response.request().newBuilder();
                        if (HttpMethod.permitsRequestBody(method)) {
                            final boolean redirectsWithBody = HttpMethod.redirectsWithBody(method);
                            if (HttpMethod.redirectsToGet(method)) {
                                builder.method("GET", null);
                            }
                            else {
                                RequestBody body;
                                if (redirectsWithBody) {
                                    body = response.request().body();
                                }
                                else {
                                    body = null;
                                }
                                builder.method(method, body);
                            }
                            if (!redirectsWithBody) {
                                builder.removeHeader("Transfer-Encoding");
                                builder.removeHeader("Content-Length");
                                builder.removeHeader("Content-Type");
                            }
                        }
                        if (!this.sameConnection(response, resolve)) {
                            builder.removeHeader("Authorization");
                        }
                        return builder.url(resolve).build();
                    }
                    break;
                }
                case 408: {
                    if (!(response.request().body() instanceof UnrepeatableRequestBody)) {
                        return response.request();
                    }
                    break;
                }
            }
        }
        return null;
    }
    
    private boolean isRecoverable(final IOException ex, final boolean b) {
        final boolean b2 = true;
        if (!(ex instanceof ProtocolException)) {
            if (ex instanceof InterruptedIOException) {
                return ex instanceof SocketTimeoutException && !b && b2;
            }
            if ((!(ex instanceof SSLHandshakeException) || !(ex.getCause() instanceof CertificateException)) && !(ex instanceof SSLPeerUnverifiedException)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean recover(final IOException ex, final boolean b, final Request request) {
        this.streamAllocation.streamFailed(ex);
        return this.client.retryOnConnectionFailure() && (!b || !(request.body() instanceof UnrepeatableRequestBody)) && this.isRecoverable(ex, b) && this.streamAllocation.hasMoreRoutes();
    }
    
    private boolean sameConnection(final Response response, final HttpUrl httpUrl) {
        final HttpUrl url = response.request().url();
        return url.host().equals(httpUrl.host()) && url.port() == httpUrl.port() && url.scheme().equals(httpUrl.scheme());
    }
    
    public void cancel() {
        this.canceled = true;
        final StreamAllocation streamAllocation = this.streamAllocation;
        if (streamAllocation != null) {
            streamAllocation.cancel();
        }
    }
    
    @Override
    public Response intercept(final Chain chain) throws IOException {
        Object o = chain.request();
        this.streamAllocation = new StreamAllocation(this.client.connectionPool(), this.createAddress(((Request)o).url()), this.callStackTrace);
        int n = 0;
        Response response = null;
        while (!this.canceled) {
            Object o2 = null;
            try {
                o2 = ((RealInterceptorChain)chain).proceed((Request)o, this.streamAllocation, null, null);
                if (false) {
                    this.streamAllocation.streamFailed(null);
                    this.streamAllocation.release();
                }
                o = o2;
                if (response != null) {
                    o = ((Response)o2).newBuilder().priorResponse(response.newBuilder().body(null).build()).build();
                }
                o2 = this.followUpRequest((Response)o);
                if (o2 == null) {
                    if (!this.forWebSocket) {
                        this.streamAllocation.release();
                    }
                    return (Response)o;
                }
            }
            catch (RouteException o2) {
                if (!this.recover(((RouteException)o2).getLastConnectException(), false, (Request)o)) {
                    throw ((RouteException)o2).getLastConnectException();
                }
                goto Label_0214;
            }
            catch (IOException o2) {
                if (!this.recover((IOException)o2, !(o2 instanceof ConnectionShutdownException), (Request)o)) {
                    throw o2;
                }
                if (false) {
                    this.streamAllocation.streamFailed(null);
                    this.streamAllocation.release();
                    continue;
                }
                continue;
            }
            finally {
                if (true) {
                    this.streamAllocation.streamFailed(null);
                    this.streamAllocation.release();
                }
            }
            Util.closeQuietly(((Response)o).body());
            ++n;
            if (n > 20) {
                this.streamAllocation.release();
                throw new ProtocolException("Too many follow-up requests: " + n);
            }
            if (((Request)o2).body() instanceof UnrepeatableRequestBody) {
                this.streamAllocation.release();
                throw new HttpRetryException("Cannot retry streamed HTTP body", ((Response)o).code());
            }
            if (!this.sameConnection((Response)o, ((Request)o2).url())) {
                this.streamAllocation.release();
                this.streamAllocation = new StreamAllocation(this.client.connectionPool(), this.createAddress(((Request)o2).url()), this.callStackTrace);
            }
            else if (this.streamAllocation.codec() != null) {
                throw new IllegalStateException("Closing the body of " + o + " didn't close its backing stream. Bad interceptor?");
            }
            response = (Response)o;
            o = o2;
        }
        this.streamAllocation.release();
        throw new IOException("Canceled");
    }
    
    public boolean isCanceled() {
        return this.canceled;
    }
    
    public void setCallStackTrace(final Object callStackTrace) {
        this.callStackTrace = callStackTrace;
    }
    
    public StreamAllocation streamAllocation() {
        return this.streamAllocation;
    }
}
