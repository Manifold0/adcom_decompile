// 
// Decompiled by Procyon v0.5.34
// 

package okhttp3.internal.http1;

import okhttp3.internal.Util;
import java.util.concurrent.TimeUnit;
import java.net.ProtocolException;
import okio.Buffer;
import okhttp3.internal.http.RequestLine;
import java.io.EOFException;
import okhttp3.internal.http.StatusLine;
import okhttp3.internal.Internal;
import okhttp3.Headers;
import okhttp3.internal.http.RealResponseBody;
import okio.Okio;
import okhttp3.ResponseBody;
import okhttp3.HttpUrl;
import okio.Timeout;
import okio.ForwardingTimeout;
import okio.Sink;
import okhttp3.Request;
import okhttp3.internal.connection.RealConnection;
import java.io.IOException;
import okhttp3.internal.http.HttpHeaders;
import okio.Source;
import okhttp3.Response;
import okhttp3.internal.connection.StreamAllocation;
import okio.BufferedSource;
import okio.BufferedSink;
import okhttp3.OkHttpClient;
import okhttp3.internal.http.HttpCodec;

public final class Http1Codec implements HttpCodec
{
    private static final int STATE_CLOSED = 6;
    private static final int STATE_IDLE = 0;
    private static final int STATE_OPEN_REQUEST_BODY = 1;
    private static final int STATE_OPEN_RESPONSE_BODY = 4;
    private static final int STATE_READING_RESPONSE_BODY = 5;
    private static final int STATE_READ_RESPONSE_HEADERS = 3;
    private static final int STATE_WRITING_REQUEST_BODY = 2;
    final OkHttpClient client;
    final BufferedSink sink;
    final BufferedSource source;
    int state;
    final StreamAllocation streamAllocation;
    
    public Http1Codec(final OkHttpClient client, final StreamAllocation streamAllocation, final BufferedSource source, final BufferedSink sink) {
        this.state = 0;
        this.client = client;
        this.streamAllocation = streamAllocation;
        this.source = source;
        this.sink = sink;
    }
    
    private Source getTransferStream(final Response response) throws IOException {
        if (!HttpHeaders.hasBody(response)) {
            return this.newFixedLengthSource(0L);
        }
        if ("chunked".equalsIgnoreCase(response.header("Transfer-Encoding"))) {
            return this.newChunkedSource(response.request().url());
        }
        final long contentLength = HttpHeaders.contentLength(response);
        if (contentLength != -1L) {
            return this.newFixedLengthSource(contentLength);
        }
        return this.newUnknownLengthSource();
    }
    
    @Override
    public void cancel() {
        final RealConnection connection = this.streamAllocation.connection();
        if (connection != null) {
            connection.cancel();
        }
    }
    
    @Override
    public Sink createRequestBody(final Request request, final long n) {
        if ("chunked".equalsIgnoreCase(request.header("Transfer-Encoding"))) {
            return this.newChunkedSink();
        }
        if (n != -1L) {
            return this.newFixedLengthSink(n);
        }
        throw new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
    }
    
    void detachTimeout(final ForwardingTimeout forwardingTimeout) {
        final Timeout delegate = forwardingTimeout.delegate();
        forwardingTimeout.setDelegate(Timeout.NONE);
        delegate.clearDeadline();
        delegate.clearTimeout();
    }
    
    @Override
    public void finishRequest() throws IOException {
        this.sink.flush();
    }
    
    @Override
    public void flushRequest() throws IOException {
        this.sink.flush();
    }
    
    public boolean isClosed() {
        return this.state == 6;
    }
    
    public Sink newChunkedSink() {
        if (this.state != 1) {
            throw new IllegalStateException("state: " + this.state);
        }
        this.state = 2;
        return (Sink)new ChunkedSink();
    }
    
    public Source newChunkedSource(final HttpUrl httpUrl) throws IOException {
        if (this.state != 4) {
            throw new IllegalStateException("state: " + this.state);
        }
        this.state = 5;
        return (Source)new ChunkedSource(httpUrl);
    }
    
    public Sink newFixedLengthSink(final long n) {
        if (this.state != 1) {
            throw new IllegalStateException("state: " + this.state);
        }
        this.state = 2;
        return (Sink)new FixedLengthSink(n);
    }
    
    public Source newFixedLengthSource(final long n) throws IOException {
        if (this.state != 4) {
            throw new IllegalStateException("state: " + this.state);
        }
        this.state = 5;
        return (Source)new FixedLengthSource(n);
    }
    
    public Source newUnknownLengthSource() throws IOException {
        if (this.state != 4) {
            throw new IllegalStateException("state: " + this.state);
        }
        if (this.streamAllocation == null) {
            throw new IllegalStateException("streamAllocation == null");
        }
        this.state = 5;
        this.streamAllocation.noNewStreams();
        return (Source)new UnknownLengthSource();
    }
    
    @Override
    public ResponseBody openResponseBody(final Response response) throws IOException {
        return new RealResponseBody(response.headers(), Okio.buffer(this.getTransferStream(response)));
    }
    
    public Headers readHeaders() throws IOException {
        final Headers.Builder builder = new Headers.Builder();
        while (true) {
            final String utf8LineStrict = this.source.readUtf8LineStrict();
            if (utf8LineStrict.length() == 0) {
                break;
            }
            Internal.instance.addLenient(builder, utf8LineStrict);
        }
        return builder.build();
    }
    
    @Override
    public Response.Builder readResponseHeaders(final boolean b) throws IOException {
        if (this.state != 1 && this.state != 3) {
            throw new IllegalStateException("state: " + this.state);
        }
        try {
            final StatusLine parse = StatusLine.parse(this.source.readUtf8LineStrict());
            final Response.Builder headers = new Response.Builder().protocol(parse.protocol).code(parse.code).message(parse.message).headers(this.readHeaders());
            if (b && parse.code == 100) {
                return null;
            }
            this.state = 4;
            return headers;
        }
        catch (EOFException ex2) {
            final IOException ex = new IOException("unexpected end of stream on " + this.streamAllocation);
            ex.initCause(ex2);
            throw ex;
        }
    }
    
    public void writeRequest(final Headers headers, final String s) throws IOException {
        if (this.state != 0) {
            throw new IllegalStateException("state: " + this.state);
        }
        this.sink.writeUtf8(s).writeUtf8("\r\n");
        for (int i = 0; i < headers.size(); ++i) {
            this.sink.writeUtf8(headers.name(i)).writeUtf8(": ").writeUtf8(headers.value(i)).writeUtf8("\r\n");
        }
        this.sink.writeUtf8("\r\n");
        this.state = 1;
    }
    
    @Override
    public void writeRequestHeaders(final Request request) throws IOException {
        this.writeRequest(request.headers(), RequestLine.get(request, this.streamAllocation.connection().route().proxy().type()));
    }
    
    private abstract class AbstractSource implements Source
    {
        protected boolean closed;
        protected final ForwardingTimeout timeout;
        
        private AbstractSource() {
            this.timeout = new ForwardingTimeout(Http1Codec.this.source.timeout());
        }
        
        protected final void endOfInput(final boolean b) throws IOException {
            if (Http1Codec.this.state != 6) {
                if (Http1Codec.this.state != 5) {
                    throw new IllegalStateException("state: " + Http1Codec.this.state);
                }
                Http1Codec.this.detachTimeout(this.timeout);
                Http1Codec.this.state = 6;
                if (Http1Codec.this.streamAllocation != null) {
                    Http1Codec.this.streamAllocation.streamFinished(!b, Http1Codec.this);
                }
            }
        }
        
        public Timeout timeout() {
            return (Timeout)this.timeout;
        }
    }
    
    private final class ChunkedSink implements Sink
    {
        private boolean closed;
        private final ForwardingTimeout timeout;
        
        ChunkedSink() {
            this.timeout = new ForwardingTimeout(Http1Codec.this.sink.timeout());
        }
        
        public void close() throws IOException {
            synchronized (this) {
                if (!this.closed) {
                    this.closed = true;
                    Http1Codec.this.sink.writeUtf8("0\r\n\r\n");
                    Http1Codec.this.detachTimeout(this.timeout);
                    Http1Codec.this.state = 3;
                }
            }
        }
        
        public void flush() throws IOException {
            synchronized (this) {
                if (!this.closed) {
                    Http1Codec.this.sink.flush();
                }
            }
        }
        
        public Timeout timeout() {
            return (Timeout)this.timeout;
        }
        
        public void write(final Buffer buffer, final long n) throws IOException {
            if (this.closed) {
                throw new IllegalStateException("closed");
            }
            if (n == 0L) {
                return;
            }
            Http1Codec.this.sink.writeHexadecimalUnsignedLong(n);
            Http1Codec.this.sink.writeUtf8("\r\n");
            Http1Codec.this.sink.write(buffer, n);
            Http1Codec.this.sink.writeUtf8("\r\n");
        }
    }
    
    private class ChunkedSource extends AbstractSource
    {
        private static final long NO_CHUNK_YET = -1L;
        private long bytesRemainingInChunk;
        private boolean hasMoreChunks;
        private final HttpUrl url;
        
        ChunkedSource(final HttpUrl url) {
            this.bytesRemainingInChunk = -1L;
            this.hasMoreChunks = true;
            this.url = url;
        }
        
        private void readChunkSize() throws IOException {
            if (this.bytesRemainingInChunk != -1L) {
                Http1Codec.this.source.readUtf8LineStrict();
            }
            try {
                this.bytesRemainingInChunk = Http1Codec.this.source.readHexadecimalUnsignedLong();
                final String trim = Http1Codec.this.source.readUtf8LineStrict().trim();
                if (this.bytesRemainingInChunk < 0L || (!trim.isEmpty() && !trim.startsWith(";"))) {
                    throw new ProtocolException("expected chunk size and optional extensions but was \"" + this.bytesRemainingInChunk + trim + "\"");
                }
            }
            catch (NumberFormatException ex) {
                throw new ProtocolException(ex.getMessage());
            }
            if (this.bytesRemainingInChunk == 0L) {
                this.hasMoreChunks = false;
                HttpHeaders.receiveHeaders(Http1Codec.this.client.cookieJar(), this.url, Http1Codec.this.readHeaders());
                ((AbstractSource)this).endOfInput(true);
            }
        }
        
        public void close() throws IOException {
            if (this.closed) {
                return;
            }
            if (this.hasMoreChunks && !Util.discard((Source)this, 100, TimeUnit.MILLISECONDS)) {
                ((AbstractSource)this).endOfInput(false);
            }
            this.closed = true;
        }
        
        public long read(final Buffer buffer, long read) throws IOException {
            if (read < 0L) {
                throw new IllegalArgumentException("byteCount < 0: " + read);
            }
            if (this.closed) {
                throw new IllegalStateException("closed");
            }
            if (!this.hasMoreChunks) {
                return -1L;
            }
            if (this.bytesRemainingInChunk == 0L || this.bytesRemainingInChunk == -1L) {
                this.readChunkSize();
                if (!this.hasMoreChunks) {
                    return -1L;
                }
            }
            read = Http1Codec.this.source.read(buffer, Math.min(read, this.bytesRemainingInChunk));
            if (read == -1L) {
                ((AbstractSource)this).endOfInput(false);
                throw new ProtocolException("unexpected end of stream");
            }
            this.bytesRemainingInChunk -= read;
            return read;
        }
    }
    
    private final class FixedLengthSink implements Sink
    {
        private long bytesRemaining;
        private boolean closed;
        private final ForwardingTimeout timeout;
        
        FixedLengthSink(final long bytesRemaining) {
            this.timeout = new ForwardingTimeout(Http1Codec.this.sink.timeout());
            this.bytesRemaining = bytesRemaining;
        }
        
        public void close() throws IOException {
            if (this.closed) {
                return;
            }
            this.closed = true;
            if (this.bytesRemaining > 0L) {
                throw new ProtocolException("unexpected end of stream");
            }
            Http1Codec.this.detachTimeout(this.timeout);
            Http1Codec.this.state = 3;
        }
        
        public void flush() throws IOException {
            if (this.closed) {
                return;
            }
            Http1Codec.this.sink.flush();
        }
        
        public Timeout timeout() {
            return (Timeout)this.timeout;
        }
        
        public void write(final Buffer buffer, final long n) throws IOException {
            if (this.closed) {
                throw new IllegalStateException("closed");
            }
            Util.checkOffsetAndCount(buffer.size(), 0L, n);
            if (n > this.bytesRemaining) {
                throw new ProtocolException("expected " + this.bytesRemaining + " bytes but received " + n);
            }
            Http1Codec.this.sink.write(buffer, n);
            this.bytesRemaining -= n;
        }
    }
    
    private class FixedLengthSource extends AbstractSource
    {
        private long bytesRemaining;
        
        public FixedLengthSource(final long bytesRemaining) throws IOException {
            this.bytesRemaining = bytesRemaining;
            if (this.bytesRemaining == 0L) {
                ((AbstractSource)this).endOfInput(true);
            }
        }
        
        public void close() throws IOException {
            if (this.closed) {
                return;
            }
            if (this.bytesRemaining != 0L && !Util.discard((Source)this, 100, TimeUnit.MILLISECONDS)) {
                ((AbstractSource)this).endOfInput(false);
            }
            this.closed = true;
        }
        
        public long read(final Buffer buffer, long n) throws IOException {
            if (n < 0L) {
                throw new IllegalArgumentException("byteCount < 0: " + n);
            }
            if (this.closed) {
                throw new IllegalStateException("closed");
            }
            if (this.bytesRemaining == 0L) {
                n = -1L;
            }
            else {
                final long read = Http1Codec.this.source.read(buffer, Math.min(this.bytesRemaining, n));
                if (read == -1L) {
                    ((AbstractSource)this).endOfInput(false);
                    throw new ProtocolException("unexpected end of stream");
                }
                this.bytesRemaining -= read;
                n = read;
                if (this.bytesRemaining == 0L) {
                    ((AbstractSource)this).endOfInput(true);
                    return read;
                }
            }
            return n;
        }
    }
    
    private class UnknownLengthSource extends AbstractSource
    {
        private boolean inputExhausted;
        
        UnknownLengthSource() {
        }
        
        public void close() throws IOException {
            if (this.closed) {
                return;
            }
            if (!this.inputExhausted) {
                ((AbstractSource)this).endOfInput(false);
            }
            this.closed = true;
        }
        
        public long read(final Buffer buffer, long read) throws IOException {
            if (read < 0L) {
                throw new IllegalArgumentException("byteCount < 0: " + read);
            }
            if (this.closed) {
                throw new IllegalStateException("closed");
            }
            if (this.inputExhausted) {
                read = -1L;
            }
            else if ((read = Http1Codec.this.source.read(buffer, read)) == -1L) {
                ((AbstractSource)this).endOfInput(this.inputExhausted = true);
                return -1L;
            }
            return read;
        }
    }
}
