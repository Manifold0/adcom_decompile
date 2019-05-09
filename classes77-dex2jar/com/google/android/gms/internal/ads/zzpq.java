// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzpq implements Runnable
{
    private final /* synthetic */ zzpd zzbki;
    private final /* synthetic */ zzpp zzbkj;
    
    zzpq(final zzpp zzbkj, final zzpd zzbki) {
        this.zzbkj = zzbkj;
        this.zzbki = zzbki;
    }
    
    @Override
    public final void run() {
        this.zzbkj.zza(this.zzbki);
    }
}
