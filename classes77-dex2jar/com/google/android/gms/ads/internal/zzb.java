// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal;

import android.os.Debug;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zznk;
import com.google.android.gms.internal.ads.zzkb;
import java.util.Timer;
import java.util.concurrent.CountDownLatch;
import java.util.TimerTask;

final class zzb extends TimerTask
{
    private final /* synthetic */ CountDownLatch zzwd;
    private final /* synthetic */ Timer zzwe;
    private final /* synthetic */ zza zzwf;
    
    zzb(final zza zzwf, final CountDownLatch zzwd, final Timer zzwe) {
        this.zzwf = zzwf;
        this.zzwd = zzwd;
        this.zzwe = zzwe;
    }
    
    @Override
    public final void run() {
        if ((int)zzkb.zzik().zzd(zznk.zzbck) != this.zzwd.getCount()) {
            zzakb.zzck("Stopping method tracing");
            Debug.stopMethodTracing();
            if (this.zzwd.getCount() == 0L) {
                this.zzwe.cancel();
                return;
            }
        }
        final String concat = String.valueOf(this.zzwf.zzvw.zzrt.getPackageName()).concat("_adsTrace_");
        try {
            zzakb.zzck("Starting method tracing");
            this.zzwd.countDown();
            Debug.startMethodTracing(new StringBuilder(String.valueOf(concat).length() + 20).append(concat).append(zzbv.zzer().currentTimeMillis()).toString(), (int)zzkb.zzik().zzd(zznk.zzbcl));
        }
        catch (Exception ex) {
            zzakb.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
}
