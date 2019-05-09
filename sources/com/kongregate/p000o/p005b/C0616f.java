package com.kongregate.p000o.p005b;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import com.kongregate.android.internal.util.C0562j;
import com.kongregate.android.internal.util.StringUtils;
import com.kongregate.p000o.p002g.C0645b;
import com.kongregate.p000o.p002g.C0645b.C0468a;
import com.kongregate.p000o.p002g.C0646c;
import com.kongregate.p000o.p004e.C0636a;
import com.kongregate.p000o.p006c.C0626d;
import java.util.Collection;
import java.util.LinkedHashSet;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.kongregate.o.b.f */
public class C0616f implements BaseColumns {
    /* renamed from: a */
    public static final String f946a = "user_badges";
    /* renamed from: b */
    public static final String f947b = "user_id";
    /* renamed from: c */
    public static final String f948c = "badge_id";

    /* renamed from: b */
    private static void m952b(SQLiteDatabase sQLiteDatabase, long j, Collection<Long> collection) {
        Cursor query;
        Throwable th;
        sQLiteDatabase.beginTransaction();
        try {
            String str = "_id IN (" + StringUtils.m562a((Iterable) collection, ",") + ") AND " + "_id" + " NOT IN (SELECT " + "badge_id" + " FROM " + f946a + " WHERE " + "user_id" + "=?)";
            String[] strArr = new String[]{String.valueOf(j)};
            query = sQLiteDatabase.query(C0603c.f884a, new String[]{"_id"}, str, strArr, null, null, null);
            if (query == null) {
                sQLiteDatabase.endTransaction();
                C0636a.m1040a(query);
                return;
            }
            try {
                query.moveToFirst();
                while (!query.isAfterLast()) {
                    C0616f.m949a(sQLiteDatabase, j, query.getLong(0));
                    query.moveToNext();
                }
                sQLiteDatabase.setTransactionSuccessful();
                sQLiteDatabase.endTransaction();
                C0636a.m1040a(query);
            } catch (Throwable th2) {
                th = th2;
                sQLiteDatabase.endTransaction();
                C0636a.m1040a(query);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            query = null;
            sQLiteDatabase.endTransaction();
            C0636a.m1040a(query);
            throw th;
        }
    }

    /* renamed from: a */
    static void m949a(SQLiteDatabase sQLiteDatabase, long j, long j2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_id", Long.valueOf(j));
        contentValues.put("badge_id", Long.valueOf(j2));
        if (C0636a.m1039a(sQLiteDatabase, f946a, contentValues) > 0) {
            C0562j.m756b("Created/updated user_badge, badge_id: " + j2 + ", user_id: " + j);
        }
    }

    /* renamed from: a */
    public static void m950a(final SQLiteDatabase sQLiteDatabase, final long j, String str) {
        C0645b.m1085a().m1090a("/accounts/" + str + "/user_mobile_badges.json", new C0468a() {
            /* renamed from: a */
            public void mo1133a(C0646c c0646c, JSONObject jSONObject) {
                JSONArray optJSONArray = jSONObject.optJSONArray("badge_ids");
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    final LinkedHashSet linkedHashSet = new LinkedHashSet();
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        linkedHashSet.add(Long.valueOf(optJSONArray.optLong(i)));
                    }
                    C0626d.m962a(new Runnable(this) {
                        /* renamed from: b */
                        final /* synthetic */ C06151 f943b;

                        public void run() {
                            C0616f.m952b(sQLiteDatabase, j, linkedHashSet);
                        }
                    });
                }
            }
        });
    }
}
