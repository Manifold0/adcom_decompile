// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.firebase_messaging;

import java.lang.ref.Reference;
import java.util.Vector;
import java.lang.ref.ReferenceQueue;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

final class zze
{
    private final ConcurrentHashMap<zzf, List<Throwable>> zze;
    private final ReferenceQueue<Throwable> zzf;
    
    zze() {
        this.zze = new ConcurrentHashMap<zzf, List<Throwable>>(16, 0.75f, 10);
        this.zzf = new ReferenceQueue<Throwable>();
    }
    
    public final List<Throwable> zza(final Throwable t, final boolean b) {
        for (Reference<? extends Throwable> reference = this.zzf.poll(); reference != null; reference = this.zzf.poll()) {
            this.zze.remove(reference);
        }
        final List<Throwable> list = this.zze.get(new zzf(t, null));
        List<Throwable> list2;
        if (list != null) {
            list2 = list;
        }
        else {
            final Vector<Throwable> vector = new Vector<Throwable>(2);
            if ((list2 = this.zze.putIfAbsent(new zzf(t, this.zzf), vector)) == null) {
                return vector;
            }
        }
        return list2;
    }
}
