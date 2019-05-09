// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzabj implements Runnable
{
    private final /* synthetic */ zzajh zzaam;
    private final /* synthetic */ zzabh zzbzk;
    
    zzabj(final zzabh zzbzk, final zzajh zzaam) {
        this.zzbzk = zzbzk;
        this.zzaam = zzaam;
    }
    
    @Override
    public final void run() {
        synchronized (this.zzbzk.mLock) {
            this.zzbzk.zzbzd.zzb(this.zzaam);
        }
    }
}
