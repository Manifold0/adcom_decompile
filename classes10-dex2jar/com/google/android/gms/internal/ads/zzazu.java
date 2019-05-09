// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

final class zzazu extends WeakReference<Throwable>
{
    private final int zzdpa;
    
    public zzazu(final Throwable t, final ReferenceQueue<Throwable> referenceQueue) {
        super(t, null);
        if (t == null) {
            throw new NullPointerException("The referent cannot be null");
        }
        this.zzdpa = System.identityHashCode(t);
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
                final zzazu zzazu = (zzazu)o;
                if (this.zzdpa == zzazu.zzdpa) {
                    b2 = b;
                    if (this.get() == zzazu.get()) {
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
        return this.zzdpa;
    }
}
