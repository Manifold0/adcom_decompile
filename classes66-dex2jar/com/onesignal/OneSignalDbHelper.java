// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import android.database.sqlite.SQLiteException;
import java.util.Iterator;
import android.database.Cursor;
import java.util.ArrayList;
import android.os.SystemClock;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase$CursorFactory;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

public class OneSignalDbHelper extends SQLiteOpenHelper
{
    private static final String COMMA_SEP = ",";
    private static final String DATABASE_NAME = "OneSignal.db";
    private static final int DATABASE_VERSION = 2;
    private static final int DB_OPEN_RETRY_BACKOFF = 400;
    private static final int DB_OPEN_RETRY_MAX = 5;
    private static final String INT_TYPE = " INTEGER";
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE notification (_id INTEGER PRIMARY KEY,notification_id TEXT,android_notification_id INTEGER,group_id TEXT,collapse_id TEXT,is_summary INTEGER DEFAULT 0,opened INTEGER DEFAULT 0,dismissed INTEGER DEFAULT 0,title TEXT,message TEXT,full_data TEXT,created_time TIMESTAMP DEFAULT (strftime('%s', 'now')));";
    private static final String[] SQL_INDEX_ENTRIES;
    private static final String TEXT_TYPE = " TEXT";
    private static OneSignalDbHelper sInstance;
    
    static {
        SQL_INDEX_ENTRIES = new String[] { "CREATE INDEX notification_notification_id_idx ON notification(notification_id); ", "CREATE INDEX notification_android_notification_id_idx ON notification(android_notification_id); ", "CREATE INDEX notification_group_id_idx ON notification(group_id); ", "CREATE INDEX notification_collapse_id_idx ON notification(collapse_id); ", "CREATE INDEX notification_created_time_idx ON notification(created_time); " };
    }
    
    private OneSignalDbHelper(final Context context) {
        super(context, "OneSignal.db", (SQLiteDatabase$CursorFactory)null, 2);
    }
    
    public static OneSignalDbHelper getInstance(final Context context) {
        synchronized (OneSignalDbHelper.class) {
            if (OneSignalDbHelper.sInstance == null) {
                OneSignalDbHelper.sInstance = new OneSignalDbHelper(context.getApplicationContext());
            }
            return OneSignalDbHelper.sInstance;
        }
    }
    
    private static void internalOnUpgrade(final SQLiteDatabase sqLiteDatabase, final int n, final int n2) {
        if (n < 2) {
            sqLiteDatabase.execSQL("ALTER TABLE notification ADD COLUMN collapse_id TEXT;");
            sqLiteDatabase.execSQL("CREATE INDEX notification_group_id_idx ON notification(group_id); ");
        }
    }
    
    SQLiteDatabase getReadableDbWithRetries() {
        // monitorenter(this)
        int n = 0;
        try {
            return this.getReadableDatabase();
        }
        catch (Throwable t) {
            ++n;
            if (n >= 5) {
                throw t;
            }
            SystemClock.sleep((long)(n * 400));
            return this.getReadableDatabase();
        }
    }
    
    SQLiteDatabase getWritableDbWithRetries() {
        // monitorenter(this)
        int n = 0;
        try {
            return this.getWritableDatabase();
        }
        catch (Throwable t) {
            ++n;
            if (n >= 5) {
                throw t;
            }
            SystemClock.sleep((long)(n * 400));
            return this.getWritableDatabase();
        }
    }
    
    public void onCreate(final SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE notification (_id INTEGER PRIMARY KEY,notification_id TEXT,android_notification_id INTEGER,group_id TEXT,collapse_id TEXT,is_summary INTEGER DEFAULT 0,opened INTEGER DEFAULT 0,dismissed INTEGER DEFAULT 0,title TEXT,message TEXT,full_data TEXT,created_time TIMESTAMP DEFAULT (strftime('%s', 'now')));");
        final String[] sql_INDEX_ENTRIES = OneSignalDbHelper.SQL_INDEX_ENTRIES;
        for (int length = sql_INDEX_ENTRIES.length, i = 0; i < length; ++i) {
            sqLiteDatabase.execSQL(sql_INDEX_ENTRIES[i]);
        }
    }
    
    public void onDowngrade(final SQLiteDatabase sqLiteDatabase, final int n, final int n2) {
        OneSignal.Log(OneSignal.LOG_LEVEL.WARN, "SDK version rolled back! Clearing OneSignal.db as it could be in an unexpected state.");
        final Cursor rawQuery = sqLiteDatabase.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", (String[])null);
        ArrayList list;
        try {
            list = new ArrayList<String>(rawQuery.getCount());
            while (rawQuery.moveToNext()) {
                list.add(rawQuery.getString(0));
            }
        }
        finally {
            rawQuery.close();
        }
        final SQLiteDatabase sqLiteDatabase2;
        for (final String s : list) {
            if (!s.startsWith("sqlite_")) {
                sqLiteDatabase2.execSQL("DROP TABLE IF EXISTS " + s);
            }
        }
        rawQuery.close();
        this.onCreate(sqLiteDatabase2);
    }
    
    public void onUpgrade(final SQLiteDatabase sqLiteDatabase, final int n, final int n2) {
        try {
            internalOnUpgrade(sqLiteDatabase, n, n2);
        }
        catch (SQLiteException ex) {
            OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error in upgrade, migration may have already run! Skipping!", (Throwable)ex);
        }
    }
}
