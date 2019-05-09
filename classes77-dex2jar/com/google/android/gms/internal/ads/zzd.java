// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.Process;
import java.util.concurrent.BlockingQueue;

public final class zzd extends Thread
{
    private static final boolean DEBUG;
    private final BlockingQueue<zzr<?>> zzh;
    private final BlockingQueue<zzr<?>> zzi;
    private final zzb zzj;
    private final zzaa zzk;
    private volatile boolean zzl;
    private final zzf zzm;
    
    static {
        DEBUG = zzaf.DEBUG;
    }
    
    public zzd(final BlockingQueue<zzr<?>> zzh, final BlockingQueue<zzr<?>> zzi, final zzb zzj, final zzaa zzk) {
        this.zzl = false;
        this.zzh = zzh;
        this.zzi = zzi;
        this.zzj = zzj;
        this.zzk = zzk;
        this.zzm = new zzf(this);
    }
    
    private final void processRequest() throws InterruptedException {
        final zzr<?> zzr = this.zzh.take();
        zzr.zzb("cache-queue-take");
        zzr.isCanceled();
        final zzc zza = this.zzj.zza(zzr.getUrl());
        if (zza == null) {
            zzr.zzb("cache-miss");
            if (!this.zzm.zzb(zzr)) {
                this.zzi.put(zzr);
            }
        }
        else {
            if (!zza.zzb()) {
                zzr.zzb("cache-hit");
                final zzx<?> zza2 = zzr.zza(new zzp(zza.data, zza.zzf));
                zzr.zzb("cache-hit-parsed");
                int n;
                if (zza.zze < System.currentTimeMillis()) {
                    n = 1;
                }
                else {
                    n = 0;
                }
                if (n != 0) {
                    zzr.zzb("cache-hit-refresh-needed");
                    zzr.zza(zza);
                    zza2.zzbi = true;
                    if (!this.zzm.zzb(zzr)) {
                        this.zzk.zza(zzr, zza2, new zze(this, zzr));
                        return;
                    }
                }
                this.zzk.zzb(zzr, zza2);
                return;
            }
            zzr.zzb("cache-hit-expired");
            zzr.zza(zza);
            if (!this.zzm.zzb(zzr)) {
                this.zzi.put(zzr);
            }
        }
    }
    
    public final void quit() {
        this.zzl = true;
        this.interrupt();
    }
    
    @Override
    public final void run() {
        if (zzd.DEBUG) {
            zzaf.v("start new dispatcher", new Object[0]);
        }
        Process.setThreadPriority(10);
        this.zzj.zza();
        while (true) {
            try {
                while (true) {
                    this.processRequest();
                }
            }
            catch (InterruptedException ex) {
                if (this.zzl) {
                    return;
                }
                continue;
            }
            break;
        }
    }
}
