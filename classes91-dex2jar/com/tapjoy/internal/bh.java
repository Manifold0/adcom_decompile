// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.io.InputStream;
import java.io.FilterInputStream;

public final class bh extends FilterInputStream
{
    public bh(final InputStream inputStream) {
        super(inputStream);
    }
    
    @Override
    public final int read(final byte[] array) {
        int i = 0;
        while (i < array.length) {
            final int read = super.read(array, i, array.length - i);
            if (read == -1) {
                if (i != 0) {
                    break;
                }
                return -1;
            }
            else {
                i += read;
            }
        }
        return i;
    }
    
    @Override
    public final int read(final byte[] array, final int n, final int n2) {
        int i = 0;
        while (i < n2) {
            final int read = super.read(array, n + i, n2 - i);
            if (read == -1) {
                if (i != 0) {
                    break;
                }
                return -1;
            }
            else {
                i += read;
            }
        }
        return i;
    }
    
    @Override
    public final long skip(final long n) {
        long n2;
        long skip;
        for (n2 = 0L; n2 < n; n2 += skip) {
            final long n3 = skip = super.skip(n - n2);
            if (n3 == 0L) {
                if (super.read() < 0) {
                    break;
                }
                skip = n3 + 1L;
            }
        }
        return n2;
    }
}
