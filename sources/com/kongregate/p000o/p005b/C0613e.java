package com.kongregate.p000o.p005b;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.support.v4.content.LocalBroadcastManager;
import com.ironsource.sdk.constants.Constants.RequestParameters;
import com.kongregate.android.internal.sdk.C0507l;
import com.kongregate.android.internal.util.C0562j;
import com.kongregate.android.internal.util.StringUtils;
import com.kongregate.p000o.p002g.C0645b;
import com.kongregate.p000o.p002g.C0645b.C0468a;
import com.kongregate.p000o.p002g.C0646c;
import com.kongregate.p000o.p004e.C0636a;
import com.kongregate.p000o.p005b.C0607d.C0606a;
import com.kongregate.p000o.p006c.C0626d;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.kongregate.o.b.e */
public class C0613e implements BaseColumns {
    /* renamed from: a */
    public static final String f923a = "statistic_records";
    /* renamed from: b */
    public static final String f924b = "statistic_records_view";
    /* renamed from: c */
    public static final String f925c = "statistic_id";
    /* renamed from: d */
    public static final String f926d = "statistic_name";
    /* renamed from: e */
    public static final String f927e = "user_id";
    /* renamed from: f */
    public static final String f928f = "value";
    /* renamed from: g */
    public static final String f929g = "server_value";
    /* renamed from: h */
    public static final String f930h = "pending_value";
    /* renamed from: i */
    public static final String f931i = "max_value";
    /* renamed from: j */
    public static final String f932j = "min_value";
    /* renamed from: k */
    public static final String f933k = "add_value";
    /* renamed from: l */
    public static final String f934l = "replace_value";
    /* renamed from: m */
    public static final String f935m = "sent_at";
    /* renamed from: n */
    public static final String f936n = "dirty";
    /* renamed from: o */
    protected static final AtomicReference<Boolean> f937o = new AtomicReference(null);
    /* renamed from: p */
    protected static final AtomicReference<Boolean> f938p = new AtomicReference(null);
    /* renamed from: q */
    protected static final AtomicBoolean f939q = new AtomicBoolean(false);
    /* renamed from: r */
    protected static final TimeZone f940r = TimeZone.getTimeZone("America/Los_Angeles");
    /* renamed from: s */
    protected static final ThreadLocal<DateFormat> f941s = new C06081();

    /* renamed from: com.kongregate.o.b.e$1 */
    static class C06081 extends ThreadLocal<DateFormat> {
        C06081() {
        }

        protected /* synthetic */ Object initialValue() {
            return m927a();
        }

        /* renamed from: a */
        protected SimpleDateFormat m927a() {
            return new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        }
    }

    /* renamed from: com.kongregate.o.b.e$a */
    public static class C0612a implements Runnable {
        /* renamed from: a */
        protected final Context f918a;
        /* renamed from: b */
        protected final String f919b;
        /* renamed from: c */
        protected final long f920c;
        /* renamed from: d */
        protected final long f921d;
        /* renamed from: e */
        protected final SQLiteDatabase f922e;

        /* renamed from: com.kongregate.o.b.e$a$1 */
        class C06111 extends C0468a {
            /* renamed from: a */
            final /* synthetic */ C0612a f917a;

            C06111(C0612a c0612a) {
                this.f917a = c0612a;
            }

            /* renamed from: a */
            public void mo1133a(C0646c c0646c, final JSONObject jSONObject) {
                C0562j.m756b("Statistic sync successful: " + jSONObject);
                C0626d.m962a(new Runnable(this) {
                    /* renamed from: b */
                    final /* synthetic */ C06111 f916b;

                    public void run() {
                        this.f916b.f917a.m934a(jSONObject);
                        C0613e.f939q.set(false);
                    }
                });
            }

            /* renamed from: a */
            public void mo1185a(C0646c c0646c) {
                super.mo1185a(c0646c);
                C0613e.f939q.set(false);
            }

            /* renamed from: b */
            public void mo1186b(C0646c c0646c, JSONObject jSONObject) {
                super.mo1186b(c0646c, jSONObject);
                C0613e.f939q.set(false);
            }
        }

        public C0612a(Context context, SQLiteDatabase sQLiteDatabase, long j, long j2, String str) {
            this.f918a = context.getApplicationContext();
            if (str == null) {
                str = "/statistics/mobile_submit.json";
            }
            this.f919b = str;
            this.f920c = j;
            this.f921d = j2;
            this.f922e = sQLiteDatabase;
        }

        public void run() {
            if (this.f920c != 0) {
                Map a = m932a(m931a());
                if (a == null) {
                    return;
                }
                if (C0613e.f939q.compareAndSet(false, true)) {
                    C0562j.m756b("Syncing statistic data, stats: " + a.get("stats") + ", s: " + a.get("s"));
                    C0645b.m1085a().m1091a(this.f919b, a, new C06111(this));
                    return;
                }
                C0562j.m756b("Already syncing stats, will retry later");
            }
        }

        /* renamed from: a */
        protected Cursor m931a() {
            return C0613e.m942a(this.f922e, this.f920c);
        }

        /* renamed from: a */
        protected Map<String, Object> m932a(Cursor cursor) {
            JSONObject jSONObject = new JSONObject();
            if (cursor == null) {
                return null;
            }
            try {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    long j = cursor.getLong(cursor.getColumnIndex("statistic_id"));
                    try {
                        jSONObject.put(String.valueOf(j), cursor.getLong(cursor.getColumnIndex("value")));
                    } catch (JSONException e) {
                    }
                    cursor.moveToNext();
                }
                if (jSONObject.length() <= 0) {
                    return null;
                }
                Map hashMap = new HashMap();
                hashMap.put("stats", jSONObject.toString());
                hashMap.put("user_id", String.valueOf(this.f920c));
                if (this.f921d != -1) {
                    hashMap.put("game_id", String.valueOf(this.f921d));
                }
                return m933a(hashMap);
            } finally {
                C0636a.m1040a(cursor);
            }
        }

        /* renamed from: a */
        protected void m934a(JSONObject jSONObject) {
            Cursor query;
            Throwable th;
            JSONObject optJSONObject = jSONObject.optJSONObject("stats");
            if (optJSONObject == null) {
                C0562j.m759c("Statistic response from server with no stats array");
                return;
            }
            Map hashMap = new HashMap();
            Iterator keys = optJSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                hashMap.put(Long.valueOf(str), Long.valueOf(optJSONObject.optLong(str)));
            }
            String str2 = "user_id=? AND statistic_id IN (" + StringUtils.m562a(hashMap.keySet(), ",") + ")";
            String[] strArr = new String[]{String.valueOf(this.f920c)};
            this.f922e.beginTransaction();
            try {
                boolean z;
                query = this.f922e.query(C0613e.f923a, null, str2, strArr, null, null, null);
                if (query != null) {
                    try {
                        query.moveToFirst();
                        z = false;
                        while (!query.isAfterLast()) {
                            z = m935a(query, hashMap);
                            query.moveToNext();
                        }
                        m936b(jSONObject);
                        m937c(jSONObject);
                        this.f922e.setTransactionSuccessful();
                    } catch (Throwable th2) {
                        th = th2;
                        C0636a.m1040a(query);
                        this.f922e.endTransaction();
                        throw th;
                    }
                }
                z = false;
                C0636a.m1040a(query);
                this.f922e.endTransaction();
                if (z) {
                    LocalBroadcastManager.getInstance(this.f918a).sendBroadcast(new Intent(C0507l.f558h));
                }
            } catch (Throwable th3) {
                th = th3;
                query = null;
                C0636a.m1040a(query);
                this.f922e.endTransaction();
                throw th;
            }
        }

        /* renamed from: a */
        protected boolean m935a(Cursor cursor, Map<Long, Long> map) {
            Throwable th;
            long j = cursor.getLong(cursor.getColumnIndex("_id"));
            long j2 = cursor.getLong(cursor.getColumnIndex("statistic_id"));
            Cursor query;
            try {
                query = this.f922e.query(C0607d.f906a, null, "_id=?", new String[]{String.valueOf(j2)}, null, null, null);
                if (query != null) {
                    try {
                        if (query.moveToFirst()) {
                            C0606a a = C0606a.m922a(query.getString(query.getColumnIndex(C0607d.f912g)));
                            long longValue = ((Long) map.get(Long.valueOf(j2))).longValue();
                            ContentValues contentValues = new ContentValues();
                            long j3 = cursor.getLong(cursor.getColumnIndex("value"));
                            if (a == C0606a.ADD) {
                                long j4 = j3 - longValue;
                                if (j4 == C0606a.ADD.m923a()) {
                                    contentValues.putNull("value");
                                } else {
                                    contentValues.put("value", Long.valueOf(j4));
                                }
                            } else if (C0607d.m926a(a, longValue, j3)) {
                                contentValues.put("value", Long.valueOf(j3));
                            } else {
                                contentValues.putNull("value");
                            }
                            contentValues.put(C0613e.f929g, Long.valueOf(longValue));
                            contentValues.put(C0613e.f935m, Long.valueOf(C0613e.m938a()));
                            if (this.f922e.update(C0613e.f923a, contentValues, "_id=?", new String[]{String.valueOf(j)}) > 0) {
                                C0636a.m1040a(query);
                                return true;
                            }
                            C0636a.m1040a(query);
                            return false;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        C0636a.m1040a(query);
                        throw th;
                    }
                }
                C0562j.m759c("Couldn't find statistic ID: " + j2);
                C0636a.m1040a(query);
                return false;
            } catch (Throwable th3) {
                th = th3;
                query = null;
                C0636a.m1040a(query);
                throw th;
            }
        }

        /* renamed from: b */
        protected boolean m936b(JSONObject jSONObject) {
            Throwable th;
            JSONObject optJSONObject = jSONObject.optJSONObject("stats.prgs");
            if (optJSONObject == null) {
                return false;
            }
            Iterator keys = optJSONObject.keys();
            boolean z = false;
            while (keys.hasNext()) {
                Cursor query;
                String str = "statistic_id=?";
                String[] strArr = new String[]{String.valueOf(Long.parseLong((String) keys.next()))};
                ContentValues contentValues = new ContentValues();
                contentValues.put("user_id", Long.valueOf(this.f920c));
                contentValues.put("value", Long.valueOf(optJSONObject.optLong(r2)));
                try {
                    this.f922e.beginTransaction();
                    query = this.f922e.query(C0599a.f864a, null, str, strArr, null, null, null);
                    if (query == null) {
                        C0636a.m1040a(query);
                        this.f922e.endTransaction();
                        return false;
                    }
                    query.moveToFirst();
                    boolean z2 = z;
                    while (!query.isAfterLast()) {
                        long j = query.getLong(query.getColumnIndex("_id"));
                        long j2 = query.getLong(query.getColumnIndex("badge_id"));
                        contentValues.put(C0600b.f875c, Long.valueOf(j));
                        boolean a = C0603c.m920a(this.f922e, this.f920c, j2);
                        C0562j.m756b("wasCompleted=" + a);
                        if (C0636a.m1039a(this.f922e, C0600b.f873a, contentValues) != -1) {
                            C0562j.m756b("Updated accomplishment task progress: " + j + RequestParameters.EQUAL + contentValues.getAsLong("value"));
                            z2 = true;
                            if (!a && C0603c.m920a(this.f922e, this.f920c, j2)) {
                                C0562j.m756b("Badge completed: " + j2);
                            }
                        }
                        query.moveToNext();
                    }
                    try {
                        this.f922e.setTransactionSuccessful();
                        C0636a.m1040a(query);
                        this.f922e.endTransaction();
                        z = z2;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    query = null;
                }
            }
            return z;
            C0636a.m1040a(query);
            this.f922e.endTransaction();
            throw th;
        }

        /* renamed from: c */
        protected void m937c(JSONObject jSONObject) {
        }

        /* renamed from: a */
        protected Map<String, Object> m933a(Map<String, Object> map) {
            map.put("s", StringUtils.m590d(StringUtils.m595g(map.get("user_id").toString() + map.get("game_id").toString() + map.get("stats").toString()), "0EADBEEF"));
            map.put("v", "1");
            return map;
        }
    }

    /* renamed from: a */
    public static long m938a() {
        return C0613e.m940a(new Date());
    }

    /* renamed from: a */
    public static long m940a(Date date) {
        return (date.getTime() + ((long) f940r.getOffset(date.getTime()))) / 1000;
    }

    /* renamed from: a */
    public static Date m943a(long j) {
        try {
            return ((DateFormat) f941s.get()).parse(((DateFormat) f941s.get()).format(new Date(1000 * j)));
        } catch (Throwable e) {
            C0562j.m762d("ParseException", e);
            throw new IllegalStateException("Invalid date", e);
        }
    }

    /* renamed from: a */
    public static Cursor m942a(SQLiteDatabase sQLiteDatabase, long j) {
        return sQLiteDatabase.query(f923a, null, "user_id=? AND value IS NOT NULL", new String[]{String.valueOf(j)}, null, null, null);
    }

    /* renamed from: a */
    static Cursor m941a(SQLiteDatabase sQLiteDatabase) {
        return sQLiteDatabase.query(f923a, null, "statistic_id IS NULL", null, null, null, null);
    }

    /* renamed from: b */
    static void m944b(SQLiteDatabase sQLiteDatabase) {
        Throwable th;
        Cursor cursor = null;
        Cursor a = C0613e.m941a(sQLiteDatabase);
        if (a == null) {
            C0562j.m759c("Couldn't query for unknown statistic records");
            return;
        }
        a.moveToFirst();
        Cursor cursor2 = null;
        while (!a.isAfterLast()) {
            long j;
            String string;
            try {
                j = a.getLong(a.getColumnIndex("_id"));
                string = a.getString(a.getColumnIndex(f926d));
                if (string != null) {
                    String[] split = string.split("-", 2);
                    if (split != null && split.length == 2) {
                        long parseLong = Long.parseLong(split[0]);
                        String str = split[1];
                        C0636a.m1040a(cursor2);
                        String[] strArr = new String[]{String.valueOf(parseLong), str};
                        SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
                        Cursor query = sQLiteDatabase2.query(C0607d.f906a, new String[]{"_id", C0607d.f912g}, "game_id=? AND name=?", strArr, null, null, null);
                        if (query != null) {
                            if (query.moveToFirst()) {
                                long j2 = query.getLong(query.getColumnIndex("_id"));
                                C0606a a2 = C0606a.m922a(query.getString(query.getColumnIndex(C0607d.f912g)));
                                ContentValues contentValues = new ContentValues();
                                contentValues.put("_id", Long.valueOf(j));
                                contentValues.put("statistic_id", Long.valueOf(j2));
                                contentValues.putNull(f926d);
                                switch (a2) {
                                    case MAX:
                                        try {
                                            if (!a.isNull(a.getColumnIndex(f931i))) {
                                                j2 = a.getLong(a.getColumnIndex(f931i));
                                                break;
                                            } else {
                                                j2 = a2.m923a();
                                                break;
                                            }
                                        } catch (Throwable th2) {
                                            th = th2;
                                            cursor = query;
                                            break;
                                        }
                                    case MIN:
                                        if (!a.isNull(a.getColumnIndex(f932j))) {
                                            j2 = a.getLong(a.getColumnIndex(f932j));
                                            break;
                                        } else {
                                            j2 = a2.m923a();
                                            break;
                                        }
                                    case ADD:
                                        if (!a.isNull(a.getColumnIndex(f933k))) {
                                            j2 = a.getLong(a.getColumnIndex(f933k));
                                            break;
                                        } else {
                                            j2 = a2.m923a();
                                            break;
                                        }
                                    case REPLACE:
                                        if (!a.isNull(a.getColumnIndex(f934l))) {
                                            j2 = a.getLong(a.getColumnIndex(f934l));
                                            break;
                                        } else {
                                            j2 = a2.m923a();
                                            break;
                                        }
                                    default:
                                        j2 = a2.m923a();
                                        break;
                                }
                                contentValues.put("value", Long.valueOf(j2));
                                contentValues.putNull(f931i);
                                contentValues.putNull(f932j);
                                contentValues.putNull(f933k);
                                contentValues.putNull(f934l);
                                C0636a.m1039a(sQLiteDatabase, f923a, contentValues);
                                cursor = query;
                                a.moveToNext();
                                cursor2 = cursor;
                            }
                        }
                        cursor = query;
                        C0562j.m753a("Deleting unknown statistic record: " + string);
                        sQLiteDatabase.delete(f923a, "_id=?", new String[]{String.valueOf(j)});
                        a.moveToNext();
                        cursor2 = cursor;
                    }
                }
                cursor = cursor2;
            } catch (Throwable th3) {
                th = th3;
                cursor = cursor2;
            }
            try {
                C0562j.m753a("Deleting unknown statistic record: " + string);
                sQLiteDatabase.delete(f923a, "_id=?", new String[]{String.valueOf(j)});
                a.moveToNext();
                cursor2 = cursor;
            } catch (Throwable th4) {
                th = th4;
            }
        }
        C0636a.m1040a(a);
        C0636a.m1040a(cursor2);
        return;
        C0636a.m1040a(a);
        C0636a.m1040a(cursor);
        throw th;
    }

    /* renamed from: b */
    public static void m945b(SQLiteDatabase sQLiteDatabase, long j) {
        Throwable th;
        Cursor cursor;
        Cursor cursor2;
        if (j != 0) {
            sQLiteDatabase.beginTransaction();
            try {
                SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
                Cursor query = sQLiteDatabase2.query(f923a, null, "user_id=?", new String[]{String.valueOf(0)}, null, null, null);
                if (query == null) {
                    try {
                        C0562j.m759c("Couldn't query statistic records");
                        sQLiteDatabase.endTransaction();
                        C0636a.m1040a(query);
                        C0636a.m1040a(null);
                        return;
                    } catch (Throwable th2) {
                        th = th2;
                        cursor = null;
                        cursor2 = query;
                        sQLiteDatabase.endTransaction();
                        C0636a.m1040a(cursor2);
                        C0636a.m1040a(cursor);
                        throw th;
                    }
                }
                ContentValues contentValues = new ContentValues();
                query.moveToFirst();
                cursor = null;
                while (!query.isAfterLast()) {
                    try {
                        contentValues.clear();
                        contentValues.put("_id", Long.valueOf(query.getLong(query.getColumnIndex("_id"))));
                        contentValues.put("user_id", Long.valueOf(j));
                        if (query.isNull(query.getColumnIndex("statistic_id"))) {
                            C0636a.m1039a(sQLiteDatabase, f923a, contentValues);
                        } else {
                            C0636a.m1040a(cursor);
                            long j2 = query.getLong(query.getColumnIndex("statistic_id"));
                            long j3 = query.getLong(query.getColumnIndex("value"));
                            SQLiteDatabase sQLiteDatabase3 = sQLiteDatabase;
                            Cursor query2 = sQLiteDatabase3.query(C0607d.f906a, null, "_id=?", new String[]{String.valueOf(j2)}, null, null, null);
                            if (query2 != null) {
                                try {
                                    if (query2.moveToNext()) {
                                        C0613e.m939a(sQLiteDatabase, j, query2.getLong(query2.getColumnIndex(C0613e.m946c(sQLiteDatabase))), query2.getString(query2.getColumnIndex("name")), j3);
                                        SQLiteDatabase sQLiteDatabase4 = sQLiteDatabase;
                                        sQLiteDatabase4.delete(f923a, "_id=?", new String[]{String.valueOf(r20)});
                                    }
                                } catch (Throwable th3) {
                                    th = th3;
                                    cursor = query2;
                                    cursor2 = query;
                                }
                            }
                            cursor = query2;
                        }
                        query.moveToNext();
                    } catch (Throwable th4) {
                        th = th4;
                        cursor2 = query;
                    }
                }
                sQLiteDatabase.setTransactionSuccessful();
                sQLiteDatabase.endTransaction();
                C0636a.m1040a(query);
                C0636a.m1040a(cursor);
            } catch (Throwable th5) {
                th = th5;
                cursor = null;
                cursor2 = null;
                sQLiteDatabase.endTransaction();
                C0636a.m1040a(cursor2);
                C0636a.m1040a(cursor);
                throw th;
            }
        }
    }

    /* renamed from: a */
    public static long m939a(SQLiteDatabase sQLiteDatabase, long j, long j2, String str, long j3) {
        Throwable th;
        Cursor cursor;
        Date a = C0613e.m943a(C0613e.m938a());
        ContentValues contentValues = new ContentValues();
        C0562j.m756b("Statistic submit: " + str + RequestParameters.EQUAL + j3);
        sQLiteDatabase.beginTransaction();
        Cursor cursor2;
        try {
            Cursor query = sQLiteDatabase.query(C0607d.f906a, null, C0613e.m946c(sQLiteDatabase) + "=? AND " + "name" + "=?", new String[]{String.valueOf(j2), str}, null, null, null, null);
            if (query == null) {
                try {
                    C0562j.m759c("Statistic query failed");
                    C0636a.m1040a(null);
                    C0636a.m1040a(query);
                    sQLiteDatabase.endTransaction();
                    return -1;
                } catch (Throwable th2) {
                    th = th2;
                    cursor = null;
                    cursor2 = query;
                    C0636a.m1040a(cursor);
                    C0636a.m1040a(cursor2);
                    sQLiteDatabase.endTransaction();
                    throw th;
                }
            }
            boolean moveToFirst = query.moveToFirst();
            boolean d = C0613e.m947d(sQLiteDatabase);
            String str2 = j2 + "-" + str;
            StringBuilder append = new StringBuilder().append("user_id=? AND ");
            String str3 = (moveToFirst || !d) ? "statistic_id=?" : "statistic_name=?";
            String stringBuilder = append.append(str3).append("").toString();
            String[] strArr = (moveToFirst || !d) ? new String[]{String.valueOf(j), String.valueOf(query.getLong(query.getColumnIndex("_id")))} : new String[]{String.valueOf(j), str2};
            cursor2 = sQLiteDatabase.query(f923a, null, stringBuilder, strArr, null, null, null);
            if (cursor2 == null) {
                try {
                    C0562j.m759c("StatisticRecord query failed");
                    C0636a.m1040a(cursor2);
                    C0636a.m1040a(query);
                    sQLiteDatabase.endTransaction();
                    return -1;
                } catch (Throwable th3) {
                    th = th3;
                    cursor = cursor2;
                    cursor2 = query;
                    C0636a.m1040a(cursor);
                    C0636a.m1040a(cursor2);
                    sQLiteDatabase.endTransaction();
                    throw th;
                }
            }
            long a2;
            Object obj = !cursor2.moveToFirst() ? 1 : null;
            if (moveToFirst) {
                Date date;
                C0606a a3 = C0606a.m922a(query.getString(query.getColumnIndex(C0607d.f912g)));
                long j4 = query.getLong(query.getColumnIndex("_id"));
                long a4 = (obj != null || cursor2.isNull(cursor2.getColumnIndex("value"))) ? a3.m923a() : cursor2.getLong(cursor2.getColumnIndex("value"));
                long a5 = (obj != null || cursor2.isNull(cursor2.getColumnIndex(f929g))) ? a3.m923a() : cursor2.getLong(cursor2.getColumnIndex(f929g));
                if (obj != null) {
                    date = new Date(0);
                } else {
                    date = C0613e.m943a(cursor2.getLong(cursor2.getColumnIndex(f935m)));
                }
                boolean before = date.before(a);
                j3 = a3.m924a(a4, j3);
                if (before || C0607d.m926a(a3, a5, j3)) {
                    if (obj != null || cursor2.isNull(cursor2.getColumnIndex("_id"))) {
                        contentValues.put("statistic_id", Long.valueOf(j4));
                        contentValues.put("user_id", Long.valueOf(j));
                    } else {
                        contentValues.put("_id", Long.valueOf(cursor2.getLong(cursor2.getColumnIndex("_id"))));
                    }
                    contentValues.put("value", Long.valueOf(j3));
                } else {
                    C0636a.m1040a(cursor2);
                    C0636a.m1040a(query);
                    sQLiteDatabase.endTransaction();
                    return -1;
                }
            } else if (d) {
                C0562j.m756b("Statistic definition doesn't exist for " + str + ", value=" + j3);
                contentValues.putNull("value");
                contentValues.put(f926d, str2);
                String str4 = f931i;
                C0606a c0606a = C0606a.MAX;
                a2 = (obj != null || cursor2.isNull(cursor2.getColumnIndex(f931i))) ? C0606a.MAX.m923a() : cursor2.getLong(cursor2.getColumnIndex(f931i));
                contentValues.put(str4, Long.valueOf(c0606a.m924a(a2, j3)));
                str4 = f932j;
                c0606a = C0606a.MIN;
                a2 = (obj != null || cursor2.isNull(cursor2.getColumnIndex(f932j))) ? C0606a.MIN.m923a() : cursor2.getLong(cursor2.getColumnIndex(f932j));
                contentValues.put(str4, Long.valueOf(c0606a.m924a(a2, j3)));
                str4 = f933k;
                c0606a = C0606a.ADD;
                a2 = (obj != null || cursor2.isNull(cursor2.getColumnIndex(f933k))) ? C0606a.ADD.m923a() : cursor2.getLong(cursor2.getColumnIndex(f933k));
                contentValues.put(str4, Long.valueOf(c0606a.m924a(a2, j3)));
                str4 = f934l;
                c0606a = C0606a.REPLACE;
                a2 = (obj != null || cursor2.isNull(cursor2.getColumnIndex(f934l))) ? C0606a.REPLACE.m923a() : cursor2.getLong(cursor2.getColumnIndex(f934l));
                contentValues.put(str4, Long.valueOf(c0606a.m924a(a2, j3)));
                contentValues.put("user_id", Long.valueOf(j));
                if (!cursor2.isAfterLast()) {
                    contentValues.put("_id", Long.valueOf(cursor2.getLong(cursor2.getColumnIndex("_id"))));
                }
            } else {
                C0562j.m756b("Ignoring statistic submission for undefined stat: " + str + RequestParameters.EQUAL + j3);
            }
            a2 = C0636a.m1039a(sQLiteDatabase, f923a, contentValues);
            if (a2 != -1) {
                sQLiteDatabase.setTransactionSuccessful();
                C0562j.m756b("StatisticRecord " + a2 + ", updated with value: " + j3);
                C0636a.m1040a(cursor2);
                C0636a.m1040a(query);
                sQLiteDatabase.endTransaction();
                return a2;
            }
            C0636a.m1040a(cursor2);
            C0636a.m1040a(query);
            sQLiteDatabase.endTransaction();
            return -1;
        } catch (Throwable th4) {
            th = th4;
            cursor = null;
            cursor2 = null;
            C0636a.m1040a(cursor);
            C0636a.m1040a(cursor2);
            sQLiteDatabase.endTransaction();
            throw th;
        }
    }

    /* renamed from: c */
    protected static String m946c(SQLiteDatabase sQLiteDatabase) {
        if (f937o.get() == null) {
            f937o.set(Boolean.valueOf(C0636a.m1042a(sQLiteDatabase, C0607d.f906a, "application_id")));
        }
        return ((Boolean) f937o.get()).booleanValue() ? "application_id" : "game_id";
    }

    /* renamed from: d */
    protected static boolean m947d(SQLiteDatabase sQLiteDatabase) {
        if (f938p.get() == null) {
            f938p.set(Boolean.valueOf(C0636a.m1042a(sQLiteDatabase, f923a, f926d)));
        }
        return ((Boolean) f938p.get()).booleanValue();
    }
}
