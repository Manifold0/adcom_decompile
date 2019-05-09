// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.vng;

import java.util.Map;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import android.support.annotation.VisibleForTesting;
import com.moat.analytics.mobile.vng.a.a.a;
import java.util.LinkedList;
import java.lang.reflect.InvocationHandler;

class x<T> implements InvocationHandler
{
    private static final Object[] a;
    private final a<T> b;
    private final Class<T> c;
    private final LinkedList<b> d;
    private boolean e;
    private T f;
    
    static {
        a = new Object[0];
    }
    
    @VisibleForTesting
    x(final a<T> b, final Class<T> c) {
        com.moat.analytics.mobile.vng.a.a.a.a(b);
        com.moat.analytics.mobile.vng.a.a.a.a(c);
        this.b = b;
        this.c = c;
        this.d = new LinkedList<b>();
        w.a().a((w.b)new w.b() {
            @Override
            public void b() {
                x.this.c();
            }
            
            @Override
            public void c() {
            }
        });
    }
    
    static <T> T a(final a<T> a, final Class<T> clazz) {
        return (T)Proxy.newProxyInstance(clazz.getClassLoader(), new Class[] { clazz }, new x<Object>(a, clazz));
    }
    
    private Object a(final Method method) {
        try {
            if (Boolean.TYPE.equals(method.getReturnType())) {
                return true;
            }
        }
        catch (Exception ex) {
            m.a(ex);
        }
        return null;
    }
    
    private Object a(final Method method, final Object[] array) {
        final Class<?> declaringClass = method.getDeclaringClass();
        final w a = w.a();
        if (Object.class.equals(declaringClass)) {
            final String name = method.getName();
            if ("getClass".equals(name)) {
                return this.c;
            }
            if ("toString".equals(name)) {
                return (method.invoke(this, array) + "").replace(x.class.getName(), this.c.getName());
            }
            return method.invoke(this, array);
        }
        else {
            if (this.e && this.f == null) {
                this.d.clear();
                return this.a(method);
            }
            if (a.a == w.d.b) {
                this.c();
                if (this.f != null) {
                    return method.invoke(this.f, array);
                }
            }
            if (a.a == w.d.a && (!this.e || this.f != null)) {
                this.b(method, array);
            }
            return this.a(method);
        }
    }
    
    private void b() {
        if (this.e) {
            return;
        }
        while (true) {
            try {
                this.f = this.b.a().c(null);
                this.e = true;
            }
            catch (Exception ex) {
                p.a("OnOffTrackerProxy", this, "Could not create instance", ex);
                m.a(ex);
                continue;
            }
            break;
        }
    }
    
    private void b(final Method method, final Object[] array) {
        if (this.d.size() >= 15) {
            this.d.remove(5);
        }
        this.d.add(new b(method, array));
    }
    
    private void c() {
        this.b();
        if (this.f == null) {
            return;
        }
        for (final b b : this.d) {
            try {
                final Object[] array = new Object[b.b.length];
                final WeakReference[] a = b.b;
                for (int length = a.length, i = 0, n = 0; i < length; ++i, ++n) {
                    array[n] = a[i].get();
                }
                b.d.invoke(this.f, array);
            }
            catch (Exception ex) {
                m.a(ex);
            }
        }
        this.d.clear();
    }
    
    @Override
    public Object invoke(Object a, final Method method, final Object[] array) {
        try {
            a = this.a(method, array);
            return a;
        }
        catch (Exception ex) {
            m.a(ex);
            return this.a(method);
        }
    }
    
    interface a<T>
    {
        com.moat.analytics.mobile.vng.a.b.a<T> a();
    }
    
    private class b
    {
        private final WeakReference[] b;
        private final LinkedList<Object> c;
        private final Method d;
        
        private b(final Method d, final Object... array) {
            int i = 0;
            this.c = new LinkedList<Object>();
            Object[] a2 = array;
            if (array == null) {
                a2 = x.a;
            }
            final WeakReference[] b = new WeakReference[a2.length];
            for (int length = a2.length, n = 0; i < length; ++i, ++n) {
                final Object o = a2[i];
                if (o instanceof Map || o instanceof Integer || o instanceof Double) {
                    this.c.add(o);
                }
                b[n] = new WeakReference(o);
            }
            this.b = b;
            this.d = d;
        }
    }
}
