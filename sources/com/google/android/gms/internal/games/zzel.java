package com.google.android.gms.internal.games;

import java.util.concurrent.atomic.AtomicReference;

public abstract class zzel {
    private final AtomicReference<zzej> zzkw = new AtomicReference();

    public final void flush() {
        zzej zzej = (zzej) this.zzkw.get();
        if (zzej != null) {
            zzej.flush();
        }
    }

    public final void zza(String str, int i) {
        zzej zzej = (zzej) this.zzkw.get();
        if (zzej == null) {
            zzej = zzbe();
            if (!this.zzkw.compareAndSet(null, zzej)) {
                zzej = (zzej) this.zzkw.get();
            }
        }
        zzej.zzg(str, i);
    }

    protected abstract zzej zzbe();
}
