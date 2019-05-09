package com.tapjoy.internal;

import android.content.Context;
import android.content.SharedPreferences;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class gl {
    /* renamed from: a */
    final C2992q f7924a = new C2992q(this.f7926c, "noMoreToday.date");
    /* renamed from: b */
    public final C2992q f7925b = new C2992q(this.f7926c, "noMoreToday.actionIds");
    /* renamed from: c */
    private final SharedPreferences f7926c;

    public gl(Context context) {
        this.f7926c = context.getApplicationContext().getSharedPreferences("fiverocks", 0);
        m7970b();
    }

    /* renamed from: a */
    static String m7969a() {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
    }

    /* renamed from: b */
    public final void m7970b() {
        String a = this.f7924a.m8219a();
        if (a != null && !m7969a().equals(a)) {
            this.f7924a.m8220a(null);
            this.f7925b.m8220a(null);
        }
    }
}
