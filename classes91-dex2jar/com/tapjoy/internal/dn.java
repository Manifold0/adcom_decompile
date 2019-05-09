// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.io.OutputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.io.IOException;

public abstract class dn
{
    public static final dn c;
    public static final dn d;
    public static final dn e;
    public static final dn f;
    public static final dn g;
    public static final dn h;
    public static final dn i;
    public static final dn j;
    public static final dn k;
    public static final dn l;
    public static final dn m;
    public static final dn n;
    public static final dn o;
    public static final dn p;
    public static final dn q;
    final Class a;
    dn b;
    private final dk r;
    
    static {
        c = new dn(dk.a, Boolean.class) {};
        d = new dn(dk.a, Integer.class) {};
        e = new dn(dk.a, Integer.class) {};
        f = new dn(dk.a, Integer.class) {};
        h = (g = new dn(dk.d, Integer.class) {});
        i = new dn(dk.a, Long.class) {};
        j = new dn(dk.a, Long.class) {};
        k = new dn(dk.a, Long.class) {};
        m = (l = new dn(dk.b, Long.class) {});
        n = new dn(dk.d, Float.class) {};
        o = new dn(dk.b, Double.class) {};
        p = new dn(dk.c, String.class) {};
        q = new dn(dk.c, hx.class) {};
    }
    
    public dn(final dk r, final Class a) {
        this.r = r;
        this.a = a;
    }
    
    private Object a(final hw hw) {
        dm.a(hw, "source == null");
        return this.a(new do(hw));
    }
    
    private void a(final hv hv, final Object o) {
        dm.a(o, "value == null");
        dm.a(hv, "sink == null");
        this.a(new dp(hv), o);
    }
    
    public static String c(final Object o) {
        return o.toString();
    }
    
    public int a(final int n, final Object o) {
        int a;
        final int n2 = a = this.a(o);
        if (this.r == dk.c) {
            a = n2 + dp.a(n2);
        }
        return a + dp.a(dp.a(n, dk.a));
    }
    
    public abstract int a(final Object p0);
    
    public final dn a() {
        final dn b = this.b;
        if (b != null) {
            return b;
        }
        return this.b = new dn(this.r, (Class)List.class) {};
    }
    
    public abstract Object a(final do p0);
    
    public final Object a(final InputStream inputStream) {
        dm.a(inputStream, "stream == null");
        return this.a(hy.a(hy.a(inputStream)));
    }
    
    public final Object a(final byte[] array) {
        dm.a(array, "bytes == null");
        final hu hu = new hu();
        if (array == null) {
            throw new IllegalArgumentException("source == null");
        }
        return this.a(hu.a(array, 0, array.length));
    }
    
    public void a(final dp dp, final int n, final Object o) {
        dp.c(dp.a(n, this.r));
        if (this.r == dk.c) {
            dp.c(this.a(o));
        }
        this.a(dp, o);
    }
    
    public abstract void a(final dp p0, final Object p1);
    
    public final void a(final OutputStream outputStream, final Object o) {
        dm.a(o, "value == null");
        dm.a(outputStream, "stream == null");
        final hv a = hy.a(hy.a(outputStream));
        this.a(a, o);
        a.a();
    }
    
    public final byte[] b(final Object o) {
        dm.a(o, "value == null");
        final hu hu = new hu();
        try {
            this.a(hu, o);
            return hu.g();
        }
        catch (IOException ex) {
            throw new AssertionError((Object)ex);
        }
    }
    
    public static final class a extends IllegalArgumentException
    {
        public final int a;
        
        a(final int a, final Class clazz) {
            super("Unknown enum tag " + a + " for " + clazz.getCanonicalName());
            this.a = a;
        }
    }
}
