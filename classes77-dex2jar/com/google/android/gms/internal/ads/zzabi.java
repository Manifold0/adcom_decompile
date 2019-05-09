// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzabi implements Runnable
{
    private final /* synthetic */ zzabh zzbzk;
    
    zzabi(final zzabh zzbzk) {
        this.zzbzk = zzbzk;
    }
    
    @Override
    public final void run() {
        this.zzbzk.onStop();
    }
}
