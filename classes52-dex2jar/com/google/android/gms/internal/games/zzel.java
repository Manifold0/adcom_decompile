// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.games;

import java.util.concurrent.atomic.AtomicReference;

public abstract class zzel
{
    private final AtomicReference<zzej> zzkw;
    
    public zzel() {
        this.zzkw = new AtomicReference<zzej>();
    }
    
    public final void flush() {
        final zzej zzej = this.zzkw.get();
        if (zzej != null) {
            zzej.flush();
        }
    }
    
    public final void zza(final String s, final int n) {
        zzej zzbe;
        if ((zzbe = this.zzkw.get()) == null && !this.zzkw.compareAndSet(null, zzbe = this.zzbe())) {
            zzbe = this.zzkw.get();
        }
        zzbe.zzg(s, n);
    }
    
    protected abstract zzej zzbe();
}
