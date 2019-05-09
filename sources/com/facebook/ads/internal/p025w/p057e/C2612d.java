package com.facebook.ads.internal.p025w.p057e;

import android.content.Context;
import android.support.annotation.AnyThread;
import android.text.TextUtils;
import com.facebook.ads.internal.p042l.C2043b;
import com.facebook.ads.internal.p045n.C2057d;
import com.facebook.ads.internal.p046v.p047a.C2138a;
import com.facebook.ads.internal.settings.AdInternalSettings;
import java.util.HashSet;
import java.util.Set;

@AnyThread
/* renamed from: com.facebook.ads.internal.w.e.d */
public class C2612d {
    /* renamed from: a */
    private static String f6468a = null;
    /* renamed from: b */
    private static final Set<String> f6469b = new HashSet(1);
    /* renamed from: c */
    private static final Set<String> f6470c = new HashSet(2);

    static {
        f6469b.add("1ww8E0AYsR2oX5lndk2hwp2Uosk=\n");
        f6470c.add("toZ2GRnRjC9P5VVUdCpOrFH8lfQ=\n");
        f6470c.add("3lKvjNsfmrn+WmfDhvr2iVh/yRs=\n");
        f6470c.add("B08QtE4yLCdli4rptyqAEczXOeA=\n");
        f6470c.add("XZXI6anZbdKf+taURdnyUH5ipgM=\n");
    }

    /* renamed from: a */
    public static C2138a m6709a(Context context) {
        C2138a c2138a = new C2138a();
        C2612d.m6711a(context, c2138a, true);
        return c2138a;
    }

    /* renamed from: a */
    public static C2138a m6710a(Context context, boolean z) {
        C2138a c2138a = new C2138a();
        C2612d.m6711a(context, c2138a, z);
        if (!C2612d.m6712a()) {
            c2138a.m5475b(f6470c);
            c2138a.m5467a(f6469b);
        }
        return c2138a;
    }

    /* renamed from: a */
    private static void m6711a(Context context, C2138a c2138a, boolean z) {
        if (C2612d.m6712a()) {
            c2138a.m5476c(360000);
            c2138a.m5477d(120000);
        } else {
            c2138a.m5476c(30000);
        }
        c2138a.m5473b(3);
        c2138a.m5456a("user-agent", C2057d.m5009a(new C2043b(context), context, z));
    }

    /* renamed from: a */
    public static boolean m6712a() {
        Object urlPrefix = AdInternalSettings.getUrlPrefix();
        return !TextUtils.isEmpty(urlPrefix) && urlPrefix.endsWith(".sb");
    }

    /* renamed from: b */
    public static C2138a m6713b(Context context) {
        return C2612d.m6710a(context, true);
    }
}
