// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Logger;

public final class hy
{
    static final Logger a;
    
    static {
        a = Logger.getLogger(hy.class.getName());
    }
    
    private hy() {
    }
    
    public static hv a(final ie ie) {
        if (ie == null) {
            throw new IllegalArgumentException("sink == null");
        }
        return new hz(ie);
    }
    
    public static hw a(final if if1) {
        if (if1 == null) {
            throw new IllegalArgumentException("source == null");
        }
        return new ia(if1);
    }
    
    public static ie a(final OutputStream outputStream) {
        final ig ig = new ig();
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        }
        return new ie() {
            @Override
            public final void a(final hu hu, long n) {
                ih.a(hu.b, 0L, n);
                while (n > 0L) {
                    ig.a();
                    final ib a = hu.a;
                    final int n2 = (int)Math.min(n, a.c - a.b);
                    outputStream.write(a.a, a.b, n2);
                    a.b += n2;
                    final long n3 = n - n2;
                    hu.b -= n2;
                    n = n3;
                    if (a.b == a.c) {
                        hu.a = a.a();
                        ic.a(a);
                        n = n3;
                    }
                }
            }
            
            @Override
            public final void close() {
                outputStream.close();
            }
            
            @Override
            public final void flush() {
                outputStream.flush();
            }
            
            @Override
            public final String toString() {
                return "sink(" + outputStream + ")";
            }
        };
    }
    
    public static if a(final InputStream inputStream) {
        final ig ig = new ig();
        if (inputStream == null) {
            throw new IllegalArgumentException("in == null");
        }
        return new if() {
            @Override
            public final long b(final hu hu, final long n) {
                if (n < 0L) {
                    throw new IllegalArgumentException("byteCount < 0: " + n);
                }
                if (n == 0L) {
                    return 0L;
                }
                try {
                    ig.a();
                    final ib c = hu.c(1);
                    final int read = inputStream.read(c.a, c.c, (int)Math.min(n, 8192 - c.c));
                    if (read == -1) {
                        return -1L;
                    }
                    c.c += read;
                    hu.b += read;
                    return read;
                }
                catch (AssertionError assertionError) {
                    if (hy.a(assertionError)) {
                        throw new IOException(assertionError);
                    }
                    throw assertionError;
                }
            }
            
            @Override
            public final void close() {
                inputStream.close();
            }
            
            @Override
            public final String toString() {
                return "source(" + inputStream + ")";
            }
        };
    }
    
    static boolean a(final AssertionError assertionError) {
        return assertionError.getCause() != null && assertionError.getMessage() != null && assertionError.getMessage().contains("getsockname failed");
    }
}
