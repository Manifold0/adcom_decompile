// 
// Decompiled by Procyon v0.5.34
// 

package okhttp3.internal.ws;

import okio.ByteString;
import java.util.concurrent.TimeUnit;
import java.io.IOException;
import java.net.ProtocolException;
import java.io.EOFException;
import okio.Buffer;
import okio.BufferedSource;

final class WebSocketReader
{
    boolean closed;
    long frameBytesRead;
    final FrameCallback frameCallback;
    long frameLength;
    final boolean isClient;
    boolean isControlFrame;
    boolean isFinalFrame;
    boolean isMasked;
    final byte[] maskBuffer;
    final byte[] maskKey;
    int opcode;
    final BufferedSource source;
    
    WebSocketReader(final boolean isClient, final BufferedSource source, final FrameCallback frameCallback) {
        this.maskKey = new byte[4];
        this.maskBuffer = new byte[8192];
        if (source == null) {
            throw new NullPointerException("source == null");
        }
        if (frameCallback == null) {
            throw new NullPointerException("frameCallback == null");
        }
        this.isClient = isClient;
        this.source = source;
        this.frameCallback = frameCallback;
    }
    
    private void readControlFrame() throws IOException {
        final Buffer buffer = new Buffer();
        if (this.frameBytesRead < this.frameLength) {
            if (this.isClient) {
                this.source.readFully(buffer, this.frameLength);
            }
            else {
                while (this.frameBytesRead < this.frameLength) {
                    final int read = this.source.read(this.maskBuffer, 0, (int)Math.min(this.frameLength - this.frameBytesRead, this.maskBuffer.length));
                    if (read == -1) {
                        throw new EOFException();
                    }
                    WebSocketProtocol.toggleMask(this.maskBuffer, read, this.maskKey, this.frameBytesRead);
                    buffer.write(this.maskBuffer, 0, read);
                    this.frameBytesRead += read;
                }
            }
        }
        switch (this.opcode) {
            default: {
                throw new ProtocolException("Unknown control opcode: " + Integer.toHexString(this.opcode));
            }
            case 9: {
                this.frameCallback.onReadPing(buffer.readByteString());
            }
            case 10: {
                this.frameCallback.onReadPong(buffer.readByteString());
            }
            case 8: {
                int short1 = 1005;
                String utf8 = "";
                final long size = buffer.size();
                if (size == 1L) {
                    throw new ProtocolException("Malformed close payload length of 1.");
                }
                if (size != 0L) {
                    short1 = buffer.readShort();
                    utf8 = buffer.readUtf8();
                    final String closeCodeExceptionMessage = WebSocketProtocol.closeCodeExceptionMessage(short1);
                    if (closeCodeExceptionMessage != null) {
                        throw new ProtocolException(closeCodeExceptionMessage);
                    }
                }
                this.frameCallback.onReadClose(short1, utf8);
                this.closed = true;
            }
        }
    }
    
    private void readHeader() throws IOException {
        final boolean b = true;
        if (this.closed) {
            throw new IOException("closed");
        }
        int n = 0;
    Label_0116_Outer:
        while (true) {
            final long timeoutNanos = this.source.timeout().timeoutNanos();
            this.source.timeout().clearTimeout();
            while (true) {
            Label_0175:
                while (true) {
                    try {
                        n = (this.source.readByte() & 0xFF);
                        this.source.timeout().timeout(timeoutNanos, TimeUnit.NANOSECONDS);
                        this.opcode = (n & 0xF);
                        if ((n & 0x80) != 0x0) {
                            final boolean isFinalFrame = true;
                            this.isFinalFrame = isFinalFrame;
                            if ((n & 0x8) == 0x0) {
                                break Label_0175;
                            }
                            final boolean isControlFrame = true;
                            this.isControlFrame = isControlFrame;
                            if (this.isControlFrame && !this.isFinalFrame) {
                                throw new ProtocolException("Control frames must be final.");
                            }
                            break;
                        }
                    }
                    finally {
                        this.source.timeout().timeout(timeoutNanos, TimeUnit.NANOSECONDS);
                    }
                    final boolean isFinalFrame = false;
                    continue Label_0116_Outer;
                }
                final boolean isControlFrame = false;
                continue;
            }
        }
        boolean b2;
        if ((n & 0x40) != 0x0) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        boolean b3;
        if ((n & 0x20) != 0x0) {
            b3 = true;
        }
        else {
            b3 = false;
        }
        boolean b4;
        if ((n & 0x10) != 0x0) {
            b4 = true;
        }
        else {
            b4 = false;
        }
        if (b2 || b3 || b4) {
            throw new ProtocolException("Reserved flags are unsupported.");
        }
        final int n2 = this.source.readByte() & 0xFF;
        this.isMasked = ((n2 & 0x80) != 0x0 && b);
        if (this.isMasked == this.isClient) {
            String s;
            if (this.isClient) {
                s = "Server-sent frames must not be masked.";
            }
            else {
                s = "Client-sent frames must be masked.";
            }
            throw new ProtocolException(s);
        }
        this.frameLength = (n2 & 0x7F);
        if (this.frameLength == 126L) {
            this.frameLength = ((long)this.source.readShort() & 0xFFFFL);
        }
        else if (this.frameLength == 127L) {
            this.frameLength = this.source.readLong();
            if (this.frameLength < 0L) {
                throw new ProtocolException("Frame length 0x" + Long.toHexString(this.frameLength) + " > 0x7FFFFFFFFFFFFFFF");
            }
        }
        this.frameBytesRead = 0L;
        if (this.isControlFrame && this.frameLength > 125L) {
            throw new ProtocolException("Control frame must be less than 125B.");
        }
        if (this.isMasked) {
            this.source.readFully(this.maskKey);
        }
    }
    
    private void readMessage(final Buffer buffer) throws IOException {
        while (!this.closed) {
            Label_0097: {
                if (this.frameBytesRead == this.frameLength) {
                    if (!this.isFinalFrame) {
                        this.readUntilNonControlFrame();
                        if (this.opcode != 0) {
                            throw new ProtocolException("Expected continuation opcode. Got: " + Integer.toHexString(this.opcode));
                        }
                        if (!this.isFinalFrame || this.frameLength != 0L) {
                            break Label_0097;
                        }
                    }
                    return;
                }
            }
            final long n = this.frameLength - this.frameBytesRead;
            long read;
            if (this.isMasked) {
                read = this.source.read(this.maskBuffer, 0, (int)Math.min(n, this.maskBuffer.length));
                if (read == -1L) {
                    throw new EOFException();
                }
                WebSocketProtocol.toggleMask(this.maskBuffer, read, this.maskKey, this.frameBytesRead);
                buffer.write(this.maskBuffer, 0, (int)read);
            }
            else if ((read = this.source.read(buffer, n)) == -1L) {
                throw new EOFException();
            }
            this.frameBytesRead += read;
        }
        throw new IOException("closed");
    }
    
    private void readMessageFrame() throws IOException {
        final int opcode = this.opcode;
        if (opcode != 1 && opcode != 2) {
            throw new ProtocolException("Unknown opcode: " + Integer.toHexString(opcode));
        }
        final Buffer buffer = new Buffer();
        this.readMessage(buffer);
        if (opcode == 1) {
            this.frameCallback.onReadMessage(buffer.readUtf8());
            return;
        }
        this.frameCallback.onReadMessage(buffer.readByteString());
    }
    
    void processNextFrame() throws IOException {
        this.readHeader();
        if (this.isControlFrame) {
            this.readControlFrame();
            return;
        }
        this.readMessageFrame();
    }
    
    void readUntilNonControlFrame() throws IOException {
        while (!this.closed) {
            this.readHeader();
            if (!this.isControlFrame) {
                break;
            }
            this.readControlFrame();
        }
    }
    
    public interface FrameCallback
    {
        void onReadClose(final int p0, final String p1);
        
        void onReadMessage(final String p0) throws IOException;
        
        void onReadMessage(final ByteString p0) throws IOException;
        
        void onReadPing(final ByteString p0);
        
        void onReadPong(final ByteString p0);
    }
}
