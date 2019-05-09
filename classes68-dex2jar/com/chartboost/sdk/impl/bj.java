// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.impl;

import java.io.IOException;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.io.OutputStream;

public class bj extends OutputStream
{
    private static final byte[] a;
    private final List<byte[]> b;
    private int c;
    private int d;
    private byte[] e;
    private int f;
    
    static {
        a = new byte[0];
    }
    
    public bj() {
        this(1024);
    }
    
    public bj(final int n) {
        this.b = new ArrayList<byte[]>();
        if (n < 0) {
            throw new IllegalArgumentException("Negative initial size: " + n);
        }
        synchronized (this) {
            this.a(n);
        }
    }
    
    private void a(int max) {
        if (this.c < this.b.size() - 1) {
            this.d += this.e.length;
            ++this.c;
            this.e = this.b.get(this.c);
            return;
        }
        if (this.e == null) {
            this.d = 0;
        }
        else {
            max = Math.max(this.e.length << 1, max - this.d);
            this.d += this.e.length;
        }
        ++this.c;
        this.e = new byte[max];
        this.b.add(this.e);
    }
    
    public byte[] a() {
        synchronized (this) {
            int f = this.f;
            byte[] a;
            if (f == 0) {
                a = bj.a;
            }
            else {
                a = new byte[f];
                final Iterator<byte[]> iterator = this.b.iterator();
                int n = 0;
                while (iterator.hasNext()) {
                    final byte[] array = iterator.next();
                    final int min = Math.min(array.length, f);
                    System.arraycopy(array, 0, a, n, min);
                    f -= min;
                    if (f == 0) {
                        break;
                    }
                    n += min;
                }
            }
            return a;
        }
    }
    
    @Override
    public void close() throws IOException {
    }
    
    @Override
    public String toString() {
        return new String(this.a());
    }
    
    @Override
    public void write(final int n) {
        synchronized (this) {
            int n2;
            if ((n2 = this.f - this.d) == this.e.length) {
                this.a(this.f + 1);
                n2 = 0;
            }
            this.e[n2] = (byte)n;
            ++this.f;
        }
    }
    
    @Override
    public void write(final byte[] array, final int n, final int n2) {
        if (n < 0 || n > array.length || n2 < 0 || n + n2 > array.length || n + n2 < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (n2 == 0) {
            return;
        }
        synchronized (this) {
            final int f = this.f + n2;
            int n3 = this.f - this.d;
            int n4;
            for (int i = n2; i > 0; i = n4) {
                final int min = Math.min(i, this.e.length - n3);
                System.arraycopy(array, n + n2 - i, this.e, n3, min);
                n4 = i - min;
                if ((i = n4) > 0) {
                    this.a(f);
                    n3 = 0;
                }
            }
            this.f = f;
        }
    }
}
