package com.moat.analytics.mobile.iro;

import android.support.annotation.VisibleForTesting;
import com.moat.analytics.mobile.iro.C0803t.C0773a;
import com.moat.analytics.mobile.iro.C0803t.C0800c;
import com.moat.analytics.mobile.iro.base.asserts.Asserts;
import com.moat.analytics.mobile.iro.base.functional.Optional;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/* renamed from: com.moat.analytics.mobile.iro.s */
class C0794s<T> implements InvocationHandler {
    /* renamed from: ˊ */
    private static final Object[] f1272 = new Object[0];
    /* renamed from: ˋ */
    private final LinkedList<C0793e> f1273 = new LinkedList();
    /* renamed from: ˎ */
    private final Class<T> f1274;
    /* renamed from: ˏ */
    private boolean f1275;
    /* renamed from: ॱ */
    private final C0747a<T> f1276;
    /* renamed from: ᐝ */
    private T f1277;

    /* renamed from: com.moat.analytics.mobile.iro.s$a */
    interface C0747a<T> {
        /* renamed from: ˏ */
        Optional<T> mo1625() throws C0785o;
    }

    /* renamed from: com.moat.analytics.mobile.iro.s$1 */
    class C07921 implements C0773a {
        /* renamed from: ˊ */
        private /* synthetic */ C0794s f1267;

        C07921(C0794s c0794s) {
            this.f1267 = c0794s;
        }

        /* renamed from: ॱ */
        public final void mo1645() throws C0785o {
            this.f1267.m1384();
        }
    }

    /* renamed from: com.moat.analytics.mobile.iro.s$e */
    class C0793e {
        /* renamed from: ˊ */
        private final Method f1268;
        /* renamed from: ˋ */
        private final LinkedList<Object> f1269;
        /* renamed from: ˎ */
        private final WeakReference[] f1270;
        /* renamed from: ॱ */
        private /* synthetic */ C0794s f1271;

        private C0793e(C0794s c0794s, Method method, Object... objArr) {
            int i = 0;
            this.f1271 = c0794s;
            this.f1269 = new LinkedList();
            if (objArr == null) {
                objArr = C0794s.f1272;
            }
            WeakReference[] weakReferenceArr = new WeakReference[objArr.length];
            int length = objArr.length;
            int i2 = 0;
            while (i < length) {
                Object obj = objArr[i];
                if ((obj instanceof Map) || (obj instanceof Integer) || (obj instanceof Double)) {
                    this.f1269.add(obj);
                }
                int i3 = i2 + 1;
                weakReferenceArr[i2] = new WeakReference(obj);
                i++;
                i2 = i3;
            }
            this.f1270 = weakReferenceArr;
            this.f1268 = method;
        }
    }

    @VisibleForTesting
    private C0794s(C0747a<T> c0747a, Class<T> cls) throws C0785o {
        Asserts.checkNotNull(c0747a);
        Asserts.checkNotNull(cls);
        this.f1276 = c0747a;
        this.f1274 = cls;
        C0803t.m1393().m1403(new C07921(this));
    }

    /* renamed from: ˊ */
    static <T> T m1382(C0747a<T> c0747a, Class<T> cls) throws C0785o {
        return Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new C0794s(c0747a, cls));
    }

    /* renamed from: ˋ */
    private void m1384() throws C0785o {
        if (!this.f1275) {
            try {
                this.f1277 = this.f1276.mo1625().orElse(null);
            } catch (Exception e) {
                C0756b.m1230("OnOffTrackerProxy", this, "Could not create instance", e);
                C0785o.m1351(e);
            }
            this.f1275 = true;
        }
        if (this.f1277 != null) {
            Iterator it = this.f1273.iterator();
            while (it.hasNext()) {
                C0793e c0793e = (C0793e) it.next();
                try {
                    Object[] objArr = new Object[c0793e.f1270.length];
                    WeakReference[] ˏ = c0793e.f1270;
                    int length = ˏ.length;
                    int i = 0;
                    int i2 = 0;
                    while (i < length) {
                        int i3 = i2 + 1;
                        objArr[i2] = ˏ[i].get();
                        i++;
                        i2 = i3;
                    }
                    c0793e.f1268.invoke(this.f1277, objArr);
                } catch (Exception e2) {
                    C0785o.m1351(e2);
                }
            }
            this.f1273.clear();
        }
    }

    /* renamed from: ˊ */
    private static Boolean m1381(Method method) {
        try {
            if (Boolean.TYPE.equals(method.getReturnType())) {
                return Boolean.valueOf(true);
            }
        } catch (Exception e) {
            C0785o.m1351(e);
        }
        return null;
    }

    public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
        try {
            Class declaringClass = method.getDeclaringClass();
            C0803t ˋ = C0803t.m1393();
            if (Object.class.equals(declaringClass)) {
                String name = method.getName();
                if ("getClass".equals(name)) {
                    return this.f1274;
                }
                if (!"toString".equals(name)) {
                    return method.invoke(this, objArr);
                }
                Object invoke = method.invoke(this, objArr);
                return String.valueOf(invoke).replace(C0794s.class.getName(), this.f1274.getName());
            } else if (this.f1275 && this.f1277 == null) {
                this.f1273.clear();
                return C0794s.m1381(method);
            } else {
                if (ˋ.f1298 == C0800c.f1286) {
                    m1384();
                    if (this.f1277 != null) {
                        return method.invoke(this.f1277, objArr);
                    }
                }
                if (ˋ.f1298 == C0800c.f1285 && !(this.f1275 && this.f1277 == null)) {
                    if (this.f1273.size() >= 15) {
                        this.f1273.remove(5);
                    }
                    this.f1273.add(new C0793e(method, objArr));
                }
                return C0794s.m1381(method);
            }
        } catch (Exception e) {
            C0785o.m1351(e);
            return C0794s.m1381(method);
        }
    }
}
