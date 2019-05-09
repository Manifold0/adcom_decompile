package com.tapjoy.internal;

import android.content.SharedPreferences;

/* renamed from: com.tapjoy.internal.o */
public abstract class C2986o {
    /* renamed from: a */
    protected SharedPreferences f8225a;
    /* renamed from: b */
    protected String f8226b;

    public C2986o(SharedPreferences sharedPreferences, String str) {
        this.f8225a = sharedPreferences;
        this.f8226b = str;
    }

    /* renamed from: c */
    public final void m8200c() {
        this.f8225a.edit().remove(this.f8226b).commit();
    }
}
