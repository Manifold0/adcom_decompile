// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzapb implements Runnable
{
    private final /* synthetic */ zzaov zzcxf;
    private final /* synthetic */ int zzcxi;
    private final /* synthetic */ int zzcxj;
    
    zzapb(final zzaov zzcxf, final int zzcxi, final int zzcxj) {
        this.zzcxf = zzcxf;
        this.zzcxi = zzcxi;
        this.zzcxj = zzcxj;
    }
    
    @Override
    public final void run() {
        if (this.zzcxf.zzcxd != null) {
            this.zzcxf.zzcxd.zzf(this.zzcxi, this.zzcxj);
        }
    }
}
