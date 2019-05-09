// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.content.Context;

final class zzakm implements Runnable
{
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ zzakk zzcru;
    
    zzakm(final zzakk zzcru, final Context val$context) {
        this.zzcru = zzcru;
        this.val$context = val$context;
    }
    
    @Override
    public final void run() {
        synchronized (this.zzcru.mLock) {
            this.zzcru.zzcpq = zzakk.zzam(this.val$context);
            this.zzcru.mLock.notifyAll();
        }
    }
}
