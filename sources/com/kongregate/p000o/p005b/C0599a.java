package com.kongregate.p000o.p005b;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import com.kongregate.android.internal.util.C0562j;
import com.kongregate.p000o.p004e.C0636a;
import com.kongregate.p000o.p004e.C0637b;
import com.kongregate.p000o.p006c.C0626d;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.kongregate.o.b.a */
public class C0599a implements BaseColumns {
    /* renamed from: a */
    public static final String f864a = "accomplishment_tasks";
    /* renamed from: b */
    public static final String f865b = "user_accomplishment_tasks_view";
    /* renamed from: c */
    public static final String f866c = "badge_id";
    /* renamed from: d */
    public static final String f867d = "description";
    /* renamed from: e */
    public static final String f868e = "name";
    /* renamed from: f */
    public static final String f869f = "quota";
    /* renamed from: g */
    public static final String f870g = "statistic_id";
    /* renamed from: h */
    public static final String f871h = "value";
    /* renamed from: i */
    public static final String f872i = "completed";

    /* renamed from: a */
    static void m914a(final long j, final JSONArray jSONArray) {
        if (jSONArray == null) {
            C0562j.m759c("null JSON passed into updateTasks");
        } else {
            C0626d.m962a(new Runnable() {
                public void run() {
                    SQLiteDatabase a = C0637b.m1044a();
                    a.beginTransaction();
                    int i = 0;
                    while (i < jSONArray.length()) {
                        try {
                            C0599a.m916b(a, j, jSONArray.optJSONObject(i));
                            i++;
                        } finally {
                            C0636a.m1040a(null);
                            a.endTransaction();
                        }
                    }
                    a.setTransactionSuccessful();
                }
            });
        }
    }

    /* renamed from: b */
    private static long m916b(SQLiteDatabase sQLiteDatabase, long j, JSONObject jSONObject) {
        if (jSONObject == null || !jSONObject.has("id")) {
            return -1;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("_id", Long.valueOf(jSONObject.optLong("id", 0)));
        contentValues.put("badge_id", Long.valueOf(j));
        contentValues.put("description", jSONObject.optString("description", ""));
        contentValues.put("name", jSONObject.optString("name", ""));
        contentValues.put(f869f, Long.valueOf(jSONObject.optLong(f869f, 0)));
        contentValues.put("statistic_id", Long.valueOf(jSONObject.optLong("statistic_id", 0)));
        long a = C0636a.m1039a(sQLiteDatabase, f864a, contentValues);
        if (a == -1) {
            C0562j.m759c("Failed to add accomplishment task, id: " + contentValues.get("_id"));
            return a;
        }
        C0562j.m753a("Task: " + contentValues.get("name"));
        return a;
    }

    /* renamed from: a */
    public static boolean m915a(SQLiteDatabase sQLiteDatabase, long j) {
        Throwable th;
        Cursor query;
        try {
            SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
            query = sQLiteDatabase2.query(f865b, new String[]{"completed"}, "_id=?", new String[]{String.valueOf(j)}, null, null, null);
            if (query != null) {
                try {
                    if (query.moveToFirst() && !query.isNull(0)) {
                        boolean z;
                        if (query.getInt(0) == 1) {
                            z = true;
                        } else {
                            z = false;
                        }
                        C0636a.m1040a(query);
                        return z;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    C0636a.m1040a(query);
                    throw th;
                }
            }
            C0636a.m1040a(query);
            return false;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            C0636a.m1040a(query);
            throw th;
        }
    }
}
