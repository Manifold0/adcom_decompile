// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal;

import android.os.Handler;

public final class zzbn
{
    private final Handler mHandler;
    
    public zzbn(final Handler mHandler) {
        this.mHandler = mHandler;
    }
    
    public final boolean postDelayed(final Runnable runnable, final long n) {
        return this.mHandler.postDelayed(runnable, n);
    }
    
    public final void removeCallbacks(final Runnable runnable) {
        this.mHandler.removeCallbacks(runnable);
    }
}
