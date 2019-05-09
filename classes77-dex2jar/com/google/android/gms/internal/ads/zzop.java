// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzop implements Runnable
{
    private final /* synthetic */ zzoo zzbik;
    
    zzop(final zzoo zzbik) {
        this.zzbik = zzbik;
    }
    
    @Override
    public final void run() {
        if (this.zzbik.zzbij != null) {
            this.zzbik.zzbij.zzkq();
            this.zzbik.zzbij.zzkp();
            this.zzbik.zzbij.zzcs();
        }
        zzoo.zza(this.zzbik, null);
    }
}
