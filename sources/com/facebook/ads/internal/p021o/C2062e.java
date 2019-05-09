package com.facebook.ads.internal.p021o;

import android.support.annotation.Nullable;
import java.lang.ref.WeakReference;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/* renamed from: com.facebook.ads.internal.o.e */
public class C2062e<T extends C1809f, E extends C2061d> {
    /* renamed from: a */
    private final Map<Class<E>, List<WeakReference<T>>> f4620a = new HashMap();
    /* renamed from: b */
    private final Queue<E> f4621b = new ArrayDeque();

    /* renamed from: a */
    private void m5027a(List<WeakReference<T>> list) {
        if (list != null) {
            int i = 0;
            for (int i2 = 0; i2 < list.size(); i2++) {
                WeakReference weakReference = (WeakReference) list.get(i2);
                if (weakReference.get() != null) {
                    int i3 = i + 1;
                    list.set(i, weakReference);
                    i = i3;
                }
            }
            for (int size = list.size() - 1; size >= i; size--) {
                list.remove(size);
            }
        }
    }

    /* renamed from: a */
    public synchronized void m5028a(E e) {
        if (this.f4621b.isEmpty()) {
            this.f4621b.add(e);
            while (!this.f4621b.isEmpty()) {
                C2061d c2061d = (C2061d) this.f4621b.peek();
                if (this.f4620a != null) {
                    List list = (List) this.f4620a.get(c2061d.getClass());
                    if (list != null) {
                        m5027a(list);
                        if (!list.isEmpty()) {
                            for (WeakReference weakReference : new ArrayList(list)) {
                                C1809f c1809f = (C1809f) weakReference.get();
                                if (c1809f != null && c1809f.m4051b(c2061d)) {
                                    c1809f.mo5360a(c2061d);
                                }
                            }
                        }
                    }
                }
                this.f4621b.remove();
            }
        } else {
            this.f4621b.add(e);
        }
    }

    /* renamed from: a */
    public synchronized void m5029a(T... tArr) {
        if (tArr != null) {
            for (C1809f a : tArr) {
                m5030a(a);
            }
        }
    }

    /* renamed from: a */
    public synchronized boolean m5030a(T t) {
        boolean z;
        if (t == null) {
            z = false;
        } else {
            Class a = t.mo5359a();
            if (this.f4620a.get(a) == null) {
                this.f4620a.put(a, new ArrayList());
            }
            List list = (List) this.f4620a.get(a);
            m5027a(list);
            int size = list.size();
            for (int i = 0; i < size; i++) {
                if (((WeakReference) list.get(i)).get() == t) {
                    z = false;
                    break;
                }
            }
            z = list.add(new WeakReference(t));
        }
        return z;
    }

    /* renamed from: b */
    public synchronized void m5031b(T... tArr) {
        if (tArr != null) {
            for (C1809f b : tArr) {
                m5032b(b);
            }
        }
    }

    /* renamed from: b */
    public synchronized boolean m5032b(@Nullable T t) {
        boolean z;
        if (t == null) {
            z = false;
        } else {
            List list = (List) this.f4620a.get(t.mo5359a());
            if (list == null) {
                z = false;
            } else {
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    if (((WeakReference) list.get(i)).get() == t) {
                        ((WeakReference) list.get(i)).clear();
                        z = true;
                        break;
                    }
                }
                z = false;
            }
        }
        return z;
    }
}
