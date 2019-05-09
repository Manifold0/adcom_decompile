package com.tapjoy.internal;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/* renamed from: com.tapjoy.internal.m */
public final class C2989m extends C2986o {
    /* renamed from: c */
    private final int f8229c;

    public C2989m(SharedPreferences sharedPreferences, String str, int i) {
        super(sharedPreferences, str);
        this.f8229c = i;
    }

    /* renamed from: a */
    public final Integer m8207a() {
        return Integer.valueOf(m8210b());
    }

    /* renamed from: b */
    public final int m8210b() {
        return this.a.getInt(this.b, this.f8229c);
    }

    /* renamed from: a */
    public final void m8209a(Integer num) {
        if (num != null) {
            m8208a(num.intValue());
        } else {
            m8200c();
        }
    }

    /* renamed from: a */
    public final void m8208a(int i) {
        this.a.edit().putInt(this.b, i).commit();
    }

    /* renamed from: a */
    public final Editor m8206a(Editor editor, int i) {
        return editor.putInt(this.b, i);
    }
}
