// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.Arrays;

final class id extends hx
{
    final transient byte[][] f;
    final transient int[] g;
    
    id(final hu hu, final int n) {
        final int n2 = 0;
        super(null);
        ih.a(hu.b, 0L, n);
        ib ib = hu.a;
        int n3 = 0;
        for (int i = 0; i < n; i += ib.c - ib.b, ++n3, ib = ib.f) {
            if (ib.c == ib.b) {
                throw new AssertionError((Object)"s.limit == s.pos");
            }
        }
        this.f = new byte[n3][];
        this.g = new int[n3 * 2];
        ib ib2 = hu.a;
        int n4 = 0;
        int j = n2;
        while (j < n) {
            this.f[n4] = ib2.a;
            if ((j += ib2.c - ib2.b) > n) {
                j = n;
            }
            this.g[n4] = j;
            this.g[this.f.length + n4] = ib2.b;
            ib2.d = true;
            ++n4;
            ib2 = ib2.f;
        }
    }
    
    private int b(int binarySearch) {
        binarySearch = Arrays.binarySearch(this.g, 0, this.f.length, binarySearch + 1);
        if (binarySearch >= 0) {
            return binarySearch;
        }
        return ~binarySearch;
    }
    
    private hx e() {
        return new hx(this.d());
    }
    
    @Override
    public final byte a(final int n) {
        ih.a(this.g[this.f.length - 1], n, 1L);
        final int b = this.b(n);
        int n2;
        if (b == 0) {
            n2 = 0;
        }
        else {
            n2 = this.g[b - 1];
        }
        return this.f[b][n - n2 + this.g[this.f.length + b]];
    }
    
    @Override
    public final hx a(final int n, final int n2) {
        return this.e().a(n, n2);
    }
    
    @Override
    public final String a() {
        return this.e().a();
    }
    
    @Override
    final void a(final hu hu) {
        int i = 0;
        final int length = this.f.length;
        int n = 0;
        while (i < length) {
            final int n2 = this.g[length + i];
            final int n3 = this.g[i];
            final ib a = new ib(this.f[i], n2, n2 + n3 - n);
            if (hu.a == null) {
                a.g = a;
                a.f = a;
                hu.a = a;
            }
            else {
                hu.a.g.a(a);
            }
            ++i;
            n = n3;
        }
        hu.b += n;
    }
    
    @Override
    public final boolean a(int n, final byte[] array, int n2, int i) {
        if (n >= 0 && n <= this.c() - i && n2 >= 0 && n2 <= array.length - i) {
            final int b = this.b(n);
            int n3 = n;
            int n4;
            int min;
            for (n = b; i > 0; i -= min, ++n) {
                if (n == 0) {
                    n4 = 0;
                }
                else {
                    n4 = this.g[n - 1];
                }
                min = Math.min(i, this.g[n] - n4 + n4 - n3);
                if (!ih.a(this.f[n], n3 - n4 + this.g[this.f.length + n], array, n2, min)) {
                    return false;
                }
                n3 += min;
                n2 += min;
            }
            return true;
        }
        return false;
    }
    
    @Override
    public final String b() {
        return this.e().b();
    }
    
    @Override
    public final int c() {
        return this.g[this.f.length - 1];
    }
    
    @Override
    public final byte[] d() {
        int i = 0;
        final byte[] array = new byte[this.g[this.f.length - 1]];
        final int length = this.f.length;
        int n = 0;
        while (i < length) {
            final int n2 = this.g[length + i];
            final int n3 = this.g[i];
            System.arraycopy(this.f[i], n2, array, n, n3 - n);
            ++i;
            n = n3;
        }
        return array;
    }
    
    @Override
    public final boolean equals(final Object o) {
        final boolean b = false;
        boolean b2;
        if (o == this) {
            b2 = true;
        }
        else {
            b2 = b;
            if (o instanceof hx) {
                b2 = b;
                if (((hx)o).c() == this.c()) {
                    final hx hx = (hx)o;
                    int i = this.c();
                    int n = 0;
                    Label_0064: {
                        if (this.c() - i < 0) {
                            n = 0;
                        }
                        else {
                            int b3 = this.b(0);
                            int n2 = 0;
                            int n3 = 0;
                            while (i > 0) {
                                int n4;
                                if (b3 == 0) {
                                    n4 = 0;
                                }
                                else {
                                    n4 = this.g[b3 - 1];
                                }
                                final int min = Math.min(i, this.g[b3] - n4 + n4 - n3);
                                if (!hx.a(n2, this.f[b3], n3 - n4 + this.g[this.f.length + b3], min)) {
                                    n = 0;
                                    break Label_0064;
                                }
                                n3 += min;
                                n2 += min;
                                i -= min;
                                ++b3;
                            }
                            n = 1;
                        }
                    }
                    b2 = b;
                    if (n != 0) {
                        return true;
                    }
                }
            }
        }
        return b2;
    }
    
    @Override
    public final int hashCode() {
        final int d = this.d;
        if (d != 0) {
            return d;
        }
        int d2 = 1;
        final int length = this.f.length;
        int i = 0;
        int n = 0;
        while (i < length) {
            final byte[] array = this.f[i];
            final int n2 = this.g[length + i];
            final int n3 = this.g[i];
            for (int j = n2; j < n3 - n + n2; ++j) {
                d2 = d2 * 31 + array[j];
            }
            ++i;
            n = n3;
        }
        return this.d = d2;
    }
    
    @Override
    public final String toString() {
        return this.e().toString();
    }
}
