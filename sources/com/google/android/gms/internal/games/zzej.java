package com.google.android.gms.internal.games;

import android.os.Handler;
import android.os.Looper;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class zzej {
    private final Object zzkq = new Object();
    private Handler zzkr;
    private boolean zzks;
    private HashMap<String, AtomicInteger> zzkt;
    private int zzku;

    public zzej(Looper looper, int i) {
        this.zzkr = new Handler(looper);
        this.zzkt = new HashMap();
        this.zzku = 1000;
    }

    private final void zzbl() {
        synchronized (this.zzkq) {
            this.zzks = false;
            flush();
        }
    }

    public final void flush() {
        synchronized (this.zzkq) {
            for (Entry entry : this.zzkt.entrySet()) {
                zzf((String) entry.getKey(), ((AtomicInteger) entry.getValue()).get());
            }
            this.zzkt.clear();
        }
    }

    protected abstract void zzf(String str, int i);

    public final void zzg(String str, int i) {
        synchronized (this.zzkq) {
            if (!this.zzks) {
                this.zzks = true;
                this.zzkr.postDelayed(new zzek(this), (long) this.zzku);
            }
            AtomicInteger atomicInteger = (AtomicInteger) this.zzkt.get(str);
            if (atomicInteger == null) {
                atomicInteger = new AtomicInteger();
                this.zzkt.put(str, atomicInteger);
            }
            atomicInteger.addAndGet(i);
        }
    }
}
