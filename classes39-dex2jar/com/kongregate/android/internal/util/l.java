// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.android.internal.util;

import android.content.SharedPreferences;

public class l
{
    public static int a(final SharedPreferences sharedPreferences, final String s, final int n) {
        try {
            return sharedPreferences.getInt(s, n);
        }
        catch (ClassCastException ex) {
            j.c("Type of preference " + s + " was not int", ex);
            sharedPreferences.edit().remove(s).apply();
            return n;
        }
    }
    
    public static long a(final SharedPreferences sharedPreferences, final String s, final long n) {
        try {
            return sharedPreferences.getLong(s, n);
        }
        catch (ClassCastException ex) {
            j.c("Type of preference " + s + " was not long", ex);
            sharedPreferences.edit().remove(s).apply();
            return n;
        }
    }
    
    public static boolean a(final SharedPreferences sharedPreferences, final String s, final boolean b) {
        try {
            return sharedPreferences.getBoolean(s, b);
        }
        catch (ClassCastException ex) {
            j.c("Type of preference " + s + " was not boolean", ex);
            sharedPreferences.edit().remove(s).apply();
            return b;
        }
    }
}
