package com.facebook.ads.internal.p040j;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.ironsource.eventsmodule.DataBaseEventsStorage.EventEntry;
import java.util.UUID;

/* renamed from: com.facebook.ads.internal.j.h */
public class C2033h extends C2024g {
    /* renamed from: a */
    public static final C2023b f4529a = new C2023b(0, "token_id", "TEXT PRIMARY KEY");
    /* renamed from: b */
    public static final C2023b f4530b = new C2023b(1, "token", "TEXT");
    /* renamed from: c */
    public static final C2023b[] f4531c = new C2023b[]{f4529a, f4530b};
    /* renamed from: d */
    private static final String f4532d = C2033h.class.getSimpleName();
    /* renamed from: e */
    private static final String f4533e = C2024g.m4879a("tokens", f4531c);
    /* renamed from: f */
    private static final String f4534f;
    /* renamed from: g */
    private static final String f4535g = ("DELETE FROM tokens WHERE NOT EXISTS (SELECT 1 FROM events WHERE tokens." + f4529a.f4484b + " = " + EventEntry.TABLE_NAME + "." + C2025c.f4488b.f4484b + ")");

    static {
        C2023b[] c2023bArr = f4531c;
        C2023b c2023b = f4530b;
        StringBuilder stringBuilder = new StringBuilder(C2024g.m4879a("tokens", c2023bArr));
        stringBuilder.append(" WHERE ");
        stringBuilder.append(c2023b.f4484b);
        stringBuilder.append(" = ?");
        f4534f = stringBuilder.toString();
    }

    public C2033h(C2030d c2030d) {
        super(c2030d);
    }

    /* renamed from: a */
    public String mo5459a() {
        return "tokens";
    }

    @WorkerThread
    /* renamed from: a */
    String m4921a(String str) {
        Throwable th;
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Invalid token.");
        }
        Cursor rawQuery;
        try {
            rawQuery = m4886f().rawQuery(f4534f, new String[]{str});
            try {
                String string = rawQuery.moveToNext() ? rawQuery.getString(f4529a.f4483a) : null;
                if (TextUtils.isEmpty(string)) {
                    string = UUID.randomUUID().toString();
                    ContentValues contentValues = new ContentValues(2);
                    contentValues.put(f4529a.f4484b, string);
                    contentValues.put(f4530b.f4484b, str);
                    m4886f().insertOrThrow("tokens", null, contentValues);
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                } else if (rawQuery != null) {
                    rawQuery.close();
                }
                return string;
            } catch (Throwable th2) {
                th = th2;
                if (rawQuery != null) {
                    rawQuery.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            rawQuery = null;
            if (rawQuery != null) {
                rawQuery.close();
            }
            throw th;
        }
    }

    /* renamed from: b */
    public C2023b[] mo5460b() {
        return f4531c;
    }

    @WorkerThread
    /* renamed from: c */
    Cursor mo5461c() {
        return m4886f().rawQuery(f4533e, null);
    }

    @WorkerThread
    /* renamed from: d */
    public void m4924d() {
        try {
            m4886f().execSQL(f4535g);
        } catch (SQLException e) {
        }
    }
}
