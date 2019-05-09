package com.facebook.ads.internal.p045n;

import android.content.Context;
import android.support.annotation.UiThread;
import android.util.Log;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.internal.p025w.p026b.C2565a;
import com.facebook.ads.internal.p025w.p044h.C2625a;
import com.facebook.ads.internal.p025w.p044h.C2625a.C2049a;
import com.facebook.ads.internal.p025w.p044h.C2626b;
import com.facebook.ads.internal.p025w.p068a.C2564b;
import com.facebook.ads.internal.p039i.C2018a;
import com.facebook.ads.internal.p041k.C2037c;
import com.facebook.ads.internal.p050r.C2078a;
import java.util.Map;

@UiThread
/* renamed from: com.facebook.ads.internal.n.a */
public final class C2051a {
    /* renamed from: a */
    private static boolean f4599a;

    /* renamed from: a */
    public static synchronized void m4996a(Context context) {
        synchronized (C2051a.class) {
            if (C2018a.m4870a() == null) {
                Context applicationContext = context.getApplicationContext();
                C2018a.m4871a(applicationContext);
                C2051a.m4998c(applicationContext);
            }
        }
    }

    /* renamed from: b */
    public static synchronized void m4997b(Context context) {
        synchronized (C2051a.class) {
            Context a = C2018a.m4870a();
            if (a == null) {
                a = context.getApplicationContext();
                C2018a.m4871a(a);
                C2051a.m4998c(a);
                Log.e(AudienceNetworkAds.TAG, "You don't call AudienceNetworkAds.initialize(). Some functionality may not work properly.");
                C2625a.m6738a(a, "api", C2626b.f6551p, new Exception("initialize() not called."));
            }
            if (!f4599a) {
                if (C2078a.m5106p(a)) {
                    try {
                        Thread.setDefaultUncaughtExceptionHandler(new C2037c(Thread.getDefaultUncaughtExceptionHandler(), a, C2057d.m5015b(a)));
                    } catch (Throwable e) {
                        Log.e(AudienceNetworkAds.TAG, "No permissions to set the default uncaught exception handler", e);
                    }
                }
                f4599a = true;
            }
        }
    }

    /* renamed from: c */
    private static synchronized void m4998c(final Context context) {
        synchronized (C2051a.class) {
            if (C2625a.f6506a == null) {
                C2625a.f6506a = new C2049a() {
                    /* renamed from: a */
                    public Map<String, String> mo5464a() {
                        return C2057d.m5015b(context);
                    }

                    /* renamed from: b */
                    public boolean mo5465b() {
                        return C2565a.m6616a();
                    }
                };
            }
            C2564b.m6614a(context);
        }
    }
}
