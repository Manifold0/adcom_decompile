// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzade implements Runnable
{
    private final /* synthetic */ zzanf zzccd;
    private final /* synthetic */ String zzcce;
    
    zzade(final zzadb zzadb, final zzanf zzccd, final String zzcce) {
        this.zzccd = zzccd;
        this.zzcce = zzcce;
    }
    
    @Override
    public final void run() {
        this.zzccd.zzcz(this.zzcce);
    }
}
