// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzjj;

final class zzai implements Runnable
{
    private final /* synthetic */ zzjj zzyh;
    private final /* synthetic */ zzah zzyi;
    
    zzai(final zzah zzyi, final zzjj zzyh) {
        this.zzyi = zzyi;
        this.zzyh = zzyh;
    }
    
    @Override
    public final void run() {
        synchronized (this.zzyi.mLock) {
            if (this.zzyi.zzde()) {
                this.zzyi.zze(this.zzyh);
            }
            else {
                this.zzyi.zzb(this.zzyh, 1);
            }
        }
    }
}
