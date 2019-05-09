// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzapl implements Runnable
{
    private final /* synthetic */ zzapi zzcyd;
    
    zzapl(final zzapi zzcyd) {
        this.zzcyd = zzcyd;
    }
    
    @Override
    public final void run() {
        this.zzcyd.zza("surfaceDestroyed", new String[0]);
    }
}
