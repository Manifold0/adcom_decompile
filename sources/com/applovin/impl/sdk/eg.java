package com.applovin.impl.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.util.Set;

public final class eg {
    /* renamed from: b */
    private static SharedPreferences f2454b;
    /* renamed from: a */
    private final AppLovinSdkImpl f2455a;
    /* renamed from: c */
    private final SharedPreferences f2456c;

    eg(AppLovinSdkImpl appLovinSdkImpl) {
        this.f2455a = appLovinSdkImpl;
        this.f2456c = appLovinSdkImpl.getApplicationContext().getSharedPreferences("com.applovin.sdk.preferences." + appLovinSdkImpl.getSdkKey(), 0);
    }

    /* renamed from: a */
    private static SharedPreferences m2728a(Context context) {
        if (f2454b == null) {
            f2454b = context.getSharedPreferences("com.applovin.sdk.shared", 0);
        }
        return f2454b;
    }

    /* renamed from: a */
    private static <T> T m2729a(String str, T t, Class cls, SharedPreferences sharedPreferences, AppLovinSdkImpl appLovinSdkImpl) {
        try {
            if (!sharedPreferences.contains(str)) {
                return t;
            }
            Object valueOf;
            if (Boolean.class.equals(cls)) {
                valueOf = t != null ? Boolean.valueOf(sharedPreferences.getBoolean(str, ((Boolean) t).booleanValue())) : Boolean.valueOf(sharedPreferences.getBoolean(str, false));
            } else if (Float.class.equals(cls)) {
                valueOf = t != null ? Float.valueOf(sharedPreferences.getFloat(str, ((Float) t).floatValue())) : Float.valueOf(sharedPreferences.getFloat(str, 0.0f));
            } else if (Integer.class.equals(cls)) {
                valueOf = t != null ? Integer.valueOf(sharedPreferences.getInt(str, ((Integer) t).intValue())) : Integer.valueOf(sharedPreferences.getInt(str, 0));
            } else if (Long.class.equals(cls)) {
                valueOf = t != null ? Long.valueOf(sharedPreferences.getLong(str, ((Long) t).longValue())) : Long.valueOf(sharedPreferences.getLong(str, 0));
            } else if (String.class.equals(cls)) {
                valueOf = sharedPreferences.getString(str, (String) t);
            } else if (!Set.class.isAssignableFrom(cls)) {
                r2 = t;
            } else if (ab.m2200b()) {
                valueOf = sharedPreferences.getStringSet(str, (Set) t);
            } else {
                if (appLovinSdkImpl != null) {
                    appLovinSdkImpl.getLogger().mo4173e("SharedPreferencesManager", "Attempting to get string set on older Android version for key: " + str);
                }
                r2 = t;
            }
            return valueOf != null ? cls.cast(valueOf) : t;
        } catch (Throwable th) {
            if (appLovinSdkImpl == null) {
                return t;
            }
            appLovinSdkImpl.getLogger().mo4174e("SharedPreferencesManager", "Error getting value for key: " + str, th);
            return t;
        }
    }

    /* renamed from: a */
    private static void m2730a(Editor editor, AppLovinSdkImpl appLovinSdkImpl) {
        if (appLovinSdkImpl == null || !((Boolean) appLovinSdkImpl.get(ea.dg)).booleanValue()) {
            editor.commit();
        } else {
            editor.apply();
        }
    }

    /* renamed from: a */
    static <T> void m2731a(ef<T> efVar, T t, Context context) {
        m2732a(efVar.m2726a(), (Object) t, m2728a(context), null);
    }

    /* renamed from: a */
    private static <T> void m2732a(String str, T t, SharedPreferences sharedPreferences, AppLovinSdkImpl appLovinSdkImpl) {
        Editor edit = sharedPreferences.edit();
        Object obj = 1;
        if (t instanceof Boolean) {
            edit.putBoolean(str, ((Boolean) t).booleanValue());
        } else if (t instanceof Float) {
            edit.putFloat(str, ((Float) t).floatValue());
        } else if (t instanceof Integer) {
            edit.putInt(str, ((Integer) t).intValue());
        } else if (t instanceof Long) {
            edit.putLong(str, ((Long) t).longValue());
        } else if (t instanceof String) {
            edit.putString(str, (String) t);
        } else if (!(t instanceof Set)) {
            if (appLovinSdkImpl != null) {
                appLovinSdkImpl.getLogger().mo4173e("SharedPreferencesManager", "Unable to put default value of invalid type: " + t);
            }
            obj = null;
        } else if (ab.m2200b()) {
            edit.putStringSet(str, (Set) t);
        } else {
            if (appLovinSdkImpl != null) {
                appLovinSdkImpl.getLogger().mo4173e("SharedPreferencesManager", "Attempting to put string set on older Android version for key: " + str + " and value: " + t);
            }
            obj = null;
        }
        if (obj != null) {
            m2730a(edit, appLovinSdkImpl);
        }
    }

    /* renamed from: b */
    static <T> T m2733b(ef<T> efVar, T t, Context context) {
        return m2729a(efVar.m2726a(), t, efVar.m2727b(), m2728a(context), null);
    }

    /* renamed from: a */
    <T> T m2734a(String str, T t, Class cls, SharedPreferences sharedPreferences) {
        return m2729a(str, t, cls, sharedPreferences, this.f2455a);
    }

    /* renamed from: a */
    void m2735a(SharedPreferences sharedPreferences) {
        m2730a(sharedPreferences.edit().clear(), this.f2455a);
    }

    /* renamed from: a */
    <T> void m2736a(ef<T> efVar) {
        m2730a(this.f2456c.edit().remove(efVar.m2726a()), this.f2455a);
    }

    /* renamed from: a */
    <T> void m2737a(ef<T> efVar, T t) {
        m2738a((ef) efVar, (Object) t, this.f2456c);
    }

    /* renamed from: a */
    <T> void m2738a(ef<T> efVar, T t, SharedPreferences sharedPreferences) {
        m2739a(efVar.m2726a(), (Object) t, sharedPreferences);
    }

    /* renamed from: a */
    <T> void m2739a(String str, T t, SharedPreferences sharedPreferences) {
        m2732a(str, (Object) t, sharedPreferences, this.f2455a);
    }

    /* renamed from: b */
    <T> T m2740b(ef<T> efVar, T t) {
        return m2741b((ef) efVar, (Object) t, this.f2456c);
    }

    /* renamed from: b */
    <T> T m2741b(ef<T> efVar, T t, SharedPreferences sharedPreferences) {
        return m2734a(efVar.m2726a(), (Object) t, efVar.m2727b(), sharedPreferences);
    }
}
