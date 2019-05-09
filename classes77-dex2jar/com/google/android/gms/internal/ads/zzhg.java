// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.DeadObjectException;
import android.support.annotation.Nullable;
import android.os.Bundle;
import com.google.android.gms.common.internal.BaseGmsClient$BaseConnectionCallbacks;

final class zzhg implements BaseGmsClient$BaseConnectionCallbacks
{
    private final /* synthetic */ zzhd zzajt;
    
    zzhg(final zzhd zzajt) {
        this.zzajt = zzajt;
    }
    
    public final void onConnected(@Nullable final Bundle bundle) {
        synchronized (this.zzajt.mLock) {
            while (true) {
                try {
                    if (this.zzajt.zzajr != null) {
                        this.zzajt.zzajs = this.zzajt.zzajr.zzhl();
                    }
                    this.zzajt.mLock.notifyAll();
                }
                catch (DeadObjectException ex) {
                    zzakb.zzb("Unable to obtain a cache service instance.", (Throwable)ex);
                    this.zzajt.disconnect();
                    continue;
                }
                break;
            }
        }
    }
    
    public final void onConnectionSuspended(final int n) {
        synchronized (this.zzajt.mLock) {
            this.zzajt.zzajs = null;
            this.zzajt.mLock.notifyAll();
        }
    }
}
