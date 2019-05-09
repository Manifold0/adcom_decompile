package com.tapjoy.internal;

import java.nio.CharBuffer;

public final class db {
    /* renamed from: a */
    private static long m7355a(Readable readable, Appendable appendable) {
        CharSequence allocate = CharBuffer.allocate(2048);
        long j = 0;
        while (true) {
            int read = readable.read(allocate);
            if (read == -1) {
                return j;
            }
            allocate.flip();
            appendable.append(allocate, 0, read);
            j += (long) read;
        }
    }

    /* renamed from: a */
    public static String m7356a(Readable readable) {
        Appendable stringBuilder = new StringBuilder();
        m7355a(readable, stringBuilder);
        return stringBuilder.toString();
    }
}
