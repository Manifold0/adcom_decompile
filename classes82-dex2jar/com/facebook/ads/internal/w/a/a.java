// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.w.a;

import android.support.annotation.Nullable;
import java.util.Iterator;
import java.lang.reflect.Field;
import java.util.Map;
import android.app.Activity;

class a
{
    @Nullable
    static Activity a() {
        try {
            final Class<?> forName = Class.forName("android.app.ActivityThread");
            final Object invoke = forName.getMethod("currentActivityThread", (Class<?>[])new Class[0]).invoke(null, new Object[0]);
            final Field declaredField = forName.getDeclaredField("mActivities");
            declaredField.setAccessible(true);
            final Map map = (Map)declaredField.get(invoke);
            if (map == null) {
                return null;
            }
            for (final Object next : map.values()) {
                final Class<?> class1 = next.getClass();
                final Field declaredField2 = class1.getDeclaredField("paused");
                declaredField2.setAccessible(true);
                if (!declaredField2.getBoolean(next)) {
                    final Field declaredField3 = class1.getDeclaredField("activity");
                    declaredField3.setAccessible(true);
                    return (Activity)declaredField3.get(next);
                }
            }
            return null;
        }
        catch (Exception ex) {
            return null;
        }
    }
}
