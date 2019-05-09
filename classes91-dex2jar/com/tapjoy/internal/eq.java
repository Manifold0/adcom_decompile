// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import android.os.Handler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import android.os.Looper;

public final class eq
{
    public static Object a(final Object o, final Class clazz) {
        return Proxy.newProxyInstance(clazz.getClassLoader(), new Class[] { clazz }, new a(o, Thread.currentThread(), Looper.myLooper()));
    }
    
    static final class a implements InvocationHandler
    {
        private final Object a;
        private final Thread b;
        private final Looper c;
        
        public a(final Object a, final Thread b, final Looper c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
        
        @Override
        public final Object invoke(Object invoke, final Method method, final Object[] array) {
            final Object o = null;
            if (this.b == Thread.currentThread()) {
                invoke = method.invoke(this.a, array);
            }
            else {
                if (!method.getReturnType().equals(Void.TYPE)) {
                    throw new UnsupportedOperationException("method not return void: " + method.getName());
                }
                final Runnable runnable = new Runnable() {
                    @Override
                    public final void run() {
                        try {
                            method.invoke(eq.a.this.a, array);
                        }
                        catch (IllegalArgumentException ex) {
                            throw cu.a(ex);
                        }
                        catch (IllegalAccessException ex2) {
                            throw cu.a(ex2);
                        }
                        catch (InvocationTargetException ex3) {
                            throw cu.a(ex3);
                        }
                    }
                };
                if (this.c != null) {
                    invoke = o;
                    if (new Handler(this.c).post((Runnable)runnable)) {
                        return invoke;
                    }
                }
                if (this.b == fu.b()) {
                    invoke = o;
                    if (fu.a.a(runnable)) {
                        return invoke;
                    }
                }
                final Looper mainLooper = Looper.getMainLooper();
                if (mainLooper != null) {
                    invoke = o;
                    if (new Handler(mainLooper).post((Runnable)runnable)) {
                        return invoke;
                    }
                }
                return method.invoke(this.a, array);
            }
            return invoke;
        }
    }
}
