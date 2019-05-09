package com.google.android.gms.internal.nearby;

import com.google.android.gms.nearby.connection.Connections.ConnectionResponseCallback;

final class zzaj extends zzau<ConnectionResponseCallback> {
    private final /* synthetic */ zzel zzbp;

    zzaj(zzai zzai, zzel zzel) {
        this.zzbp = zzel;
        super();
    }

    public final /* synthetic */ void notifyListener(Object obj) {
        ((ConnectionResponseCallback) obj).onConnectionResponse(this.zzbp.zzg(), zzx.zza(this.zzbp.getStatusCode()), this.zzbp.zzj());
    }
}
