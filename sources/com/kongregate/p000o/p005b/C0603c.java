package com.kongregate.p000o.p005b;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.kongregate.android.internal.util.C0561i;
import com.kongregate.android.internal.util.C0562j;
import com.kongregate.android.internal.util.StringUtils;
import com.kongregate.p000o.p004e.C0636a;
import com.kongregate.p000o.p004e.C0637b;
import com.kongregate.p000o.p006c.C0626d;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.kongregate.o.b.c */
public class C0603c implements BaseColumns {
    /* renamed from: a */
    public static final String f884a = "badges";
    /* renamed from: b */
    public static final String f885b = "user_badges_view";
    /* renamed from: c */
    public static final String f886c = "level";
    /* renamed from: d */
    public static final String f887d = "name";
    /* renamed from: e */
    public static final String f888e = "description";
    /* renamed from: f */
    public static final String f889f = "users_count";
    /* renamed from: g */
    public static final String f890g = "url";
    /* renamed from: h */
    public static final String f891h = "application_id";
    /* renamed from: i */
    public static final String f892i = "game_id";
    /* renamed from: j */
    public static final String f893j = "download_state";
    /* renamed from: k */
    public static final String f894k = "updated_at";
    /* renamed from: l */
    public static final String f895l = "_data";
    /* renamed from: m */
    public static final String f896m = "completed";
    /* renamed from: n */
    public static final String f897n = "CASE level WHEN 'easy' THEN 1 WHEN 'medium' THEN 2 WHEN 'hard' THEN 3 WHEN 'impossible' THEN 4 ELSE 5 END, name";

    /* renamed from: com.kongregate.o.b.c$a */
    public enum C0602a {
        EASY,
        MEDIUM,
        HARD,
        IMPOSSIBLE;

        /* renamed from: a */
        public static C0602a m917a(String str) {
            if (StringUtils.m587c((CharSequence) str)) {
                return EASY;
            }
            return C0602a.valueOf(StringUtils.m595g(str));
        }

        public String toString() {
            return StringUtils.m594f(name());
        }
    }

    /* renamed from: a */
    public static void m919a(final long j, final JSONArray jSONArray) {
        if (jSONArray == null) {
            C0562j.m759c("null JSON passed into updateBadges");
        } else {
            C0626d.m962a(new Runnable() {
                public void run() {
                    SQLiteDatabase a = C0637b.m1044a();
                    a.beginTransaction();
                    int i = 0;
                    while (i < jSONArray.length()) {
                        try {
                            C0603c.m921b(a, j, jSONArray.optJSONObject(i));
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
    private static long m921b(SQLiteDatabase sQLiteDatabase, long j, JSONObject jSONObject) {
        if (jSONObject == null || !jSONObject.has("id")) {
            return -1;
        }
        ContentValues contentValues = new ContentValues();
        long optLong = jSONObject.optLong("id", 0);
        contentValues.put("_id", Long.valueOf(optLong));
        contentValues.put("game_id", Long.valueOf(j));
        contentValues.put("description", jSONObject.optString("description", ""));
        contentValues.put("url", jSONObject.optString(MessengerShareContentUtility.IMAGE_URL, ""));
        contentValues.put("name", jSONObject.optString("name", ""));
        contentValues.put(f889f, Long.valueOf(jSONObject.optLong(f889f, 0)));
        contentValues.put(f894k, Long.valueOf(C0561i.m745b(jSONObject, "created_at")));
        contentValues.put("level", jSONObject.optString("level", "easy"));
        long a = C0636a.m1039a(sQLiteDatabase, f884a, contentValues);
        if (a == -1) {
            C0562j.m759c("Failed to add badge, id: " + contentValues.get("_id"));
            return a;
        }
        C0562j.m753a("Badge: " + contentValues.get("name"));
        C0599a.m914a(optLong, jSONObject.optJSONArray(C0599a.f864a));
        return a;
    }

    /* renamed from: a */
    static boolean m920a(SQLiteDatabase sQLiteDatabase, long j, long j2) {
        Throwable th;
        Cursor cursor;
        Cursor cursor2 = null;
        try {
            String[] strArr = new String[]{String.valueOf(j), String.valueOf(j2)};
            SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
            cursor2 = sQLiteDatabase2.query(C0616f.f946a, new String[]{"_id"}, "user_id=? AND badge_id=?", strArr, null, null, null);
            if (cursor2 != null) {
                try {
                    if (cursor2.moveToFirst()) {
                        C0636a.m1040a(cursor2);
                        return true;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    cursor = cursor2;
                    C0636a.m1040a(cursor);
                    throw th;
                }
            }
            C0636a.m1040a(cursor2);
            strArr = new String[]{String.valueOf(j2)};
            sQLiteDatabase2 = sQLiteDatabase;
            cursor = sQLiteDatabase2.query(C0599a.f865b, new String[]{"completed"}, "badge_id=? AND completed=0", strArr, null, null, null);
            if (cursor != null) {
                try {
                    if (!cursor.moveToFirst()) {
                        C0562j.m756b("Found " + cursor.getCount() + " incomplete tasks for badge " + j2);
                        C0616f.m949a(sQLiteDatabase, j, j2);
                        C0636a.m1040a(cursor);
                        return true;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    C0636a.m1040a(cursor);
                    throw th;
                }
            }
            C0636a.m1040a(cursor);
            return false;
        } catch (Throwable th4) {
            th = th4;
            cursor = cursor2;
            C0636a.m1040a(cursor);
            throw th;
        }
    }
}
