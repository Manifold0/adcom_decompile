package com.kongregate.p000o.p003a;

import android.content.Context;
import android.content.SharedPreferences;
import com.kongregate.android.internal.util.C0564l;
import java.util.Date;

/* renamed from: com.kongregate.o.a.j */
public class C0594j {
    /* renamed from: a */
    public static final String f853a = "kongregate_local_gdpr";
    /* renamed from: b */
    private volatile SharedPreferences f854b;

    public C0594j(Context context) {
        this.f854b = context.getSharedPreferences(f853a, 0);
    }

    /* renamed from: a */
    public boolean m907a(long j) {
        return C0564l.m774a(this.f854b, m904a(j, "show_alert"), false);
    }

    /* renamed from: a */
    public void m906a(long j, boolean z) {
        this.f854b.edit().putBoolean(m904a(j, "show_alert"), z).apply();
    }

    /* renamed from: b */
    public int m908b(long j) {
        return C0564l.m772a(this.f854b, m904a(j, "accepted_policy_version"), 0);
    }

    /* renamed from: c */
    public Date m909c(long j) {
        long a = C0564l.m773a(this.f854b, m904a(j, "accepted_at"), 0);
        if (a > 0) {
            return new Date(a);
        }
        return null;
    }

    /* renamed from: a */
    public void m905a(long j, int i) {
        this.f854b.edit().putInt(m904a(j, "accepted_policy_version"), i).putLong(m904a(j, "accepted_at"), System.currentTimeMillis()).apply();
    }

    /* renamed from: a */
    private String m904a(long j, String str) {
        return str + "." + j;
    }
}
