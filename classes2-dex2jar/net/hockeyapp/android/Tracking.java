// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android;

import android.content.SharedPreferences$Editor;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.Context;

public class Tracking
{
    protected static final String START_TIME_KEY = "startTime";
    protected static final String USAGE_TIME_KEY = "usageTime";
    
    private static boolean checkVersion(final Context context) {
        if (Constants.APP_VERSION == null) {
            Constants.loadFromContext(context);
            if (Constants.APP_VERSION == null) {
                return false;
            }
        }
        return true;
    }
    
    protected static SharedPreferences getPreferences(final Context context) {
        return context.getSharedPreferences("HockeyApp", 0);
    }
    
    public static long getUsageTime(final Context context) {
        if (!checkVersion(context)) {
            return 0L;
        }
        final SharedPreferences preferences = getPreferences(context);
        final long long1 = preferences.getLong("usageTime" + Constants.APP_VERSION, 0L);
        if (long1 < 0L) {
            preferences.edit().remove("usageTime" + Constants.APP_VERSION).apply();
            return 0L;
        }
        return long1 / 1000L;
    }
    
    public static void startUsage(final Activity activity) {
        final long currentTimeMillis = System.currentTimeMillis();
        if (activity == null) {
            return;
        }
        final SharedPreferences$Editor edit = getPreferences((Context)activity).edit();
        edit.putLong("startTime" + activity.hashCode(), currentTimeMillis);
        edit.apply();
    }
    
    public static void stopUsage(final Activity activity) {
        final long currentTimeMillis = System.currentTimeMillis();
        if (activity != null && checkVersion((Context)activity)) {
            final SharedPreferences preferences = getPreferences((Context)activity);
            final long long1 = preferences.getLong("startTime" + activity.hashCode(), 0L);
            final long long2 = preferences.getLong("usageTime" + Constants.APP_VERSION, 0L);
            if (long1 > 0L) {
                final long n = currentTimeMillis - long1;
                final long n2 = long2 + n;
                if (n > 0L && n2 >= 0L) {
                    final SharedPreferences$Editor edit = preferences.edit();
                    edit.putLong("usageTime" + Constants.APP_VERSION, n2);
                    edit.apply();
                }
            }
        }
    }
}
