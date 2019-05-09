package com.kongregate.android.internal.util;

import android.content.SharedPreferences;

/* renamed from: com.kongregate.android.internal.util.l */
public class C0564l {
    /* renamed from: a */
    public static int m772a(SharedPreferences sharedPreferences, String str, int i) {
        try {
            i = sharedPreferences.getInt(str, i);
        } catch (Throwable e) {
            C0562j.m760c("Type of preference " + str + " was not int", e);
            sharedPreferences.edit().remove(str).apply();
        }
        return i;
    }

    /* renamed from: a */
    public static boolean m774a(SharedPreferences sharedPreferences, String str, boolean z) {
        try {
            z = sharedPreferences.getBoolean(str, z);
        } catch (Throwable e) {
            C0562j.m760c("Type of preference " + str + " was not boolean", e);
            sharedPreferences.edit().remove(str).apply();
        }
        return z;
    }

    /* renamed from: a */
    public static long m773a(SharedPreferences sharedPreferences, String str, long j) {
        try {
            j = sharedPreferences.getLong(str, j);
        } catch (Throwable e) {
            C0562j.m760c("Type of preference " + str + " was not long", e);
            sharedPreferences.edit().remove(str).apply();
        }
        return j;
    }
}
