// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzow implements Runnable
{
    private final /* synthetic */ zzov zzbis;
    
    zzow(final zzov zzbis) {
        this.zzbis = zzbis;
    }
    
    @Override
    public final void run() {
        if (this.zzbis.zzbij != null) {
            this.zzbis.zzbij.zzkq();
            this.zzbis.zzbij.zzkp();
            this.zzbis.zzbij.zzcs();
        }
        zzov.zza(this.zzbis, null);
    }
}
