// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android.utils;

import android.content.SharedPreferences$Editor;
import android.content.Context;

public class VersionCache
{
    private static String PREF_VERSION_INFO_KEY;
    
    static {
        VersionCache.PREF_VERSION_INFO_KEY = "versionInfo";
    }
    
    public static String getVersionInfo(final Context context) {
        if (context != null) {
            return context.getSharedPreferences("HockeyApp", 0).getString(VersionCache.PREF_VERSION_INFO_KEY, "[]");
        }
        return "[]";
    }
    
    public static void setVersionInfo(final Context context, final String s) {
        if (context != null) {
            final SharedPreferences$Editor edit = context.getSharedPreferences("HockeyApp", 0).edit();
            edit.putString(VersionCache.PREF_VERSION_INFO_KEY, s);
            edit.apply();
        }
    }
}
