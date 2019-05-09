// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.sdk.utils;

import android.text.TextUtils;
import com.ironsource.sdk.data.SSAEnums;
import android.util.Log;

public class Logger
{
    private static boolean enableLogging;
    
    public static void d(final String s, final String s2) {
        if (Logger.enableLogging) {
            Log.d(s, s2);
        }
    }
    
    public static void d(final String s, final String s2, final Throwable t) {
        if (Logger.enableLogging) {
            Log.d(s, s2, t);
        }
    }
    
    public static void e(final String s, final String s2) {
        if (Logger.enableLogging) {
            Log.e(s, s2);
        }
    }
    
    public static void e(final String s, final String s2, final Throwable t) {
        if (Logger.enableLogging) {
            Log.e(s, s2, t);
        }
    }
    
    public static void enableLogging(final int n) {
        if (SSAEnums.DebugMode.MODE_0.getValue() == n) {
            Logger.enableLogging = false;
            return;
        }
        Logger.enableLogging = true;
    }
    
    public static void i(final String s, final String s2) {
        if (Logger.enableLogging) {
            Log.i(s, s2);
        }
    }
    
    public static void i(final String s, final String s2, final Throwable t) {
        if (Logger.enableLogging && !TextUtils.isEmpty((CharSequence)s2)) {
            Log.i(s, s2, t);
        }
    }
    
    public static void v(final String s, final String s2) {
        if (Logger.enableLogging) {
            Log.v(s, s2);
        }
    }
    
    public static void v(final String s, final String s2, final Throwable t) {
        if (Logger.enableLogging) {
            Log.v(s, s2, t);
        }
    }
    
    public static void w(final String s, final String s2) {
        if (Logger.enableLogging) {
            Log.w(s, s2);
        }
    }
    
    public static void w(final String s, final String s2, final Throwable t) {
        if (Logger.enableLogging) {
            Log.w(s, s2, t);
        }
    }
}
