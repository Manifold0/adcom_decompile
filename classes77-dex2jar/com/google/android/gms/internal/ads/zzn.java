// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.Process;
import android.net.TrafficStats;
import android.os.SystemClock;
import java.util.concurrent.BlockingQueue;

public final class zzn extends Thread
{
    private final zzm zzaa;
    private final zzb zzj;
    private final zzaa zzk;
    private volatile boolean zzl;
    private final BlockingQueue<zzr<?>> zzz;
    
    public zzn(final BlockingQueue<zzr<?>> zzz, final zzm zzaa, final zzb zzj, final zzaa zzk) {
        this.zzl = false;
        this.zzz = zzz;
        this.zzaa = zzaa;
        this.zzj = zzj;
        this.zzk = zzk;
    }
    
    private final void processRequest() throws InterruptedException {
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        final zzr<?> zzr = this.zzz.take();
        try {
            zzr.zzb("network-queue-take");
            zzr.isCanceled();
            TrafficStats.setThreadStatsTag(zzr.zze());
            final zzp zzc = this.zzaa.zzc(zzr);
            zzr.zzb("network-http-complete");
            if (zzc.zzac && zzr.zzl()) {
                zzr.zzc("not-modified");
                zzr.zzm();
                return;
            }
            final zzx<?> zza = zzr.zza(zzc);
            zzr.zzb("network-parse-complete");
            if (zzr.zzh() && zza.zzbg != null) {
                this.zzj.zza(zzr.getUrl(), zza.zzbg);
                zzr.zzb("network-cache-written");
            }
            zzr.zzk();
            this.zzk.zzb(zzr, zza);
            zzr.zza(zza);
        }
        catch (zzae zzae) {
            zzae.zza(SystemClock.elapsedRealtime() - elapsedRealtime);
            this.zzk.zza(zzr, zzae);
            zzr.zzm();
        }
        catch (Exception ex) {
            zzaf.zza(ex, "Unhandled exception %s", ex.toString());
            final zzae zzae2 = new zzae(ex);
            zzae2.zza(SystemClock.elapsedRealtime() - elapsedRealtime);
            this.zzk.zza(zzr, zzae2);
            zzr.zzm();
        }
    }
    
    public final void quit() {
        this.zzl = true;
        this.interrupt();
    }
    
    @Override
    public final void run() {
        Process.setThreadPriority(10);
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
