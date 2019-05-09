// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzjj;

final class zzaj implements Runnable
{
    private final /* synthetic */ zzjj zzyh;
    private final /* synthetic */ zzah zzyi;
    private final /* synthetic */ int zzyj;
    
    zzaj(final zzah zzyi, final zzjj zzyh, final int zzyj) {
        this.zzyi = zzyi;
        this.zzyh = zzyh;
        this.zzyj = zzyj;
    }
    
    @Override
    public final void run() {
        synchronized (this.zzyi.mLock) {
            this.zzyi.zzb(this.zzyh, this.zzyj);
        }
    }
}
