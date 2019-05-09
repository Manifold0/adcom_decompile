// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzamw extends Thread
{
    private final /* synthetic */ String zzcce;
    
    zzamw(final zzamv zzamv, final String zzcce) {
        this.zzcce = zzcce;
    }
    
    @Override
    public final void run() {
        new zzanf().zzcz(this.zzcce);
    }
}
