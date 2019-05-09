package com.facebook.ads.internal.p041k;

import android.content.Context;
import android.os.Process;
import android.support.annotation.Nullable;
import android.util.Log;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.internal.p025w.p026b.C2585o;
import com.facebook.ads.internal.p025w.p026b.C2589s;
import com.facebook.ads.internal.p050r.C2078a;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.facebook.appevents.AppEventsConstants;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Map;

/* renamed from: com.facebook.ads.internal.k.c */
public class C2037c implements UncaughtExceptionHandler {
    /* renamed from: a */
    private final UncaughtExceptionHandler f4543a;
    /* renamed from: b */
    private final Context f4544b;
    /* renamed from: c */
    private final Map<String, String> f4545c;

    public C2037c(@Nullable UncaughtExceptionHandler uncaughtExceptionHandler, Context context, Map<String, String> map) {
        this.f4543a = uncaughtExceptionHandler;
        if (context == null) {
            throw new IllegalArgumentException("Missing Context");
        }
        this.f4544b = context.getApplicationContext();
        this.f4545c = map;
    }

    /* renamed from: a */
    private static void m4932a() {
        try {
            Process.killProcess(Process.myPid());
        } catch (Throwable th) {
        }
        try {
            System.exit(10);
        } catch (Throwable th2) {
        }
    }

    public void uncaughtException(Thread thread, Throwable th) {
        try {
            String a = C2589s.m6655a(th);
            if (a != null && a.contains("com.facebook.ads")) {
                Map a2 = new C2036b(a, this.f4545c).m4931a();
                a2.put("subtype", "crash");
                a2.put("subtype_code", AppEventsConstants.EVENT_PARAM_VALUE_NO);
                C2038e.m4936a(new C2034d(C2585o.m6652b(), C2585o.m6653c(), a2), this.f4544b);
            }
        } catch (Exception e) {
        }
        if (C2078a.m5085X(this.f4544b) && AdInternalSettings.f4779d) {
            try {
                Log.e(AudienceNetworkAds.TAG, "Facebook Audience Network process crashed with exception: ", th);
            } catch (Throwable th2) {
            }
            C2037c.m4932a();
        } else if (this.f4543a != null) {
            this.f4543a.uncaughtException(thread, th);
        } else {
            C2037c.m4932a();
        }
    }
}
