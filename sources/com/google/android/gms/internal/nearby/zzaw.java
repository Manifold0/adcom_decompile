package com.google.android.gms.internal.nearby;

import com.google.android.gms.nearby.connection.Payload;
import com.google.android.gms.nearby.connection.PayloadCallback;

final class zzaw extends zzau<PayloadCallback> {
    private final /* synthetic */ zzev zzbu;
    private final /* synthetic */ Payload zzbx;

    zzaw(zzav zzav, zzev zzev, Payload payload) {
        this.zzbu = zzev;
        this.zzbx = payload;
        super();
    }

    public final /* synthetic */ void notifyListener(Object obj) {
        ((PayloadCallback) obj).onPayloadReceived(this.zzbu.zzg(), this.zzbx);
    }
}
