package com.facebook.ads.internal.p025w.p068a;

import android.app.Activity;
import android.support.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.Map;

/* renamed from: com.facebook.ads.internal.w.a.a */
class C2563a {
    @Nullable
    /* renamed from: a */
    static Activity m6612a() {
        try {
            Class cls = Class.forName("android.app.ActivityThread");
            Object invoke = cls.getMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]);
            Field declaredField = cls.getDeclaredField("mActivities");
            declaredField.setAccessible(true);
            Map map = (Map) declaredField.get(invoke);
            if (map == null) {
                return null;
            }
            for (Object invoke2 : map.values()) {
                Class cls2 = invoke2.getClass();
                Field declaredField2 = cls2.getDeclaredField("paused");
                declaredField2.setAccessible(true);
                if (!declaredField2.getBoolean(invoke2)) {
                    declaredField = cls2.getDeclaredField("activity");
                    declaredField.setAccessible(true);
                    return (Activity) declaredField.get(invoke2);
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
