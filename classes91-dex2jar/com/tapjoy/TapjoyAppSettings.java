// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy;

import android.content.SharedPreferences$Editor;
import com.tapjoy.internal.ct;
import android.content.SharedPreferences;
import android.content.Context;

public class TapjoyAppSettings
{
    public static final String TAG;
    private static TapjoyAppSettings b;
    String a;
    private Context c;
    private SharedPreferences d;
    
    static {
        TAG = TapjoyAppSettings.class.getSimpleName();
    }
    
    private TapjoyAppSettings(final Context c) {
        this.c = c;
        this.d = this.c.getSharedPreferences("tjcPrefrences", 0);
        this.a = this.d.getString("tapjoyLogLevel", (String)null);
        if (!ct.c(this.a)) {
            TapjoyLog.d(TapjoyAppSettings.TAG, "restoreLoggingLevel from sharedPref -- loggingLevel=" + this.a);
            TapjoyLog.a(this.a, true);
        }
    }
    
    public static TapjoyAppSettings getInstance() {
        return TapjoyAppSettings.b;
    }
    
    public static void init(final Context context) {
        TapjoyLog.d(TapjoyAppSettings.TAG, "initializing app settings");
        TapjoyAppSettings.b = new TapjoyAppSettings(context);
    }
    
    public void clearLoggingLevel() {
        final SharedPreferences$Editor edit = this.d.edit();
        edit.remove("tapjoyLogLevel");
        edit.commit();
        this.a = null;
        final boolean loggingEnabled = TapjoyLog.isLoggingEnabled();
        final String tag = TapjoyAppSettings.TAG;
        final StringBuilder sb = new StringBuilder("Tapjoy remote device debugging 'Disabled'. The SDK Debug-setting is: ");
        String s;
        if (loggingEnabled) {
            s = "'Enabled'";
        }
        else {
            s = "'Disabled'";
        }
        TapjoyLog.i(tag, sb.append(s).toString());
        TapjoyLog.setDebugEnabled(loggingEnabled);
    }
    
    public String getConnectResult(final String s, final long n) {
        final String string = this.d.getString("connectResult", (String)null);
        if (!ct.c(string) && !ct.c(s) && s.equals(this.d.getString("connectParamsHash", (String)null))) {
            final long long1 = this.d.getLong("connectResultExpires", -1L);
            if (long1 < 0L || long1 >= n) {
                return string;
            }
        }
        return null;
    }
    
    public void removeConnectResult() {
        if (this.d.getString("connectParamsHash", (String)null) != null) {
            final SharedPreferences$Editor edit = this.d.edit();
            edit.remove("connectResult");
            edit.remove("connectParamsHash");
            edit.remove("connectResultExpires");
            TapjoyLog.i(TapjoyAppSettings.TAG, "Removed connect result");
            edit.commit();
        }
    }
    
    public void saveConnectResultAndParams(final String s, final String s2, final long n) {
        if (ct.c(s) || ct.c(s2)) {
            return;
        }
        final SharedPreferences$Editor edit = this.d.edit();
        edit.putString("connectResult", s);
        edit.putString("connectParamsHash", s2);
        if (n >= 0L) {
            edit.putLong("connectResultExpires", n);
        }
        else {
            edit.remove("connectResultExpires");
        }
        TapjoyLog.i(TapjoyAppSettings.TAG, "Stored connect result");
        edit.commit();
    }
    
    public void saveLoggingLevel(String a) {
        if (ct.c(a)) {
            TapjoyLog.d(TapjoyAppSettings.TAG, "saveLoggingLevel -- server logging level is NULL or Empty string");
            return;
        }
        TapjoyLog.d(TapjoyAppSettings.TAG, "saveLoggingLevel -- currentLevel=" + this.a + ";newLevel=" + a);
        if (ct.c(this.a) || !this.a.equals(a)) {
            final SharedPreferences$Editor edit = this.d.edit();
            edit.putString("tapjoyLogLevel", a);
            edit.commit();
            TapjoyLog.a(this.a = a, true);
        }
        final boolean loggingEnabled = TapjoyLog.isLoggingEnabled();
        final String tag = TapjoyAppSettings.TAG;
        final StringBuilder append = new StringBuilder("Tapjoy remote device debugging set to '").append(a).append("'. The SDK Debug-setting is: ");
        if (loggingEnabled) {
            a = "'Enabled'";
        }
        else {
            a = "'Disabled'";
        }
        TapjoyLog.i(tag, append.append(a).toString());
    }
}
