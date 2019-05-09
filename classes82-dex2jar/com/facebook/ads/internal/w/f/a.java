// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.w.f;

import android.text.TextUtils;
import android.os.Build$VERSION;
import android.content.Context;
import java.lang.reflect.Field;
import android.app.Application;
import android.support.annotation.Nullable;
import android.support.annotation.AnyThread;

@AnyThread
public final class a
{
    public static boolean a;
    @Nullable
    private static String b;
    
    @Nullable
    private static String a() {
        try {
            return (String)Application.class.getMethod("getProcessName", (Class<?>[])null).invoke(null, (Object[])null);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    @Nullable
    private static String a(final Application application) {
        try {
            final Field field = application.getClass().getField("mLoadedApk");
            field.setAccessible(true);
            final Object value = field.get(application);
            final Field declaredField = value.getClass().getDeclaredField("mActivityThread");
            declaredField.setAccessible(true);
            final Object value2 = declaredField.get(value);
            return (String)value2.getClass().getDeclaredMethod("getProcessName", (Class<?>[])null).invoke(value2, (Object[])null);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    @Nullable
    public static String a(Context applicationContext) {
        if (com.facebook.ads.internal.w.f.a.b != null) {
            return com.facebook.ads.internal.w.f.a.b;
        }
        if (Build$VERSION.SDK_INT >= 28) {
            return a();
        }
        applicationContext = applicationContext.getApplicationContext();
        if (applicationContext instanceof Application) {
            return com.facebook.ads.internal.w.f.a.b = a((Application)applicationContext);
        }
        return null;
    }
    
    public static String a(final String s, final Context context) {
        final String packageName = context.getPackageName();
        final String a = a(context);
        String string = s;
        if (!TextUtils.isEmpty((CharSequence)a)) {
            string = s;
            if (!packageName.equals(a)) {
                String s2 = a;
                if (a.contains(":")) {
                    s2 = a.split(":")[1];
                }
                string = s + "_" + s2;
            }
        }
        return string;
    }
}
