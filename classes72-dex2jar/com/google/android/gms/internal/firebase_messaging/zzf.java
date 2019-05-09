// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.firebase_messaging;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

final class zzf extends WeakReference<Throwable>
{
    private final int zzg;
    
    public zzf(final Throwable t, final ReferenceQueue<Throwable> referenceQueue) {
        super(t, referenceQueue);
        if (t == null) {
            throw new NullPointerException("The referent cannot be null");
        }
        this.zzg = System.identityHashCode(t);
    }
    
    @Override
    public final boolean equals(final Object o) {
        final boolean b = true;
        boolean b2;
        if (o == null || o.getClass() != this.getClass()) {
            b2 = false;
        }
        else {
            b2 = b;
            if (this != o) {
                final zzf zzf = (zzf)o;
                if (this.zzg == zzf.zzg) {
                    b2 = b;
                    if (this.get() == zzf.get()) {
                        return b2;
                    }
                }
                return false;
            }
        }
        return b2;
    }
    
    @Override
    public final int hashCode() {
        return this.zzg;
    }
}
