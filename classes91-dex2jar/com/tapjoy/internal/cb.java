// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public final class cb extends WeakReference
{
    public final Object a;
    
    public cb(final Object a, final Object o, final ReferenceQueue referenceQueue) {
        super(o, referenceQueue);
        this.a = a;
    }
}
