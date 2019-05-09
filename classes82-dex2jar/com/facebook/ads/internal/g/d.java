// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.g;

import java.lang.reflect.Method;

public class d
{
    public static Object a(Object invoke, final Method method, final Object... array) {
        try {
            invoke = method.invoke(invoke, array);
            return invoke;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static Method a(final Class<?> clazz, final String s, final Class<?>... array) {
        try {
            return clazz.getMethod(s, array);
        }
        catch (NoSuchMethodException ex) {
            return null;
        }
    }
    
    public static Method a(final String s, final String s2, final Class<?>... array) {
        try {
            return a(Class.forName(s), s2, array);
        }
        catch (ClassNotFoundException ex) {
            return null;
        }
    }
}
