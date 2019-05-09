package com.moat.analytics.mobile.tjy;

import com.moat.analytics.mobile.tjy.base.asserts.C2746a;
import com.moat.analytics.mobile.tjy.base.exception.C2747a;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

class ay implements InvocationHandler {
    /* renamed from: a */
    private static final Object[] f6670a = new Object[0];
    /* renamed from: b */
    private final ap f6671b;
    /* renamed from: c */
    private final ba f6672c;
    /* renamed from: d */
    private final bc f6673d;
    /* renamed from: e */
    private final LinkedList f6674e = new LinkedList();
    /* renamed from: f */
    private final LinkedList f6675f = new LinkedList();
    /* renamed from: g */
    private boolean f6676g;
    /* renamed from: h */
    private Object f6677h;

    ay(ap apVar, ba baVar, bc bcVar) {
        C2746a.m6881a(apVar);
        C2746a.m6881a(baVar);
        C2746a.m6881a(bcVar);
        this.f6671b = apVar;
        this.f6672c = baVar;
        this.f6673d = bcVar;
        apVar.mo6104a(new az(this));
    }

    /* renamed from: a */
    static Object m6867a(ap apVar, ba baVar, bc bcVar) {
        return Proxy.newProxyInstance(bcVar.mo6083a().getClassLoader(), new Class[]{r0}, new ay(apVar, baVar, bcVar));
    }

    /* renamed from: a */
    private Object m6868a(Method method) {
        try {
            if (Boolean.TYPE.equals(method.getReturnType())) {
                return Boolean.valueOf(true);
            }
        } catch (Exception e) {
            C2747a.m6882a(e);
        }
        return null;
    }

    /* renamed from: a */
    private Object m6869a(Method method, Object[] objArr) {
        if (Object.class.equals(method.getDeclaringClass())) {
            String name = method.getName();
            Class a = this.f6673d.mo6083a();
            if ("getClass".equals(name)) {
                return a;
            }
            if (!"toString".equals(name)) {
                return method.invoke(this, objArr);
            }
            Object invoke = method.invoke(this, objArr);
            return String.valueOf(invoke).replace(ay.class.getName(), a.getName());
        } else if (this.f6676g && this.f6677h == null) {
            m6875c();
            return m6868a(method);
        } else {
            if (this.f6671b.mo6103a() == ar.ON) {
                m6872b();
                if (this.f6677h != null) {
                    return method.invoke(this.f6677h, objArr);
                }
            }
            if (this.f6671b.mo6103a() == ar.OFF && !(this.f6676g && this.f6677h == null)) {
                m6873b(method, objArr);
            }
            return m6868a(method);
        }
    }

    /* renamed from: b */
    private void m6872b() {
        if (!this.f6676g) {
            try {
                this.f6677h = this.f6672c.mo6117a().m6887c(null);
            } catch (Exception e) {
            }
            this.f6676g = true;
        }
        if (this.f6677h != null) {
            List<LinkedList> linkedList = new LinkedList();
            linkedList.add(this.f6674e);
            linkedList.add(this.f6675f);
            for (LinkedList linkedList2 : linkedList) {
                Iterator it = linkedList2.iterator();
                while (it.hasNext()) {
                    bb bbVar = (bb) it.next();
                    try {
                        Object[] objArr = new Object[bbVar.f6683b.length];
                        WeakReference[] a = bbVar.f6683b;
                        int length = a.length;
                        int i = 0;
                        int i2 = 0;
                        while (i < length) {
                            int i3 = i2 + 1;
                            objArr[i2] = a[i].get();
                            i++;
                            i2 = i3;
                        }
                        bbVar.f6685d.invoke(this.f6677h, objArr);
                    } catch (Exception e2) {
                    }
                }
                linkedList2.clear();
            }
        }
    }

    /* renamed from: b */
    private void m6873b(Method method, Object[] objArr) {
        if (this.f6674e.size() < 5) {
            this.f6674e.add(new bb(this, method, objArr));
            return;
        }
        if (this.f6675f.size() >= 10) {
            this.f6675f.removeFirst();
        }
        this.f6675f.add(new bb(this, method, objArr));
    }

    /* renamed from: c */
    private void m6875c() {
        this.f6674e.clear();
        this.f6675f.clear();
    }

    public Object invoke(Object obj, Method method, Object[] objArr) {
        try {
            return m6869a(method, objArr);
        } catch (Exception e) {
            C2747a.m6882a(e);
            return m6868a(method);
        }
    }
}
