// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzou implements Runnable
{
    private final /* synthetic */ zzos zzbir;
    
    zzou(final zzos zzbir) {
        this.zzbir = zzbir;
    }
    
    @Override
    public final void run() {
        if (this.zzbir.zzbij != null) {
            this.zzbir.zzbij.zzkq();
            this.zzbir.zzbij.zzkp();
        }
        zzos.zza(this.zzbir, null);
    }
}
