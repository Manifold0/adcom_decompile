// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzaoy implements Runnable
{
    private final /* synthetic */ zzaov zzcxf;
    
    zzaoy(final zzaov zzcxf) {
        this.zzcxf = zzcxf;
    }
    
    @Override
    public final void run() {
        if (this.zzcxf.zzcxd != null) {
            this.zzcxf.zzcxd.zzsx();
        }
    }
}
