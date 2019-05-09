// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.iro.base.functional;

import java.util.NoSuchElementException;

public final class Optional<T>
{
    private static final Optional<?> \u02cf;
    private final T \u02cb;
    
    static {
        \u02cf = new Optional<Object>();
    }
    
    private Optional() {
        this.\u02cb = null;
    }
    
    private Optional(final T \u02cb) {
        if (\u02cb == null) {
            throw new NullPointerException("Optional of null value.");
        }
        this.\u02cb = \u02cb;
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
            if (this.\u02cb != optional.\u02cb && (this.\u02cb == null || optional.\u02cb == null || !this.\u02cb.equals(optional.\u02cb))) {
                return false;
            }
        }
        return true;
    }
    
    public final T get() {
        if (this.\u02cb == null) {
            throw new NoSuchElementException("No value present");
        }
        return this.\u02cb;
    }
    
    @Override
    public final int hashCode() {
        if (this.\u02cb == null) {
            return 0;
        }
        return this.\u02cb.hashCode();
    }
    
    public final boolean isPresent() {
        return this.\u02cb != null;
    }
    
    public final T orElse(T \u02cb) {
        if (this.\u02cb != null) {
            \u02cb = this.\u02cb;
        }
        return \u02cb;
    }
    
    @Override
    public final String toString() {
        if (this.\u02cb != null) {
            return String.format("Optional[%s]", this.\u02cb);
        }
        return "Optional.empty";
    }
}
