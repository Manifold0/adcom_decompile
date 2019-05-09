package com.tapjoy.internal;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/* renamed from: com.tapjoy.internal.q */
public final class C2992q extends C2986o {
    /* renamed from: c */
    private final String f8231c = null;

    public C2992q(SharedPreferences sharedPreferences, String str) {
        super(sharedPreferences, str);
    }

    /* renamed from: a */
    public final String m8219a() {
        return this.a.getString(this.b, this.f8231c);
    }

    /* renamed from: a */
    public final void m8220a(String str) {
        this.a.edit().putString(this.b, str).commit();
    }

    /* renamed from: a */
    public final Editor m8218a(Editor editor, String str) {
        return str != null ? editor.putString(this.b, str) : editor.remove(this.b);
    }
}
