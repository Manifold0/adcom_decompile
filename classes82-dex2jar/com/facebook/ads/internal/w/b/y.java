// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.w.b;

import java.lang.ref.WeakReference;

public abstract class y<T> implements Runnable
{
    private final WeakReference<T> a;
    
    public y(final T t) {
        this.a = new WeakReference<T>(t);
    }
    
    public T a() {
        return this.a.get();
    }
}
