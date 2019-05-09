// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.w.b;

import android.util.Log;
import java.util.Map;
import android.app.KeyguardManager;
import android.content.Context;

public class z
{
    private static final String a;
    
    static {
        a = z.class.getSimpleName();
    }
    
    public static boolean a(final Context context) {
        final KeyguardManager keyguardManager = (KeyguardManager)context.getSystemService("keyguard");
        return keyguardManager != null && keyguardManager.inKeyguardRestrictedInputMode();
    }
    
    public static boolean a(final Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            Log.v(z.a, "Invalid Window info in window interactive check, assuming is not a Lockscreen.");
            return false;
        }
        final String s = map.get("wfdkg");
        final String s2 = map.get("wfswl");
        final String s3 = map.get("kgr");
        return s != null && s.equals("1") && s2 != null && s2.equals("1") && s3 != null && s3.equals("true");
    }
    
    public static boolean b(final Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            Log.v(z.a, "Invalid Window info in window interactive check, assuming not obstructed by Keyguard.");
        }
        else {
            final String s = map.get("wfdkg");
            final String s2 = map.get("wfswl");
            if ((s == null || !s.equals("1")) && (s2 == null || !s2.equals("1"))) {
                final String s3 = map.get("kgr");
                return s3 != null && s3.equals("true");
            }
        }
        return false;
    }
}
