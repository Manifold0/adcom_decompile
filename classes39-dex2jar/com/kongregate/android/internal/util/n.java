// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.android.internal.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class n
{
    public static Object a(final Class clazz, final String s, final Object o, final Class[] array, final Object... array2) {
        return a(false, clazz, s, o, array, array2);
    }
    
    public static Object a(final Class clazz, final String s, final Class[] array, final Object... array2) {
        try {
            final Method declaredMethod = clazz.getDeclaredMethod(s, (Class[])array);
            declaredMethod.setAccessible(true);
            return declaredMethod.invoke(null, array2);
        }
        catch (Exception ex) {
            j.c("Exception executing static method " + s + " on class " + clazz.getSimpleName(), ex);
            return null;
        }
    }
    
    public static Object a(final Class clazz, final Class[] array, final Object... array2) {
        try {
            if (array.length == 0) {
                return clazz.newInstance();
            }
            return clazz.getConstructor((Class<?>[])array).newInstance(array2);
        }
        catch (Exception ex) {
            j.c("Exception calling constructor on " + clazz.getSimpleName(), ex);
            return null;
        }
    }
    
    public static Object a(final String s, final String s2, final Object o, final Class[] array, final Object... array2) {
        return a(false, s, s2, o, array, array2);
    }
    
    public static Object a(final String s, final String s2, final Class[] array, final Object... array2) {
        try {
            return a(Class.forName(s), s2, array, array2);
        }
        catch (ClassNotFoundException ex) {
            j.c("Class not found: " + s + " attempting to invoke static method: " + s2, ex);
            return null;
        }
    }
    
    public static Object a(final boolean b, final Class clazz, final String s, final Object o, final Class[] array, final Object... array2) {
        Label_0029: {
            if (array == null) {
                break Label_0029;
            }
            try {
                Method method = clazz.getDeclaredMethod(s, (Class[])array);
                while (true) {
                    method.setAccessible(true);
                    return method.invoke(o, array2);
                    method = clazz.getDeclaredMethod(s, (Class[])new Class[0]);
                    continue;
                }
            }
            catch (Exception ex) {
                if (!b) {
                    j.c("Exception executing method " + s + " on class " + clazz.getSimpleName(), ex);
                }
                return null;
            }
        }
    }
    
    public static Object a(final boolean b, final String s, final String s2, Object a, final Class[] array, final Object... array2) {
        try {
            a = a(b, Class.forName(s), s2, a, array, array2);
            return a;
        }
        catch (ClassNotFoundException ex) {
            if (!b) {
                j.c("Class not found: " + s + " attempting to invoke static method: " + s2);
            }
            return null;
        }
    }
    
    public static void a(final Class clazz, final String s, final Object o) {
        a(clazz, s, o, (Object)null);
    }
    
    public static void a(final Class clazz, final String s, final Object o, final Object o2) {
        try {
            final Field declaredField = clazz.getDeclaredField(s);
            declaredField.setAccessible(true);
            declaredField.set(o2, o);
        }
        catch (NoSuchFieldException ex) {
            j.d("Error trying to set " + s + " on " + clazz.getSimpleName(), ex);
        }
        catch (IllegalAccessException ex2) {
            j.d("Error trying to set " + s + " on " + clazz.getSimpleName(), ex2);
        }
        catch (IllegalArgumentException ex3) {
            j.d("Error trying to set " + s + " on " + clazz.getSimpleName(), ex3);
        }
    }
    
    public static boolean a(final Class clazz, final String s) {
        try {
            clazz.getDeclaredField(s);
            return true;
        }
        catch (NoSuchFieldException ex) {
            j.d("Error trying to get " + s + " on " + clazz.getSimpleName(), ex);
        }
        catch (IllegalArgumentException ex2) {
            j.d("Error trying to get " + s + " on " + clazz.getSimpleName(), ex2);
            goto Label_0044;
        }
        catch (ClassCastException ex3) {
            j.d("Error trying to get " + s + " on " + clazz.getSimpleName(), ex3);
            goto Label_0044;
        }
    }
    
    public static <T> T b(final Class clazz, final String s) {
        return b(clazz, s, null);
    }
    
    public static <T> T b(final Class clazz, final String s, Object value) {
        try {
            final Field declaredField = clazz.getDeclaredField(s);
            declaredField.setAccessible(true);
            value = declaredField.get(value);
            return (T)value;
        }
        catch (NoSuchFieldException ex) {
            j.d("Error trying to get " + s + " on " + clazz.getSimpleName(), ex);
        }
        catch (IllegalAccessException ex2) {
            j.d("Error trying to get " + s + " on " + clazz.getSimpleName(), ex2);
            goto Label_0055;
        }
        catch (IllegalArgumentException ex3) {
            j.d("Error trying to get " + s + " on " + clazz.getSimpleName(), ex3);
            goto Label_0055;
        }
        catch (ClassCastException ex4) {
            j.d("Error trying to get " + s + " on " + clazz.getSimpleName(), ex4);
            goto Label_0055;
        }
    }
}
