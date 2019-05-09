package com.facebook.ads.internal.p025w.p044h;

import android.content.Context;
import android.support.annotation.AnyThread;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.internal.p039i.C2018a;
import com.facebook.ads.internal.p041k.C2038e;
import com.facebook.ads.internal.p050r.C2078a;
import com.facebook.ads.internal.settings.AdInternalSettings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@AnyThread
/* renamed from: com.facebook.ads.internal.w.h.a */
public class C2625a {
    @Nullable
    /* renamed from: a */
    public static C2049a f6506a;
    @VisibleForTesting
    /* renamed from: b */
    static Executor f6507b = Executors.newSingleThreadExecutor();
    /* renamed from: c */
    public static boolean f6508c = false;
    /* renamed from: d */
    private static final Set<Integer> f6509d = new HashSet();

    /* renamed from: com.facebook.ads.internal.w.h.a$a */
    public interface C2049a {
        /* renamed from: a */
        Map<String, String> mo5464a();

        /* renamed from: b */
        boolean mo5465b();
    }

    /* renamed from: a */
    public static void m6738a(Context context, String str, int i, Exception exception) {
        Object obj = null;
        try {
            synchronized (f6509d) {
                if (!f6509d.contains(Integer.valueOf(i))) {
                    f6509d.add(Integer.valueOf(i));
                    obj = 1;
                }
            }
            if (obj == null) {
                return;
            }
            if (C2625a.m6740a(context, str, i, Math.random())) {
                C2625a.m6742c(context, str, i, exception);
            }
        } catch (Throwable th) {
            C2625a.m6739a(th);
        }
    }

    /* renamed from: a */
    public static void m6739a(Throwable th) {
        Log.e(AudienceNetworkAds.TAG, "Exception during logging debug event.", th);
        if (f6508c) {
            throw new RuntimeException(th);
        }
    }

    @VisibleForTesting
    /* renamed from: a */
    static boolean m6740a(Context context, String str, int i, double d) {
        return d >= 1.0d - (C2078a.m5109s(context).contains(new StringBuilder().append(str).append(":").append(i).toString()) ? ((double) (C2078a.m5111u(context) * C2078a.m5110t(context))) / 10000.0d : ((double) C2078a.m5111u(context)) / 100.0d);
    }

    /* renamed from: b */
    public static void m6741b(Context context, String str, int i, Exception exception) {
        if (context == null) {
            try {
                C2625a.m6739a(new RuntimeException("Can't log Debug Event. Context is null."));
                return;
            } catch (Throwable th) {
                C2625a.m6739a(th);
                return;
            }
        }
        C2018a.m4871a(context);
        if (f6508c) {
            String str2 = "Debug crash because of event with subtype = " + str + ", subtypeCode = " + i;
            if (!(AdInternalSettings.f4779d && i == C2626b.aa)) {
                throw new RuntimeException(str2, exception);
            }
        }
        if (C2625a.m6740a(context, str, i, Math.random())) {
            C2625a.m6742c(context, str, i, exception);
        }
    }

    /* renamed from: c */
    private static void m6742c(final Context context, final String str, final int i, final Exception exception) {
        Object obj = (f6506a == null || !f6506a.mo5465b()) ? null : 1;
        if (obj != null) {
            Log.e(AudienceNetworkAds.TAG, "Debug Event with subtype = " + str + ", subtypeCode = " + i, exception);
        }
        f6507b.execute(new Runnable() {
            public void run() {
                try {
                    Map a;
                    if (C2625a.f6506a != null) {
                        a = C2625a.f6506a.mo5464a();
                    } else if (C2625a.f6508c) {
                        throw new RuntimeException("Debug crash because sEnvironmentDataProvider not injected", exception);
                    } else {
                        a = new HashMap();
                    }
                    a.put("subtype", str);
                    a.put("subtype_code", String.valueOf(i));
                    C2038e.m4933a(exception, context, a);
                } catch (Throwable th) {
                    C2625a.m6739a(th);
                }
            }
        });
    }
}
