package com.facebook.ads.internal.p037f;

import android.support.annotation.Nullable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.facebook.ads.internal.f.c */
public class C1996c implements Serializable {
    private static final long serialVersionUID = -3209129042070173126L;
    /* renamed from: a */
    private final int f4412a;
    @Nullable
    /* renamed from: b */
    private final String f4413b;
    /* renamed from: c */
    private final String f4414c;
    /* renamed from: d */
    private final List<C1996c> f4415d;
    /* renamed from: e */
    private C1996c f4416e;

    C1996c(int i, @Nullable String str, String str2) {
        this.f4415d = new ArrayList();
        this.f4412a = i;
        this.f4413b = str;
        this.f4414c = str2;
    }

    C1996c(String str) {
        this(0, null, str);
    }

    /* renamed from: a */
    public int m4813a() {
        return this.f4412a;
    }

    /* renamed from: a */
    public void m4814a(C1996c c1996c) {
        c1996c.f4416e = this;
        this.f4415d.add(c1996c);
    }

    /* renamed from: b */
    public String m4815b() {
        return this.f4413b;
    }

    @Nullable
    /* renamed from: c */
    public String m4816c() {
        return this.f4414c;
    }

    /* renamed from: d */
    public List<C1996c> m4817d() {
        return this.f4415d;
    }

    /* renamed from: e */
    public C1996c m4818e() {
        return this.f4416e;
    }
}
