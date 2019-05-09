package com.moat.analytics.mobile.vng;

import android.util.Log;

/* renamed from: com.moat.analytics.mobile.vng.p */
class C0858p {
    C0858p() {
    }

    /* renamed from: a */
    private static String m1576a(String str) {
        return "Moat" + str;
    }

    /* renamed from: a */
    static void m1577a(int i, String str, Object obj, String str2) {
        if (!C0879w.m1610a().f1479b) {
            return;
        }
        if (obj == null) {
            Log.println(i, C0858p.m1576a(str), String.format("message = %s", new Object[]{str2}));
            return;
        }
        Log.println(i, C0858p.m1576a(str), String.format("id = %s, message = %s", new Object[]{Integer.valueOf(obj.hashCode()), str2}));
    }

    /* renamed from: a */
    static void m1578a(String str, Object obj, String str2, Throwable th) {
        if (C0879w.m1610a().f1479b) {
            Log.e(C0858p.m1576a(str), String.format("id = %s, message = %s", new Object[]{Integer.valueOf(obj.hashCode()), str2}), th);
        }
    }

    /* renamed from: a */
    static void m1579a(String str, String str2) {
        if (!C0879w.m1610a().f1479b && ((C0847k) MoatAnalytics.getInstance()).f1423a) {
            int i = 2;
            if (str.equals("[ERROR] ")) {
                i = 6;
            }
            Log.println(i, "MoatAnalytics", str + str2);
        }
    }

    /* renamed from: b */
    static void m1580b(int i, String str, Object obj, String str2) {
        if (C0879w.m1610a().f1480c) {
            String a = C0858p.m1576a(str);
            String str3 = "id = %s, message = %s";
            Object[] objArr = new Object[2];
            objArr[0] = obj == null ? "null" : Integer.valueOf(obj.hashCode());
            objArr[1] = str2;
            Log.println(i, a, String.format(str3, objArr));
        }
    }
}
