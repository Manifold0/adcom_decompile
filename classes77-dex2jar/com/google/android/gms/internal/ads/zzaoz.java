// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzaoz implements Runnable
{
    private final /* synthetic */ zzaov zzcxf;
    private final /* synthetic */ String zzcxg;
    private final /* synthetic */ String zzcxh;
    
    zzaoz(final zzaov zzcxf, final String zzcxg, final String zzcxh) {
        this.zzcxf = zzcxf;
        this.zzcxg = zzcxg;
        this.zzcxh = zzcxh;
    }
    
    @Override
    public final void run() {
        if (this.zzcxf.zzcxd != null) {
            this.zzcxf.zzcxd.zzg(this.zzcxg, this.zzcxh);
        }
    }
}
