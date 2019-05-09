// 
// Decompiled by Procyon v0.5.34
// 

package com.adjust.sdk;

import org.json.JSONArray;
import org.json.JSONException;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager
{
    private static final int INDEX_CLICK_TIME = 1;
    private static final int INDEX_IS_SENDING = 2;
    private static final int INDEX_RAW_REFERRER = 0;
    private static final String PREFS_KEY_INSTALL_TRACKED = "install_tracked";
    private static final String PREFS_KEY_PUSH_TOKEN = "push_token";
    private static final String PREFS_KEY_RAW_REFERRERS = "raw_referrers";
    private static final String PREFS_NAME = "adjust_preferences";
    private final SharedPreferences sharedPreferences;
    
    public SharedPreferencesManager(final Context context) {
        this.sharedPreferences = context.getSharedPreferences("adjust_preferences", 0);
    }
    
    private boolean getBoolean(final String s, boolean boolean1) {
        synchronized (this) {
            try {
                boolean1 = this.sharedPreferences.getBoolean(s, boolean1);
                return boolean1;
            }
            catch (ClassCastException ex) {
                return boolean1;
            }
        }
    }
    
    private int getRawReferrerIndex(final String s, final long n) {
        while (true) {
            while (true) {
                int n2;
                synchronized (this) {
                    try {
                        final JSONArray rawReferrerArray = this.getRawReferrerArray();
                        n2 = 0;
                        if (n2 >= rawReferrerArray.length()) {
                            return -1;
                        }
                        final JSONArray jsonArray = rawReferrerArray.getJSONArray(n2);
                        final String optString = jsonArray.optString(0, (String)null);
                        if (optString != null) {
                            if (optString.equals(s)) {
                                if (jsonArray.optLong(1, -1L) == n) {
                                    return n2;
                                }
                            }
                        }
                    }
                    catch (JSONException ex) {
                        n2 = -1;
                        return n2;
                    }
                }
                ++n2;
                continue;
            }
        }
    }
    
    private String getString(String string) {
        final String s = null;
        synchronized (this) {
            try {
                string = this.sharedPreferences.getString(string, (String)null);
                return string;
            }
            catch (ClassCastException ex) {
                string = s;
                return string;
            }
        }
    }
    
    private void remove(final String s) {
        synchronized (this) {
            this.sharedPreferences.edit().remove(s).apply();
        }
    }
    
    private void saveBoolean(final String s, final boolean b) {
        synchronized (this) {
            this.sharedPreferences.edit().putBoolean(s, b).apply();
        }
    }
    
    private void saveString(final String s, final String s2) {
        synchronized (this) {
            this.sharedPreferences.edit().putString(s, s2).apply();
        }
    }
    
    public void clear() {
        synchronized (this) {
            this.sharedPreferences.edit().clear().apply();
        }
    }
    
    public boolean getInstallTracked() {
        synchronized (this) {
            return this.getBoolean("install_tracked", false);
        }
    }
    
    public String getPushToken() {
        synchronized (this) {
            return this.getString("push_token");
        }
    }
    
    public JSONArray getRawReferrer(final String s, final long n) {
        synchronized (this) {
            final int rawReferrerIndex = this.getRawReferrerIndex(s, n);
            if (rawReferrerIndex < 0) {
                return null;
            }
            try {
                return this.getRawReferrerArray().getJSONArray(rawReferrerIndex);
            }
            catch (JSONException ex) {}
            return null;
        }
    }
    
    public JSONArray getRawReferrerArray() {
        synchronized (this) {
            while (true) {
                try {
                    final String string = this.getString("raw_referrers");
                    if (string != null) {
                        return new JSONArray(string);
                    }
                }
                catch (JSONException ex) {}
                return new JSONArray();
            }
        }
    }
    
    public void removePushToken() {
        synchronized (this) {
            this.remove("push_token");
        }
    }
    
    public void removeRawReferrer(String rawReferrerArray, final long n) {
        // monitorenter(this)
        if (rawReferrerArray == null) {
            return;
        }
        try {
            if (rawReferrerArray.length() != 0) {
                final int rawReferrerIndex = this.getRawReferrerIndex(rawReferrerArray, n);
                if (rawReferrerIndex >= 0) {
                    rawReferrerArray = (String)this.getRawReferrerArray();
                    final JSONArray jsonArray = new JSONArray();
                    for (int i = 0; i < ((JSONArray)rawReferrerArray).length(); ++i) {
                        if (i != rawReferrerIndex) {
                            try {
                                jsonArray.put((Object)((JSONArray)rawReferrerArray).getJSONArray(i));
                            }
                            catch (JSONException ex) {}
                        }
                    }
                    this.saveString("raw_referrers", jsonArray.toString());
                }
            }
        }
        finally {
        }
        // monitorexit(this)
    }
    
    public void savePushToken(final String s) {
        synchronized (this) {
            this.saveString("push_token", s);
        }
    }
    
    public void saveRawReferrer(final String s, final long n) {
        synchronized (this) {
            try {
                final JSONArray rawReferrerArray = this.getRawReferrerArray();
                if (this.getRawReferrer(s, n) == null) {
                    final JSONArray jsonArray = new JSONArray();
                    jsonArray.put(0, (Object)s);
                    jsonArray.put(1, n);
                    jsonArray.put(2, 0);
                    rawReferrerArray.put((Object)jsonArray);
                    this.saveRawReferrerArray(rawReferrerArray);
                }
            }
            catch (JSONException ex) {}
        }
    }
    
    public void saveRawReferrerArray(final JSONArray jsonArray) {
        synchronized (this) {
            this.saveString("raw_referrers", jsonArray.toString());
        }
    }
    
    public void setInstallTracked() {
        synchronized (this) {
            this.saveBoolean("install_tracked", true);
        }
    }
    
    public void setSendingReferrersAsNotSent() {
        while (true) {
            while (true) {
                int n;
                synchronized (this) {
                    try {
                        final JSONArray rawReferrerArray = this.getRawReferrerArray();
                        boolean b = false;
                        n = 0;
                        if (n >= rawReferrerArray.length()) {
                            if (b) {
                                this.saveRawReferrerArray(rawReferrerArray);
                            }
                            return;
                        }
                        final JSONArray jsonArray = rawReferrerArray.getJSONArray(n);
                        if (jsonArray.optInt(2, -1) == 1) {
                            jsonArray.put(2, 0);
                            b = true;
                        }
                    }
                    catch (JSONException ex) {
                        return;
                    }
                }
                ++n;
                continue;
            }
        }
    }
}
