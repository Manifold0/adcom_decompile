package com.kongregate.android.internal.sdk;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* renamed from: com.kongregate.android.internal.sdk.s */
class C0529s {
    /* renamed from: a */
    private HashMap<String, C0505j> f671a = new HashMap();
    /* renamed from: b */
    private C0502g f672b;

    C0529s(Context context) {
        this.f672b = new C0502g(context);
    }

    /* renamed from: a */
    ArrayList<Bundle> m546a() {
        Throwable th;
        Cursor cursor;
        SQLiteDatabase sQLiteDatabase = null;
        ArrayList<Bundle> arrayList = new ArrayList();
        try {
            SQLiteDatabase readableDatabase = this.f672b.getReadableDatabase();
            try {
                Cursor query = readableDatabase.query("statistic_cache", null, null, null, null, null, null, null);
                try {
                    query.moveToFirst();
                    while (!query.isAfterLast()) {
                        arrayList.add(new C0505j(query).m454a());
                        query.moveToNext();
                    }
                    if (!(query == null || query.isClosed())) {
                        query.close();
                    }
                    if (readableDatabase != null && readableDatabase.isOpen()) {
                        readableDatabase.close();
                    }
                    return arrayList;
                } catch (Throwable th2) {
                    sQLiteDatabase = readableDatabase;
                    th = th2;
                    cursor = query;
                    if (!(cursor == null || cursor.isClosed())) {
                        cursor.close();
                    }
                    if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                        sQLiteDatabase.close();
                    }
                    throw th;
                }
            } catch (Throwable th22) {
                Throwable th3 = th22;
                cursor = null;
                sQLiteDatabase = readableDatabase;
                th = th3;
                cursor.close();
                sQLiteDatabase.close();
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            cursor = null;
            cursor.close();
            sQLiteDatabase.close();
            throw th;
        }
    }

    /* renamed from: a */
    void m547a(List<String> list) {
        if (list != null && !list.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder("(");
            for (String append : list) {
                stringBuilder.append("'").append(append).append("',");
            }
            String append2 = stringBuilder.substring(0, stringBuilder.lastIndexOf(",")) + ")";
            SQLiteDatabase writableDatabase = this.f672b.getWritableDatabase();
            Log.v(C0498e.f484a, "cleared " + writableDatabase.delete("statistic_cache", "NAME IN " + append2, null) + " rows from stats cache");
            writableDatabase.close();
        }
    }
}
