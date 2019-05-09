package com.facebook.ads.internal.p038g;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

/* renamed from: com.facebook.ads.internal.g.c */
public class C2004c {

    /* renamed from: com.facebook.ads.internal.g.c$a */
    public static class C2003a {
        /* renamed from: a */
        public String f4434a;
        /* renamed from: b */
        public String f4435b;
        /* renamed from: c */
        public boolean f4436c;

        public C2003a(String str, String str2, boolean z) {
            this.f4434a = str;
            this.f4435b = str2;
            this.f4436c = z;
        }
    }

    /* renamed from: a */
    public static C2003a m4829a(ContentResolver contentResolver) {
        C2003a c2003a;
        Throwable th;
        Cursor query;
        try {
            String[] strArr = new String[]{"aid", "androidid", "limit_tracking"};
            query = contentResolver.query(Uri.parse("content://com.facebook.katana.provider.AttributionIdProvider"), strArr, null, null, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        c2003a = new C2003a(query.getString(query.getColumnIndex("aid")), query.getString(query.getColumnIndex("androidid")), Boolean.valueOf(query.getString(query.getColumnIndex("limit_tracking"))).booleanValue());
                        if (query != null) {
                            query.close();
                        }
                        return c2003a;
                    }
                } catch (Exception e) {
                    try {
                        c2003a = new C2003a(null, null, false);
                        if (query != null) {
                            query.close();
                        }
                        return c2003a;
                    } catch (Throwable th2) {
                        th = th2;
                        if (query != null) {
                            query.close();
                        }
                        throw th;
                    }
                }
            }
            c2003a = new C2003a(null, null, false);
            if (query != null) {
                query.close();
            }
        } catch (Exception e2) {
            query = null;
            c2003a = new C2003a(null, null, false);
            if (query != null) {
                query.close();
            }
            return c2003a;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
        return c2003a;
    }
}
