package com.facebook.ads.internal.p045n;

import android.content.Context;
import android.os.Build;
import com.facebook.ads.internal.p025w.p044h.C2625a;
import com.facebook.ads.internal.p025w.p044h.C2626b;
import com.facebook.share.internal.MessengerShareContentUtility;
import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.facebook.ads.internal.n.b */
class C2052b {
    /* renamed from: a */
    private static final String f4600a = C2052b.class.getSimpleName();
    /* renamed from: b */
    private static final AtomicBoolean f4601b = new AtomicBoolean();

    C2052b() {
    }

    /* renamed from: a */
    static String m4999a(Context context) {
        JSONObject jSONObject = new JSONObject();
        String str = "is_emu";
        boolean z = Build.FINGERPRINT.contains(MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE) || Build.FINGERPRINT.startsWith("unknown") || Build.MODEL.contains("google_sdk") || Build.MODEL.contains("Emulator") || Build.MODEL.contains("Android SDK built for x86") || Build.MANUFACTURER.contains("Genymotion") || ((Build.BRAND.startsWith(MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE) && Build.DEVICE.startsWith(MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE)) || "google_sdk".equals(Build.PRODUCT));
        C2052b.m5000a(jSONObject, str, String.valueOf(z));
        C2052b.m5000a(jSONObject, "apk_size", String.valueOf(C2052b.m5001b(context)));
        return jSONObject.toString();
    }

    /* renamed from: a */
    private static void m5000a(JSONObject jSONObject, String str, String str2) {
        try {
            jSONObject.put(str, str2);
        } catch (JSONException e) {
        }
    }

    /* renamed from: b */
    private static long m5001b(Context context) {
        try {
            return new File(context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).publicSourceDir).length();
        } catch (Exception e) {
            if (f4601b.compareAndSet(false, true)) {
                C2625a.m6741b(context, MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE, C2626b.f6512C, e);
            }
            return -1;
        }
    }
}
