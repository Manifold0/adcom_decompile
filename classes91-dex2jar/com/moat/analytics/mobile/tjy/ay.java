// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.tjy;

import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import com.moat.analytics.mobile.tjy.base.asserts.a;
import java.util.LinkedList;
import java.lang.reflect.InvocationHandler;

class ay implements InvocationHandler
{
    private static final Object[] a;
    private final ap b;
    private final ba c;
    private final bc d;
    private final LinkedList e;
    private final LinkedList f;
    private boolean g;
    private Object h;
    
    static {
        a = new Object[0];
    }
    
    ay(final ap b, final ba c, final bc d) {
        com.moat.analytics.mobile.tjy.base.asserts.a.a(b);
        com.moat.analytics.mobile.tjy.base.asserts.a.a(c);
        com.moat.analytics.mobile.tjy.base.asserts.a.a(d);
        this.b = b;
        this.c = c;
        this.d = d;
        b.a(new az(this));
        this.e = new LinkedList();
        this.f = new LinkedList();
    }
    
    static Object a(final ap ap, final ba ba, final bc bc) {
        final Class a = bc.a();
        return Proxy.newProxyInstance(a.getClassLoader(), new Class[] { a }, new ay(ap, ba, bc));
    }
    
    private Object a(final Method method) {
        try {
            if (Boolean.TYPE.equals(method.getReturnType())) {
                return true;
            }
        }
        catch (Exception ex) {
            com.moat.analytics.mobile.tjy.base.exception.a.a(ex);
        }
        return null;
    }
    
    private Object a(final Method method, final Object[] array) {
        if (Object.class.equals(method.getDeclaringClass())) {
            final String name = method.getName();
            final Class a = this.d.a();
            if ("getClass".equals(name)) {
                return a;
            }
            if ("toString".equals(name)) {
                return String.valueOf(method.invoke(this, array)).replace(ay.class.getName(), a.getName());
            }
            return method.invoke(this, array);
        }
        else {
            if (this.g && this.h == null) {
                this.c();
                return this.a(method);
            }
            if (this.b.a() == ar.b) {
                this.b();
                if (this.h != null) {
                    return method.invoke(this.h, array);
                }
            }
            if (this.b.a() == ar.a && (!this.g || this.h != null)) {
                this.b(method, array);
            }
            return this.a(method);
        }
    }
    
    private void b() {
        Label_0029: {
            if (this.g) {
                break Label_0029;
            }
            while (true) {
                try {
                    this.h = this.c.a().c(null);
                    this.g = true;
                    if (this.h != null) {
                        final LinkedList<Object> list = new LinkedList<Object>();
                        list.add(this.e);
                        list.add(this.f);
                        for (final LinkedList<bb> list2 : list) {
                            for (final bb bb : list2) {
                                try {
                                    final Object[] array = new Object[bb.b.length];
                                    final WeakReference[] a = bb.b;
                                    for (int length = a.length, i = 0, n = 0; i < length; ++i, ++n) {
                                        array[n] = a[i].get();
                                    }
                                    bb.d.invoke(this.h, array);
                                }
                                catch (Exception ex) {}
                            }
                            list2.clear();
                        }
                    }
                }
                catch (Exception ex2) {
                    continue;
                }
                break;
            }
        }
    }
    
    private void b(final Method method, final Object[] array) {
        if (this.e.size() < 5) {
            this.e.add(new bb(this, method, array, null));
            return;
        }
        if (this.f.size() >= 10) {
            this.f.removeFirst();
        }
        this.f.add(new bb(this, method, array, null));
    }
    
    private void c() {
        this.e.clear();
        this.f.clear();
    }
    
    @Override
    public Object invoke(Object a, final Method method, final Object[] array) {
        try {
            a = this.a(method, array);
            return a;
        }
        catch (Exception ex) {
            com.moat.analytics.mobile.tjy.base.exception.a.a(ex);
            return this.a(method);
        }
    }
}
