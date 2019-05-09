// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzabp implements Runnable
{
    private final /* synthetic */ zzajh zzbzl;
    private final /* synthetic */ zzabo zzbzm;
    
    zzabp(final zzabo zzbzm, final zzajh zzbzl) {
        this.zzbzm = zzbzm;
        this.zzbzl = zzbzl;
    }
    
    @Override
    public final void run() {
        this.zzbzm.zzbzd.zzb(this.zzbzl);
    }
}
