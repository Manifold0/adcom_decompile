package com.moat.analytics.mobile.vng;

import android.support.annotation.VisibleForTesting;
import com.moat.analytics.mobile.vng.C0879w.C0846b;
import com.moat.analytics.mobile.vng.C0879w.C0878d;
import com.moat.analytics.mobile.vng.p013a.p014a.C0819a;
import com.moat.analytics.mobile.vng.p013a.p015b.C0820a;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/* renamed from: com.moat.analytics.mobile.vng.x */
class C0882x<T> implements InvocationHandler {
    /* renamed from: a */
    private static final Object[] f1494a = new Object[0];
    /* renamed from: b */
    private final C0815a<T> f1495b;
    /* renamed from: c */
    private final Class<T> f1496c;
    /* renamed from: d */
    private final LinkedList<C0881b> f1497d = new LinkedList();
    /* renamed from: e */
    private boolean f1498e;
    /* renamed from: f */
    private T f1499f;

    /* renamed from: com.moat.analytics.mobile.vng.x$a */
    interface C0815a<T> {
        /* renamed from: a */
        C0820a<T> mo1653a();
    }

    /* renamed from: com.moat.analytics.mobile.vng.x$1 */
    class C08801 implements C0846b {
        /* renamed from: a */
        final /* synthetic */ C0882x f1489a;

        C08801(C0882x c0882x) {
            this.f1489a = c0882x;
        }

        /* renamed from: b */
        public void mo1669b() {
            this.f1489a.m1635c();
        }

        /* renamed from: c */
        public void mo1670c() {
        }
    }

    /* renamed from: com.moat.analytics.mobile.vng.x$b */
    private class C0881b {
        /* renamed from: a */
        final /* synthetic */ C0882x f1490a;
        /* renamed from: b */
        private final WeakReference[] f1491b;
        /* renamed from: c */
        private final LinkedList<Object> f1492c;
        /* renamed from: d */
        private final Method f1493d;

        private C0881b(C0882x c0882x, Method method, Object... objArr) {
            int i = 0;
            this.f1490a = c0882x;
            this.f1492c = new LinkedList();
            if (objArr == null) {
                objArr = C0882x.f1494a;
            }
            WeakReference[] weakReferenceArr = new WeakReference[objArr.length];
            int length = objArr.length;
            int i2 = 0;
            while (i < length) {
                Object obj = objArr[i];
                if ((obj instanceof Map) || (obj instanceof Integer) || (obj instanceof Double)) {
                    this.f1492c.add(obj);
                }
                int i3 = i2 + 1;
                weakReferenceArr[i2] = new WeakReference(obj);
                i++;
                i2 = i3;
            }
            this.f1491b = weakReferenceArr;
            this.f1493d = method;
        }
    }

    @VisibleForTesting
    C0882x(C0815a<T> c0815a, Class<T> cls) {
        C0819a.m1431a(c0815a);
        C0819a.m1431a(cls);
        this.f1495b = c0815a;
        this.f1496c = cls;
        C0879w.m1610a().m1622a(new C08801(this));
    }

    /* renamed from: a */
    static <T> T m1628a(C0815a<T> c0815a, Class<T> cls) {
        return Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new C0882x(c0815a, cls));
    }

    /* renamed from: a */
    private Object m1629a(Method method) {
        try {
            if (Boolean.TYPE.equals(method.getReturnType())) {
                return Boolean.valueOf(true);
            }
        } catch (Exception e) {
            C0849m.m1543a(e);
        }
        return null;
    }

    /* renamed from: a */
    private Object m1630a(Method method, Object[] objArr) {
        Class declaringClass = method.getDeclaringClass();
        C0879w a = C0879w.m1610a();
        if (Object.class.equals(declaringClass)) {
            String name = method.getName();
            if ("getClass".equals(name)) {
                return this.f1496c;
            }
            if (!"toString".equals(name)) {
                return method.invoke(this, objArr);
            }
            Object invoke = method.invoke(this, objArr);
            return (invoke + "").replace(C0882x.class.getName(), this.f1496c.getName());
        } else if (this.f1498e && this.f1499f == null) {
            this.f1497d.clear();
            return m1629a(method);
        } else {
            if (a.f1478a == C0878d.ON) {
                m1635c();
                if (this.f1499f != null) {
                    return method.invoke(this.f1499f, objArr);
                }
            }
            if (a.f1478a == C0878d.OFF && !(this.f1498e && this.f1499f == null)) {
                m1634b(method, objArr);
            }
            return m1629a(method);
        }
    }

    /* renamed from: b */
    private void m1633b() {
        if (!this.f1498e) {
            try {
                this.f1499f = this.f1495b.mo1653a().m1436c(null);
            } catch (Throwable e) {
                C0858p.m1578a("OnOffTrackerProxy", (Object) this, "Could not create instance", e);
                C0849m.m1543a(e);
            }
            this.f1498e = true;
        }
    }

    /* renamed from: b */
    private void m1634b(Method method, Object[] objArr) {
        if (this.f1497d.size() >= 15) {
            this.f1497d.remove(5);
        }
        this.f1497d.add(new C0881b(method, objArr));
    }

    /* renamed from: c */
    private void m1635c() {
        m1633b();
        if (this.f1499f != null) {
            Iterator it = this.f1497d.iterator();
            while (it.hasNext()) {
                C0881b c0881b = (C0881b) it.next();
                try {
                    Object[] objArr = new Object[c0881b.f1491b.length];
                    WeakReference[] a = c0881b.f1491b;
                    int length = a.length;
                    int i = 0;
                    int i2 = 0;
                    while (i < length) {
                        int i3 = i2 + 1;
                        objArr[i2] = a[i].get();
                        i++;
                        i2 = i3;
                    }
                    c0881b.f1493d.invoke(this.f1499f, objArr);
                } catch (Exception e) {
                    C0849m.m1543a(e);
                }
            }
            this.f1497d.clear();
        }
    }

    public Object invoke(Object obj, Method method, Object[] objArr) {
        try {
            return m1630a(method, objArr);
        } catch (Exception e) {
            C0849m.m1543a(e);
            return m1629a(method);
        }
    }
}
