// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import android.content.SharedPreferences$Editor;
import java.util.Iterator;
import android.os.Handler;
import android.os.HandlerThread;
import android.content.SharedPreferences;
import java.util.HashMap;

class OneSignalPrefs
{
    static final String PREFS_EXISTING_PURCHASES = "ExistingPurchases";
    static final String PREFS_GT_APP_ID = "GT_APP_ID";
    static final String PREFS_GT_DO_NOT_SHOW_MISSING_GPS = "GT_DO_NOT_SHOW_MISSING_GPS";
    static final String PREFS_GT_FIREBASE_TRACKING_ENABLED = "GT_FIREBASE_TRACKING_ENABLED";
    static final String PREFS_GT_PLAYER_ID = "GT_PLAYER_ID";
    static final String PREFS_GT_REGISTRATION_ID = "GT_REGISTRATION_ID";
    static final String PREFS_GT_SOUND_ENABLED = "GT_SOUND_ENABLED";
    static final String PREFS_GT_UNSENT_ACTIVE_TIME = "GT_UNSENT_ACTIVE_TIME";
    static final String PREFS_GT_VIBRATE_ENABLED = "GT_VIBRATE_ENABLED";
    public static final String PREFS_ONESIGNAL;
    static final String PREFS_ONESIGNAL_ACCEPTED_NOTIFICATION_LAST = "ONESIGNAL_ACCEPTED_NOTIFICATION_LAST";
    static final String PREFS_ONESIGNAL_EMAIL_ADDRESS_LAST = "PREFS_ONESIGNAL_EMAIL_ADDRESS_LAST";
    static final String PREFS_ONESIGNAL_EMAIL_ID_LAST = "PREFS_ONESIGNAL_EMAIL_ID_LAST";
    static final String PREFS_ONESIGNAL_PERMISSION_ACCEPTED_LAST = "ONESIGNAL_PERMISSION_ACCEPTED_LAST";
    static final String PREFS_ONESIGNAL_PLAYER_ID_LAST = "ONESIGNAL_PLAYER_ID_LAST";
    static final String PREFS_ONESIGNAL_PUSH_TOKEN_LAST = "ONESIGNAL_PUSH_TOKEN_LAST";
    static final String PREFS_ONESIGNAL_SUBSCRIPTION = "ONESIGNAL_SUBSCRIPTION";
    static final String PREFS_ONESIGNAL_SUBSCRIPTION_LAST = "ONESIGNAL_SUBSCRIPTION_LAST";
    static final String PREFS_ONESIGNAL_SYNCED_SUBSCRIPTION = "ONESIGNAL_SYNCED_SUBSCRIPTION";
    static final String PREFS_ONESIGNAL_USERSTATE_DEPENDVALYES_ = "ONESIGNAL_USERSTATE_DEPENDVALYES_";
    static final String PREFS_ONESIGNAL_USERSTATE_SYNCVALYES_ = "ONESIGNAL_USERSTATE_SYNCVALYES_";
    static final String PREFS_ONESIGNAL_USER_PROVIDED_CONSENT = "ONESIGNAL_USER_PROVIDED_CONSENT";
    static final String PREFS_OS_EMAIL_ID = "OS_EMAIL_ID";
    static final String PREFS_OS_FILTER_OTHER_GCM_RECEIVERS = "OS_FILTER_OTHER_GCM_RECEIVERS";
    static final String PREFS_OS_LAST_LOCATION_TIME = "OS_LAST_LOCATION_TIME";
    static final String PREFS_OS_LAST_SESSION_TIME = "OS_LAST_SESSION_TIME";
    static final String PREFS_PLAYER_PURCHASES = "GTPlayerPurchases";
    static final String PREFS_PURCHASE_TOKENS = "purchaseTokens";
    public static WritePrefHandlerThread prefsHandler;
    static HashMap<String, HashMap<String, Object>> prefsToApply;
    
    static {
        PREFS_ONESIGNAL = OneSignal.class.getSimpleName();
        initializePool();
    }
    
    private static Object get(final String s, final String s2, final Class clazz, final Object o) {
        Object sharedPrefsByName = OneSignalPrefs.prefsToApply.get(s);
        synchronized (sharedPrefsByName) {
            if (clazz.equals(Object.class) && ((HashMap)sharedPrefsByName).containsKey(s2)) {
                // monitorexit(sharedPrefsByName)
                return true;
            }
            final Object value = ((HashMap<K, Object>)sharedPrefsByName).get(s2);
            if (value != null || ((HashMap)sharedPrefsByName).containsKey(s2)) {
                return value;
            }
            // monitorexit(sharedPrefsByName)
            sharedPrefsByName = getSharedPrefsByName(s);
            if (sharedPrefsByName == null) {
                return;
            }
            if (clazz.equals(String.class)) {
                return ((SharedPreferences)sharedPrefsByName).getString(s2, (String)o);
            }
        }
        if (clazz.equals(Boolean.class)) {
            return ((SharedPreferences)sharedPrefsByName).getBoolean(s2, (boolean)o);
        }
        if (clazz.equals(Integer.class)) {
            return ((SharedPreferences)sharedPrefsByName).getInt(s2, (int)o);
        }
        if (clazz.equals(Long.class)) {
            return ((SharedPreferences)sharedPrefsByName).getLong(s2, (long)o);
        }
        if (clazz.equals(Object.class)) {
            return ((SharedPreferences)sharedPrefsByName).contains(s2);
        }
        return null;
    }
    
    static boolean getBool(final String s, final String s2, final boolean b) {
        return (boolean)get(s, s2, Boolean.class, b);
    }
    
    static int getInt(final String s, final String s2, final int n) {
        return (int)get(s, s2, Integer.class, n);
    }
    
    static long getLong(final String s, final String s2, final long n) {
        return (long)get(s, s2, Long.class, n);
    }
    
    private static SharedPreferences getSharedPrefsByName(String string) {
        synchronized (OneSignalPrefs.class) {
            SharedPreferences sharedPreferences;
            if (OneSignal.appContext == null) {
                string = "OneSignal.appContext null, could not read " + string + " from getSharedPreferences.";
                OneSignal.Log(OneSignal.LOG_LEVEL.WARN, string, new Throwable());
                sharedPreferences = null;
            }
            else {
                sharedPreferences = OneSignal.appContext.getSharedPreferences(string, 0);
            }
            return sharedPreferences;
        }
    }
    
    static String getString(final String s, final String s2, final String s3) {
        return (String)get(s, s2, String.class, s3);
    }
    
    public static void initializePool() {
        (OneSignalPrefs.prefsToApply = new HashMap<String, HashMap<String, Object>>()).put(OneSignalPrefs.PREFS_ONESIGNAL, new HashMap<String, Object>());
        OneSignalPrefs.prefsToApply.put("GTPlayerPurchases", new HashMap<String, Object>());
        OneSignalPrefs.prefsHandler = new WritePrefHandlerThread();
    }
    
    private static void save(String s, final String s2, final Object o) {
        s = (String)OneSignalPrefs.prefsToApply.get(s);
        synchronized (s) {
            ((HashMap<String, Object>)s).put(s2, o);
            startDelayedWrite();
        }
    }
    
    public static void saveBool(final String s, final String s2, final boolean b) {
        save(s, s2, b);
    }
    
    public static void saveInt(final String s, final String s2, final int n) {
        save(s, s2, n);
    }
    
    public static void saveLong(final String s, final String s2, final long n) {
        save(s, s2, n);
    }
    
    public static void saveString(final String s, final String s2, final String s3) {
        save(s, s2, s3);
    }
    
    public static void startDelayedWrite() {
        OneSignalPrefs.prefsHandler.startDelayedWrite();
    }
    
    public static class WritePrefHandlerThread extends HandlerThread
    {
        private static final int WRITE_CALL_DELAY_TO_BUFFER_MS = 200;
        private long lastSyncTime;
        public Handler mHandler;
        
        WritePrefHandlerThread() {
            super("OSH_WritePrefs");
            this.lastSyncTime = 0L;
            this.start();
            this.mHandler = new Handler(this.getLooper());
        }
        
        private void flushBufferToDisk() {
            if (OneSignal.appContext == null) {
                return;
            }
            final Iterator<String> iterator = OneSignalPrefs.prefsToApply.keySet().iterator();
            while (iterator.hasNext()) {
                SharedPreferences$Editor edit;
                HashMap<String, Object> hashMap;
                while (true) {
                    final String s = iterator.next();
                    edit = getSharedPrefsByName(s).edit();
                    hashMap = OneSignalPrefs.prefsToApply.get(s);
                    while (true) {
                        String s2 = null;
                        Long value = null;
                        Label_0132: {
                            synchronized (hashMap) {
                                final Iterator<String> iterator2 = hashMap.keySet().iterator();
                                while (iterator2.hasNext()) {
                                    s2 = iterator2.next();
                                    value = hashMap.get(s2);
                                    if (!(value instanceof String)) {
                                        break Label_0132;
                                    }
                                    edit.putString(s2, (String)value);
                                }
                                break;
                            }
                        }
                        if (value instanceof Boolean) {
                            edit.putBoolean(s2, (boolean)(Boolean)(Object)value);
                            continue;
                        }
                        if (value instanceof Integer) {
                            edit.putInt(s2, (int)(Integer)(Object)value);
                            continue;
                        }
                        if (value instanceof Long) {
                            edit.putLong(s2, (long)value);
                            continue;
                        }
                        continue;
                    }
                }
                hashMap.clear();
                // monitorexit(hashMap)
                edit.apply();
            }
            this.lastSyncTime = System.currentTimeMillis();
        }
        
        private Runnable getNewRunnable() {
            return new Runnable() {
                @Override
                public void run() {
                    WritePrefHandlerThread.this.flushBufferToDisk();
                }
            };
        }
        
        void startDelayedWrite() {
            synchronized (this.mHandler) {
                this.mHandler.removeCallbacksAndMessages((Object)null);
                if (this.lastSyncTime == 0L) {
                    this.lastSyncTime = System.currentTimeMillis();
                }
                this.mHandler.postDelayed(this.getNewRunnable(), this.lastSyncTime - System.currentTimeMillis() + 200L);
            }
        }
    }
}
