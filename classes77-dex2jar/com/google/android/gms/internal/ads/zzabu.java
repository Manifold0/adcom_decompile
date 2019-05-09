// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzabu implements Runnable
{
    private final /* synthetic */ zzajh zzaam;
    private final /* synthetic */ zzabt zzbzw;
    
    zzabu(final zzabt zzbzw, final zzajh zzaam) {
        this.zzbzw = zzbzw;
        this.zzaam = zzaam;
    }
    
    @Override
    public final void run() {
        this.zzbzw.zzbzd.zzb(this.zzaam);
    }
}
