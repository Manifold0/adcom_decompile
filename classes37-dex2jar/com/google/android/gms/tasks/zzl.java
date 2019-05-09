// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.tasks;

final class zzl implements Runnable
{
    private final /* synthetic */ Task zzg;
    private final /* synthetic */ zzk zzo;
    
    zzl(final zzk zzo, final Task zzg) {
        this.zzo = zzo;
        this.zzg = zzg;
    }
    
    @Override
    public final void run() {
        synchronized (this.zzo.mLock) {
            if (this.zzo.zzn != null) {
                this.zzo.zzn.onFailure(this.zzg.getException());
            }
        }
    }
}
