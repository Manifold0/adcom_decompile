// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzapm implements Runnable
{
    private final /* synthetic */ zzapi zzcyd;
    private final /* synthetic */ boolean zzcye;
    
    zzapm(final zzapi zzcyd, final boolean zzcye) {
        this.zzcyd = zzcyd;
        this.zzcye = zzcye;
    }
    
    @Override
    public final void run() {
        this.zzcyd.zza("windowVisibilityChanged", "isVisible", String.valueOf(this.zzcye));
    }
}
