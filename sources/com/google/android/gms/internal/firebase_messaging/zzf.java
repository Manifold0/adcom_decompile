package com.google.android.gms.internal.firebase_messaging;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

final class zzf extends WeakReference<Throwable> {
    private final int zzg;

    public zzf(Throwable th, ReferenceQueue<Throwable> referenceQueue) {
        super(th, referenceQueue);
        if (th == null) {
            throw new NullPointerException("The referent cannot be null");
        }
        this.zzg = System.identityHashCode(th);
    }

    public final int hashCode() {
        return this.zzg;
    }

    public final boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        zzf zzf = (zzf) obj;
        if (this.zzg == zzf.zzg && get() == zzf.get()) {
            return true;
        }
        return false;
    }
}
