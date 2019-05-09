package com.moat.analytics.mobile.cha;

import android.support.annotation.VisibleForTesting;
import com.moat.analytics.mobile.cha.C1536t.C1494b;
import com.moat.analytics.mobile.cha.C1536t.C1532a;
import com.moat.analytics.mobile.cha.base.asserts.Asserts;
import com.moat.analytics.mobile.cha.base.functional.Optional;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/* renamed from: com.moat.analytics.mobile.cha.p */
class C1521p<T> implements InvocationHandler {
    /* renamed from: ˋ */
    private static final Object[] f3562 = new Object[0];
    /* renamed from: ˊ */
    private final C1480c<T> f3563;
    /* renamed from: ˎ */
    private boolean f3564;
    /* renamed from: ˏ */
    private final LinkedList<C1520d> f3565 = new LinkedList();
    /* renamed from: ॱ */
    private final Class<T> f3566;
    /* renamed from: ᐝ */
    private T f3567;

    /* renamed from: com.moat.analytics.mobile.cha.p$c */
    interface C1480c<T> {
        /* renamed from: ˋ */
        Optional<T> mo4353() throws C1518o;
    }

    /* renamed from: com.moat.analytics.mobile.cha.p$1 */
    class C15191 implements C1494b {
        /* renamed from: ॱ */
        private /* synthetic */ C1521p f3557;

        C15191(C1521p c1521p) {
            this.f3557 = c1521p;
        }

        /* renamed from: ˎ */
        public final void mo4370() throws C1518o {
            this.f3557.m3847();
        }
    }

    /* renamed from: com.moat.analytics.mobile.cha.p$d */
    class C1520d {
        /* renamed from: ˊ */
        private final WeakReference[] f3558;
        /* renamed from: ˋ */
        private final Method f3559;
        /* renamed from: ˎ */
        private final LinkedList<Object> f3560;
        /* renamed from: ॱ */
        private /* synthetic */ C1521p f3561;

        private C1520d(C1521p c1521p, Method method, Object... objArr) {
            int i = 0;
            this.f3561 = c1521p;
            this.f3560 = new LinkedList();
            if (objArr == null) {
                objArr = C1521p.f3562;
            }
            WeakReference[] weakReferenceArr = new WeakReference[objArr.length];
            int length = objArr.length;
            int i2 = 0;
            while (i < length) {
                Object obj = objArr[i];
                if ((obj instanceof Map) || (obj instanceof Integer) || (obj instanceof Double)) {
                    this.f3560.add(obj);
                }
                int i3 = i2 + 1;
                weakReferenceArr[i2] = new WeakReference(obj);
                i++;
                i2 = i3;
            }
            this.f3558 = weakReferenceArr;
            this.f3559 = method;
        }
    }

    @VisibleForTesting
    private C1521p(C1480c<T> c1480c, Class<T> cls) throws C1518o {
        Asserts.checkNotNull(c1480c);
        Asserts.checkNotNull(cls);
        this.f3563 = c1480c;
        this.f3566 = cls;
        C1536t.m3887().m3892(new C15191(this));
    }

    /* renamed from: ˋ */
    static <T> T m3846(C1480c<T> c1480c, Class<T> cls) throws C1518o {
        return Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new C1521p(c1480c, cls));
    }

    /* renamed from: ˎ */
    private void m3847() throws C1518o {
        if (!this.f3564) {
            try {
                this.f3567 = this.f3563.mo4353().orElse(null);
            } catch (Exception e) {
                C1487a.m3717("OnOffTrackerProxy", this, "Could not create instance", e);
                C1518o.m3840(e);
            }
            this.f3564 = true;
        }
        if (this.f3567 != null) {
            Iterator it = this.f3565.iterator();
            while (it.hasNext()) {
                C1520d c1520d = (C1520d) it.next();
                try {
                    Object[] objArr = new Object[c1520d.f3558.length];
                    WeakReference[] ˊ = c1520d.f3558;
                    int length = ˊ.length;
                    int i = 0;
                    int i2 = 0;
                    while (i < length) {
                        int i3 = i2 + 1;
                        objArr[i2] = ˊ[i].get();
                        i++;
                        i2 = i3;
                    }
                    c1520d.f3559.invoke(this.f3567, objArr);
                } catch (Exception e2) {
                    C1518o.m3840(e2);
                }
            }
            this.f3565.clear();
        }
    }

    /* renamed from: ˊ */
    private static Boolean m3845(Method method) {
        try {
            if (Boolean.TYPE.equals(method.getReturnType())) {
                return Boolean.valueOf(true);
            }
        } catch (Exception e) {
            C1518o.m3840(e);
        }
        return null;
    }

    public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
        try {
            Class declaringClass = method.getDeclaringClass();
            C1536t ˏ = C1536t.m3887();
            if (Object.class.equals(declaringClass)) {
                String name = method.getName();
                if ("getClass".equals(name)) {
                    return this.f3566;
                }
                if (!"toString".equals(name)) {
                    return method.invoke(this, objArr);
                }
                Object invoke = method.invoke(this, objArr);
                return String.valueOf(invoke).replace(C1521p.class.getName(), this.f3566.getName());
            } else if (this.f3564 && this.f3567 == null) {
                this.f3565.clear();
                return C1521p.m3845(method);
            } else {
                if (ˏ.f3610 == C1532a.f3592) {
                    m3847();
                    if (this.f3567 != null) {
                        return method.invoke(this.f3567, objArr);
                    }
                }
                if (ˏ.f3610 == C1532a.f3593 && !(this.f3564 && this.f3567 == null)) {
                    if (this.f3565.size() >= 15) {
                        this.f3565.remove(5);
                    }
                    this.f3565.add(new C1520d(method, objArr));
                }
                return C1521p.m3845(method);
            }
        } catch (Exception e) {
            C1518o.m3840(e);
            return C1521p.m3845(method);
        }
    }
}
