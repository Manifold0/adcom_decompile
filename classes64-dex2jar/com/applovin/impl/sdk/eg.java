// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import android.content.SharedPreferences$Editor;
import java.util.Set;
import android.content.Context;
import android.content.SharedPreferences;

public final class eg
{
    private static SharedPreferences b;
    private final AppLovinSdkImpl a;
    private final SharedPreferences c;
    
    eg(final AppLovinSdkImpl a) {
        this.a = a;
        this.c = a.getApplicationContext().getSharedPreferences("com.applovin.sdk.preferences." + a.getSdkKey(), 0);
    }
    
    private static SharedPreferences a(final Context context) {
        if (eg.b == null) {
            eg.b = context.getSharedPreferences("com.applovin.sdk.shared", 0);
        }
        return eg.b;
    }
    
    private static <T> T a(final String s, final T t, final Class clazz, final SharedPreferences sharedPreferences, final AppLovinSdkImpl appLovinSdkImpl) {
        try {
            if (sharedPreferences.contains(s)) {
                Object o;
                if (Boolean.class.equals(clazz)) {
                    if (t != null) {
                        o = sharedPreferences.getBoolean(s, (boolean)t);
                    }
                    else {
                        o = sharedPreferences.getBoolean(s, false);
                    }
                }
                else if (Float.class.equals(clazz)) {
                    if (t != null) {
                        o = sharedPreferences.getFloat(s, (float)t);
                    }
                    else {
                        o = sharedPreferences.getFloat(s, 0.0f);
                    }
                }
                else if (Integer.class.equals(clazz)) {
                    if (t != null) {
                        o = sharedPreferences.getInt(s, (int)t);
                    }
                    else {
                        o = sharedPreferences.getInt(s, 0);
                    }
                }
                else if (Long.class.equals(clazz)) {
                    if (t != null) {
                        o = sharedPreferences.getLong(s, (long)t);
                    }
                    else {
                        o = sharedPreferences.getLong(s, 0L);
                    }
                }
                else if (String.class.equals(clazz)) {
                    o = sharedPreferences.getString(s, (String)t);
                }
                else if (Set.class.isAssignableFrom(clazz)) {
                    if (ab.b()) {
                        o = sharedPreferences.getStringSet(s, (Set)t);
                    }
                    else {
                        if (appLovinSdkImpl != null) {
                            appLovinSdkImpl.getLogger().e("SharedPreferencesManager", "Attempting to get string set on older Android version for key: " + s);
                        }
                        o = t;
                    }
                }
                else {
                    o = t;
                }
                if (o != null) {
                    return (T)clazz.cast(o);
                }
            }
        }
        catch (Throwable t2) {
            if (appLovinSdkImpl != null) {
                appLovinSdkImpl.getLogger().e("SharedPreferencesManager", "Error getting value for key: " + s, t2);
            }
        }
        return t;
    }
    
    private static void a(final SharedPreferences$Editor sharedPreferences$Editor, final AppLovinSdkImpl appLovinSdkImpl) {
        if (appLovinSdkImpl != null && appLovinSdkImpl.get(ea.dg)) {
            sharedPreferences$Editor.apply();
            return;
        }
        sharedPreferences$Editor.commit();
    }
    
    static <T> void a(final ef<T> ef, final T t, final Context context) {
        a(ef.a(), t, a(context), null);
    }
    
    private static <T> void a(final String s, final T t, final SharedPreferences sharedPreferences, final AppLovinSdkImpl appLovinSdkImpl) {
        final SharedPreferences$Editor edit = sharedPreferences.edit();
        int n = 1;
        if (t instanceof Boolean) {
            edit.putBoolean(s, (boolean)t);
        }
        else if (t instanceof Float) {
            edit.putFloat(s, (float)t);
        }
        else if (t instanceof Integer) {
            edit.putInt(s, (int)t);
        }
        else if (t instanceof Long) {
            edit.putLong(s, (long)t);
        }
        else if (t instanceof String) {
            edit.putString(s, (String)t);
        }
        else if (t instanceof Set) {
            if (ab.b()) {
                edit.putStringSet(s, (Set)t);
            }
            else {
                if (appLovinSdkImpl != null) {
                    appLovinSdkImpl.getLogger().e("SharedPreferencesManager", "Attempting to put string set on older Android version for key: " + s + " and value: " + t);
                }
                n = 0;
            }
        }
        else {
            if (appLovinSdkImpl != null) {
                appLovinSdkImpl.getLogger().e("SharedPreferencesManager", "Unable to put default value of invalid type: " + t);
            }
            n = 0;
        }
        if (n != 0) {
            a(edit, appLovinSdkImpl);
        }
    }
    
    static <T> T b(final ef<T> ef, final T t, final Context context) {
        return a(ef.a(), t, ef.b(), a(context), null);
    }
    
     <T> T a(final String s, final T t, final Class clazz, final SharedPreferences sharedPreferences) {
        return a(s, t, clazz, sharedPreferences, this.a);
    }
    
    void a(final SharedPreferences sharedPreferences) {
        a(sharedPreferences.edit().clear(), this.a);
    }
    
     <T> void a(final ef<T> ef) {
        a(this.c.edit().remove(ef.a()), this.a);
    }
    
     <T> void a(final ef<T> ef, final T t) {
        this.a(ef, t, this.c);
    }
    
     <T> void a(final ef<T> ef, final T t, final SharedPreferences sharedPreferences) {
        this.a(ef.a(), t, sharedPreferences);
    }
    
     <T> void a(final String s, final T t, final SharedPreferences sharedPreferences) {
        a(s, t, sharedPreferences, this.a);
    }
    
     <T> T b(final ef<T> ef, final T t) {
        return this.b(ef, t, this.c);
    }
    
     <T> T b(final ef<T> ef, final T t, final SharedPreferences sharedPreferences) {
        return this.a(ef.a(), t, ef.b(), sharedPreferences);
    }
}
