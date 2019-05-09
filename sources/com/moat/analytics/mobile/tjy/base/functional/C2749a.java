package com.moat.analytics.mobile.tjy.base.functional;

import java.util.NoSuchElementException;

/* renamed from: com.moat.analytics.mobile.tjy.base.functional.a */
public final class C2749a {
    /* renamed from: a */
    private static final C2749a f6680a = new C2749a();
    /* renamed from: b */
    private final Object f6681b;

    private C2749a() {
        this.f6681b = null;
    }

    private C2749a(Object obj) {
        if (obj == null) {
            throw new NullPointerException("Optional of null value.");
        }
        this.f6681b = obj;
    }

    /* renamed from: a */
    public static C2749a m6883a() {
        return f6680a;
    }

    /* renamed from: a */
    public static C2749a m6884a(Object obj) {
        return new C2749a(obj);
    }

    /* renamed from: b */
    public static C2749a m6885b(Object obj) {
        return obj == null ? C2749a.m6883a() : C2749a.m6884a(obj);
    }

    /* renamed from: b */
    public final Object m6886b() {
        if (this.f6681b != null) {
            return this.f6681b;
        }
        throw new NoSuchElementException("No value present");
    }

    /* renamed from: c */
    public final Object m6887c(Object obj) {
        return this.f6681b != null ? this.f6681b : obj;
    }

    /* renamed from: c */
    public final boolean m6888c() {
        return this.f6681b != null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C2749a)) {
            return false;
        }
        C2749a c2749a = (C2749a) obj;
        return this.f6681b != c2749a.f6681b ? (this.f6681b == null || c2749a.f6681b == null) ? false : this.f6681b.equals(c2749a.f6681b) : true;
    }

    public final int hashCode() {
        return this.f6681b == null ? 0 : this.f6681b.hashCode();
    }

    public final String toString() {
        if (this.f6681b == null) {
            return "Optional.empty";
        }
        return String.format("Optional[%s]", new Object[]{this.f6681b});
    }
}
