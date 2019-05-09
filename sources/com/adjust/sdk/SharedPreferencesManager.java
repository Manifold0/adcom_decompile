package com.adjust.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import org.json.JSONArray;
import org.json.JSONException;

public class SharedPreferencesManager {
    private static final int INDEX_CLICK_TIME = 1;
    private static final int INDEX_IS_SENDING = 2;
    private static final int INDEX_RAW_REFERRER = 0;
    private static final String PREFS_KEY_INSTALL_TRACKED = "install_tracked";
    private static final String PREFS_KEY_PUSH_TOKEN = "push_token";
    private static final String PREFS_KEY_RAW_REFERRERS = "raw_referrers";
    private static final String PREFS_NAME = "adjust_preferences";
    private final SharedPreferences sharedPreferences;

    public SharedPreferencesManager(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREFS_NAME, 0);
    }

    public synchronized void saveRawReferrer(String rawReferrer, long clickTime) {
        try {
            JSONArray rawReferrerArray = getRawReferrerArray();
            if (getRawReferrer(rawReferrer, clickTime) == null) {
                JSONArray newRawReferrer = new JSONArray();
                newRawReferrer.put(0, rawReferrer);
                newRawReferrer.put(1, clickTime);
                newRawReferrer.put(2, 0);
                rawReferrerArray.put(newRawReferrer);
                saveRawReferrerArray(rawReferrerArray);
            }
        } catch (JSONException e) {
        }
    }

    public synchronized void saveRawReferrerArray(JSONArray rawReferrerArray) {
        saveString(PREFS_KEY_RAW_REFERRERS, rawReferrerArray.toString());
    }

    public synchronized void removeRawReferrer(String rawReferrer, long clickTime) {
        if (rawReferrer != null) {
            if (rawReferrer.length() != 0) {
                int rawReferrerIndex = getRawReferrerIndex(rawReferrer, clickTime);
                if (rawReferrerIndex >= 0) {
                    JSONArray rawReferrerArray = getRawReferrerArray();
                    JSONArray updatedReferrers = new JSONArray();
                    for (int i = 0; i < rawReferrerArray.length(); i++) {
                        if (i != rawReferrerIndex) {
                            try {
                                updatedReferrers.put(rawReferrerArray.getJSONArray(i));
                            } catch (JSONException e) {
                            }
                        }
                    }
                    saveString(PREFS_KEY_RAW_REFERRERS, updatedReferrers.toString());
                }
            }
        }
    }

    public synchronized JSONArray getRawReferrer(String rawReferrer, long clickTime) {
        JSONArray jSONArray;
        int rawReferrerIndex = getRawReferrerIndex(rawReferrer, clickTime);
        if (rawReferrerIndex >= 0) {
            try {
                jSONArray = getRawReferrerArray().getJSONArray(rawReferrerIndex);
            } catch (JSONException e) {
            }
        }
        jSONArray = null;
        return jSONArray;
    }

    public synchronized JSONArray getRawReferrerArray() {
        JSONArray jSONArray;
        try {
            String referrerQueueString = getString(PREFS_KEY_RAW_REFERRERS);
            if (referrerQueueString != null) {
                jSONArray = new JSONArray(referrerQueueString);
            }
        } catch (JSONException e) {
        }
        jSONArray = new JSONArray();
        return jSONArray;
    }

    public synchronized void setSendingReferrersAsNotSent() {
        try {
            JSONArray rawReferrerArray = getRawReferrerArray();
            boolean hasRawReferrersBeenChanged = false;
            for (int i = 0; i < rawReferrerArray.length(); i++) {
                JSONArray rawReferrer = rawReferrerArray.getJSONArray(i);
                if (rawReferrer.optInt(2, -1) == 1) {
                    rawReferrer.put(2, 0);
                    hasRawReferrersBeenChanged = true;
                }
            }
            if (hasRawReferrersBeenChanged) {
                saveRawReferrerArray(rawReferrerArray);
            }
        } catch (JSONException e) {
        }
    }

    private synchronized int getRawReferrerIndex(String rawReferrer, long clickTime) {
        int i;
        try {
            JSONArray rawReferrers = getRawReferrerArray();
            i = 0;
            while (i < rawReferrers.length()) {
                JSONArray savedRawReferrer = rawReferrers.getJSONArray(i);
                String savedRawReferrerString = savedRawReferrer.optString(0, null);
                if (savedRawReferrerString != null && savedRawReferrerString.equals(rawReferrer) && savedRawReferrer.optLong(1, -1) == clickTime) {
                    break;
                }
                i++;
            }
        } catch (JSONException e) {
        }
        i = -1;
        return i;
    }

    public synchronized void savePushToken(String pushToken) {
        saveString(PREFS_KEY_PUSH_TOKEN, pushToken);
    }

    public synchronized String getPushToken() {
        return getString(PREFS_KEY_PUSH_TOKEN);
    }

    public synchronized void removePushToken() {
        remove(PREFS_KEY_PUSH_TOKEN);
    }

    public synchronized void setInstallTracked() {
        saveBoolean(PREFS_KEY_INSTALL_TRACKED, true);
    }

    public synchronized boolean getInstallTracked() {
        return getBoolean(PREFS_KEY_INSTALL_TRACKED, false);
    }

    public synchronized void clear() {
        this.sharedPreferences.edit().clear().apply();
    }

    private synchronized void saveString(String key, String value) {
        this.sharedPreferences.edit().putString(key, value).apply();
    }

    private synchronized void saveBoolean(String key, boolean value) {
        this.sharedPreferences.edit().putBoolean(key, value).apply();
    }

    private synchronized String getString(String key) {
        String str = null;
        synchronized (this) {
            try {
                str = this.sharedPreferences.getString(key, null);
            } catch (ClassCastException e) {
            }
        }
        return str;
    }

    private synchronized boolean getBoolean(String key, boolean defaultValue) {
        try {
            defaultValue = this.sharedPreferences.getBoolean(key, defaultValue);
        } catch (ClassCastException e) {
        }
        return defaultValue;
    }

    private synchronized void remove(String key) {
        this.sharedPreferences.edit().remove(key).apply();
    }
}
