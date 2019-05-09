// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzs implements Runnable
{
    private final /* synthetic */ String zzas;
    private final /* synthetic */ long zzat;
    private final /* synthetic */ zzr zzau;
    
    zzs(final zzr zzau, final String zzas, final long zzat) {
        this.zzau = zzau;
        this.zzas = zzas;
        this.zzat = zzat;
    }
    
    @Override
    public final void run() {
        this.zzau.zzae.zza(this.zzas, this.zzat);
        this.zzau.zzae.zzc(this.toString());
    }
}
