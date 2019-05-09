package com.tapjoy.internal;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/* renamed from: com.tapjoy.internal.n */
public final class C2990n extends C2986o {
    /* renamed from: c */
    private final long f8230c = 0;

    public C2990n(SharedPreferences sharedPreferences, String str) {
        super(sharedPreferences, str);
    }

    /* renamed from: a */
    public final long m8211a() {
        return this.a.getLong(this.b, this.f8230c);
    }

    /* renamed from: a */
    public final void m8214a(long j) {
        this.a.edit().putLong(this.b, j).commit();
    }

    /* renamed from: a */
    public final Editor m8212a(Editor editor) {
        return editor.remove(this.b);
    }

    /* renamed from: a */
    public final Editor m8213a(Editor editor, long j) {
        return editor.putLong(this.b, j);
    }
}
