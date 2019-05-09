// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzlz extends zzkd
{
    private final /* synthetic */ zzly zzatc;
    
    zzlz(final zzly zzatc) {
        this.zzatc = zzatc;
    }
    
    @Override
    public final void onAdFailedToLoad(final int n) {
        this.zzatc.zzasv.zza(this.zzatc.zzbc());
        super.onAdFailedToLoad(n);
    }
    
    @Override
    public final void onAdLoaded() {
        this.zzatc.zzasv.zza(this.zzatc.zzbc());
        super.onAdLoaded();
    }
}
