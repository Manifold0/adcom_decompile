// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import org.json.JSONException;
import org.json.JSONObject;
import android.content.ContentValues;
import android.app.NotificationManager;
import android.content.Context;
import android.database.Cursor;
import java.io.Serializable;
import android.database.sqlite.SQLiteDatabase;

class NotificationSummaryManager
{
    private static Integer getSummaryNotificationId(final SQLiteDatabase sqLiteDatabase, String s) {
        final Serializable s2 = null;
        Cursor cursor = null;
        Cursor cursor2 = null;
        Serializable value = s2;
        try {
            final Cursor query = sqLiteDatabase.query("notification", new String[] { "android_notification_id" }, "group_id = ? AND dismissed = 0 AND opened = 0 AND is_summary = 1", new String[] { (String)s }, (String)null, (String)null, (String)null);
            value = s2;
            cursor2 = query;
            cursor = query;
            if (!query.moveToFirst()) {
                value = s2;
                cursor2 = query;
                cursor = query;
                query.close();
                return null;
            }
            value = s2;
            cursor2 = query;
            cursor = query;
            final Integer n = (Integer)(value = query.getInt(query.getColumnIndex("android_notification_id")));
            cursor2 = query;
            cursor = query;
            query.close();
            s = n;
            if (query != null) {
                s = n;
                if (!query.isClosed()) {
                    query.close();
                    s = n;
                }
            }
            return (Integer)s;
        }
        catch (Throwable t) {
            cursor = cursor2;
            OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error getting android notification id for summary notification group: " + (String)s, t);
            s = value;
            if (cursor2 == null) {
                return (Integer)s;
            }
            s = value;
            if (!cursor2.isClosed()) {
                cursor2.close();
                s = value;
                return (Integer)s;
            }
            return (Integer)s;
        }
        finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
    }
    
    private static Cursor internalUpdateSummaryNotificationAfterChildRemoved(final Context context, final SQLiteDatabase sqLiteDatabase, final String s, final boolean b) {
        final Cursor query = sqLiteDatabase.query("notification", new String[] { "android_notification_id", "created_time" }, "group_id = ? AND dismissed = 0 AND opened = 0 AND is_summary = 0", new String[] { s }, (String)null, (String)null, "_id DESC");
        final int count = query.getCount();
        if (count == 0) {
            query.close();
            final Integer summaryNotificationId = getSummaryNotificationId(sqLiteDatabase, s);
            if (summaryNotificationId != null) {
                ((NotificationManager)context.getSystemService("notification")).cancel((int)summaryNotificationId);
                final ContentValues contentValues = new ContentValues();
                String s2;
                if (b) {
                    s2 = "dismissed";
                }
                else {
                    s2 = "opened";
                }
                contentValues.put(s2, Integer.valueOf(1));
                sqLiteDatabase.update("notification", contentValues, "android_notification_id = " + summaryNotificationId, (String[])null);
                return query;
            }
        }
        else if (count == 1) {
            query.close();
            if (getSummaryNotificationId(sqLiteDatabase, s) != null) {
                restoreSummary(context, s);
                return query;
            }
        }
        else {
            try {
                query.moveToFirst();
                final long long1 = query.getLong(query.getColumnIndex("created_time"));
                query.close();
                if (getSummaryNotificationId(sqLiteDatabase, s) != null) {
                    final NotificationGenerationJob notificationGenerationJob = new NotificationGenerationJob(context);
                    notificationGenerationJob.restoring = true;
                    notificationGenerationJob.shownTimeStamp = long1;
                    final JSONObject jsonPayload = new JSONObject();
                    jsonPayload.put("grp", (Object)s);
                    notificationGenerationJob.jsonPayload = jsonPayload;
                    GenerateNotification.updateSummaryNotification(notificationGenerationJob);
                    return query;
                }
            }
            catch (JSONException ex) {
                return query;
            }
        }
        return query;
    }
    
    private static void restoreSummary(final Context context, final String s) {
        final OneSignalDbHelper instance = OneSignalDbHelper.getInstance(context);
        Cursor cursor = null;
        Cursor query = null;
        try {
            NotificationRestorer.showNotifications(context, cursor = (query = instance.getReadableDbWithRetries().query("notification", NotificationRestorer.COLUMNS_FOR_RESTORE, "group_id = ? AND dismissed = 0 AND opened = 0 AND is_summary = 0", new String[] { s }, (String)null, (String)null, (String)null)), 0);
        }
        catch (Throwable t) {
            cursor = query;
            OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error restoring notification records! ", t);
        }
        finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
    }
    
    static void updatePossibleDependentSummaryOnDismiss(final Context context, final SQLiteDatabase sqLiteDatabase, final int n) {
        final Cursor query = sqLiteDatabase.query("notification", new String[] { "group_id" }, "android_notification_id = " + n, (String[])null, (String)null, (String)null, (String)null);
        if (query.moveToFirst()) {
            final String string = query.getString(query.getColumnIndex("group_id"));
            query.close();
            if (string != null) {
                updateSummaryNotificationAfterChildRemoved(context, sqLiteDatabase, string, true);
            }
            return;
        }
        query.close();
    }
    
    static void updateSummaryNotificationAfterChildRemoved(final Context context, final SQLiteDatabase sqLiteDatabase, final String s, final boolean b) {
        try {
            final Cursor internalUpdateSummaryNotificationAfterChildRemoved = internalUpdateSummaryNotificationAfterChildRemoved(context, sqLiteDatabase, s, b);
            if (internalUpdateSummaryNotificationAfterChildRemoved != null && !internalUpdateSummaryNotificationAfterChildRemoved.isClosed()) {
                internalUpdateSummaryNotificationAfterChildRemoved.close();
            }
        }
        catch (Throwable t) {
            OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error running updateSummaryNotificationAfterChildRemoved!", t);
        }
        finally {
            if (false) {
                throw new NullPointerException();
            }
        }
    }
}
