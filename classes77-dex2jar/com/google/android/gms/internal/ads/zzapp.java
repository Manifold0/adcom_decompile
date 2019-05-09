// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.graphics.SurfaceTexture;
import java.util.concurrent.TimeUnit;
import android.annotation.TargetApi;

@zzadh
@TargetApi(14)
public final class zzapp
{
    private final long zzcyj;
    private long zzcyk;
    private boolean zzcyl;
    
    zzapp() {
        this.zzcyj = TimeUnit.MILLISECONDS.toNanos((long)zzkb.zzik().zzd(zznk.zzavh));
        this.zzcyl = true;
    }
    
    public final void zza(final SurfaceTexture surfaceTexture, final zzapf zzapf) {
        if (zzapf != null) {
            final long timestamp = surfaceTexture.getTimestamp();
            if (this.zzcyl || Math.abs(timestamp - this.zzcyk) >= this.zzcyj) {
                this.zzcyl = false;
                this.zzcyk = timestamp;
                zzakk.zzcrm.post((Runnable)new zzapq(this, zzapf));
            }
        }
    }
    
    public final void zzsw() {
        this.zzcyl = true;
    }
}
