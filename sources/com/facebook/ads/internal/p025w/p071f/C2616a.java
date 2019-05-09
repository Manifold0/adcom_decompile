package com.facebook.ads.internal.p025w.p071f;

import android.app.Application;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.AnyThread;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import java.lang.reflect.Field;

@AnyThread
/* renamed from: com.facebook.ads.internal.w.f.a */
public final class C2616a {
    /* renamed from: a */
    public static boolean f6479a;
    @Nullable
    /* renamed from: b */
    private static String f6480b;

    @Nullable
    /* renamed from: a */
    private static String m6727a() {
        try {
            return (String) Application.class.getMethod("getProcessName", (Class[]) null).invoke(null, (Object[]) null);
        } catch (Exception e) {
            return null;
        }
    }

    @Nullable
    /* renamed from: a */
    private static String m6728a(Application application) {
        try {
            Field field = application.getClass().getField("mLoadedApk");
            field.setAccessible(true);
            Object obj = field.get(application);
            Field declaredField = obj.getClass().getDeclaredField("mActivityThread");
            declaredField.setAccessible(true);
            Object obj2 = declaredField.get(obj);
            return (String) obj2.getClass().getDeclaredMethod("getProcessName", (Class[]) null).invoke(obj2, (Object[]) null);
        } catch (Exception e) {
            return null;
        }
    }

    @Nullable
    /* renamed from: a */
    public static String m6729a(Context context) {
        if (f6480b != null) {
            return f6480b;
        }
        if (VERSION.SDK_INT >= 28) {
            return C2616a.m6727a();
        }
        Context applicationContext = context.getApplicationContext();
        if (!(applicationContext instanceof Application)) {
            return null;
        }
        f6480b = C2616a.m6728a((Application) applicationContext);
        return f6480b;
    }

    /* renamed from: a */
    public static String m6730a(String str, Context context) {
        String packageName = context.getPackageName();
        String a = C2616a.m6729a(context);
        if (TextUtils.isEmpty(a) || packageName.equals(a)) {
            return str;
        }
        if (a.contains(":")) {
            a = a.split(":")[1];
        }
        return str + "_" + a;
    }
}
