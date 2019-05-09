package com.facebook.ads.internal.p025w.p073i;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.PowerManager;
import android.util.Log;
import com.facebook.ads.internal.p025w.p044h.C2625a;
import com.facebook.ads.internal.p025w.p044h.C2626b;

/* renamed from: com.facebook.ads.internal.w.i.a */
public class C2627a {
    /* renamed from: a */
    private static final String f6562a = C2627a.class.getSimpleName();

    /* renamed from: a */
    public static boolean m6743a(Context context) {
        return C2627a.m6744b(context) && C2628b.m6746b(context);
    }

    /* renamed from: b */
    public static boolean m6744b(Context context) {
        if (context == null) {
            Log.v(f6562a, "Invalid context in screen interactive check, assuming interactive.");
            return true;
        }
        try {
            PowerManager powerManager = (PowerManager) context.getSystemService("power");
            return VERSION.SDK_INT >= 20 ? powerManager.isInteractive() : powerManager.isScreenOn();
        } catch (Throwable e) {
            Log.e(f6562a, "Exception in screen interactive check, assuming interactive.", e);
            C2625a.m6741b(context, "risky", C2626b.f6516G, e);
            return true;
        }
    }
}
