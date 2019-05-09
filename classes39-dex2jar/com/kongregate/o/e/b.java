// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.o.e;

import android.database.Cursor;
import com.kongregate.android.internal.util.j;
import com.kongregate.android.internal.util.StringUtils;
import com.kongregate.android.internal.util.g;
import android.annotation.TargetApi;
import com.kongregate.android.internal.util.a;
import android.database.sqlite.SQLiteStatement;
import android.database.sqlite.SQLiteDatabase;

public class b
{
    private static volatile SQLiteDatabase a;
    
    static {
        b.a = null;
    }
    
    public static int a(String rawQuery) {
        final int n = 0;
        rawQuery = (String)a().rawQuery("SELECT COUNT (*) FROM " + rawQuery, (String[])null);
        try {
            int int1;
            if (((Cursor)rawQuery).moveToFirst()) {
                final int n2 = int1 = ((Cursor)rawQuery).getInt(0);
                if (rawQuery != null) {
                    int1 = n2;
                    if (!((Cursor)rawQuery).isClosed()) {
                        ((Cursor)rawQuery).close();
                        int1 = n2;
                    }
                }
            }
            else {
                int1 = n;
                if (rawQuery != null) {
                    int1 = n;
                    if (!((Cursor)rawQuery).isClosed()) {
                        ((Cursor)rawQuery).close();
                        return 0;
                    }
                }
            }
            return int1;
        }
        finally {
            if (rawQuery != null && !((Cursor)rawQuery).isClosed()) {
                ((Cursor)rawQuery).close();
            }
        }
    }
    
    public static SQLiteDatabase a() {
        return b.a;
    }
    
    public static SQLiteStatement a(String s, final String... array) {
        int i = 0;
        final StringBuilder sb = new StringBuilder(160);
        final StringBuilder sb2 = new StringBuilder(60);
        sb.append("INSERT OR REPLACE INTO ").append(s);
        sb.append(" (");
        final int length = array.length;
        int n = 0;
        while (i < length) {
            s = array[i];
            if (n != 0) {
                sb.append(",");
                sb2.append(",");
            }
            n = 1;
            sb.append(s);
            sb2.append("?");
            ++i;
        }
        sb.append(") VALUES (").append((CharSequence)sb2).append(");");
        return a().compileStatement(sb.toString());
    }
    
    @TargetApi(11)
    public static void a(final SQLiteDatabase a) {
        c(a);
        a.rawQuery("PRAGMA journal_mode=truncate", (String[])null).close();
        a.rawQuery("PRAGMA journal_size_limit=51200", (String[])null).close();
        a.rawQuery("PRAGMA cache_size=" + 512000L / a.getPageSize(), (String[])null).close();
        if (!a.isReadOnly() && a.a()) {
            a.enableWriteAheadLogging();
        }
        b.a = a;
    }
    
    public static void a(final SQLiteDatabase sqLiteDatabase, final int n) {
        a(sqLiteDatabase, g.e(n).split(";"));
    }
    
    public static void a(final SQLiteDatabase sqLiteDatabase, final String[] array) {
        for (int length = array.length, i = 0; i < length; ++i) {
            final String s = array[i];
            if (!StringUtils.c((CharSequence)s)) {
                sqLiteDatabase.execSQL(s);
            }
        }
    }
    
    public static void b(final SQLiteDatabase sqLiteDatabase) {
        j.b("Enabling foreign keys");
        sqLiteDatabase.rawQuery("PRAGMA foreign_keys=1;", (String[])null).close();
    }
    
    public static void c(final SQLiteDatabase sqLiteDatabase) {
        j.b("Disabling foreign keys");
        sqLiteDatabase.rawQuery("PRAGMA foreign_keys=0;", (String[])null).close();
    }
}
