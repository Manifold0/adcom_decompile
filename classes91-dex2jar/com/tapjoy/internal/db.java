// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.nio.CharBuffer;

public final class db
{
    private static long a(final Readable readable, final Appendable appendable) {
        final CharBuffer allocate = CharBuffer.allocate(2048);
        long n = 0L;
        while (true) {
            final int read = readable.read(allocate);
            if (read == -1) {
                break;
            }
            allocate.flip();
            appendable.append(allocate, 0, read);
            n += read;
        }
        return n;
    }
    
    public static String a(final Readable readable) {
        final StringBuilder sb = new StringBuilder();
        a(readable, sb);
        return sb.toString();
    }
}
