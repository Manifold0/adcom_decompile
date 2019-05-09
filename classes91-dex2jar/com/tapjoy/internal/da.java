// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.io.OutputStream;
import java.io.InputStream;

public final class da
{
    public static long a(final InputStream inputStream, final OutputStream outputStream) {
        final byte[] array = new byte[4096];
        long n = 0L;
        while (true) {
            final int read = inputStream.read(array);
            if (read == -1) {
                break;
            }
            outputStream.write(array, 0, read);
            n += read;
        }
        return n;
    }
}
