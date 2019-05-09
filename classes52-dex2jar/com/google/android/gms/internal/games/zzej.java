// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.games;

import java.util.Iterator;
import java.util.Map;
import android.os.Looper;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.HashMap;
import android.os.Handler;

public abstract class zzej
{
    private final Object zzkq;
    private Handler zzkr;
    private boolean zzks;
    private HashMap<String, AtomicInteger> zzkt;
    private int zzku;
    
    public zzej(final Looper looper, final int n) {
        this.zzkq = new Object();
        this.zzkr = new Handler(looper);
        this.zzkt = new HashMap<String, AtomicInteger>();
        this.zzku = 1000;
    }
    
    private final void zzbl() {
        synchronized (this.zzkq) {
            this.zzks = false;
            this.flush();
        }
    }
    
    public final void flush() {
        synchronized (this.zzkq) {
            for (final Map.Entry<String, AtomicInteger> entry : this.zzkt.entrySet()) {
                this.zzf(entry.getKey(), entry.getValue().get());
            }
        }
        this.zzkt.clear();
    }
    // monitorexit(o)
    
    protected abstract void zzf(final String p0, final int p1);
    
    public final void zzg(final String s, final int n) {
        synchronized (this.zzkq) {
            if (!this.zzks) {
                this.zzks = true;
                this.zzkr.postDelayed((Runnable)new zzek(this), (long)this.zzku);
            }
            AtomicInteger atomicInteger;
            if ((atomicInteger = this.zzkt.get(s)) == null) {
                atomicInteger = new AtomicInteger();
                this.zzkt.put(s, atomicInteger);
            }
            atomicInteger.addAndGet(n);
        }
    }
}
