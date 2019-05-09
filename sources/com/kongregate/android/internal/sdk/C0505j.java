package com.kongregate.android.internal.sdk;

import android.database.Cursor;
import android.os.Bundle;
import com.applovin.sdk.AppLovinEventTypes;
import com.kongregate.p000o.p005b.C0613e;

/* renamed from: com.kongregate.android.internal.sdk.j */
class C0505j {
    /* renamed from: a */
    private String f524a;
    /* renamed from: b */
    private long f525b = 0;
    /* renamed from: c */
    private long f526c = Long.MAX_VALUE;
    /* renamed from: d */
    private long f527d = 0;
    /* renamed from: e */
    private long f528e = 0;

    C0505j(Cursor cursor) {
        m453a(cursor);
    }

    /* renamed from: a */
    private void m453a(Cursor cursor) {
        this.f524a = cursor.getString(cursor.getColumnIndex("name"));
        this.f526c = cursor.getLong(cursor.getColumnIndex(C0613e.f932j));
        this.f525b = cursor.getLong(cursor.getColumnIndex(C0613e.f931i));
        this.f527d = cursor.getLong(cursor.getColumnIndex(C0613e.f933k));
        this.f528e = cursor.getLong(cursor.getColumnIndex(C0613e.f934l));
    }

    /* renamed from: a */
    public Bundle m454a() {
        Bundle bundle = new Bundle(2);
        bundle.putString("name", this.f524a);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.f526c).append(",");
        stringBuilder.append(this.f525b).append(",");
        stringBuilder.append(this.f527d).append(",");
        stringBuilder.append(this.f528e);
        bundle.putString(AppLovinEventTypes.USER_VIEWED_CONTENT, stringBuilder.toString());
        return bundle;
    }
}
