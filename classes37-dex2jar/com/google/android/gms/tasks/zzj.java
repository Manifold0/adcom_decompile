// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.tasks;

final class zzj implements Runnable
{
    private final /* synthetic */ Task zzg;
    private final /* synthetic */ zzi zzm;
    
    zzj(final zzi zzm, final Task zzg) {
        this.zzm = zzm;
        this.zzg = zzg;
    }
    
    @Override
    public final void run() {
        synchronized (this.zzm.mLock) {
            if (this.zzm.zzl != null) {
                this.zzm.zzl.onComplete(this.zzg);
            }
        }
    }
}
