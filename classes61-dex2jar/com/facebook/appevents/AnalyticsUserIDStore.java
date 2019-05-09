// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.appevents;

import android.content.SharedPreferences$Editor;
import com.facebook.appevents.internal.AppEventUtility;
import android.preference.PreferenceManager;
import com.facebook.FacebookSdk;
import android.util.Log;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class AnalyticsUserIDStore
{
    private static final String ANALYTICS_USER_ID_KEY = "com.facebook.appevents.AnalyticsUserIDStore.userID";
    private static final String TAG;
    private static volatile boolean initialized;
    private static ReentrantReadWriteLock lock;
    private static String userID;
    
    static {
        TAG = AnalyticsUserIDStore.class.getSimpleName();
        AnalyticsUserIDStore.lock = new ReentrantReadWriteLock();
        AnalyticsUserIDStore.initialized = false;
    }
    
    public static String getUserID() {
        if (!AnalyticsUserIDStore.initialized) {
            Log.w(AnalyticsUserIDStore.TAG, "initStore should have been called before calling setUserID");
            initAndWait();
        }
        AnalyticsUserIDStore.lock.readLock().lock();
        try {
            return AnalyticsUserIDStore.userID;
        }
        finally {
            AnalyticsUserIDStore.lock.readLock().unlock();
        }
    }
    
    private static void initAndWait() {
        if (AnalyticsUserIDStore.initialized) {
            return;
        }
        AnalyticsUserIDStore.lock.writeLock().lock();
        try {
            if (AnalyticsUserIDStore.initialized) {
                return;
            }
            AnalyticsUserIDStore.userID = PreferenceManager.getDefaultSharedPreferences(FacebookSdk.getApplicationContext()).getString("com.facebook.appevents.AnalyticsUserIDStore.userID", (String)null);
            AnalyticsUserIDStore.initialized = true;
        }
        finally {
            AnalyticsUserIDStore.lock.writeLock().unlock();
        }
    }
    
    public static void initStore() {
        if (AnalyticsUserIDStore.initialized) {
            return;
        }
        AppEventsLogger.getAnalyticsExecutor().execute(new Runnable() {
            @Override
            public void run() {
                initAndWait();
            }
        });
    }
    
    public static void setUserID(final String s) {
        AppEventUtility.assertIsNotMainThread();
        if (!AnalyticsUserIDStore.initialized) {
            Log.w(AnalyticsUserIDStore.TAG, "initStore should have been called before calling setUserID");
            initAndWait();
        }
        AppEventsLogger.getAnalyticsExecutor().execute(new Runnable() {
            @Override
            public void run() {
                AnalyticsUserIDStore.lock.writeLock().lock();
                try {
                    AnalyticsUserIDStore.userID = s;
                    final SharedPreferences$Editor edit = PreferenceManager.getDefaultSharedPreferences(FacebookSdk.getApplicationContext()).edit();
                    edit.putString("com.facebook.appevents.AnalyticsUserIDStore.userID", AnalyticsUserIDStore.userID);
                    edit.apply();
                }
                finally {
                    AnalyticsUserIDStore.lock.writeLock().unlock();
                }
            }
        });
    }
}
