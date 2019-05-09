// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzws implements zzaom
{
    private final /* synthetic */ zzvs zzbrl;
    private final /* synthetic */ zzaoj zzbrn;
    
    zzws(final zzwq zzwq, final zzaoj zzbrn, final zzvs zzbrl) {
        this.zzbrn = zzbrn;
        this.zzbrl = zzbrl;
    }
    
    @Override
    public final void run() {
        this.zzbrn.setException(new zzwe("Unable to obtain a JavascriptEngine."));
        this.zzbrl.release();
    }
}
