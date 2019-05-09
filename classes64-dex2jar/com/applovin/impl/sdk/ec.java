// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

public class ec<T> implements Comparable
{
    private static int a;
    private final int b;
    private final String c;
    private final T d;
    
    static {
        ec.a = 0;
    }
    
    private ec(final String c, final T d) {
        if (c == null) {
            throw new IllegalArgumentException("No name specified");
        }
        if (d == null) {
            throw new IllegalArgumentException("No default value specified");
        }
        this.c = c;
        this.d = d;
        this.b = ec.a;
        ++ec.a;
    }
    
    public int a() {
        return this.b;
    }
    
    T a(final Object o) {
        return (T)this.d.getClass().cast(o);
    }
    
    public String b() {
        return this.c;
    }
    
    public T c() {
        return this.d;
    }
    
    @Override
    public int compareTo(final Object o) {
        if (o != null && o instanceof ec) {
            return this.c.compareTo(((ec)o).b());
        }
        return 0;
    }
}
