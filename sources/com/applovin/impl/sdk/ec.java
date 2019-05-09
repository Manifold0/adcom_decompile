package com.applovin.impl.sdk;

public class ec<T> implements Comparable {
    /* renamed from: a */
    private static int f2430a = 0;
    /* renamed from: b */
    private final int f2431b;
    /* renamed from: c */
    private final String f2432c;
    /* renamed from: d */
    private final T f2433d;

    private ec(String str, T t) {
        if (str == null) {
            throw new IllegalArgumentException("No name specified");
        } else if (t == null) {
            throw new IllegalArgumentException("No default value specified");
        } else {
            this.f2432c = str;
            this.f2433d = t;
            this.f2431b = f2430a;
            f2430a++;
        }
    }

    /* renamed from: a */
    public int m2659a() {
        return this.f2431b;
    }

    /* renamed from: a */
    T m2660a(Object obj) {
        return this.f2433d.getClass().cast(obj);
    }

    /* renamed from: b */
    public String m2661b() {
        return this.f2432c;
    }

    /* renamed from: c */
    public T m2662c() {
        return this.f2433d;
    }

    public int compareTo(Object obj) {
        return (obj == null || !(obj instanceof ec)) ? 0 : this.f2432c.compareTo(((ec) obj).m2661b());
    }
}
