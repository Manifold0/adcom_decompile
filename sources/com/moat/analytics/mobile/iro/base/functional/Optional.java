package com.moat.analytics.mobile.iro.base.functional;

import java.util.NoSuchElementException;

public final class Optional<T> {
    /* renamed from: ˏ */
    private static final Optional<?> f1134 = new Optional();
    /* renamed from: ˋ */
    private final T f1135;

    private Optional() {
        this.f1135 = null;
    }

    public static <T> Optional<T> empty() {
        return f1134;
    }

    private Optional(T t) {
        if (t == null) {
            throw new NullPointerException("Optional of null value.");
        }
        this.f1135 = t;
    }

    public static <T> Optional<T> of(T t) {
        return new Optional(t);
    }

    public static <T> Optional<T> ofNullable(T t) {
        return t == null ? empty() : of(t);
    }

    public final T get() {
        if (this.f1135 != null) {
            return this.f1135;
        }
        throw new NoSuchElementException("No value present");
    }

    public final boolean isPresent() {
        return this.f1135 != null;
    }

    public final T orElse(T t) {
        return this.f1135 != null ? this.f1135 : t;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Optional)) {
            return false;
        }
        Optional optional = (Optional) obj;
        if (this.f1135 == optional.f1135) {
            return true;
        }
        if (this.f1135 == null || optional.f1135 == null || !this.f1135.equals(optional.f1135)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return this.f1135 == null ? 0 : this.f1135.hashCode();
    }

    public final String toString() {
        if (this.f1135 == null) {
            return "Optional.empty";
        }
        return String.format("Optional[%s]", new Object[]{this.f1135});
    }
}
