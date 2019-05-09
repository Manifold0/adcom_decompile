package com.kongregate.p000o.p005b;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import com.kongregate.android.internal.util.C0562j;
import com.kongregate.android.internal.util.StringUtils;
import com.kongregate.p000o.p004e.C0636a;
import com.kongregate.p000o.p004e.C0637b;
import com.kongregate.p000o.p006c.C0626d;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.kongregate.o.b.d */
public class C0607d implements BaseColumns {
    /* renamed from: a */
    public static final String f906a = "statistics";
    /* renamed from: b */
    public static final String f907b = "game_id";
    /* renamed from: c */
    public static final String f908c = "application_id";
    /* renamed from: d */
    public static final String f909d = "name";
    /* renamed from: e */
    public static final String f910e = "display_name";
    /* renamed from: f */
    public static final String f911f = "description";
    /* renamed from: g */
    public static final String f912g = "stat_type";
    /* renamed from: h */
    public static final String f913h = "display_in_table";

    /* renamed from: com.kongregate.o.b.d$a */
    public enum C0606a {
        MAX,
        MIN,
        REPLACE,
        ADD;

        /* renamed from: a */
        public long m924a(long j, long j2) {
            switch (this) {
                case ADD:
                    return j2 + j;
                case MAX:
                case MIN:
                case REPLACE:
                    if (C0607d.m926a(this, j, j2)) {
                        return j2;
                    }
                    return j;
                default:
                    throw new IllegalStateException("Invalid statistic type");
            }
        }

        /* renamed from: a */
        public long m923a() {
            switch (this) {
                case MIN:
                    return Long.MAX_VALUE;
                default:
                    return 0;
            }
        }

        /* renamed from: a */
        public static C0606a m922a(String str) {
            if (StringUtils.m580a((CharSequence) str)) {
                return MAX;
            }
            if (str.equalsIgnoreCase("Max")) {
                return MAX;
            }
            if (str.equalsIgnoreCase("Add")) {
                return ADD;
            }
            if (str.equalsIgnoreCase("Min")) {
                return MIN;
            }
            if (str.equalsIgnoreCase("Replace")) {
                return REPLACE;
            }
            return MAX;
        }
    }

    /* renamed from: a */
    public static boolean m926a(C0606a c0606a, long j, long j2) {
        switch (c0606a) {
            case ADD:
                if (j2 == C0606a.ADD.m923a()) {
                    return false;
                }
                return true;
            case MAX:
                if (j2 <= j) {
                    return false;
                }
                return true;
            case MIN:
                if (j2 >= j) {
                    return false;
                }
                return true;
            case REPLACE:
                return j != j2;
            default:
                return false;
        }
    }

    /* renamed from: a */
    public static void m925a(final long j, final JSONArray jSONArray) {
        if (jSONArray == null) {
            C0562j.m759c("null JSON passed into updateStatistics");
        } else {
            C0626d.m962a(new Runnable() {
                public void run() {
                    SQLiteDatabase a = C0637b.m1044a();
                    a.beginTransaction();
                    try {
                        ContentValues contentValues = new ContentValues();
                        for (int i = 0; i < jSONArray.length(); i++) {
                            contentValues.clear();
                            JSONObject optJSONObject = jSONArray.optJSONObject(i);
                            if (optJSONObject != null) {
                                int i2;
                                contentValues.put("_id", Long.valueOf(optJSONObject.optLong("id", 0)));
                                contentValues.put("game_id", Long.valueOf(j));
                                contentValues.put("name", optJSONObject.optString("name", ""));
                                contentValues.put("description", optJSONObject.optString("description"));
                                contentValues.put(C0607d.f912g, optJSONObject.optString(C0607d.f912g, C0606a.MAX.toString()));
                                String str = C0607d.f913h;
                                if (optJSONObject.optBoolean(C0607d.f913h, false)) {
                                    i2 = 1;
                                } else {
                                    i2 = 0;
                                }
                                contentValues.put(str, Integer.valueOf(i2));
                                contentValues.put(C0607d.f910e, optJSONObject.optString(C0607d.f910e));
                            }
                            C0636a.m1039a(C0637b.m1044a(), C0607d.f906a, contentValues);
                        }
                        C0613e.m944b(a);
                        a.setTransactionSuccessful();
                    } finally {
                        a.endTransaction();
                    }
                }
            });
        }
    }
}
