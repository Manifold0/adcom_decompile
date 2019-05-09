package com.moat.analytics.mobile.cha.base.functional;

import java.util.NoSuchElementException;

public final class Optional<T> {
    /* renamed from: ˏ */
    private static final Optional<?> f3458 = new Optional();
    /* renamed from: ॱ */
    private final T f3459;

    private Optional() {
        this.f3459 = null;
    }

    public static <T> Optional<T> empty() {
        return f3458;
    }

    private Optional(T t) {
        if (t == null) {
            throw new NullPointerException("Optional of null value.");
        }
        this.f3459 = t;
    }

    public static <T> Optional<T> of(T t) {
        return new Optional(t);
    }

    public static <T> Optional<T> ofNullable(T t) {
        return t == null ? empty() : of(t);
    }

    public final T get() {
        if (this.f3459 != null) {
            return this.f3459;
        }
        throw new NoSuchElementException("No value present");
    }

    public final boolean isPresent() {
        return this.f3459 != null;
    }

    public final T orElse(T t) {
        return this.f3459 != null ? this.f3459 : t;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Optional)) {
            return false;
        }
        Optional optional = (Optional) obj;
        if (this.f3459 == optional.f3459) {
            return true;
        }
        if (this.f3459 == null || optional.f3459 == null || !this.f3459.equals(optional.f3459)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return this.f3459 == null ? 0 : this.f3459.hashCode();
    }

    public final String toString() {
        if (this.f3459 == null) {
            return "Optional.empty";
        }
        return String.format("Optional[%s]", new Object[]{this.f3459});
    }
}
