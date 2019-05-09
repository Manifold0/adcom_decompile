// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzabg implements Runnable
{
    private final /* synthetic */ zzabf zzbzj;
    
    zzabg(final zzabf zzbzj) {
        this.zzbzj = zzbzj;
    }
    
    @Override
    public final void run() {
        if (!this.zzbzj.zzbzi.get()) {
            return;
        }
        zzakb.e("Timed out waiting for WebView to finish loading.");
        this.zzbzj.cancel();
    }
}
