// 
// Decompiled by Procyon v0.5.34
// 

package okhttp3.internal.cache2;

import java.io.IOException;
import java.io.EOFException;
import okio.Buffer;
import java.nio.channels.FileChannel;
import java.nio.ByteBuffer;

final class FileOperator
{
    private static final int BUFFER_SIZE = 8192;
    private final byte[] byteArray;
    private final ByteBuffer byteBuffer;
    private final FileChannel fileChannel;
    
    public FileOperator(final FileChannel fileChannel) {
        this.byteArray = new byte[8192];
        this.byteBuffer = ByteBuffer.wrap(this.byteArray);
        this.fileChannel = fileChannel;
    }
    
    public void read(long n, final Buffer buffer, final long n2) throws IOException {
        long n3 = n;
        n = n2;
        if (n2 < 0L) {
            throw new IndexOutOfBoundsException();
        }
        Label_0064: {
            break Label_0064;
            try {
                do {
                    final int position = this.byteBuffer.position();
                    buffer.write(this.byteArray, 0, position);
                    n3 += position;
                    n -= position;
                    this.byteBuffer.clear();
                    if (n <= 0L) {
                        return;
                    }
                    this.byteBuffer.limit((int)Math.min(8192L, n));
                } while (this.fileChannel.read(this.byteBuffer, n3) != -1);
                throw new EOFException();
            }
            finally {
                this.byteBuffer.clear();
            }
        }
    }
    
    public void write(long n, final Buffer buffer, long n2) throws IOException {
        if (n2 >= 0L) {
            long n3 = n2;
            if (n2 <= buffer.size()) {
                while (n3 > 0L) {
                    try {
                        final int n4 = (int)Math.min(8192L, n3);
                        buffer.read(this.byteArray, 0, n4);
                        this.byteBuffer.limit(n4);
                        n2 = n;
                        boolean hasRemaining;
                        do {
                            n = n2 + this.fileChannel.write(this.byteBuffer, n2);
                            hasRemaining = this.byteBuffer.hasRemaining();
                            n2 = n;
                        } while (hasRemaining);
                        n3 -= n4;
                        continue;
                    }
                    finally {
                        this.byteBuffer.clear();
                    }
                    break;
                }
                return;
            }
        }
        throw new IndexOutOfBoundsException();
    }
}
