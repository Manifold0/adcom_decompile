package com.unity3d.player;

import com.ironsource.mediationsdk.logger.IronSourceError;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

final class ReflectionHelper {
    protected static boolean LOG = false;
    protected static final boolean LOGV = false;
    /* renamed from: a */
    private static C0210a[] f50a = new C0210a[4096];

    /* renamed from: com.unity3d.player.ReflectionHelper$a */
    private static class C0210a {
        /* renamed from: a */
        public volatile Member f45a;
        /* renamed from: b */
        private final Class f46b;
        /* renamed from: c */
        private final String f47c;
        /* renamed from: d */
        private final String f48d;
        /* renamed from: e */
        private final int f49e = (((((this.f46b.hashCode() + IronSourceError.ERROR_NON_EXISTENT_INSTANCE) * 31) + this.f47c.hashCode()) * 31) + this.f48d.hashCode());

        C0210a(Class cls, String str, String str2) {
            this.f46b = cls;
            this.f47c = str;
            this.f48d = str2;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof C0210a)) {
                return false;
            }
            C0210a c0210a = (C0210a) obj;
            return this.f49e == c0210a.f49e && this.f48d.equals(c0210a.f48d) && this.f47c.equals(c0210a.f47c) && this.f46b.equals(c0210a.f46b);
        }

        public final int hashCode() {
            return this.f49e;
        }
    }

    ReflectionHelper() {
    }

    /* renamed from: a */
    private static float m51a(Class cls, Class cls2) {
        if (cls.equals(cls2)) {
            return 1.0f;
        }
        if (!(cls.isPrimitive() || cls2.isPrimitive())) {
            try {
                if (cls.asSubclass(cls2) != null) {
                    return 0.5f;
                }
            } catch (ClassCastException e) {
            }
            try {
                if (cls2.asSubclass(cls) != null) {
                    return 0.1f;
                }
            } catch (ClassCastException e2) {
            }
        }
        return 0.0f;
    }

    /* renamed from: a */
    private static float m52a(Class cls, Class[] clsArr, Class[] clsArr2) {
        int i = 0;
        if (clsArr2.length == 0) {
            return 0.1f;
        }
        if ((clsArr == null ? 0 : clsArr.length) + 1 != clsArr2.length) {
            return 0.0f;
        }
        float f = 1.0f;
        if (clsArr != null) {
            int i2 = 0;
            while (i < clsArr.length) {
                f *= m51a(clsArr[i], clsArr2[i2]);
                i++;
                i2++;
            }
        }
        return f * m51a(cls, clsArr2[clsArr2.length - 1]);
    }

    /* renamed from: a */
    private static Class m53a(String str, int[] iArr) {
        while (iArr[0] < str.length()) {
            int i = iArr[0];
            iArr[0] = i + 1;
            char charAt = str.charAt(i);
            if (charAt != '(' && charAt != ')') {
                if (charAt == 'L') {
                    i = str.indexOf(59, iArr[0]);
                    if (i != -1) {
                        String substring = str.substring(iArr[0], i);
                        iArr[0] = i + 1;
                        try {
                            return Class.forName(substring.replace('/', '.'));
                        } catch (ClassNotFoundException e) {
                        }
                    }
                } else if (charAt == 'Z') {
                    return Boolean.TYPE;
                } else {
                    if (charAt == 'I') {
                        return Integer.TYPE;
                    }
                    if (charAt == 'F') {
                        return Float.TYPE;
                    }
                    if (charAt == 'V') {
                        return Void.TYPE;
                    }
                    if (charAt == 'B') {
                        return Byte.TYPE;
                    }
                    if (charAt == 'S') {
                        return Short.TYPE;
                    }
                    if (charAt == 'J') {
                        return Long.TYPE;
                    }
                    if (charAt == 'D') {
                        return Double.TYPE;
                    }
                    if (charAt == '[') {
                        return Array.newInstance(m53a(str, iArr), 0).getClass();
                    }
                    C0243g.Log(5, "! parseType; " + charAt + " is not known!");
                }
                return null;
            }
        }
        return null;
    }

    /* renamed from: a */
    private static void m57a(C0210a c0210a, Member member) {
        c0210a.f45a = member;
        f50a[c0210a.hashCode() & (f50a.length - 1)] = c0210a;
    }

    /* renamed from: a */
    private static boolean m58a(C0210a c0210a) {
        C0210a c0210a2 = f50a[c0210a.hashCode() & (f50a.length - 1)];
        if (!c0210a.equals(c0210a2)) {
            return false;
        }
        c0210a.f45a = c0210a2.f45a;
        return true;
    }

    /* renamed from: a */
    private static Class[] m59a(String str) {
        int[] iArr = new int[]{0};
        ArrayList arrayList = new ArrayList();
        while (iArr[0] < str.length()) {
            Class a = m53a(str, iArr);
            if (a == null) {
                break;
            }
            arrayList.add(a);
        }
        Class[] clsArr = new Class[arrayList.size()];
        Iterator it = arrayList.iterator();
        int i = 0;
        while (it.hasNext()) {
            int i2 = i + 1;
            clsArr[i] = (Class) it.next();
            i = i2;
        }
        return clsArr;
    }

    protected static Constructor getConstructorID(Class cls, String str) {
        Constructor constructor;
        int i;
        if (LOG) {
            C0243g.Log(3, "? getConstructorID(\"" + cls.getName() + "\", \"" + str + "\")");
        }
        Constructor constructor2 = null;
        C0210a c0210a = new C0210a(cls, "", str);
        if (m58a(c0210a)) {
            constructor = (Constructor) c0210a.f45a;
        } else {
            Class[] a = m59a(str);
            float f = 0.0f;
            Constructor[] constructors = cls.getConstructors();
            int length = constructors.length;
            i = 0;
            while (i < length) {
                Constructor constructor3;
                constructor = constructors[i];
                float a2 = m52a(Void.TYPE, constructor.getParameterTypes(), a);
                if (a2 > f) {
                    if (a2 == 1.0f) {
                        break;
                    }
                    constructor3 = constructor;
                } else {
                    a2 = f;
                    constructor3 = constructor2;
                }
                i++;
                constructor2 = constructor3;
                f = a2;
            }
            constructor = constructor2;
            m57a(c0210a, r2);
        }
        if (constructor == null) {
            throw new NoSuchMethodError("<init>" + str + " in class " + cls.getName());
        }
        if (LOG) {
            StringBuilder stringBuilder = new StringBuilder();
            for (Class cls2 : constructor.getParameterTypes()) {
                if (stringBuilder.length() != 0) {
                    stringBuilder.append(", ");
                }
                stringBuilder.append(cls2.getSimpleName());
            }
            C0243g.Log(3, "! " + constructor.getName() + "(" + stringBuilder.toString() + ");");
        }
        return constructor;
    }

    protected static Field getFieldID(Class cls, String str, String str2, boolean z) {
        Field field;
        if (LOG) {
            C0243g.Log(3, "? getFieldID(\"" + cls.getName() + "\", \"" + str + "\", \"" + str2 + "\", " + (z ? "static)" : "non-static)"));
        }
        C0210a c0210a = new C0210a(cls, str, str2);
        if (m58a(c0210a)) {
            field = (Field) c0210a.f45a;
        } else {
            Class[] a = m59a(str2);
            field = null;
            float f = 0.0f;
            while (cls != null) {
                Field[] declaredFields = cls.getDeclaredFields();
                int length = declaredFields.length;
                int i = 0;
                Field field2 = field;
                while (i < length) {
                    float a2;
                    Field field3;
                    Field field4 = declaredFields[i];
                    if (z == Modifier.isStatic(field4.getModifiers()) && field4.getName().compareTo(str) == 0) {
                        a2 = m52a(field4.getType(), null, a);
                        if (a2 > f) {
                            if (a2 == 1.0f) {
                                f = a2;
                                field = field4;
                                break;
                            }
                            field3 = field4;
                            i++;
                            field2 = field3;
                            f = a2;
                        }
                    }
                    a2 = f;
                    field3 = field2;
                    i++;
                    field2 = field3;
                    f = a2;
                }
                field = field2;
                if (f == 1.0f || cls.isPrimitive() || cls.isInterface() || cls.equals(Object.class) || cls.equals(Void.TYPE)) {
                    break;
                }
                cls = cls.getSuperclass();
            }
            m57a(c0210a, r0);
        }
        if (field == null) {
            String str3 = "no %s field with name='%s' signature='%s' in class L%s;";
            Object[] objArr = new Object[4];
            objArr[0] = z ? "static" : "non-static";
            objArr[1] = str;
            objArr[2] = str2;
            objArr[3] = cls.getName();
            throw new NoSuchFieldError(String.format(str3, objArr));
        }
        if (LOG) {
            C0243g.Log(3, "! " + field.getType().getSimpleName() + " " + field.getDeclaringClass().getSimpleName() + "." + field.getName() + ";");
        }
        return field;
    }

    protected static Method getMethodID(Class cls, String str, String str2, boolean z) {
        Method method;
        int i;
        if (LOG) {
            C0243g.Log(3, "? getMethodID(\"" + cls.getName() + "\", \"" + str + "\", \"" + str2 + "\", " + (z ? "static)" : "non-static)"));
        }
        C0210a c0210a = new C0210a(cls, str, str2);
        if (m58a(c0210a)) {
            method = (Method) c0210a.f45a;
        } else {
            Member member;
            Class[] a = m59a(str2);
            Member member2 = null;
            float f = 0.0f;
            while (cls != null) {
                Method[] declaredMethods = cls.getDeclaredMethods();
                int length = declaredMethods.length;
                i = 0;
                Member member3 = member2;
                while (i < length) {
                    float a2;
                    Object obj;
                    Method method2 = declaredMethods[i];
                    if (z == Modifier.isStatic(method2.getModifiers()) && method2.getName().compareTo(str) == 0) {
                        a2 = m52a(method2.getReturnType(), method2.getParameterTypes(), a);
                        if (a2 > f) {
                            if (a2 == 1.0f) {
                                f = a2;
                                member2 = method2;
                                break;
                            }
                            method = method2;
                            i++;
                            obj = method;
                            f = a2;
                        }
                    }
                    a2 = f;
                    member = member3;
                    i++;
                    obj = method;
                    f = a2;
                }
                member2 = member3;
                if (f == 1.0f || cls.isPrimitive() || cls.isInterface() || cls.equals(Object.class) || cls.equals(Void.TYPE)) {
                    break;
                }
                cls = cls.getSuperclass();
            }
            m57a(c0210a, member2);
            member = member2;
        }
        if (method == null) {
            String str3 = "no %s method with name='%s' signature='%s' in class L%s;";
            Object[] objArr = new Object[4];
            objArr[0] = z ? "static" : "non-static";
            objArr[1] = str;
            objArr[2] = str2;
            objArr[3] = cls.getName();
            throw new NoSuchMethodError(String.format(str3, objArr));
        }
        if (LOG) {
            StringBuilder stringBuilder = new StringBuilder();
            for (Class cls2 : method.getParameterTypes()) {
                if (stringBuilder.length() != 0) {
                    stringBuilder.append(", ");
                }
                stringBuilder.append(cls2.getSimpleName());
            }
            C0243g.Log(3, "! " + method.getReturnType().getSimpleName() + " " + method.getDeclaringClass().getSimpleName() + "." + method.getName() + "(" + stringBuilder.toString() + ");");
        }
        return method;
    }

    private static native void nativeProxyFinalize(int i);

    private static native Object nativeProxyInvoke(int i, String str, Object[] objArr);

    private static native void nativeProxyLogJNIInvokeException();

    protected static Object newProxyInstance(int i, Class cls) {
        return newProxyInstance(i, new Class[]{cls});
    }

    protected static Object newProxyInstance(final int i, final Class[] clsArr) {
        if (LOG) {
            C0243g.Log(3, String.format("ReflectionHelper.Proxy(%d,%s)", new Object[]{Integer.valueOf(i), Arrays.asList(clsArr)}));
        }
        return Proxy.newProxyInstance(ReflectionHelper.class.getClassLoader(), clsArr, new InvocationHandler() {
            /* renamed from: a */
            private static Object m50a(Object obj, Method method, Object[] objArr) {
                if (objArr == null) {
                    try {
                        objArr = new Object[0];
                    } catch (NoClassDefFoundError e) {
                        C0243g.Log(6, String.format("Java interface default methods are only supported since Android Oreo", new Object[0]));
                        ReflectionHelper.nativeProxyLogJNIInvokeException();
                        return null;
                    }
                }
                Class declaringClass = method.getDeclaringClass();
                Constructor declaredConstructor = Lookup.class.getDeclaredConstructor(new Class[]{Class.class, Integer.TYPE});
                declaredConstructor.setAccessible(true);
                return ((Lookup) declaredConstructor.newInstance(new Object[]{declaringClass, Integer.valueOf(2)})).in(declaringClass).unreflectSpecial(method, declaringClass).bindTo(obj).invokeWithArguments(objArr);
            }

            protected final void finalize() {
                try {
                    if (ReflectionHelper.LOG) {
                        C0243g.Log(3, String.format("ReflectionHelper.Proxy.finilize(%d, %s)", new Object[]{Integer.valueOf(i), Arrays.asList(clsArr)}));
                    }
                    ReflectionHelper.nativeProxyFinalize(i);
                } finally {
                    super.finalize();
                }
            }

            public final Object invoke(Object obj, Method method, Object[] objArr) {
                if (ReflectionHelper.LOG) {
                    String str = "ReflectionHelper.Proxy.invoke(%d, %s, %s, %s)";
                    Object[] objArr2 = new Object[4];
                    objArr2[0] = Integer.valueOf(i);
                    objArr2[1] = Arrays.asList(clsArr);
                    objArr2[2] = method.getName();
                    objArr2[3] = objArr == null ? "<null>" : Arrays.asList(objArr);
                    C0243g.Log(3, String.format(str, objArr2));
                }
                Object a = ReflectionHelper.nativeProxyInvoke(i, method.getName(), objArr);
                if (a != null) {
                    return a;
                }
                if ((method.getModifiers() & 1024) == 0) {
                    return C02091.m50a(obj, method, objArr);
                }
                ReflectionHelper.nativeProxyLogJNIInvokeException();
                return a;
            }
        });
    }
}
