package com.unity3d.player;

import java.lang.reflect.Method;
import java.util.HashMap;

/* renamed from: com.unity3d.player.o */
final class C0257o {
    /* renamed from: a */
    private HashMap f219a = new HashMap();
    /* renamed from: b */
    private Class f220b = null;
    /* renamed from: c */
    private Object f221c = null;

    /* renamed from: com.unity3d.player.o$a */
    class C0256a {
        /* renamed from: a */
        public Class[] f216a;
        /* renamed from: b */
        public Method f217b = null;
        /* renamed from: c */
        final /* synthetic */ C0257o f218c;

        public C0256a(C0257o c0257o, Class[] clsArr) {
            this.f218c = c0257o;
            this.f216a = clsArr;
        }
    }

    public C0257o(Class cls, Object obj) {
        this.f220b = cls;
        this.f221c = obj;
    }

    /* renamed from: a */
    private void m176a(String str, C0256a c0256a) {
        try {
            c0256a.f217b = this.f220b.getMethod(str, c0256a.f216a);
        } catch (Exception e) {
            C0243g.Log(6, "Exception while trying to get method " + str + ". " + e.getLocalizedMessage());
            c0256a.f217b = null;
        }
    }

    /* renamed from: a */
    public final Object m177a(String str, Object... objArr) {
        if (this.f219a.containsKey(str)) {
            C0256a c0256a = (C0256a) this.f219a.get(str);
            if (c0256a.f217b == null) {
                m176a(str, c0256a);
            }
            if (c0256a.f217b == null) {
                C0243g.Log(6, "Unable to create method: " + str);
                return null;
            }
            Object invoke;
            try {
                invoke = objArr.length == 0 ? c0256a.f217b.invoke(this.f221c, new Object[0]) : c0256a.f217b.invoke(this.f221c, objArr);
            } catch (Exception e) {
                C0243g.Log(6, "Error trying to call delegated method " + str + ". " + e.getLocalizedMessage());
                invoke = null;
            }
            return invoke;
        }
        C0243g.Log(6, "No definition for method " + str + " can be found");
        return null;
    }

    /* renamed from: a */
    public final void m178a(String str, Class[] clsArr) {
        this.f219a.put(str, new C0256a(this, clsArr));
    }
}
