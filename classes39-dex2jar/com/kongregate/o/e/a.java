// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.o.e;

import java.util.Iterator;
import com.kongregate.android.internal.util.g;
import com.kongregate.android.internal.util.StringUtils;
import java.util.LinkedList;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import com.kongregate.android.internal.util.j;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class a
{
    public static long a(final SQLiteDatabase sqLiteDatabase, final String s, final ContentValues contentValues) {
        if (contentValues == null) {
            return -1L;
        }
        if (!contentValues.containsKey("_id") || contentValues.get("_id") == null) {
            return sqLiteDatabase.insertWithOnConflict(s, (String)null, contentValues, 5);
        }
        final long longValue = contentValues.getAsLong("_id");
        try {
            if (sqLiteDatabase.update(s, contentValues, "_id=?", new String[] { String.valueOf(longValue) }) <= 0) {
                return sqLiteDatabase.insertWithOnConflict(s, (String)null, contentValues, 4);
            }
            return longValue;
        }
        catch (SQLiteException ex) {
            j.c("Error while performing upsert", (Throwable)ex);
            return -1L;
        }
    }
    
    public static void a(final Cursor cursor) {
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
    }
    
    protected static void a(final c c, final SQLiteDatabase sqLiteDatabase) {
        j.c("Old database detected, rebuilding");
        final LinkedList<String> list = new LinkedList<String>();
        final Cursor rawQuery = sqLiteDatabase.rawQuery("SELECT name FROM sqlite_master WHERE type='table';", (String[])null);
        try {
            rawQuery.moveToFirst();
            while (!rawQuery.isAfterLast()) {
                final String string = rawQuery.getString(0);
                if (!StringUtils.a((CharSequence)string) && !string.startsWith("android")) {
                    list.add(string);
                }
                rawQuery.moveToNext();
            }
        }
        finally {
            if (rawQuery != null && !rawQuery.isClosed()) {
                rawQuery.close();
            }
        }
        if (rawQuery != null && !rawQuery.isClosed()) {
            rawQuery.close();
        }
        for (final String s : list) {
            if (!s.startsWith("sqlite")) {
                j.a("Dropping table: " + s);
                sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + s + ";");
            }
        }
        j.c("Clearing shared preferences");
        g.e().edit().clear().commit();
        j.a("Rebuilding database");
        final c c2;
        c2.a(sqLiteDatabase);
    }
    
    public static boolean a(SQLiteDatabase rawQuery, final String s, final String s2) {
        rawQuery = (SQLiteDatabase)rawQuery.rawQuery("PRAGMA table_info(" + s + ")", (String[])null);
        Label_0144: {
            try {
                ((Cursor)rawQuery).moveToFirst();
                Block_6: {
                    while (!((Cursor)rawQuery).isAfterLast()) {
                        if (StringUtils.b(((Cursor)rawQuery).getString(1), s2)) {
                            break Block_6;
                        }
                        ((Cursor)rawQuery).moveToNext();
                    }
                    break Label_0144;
                }
                j.b("Found column " + s2 + " in table " + s);
                return true;
            }
            finally {
                if (rawQuery != null && !((Cursor)rawQuery).isClosed()) {
                    ((Cursor)rawQuery).close();
                }
            }
        }
        if (rawQuery != null && !((Cursor)rawQuery).isClosed()) {
            ((Cursor)rawQuery).close();
        }
        return false;
    }
}
