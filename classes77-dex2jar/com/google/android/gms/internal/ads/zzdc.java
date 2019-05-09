// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzdc implements Runnable
{
    private final /* synthetic */ zzcz zzsl;
    private final /* synthetic */ int zzsm;
    private final /* synthetic */ boolean zzsn;
    
    zzdc(final zzcz zzsl, final int zzsm, final boolean zzsn) {
        this.zzsl = zzsl;
        this.zzsm = zzsm;
        this.zzsn = zzsn;
    }
    
    @Override
    public final void run() {
        final zzba zzb = this.zzsl.zzb(this.zzsm, this.zzsn);
        this.zzsl.zzsb = zzb;
        if (zza(this.zzsm, zzb)) {
            this.zzsl.zza(this.zzsm + 1, this.zzsn);
        }
    }
}
