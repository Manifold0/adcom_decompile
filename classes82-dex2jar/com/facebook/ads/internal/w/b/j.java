// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.w.b;

import android.os.Build$VERSION;
import android.view.View;

public enum j
{
    a(0), 
    b(1), 
    c(2), 
    d(3), 
    e(4), 
    f(5), 
    g(6), 
    h(7), 
    i(8), 
    j(9), 
    k(10), 
    l(11), 
    m(12), 
    n(13), 
    o(12);
    
    public static int p;
    private final int q;
    
    static {
        j.p = -1593835521;
    }
    
    private j(final int q) {
        this.q = q;
    }
    
    public static void a(final View view, final j j) {
        if (view != null && j != null && Build$VERSION.SDK_INT > 4) {
            view.setTag(j.p, (Object)j);
        }
    }
    
    public int a() {
        return this.q;
    }
}
