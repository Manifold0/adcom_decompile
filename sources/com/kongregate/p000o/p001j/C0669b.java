package com.kongregate.p000o.p001j;

import android.content.ContentValues;
import android.database.Cursor;
import com.kongregate.p000o.p004e.C0636a;
import com.kongregate.p000o.p004e.C0637b;
import com.kongregate.p000o.p006c.C0626d;
import org.json.JSONObject;

/* renamed from: com.kongregate.o.j.b */
public class C0669b extends C0668c {
    /* renamed from: f */
    private String f1104f;

    public C0669b() {
        this.f1104f = "Guest_Game_Auth_Token";
    }

    public C0669b(Cursor cursor) {
        this.e = cursor.getLong(cursor.getColumnIndex("_id"));
        this.c = cursor.getString(cursor.getColumnIndex("username"));
        this.f1104f = cursor.getString(cursor.getColumnIndex("game_auth_token"));
    }

    /* renamed from: a */
    public static C0669b m1199a(long j) {
        C0669b c0669b = null;
        Cursor query = C0637b.m1044a().query(C0668c.f1099a, null, "_ID = ?", new String[]{Long.toString(j)}, null, null, null);
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    c0669b = new C0669b(query);
                }
            } catch (Throwable th) {
                C0636a.m1040a(query);
            }
        }
        C0636a.m1040a(query);
        return c0669b;
    }

    /* renamed from: a */
    public void m1202a(JSONObject jSONObject, String str) {
        this.e = jSONObject.optLong("id", 0);
        this.c = jSONObject.optString("username", "Guest");
        if (str != null && !m1198f()) {
            this.f1104f = str;
        }
    }

    /* renamed from: a */
    public String m1200a() {
        return this.f1104f;
    }

    /* renamed from: a */
    public void m1201a(String str) {
        this.f1104f = str;
    }

    /* renamed from: b */
    public void m1203b() {
        final ContentValues contentValues = new ContentValues();
        contentValues.put("_id", Long.valueOf(m1197e()));
        contentValues.put("username", m1195c());
        if (this.f1104f != null) {
            contentValues.put("game_auth_token", this.f1104f);
        }
        C0626d.m962a(new Runnable(this) {
            /* renamed from: b */
            final /* synthetic */ C0669b f1098b;

            public void run() {
                C0636a.m1039a(C0637b.m1044a(), C0668c.f1099a, contentValues);
            }
        });
    }
}
