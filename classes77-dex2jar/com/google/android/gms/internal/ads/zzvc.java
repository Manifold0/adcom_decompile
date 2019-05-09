// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzvc implements Runnable
{
    private final /* synthetic */ zzuw zzbpu;
    private final /* synthetic */ String zzbpv;
    
    zzvc(final zzuw zzbpu, final String zzbpv) {
        this.zzbpu = zzbpu;
        this.zzbpv = zzbpv;
    }
    
    @Override
    public final void run() {
        this.zzbpu.zzbnd.loadUrl(this.zzbpv);
    }
}
