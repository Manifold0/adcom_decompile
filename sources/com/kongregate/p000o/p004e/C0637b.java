package com.kongregate.p000o.p004e;

import android.annotation.TargetApi;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import com.kongregate.android.internal.util.C0542a;
import com.kongregate.android.internal.util.C0558g;
import com.kongregate.android.internal.util.C0562j;
import com.kongregate.android.internal.util.StringUtils;

/* renamed from: com.kongregate.o.e.b */
public class C0637b {
    /* renamed from: a */
    private static volatile SQLiteDatabase f1001a = null;

    /* renamed from: a */
    public static SQLiteDatabase m1044a() {
        return f1001a;
    }

    @TargetApi(11)
    /* renamed from: a */
    public static void m1046a(SQLiteDatabase sQLiteDatabase) {
        C0637b.m1050c(sQLiteDatabase);
        sQLiteDatabase.rawQuery("PRAGMA journal_mode=truncate", null).close();
        sQLiteDatabase.rawQuery("PRAGMA journal_size_limit=51200", null).close();
        sQLiteDatabase.rawQuery("PRAGMA cache_size=" + (512000 / sQLiteDatabase.getPageSize()), null).close();
        if (!sQLiteDatabase.isReadOnly() && C0542a.m605a()) {
            sQLiteDatabase.enableWriteAheadLogging();
        }
        f1001a = sQLiteDatabase;
    }

    /* renamed from: b */
    public static void m1049b(SQLiteDatabase sQLiteDatabase) {
        C0562j.m756b("Enabling foreign keys");
        sQLiteDatabase.rawQuery("PRAGMA foreign_keys=1;", null).close();
    }

    /* renamed from: c */
    public static void m1050c(SQLiteDatabase sQLiteDatabase) {
        C0562j.m756b("Disabling foreign keys");
        sQLiteDatabase.rawQuery("PRAGMA foreign_keys=0;", null).close();
    }

    /* renamed from: a */
    public static void m1048a(SQLiteDatabase sQLiteDatabase, String[] strArr) {
        for (CharSequence charSequence : strArr) {
            if (!StringUtils.m587c(charSequence)) {
                sQLiteDatabase.execSQL(charSequence);
            }
        }
    }

    /* renamed from: a */
    public static void m1047a(SQLiteDatabase sQLiteDatabase, int i) {
        C0637b.m1048a(sQLiteDatabase, C0558g.m699e(i).split(";"));
    }

    /* renamed from: a */
    public static int m1043a(String str) {
        int i = 0;
        Cursor rawQuery = C0637b.m1044a().rawQuery("SELECT COUNT (*) FROM " + str, null);
        try {
            if (rawQuery.moveToFirst()) {
                i = rawQuery.getInt(0);
            } else if (!(rawQuery == null || rawQuery.isClosed())) {
                rawQuery.close();
            }
            return i;
        } finally {
            if (!(rawQuery == null || rawQuery.isClosed())) {
                rawQuery.close();
            }
        }
    }

    /* renamed from: a */
    public static SQLiteStatement m1045a(String str, String... strArr) {
        int i = 0;
        StringBuilder stringBuilder = new StringBuilder(160);
        CharSequence stringBuilder2 = new StringBuilder(60);
        stringBuilder.append("INSERT OR REPLACE INTO ").append(str);
        stringBuilder.append(" (");
        int length = strArr.length;
        int i2 = 0;
        while (i < length) {
            String str2 = strArr[i];
            if (i2 != 0) {
                stringBuilder.append(",");
                stringBuilder2.append(",");
            }
            i2 = 1;
            stringBuilder.append(str2);
            stringBuilder2.append("?");
            i++;
        }
        stringBuilder.append(") VALUES (").append(stringBuilder2).append(");");
        return C0637b.m1044a().compileStatement(stringBuilder.toString());
    }
}
