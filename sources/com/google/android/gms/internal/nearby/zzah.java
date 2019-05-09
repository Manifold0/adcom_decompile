package com.google.android.gms.internal.nearby;

import com.google.android.gms.nearby.connection.Connections.ConnectionRequestListener;

final class zzah extends zzau<ConnectionRequestListener> {
    private final /* synthetic */ zzej zzbn;

    zzah(zzag zzag, zzej zzej) {
        this.zzbn = zzej;
        super();
    }

    public final /* synthetic */ void notifyListener(Object obj) {
        ((ConnectionRequestListener) obj).onConnectionRequest(this.zzbn.zzg(), this.zzbn.zzh(), this.zzbn.zzj());
    }
}
