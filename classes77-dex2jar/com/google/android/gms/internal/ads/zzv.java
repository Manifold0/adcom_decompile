// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.ArrayList;
import java.util.HashSet;
import android.os.Handler;
import android.os.Looper;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public final class zzv
{
    private final zzm zzaa;
    private final AtomicInteger zzba;
    private final Set<zzr<?>> zzbb;
    private final PriorityBlockingQueue<zzr<?>> zzbc;
    private final PriorityBlockingQueue<zzr<?>> zzbd;
    private final zzn[] zzbe;
    private final List<zzw> zzbf;
    private final zzb zzj;
    private final zzaa zzk;
    private zzd zzq;
    
    public zzv(final zzb zzb, final zzm zzm) {
        this(zzb, zzm, 4);
    }
    
    private zzv(final zzb zzb, final zzm zzm, final int n) {
        this(zzb, zzm, 4, new zzi(new Handler(Looper.getMainLooper())));
    }
    
    private zzv(final zzb zzj, final zzm zzaa, final int n, final zzaa zzk) {
        this.zzba = new AtomicInteger();
        this.zzbb = new HashSet<zzr<?>>();
        this.zzbc = new PriorityBlockingQueue<zzr<?>>();
        this.zzbd = new PriorityBlockingQueue<zzr<?>>();
        this.zzbf = new ArrayList<zzw>();
        this.zzj = zzj;
        this.zzaa = zzaa;
        this.zzbe = new zzn[4];
        this.zzk = zzk;
    }
    
    public final void start() {
        final int n = 0;
        if (this.zzq != null) {
            this.zzq.quit();
        }
        final zzn[] zzbe = this.zzbe;
        for (int length = zzbe.length, i = 0; i < length; ++i) {
            final zzn zzn = zzbe[i];
            if (zzn != null) {
                zzn.quit();
            }
        }
        (this.zzq = new zzd(this.zzbc, this.zzbd, this.zzj, this.zzk)).start();
        for (int j = n; j < this.zzbe.length; ++j) {
            (this.zzbe[j] = new zzn(this.zzbd, this.zzaa, this.zzj, this.zzk)).start();
        }
    }
    
    public final <T> zzr<T> zze(final zzr<T> zzr) {
        zzr.zza(this);
        synchronized (this.zzbb) {
            this.zzbb.add(zzr);
            // monitorexit(this.zzbb)
            zzr.zza(this.zzba.incrementAndGet());
            zzr.zzb("add-to-queue");
            if (!zzr.zzh()) {
                this.zzbd.add(zzr);
                return zzr;
            }
        }
        final zzr<?> zzr2;
        this.zzbc.add(zzr2);
        return (zzr<T>)zzr2;
    }
    
    final <T> void zzf(final zzr<T> zzr) {
        synchronized (this.zzbb) {
            this.zzbb.remove(zzr);
            // monitorexit(this.zzbb)
            final List<zzw> zzbf = this.zzbf;
            synchronized (this.zzbb) {
                final Iterator<zzw<T>> iterator = (Iterator<zzw<T>>)this.zzbf.iterator();
                while (iterator.hasNext()) {
                    iterator.next().zzg(zzr);
                }
            }
        }
    }
    // monitorexit(zzbf)
}
