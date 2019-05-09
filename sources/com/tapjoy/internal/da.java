package com.tapjoy.internal;

import java.io.InputStream;
import java.io.OutputStream;

public final class da {
    /* renamed from: a */
    public static long m7354a(InputStream inputStream, OutputStream outputStream) {
        byte[] bArr = new byte[4096];
        long j = 0;
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                return j;
            }
            outputStream.write(bArr, 0, read);
            j += (long) read;
        }
    }
}
