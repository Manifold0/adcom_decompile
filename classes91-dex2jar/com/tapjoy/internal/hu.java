// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.nio.charset.Charset;
import java.io.EOFException;

public final class hu implements hv, hw, Cloneable
{
    private static final byte[] c;
    ib a;
    long b;
    
    static {
        c = new byte[] { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102 };
    }
    
    private void a(final byte[] array) {
        int n2;
        for (int i = 0; i < array.length; i += n2) {
            final int n = array.length - i;
            ih.a(array.length, i, n);
            final ib a = this.a;
            if (a == null) {
                n2 = -1;
            }
            else {
                final int min = Math.min(n, a.c - a.b);
                System.arraycopy(a.a, a.b, array, i, min);
                a.b += min;
                this.b -= min;
                n2 = min;
                if (a.b == a.c) {
                    this.a = a.a();
                    ic.a(a);
                    n2 = min;
                }
            }
            if (n2 == -1) {
                throw new EOFException();
            }
        }
    }
    
    private byte[] g(final long n) {
        ih.a(this.b, 0L, n);
        if (n > 2147483647L) {
            throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + n);
        }
        final byte[] array = new byte[(int)n];
        this.a(array);
        return array;
    }
    
    public final hu a(final int n) {
        final ib c = this.c(1);
        c.a[c.c++] = (byte)n;
        ++this.b;
        return this;
    }
    
    public final hu a(final hx hx) {
        if (hx == null) {
            throw new IllegalArgumentException("byteString == null");
        }
        hx.a(this);
        return this;
    }
    
    public final hu a(final String s) {
        final int length = s.length();
        if (s == null) {
            throw new IllegalArgumentException("string == null");
        }
        if (length < 0) {
            throw new IllegalArgumentException("endIndex < beginIndex: " + length + " < 0");
        }
        if (length > s.length()) {
            throw new IllegalArgumentException("endIndex > string.length: " + length + " > " + s.length());
        }
        int i = 0;
        while (i < length) {
            final char char1 = s.charAt(i);
            if (char1 < '\u0080') {
                final ib c = this.c(1);
                final byte[] a = c.a;
                final int n = c.c - i;
                final int min = Math.min(length, 8192 - n);
                final int n2 = i + 1;
                a[i + n] = (byte)char1;
                for (i = n2; i < min; ++i) {
                    final char char2 = s.charAt(i);
                    if (char2 >= '\u0080') {
                        break;
                    }
                    a[i + n] = (byte)char2;
                }
                final int n3 = i + n - c.c;
                c.c += n3;
                this.b += n3;
            }
            else if (char1 < '\u0800') {
                this.a(char1 >> 6 | 0xC0);
                this.a((char1 & '?') | 0x80);
                ++i;
            }
            else if (char1 < '\ud800' || char1 > '\udfff') {
                this.a(char1 >> 12 | 0xE0);
                this.a((char1 >> 6 & 0x3F) | 0x80);
                this.a((char1 & '?') | 0x80);
                ++i;
            }
            else {
                char char3;
                if (i + 1 < length) {
                    char3 = s.charAt(i + 1);
                }
                else {
                    char3 = '\0';
                }
                if (char1 > '\udbff' || char3 < '\udc00' || char3 > '\udfff') {
                    this.a(63);
                    ++i;
                }
                else {
                    final int n4 = ((char3 & 0xFFFF23FF) | (char1 & 0xFFFF27FF) << 10) + 65536;
                    this.a(n4 >> 18 | 0xF0);
                    this.a((n4 >> 12 & 0x3F) | 0x80);
                    this.a((n4 >> 6 & 0x3F) | 0x80);
                    this.a((n4 & 0x3F) | 0x80);
                    i += 2;
                }
            }
        }
        return this;
    }
    
    public final hu a(final byte[] array, int i, final int n) {
        if (array == null) {
            throw new IllegalArgumentException("source == null");
        }
        ih.a(array.length, 0L, n);
        ib c;
        int min;
        for (int n2 = n + 0; i < n2; i += min, c.c += min) {
            c = this.c(1);
            min = Math.min(n2 - i, 8192 - c.c);
            System.arraycopy(array, i, c.a, c.c, min);
        }
        this.b += n;
        return this;
    }
    
    @Override
    public final hv a() {
        return this;
    }
    
    @Override
    public final void a(final long n) {
        if (this.b < n) {
            throw new EOFException();
        }
    }
    
    @Override
    public final void a(final hu hu, long n) {
        if (hu == null) {
            throw new IllegalArgumentException("source == null");
        }
        if (hu == this) {
            throw new IllegalArgumentException("source == this");
        }
        ih.a(hu.b, 0L, n);
        while (n > 0L) {
            if (n < hu.a.c - hu.a.b) {
                ib g;
                if (this.a != null) {
                    g = this.a.g;
                }
                else {
                    g = null;
                }
                if (g != null && g.e) {
                    final long n2 = g.c;
                    int b;
                    if (g.d) {
                        b = 0;
                    }
                    else {
                        b = g.b;
                    }
                    if (n2 + n - b <= 8192L) {
                        hu.a.a(g, (int)n);
                        hu.b -= n;
                        this.b += n;
                        break;
                    }
                }
                final ib a = hu.a;
                final int n3 = (int)n;
                if (n3 <= 0 || n3 > a.c - a.b) {
                    throw new IllegalArgumentException();
                }
                ib a2;
                if (n3 >= 1024) {
                    a2 = new ib(a);
                }
                else {
                    a2 = ic.a();
                    System.arraycopy(a.a, a.b, a2.a, 0, n3);
                }
                a2.c = a2.b + n3;
                a.b += n3;
                a.g.a(a2);
                hu.a = a2;
            }
            final ib a3 = hu.a;
            final long n4 = a3.c - a3.b;
            hu.a = a3.a();
            if (this.a == null) {
                this.a = a3;
                final ib a4 = this.a;
                final ib a5 = this.a;
                final ib a6 = this.a;
                a5.g = a6;
                a4.f = a6;
            }
            else {
                final ib a7 = this.a.g.a(a3);
                if (a7.g == a7) {
                    throw new IllegalStateException();
                }
                if (a7.g.e) {
                    final int n5 = a7.c - a7.b;
                    final int c = a7.g.c;
                    int b2;
                    if (a7.g.d) {
                        b2 = 0;
                    }
                    else {
                        b2 = a7.g.b;
                    }
                    if (n5 <= b2 + (8192 - c)) {
                        a7.a(a7.g, n5);
                        a7.a();
                        ic.a(a7);
                    }
                }
            }
            hu.b -= n4;
            this.b += n4;
            n -= n4;
        }
    }
    
    @Override
    public final long b(final hu hu, final long n) {
        if (hu == null) {
            throw new IllegalArgumentException("sink == null");
        }
        if (n < 0L) {
            throw new IllegalArgumentException("byteCount < 0: " + n);
        }
        if (this.b == 0L) {
            return -1L;
        }
        long b = n;
        if (n > this.b) {
            b = this.b;
        }
        hu.a(this, b);
        return b;
    }
    
    public final hu b(int a) {
        a = ih.a(a);
        final ib c = this.c(4);
        final byte[] a2 = c.a;
        final int c2 = c.c;
        final int n = c2 + 1;
        a2[c2] = (byte)(a >>> 24 & 0xFF);
        final int n2 = n + 1;
        a2[n] = (byte)(a >>> 16 & 0xFF);
        final int n3 = n2 + 1;
        a2[n2] = (byte)(a >>> 8 & 0xFF);
        a2[n3] = (byte)(a & 0xFF);
        c.c = n3 + 1;
        this.b += 4L;
        return this;
    }
    
    @Override
    public final hx b(final long n) {
        return new hx(this.g(n));
    }
    
    @Override
    public final boolean b() {
        return this.b == 0L;
    }
    
    @Override
    public final byte c() {
        if (this.b == 0L) {
            throw new IllegalStateException("size == 0");
        }
        final ib a = this.a;
        final int b = a.b;
        final int c = a.c;
        final byte[] a2 = a.a;
        final int b2 = b + 1;
        final byte b3 = a2[b];
        --this.b;
        if (b2 == c) {
            this.a = a.a();
            ic.a(a);
            return b3;
        }
        a.b = b2;
        return b3;
    }
    
    final ib c(final int n) {
        if (n <= 0 || n > 8192) {
            throw new IllegalArgumentException();
        }
        if (this.a != null) {
            final ib g = this.a.g;
            if (g.c + n <= 8192) {
                final ib a = g;
                if (g.e) {
                    return a;
                }
            }
            return g.a(ic.a());
        }
        this.a = ic.a();
        final ib a2 = this.a;
        final ib a3 = this.a;
        final ib a = this.a;
        a3.g = a;
        a2.f = a;
        return a;
    }
    
    @Override
    public final String c(final long n) {
        final Charset a = ih.a;
        ih.a(this.b, 0L, n);
        if (a == null) {
            throw new IllegalArgumentException("charset == null");
        }
        if (n > 2147483647L) {
            throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + n);
        }
        String s;
        if (n == 0L) {
            s = "";
        }
        else {
            final ib a2 = this.a;
            if (a2.b + n > a2.c) {
                return new String(this.g(n), a);
            }
            final String s2 = new String(a2.a, a2.b, (int)n, a);
            a2.b += (int)n;
            this.b -= n;
            s = s2;
            if (a2.b == a2.c) {
                this.a = a2.a();
                ic.a(a2);
                return s2;
            }
        }
        return s;
    }
    
    @Override
    public final void close() {
    }
    
    public final int d() {
        if (this.b < 4L) {
            throw new IllegalStateException("size < 4: " + this.b);
        }
        final ib a = this.a;
        final int b = a.b;
        final int c = a.c;
        if (c - b < 4) {
            return (this.c() & 0xFF) << 24 | (this.c() & 0xFF) << 16 | (this.c() & 0xFF) << 8 | (this.c() & 0xFF);
        }
        final byte[] a2 = a.a;
        final int n = b + 1;
        final byte b2 = a2[b];
        final int n2 = n + 1;
        final byte b3 = a2[n];
        final int n3 = n2 + 1;
        final byte b4 = a2[n2];
        final int b5 = n3 + 1;
        final int n4 = (b2 & 0xFF) << 24 | (b3 & 0xFF) << 16 | (b4 & 0xFF) << 8 | (a2[n3] & 0xFF);
        this.b -= 4L;
        if (b5 == c) {
            this.a = a.a();
            ic.a(a);
            return n4;
        }
        a.b = b5;
        return n4;
    }
    
    @Override
    public final void d(long n) {
        while (n > 0L) {
            if (this.a == null) {
                throw new EOFException();
            }
            final int n2 = (int)Math.min(n, this.a.c - this.a.b);
            this.b -= n2;
            final long n3 = n - n2;
            final ib a = this.a;
            a.b += n2;
            n = n3;
            if (this.a.b != this.a.c) {
                continue;
            }
            final ib a2 = this.a;
            this.a = a2.a();
            ic.a(a2);
            n = n3;
        }
    }
    
    @Override
    public final int e() {
        return ih.a(this.d());
    }
    
    public final hu e(long a) {
        a = ih.a(a);
        final ib c = this.c(8);
        final byte[] a2 = c.a;
        final int c2 = c.c;
        final int n = c2 + 1;
        a2[c2] = (byte)(a >>> 56 & 0xFFL);
        final int n2 = n + 1;
        a2[n] = (byte)(a >>> 48 & 0xFFL);
        final int n3 = n2 + 1;
        a2[n2] = (byte)(a >>> 40 & 0xFFL);
        final int n4 = n3 + 1;
        a2[n3] = (byte)(a >>> 32 & 0xFFL);
        final int n5 = n4 + 1;
        a2[n4] = (byte)(a >>> 24 & 0xFFL);
        final int n6 = n5 + 1;
        a2[n5] = (byte)(a >>> 16 & 0xFFL);
        final int n7 = n6 + 1;
        a2[n6] = (byte)(a >>> 8 & 0xFFL);
        a2[n7] = (byte)(a & 0xFFL);
        c.c = n7 + 1;
        this.b += 8L;
        return this;
    }
    
    @Override
    public final boolean equals(final Object o) {
        long n = 0L;
        if (this == o) {
            return true;
        }
        if (!(o instanceof hu)) {
            return false;
        }
        final hu hu = (hu)o;
        if (this.b != hu.b) {
            return false;
        }
        if (this.b == 0L) {
            return true;
        }
        ib a = this.a;
        ib a2 = hu.a;
        int b = a.b;
        int b2 = a2.b;
        while (n < this.b) {
            final long n2 = Math.min(a.c - b, a2.c - b2);
            for (int n3 = 0; n3 < n2; ++n3, ++b2, ++b) {
                if (a.a[b] != a2.a[b2]) {
                    return false;
                }
            }
            int b3 = b;
            ib f = a;
            if (b == a.c) {
                f = a.f;
                b3 = f.b;
            }
            int b4 = b2;
            ib f2 = a2;
            if (b2 == a2.c) {
                f2 = a2.f;
                b4 = f2.b;
            }
            n += n2;
            b2 = b4;
            b = b3;
            a2 = f2;
            a = f;
        }
        return true;
    }
    
    @Override
    public final long f() {
        if (this.b < 8L) {
            throw new IllegalStateException("size < 8: " + this.b);
        }
        final ib a = this.a;
        final int b = a.b;
        final int c = a.c;
        long n;
        if (c - b < 8) {
            n = (((long)this.d() & 0xFFFFFFFFL) << 32 | ((long)this.d() & 0xFFFFFFFFL));
        }
        else {
            final byte[] a2 = a.a;
            final int n2 = b + 1;
            final long n3 = a2[b];
            final int n4 = n2 + 1;
            final long n5 = a2[n2];
            final int n6 = n4 + 1;
            final long n7 = a2[n4];
            final int n8 = n6 + 1;
            final long n9 = a2[n6];
            final int n10 = n8 + 1;
            final long n11 = a2[n8];
            final int n12 = n10 + 1;
            final long n13 = a2[n10];
            final int n14 = n12 + 1;
            final long n15 = a2[n12];
            final int b2 = n14 + 1;
            n = (((long)a2[n14] & 0xFFL) | ((n5 & 0xFFL) << 48 | (n3 & 0xFFL) << 56 | (n7 & 0xFFL) << 40 | (n9 & 0xFFL) << 32 | (n11 & 0xFFL) << 24 | (n13 & 0xFFL) << 16 | (n15 & 0xFFL) << 8));
            this.b -= 8L;
            if (b2 == c) {
                this.a = a.a();
                ic.a(a);
            }
            else {
                a.b = b2;
            }
        }
        return ih.a(n);
    }
    
    @Override
    public final void flush() {
    }
    
    public final byte[] g() {
        try {
            return this.g(this.b);
        }
        catch (EOFException ex) {
            throw new AssertionError((Object)ex);
        }
    }
    
    public final hu h() {
        final hu hu = new hu();
        if (this.b == 0L) {
            return hu;
        }
        hu.a = new ib(this.a);
        final ib a = hu.a;
        final ib a2 = hu.a;
        final ib a3 = hu.a;
        a2.g = a3;
        a.f = a3;
        for (ib ib = this.a.f; ib != this.a; ib = ib.f) {
            hu.a.g.a(new ib(ib));
        }
        hu.b = this.b;
        return hu;
    }
    
    @Override
    public final int hashCode() {
        ib a = this.a;
        if (a == null) {
            return 0;
        }
        int n = 1;
        ib f;
        int n2;
        do {
            int i;
            int c;
            byte b;
            for (i = a.b, c = a.c, n2 = n; i < c; ++i, n2 = b + n2 * 31) {
                b = a.a[i];
            }
            f = a.f;
            n = n2;
        } while ((a = f) != this.a);
        return n2;
    }
    
    @Override
    public final String toString() {
        if (this.b > 2147483647L) {
            throw new IllegalArgumentException("size > Integer.MAX_VALUE: " + this.b);
        }
        final int n = (int)this.b;
        hx b;
        if (n == 0) {
            b = hx.b;
        }
        else {
            b = new id(this, n);
        }
        return b.toString();
    }
}
