// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.Bundle;

@zzadh
public final class zzua
{
    private static zzua zzbox;
    private int zzboy;
    private int zzboz;
    private int zzbpa;
    private int zzbpb;
    private int zzbpc;
    
    static {
        zzua.zzbox = new zzua();
    }
    
    public static zzua zzlk() {
        return zzua.zzbox;
    }
    
    final void zzll() {
        ++this.zzboz;
    }
    
    final void zzlm() {
        ++this.zzbpa;
    }
    
    final void zzln() {
        ++this.zzbpb;
    }
    
    final void zzlo() {
        ++this.zzbpc;
    }
    
    public final int zzlp() {
        return this.zzboz;
    }
    
    public final int zzlq() {
        return this.zzbpa;
    }
    
    public final int zzlr() {
        return this.zzbpb;
    }
    
    public final int zzls() {
        return this.zzbpc;
    }
    
    public final Bundle zzlt() {
        final Bundle bundle = new Bundle();
        bundle.putInt("ipl", this.zzboy);
        bundle.putInt("ipds", this.zzboz);
        bundle.putInt("ipde", this.zzbpa);
        bundle.putInt("iph", this.zzbpb);
        bundle.putInt("ipm", this.zzbpc);
        return bundle;
    }
    
    final void zzw(final int n) {
        this.zzboy += n;
    }
}
