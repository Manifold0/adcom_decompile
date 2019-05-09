package com.moat.analytics.mobile.vng.p013a.p015b;

import java.util.NoSuchElementException;

/* renamed from: com.moat.analytics.mobile.vng.a.b.a */
public final class C0820a<T> {
    /* renamed from: a */
    private static final C0820a<?> f1337a = new C0820a();
    /* renamed from: b */
    private final T f1338b;

    private C0820a() {
        this.f1338b = null;
    }

    private C0820a(T t) {
        if (t == null) {
            throw new NullPointerException("Optional of null value.");
        }
        this.f1338b = t;
    }

    /* renamed from: a */
    public static <T> C0820a<T> m1432a() {
        return f1337a;
    }

    /* renamed from: a */
    public static <T> C0820a<T> m1433a(T t) {
        return new C0820a(t);
    }

    /* renamed from: b */
    public static <T> C0820a<T> m1434b(T t) {
        return t == null ? C0820a.m1432a() : C0820a.m1433a(t);
    }

    /* renamed from: b */
    public T m1435b() {
        if (this.f1338b != null) {
            return this.f1338b;
        }
        throw new NoSuchElementException("No value present");
    }

    /* renamed from: c */
    public T m1436c(T t) {
        return this.f1338b != null ? this.f1338b : t;
    }

    /* renamed from: c */
    public boolean m1437c() {
        return this.f1338b != null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0820a)) {
            return false;
        }
        C0820a c0820a = (C0820a) obj;
        return this.f1338b == c0820a.f1338b || !(this.f1338b == null || c0820a.f1338b == null || !this.f1338b.equals(c0820a.f1338b));
    }

    public int hashCode() {
        return this.f1338b == null ? 0 : this.f1338b.hashCode();
    }

    public String toString() {
        if (this.f1338b == null) {
            return "Optional.empty";
        }
        return String.format("Optional[%s]", new Object[]{this.f1338b});
    }
}
