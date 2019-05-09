// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient$BaseOnConnectionFailedListener;

final class zzsr implements BaseGmsClient$BaseOnConnectionFailedListener
{
    private final /* synthetic */ zzsm zzbnn;
    private final /* synthetic */ zzaoj zzbno;
    
    zzsr(final zzsm zzbnn, final zzaoj zzbno) {
        this.zzbnn = zzbnn;
        this.zzbno = zzbno;
    }
    
    public final void onConnectionFailed(@NonNull final ConnectionResult connectionResult) {
        synchronized (this.zzbnn.mLock) {
            this.zzbno.setException(new RuntimeException("Connection failed."));
        }
    }
}
