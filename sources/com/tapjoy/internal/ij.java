package com.tapjoy.internal;

public final class ij {
    /* renamed from: a */
    public static ij f8221a = new ij(null);
    /* renamed from: b */
    public String f8222b;
    /* renamed from: c */
    public Throwable f8223c;
    /* renamed from: d */
    private Object[] f8224d;

    public ij(String str) {
        this(str, null, null);
    }

    public ij(String str, Object[] objArr, Throwable th) {
        this.f8222b = str;
        this.f8223c = th;
        if (th == null) {
            this.f8224d = objArr;
        } else if (objArr == null || objArr.length == 0) {
            throw new IllegalStateException("non-sensical empty or null argument array");
        } else {
            int length = objArr.length - 1;
            Object obj = new Object[length];
            System.arraycopy(objArr, 0, obj, 0, length);
            this.f8224d = obj;
        }
    }
}
