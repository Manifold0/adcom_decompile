package com.tapjoy.internal;

import java.util.Iterator;
import java.util.LinkedHashMap;

public final class aw extends av {
    /* renamed from: a */
    private final LinkedHashMap f7212a = new LinkedHashMap(0, 0.75f, true);
    /* renamed from: b */
    private int f7213b = 10;

    /* renamed from: a */
    private void m7181a() {
        int size = this.f7212a.size() - this.f7213b;
        if (size > 0) {
            Iterator it = this.f7212a.entrySet().iterator();
            while (size > 0 && it.hasNext()) {
                size--;
                it.next();
                it.remove();
            }
        }
    }

    /* renamed from: a */
    public final void mo6180a(Object obj, Object obj2) {
        super.mo6180a(obj, obj2);
        m7181a();
    }

    /* renamed from: a */
    protected final at mo6181a(Object obj, boolean z) {
        ar arVar = (ar) this.f7212a.get(obj);
        if (arVar != null || !z) {
            return arVar;
        }
        at arVar2 = new ar(obj);
        this.f7212a.put(obj, arVar2);
        m7181a();
        return arVar2;
    }
}
