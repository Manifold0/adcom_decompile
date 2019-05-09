// 
// Decompiled by Procyon v0.5.34
// 

package okhttp3;

import java.io.IOException;
import okio.BufferedSource;
import okio.Buffer;
import okhttp3.internal.http.HttpHeaders;
import java.util.Collections;
import java.util.List;
import java.io.Closeable;

public final class Response implements Closeable
{
    final ResponseBody body;
    private volatile CacheControl cacheControl;
    final Response cacheResponse;
    final int code;
    final Handshake handshake;
    final Headers headers;
    final String message;
    final Response networkResponse;
    final Response priorResponse;
    final Protocol protocol;
    final long receivedResponseAtMillis;
    final Request request;
    final long sentRequestAtMillis;
    
    Response(final Builder builder) {
        this.request = builder.request;
        this.protocol = builder.protocol;
        this.code = builder.code;
        this.message = builder.message;
        this.handshake = builder.handshake;
        this.headers = builder.headers.build();
        this.body = builder.body;
        this.networkResponse = builder.networkResponse;
        this.cacheResponse = builder.cacheResponse;
        this.priorResponse = builder.priorResponse;
        this.sentRequestAtMillis = builder.sentRequestAtMillis;
        this.receivedResponseAtMillis = builder.receivedResponseAtMillis;
    }
    
    public ResponseBody body() {
        return this.body;
    }
    
    public CacheControl cacheControl() {
        final CacheControl cacheControl = this.cacheControl;
        if (cacheControl != null) {
            return cacheControl;
        }
        return this.cacheControl = CacheControl.parse(this.headers);
    }
    
    public Response cacheResponse() {
        return this.cacheResponse;
    }
    
    public List<Challenge> challenges() {
        String s;
        if (this.code == 401) {
            s = "WWW-Authenticate";
        }
        else {
            if (this.code != 407) {
                return Collections.emptyList();
            }
            s = "Proxy-Authenticate";
        }
        return HttpHeaders.parseChallenges(this.headers(), s);
    }
    
    @Override
    public void close() {
        this.body.close();
    }
    
    public int code() {
        return this.code;
    }
    
    public Handshake handshake() {
        return this.handshake;
    }
    
    public String header(final String s) {
        return this.header(s, null);
    }
    
    public String header(String value, final String s) {
        value = this.headers.get(value);
        if (value != null) {
            return value;
        }
        return s;
    }
    
    public List<String> headers(final String s) {
        return this.headers.values(s);
    }
    
    public Headers headers() {
        return this.headers;
    }
    
    public boolean isRedirect() {
        switch (this.code) {
            default: {
                return false;
            }
            case 300:
            case 301:
            case 302:
            case 303:
            case 307:
            case 308: {
                return true;
            }
        }
    }
    
    public boolean isSuccessful() {
        return this.code >= 200 && this.code < 300;
    }
    
    public String message() {
        return this.message;
    }
    
    public Response networkResponse() {
        return this.networkResponse;
    }
    
    public Builder newBuilder() {
        return new Builder(this);
    }
    
    public ResponseBody peekBody(final long n) throws IOException {
        final BufferedSource source = this.body.source();
        source.request(n);
        Object clone = source.buffer().clone();
        if (((Buffer)clone).size() > n) {
            final Buffer buffer = new Buffer();
            buffer.write((Buffer)clone, n);
            ((Buffer)clone).clear();
            clone = buffer;
        }
        return ResponseBody.create(this.body.contentType(), ((Buffer)clone).size(), (BufferedSource)clone);
    }
    
    public Response priorResponse() {
        return this.priorResponse;
    }
    
    public Protocol protocol() {
        return this.protocol;
    }
    
    public long receivedResponseAtMillis() {
        return this.receivedResponseAtMillis;
    }
    
    public Request request() {
        return this.request;
    }
    
    public long sentRequestAtMillis() {
        return this.sentRequestAtMillis;
    }
    
    @Override
    public String toString() {
        return "Response{protocol=" + this.protocol + ", code=" + this.code + ", message=" + this.message + ", url=" + this.request.url() + '}';
    }
    
    public static class Builder
    {
        ResponseBody body;
        Response cacheResponse;
        int code;
        Handshake handshake;
        Headers.Builder headers;
        String message;
        Response networkResponse;
        Response priorResponse;
        Protocol protocol;
        long receivedResponseAtMillis;
        Request request;
        long sentRequestAtMillis;
        
        public Builder() {
            this.code = -1;
            this.headers = new Headers.Builder();
        }
        
        Builder(final Response response) {
            this.code = -1;
            this.request = response.request;
            this.protocol = response.protocol;
            this.code = response.code;
            this.message = response.message;
            this.handshake = response.handshake;
            this.headers = response.headers.newBuilder();
            this.body = response.body;
            this.networkResponse = response.networkResponse;
            this.cacheResponse = response.cacheResponse;
            this.priorResponse = response.priorResponse;
            this.sentRequestAtMillis = response.sentRequestAtMillis;
            this.receivedResponseAtMillis = response.receivedResponseAtMillis;
        }
        
        private void checkPriorResponse(final Response response) {
            if (response.body != null) {
                throw new IllegalArgumentException("priorResponse.body != null");
            }
        }
        
        private void checkSupportResponse(final String s, final Response response) {
            if (response.body != null) {
                throw new IllegalArgumentException(s + ".body != null");
            }
            if (response.networkResponse != null) {
                throw new IllegalArgumentException(s + ".networkResponse != null");
            }
            if (response.cacheResponse != null) {
                throw new IllegalArgumentException(s + ".cacheResponse != null");
            }
            if (response.priorResponse != null) {
                throw new IllegalArgumentException(s + ".priorResponse != null");
            }
        }
        
        public Builder addHeader(final String s, final String s2) {
            this.headers.add(s, s2);
            return this;
        }
        
        public Builder body(final ResponseBody body) {
            this.body = body;
            return this;
        }
        
        public Response build() {
            if (this.request == null) {
                throw new IllegalStateException("request == null");
            }
            if (this.protocol == null) {
                throw new IllegalStateException("protocol == null");
            }
            if (this.code < 0) {
                throw new IllegalStateException("code < 0: " + this.code);
            }
            return new Response(this);
        }
        
        public Builder cacheResponse(final Response cacheResponse) {
            if (cacheResponse != null) {
                this.checkSupportResponse("cacheResponse", cacheResponse);
            }
            this.cacheResponse = cacheResponse;
            return this;
        }
        
        public Builder code(final int code) {
            this.code = code;
            return this;
        }
        
        public Builder handshake(final Handshake handshake) {
            this.handshake = handshake;
            return this;
        }
        
        public Builder header(final String s, final String s2) {
            this.headers.set(s, s2);
            return this;
        }
        
        public Builder headers(final Headers headers) {
            this.headers = headers.newBuilder();
            return this;
        }
        
        public Builder message(final String message) {
            this.message = message;
            return this;
        }
        
        public Builder networkResponse(final Response networkResponse) {
            if (networkResponse != null) {
                this.checkSupportResponse("networkResponse", networkResponse);
            }
            this.networkResponse = networkResponse;
            return this;
        }
        
        public Builder priorResponse(final Response priorResponse) {
            if (priorResponse != null) {
                this.checkPriorResponse(priorResponse);
            }
            this.priorResponse = priorResponse;
            return this;
        }
        
        public Builder protocol(final Protocol protocol) {
            this.protocol = protocol;
            return this;
        }
        
        public Builder receivedResponseAtMillis(final long receivedResponseAtMillis) {
            this.receivedResponseAtMillis = receivedResponseAtMillis;
            return this;
        }
        
        public Builder removeHeader(final String s) {
            this.headers.removeAll(s);
            return this;
        }
        
        public Builder request(final Request request) {
            this.request = request;
            return this;
        }
        
        public Builder sentRequestAtMillis(final long sentRequestAtMillis) {
            this.sentRequestAtMillis = sentRequestAtMillis;
            return this;
        }
    }
}
