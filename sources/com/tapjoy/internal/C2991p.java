package com.tapjoy.internal;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/* renamed from: com.tapjoy.internal.p */
public final class C2991p {
    /* renamed from: a */
    public static boolean m8217a(SharedPreferences sharedPreferences, String str, boolean z) {
        Editor edit = sharedPreferences.edit();
        edit.putBoolean(str, z);
        return edit.commit();
    }

    /* renamed from: a */
    public static boolean m8215a(SharedPreferences sharedPreferences, String str, int i) {
        Editor edit = sharedPreferences.edit();
        edit.putInt(str, i);
        return edit.commit();
    }

    /* renamed from: a */
    public static boolean m8216a(SharedPreferences sharedPreferences, String str, String str2) {
        Editor edit = sharedPreferences.edit();
        edit.putString(str, str2);
        return edit.commit();
    }
}
