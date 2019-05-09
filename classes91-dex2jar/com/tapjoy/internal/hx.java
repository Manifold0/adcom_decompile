// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.Arrays;
import java.io.Serializable;

public class hx implements Serializable, Comparable
{
    static final char[] a;
    public static final hx b;
    final byte[] c;
    transient int d;
    transient String e;
    
    static {
        a = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        b = new hx(new byte[0].clone());
    }
    
    public hx(final byte[] c) {
        this.c = c;
    }
    
    public byte a(final int n) {
        return this.c[n];
    }
    
    public hx a(final int n, final int n2) {
        if (n < 0) {
            throw new IllegalArgumentException("beginIndex < 0");
        }
        if (n2 > this.c.length) {
            throw new IllegalArgumentException("endIndex > length(" + this.c.length + ")");
        }
        final int n3 = n2 - n;
        if (n3 < 0) {
            throw new IllegalArgumentException("endIndex < beginIndex");
        }
        if (n == 0 && n2 == this.c.length) {
            return this;
        }
        final byte[] array = new byte[n3];
        System.arraycopy(this.c, n, array, 0, n3);
        return new hx(array);
    }
    
    public String a() {
        final String e = this.e;
        if (e != null) {
            return e;
        }
        return this.e = new String(this.c, ih.a);
    }
    
    void a(final hu hu) {
        hu.a(this.c, 0, this.c.length);
    }
    
    public boolean a(final int n, final byte[] array, final int n2, final int n3) {
        return n >= 0 && n <= this.c.length - n3 && n2 >= 0 && n2 <= array.length - n3 && ih.a(this.c, n, array, n2, n3);
    }
    
    public String b() {
        int i = 0;
        final char[] array = new char[this.c.length * 2];
        final byte[] c = this.c;
        final int length = c.length;
        int n = 0;
        while (i < length) {
            final byte b = c[i];
            final int n2 = n + 1;
            array[n] = hx.a[b >> 4 & 0xF];
            n = n2 + 1;
            array[n2] = hx.a[b & 0xF];
            ++i;
        }
        return new String(array);
    }
    
    public int c() {
        return this.c.length;
    }
    
    public byte[] d() {
        return this.c.clone();
    }
    
    @Override
    public boolean equals(final Object o) {
        return o == this || (o instanceof hx && ((hx)o).c() == this.c.length && ((hx)o).a(0, this.c, 0, this.c.length));
    }
    
    @Override
    public int hashCode() {
        final int d = this.d;
        if (d != 0) {
            return d;
        }
        return this.d = Arrays.hashCode(this.c);
    }
    
    @Override
    public String toString() {
        if (this.c.length == 0) {
            return "[size=0]";
        }
        final String a = this.a();
        final int length = a.length();
        int n = 0;
        int i = 0;
        while (true) {
            while (i < length) {
                if (n != 64) {
                    final int codePoint = a.codePointAt(i);
                    if ((Character.isISOControl(codePoint) || codePoint == 10 || codePoint == 13) && codePoint != 65533) {
                        ++n;
                        i += Character.charCount(codePoint);
                        continue;
                    }
                    i = -1;
                }
                if (i == -1) {
                    if (this.c.length <= 64) {
                        return "[hex=" + this.b() + "]";
                    }
                    return "[size=" + this.c.length + " hex=" + this.a(0, 64).b() + "\u2026]";
                }
                else {
                    final String replace = a.substring(0, i).replace("\\", "\\\\").replace("\n", "\\n").replace("\r", "\\r");
                    if (i < a.length()) {
                        return "[size=" + this.c.length + " text=" + replace + "\u2026]";
                    }
                    return "[text=" + replace + "]";
                }
            }
            i = a.length();
            continue;
        }
    }
}
