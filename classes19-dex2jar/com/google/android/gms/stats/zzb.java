// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.stats;

final class zzb implements Runnable
{
    private final /* synthetic */ WakeLock zzp;
    
    zzb(final WakeLock zzp) {
        this.zzp = zzp;
    }
    
    @Override
    public final void run() {
        WakeLock.zza(this.zzp, 0);
    }
}
