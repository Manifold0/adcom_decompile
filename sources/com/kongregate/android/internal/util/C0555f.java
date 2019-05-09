package com.kongregate.android.internal.util;

import android.app.Activity;
import android.content.Intent;
import com.kongregate.android.internal.util.C0553e.C0550c;

/* renamed from: com.kongregate.android.internal.util.f */
public class C0555f {
    /* renamed from: a */
    private C0439b f745a;

    /* renamed from: com.kongregate.android.internal.util.f$b */
    public interface C0439b {
        /* renamed from: a */
        void mo1122a(String str, C0554a c0554a);
    }

    /* renamed from: com.kongregate.android.internal.util.f$a */
    public enum C0554a {
        SUCCESS,
        UNKNOWN,
        ACCESS_DISABLED,
        LOGIN_CANCELLED,
        NO_SYSTEM_ACCOUNT;

        /* renamed from: a */
        public String m656a() {
            return toString().toLowerCase();
        }
    }

    public C0555f(C0439b c0439b) {
        C0562j.m753a("FacebookSDKHelper: use proxy auth method for FB connect");
        this.f745a = c0439b;
    }

    /* renamed from: a */
    public void m657a(Activity activity, String str) {
        C0550c a = C0553e.m650a(activity, str);
        if (a != null) {
            m658a(null, a.m639b());
        }
    }

    /* renamed from: a */
    public void m658a(String str, C0554a c0554a) {
        C0562j.m753a("FacebookSDKHelper: result: " + c0554a);
        if (this.f745a == null) {
            C0562j.m759c("FacebookSDKHelper retrieved result but listener not set");
        } else {
            this.f745a.mo1122a(str, c0554a);
        }
    }

    /* renamed from: a */
    public final boolean m659a(Activity activity, int i, int i2, Intent intent) {
        C0562j.m753a("FacebookSDKHelper - onActivityResult(" + i + "," + i2 + "," + intent);
        Boolean valueOf = Boolean.valueOf(false);
        C0550c a = C0553e.m649a(i, i2, intent);
        if (a != null) {
            m658a(a.m638a(), a.m639b());
            valueOf = Boolean.valueOf(true);
        }
        return valueOf.booleanValue();
    }
}
