// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.w.b;

import android.text.TextUtils;
import android.support.annotation.Nullable;

public class a
{
    private static boolean a;
    private static boolean b;
    
    static {
        com.facebook.ads.internal.w.b.a.a = false;
        com.facebook.ads.internal.w.b.a.b = false;
    }
    
    @Nullable
    public static String a(String property) {
        synchronized (a.class) {
            if (!a()) {
                property = null;
            }
            else {
                property = System.getProperty("fb.e2e." + property);
            }
            return property;
        }
    }
    
    public static boolean a() {
        synchronized (a.class) {
            if (!com.facebook.ads.internal.w.b.a.b) {
                com.facebook.ads.internal.w.b.a.a = "true".equals(System.getProperty("fb.running_e2e"));
                com.facebook.ads.internal.w.b.a.b = true;
            }
            return com.facebook.ads.internal.w.b.a.a;
        }
    }
    
    public static boolean b(final String s) {
        synchronized (a.class) {
            return !TextUtils.isEmpty((CharSequence)a(s));
        }
    }
}
