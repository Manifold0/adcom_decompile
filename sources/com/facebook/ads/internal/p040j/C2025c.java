package com.facebook.ads.internal.p040j;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.WorkerThread;
import com.ironsource.eventsmodule.DataBaseEventsStorage.EventEntry;
import com.ironsource.sdk.constants.LocationConst;
import com.tapjoy.TapjoyConstants;
import java.util.Map;
import java.util.UUID;
import org.json.JSONObject;

/* renamed from: com.facebook.ads.internal.j.c */
public class C2025c extends C2024g {
    /* renamed from: a */
    public static final C2023b f4487a = new C2023b(0, "event_id", "TEXT PRIMARY KEY");
    /* renamed from: b */
    public static final C2023b f4488b = new C2023b(1, "token_id", "TEXT REFERENCES tokens ON UPDATE CASCADE ON DELETE RESTRICT");
    /* renamed from: c */
    public static final C2023b f4489c = new C2023b(2, "priority", "INTEGER");
    /* renamed from: d */
    public static final C2023b f4490d = new C2023b(3, "type", "TEXT");
    /* renamed from: e */
    public static final C2023b f4491e = new C2023b(4, LocationConst.TIME, "REAL");
    /* renamed from: f */
    public static final C2023b f4492f = new C2023b(5, "session_time", "REAL");
    /* renamed from: g */
    public static final C2023b f4493g = new C2023b(6, TapjoyConstants.TJC_SESSION_ID, "TEXT");
    /* renamed from: h */
    public static final C2023b f4494h = new C2023b(7, "data", "TEXT");
    /* renamed from: i */
    public static final C2023b f4495i = new C2023b(8, "attempt", "INTEGER");
    /* renamed from: j */
    public static final C2023b[] f4496j = new C2023b[]{f4487a, f4488b, f4489c, f4490d, f4491e, f4492f, f4493g, f4494h, f4495i};
    /* renamed from: l */
    private static final String f4497l = C2024g.m4879a(EventEntry.TABLE_NAME, f4496j);

    public C2025c(C2030d c2030d) {
        super(c2030d);
    }

    /* renamed from: a */
    public String mo5459a() {
        return EventEntry.TABLE_NAME;
    }

    @WorkerThread
    /* renamed from: a */
    String m4889a(String str, int i, String str2, double d, double d2, String str3, Map<String, String> map) {
        String uuid = UUID.randomUUID().toString();
        ContentValues contentValues = new ContentValues(9);
        contentValues.put(f4487a.f4484b, uuid);
        contentValues.put(f4488b.f4484b, str);
        contentValues.put(f4489c.f4484b, Integer.valueOf(i));
        contentValues.put(f4490d.f4484b, str2);
        contentValues.put(f4491e.f4484b, Double.valueOf(d));
        contentValues.put(f4492f.f4484b, Double.valueOf(d2));
        contentValues.put(f4493g.f4484b, str3);
        contentValues.put(f4494h.f4484b, map != null ? new JSONObject(map).toString() : null);
        contentValues.put(f4495i.f4484b, Integer.valueOf(0));
        m4886f().insertOrThrow(EventEntry.TABLE_NAME, null, contentValues);
        return uuid;
    }

    /* renamed from: a */
    boolean m4890a(String str) {
        return m4886f().delete(EventEntry.TABLE_NAME, new StringBuilder().append(f4487a.f4484b).append(" = ?").toString(), new String[]{str}) > 0;
    }

    /* renamed from: b */
    public C2023b[] mo5460b() {
        return f4496j;
    }

    @WorkerThread
    /* renamed from: c */
    Cursor mo5461c() {
        return m4886f().rawQuery("SELECT count(*) FROM events", null);
    }

    @WorkerThread
    /* renamed from: d */
    Cursor m4893d() {
        return m4886f().rawQuery(f4497l, null);
    }
}
