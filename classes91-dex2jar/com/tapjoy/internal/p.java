// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import android.content.SharedPreferences$Editor;
import android.content.SharedPreferences;

public final class p
{
    public static boolean a(final SharedPreferences sharedPreferences, final String s, final int n) {
        final SharedPreferences$Editor edit = sharedPreferences.edit();
        edit.putInt(s, n);
        return edit.commit();
    }
    
    public static boolean a(final SharedPreferences sharedPreferences, final String s, final String s2) {
        final SharedPreferences$Editor edit = sharedPreferences.edit();
        edit.putString(s, s2);
        return edit.commit();
    }
    
    public static boolean a(final SharedPreferences sharedPreferences, final String s, final boolean b) {
        final SharedPreferences$Editor edit = sharedPreferences.edit();
        edit.putBoolean(s, b);
        return edit.commit();
    }
}
