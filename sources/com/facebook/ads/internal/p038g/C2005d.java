package com.facebook.ads.internal.p038g;

import java.lang.reflect.Method;

/* renamed from: com.facebook.ads.internal.g.d */
public class C2005d {
    /* renamed from: a */
    public static Object m4830a(Object obj, Method method, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: a */
    public static Method m4831a(Class<?> cls, String str, Class<?>... clsArr) {
        try {
            return cls.getMethod(str, clsArr);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    /* renamed from: a */
    public static Method m4832a(String str, String str2, Class<?>... clsArr) {
        try {
            return C2005d.m4831a(Class.forName(str), str2, (Class[]) clsArr);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
}
