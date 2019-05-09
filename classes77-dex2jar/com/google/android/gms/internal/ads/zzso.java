// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.concurrent.Future;
import android.support.annotation.Nullable;
import android.os.Bundle;
import com.google.android.gms.common.internal.BaseGmsClient$BaseConnectionCallbacks;

final class zzso implements BaseGmsClient$BaseConnectionCallbacks
{
    final /* synthetic */ zzsm zzbnn;
    private final /* synthetic */ zzaoj zzbno;
    private final /* synthetic */ zzsg zzbnp;
    
    zzso(final zzsm zzbnn, final zzaoj zzbno, final zzsg zzbnp) {
        this.zzbnn = zzbnn;
        this.zzbno = zzbno;
        this.zzbnp = zzbnp;
    }
    
    public final void onConnected(@Nullable final Bundle bundle) {
        synchronized (this.zzbnn.mLock) {
            if (this.zzbnn.zzbnm) {
                return;
            }
            zzsm.zza(this.zzbnn, true);
            if (this.zzbnn.zzbnl == null) {
                return;
            }
        }
        final zzsf zzsf;
        this.zzbno.zza(new zzsq(this.zzbno, zzaki.zzb(new zzsp(this, zzsf, this.zzbno, this.zzbnp))), zzaoe.zzcvz);
    }
    // monitorexit(bundle)
    
    public final void onConnectionSuspended(final int n) {
    }
}
