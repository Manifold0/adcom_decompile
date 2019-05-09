package com.tapjoy.internal;

import android.content.SharedPreferences;

/* renamed from: com.tapjoy.internal.j */
public final class C2987j extends C2986o {
    /* renamed from: c */
    private final boolean f8227c = false;

    public C2987j(SharedPreferences sharedPreferences, String str) {
        super(sharedPreferences, str);
    }

    /* renamed from: a */
    public final void m8202a(boolean z) {
        this.a.edit().putBoolean(this.b, z).commit();
    }

    /* renamed from: a */
    public final Boolean m8201a() {
        return Boolean.valueOf(this.a.getBoolean(this.b, this.f8227c));
    }
}
