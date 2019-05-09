// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.tjy.base.functional;

import java.util.NoSuchElementException;

public final class a
{
    private static final a a;
    private final Object b;
    
    static {
        a = new a();
    }
    
    private a() {
        this.b = null;
    }
    
    private a(final Object b) {
        if (b == null) {
            throw new NullPointerException("Optional of null value.");
        }
        this.b = b;
    }
    
    public static a a() {
        return com.moat.analytics.mobile.tjy.base.functional.a.a;
    }
    
    public static a a(final Object o) {
        return new a(o);
    }
    
    public static a b(final Object o) {
        if (o == null) {
            return a();
        }
        return a(o);
    }
    
    public final Object b() {
        if (this.b == null) {
            throw new NoSuchElementException("No value present");
        }
        return this.b;
    }
    
    public final Object c(Object b) {
        if (this.b != null) {
            b = this.b;
        }
        return b;
    }
    
    public final boolean c() {
        return this.b != null;
    }
    
    @Override
    public final boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof a)) {
                return false;
            }
            final a a = (a)o;
            if (this.b != a.b) {
                return this.b != null && a.b != null && this.b.equals(a.b);
            }
        }
        return true;
    }
    
    @Override
    public final int hashCode() {
        if (this.b == null) {
            return 0;
        }
        return this.b.hashCode();
    }
    
    @Override
    public final String toString() {
        if (this.b != null) {
            return String.format("Optional[%s]", this.b);
        }
        return "Optional.empty";
    }
}
