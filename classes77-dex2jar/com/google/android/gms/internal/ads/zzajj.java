// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.SystemClock;
import java.util.Iterator;
import java.util.ArrayList;
import android.os.Bundle;
import com.google.android.gms.ads.internal.zzbv;
import java.util.LinkedList;
import javax.annotation.concurrent.GuardedBy;

@zzadh
public final class zzajj
{
    private final Object mLock;
    private final zzajv zzacn;
    @GuardedBy("mLock")
    private boolean zzcif;
    @GuardedBy("mLock")
    private final LinkedList<zzajk> zzcot;
    private final String zzcou;
    private final String zzcov;
    @GuardedBy("mLock")
    private long zzcow;
    @GuardedBy("mLock")
    private long zzcox;
    @GuardedBy("mLock")
    private long zzcoy;
    @GuardedBy("mLock")
    private long zzcoz;
    @GuardedBy("mLock")
    private long zzcpa;
    @GuardedBy("mLock")
    private long zzcpb;
    
    private zzajj(final zzajv zzacn, final String zzcou, final String zzcov) {
        this.mLock = new Object();
        this.zzcow = -1L;
        this.zzcox = -1L;
        this.zzcif = false;
        this.zzcoy = -1L;
        this.zzcoz = 0L;
        this.zzcpa = -1L;
        this.zzcpb = -1L;
        this.zzacn = zzacn;
        this.zzcou = zzcou;
        this.zzcov = zzcov;
        this.zzcot = new LinkedList<zzajk>();
    }
    
    public zzajj(final String s, final String s2) {
        this(zzbv.zzep(), s, s2);
    }
    
    public final Bundle toBundle() {
        final ArrayList<Bundle> list;
        synchronized (this.mLock) {
            final Bundle bundle = new Bundle();
            bundle.putString("seq_num", this.zzcou);
            bundle.putString("slotid", this.zzcov);
            bundle.putBoolean("ismediation", this.zzcif);
            bundle.putLong("treq", this.zzcpa);
            bundle.putLong("tresponse", this.zzcpb);
            bundle.putLong("timp", this.zzcox);
            bundle.putLong("tload", this.zzcoy);
            bundle.putLong("pcc", this.zzcoz);
            bundle.putLong("tfetch", this.zzcow);
            list = new ArrayList<Bundle>();
            final Iterator<zzajk> iterator = this.zzcot.iterator();
            while (iterator.hasNext()) {
                list.add(iterator.next().toBundle());
            }
        }
        final Bundle bundle2;
        bundle2.putParcelableArrayList("tclick", (ArrayList)list);
        // monitorexit(o)
        return bundle2;
    }
    
    public final void zzh(final long zzcpb) {
        synchronized (this.mLock) {
            this.zzcpb = zzcpb;
            if (this.zzcpb != -1L) {
                this.zzacn.zza(this);
            }
        }
    }
    
    public final void zzi(final long zzcow) {
        synchronized (this.mLock) {
            if (this.zzcpb != -1L) {
                this.zzcow = zzcow;
                this.zzacn.zza(this);
            }
        }
    }
    
    public final void zzn(final zzjj zzjj) {
        synchronized (this.mLock) {
            this.zzcpa = SystemClock.elapsedRealtime();
            this.zzacn.zzb(zzjj, this.zzcpa);
        }
    }
    
    public final void zzpm() {
        synchronized (this.mLock) {
            if (this.zzcpb != -1L && this.zzcox == -1L) {
                this.zzcox = SystemClock.elapsedRealtime();
                this.zzacn.zza(this);
            }
            this.zzacn.zzpm();
        }
    }
    
    public final void zzpn() {
        synchronized (this.mLock) {
            if (this.zzcpb != -1L) {
                final zzajk zzajk = new zzajk();
                zzajk.zzpr();
                this.zzcot.add(zzajk);
                ++this.zzcoz;
                this.zzacn.zzpn();
                this.zzacn.zza(this);
            }
        }
    }
    
    public final void zzpo() {
        synchronized (this.mLock) {
            if (this.zzcpb != -1L && !this.zzcot.isEmpty()) {
                final zzajk zzajk = this.zzcot.getLast();
                if (zzajk.zzpp() == -1L) {
                    zzajk.zzpq();
                    this.zzacn.zza(this);
                }
            }
        }
    }
    
    public final void zzy(final boolean b) {
        synchronized (this.mLock) {
            if (this.zzcpb != -1L) {
                this.zzcoy = SystemClock.elapsedRealtime();
                if (!b) {
                    this.zzcox = this.zzcoy;
                    this.zzacn.zza(this);
                }
            }
        }
    }
    
    public final void zzz(final boolean zzcif) {
        synchronized (this.mLock) {
            if (this.zzcpb != -1L) {
                this.zzcif = zzcif;
                this.zzacn.zza(this);
            }
        }
    }
}
