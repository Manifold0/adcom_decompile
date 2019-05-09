package com.kongregate.p000o.p004e;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.kongregate.android.internal.util.C0558g;
import com.kongregate.android.internal.util.C0562j;
import com.kongregate.android.internal.util.StringUtils;
import com.tapjoy.TapjoyConstants;
import java.util.Iterator;
import java.util.LinkedList;

/* renamed from: com.kongregate.o.e.a */
public class C0636a {
    /* renamed from: a */
    public static void m1040a(Cursor cursor) {
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
    }

    /* renamed from: a */
    public static long m1039a(SQLiteDatabase sQLiteDatabase, String str, ContentValues contentValues) {
        if (contentValues == null) {
            return -1;
        }
        if (!contentValues.containsKey("_id") || contentValues.get("_id") == null) {
            return sQLiteDatabase.insertWithOnConflict(str, null, contentValues, 5);
        }
        try {
            return sQLiteDatabase.update(str, contentValues, "_id=?", new String[]{String.valueOf(contentValues.getAsLong("_id").longValue())}) <= 0 ? sQLiteDatabase.insertWithOnConflict(str, null, contentValues, 4) : contentValues.getAsLong("_id").longValue();
        } catch (Throwable e) {
            C0562j.m760c("Error while performing upsert", e);
            return -1;
        }
    }

    /* renamed from: a */
    public static boolean m1042a(SQLiteDatabase sQLiteDatabase, String str, String str2) {
        Cursor rawQuery = sQLiteDatabase.rawQuery("PRAGMA table_info(" + str + ")", null);
        try {
            rawQuery.moveToFirst();
            while (!rawQuery.isAfterLast()) {
                if (StringUtils.m585b(rawQuery.getString(1), str2)) {
                    C0562j.m756b("Found column " + str2 + " in table " + str);
                    return true;
                }
                rawQuery.moveToNext();
            }
            if (!(rawQuery == null || rawQuery.isClosed())) {
                rawQuery.close();
            }
            return false;
        } finally {
            if (!(rawQuery == null || rawQuery.isClosed())) {
                rawQuery.close();
            }
        }
    }

    /* renamed from: a */
    protected static void m1041a(C0526c c0526c, SQLiteDatabase sQLiteDatabase) {
        C0562j.m759c("Old database detected, rebuilding");
        LinkedList linkedList = new LinkedList();
        Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT name FROM sqlite_master WHERE type='table';", null);
        try {
            rawQuery.moveToFirst();
            while (!rawQuery.isAfterLast()) {
                CharSequence string = rawQuery.getString(0);
                if (!(StringUtils.m580a(string) || string.startsWith(TapjoyConstants.TJC_DEVICE_PLATFORM_TYPE))) {
                    linkedList.add(string);
                }
                rawQuery.moveToNext();
            }
            Iterator it = linkedList.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                if (!str.startsWith("sqlite")) {
                    C0562j.m753a("Dropping table: " + str);
                    sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + str + ";");
                }
            }
            C0562j.m759c("Clearing shared preferences");
            C0558g.m698e().edit().clear().commit();
            C0562j.m753a("Rebuilding database");
            c0526c.mo1229a(sQLiteDatabase);
        } finally {
            if (!(rawQuery == null || rawQuery.isClosed())) {
                rawQuery.close();
            }
        }
    }
}
