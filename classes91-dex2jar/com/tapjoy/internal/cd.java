// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.lang.ref.WeakReference;

public final class cd
{
    public WeakReference a;
    
    public final Object a() {
        final WeakReference a = this.a;
        if (a != null) {
            return a.get();
        }
        return null;
    }
    
    public final void a(final Object o) {
        this.a = new WeakReference((T)o);
    }
}
