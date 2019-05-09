// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;
import javax.annotation.concurrent.GuardedBy;

@zzadh
public final class zzamj
{
    private Object mLock;
    private long zzctx;
    @GuardedBy("mLock")
    private long zzcty;
    
    public zzamj(final long zzctx) {
        this.zzcty = Long.MIN_VALUE;
        this.mLock = new Object();
        this.zzctx = zzctx;
    }
    
    public final boolean tryAcquire() {
        synchronized (this.mLock) {
            final long elapsedRealtime = zzbv.zzer().elapsedRealtime();
            if (this.zzcty + this.zzctx > elapsedRealtime) {
                return false;
            }
            this.zzcty = elapsedRealtime;
            return true;
        }
    }
}
