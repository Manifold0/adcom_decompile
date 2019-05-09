// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Map;
import com.google.android.gms.ads.internal.gmsg.zzv;

final class zzafw implements zzv<Object>
{
    private final /* synthetic */ zzaft zzchv;
    
    zzafw(final zzaft zzchv) {
        this.zzchv = zzchv;
    }
    
    @Override
    public final void zza(final Object o, final Map<String, String> map) {
        synchronized (this.zzchv.mLock) {
            if (this.zzchv.zzchr.isDone()) {
                return;
            }
            if (!this.zzchv.zzchp.equals(new zzafz(-2, map).zzol())) {
                return;
            }
        }
        final Throwable t;
        this.zzchv.zzchr.set(t);
    }
    // monitorexit(o)
}
