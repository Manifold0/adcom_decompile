// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import com.onesignal.shortcutbadger.ShortcutBadger;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.content.Context;

class BadgeCountUpdater
{
    private static int badgesEnabled;
    
    static {
        BadgeCountUpdater.badgesEnabled = -1;
    }
    
    private static boolean areBadgeSettingsEnabled(final Context context) {
        if (BadgeCountUpdater.badgesEnabled != -1) {
            if (BadgeCountUpdater.badgesEnabled != 1) {
                return false;
            }
        }
        else {
            while (true) {
                while (true) {
                    try {
                        final Bundle metaData = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
                        if (metaData != null) {
                            int badgesEnabled;
                            if ("DISABLE".equals(metaData.getString("com.onesignal.BadgeCount"))) {
                                badgesEnabled = 0;
                            }
                            else {
                                badgesEnabled = 1;
                            }
                            BadgeCountUpdater.badgesEnabled = badgesEnabled;
                        }
                        else {
                            BadgeCountUpdater.badgesEnabled = 1;
                        }
                        if (BadgeCountUpdater.badgesEnabled != 1) {
                            return false;
                        }
                        break;
                    }
                    catch (Throwable t) {
                        BadgeCountUpdater.badgesEnabled = 0;
                        OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error reading meta-data tag 'com.onesignal.BadgeCount'. Disabling badge setting.", t);
                        continue;
                    }
                    continue;
                }
            }
        }
        return true;
    }
    
    private static boolean areBadgesEnabled(final Context context) {
        return areBadgeSettingsEnabled(context) && OSUtils.areNotificationsEnabled(context);
    }
    
    static void update(final SQLiteDatabase sqLiteDatabase, final Context context) {
        if (!areBadgesEnabled(context)) {
            return;
        }
        final Cursor query = sqLiteDatabase.query("notification", (String[])null, "dismissed = 0 AND opened = 0 AND is_summary = 0 ", (String[])null, (String)null, (String)null, (String)null);
        updateCount(query.getCount(), context);
        query.close();
    }
    
    static void updateCount(final int n, final Context context) {
        if (!areBadgeSettingsEnabled(context)) {
            return;
        }
        try {
            ShortcutBadger.applyCountOrThrow(context, n);
        }
        catch (Throwable t) {}
    }
}
