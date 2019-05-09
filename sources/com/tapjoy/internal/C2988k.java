package com.tapjoy.internal;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/* renamed from: com.tapjoy.internal.k */
public final class C2988k extends C2986o {
    /* renamed from: c */
    private final double f8228c = 0.0d;

    public C2988k(SharedPreferences sharedPreferences, String str) {
        super(sharedPreferences, str);
    }

    /* renamed from: a */
    public final double m8203a() {
        String string = this.a.getString(this.b, null);
        if (string != null) {
            try {
                return Double.parseDouble(string);
            } catch (NumberFormatException e) {
            }
        }
        return this.f8228c;
    }

    /* renamed from: a */
    public final Editor m8204a(Editor editor) {
        return editor.remove(this.b);
    }

    /* renamed from: a */
    public final Editor m8205a(Editor editor, double d) {
        return editor.putString(this.b, Double.toString(d));
    }
}
