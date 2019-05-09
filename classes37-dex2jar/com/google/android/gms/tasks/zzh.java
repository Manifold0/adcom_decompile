// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.tasks;

final class zzh implements Runnable
{
    private final /* synthetic */ zzg zzk;
    
    zzh(final zzg zzk) {
        this.zzk = zzk;
    }
    
    @Override
    public final void run() {
        synchronized (this.zzk.mLock) {
            if (this.zzk.zzj != null) {
                this.zzk.zzj.onCanceled();
            }
        }
    }
}
