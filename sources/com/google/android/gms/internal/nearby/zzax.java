package com.google.android.gms.internal.nearby;

import com.google.android.gms.nearby.connection.PayloadCallback;

final class zzax extends zzau<PayloadCallback> {
    private final /* synthetic */ zzex zzby;

    zzax(zzav zzav, zzex zzex) {
        this.zzby = zzex;
        super();
    }

    public final /* synthetic */ void notifyListener(Object obj) {
        ((PayloadCallback) obj).onPayloadTransferUpdate(this.zzby.zzg(), this.zzby.zzn());
    }
}
