// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.android.internal.util;

import android.util.Log;
import java.lang.reflect.Method;

public class b
{
    private static boolean a;
    private static Method b;
    
    public static void a(final int n, final String s, final String s2) {
        if (!a()) {
            Log.w("KONG", "Crashlytics not available. Be sure to initialize before using.");
            return;
        }
        try {
            com.kongregate.android.internal.util.b.b.invoke(null, n, s, s2);
        }
        catch (Exception ex) {
            Log.w("KONG", "exception logging to crashlytics");
        }
    }
    
    public static void a(final String s, final float n) {
        if (!a()) {
            Log.w("KONG", "Crashlytics not available - setFloat");
            return;
        }
        n.a(b(), "setFloat", new Class[] { String.class, Float.TYPE }, s, n);
    }
    
    public static void a(final String s, final int n) {
        if (!a()) {
            Log.w("KONG", "Crashlytics not available - setInt");
            return;
        }
        n.a(b(), "setInt", new Class[] { String.class, Integer.TYPE }, s, n);
    }
    
    public static void a(final String s, final String s2) {
        if (!a()) {
            Log.w("KONG", "Crashlytics not available - setString");
            return;
        }
        n.a(b(), "setString", new Class[] { String.class, String.class }, s, s2);
    }
    
    public static void a(final String s, final boolean b) {
        if (!a()) {
            Log.w("KONG", "Crashlytics not available - setBool");
            return;
        }
        n.a(b(), "setBool", new Class[] { String.class, Boolean.TYPE }, s, b);
    }
    
    public static boolean a() {
        if (com.kongregate.android.internal.util.b.a) {
            if (com.kongregate.android.internal.util.b.b == null) {
                return false;
            }
        }
        else {
            final Class b = b();
            while (true) {
                if (b == null) {
                    break Label_0053;
                }
                try {
                    com.kongregate.android.internal.util.b.b = b.getDeclaredMethod("log", Integer.TYPE, String.class, String.class);
                    com.kongregate.android.internal.util.b.a = true;
                    if (com.kongregate.android.internal.util.b.b == null) {
                        return false;
                    }
                }
                catch (Exception ex) {
                    Log.w("KONG", "Exception initializing CrashltyicsHelper:", (Throwable)ex);
                    continue;
                }
                break;
            }
        }
        return true;
    }
    
    private static Class b() {
        try {
            return Class.forName("com.crashlytics.android.Crashlytics");
        }
        catch (Exception ex) {
            Log.w("KONG", "Exception initializing CrashltyicsHelper:", (Throwable)ex);
            return null;
        }
    }
}
