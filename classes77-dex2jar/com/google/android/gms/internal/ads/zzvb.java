// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzvb implements Runnable
{
    private final /* synthetic */ String zzbpt;
    private final /* synthetic */ zzuw zzbpu;
    
    zzvb(final zzuw zzbpu, final String zzbpt) {
        this.zzbpu = zzbpu;
        this.zzbpt = zzbpt;
    }
    
    @Override
    public final void run() {
        this.zzbpu.zzbnd.loadData(this.zzbpt, "text/html", "UTF-8");
    }
}
