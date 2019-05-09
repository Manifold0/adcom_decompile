// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzor implements Runnable
{
    private final /* synthetic */ zzoq zzbin;
    
    zzor(final zzoq zzbin) {
        this.zzbin = zzbin;
    }
    
    @Override
    public final void run() {
        if (this.zzbin.zzbij != null) {
            this.zzbin.zzbij.zzkq();
            this.zzbin.zzbij.zzkp();
            this.zzbin.zzbij.zzcs();
        }
        zzoq.zza(this.zzbin, null);
    }
}
