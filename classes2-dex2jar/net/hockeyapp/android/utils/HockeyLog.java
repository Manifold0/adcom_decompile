// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android.utils;

import android.util.Log;

public class HockeyLog
{
    public static final String HOCKEY_TAG = "HockeyApp";
    private static int sLogLevel;
    
    static {
        HockeyLog.sLogLevel = 6;
    }
    
    public static void debug(final String s) {
        debug(null, s);
    }
    
    public static void debug(String sanitizeTag, final String s) {
        sanitizeTag = sanitizeTag(sanitizeTag);
        if (HockeyLog.sLogLevel <= 3) {
            Log.d(sanitizeTag, s);
        }
    }
    
    public static void debug(String sanitizeTag, final String s, final Throwable t) {
        sanitizeTag = sanitizeTag(sanitizeTag);
        if (HockeyLog.sLogLevel <= 3) {
            Log.d(sanitizeTag, s, t);
        }
    }
    
    public static void debug(final String s, final Throwable t) {
        debug(null, s, t);
    }
    
    public static void error(final String s) {
        error(null, s);
    }
    
    public static void error(String sanitizeTag, final String s) {
        sanitizeTag = sanitizeTag(sanitizeTag);
        if (HockeyLog.sLogLevel <= 6) {
            Log.e(sanitizeTag, s);
        }
    }
    
    public static void error(String sanitizeTag, final String s, final Throwable t) {
        sanitizeTag = sanitizeTag(sanitizeTag);
        if (HockeyLog.sLogLevel <= 6) {
            Log.e(sanitizeTag, s, t);
        }
    }
    
    public static void error(final String s, final Throwable t) {
        error(null, s, t);
    }
    
    public static int getLogLevel() {
        return HockeyLog.sLogLevel;
    }
    
    public static void info(final String s) {
        info(null, s);
    }
    
    public static void info(String sanitizeTag, final String s) {
        sanitizeTag = sanitizeTag(sanitizeTag);
        if (HockeyLog.sLogLevel <= 4) {
            Log.i(sanitizeTag, s);
        }
    }
    
    public static void info(String sanitizeTag, final String s, final Throwable t) {
        sanitizeTag = sanitizeTag(sanitizeTag);
        if (HockeyLog.sLogLevel <= 4) {
            Log.i(sanitizeTag, s, t);
        }
    }
    
    public static void info(final String s, final Throwable t) {
        info(s, t);
    }
    
    static String sanitizeTag(final String s) {
        if (s != null && s.length() != 0) {
            final String s2 = s;
            if (s.length() <= 23) {
                return s2;
            }
        }
        return "HockeyApp";
    }
    
    public static void setLogLevel(final int sLogLevel) {
        HockeyLog.sLogLevel = sLogLevel;
    }
    
    public static void verbose(final String s) {
        verbose(null, s);
    }
    
    public static void verbose(String sanitizeTag, final String s) {
        sanitizeTag = sanitizeTag(sanitizeTag);
        if (HockeyLog.sLogLevel <= 2) {
            Log.v(sanitizeTag, s);
        }
    }
    
    public static void verbose(String sanitizeTag, final String s, final Throwable t) {
        sanitizeTag = sanitizeTag(sanitizeTag);
        if (HockeyLog.sLogLevel <= 2) {
            Log.v(sanitizeTag, s, t);
        }
    }
    
    public static void verbose(final String s, final Throwable t) {
        verbose(null, s, t);
    }
    
    public static void warn(final String s) {
        warn(null, s);
    }
    
    public static void warn(String sanitizeTag, final String s) {
        sanitizeTag = sanitizeTag(sanitizeTag);
        if (HockeyLog.sLogLevel <= 5) {
            Log.w(sanitizeTag, s);
        }
    }
    
    public static void warn(String sanitizeTag, final String s, final Throwable t) {
        sanitizeTag = sanitizeTag(sanitizeTag);
        if (HockeyLog.sLogLevel <= 5) {
            Log.w(sanitizeTag, s, t);
        }
    }
    
    public static void warn(final String s, final Throwable t) {
        warn(null, s, t);
    }
}
