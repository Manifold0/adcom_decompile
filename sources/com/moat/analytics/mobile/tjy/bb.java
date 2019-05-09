package com.moat.analytics.mobile.tjy;

import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.Map;

class bb {
    /* renamed from: a */
    final /* synthetic */ ay f6682a;
    /* renamed from: b */
    private final WeakReference[] f6683b;
    /* renamed from: c */
    private final LinkedList f6684c;
    /* renamed from: d */
    private final Method f6685d;

    private bb(ay ayVar, Method method, Object... objArr) {
        int i = 0;
        this.f6682a = ayVar;
        this.f6684c = new LinkedList();
        if (objArr == null) {
            objArr = ay.f6670a;
        }
        WeakReference[] weakReferenceArr = new WeakReference[objArr.length];
        int length = objArr.length;
        int i2 = 0;
        while (i < length) {
            Object obj = objArr[i];
            if ((obj instanceof Map) || (obj instanceof Integer)) {
                this.f6684c.add(obj);
            }
            int i3 = i2 + 1;
            weakReferenceArr[i2] = new WeakReference(obj);
            i++;
            i2 = i3;
        }
        this.f6683b = weakReferenceArr;
        this.f6685d = method;
    }
}
