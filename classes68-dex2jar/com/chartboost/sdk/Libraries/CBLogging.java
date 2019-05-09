// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.Libraries;

import android.util.Log;

public final class CBLogging
{
    public static Level a;
    
    static {
        CBLogging.a = Level.INTEGRATION;
    }
    
    public static void a(final String s, final String s2) {
        if (CBLogging.a == Level.ALL) {
            Log.d(s, s2);
        }
    }
    
    public static void a(final String s, final String s2, final Throwable t) {
        if (CBLogging.a == Level.ALL) {
            Log.e(s, s2, t);
        }
    }
    
    public static void b(final String s, final String s2) {
        if (CBLogging.a == Level.ALL) {
            Log.e(s, s2);
        }
    }
    
    public static void b(final String s, final String s2, final Throwable t) {
        if (CBLogging.a == Level.ALL) {
            Log.v(s, s2, t);
        }
    }
    
    public static void c(final String s, final String s2) {
        if (CBLogging.a == Level.ALL) {
            Log.v(s, s2);
        }
    }
    
    public static void c(final String s, final String s2, final Throwable t) {
        if (CBLogging.a == Level.ALL) {
            Log.w(s, s2, t);
        }
    }
    
    public static void d(final String s, final String s2) {
        if (CBLogging.a == Level.ALL) {
            Log.w(s, s2);
        }
    }
    
    public static void e(final String s, final String s2) {
        if (CBLogging.a == Level.ALL) {
            Log.i(s, s2);
        }
    }
    
    public enum Level
    {
        ALL, 
        INTEGRATION, 
        NONE;
    }
}
