// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;
import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;
import android.os.Bundle;
import android.content.Context;
import java.util.HashSet;
import com.google.android.gms.common.util.VisibleForTesting;

@zzadh
public final class zzajv implements zzgj
{
    private final Object lock;
    @VisibleForTesting
    private final zzajr zzcqn;
    @VisibleForTesting
    private final HashSet<zzajj> zzcqo;
    @VisibleForTesting
    private final HashSet<zzaju> zzcqp;
    
    public zzajv() {
        this(zzkb.zzih());
    }
    
    private zzajv(final String s) {
        this.lock = new Object();
        this.zzcqo = new HashSet<zzajj>();
        this.zzcqp = new HashSet<zzaju>();
        this.zzcqn = new zzajr(s);
    }
    
    public final Bundle zza(final Context context, final zzajs zzajs, final String s) {
        final Bundle bundle;
        synchronized (this.lock) {
            bundle = new Bundle();
            bundle.putBundle("app", this.zzcqn.zzk(context, s));
            final Bundle bundle2 = new Bundle();
            for (final zzaju zzaju : this.zzcqp) {
                bundle2.putBundle(zzaju.zzqm(), zzaju.toBundle());
            }
        }
        final Bundle bundle3;
        bundle.putBundle("slots", bundle3);
        final ArrayList<Bundle> list = new ArrayList<Bundle>();
        final Iterator<zzajj> iterator2 = this.zzcqo.iterator();
        while (iterator2.hasNext()) {
            list.add(iterator2.next().toBundle());
        }
        bundle.putParcelableArrayList("ads", (ArrayList)list);
        zzajs.zza(this.zzcqo);
        this.zzcqo.clear();
        // monitorexit(o)
        return bundle;
    }
    
    public final void zza(final zzajj zzajj) {
        synchronized (this.lock) {
            this.zzcqo.add(zzajj);
        }
    }
    
    public final void zza(final zzaju zzaju) {
        synchronized (this.lock) {
            this.zzcqp.add(zzaju);
        }
    }
    
    public final void zzb(final zzjj zzjj, final long n) {
        synchronized (this.lock) {
            this.zzcqn.zzb(zzjj, n);
        }
    }
    
    public final void zzb(final HashSet<zzajj> set) {
        synchronized (this.lock) {
            this.zzcqo.addAll((Collection<?>)set);
        }
    }
    
    @Override
    public final void zzh(final boolean b) {
        final long currentTimeMillis = zzbv.zzer().currentTimeMillis();
        if (!b) {
            zzbv.zzeo().zzqh().zzj(currentTimeMillis);
            zzbv.zzeo().zzqh().zzaf(this.zzcqn.zzcqg);
            return;
        }
        if (currentTimeMillis - zzbv.zzeo().zzqh().zzrb() > (long)zzkb.zzik().zzd(zznk.zzayi)) {
            this.zzcqn.zzcqg = -1;
            return;
        }
        this.zzcqn.zzcqg = zzbv.zzeo().zzqh().zzrc();
    }
    
    public final void zzpm() {
        synchronized (this.lock) {
            this.zzcqn.zzpm();
        }
    }
    
    public final void zzpn() {
        synchronized (this.lock) {
            this.zzcqn.zzpn();
        }
    }
}
