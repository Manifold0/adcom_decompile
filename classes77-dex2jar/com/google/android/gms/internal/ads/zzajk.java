// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.SystemClock;
import android.os.Bundle;

@zzadh
final class zzajk
{
    private long zzcpc;
    private long zzcpd;
    
    public zzajk() {
        this.zzcpc = -1L;
        this.zzcpd = -1L;
    }
    
    public final Bundle toBundle() {
        final Bundle bundle = new Bundle();
        bundle.putLong("topen", this.zzcpc);
        bundle.putLong("tclose", this.zzcpd);
        return bundle;
    }
    
    public final long zzpp() {
        return this.zzcpd;
    }
    
    public final void zzpq() {
        this.zzcpd = SystemClock.elapsedRealtime();
    }
    
    public final void zzpr() {
        this.zzcpc = SystemClock.elapsedRealtime();
    }
}
