// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient$BaseOnConnectionFailedListener;

final class zzhh implements BaseGmsClient$BaseOnConnectionFailedListener
{
    private final /* synthetic */ zzhd zzajt;
    
    zzhh(final zzhd zzajt) {
        this.zzajt = zzajt;
    }
    
    public final void onConnectionFailed(@NonNull final ConnectionResult connectionResult) {
        synchronized (this.zzajt.mLock) {
            this.zzajt.zzajs = null;
            if (this.zzajt.zzajr != null) {
                zzhd.zza(this.zzajt, (zzhk)null);
            }
            this.zzajt.mLock.notifyAll();
        }
    }
}
