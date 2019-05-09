package com.tapjoy.internal;

import java.io.FilterInputStream;
import java.io.InputStream;

public final class bh extends FilterInputStream {
    public bh(InputStream inputStream) {
        super(inputStream);
    }

    public final int read(byte[] buffer, int offset, int count) {
        int i = 0;
        while (i < count) {
            int read = super.read(buffer, offset + i, count - i);
            if (read == -1) {
                return i != 0 ? i : -1;
            } else {
                i += read;
            }
        }
        return i;
    }

    public final int read(byte[] buffer) {
        int i = 0;
        while (i < buffer.length) {
            int read = super.read(buffer, i, buffer.length - i);
            if (read == -1) {
                return i != 0 ? i : -1;
            } else {
                i += read;
            }
        }
        return i;
    }

    public final long skip(long count) {
        long j = 0;
        while (j < count) {
            long skip = super.skip(count - j);
            if (skip == 0) {
                if (super.read() < 0) {
                    break;
                }
                skip++;
            }
            j = skip + j;
        }
        return j;
    }
}
