package com.tapjoy;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.tapjoy.internal.ct;

public class TapjoyAppSettings {
    public static final String TAG = TapjoyAppSettings.class.getSimpleName();
    /* renamed from: b */
    private static TapjoyAppSettings f7002b;
    /* renamed from: a */
    String f7003a = this.f7005d.getString(TapjoyConstants.PREF_LOG_LEVEL, null);
    /* renamed from: c */
    private Context f7004c;
    /* renamed from: d */
    private SharedPreferences f7005d = this.f7004c.getSharedPreferences(TapjoyConstants.TJC_PREFERENCE, 0);

    private TapjoyAppSettings(Context applicationContext) {
        this.f7004c = applicationContext;
        if (!ct.m7339c(this.f7003a)) {
            TapjoyLog.m7126d(TAG, "restoreLoggingLevel from sharedPref -- loggingLevel=" + this.f7003a);
            TapjoyLog.m7125a(this.f7003a, true);
        }
    }

    public static TapjoyAppSettings getInstance() {
        return f7002b;
    }

    public static void init(Context applicationContext) {
        TapjoyLog.m7126d(TAG, "initializing app settings");
        f7002b = new TapjoyAppSettings(applicationContext);
    }

    public void saveLoggingLevel(String level) {
        if (ct.m7339c(level)) {
            TapjoyLog.m7126d(TAG, "saveLoggingLevel -- server logging level is NULL or Empty string");
            return;
        }
        TapjoyLog.m7126d(TAG, "saveLoggingLevel -- currentLevel=" + this.f7003a + ";newLevel=" + level);
        if (ct.m7339c(this.f7003a) || !this.f7003a.equals(level)) {
            Editor edit = this.f7005d.edit();
            edit.putString(TapjoyConstants.PREF_LOG_LEVEL, level);
            edit.commit();
            this.f7003a = level;
            TapjoyLog.m7125a(this.f7003a, true);
        }
        TapjoyLog.m7129i(TAG, "Tapjoy remote device debugging set to '" + level + "'. The SDK Debug-setting is: " + (TapjoyLog.isLoggingEnabled() ? "'Enabled'" : "'Disabled'"));
    }

    public void clearLoggingLevel() {
        Editor edit = this.f7005d.edit();
        edit.remove(TapjoyConstants.PREF_LOG_LEVEL);
        edit.commit();
        this.f7003a = null;
        boolean isLoggingEnabled = TapjoyLog.isLoggingEnabled();
        TapjoyLog.m7129i(TAG, "Tapjoy remote device debugging 'Disabled'. The SDK Debug-setting is: " + (isLoggingEnabled ? "'Enabled'" : "'Disabled'"));
        TapjoyLog.setDebugEnabled(isLoggingEnabled);
    }

    public void saveConnectResultAndParams(String result, String paramsHash, long expires) {
        if (!ct.m7339c(result) && !ct.m7339c(paramsHash)) {
            Editor edit = this.f7005d.edit();
            edit.putString(TapjoyConstants.PREF_LAST_CONNECT_RESULT, result);
            edit.putString(TapjoyConstants.PREF_LAST_CONNECT_PARAMS_HASH, paramsHash);
            if (expires >= 0) {
                edit.putLong(TapjoyConstants.PREF_LAST_CONNECT_RESULT_EXPIRES, expires);
            } else {
                edit.remove(TapjoyConstants.PREF_LAST_CONNECT_RESULT_EXPIRES);
            }
            TapjoyLog.m7129i(TAG, "Stored connect result");
            edit.commit();
        }
    }

    public void removeConnectResult() {
        if (this.f7005d.getString(TapjoyConstants.PREF_LAST_CONNECT_PARAMS_HASH, null) != null) {
            Editor edit = this.f7005d.edit();
            edit.remove(TapjoyConstants.PREF_LAST_CONNECT_RESULT);
            edit.remove(TapjoyConstants.PREF_LAST_CONNECT_PARAMS_HASH);
            edit.remove(TapjoyConstants.PREF_LAST_CONNECT_RESULT_EXPIRES);
            TapjoyLog.m7129i(TAG, "Removed connect result");
            edit.commit();
        }
    }

    public String getConnectResult(String paramsHash, long currentTimeMillis) {
        String string = this.f7005d.getString(TapjoyConstants.PREF_LAST_CONNECT_RESULT, null);
        if (ct.m7339c(string) || ct.m7339c(paramsHash) || !paramsHash.equals(this.f7005d.getString(TapjoyConstants.PREF_LAST_CONNECT_PARAMS_HASH, null))) {
            return null;
        }
        long j = this.f7005d.getLong(TapjoyConstants.PREF_LAST_CONNECT_RESULT_EXPIRES, -1);
        if (j < 0 || j >= currentTimeMillis) {
            return string;
        }
        return null;
    }
}
