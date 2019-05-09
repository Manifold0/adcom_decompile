// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.d;

import android.util.SparseArray;

public class a
{
    private final SparseArray<int[]> a;
    
    public a() {
        this.a = (SparseArray<int[]>)new SparseArray();
    }
    
    public void a(final int n, final int[] array) {
        this.a.put(n, (Object)array);
    }
    
    public int[] a(final int n) {
        return (int[])this.a.get(n);
    }
    
    public boolean b(final int n) {
        return this.a.indexOfKey(n) >= 0;
    }
}
