// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzagt implements Runnable
{
    private final /* synthetic */ zzagr zzclh;
    private final /* synthetic */ zzaji zzwg;
    
    zzagt(final zzagr zzclh, final zzaji zzwg) {
        this.zzclh = zzclh;
        this.zzwg = zzwg;
    }
    
    @Override
    public final void run() {
        this.zzclh.zzb(new zzajh(this.zzwg, null, null, null, null, null, null, null));
    }
}
