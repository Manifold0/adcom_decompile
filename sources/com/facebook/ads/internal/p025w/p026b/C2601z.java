package com.facebook.ads.internal.p025w.p026b;

import android.app.KeyguardManager;
import android.content.Context;
import android.util.Log;
import java.util.Map;

/* renamed from: com.facebook.ads.internal.w.b.z */
public class C2601z {
    /* renamed from: a */
    private static final String f6422a = C2601z.class.getSimpleName();

    /* renamed from: a */
    public static boolean m6691a(Context context) {
        KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
        return keyguardManager != null && keyguardManager.inKeyguardRestrictedInputMode();
    }

    /* renamed from: a */
    public static boolean m6692a(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            Log.v(f6422a, "Invalid Window info in window interactive check, assuming is not a Lockscreen.");
            return false;
        }
        String str = (String) map.get("wfdkg");
        String str2 = (String) map.get("wfswl");
        String str3 = (String) map.get("kgr");
        return str != null && str.equals("1") && str2 != null && str2.equals("1") && str3 != null && str3.equals("true");
    }

    /* renamed from: b */
    public static boolean m6693b(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            Log.v(f6422a, "Invalid Window info in window interactive check, assuming not obstructed by Keyguard.");
            return false;
        }
        String str = (String) map.get("wfdkg");
        String str2 = (String) map.get("wfswl");
        if (str != null && str.equals("1")) {
            return false;
        }
        if (str2 != null && str2.equals("1")) {
            return false;
        }
        str = (String) map.get("kgr");
        boolean z = str != null && str.equals("true");
        return z;
    }
}
