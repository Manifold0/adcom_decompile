package com.google.android.gms.internal.nearby;

import com.google.android.gms.nearby.connection.PayloadCallback;
import com.google.android.gms.nearby.connection.PayloadTransferUpdate;
import com.google.android.gms.nearby.connection.PayloadTransferUpdate.Builder;

final class zzay extends zzau<PayloadCallback> {
    private final /* synthetic */ String zzbm;
    private final /* synthetic */ PayloadTransferUpdate zzbz;

    zzay(zzav zzav, String str, PayloadTransferUpdate payloadTransferUpdate) {
        this.zzbm = str;
        this.zzbz = payloadTransferUpdate;
        super();
    }

    public final /* synthetic */ void notifyListener(Object obj) {
        ((PayloadCallback) obj).onPayloadTransferUpdate(this.zzbm, new Builder(this.zzbz).setStatus(2).build());
    }
}
