// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzapc implements Runnable
{
    private final /* synthetic */ zzaov zzcxf;
    
    zzapc(final zzaov zzcxf) {
        this.zzcxf = zzcxf;
    }
    
    @Override
    public final void run() {
        if (this.zzcxf.zzcxd != null) {
            this.zzcxf.zzcxd.onPaused();
            this.zzcxf.zzcxd.zzsy();
        }
    }
}
