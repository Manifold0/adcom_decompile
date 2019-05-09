// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.tasks;

final class zzn implements Runnable
{
    private final /* synthetic */ Task zzg;
    private final /* synthetic */ zzm zzq;
    
    zzn(final zzm zzq, final Task zzg) {
        this.zzq = zzq;
        this.zzg = zzg;
    }
    
    @Override
    public final void run() {
        synchronized (this.zzq.mLock) {
            if (this.zzq.zzp != null) {
                this.zzq.zzp.onSuccess(this.zzg.getResult());
            }
        }
    }
}
