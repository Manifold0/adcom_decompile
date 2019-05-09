package com.kongregate.android.internal.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* renamed from: com.kongregate.android.internal.util.n */
public class C0566n {
    /* renamed from: a */
    public static Object m777a(Class cls, Class[] clsArr, Object... objArr) {
        try {
            if (clsArr.length == 0) {
                return cls.newInstance();
            }
            return cls.getConstructor(clsArr).newInstance(objArr);
        } catch (Throwable e) {
            C0562j.m760c("Exception calling constructor on " + cls.getSimpleName(), e);
            return null;
        }
    }

    /* renamed from: a */
    public static Object m779a(String str, String str2, Class[] clsArr, Object... objArr) {
        try {
            return C0566n.m776a(Class.forName(str), str2, clsArr, objArr);
        } catch (Throwable e) {
            C0562j.m760c("Class not found: " + str + " attempting to invoke static method: " + str2, e);
            return null;
        }
    }

    /* renamed from: a */
    public static Object m776a(Class cls, String str, Class[] clsArr, Object... objArr) {
        Object obj = null;
        try {
            Method declaredMethod = cls.getDeclaredMethod(str, clsArr);
            declaredMethod.setAccessible(true);
            obj = declaredMethod.invoke(null, objArr);
        } catch (Throwable e) {
            C0562j.m760c("Exception executing static method " + str + " on class " + cls.getSimpleName(), e);
        }
        return obj;
    }

    /* renamed from: a */
    public static Object m778a(String str, String str2, Object obj, Class[] clsArr, Object... objArr) {
        return C0566n.m781a(false, str, str2, obj, clsArr, objArr);
    }

    /* renamed from: a */
    public static Object m781a(boolean z, String str, String str2, Object obj, Class[] clsArr, Object... objArr) {
        try {
            return C0566n.m780a(z, Class.forName(str), str2, obj, clsArr, objArr);
        } catch (ClassNotFoundException e) {
            if (!z) {
                C0562j.m759c("Class not found: " + str + " attempting to invoke static method: " + str2);
            }
            return null;
        }
    }

    /* renamed from: a */
    public static Object m775a(Class cls, String str, Object obj, Class[] clsArr, Object... objArr) {
        return C0566n.m780a(false, cls, str, obj, clsArr, objArr);
    }

    /* renamed from: a */
    public static Object m780a(boolean z, Class cls, String str, Object obj, Class[] clsArr, Object... objArr) {
        Method declaredMethod;
        if (clsArr != null) {
            try {
                declaredMethod = cls.getDeclaredMethod(str, clsArr);
            } catch (Throwable e) {
                if (!z) {
                    C0562j.m760c("Exception executing method " + str + " on class " + cls.getSimpleName(), e);
                }
                return null;
            }
        }
        declaredMethod = cls.getDeclaredMethod(str, new Class[0]);
        declaredMethod.setAccessible(true);
        return declaredMethod.invoke(obj, objArr);
    }

    /* renamed from: a */
    public static void m783a(Class cls, String str, Object obj, Object obj2) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            declaredField.set(obj2, obj);
        } catch (Throwable e) {
            C0562j.m762d("Error trying to set " + str + " on " + cls.getSimpleName(), e);
        } catch (Throwable e2) {
            C0562j.m762d("Error trying to set " + str + " on " + cls.getSimpleName(), e2);
        } catch (Throwable e22) {
            C0562j.m762d("Error trying to set " + str + " on " + cls.getSimpleName(), e22);
        }
    }

    /* renamed from: a */
    public static void m782a(Class cls, String str, Object obj) {
        C0566n.m783a(cls, str, obj, null);
    }

    /* renamed from: b */
    public static <T> T m786b(Class cls, String str, Object obj) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField.get(obj);
        } catch (Throwable e) {
            C0562j.m762d("Error trying to get " + str + " on " + cls.getSimpleName(), e);
            return null;
        } catch (Throwable e2) {
            C0562j.m762d("Error trying to get " + str + " on " + cls.getSimpleName(), e2);
            return null;
        } catch (Throwable e22) {
            C0562j.m762d("Error trying to get " + str + " on " + cls.getSimpleName(), e22);
            return null;
        } catch (Throwable e222) {
            C0562j.m762d("Error trying to get " + str + " on " + cls.getSimpleName(), e222);
            return null;
        }
    }

    /* renamed from: a */
    public static boolean m784a(Class cls, String str) {
        try {
            cls.getDeclaredField(str);
            return true;
        } catch (Throwable e) {
            C0562j.m762d("Error trying to get " + str + " on " + cls.getSimpleName(), e);
            return false;
        } catch (Throwable e2) {
            C0562j.m762d("Error trying to get " + str + " on " + cls.getSimpleName(), e2);
            return false;
        } catch (Throwable e22) {
            C0562j.m762d("Error trying to get " + str + " on " + cls.getSimpleName(), e22);
            return false;
        }
    }

    /* renamed from: b */
    public static <T> T m785b(Class cls, String str) {
        return C0566n.m786b(cls, str, null);
    }
}
