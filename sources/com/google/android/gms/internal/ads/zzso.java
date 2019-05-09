package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks;

final class zzso implements BaseConnectionCallbacks {
    final /* synthetic */ zzsm zzbnn;
    private final /* synthetic */ zzaoj zzbno;
    private final /* synthetic */ zzsg zzbnp;

    zzso(zzsm zzsm, zzaoj zzaoj, zzsg zzsg) {
        this.zzbnn = zzsm;
        this.zzbno = zzaoj;
        this.zzbnp = zzsg;
    }

    public final void onConnected(@Nullable Bundle bundle) {
        synchronized (this.zzbnn.mLock) {
            if (this.zzbnn.zzbnm) {
                return;
            }
            this.zzbnn.zzbnm = true;
            zzsf zzd = this.zzbnn.zzbnl;
            if (zzd == null) {
                return;
            }
            this.zzbno.zza(new zzsq(this.zzbno, zzaki.zzb(new zzsp(this, zzd, this.zzbno, this.zzbnp))), zzaoe.zzcvz);
        }
    }

    public final void onConnectionSuspended(int i) {
    }
}
