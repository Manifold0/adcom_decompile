// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.cha.base.functional;

import java.util.NoSuchElementException;

public final class Optional<T>
{
    private static final Optional<?> \u02cf;
    private final T \u0971;
    
    static {
        \u02cf = new Optional<Object>();
    }
    
    private Optional() {
        this.\u0971 = null;
    }
    
    private Optional(final T \u0971) {
        if (\u0971 == null) {
            throw new NullPointerException("Optional of null value.");
        }
        this.\u0971 = \u0971;
    }
    
    public static <T> Optional<T> empty() {
        return (Optional<T>)Optional.\u02cf;
    }
    
    public static <T> Optional<T> of(final T t) {
        return new Optional<T>(t);
    }
    
    public static <T> Optional<T> ofNullable(final T t) {
        if (t == null) {
            return empty();
        }
        return (Optional<T>)of((Object)t);
    }
    
    @Override
    public final boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof Optional)) {
                return false;
            }
            final Optional optional = (Optional)o;
            if (this.\u0971 != optional.\u0971 && (this.\u0971 == null || optional.\u0971 == null || !this.\u0971.equals(optional.\u0971))) {
                return false;
            }
        }
        return true;
    }
    
    public final T get() {
        if (this.\u0971 == null) {
            throw new NoSuchElementException("No value present");
        }
        return this.\u0971;
    }
    
    @Override
    public final int hashCode() {
        if (this.\u0971 == null) {
            return 0;
        }
        return this.\u0971.hashCode();
    }
    
    public final boolean isPresent() {
        return this.\u0971 != null;
    }
    
    public final T orElse(T \u0971) {
        if (this.\u0971 != null) {
            \u0971 = this.\u0971;
        }
        return \u0971;
    }
    
    @Override
    public final String toString() {
        if (this.\u0971 != null) {
            return String.format("Optional[%s]", this.\u0971);
        }
        return "Optional.empty";
    }
}
