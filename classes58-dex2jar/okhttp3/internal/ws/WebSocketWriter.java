// 
// Decompiled by Procyon v0.5.34
// 

package okhttp3.internal.ws;

import okio.Timeout;
import okio.Sink;
import java.io.IOException;
import okio.ByteString;
import okio.BufferedSink;
import java.util.Random;
import okio.Buffer;

final class WebSocketWriter
{
    boolean activeWriter;
    final Buffer buffer;
    final FrameSink frameSink;
    final boolean isClient;
    final byte[] maskBuffer;
    final byte[] maskKey;
    final Random random;
    final BufferedSink sink;
    boolean writerClosed;
    
    WebSocketWriter(final boolean isClient, final BufferedSink sink, final Random random) {
        final byte[] array = null;
        this.buffer = new Buffer();
        this.frameSink = new FrameSink();
        if (sink == null) {
            throw new NullPointerException("sink == null");
        }
        if (random == null) {
            throw new NullPointerException("random == null");
        }
        this.isClient = isClient;
        this.sink = sink;
        this.random = random;
        byte[] maskKey;
        if (isClient) {
            maskKey = new byte[4];
        }
        else {
            maskKey = null;
        }
        this.maskKey = maskKey;
        byte[] maskBuffer = array;
        if (isClient) {
            maskBuffer = new byte[8192];
        }
        this.maskBuffer = maskBuffer;
    }
    
    private void writeControlFrameSynchronized(final int n, final ByteString byteString) throws IOException {
        assert Thread.holdsLock(this);
        if (this.writerClosed) {
            throw new IOException("closed");
        }
        final int size = byteString.size();
        if (size > 125L) {
            throw new IllegalArgumentException("Payload size must be less than or equal to 125");
        }
        this.sink.writeByte(n | 0x80);
        if (this.isClient) {
            this.sink.writeByte(size | 0x80);
            this.random.nextBytes(this.maskKey);
            this.sink.write(this.maskKey);
            final byte[] byteArray = byteString.toByteArray();
            WebSocketProtocol.toggleMask(byteArray, byteArray.length, this.maskKey, 0L);
            this.sink.write(byteArray);
        }
        else {
            this.sink.writeByte(size);
            this.sink.write(byteString);
        }
        this.sink.flush();
    }
    
    Sink newMessageSink(final int formatOpcode, final long contentLength) {
        if (this.activeWriter) {
            throw new IllegalStateException("Another message writer is active. Did you call close()?");
        }
        this.activeWriter = true;
        this.frameSink.formatOpcode = formatOpcode;
        this.frameSink.contentLength = contentLength;
        this.frameSink.isFirstFrame = true;
        this.frameSink.closed = false;
        return (Sink)this.frameSink;
    }
    
    void writeClose(final int n, final ByteString byteString) throws IOException {
        ByteString byteString2 = ByteString.EMPTY;
        if (n != 0 || byteString != null) {
            if (n != 0) {
                WebSocketProtocol.validateCloseCode(n);
            }
            final Buffer buffer = new Buffer();
            buffer.writeShort(n);
            if (byteString != null) {
                buffer.write(byteString);
            }
            byteString2 = buffer.readByteString();
        }
        // monitorenter(this)
        try {
            this.writeControlFrameSynchronized(8, byteString2);
            return;
        }
        finally {
            this.writerClosed = true;
        }
        try {}
        finally {
        }
        // monitorexit(this)
    }
    
    void writeMessageFrameSynchronized(int read, final long n, final boolean b, final boolean b2) throws IOException {
        assert Thread.holdsLock(this);
        if (this.writerClosed) {
            throw new IOException("closed");
        }
        if (!b) {
            read = 0;
        }
        int n2 = read;
        if (b2) {
            n2 = (read | 0x80);
        }
        this.sink.writeByte(n2);
        read = 0;
        if (this.isClient) {
            read = (0x0 | 0x80);
        }
        if (n <= 125L) {
            this.sink.writeByte(read | (int)n);
        }
        else if (n <= 65535L) {
            this.sink.writeByte(read | 0x7E);
            this.sink.writeShort((int)n);
        }
        else {
            this.sink.writeByte(read | 0x7F);
            this.sink.writeLong(n);
        }
        if (this.isClient) {
            this.random.nextBytes(this.maskKey);
            this.sink.write(this.maskKey);
            for (long n3 = 0L; n3 < n; n3 += read) {
                read = (int)Math.min(n, this.maskBuffer.length);
                read = this.buffer.read(this.maskBuffer, 0, read);
                if (read == -1) {
                    throw new AssertionError();
                }
                WebSocketProtocol.toggleMask(this.maskBuffer, read, this.maskKey, n3);
                this.sink.write(this.maskBuffer, 0, read);
            }
        }
        else {
            this.sink.write(this.buffer, n);
        }
        this.sink.emit();
    }
    
    void writePing(final ByteString byteString) throws IOException {
        synchronized (this) {
            this.writeControlFrameSynchronized(9, byteString);
        }
    }
    
    void writePong(final ByteString byteString) throws IOException {
        synchronized (this) {
            this.writeControlFrameSynchronized(10, byteString);
        }
    }
    
    final class FrameSink implements Sink
    {
        boolean closed;
        long contentLength;
        int formatOpcode;
        boolean isFirstFrame;
        
        public void close() throws IOException {
            if (this.closed) {
                throw new IOException("closed");
            }
            synchronized (WebSocketWriter.this) {
                WebSocketWriter.this.writeMessageFrameSynchronized(this.formatOpcode, WebSocketWriter.this.buffer.size(), this.isFirstFrame, true);
                // monitorexit(this.this$0)
                this.closed = true;
                WebSocketWriter.this.activeWriter = false;
            }
        }
        
        public void flush() throws IOException {
            if (this.closed) {
                throw new IOException("closed");
            }
            synchronized (WebSocketWriter.this) {
                WebSocketWriter.this.writeMessageFrameSynchronized(this.formatOpcode, WebSocketWriter.this.buffer.size(), this.isFirstFrame, false);
                // monitorexit(this.this$0)
                this.isFirstFrame = false;
            }
        }
        
        public Timeout timeout() {
            return WebSocketWriter.this.sink.timeout();
        }
        
        public void write(final Buffer buffer, long completeSegmentByteCount) throws IOException {
            if (this.closed) {
                throw new IOException("closed");
            }
            WebSocketWriter.this.buffer.write(buffer, completeSegmentByteCount);
            Label_0126: {
                if (!this.isFirstFrame || this.contentLength == -1L || WebSocketWriter.this.buffer.size() <= this.contentLength - 8192L) {
                    break Label_0126;
                }
                int n = 1;
                while (true) {
                    completeSegmentByteCount = WebSocketWriter.this.buffer.completeSegmentByteCount();
                    if (completeSegmentByteCount <= 0L || n != 0) {
                        return;
                    }
                    synchronized (WebSocketWriter.this) {
                        WebSocketWriter.this.writeMessageFrameSynchronized(this.formatOpcode, completeSegmentByteCount, this.isFirstFrame, false);
                        // monitorexit(this.this$0)
                        this.isFirstFrame = false;
                        return;
                        n = 0;
                        continue;
                    }
                    break;
                }
            }
        }
    }
}
