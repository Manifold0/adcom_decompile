// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzaox implements Runnable
{
    private final /* synthetic */ zzaov zzcxf;
    
    zzaox(final zzaov zzcxf) {
        this.zzcxf = zzcxf;
    }
    
    @Override
    public final void run() {
        if (this.zzcxf.zzcxd != null) {
            this.zzcxf.zzcxd.zzsv();
        }
    }
}
