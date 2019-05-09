// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.n;

import com.facebook.ads.internal.w.h.a;
import java.io.File;
import org.json.JSONException;
import android.os.Build;
import org.json.JSONObject;
import android.content.Context;
import java.util.concurrent.atomic.AtomicBoolean;

class b
{
    private static final String a;
    private static final AtomicBoolean b;
    
    static {
        a = b.class.getSimpleName();
        b = new AtomicBoolean();
    }
    
    static String a(final Context context) {
        final JSONObject jsonObject = new JSONObject();
        a(jsonObject, "is_emu", String.valueOf(Build.FINGERPRINT.contains("generic") || Build.FINGERPRINT.startsWith("unknown") || Build.MODEL.contains("google_sdk") || Build.MODEL.contains("Emulator") || Build.MODEL.contains("Android SDK built for x86") || Build.MANUFACTURER.contains("Genymotion") || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")) || "google_sdk".equals(Build.PRODUCT)));
        a(jsonObject, "apk_size", String.valueOf(b(context)));
        return jsonObject.toString();
    }
    
    private static void a(final JSONObject jsonObject, final String s, final String s2) {
        try {
            jsonObject.put(s, (Object)s2);
        }
        catch (JSONException ex) {}
    }
    
    private static long b(final Context context) {
        try {
            return new File(context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).publicSourceDir).length();
        }
        catch (Exception ex) {
            if (com.facebook.ads.internal.n.b.b.compareAndSet(false, true)) {
                com.facebook.ads.internal.w.h.a.b(context, "generic", com.facebook.ads.internal.w.h.b.C, ex);
            }
            return -1L;
        }
    }
}
