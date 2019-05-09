package com.kongregate.p000o.p001j;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.BaseColumns;

/* renamed from: com.kongregate.o.j.c */
public class C0668c implements BaseColumns {
    /* renamed from: a */
    public static final String f1099a = "users";
    /* renamed from: b */
    public static final String f1100b = "username";
    /* renamed from: c */
    String f1101c;
    /* renamed from: d */
    String f1102d;
    /* renamed from: e */
    long f1103e;

    public C0668c() {
        this.f1101c = "Guest";
        this.f1102d = "http://cdn4.kongcdn.com/assets/avatars/defaults/headbot.png";
        this.f1103e = 0;
    }

    public C0668c(Cursor cursor) {
        this.f1103e = cursor.getLong(cursor.getColumnIndex("user_id"));
        this.f1101c = cursor.getString(cursor.getColumnIndex("username"));
        this.f1102d = cursor.getString(cursor.getColumnIndex("avatar"));
    }

    public C0668c(Bundle bundle) {
        this.f1101c = bundle.getString("username");
        this.f1102d = bundle.getString("avatar");
        this.f1103e = bundle.getLong("user_id");
    }

    /* renamed from: c */
    public String m1195c() {
        return this.f1101c;
    }

    /* renamed from: d */
    public String m1196d() {
        return this.f1102d;
    }

    /* renamed from: e */
    public long m1197e() {
        return this.f1103e;
    }

    /* renamed from: f */
    public boolean m1198f() {
        return this.f1103e == 0;
    }
}
