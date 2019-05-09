// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.vng.a.b;

import java.util.NoSuchElementException;

public final class a<T>
{
    private static final a<?> a;
    private final T b;
    
    static {
        a = new a<Object>();
    }
    
    private a() {
        this.b = null;
    }
    
    private a(final T b) {
        if (b == null) {
            throw new NullPointerException("Optional of null value.");
        }
        this.b = b;
    }
    
    public static <T> a<T> a() {
        return (a<T>)com.moat.analytics.mobile.vng.a.b.a.a;
    }
    
    public static <T> a<T> a(final T t) {
        return new a<T>(t);
    }
    
    public static <T> a<T> b(final T t) {
        if (t == null) {
            return a();
        }
        return (a<T>)a((Object)t);
    }
    
    public T b() {
        if (this.b == null) {
            throw new NoSuchElementException("No value present");
        }
        return this.b;
    }
    
    public T c(T b) {
        if (this.b != null) {
            b = this.b;
        }
        return b;
    }
    
    public boolean c() {
        return this.b != null;
    }
    
    @Override
    public boolean equals(final Object o) {
        final boolean b = false;
        boolean b2;
        if (this == o) {
            b2 = true;
        }
        else {
            b2 = b;
            if (o instanceof a) {
                final a a = (a)o;
                if (this.b != a.b) {
                    b2 = b;
                    if (this.b == null) {
                        return b2;
                    }
                    b2 = b;
                    if (a.b == null) {
                        return b2;
                    }
                    b2 = b;
                    if (!this.b.equals(a.b)) {
                        return b2;
                    }
                }
                return true;
            }
        }
        return b2;
    }
    
    @Override
    public int hashCode() {
        if (this.b == null) {
            return 0;
        }
        return this.b.hashCode();
    }
    
    @Override
    public String toString() {
        if (this.b != null) {
            return String.format("Optional[%s]", this.b);
        }
        return "Optional.empty";
    }
}
