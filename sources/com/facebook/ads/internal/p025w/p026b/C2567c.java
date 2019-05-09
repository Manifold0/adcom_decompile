package com.facebook.ads.internal.p025w.p026b;

import android.content.Context;
import android.util.Log;
import com.facebook.ads.internal.settings.AdInternalSettings;

/* renamed from: com.facebook.ads.internal.w.b.c */
public class C2567c {
    /* renamed from: a */
    public static void m6620a() {
        try {
            Class.forName("android.os.AsyncTask");
        } catch (Throwable th) {
        }
    }

    /* renamed from: a */
    public static void m6621a(Context context, String str) {
        if (AdInternalSettings.isTestMode(context)) {
            Log.d("FBAudienceNetworkLog", str + " (displayed for test ads only)");
        }
    }
}
