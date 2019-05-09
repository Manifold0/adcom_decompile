package com.facebook.ads.internal.p040j;

import android.database.sqlite.SQLiteDatabase;

/* renamed from: com.facebook.ads.internal.j.g */
public abstract class C2024g {
    /* renamed from: k */
    protected final C2030d f4486k;

    protected C2024g(C2030d c2030d) {
        this.f4486k = c2030d;
    }

    /* renamed from: a */
    public static String m4879a(String str, C2023b[] c2023bArr) {
        StringBuilder stringBuilder = new StringBuilder("SELECT ");
        for (int i = 0; i < c2023bArr.length - 1; i++) {
            stringBuilder.append(c2023bArr[i].f4484b);
            stringBuilder.append(", ");
        }
        stringBuilder.append(c2023bArr[c2023bArr.length - 1].f4484b);
        stringBuilder.append(" FROM ");
        stringBuilder.append(str);
        return stringBuilder.toString();
    }

    /* renamed from: c */
    private String mo5461c() {
        C2023b[] b = mo5460b();
        if (b.length < 1) {
            return null;
        }
        String str = "";
        for (int i = 0; i < b.length - 1; i++) {
            str = str + b[i].m4878a() + ", ";
        }
        return str + b[b.length - 1].m4878a();
    }

    /* renamed from: a */
    public abstract String mo5459a();

    /* renamed from: a */
    public void m4882a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE " + mo5459a() + " (" + mo5461c() + ")");
    }

    /* renamed from: b */
    public void m4883b(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + mo5459a());
    }

    /* renamed from: b */
    public abstract C2023b[] mo5460b();

    /* renamed from: e */
    public void m4885e() {
    }

    /* renamed from: f */
    protected SQLiteDatabase m4886f() {
        return this.f4486k.m4906a();
    }

    /* renamed from: g */
    protected boolean m4887g() {
        return m4886f().delete(mo5459a(), null, null) > 0;
    }
}
