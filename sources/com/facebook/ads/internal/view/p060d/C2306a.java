package com.facebook.ads.internal.view.p060d;

import android.util.SparseArray;

/* renamed from: com.facebook.ads.internal.view.d.a */
public class C2306a {
    /* renamed from: a */
    private final SparseArray<int[]> f5485a = new SparseArray();

    /* renamed from: a */
    public void m5935a(int i, int[] iArr) {
        this.f5485a.put(i, iArr);
    }

    /* renamed from: a */
    public int[] m5936a(int i) {
        return (int[]) this.f5485a.get(i);
    }

    /* renamed from: b */
    public boolean m5937b(int i) {
        return this.f5485a.indexOfKey(i) >= 0;
    }
}
