// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzas;
import java.util.concurrent.CountDownLatch;

final class zzabs implements Runnable
{
    private final /* synthetic */ zzabr zzbzt;
    private final /* synthetic */ CountDownLatch zzwd;
    
    zzabs(final zzabr zzbzt, final CountDownLatch zzwd) {
        this.zzbzt = zzbzt;
        this.zzwd = zzwd;
    }
    
    @Override
    public final void run() {
        synchronized (this.zzbzt.zzbzh) {
            this.zzbzt.zzbzs = zzas.zza(this.zzbzt.zzbnd, this.zzbzt.zzbzr, this.zzwd);
        }
    }
}
