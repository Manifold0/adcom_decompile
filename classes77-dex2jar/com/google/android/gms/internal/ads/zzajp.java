// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;

final class zzajp
{
    private final Object mLock;
    private volatile int zzcpx;
    private volatile long zzcpy;
    
    private zzajp() {
        this.mLock = new Object();
        this.zzcpx = zzajq.zzcpz;
        this.zzcpy = 0L;
    }
    
    private final void zzd(final int n, final int zzcpx) {
        this.zzqk();
        final long currentTimeMillis = zzbv.zzer().currentTimeMillis();
        synchronized (this.mLock) {
            if (this.zzcpx != n) {
                return;
            }
            this.zzcpx = zzcpx;
            if (this.zzcpx == zzajq.zzcqb) {
                this.zzcpy = currentTimeMillis;
            }
        }
    }
    
    private final void zzqk() {
        final long currentTimeMillis = zzbv.zzer().currentTimeMillis();
        synchronized (this.mLock) {
            if (this.zzcpx == zzajq.zzcqb && this.zzcpy + (long)zzkb.zzik().zzd(zznk.zzbfn) <= currentTimeMillis) {
                this.zzcpx = zzajq.zzcpz;
            }
        }
    }
    
    public final void zzaa(final boolean b) {
        if (b) {
            this.zzd(zzajq.zzcpz, zzajq.zzcqa);
            return;
        }
        this.zzd(zzajq.zzcqa, zzajq.zzcpz);
    }
    
    public final boolean zzqa() {
        this.zzqk();
        return this.zzcpx == zzajq.zzcqa;
    }
    
    public final boolean zzqb() {
        this.zzqk();
        return this.zzcpx == zzajq.zzcqb;
    }
    
    public final void zzqc() {
        this.zzd(zzajq.zzcqa, zzajq.zzcqb);
    }
}
