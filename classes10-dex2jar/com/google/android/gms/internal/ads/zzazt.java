// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

final class zzazt
{
    private final ConcurrentHashMap<zzazu, List<Throwable>> zzdoy;
    private final ReferenceQueue<Throwable> zzdoz;
    
    zzazt() {
        this.zzdoy = new ConcurrentHashMap<zzazu, List<Throwable>>(16, 0.75f, 10);
        this.zzdoz = new ReferenceQueue<Throwable>();
    }
    
    public final List<Throwable> zza(final Throwable t, final boolean b) {
        for (Reference<? extends Throwable> reference = this.zzdoz.poll(); reference != null; reference = this.zzdoz.poll()) {
            this.zzdoy.remove(reference);
        }
        return this.zzdoy.get(new zzazu(t, null));
    }
}
