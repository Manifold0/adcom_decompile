// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.player;

import java.lang.reflect.Proxy;
import java.util.List;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationHandler;
import java.util.Arrays;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Field;
import java.lang.reflect.Constructor;
import java.util.Iterator;
import java.util.ArrayList;
import java.lang.reflect.Member;
import java.lang.reflect.Array;

final class ReflectionHelper
{
    protected static boolean LOG;
    protected static final boolean LOGV = false;
    private static a[] a;
    
    static {
        ReflectionHelper.LOG = false;
        ReflectionHelper.a = new a[4096];
    }
    
    private static float a(Class subclass, final Class clazz) {
        if (subclass.equals(clazz)) {
            return 1.0f;
        }
        if (!subclass.isPrimitive() && !clazz.isPrimitive()) {
            try {
                if (subclass.asSubclass((Class<Object>)clazz) != null) {
                    return 0.5f;
                }
            }
            catch (ClassCastException ex) {}
            try {
                subclass = clazz.asSubclass(subclass);
                if (subclass != null) {
                    return 0.1f;
                }
            }
            catch (ClassCastException ex2) {}
        }
        return 0.0f;
    }
    
    private static float a(final Class clazz, final Class[] array, final Class[] array2) {
        int n = 0;
        if (array2.length == 0) {
            return 0.1f;
        }
        int length;
        if (array == null) {
            length = 0;
        }
        else {
            length = array.length;
        }
        if (length + 1 != array2.length) {
            return 0.0f;
        }
        float n3;
        float n2 = n3 = 1.0f;
        if (array != null) {
            final int length2 = array.length;
            int n4 = 0;
            while (true) {
                n3 = n2;
                if (n >= length2) {
                    break;
                }
                n2 *= a(array[n], array2[n4]);
                ++n;
                ++n4;
            }
        }
        return n3 * a(clazz, array2[array2.length - 1]);
    }
    
    private static Class a(String s, final int[] array) {
        Label_0214: {
            int index = 0;
            Block_4: {
                while (array[0] < s.length()) {
                    final int n = array[0];
                    array[0] = n + 1;
                    final char char1 = s.charAt(n);
                    if (char1 != '(' && char1 != ')') {
                        if (char1 != 'L') {
                            break Label_0214;
                        }
                        index = s.indexOf(59, array[0]);
                        if (index != -1) {
                            break Block_4;
                        }
                        break Label_0214;
                    }
                }
                break Label_0214;
            }
            s = s.substring(array[0], index);
            array[0] = index + 1;
            s = s.replace('/', '.');
            try {
                return Class.forName(s);
                // iftrue(Label_0100:, char1 != 'Z')
                return Boolean.TYPE;
                return null;
                Label_0130: {
                    return Byte.TYPE;
                }
                // iftrue(Label_0140:, char1 != 'B')
                Label_0170: {
                    return Array.newInstance(a(s, array), 0).getClass();
                }
                // iftrue(Label_0189:, char1 != '[')
                Label_0110: {
                    return Float.TYPE;
                }
                // iftrue(Label_0120:, char1 != 'F')
                Label_0189: {
                    final char char1;
                    g.Log(5, "! parseType; " + char1 + " is not known!");
                }
                return null;
                Label_0160:
                // iftrue(Label_0170:, char1 != 'D')
                return Double.TYPE;
                Label_0150:
                // iftrue(Label_0160:, char1 != 'J')
                return Long.TYPE;
                Label_0140:
                // iftrue(Label_0150:, char1 != 'S')
                return Short.TYPE;
                Label_0120:
                // iftrue(Label_0130:, char1 != 'V')
                return Void.TYPE;
                Label_0100:
                // iftrue(Label_0110:, char1 != 'I')
                return Integer.TYPE;
            }
            catch (ClassNotFoundException ex) {
                return null;
            }
        }
    }
    
    private static void a(final a a, final Member a2) {
        a.a = a2;
        ReflectionHelper.a[a.hashCode() & ReflectionHelper.a.length - 1] = a;
    }
    
    private static boolean a(final a a) {
        final a a2 = ReflectionHelper.a[a.hashCode() & ReflectionHelper.a.length - 1];
        if (!a.equals(a2)) {
            return false;
        }
        a.a = a2.a;
        return true;
    }
    
    private static Class[] a(final String s) {
        final int[] array = { 0 };
        final ArrayList<Class> list = new ArrayList<Class>();
        while (array[0] < s.length()) {
            final Class a = a(s, array);
            if (a == null) {
                break;
            }
            list.add(a);
        }
        final Class[] array2 = new Class[list.size()];
        final Iterator<Class> iterator = list.iterator();
        int n = 0;
        while (iterator.hasNext()) {
            array2[n] = iterator.next();
            ++n;
        }
        return array2;
    }
    
    protected static Constructor getConstructorID(final Class clazz, final String s) {
        if (ReflectionHelper.LOG) {
            g.Log(3, "? getConstructorID(\"" + clazz.getName() + "\", \"" + s + "\")");
        }
        Constructor constructor = null;
        final a a = new a(clazz, "", s);
        if (a(a)) {
            constructor = (Constructor)a.a;
        }
        else {
            final Class[] a2 = a(s);
            float n = 0.0f;
            final Constructor[] constructors = clazz.getConstructors();
            for (int length = constructors.length, i = 0; i < length; ++i) {
                final Constructor constructor2 = constructors[i];
                final float a3 = a(Void.TYPE, constructor2.getParameterTypes(), a2);
                if (a3 > n) {
                    constructor = constructor2;
                    if (a3 == 1.0f) {
                        break;
                    }
                    constructor = constructor2;
                    n = a3;
                }
            }
            a(a, constructor);
        }
        if (constructor == null) {
            throw new NoSuchMethodError("<init>" + s + " in class " + clazz.getName());
        }
        if (ReflectionHelper.LOG) {
            final StringBuilder sb = new StringBuilder();
            final Class[] parameterTypes = constructor.getParameterTypes();
            for (int length2 = parameterTypes.length, j = 0; j < length2; ++j) {
                final Class clazz2 = parameterTypes[j];
                if (sb.length() != 0) {
                    sb.append(", ");
                }
                sb.append(clazz2.getSimpleName());
            }
            g.Log(3, "! " + constructor.getName() + "(" + sb.toString() + ");");
        }
        return constructor;
    }
    
    protected static Field getFieldID(Class clazz, final String s, final String s2, final boolean b) {
        if (ReflectionHelper.LOG) {
            final StringBuilder append = new StringBuilder("? getFieldID(\"").append(clazz.getName()).append("\", \"").append(s).append("\", \"").append(s2).append("\", ");
            String s3;
            if (b) {
                s3 = "static)";
            }
            else {
                s3 = "non-static)";
            }
            g.Log(3, append.append(s3).toString());
        }
        final a a = new a(clazz, s, s2);
        Field field = null;
        if (a(a)) {
            field = (Field)a.a;
        }
        else {
            final Class[] a2 = a(s2);
            final Field field2 = null;
            float n = 0.0f;
            Class superclass = clazz;
            Field field3 = field2;
        Label_0287_Outer:
            while (true) {
                field = field3;
                if (superclass != null) {
                    final Field[] declaredFields = superclass.getDeclaredFields();
                    final int length = declaredFields.length;
                    int i = 0;
                    while (true) {
                        while (i < length) {
                            final Field field4 = declaredFields[i];
                            if (b == Modifier.isStatic(field4.getModifiers()) && field4.getName().compareTo(s) == 0) {
                                final float a3 = a(field4.getType(), null, a2);
                                if (a3 > n) {
                                    if (a3 != 1.0f) {
                                        field3 = field4;
                                        n = a3;
                                    }
                                    else {
                                        n = a3;
                                        field3 = field4;
                                        field = field3;
                                        if (n == 1.0f) {
                                            break Label_0287_Outer;
                                        }
                                        field = field3;
                                        if (superclass.isPrimitive()) {
                                            break Label_0287_Outer;
                                        }
                                        field = field3;
                                        if (superclass.isInterface()) {
                                            break Label_0287_Outer;
                                        }
                                        field = field3;
                                        if (superclass.equals(Object.class)) {
                                            break Label_0287_Outer;
                                        }
                                        field = field3;
                                        if (!superclass.equals(Void.TYPE)) {
                                            superclass = superclass.getSuperclass();
                                            continue Label_0287_Outer;
                                        }
                                        break Label_0287_Outer;
                                    }
                                }
                            }
                            while (true) {
                                ++i;
                                continue Label_0287_Outer;
                                continue;
                            }
                        }
                        continue;
                    }
                }
                break;
            }
            a(a, field);
            clazz = superclass;
        }
        if (field == null) {
            String s4;
            if (b) {
                s4 = "static";
            }
            else {
                s4 = "non-static";
            }
            throw new NoSuchFieldError(String.format("no %s field with name='%s' signature='%s' in class L%s;", s4, s, s2, clazz.getName()));
        }
        if (ReflectionHelper.LOG) {
            g.Log(3, "! " + field.getType().getSimpleName() + " " + field.getDeclaringClass().getSimpleName() + "." + field.getName() + ";");
        }
        return field;
    }
    
    protected static Method getMethodID(final Class clazz, final String s, final String s2, final boolean b) {
        if (ReflectionHelper.LOG) {
            final StringBuilder append = new StringBuilder("? getMethodID(\"").append(clazz.getName()).append("\", \"").append(s).append("\", \"").append(s2).append("\", ");
            String s3;
            if (b) {
                s3 = "static)";
            }
            else {
                s3 = "non-static)";
            }
            g.Log(3, append.append(s3).toString());
        }
        final a a = new a(clazz, s, s2);
        Class superclass;
        Method method2;
        if (a(a)) {
            final Method method = (Method)a.a;
            superclass = clazz;
            method2 = method;
        }
        else {
            final Class[] a2 = a(s2);
            final Method method3 = null;
            float n = 0.0f;
            superclass = clazz;
            Method method4 = method3;
            Method method5 = null;
        Label_0297_Outer:
            while (true) {
                method5 = method4;
                if (superclass != null) {
                    final Method[] declaredMethods = superclass.getDeclaredMethods();
                    final int length = declaredMethods.length;
                    int i = 0;
                    while (true) {
                        while (i < length) {
                            final Method method6 = declaredMethods[i];
                            if (b == Modifier.isStatic(method6.getModifiers()) && method6.getName().compareTo(s) == 0) {
                                final float a3 = a(method6.getReturnType(), method6.getParameterTypes(), a2);
                                if (a3 > n) {
                                    if (a3 != 1.0f) {
                                        method4 = method6;
                                        n = a3;
                                    }
                                    else {
                                        n = a3;
                                        method4 = method6;
                                        method5 = method4;
                                        if (n == 1.0f) {
                                            break Label_0297_Outer;
                                        }
                                        method5 = method4;
                                        if (superclass.isPrimitive()) {
                                            break Label_0297_Outer;
                                        }
                                        method5 = method4;
                                        if (superclass.isInterface()) {
                                            break Label_0297_Outer;
                                        }
                                        method5 = method4;
                                        if (superclass.equals(Object.class)) {
                                            break Label_0297_Outer;
                                        }
                                        method5 = method4;
                                        if (!superclass.equals(Void.TYPE)) {
                                            superclass = superclass.getSuperclass();
                                            continue Label_0297_Outer;
                                        }
                                        break Label_0297_Outer;
                                    }
                                }
                            }
                            while (true) {
                                ++i;
                                continue Label_0297_Outer;
                                continue;
                            }
                        }
                        continue;
                    }
                }
                break;
            }
            a(a, method5);
            method2 = method5;
        }
        if (method2 == null) {
            String s4;
            if (b) {
                s4 = "static";
            }
            else {
                s4 = "non-static";
            }
            throw new NoSuchMethodError(String.format("no %s method with name='%s' signature='%s' in class L%s;", s4, s, s2, superclass.getName()));
        }
        if (ReflectionHelper.LOG) {
            final StringBuilder sb = new StringBuilder();
            final Class<?>[] parameterTypes = method2.getParameterTypes();
            for (int length2 = parameterTypes.length, j = 0; j < length2; ++j) {
                final Class<?> clazz2 = parameterTypes[j];
                if (sb.length() != 0) {
                    sb.append(", ");
                }
                sb.append(clazz2.getSimpleName());
            }
            g.Log(3, "! " + method2.getReturnType().getSimpleName() + " " + method2.getDeclaringClass().getSimpleName() + "." + method2.getName() + "(" + sb.toString() + ");");
        }
        return method2;
    }
    
    private static native void nativeProxyFinalize(final int p0);
    
    private static native Object nativeProxyInvoke(final int p0, final String p1, final Object[] p2);
    
    private static native void nativeProxyLogJNIInvokeException();
    
    protected static Object newProxyInstance(final int n, final Class clazz) {
        return newProxyInstance(n, new Class[] { clazz });
    }
    
    protected static Object newProxyInstance(final int n, final Class[] array) {
        if (ReflectionHelper.LOG) {
            g.Log(3, String.format("ReflectionHelper.Proxy(%d,%s)", n, Arrays.asList((Class<?>[])array)));
        }
        return Proxy.newProxyInstance(ReflectionHelper.class.getClassLoader(), array, new InvocationHandler() {
            private static Object a(Object invokeWithArguments, final Method method, final Object[] array) {
                Object[] array2 = array;
                Label_0011: {
                    if (array != null) {
                        break Label_0011;
                    }
                    try {
                        array2 = new Object[0];
                        final Class<?> declaringClass = method.getDeclaringClass();
                        final Constructor<MethodHandles.Lookup> declaredConstructor = MethodHandles.Lookup.class.getDeclaredConstructor(Class.class, Integer.TYPE);
                        declaredConstructor.setAccessible(true);
                        invokeWithArguments = declaredConstructor.newInstance(declaringClass, 2).in(declaringClass).unreflectSpecial(method, declaringClass).bindTo(invokeWithArguments).invokeWithArguments(array2);
                        return invokeWithArguments;
                    }
                    catch (NoClassDefFoundError noClassDefFoundError) {
                        g.Log(6, String.format("Java interface default methods are only supported since Android Oreo", new Object[0]));
                        nativeProxyLogJNIInvokeException();
                        return null;
                    }
                }
            }
            
            @Override
            protected final void finalize() {
                try {
                    if (ReflectionHelper.LOG) {
                        g.Log(3, String.format("ReflectionHelper.Proxy.finilize(%d, %s)", n, Arrays.asList((Class[])array)));
                    }
                    nativeProxyFinalize(n);
                }
                finally {
                    super.finalize();
                }
            }
            
            @Override
            public final Object invoke(final Object o, final Method method, final Object[] array) {
                if (ReflectionHelper.LOG) {
                    final int a = n;
                    final List<Class> list = Arrays.asList((Class[])array);
                    final String name = method.getName();
                    Object list2;
                    if (array == null) {
                        list2 = "<null>";
                    }
                    else {
                        list2 = Arrays.asList(array);
                    }
                    g.Log(3, String.format("ReflectionHelper.Proxy.invoke(%d, %s, %s, %s)", a, list, name, list2));
                }
                final Object a2 = nativeProxyInvoke(n, method.getName(), array);
                Object a3;
                if ((a3 = a2) == null) {
                    if ((method.getModifiers() & 0x400) != 0x0) {
                        nativeProxyLogJNIInvokeException();
                        return a2;
                    }
                    a3 = a(o, method, array);
                }
                return a3;
            }
        });
    }
    
    private static final class a
    {
        public volatile Member a;
        private final Class b;
        private final String c;
        private final String d;
        private final int e;
        
        a(final Class b, final String c, final String d) {
            this.b = b;
            this.c = c;
            this.d = d;
            this.e = ((this.b.hashCode() + 527) * 31 + this.c.hashCode()) * 31 + this.d.hashCode();
        }
        
        @Override
        public final boolean equals(final Object o) {
            if (o != this) {
                if (!(o instanceof a)) {
                    return false;
                }
                final a a = (a)o;
                if (this.e != a.e || !this.d.equals(a.d) || !this.c.equals(a.c) || !this.b.equals(a.b)) {
                    return false;
                }
            }
            return true;
        }
        
        @Override
        public final int hashCode() {
            return this.e;
        }
    }
}
