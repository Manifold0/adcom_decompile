package com.google.android.gms.internal.nearby;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.nearby.connection.Connections.StartAdvertisingResult;

final class zzbc extends zzed {
    private final ResultHolder<StartAdvertisingResult> zzcb;

    zzbc(ResultHolder<StartAdvertisingResult> resultHolder) {
        this.zzcb = (ResultHolder) Preconditions.checkNotNull(resultHolder);
    }

    public final void zza(zzez zzez) {
        Status zzb = zzx.zza(zzez.getStatusCode());
        if (zzb.isSuccess()) {
            this.zzcb.setResult(new zzbb(zzb, zzez.getLocalEndpointName()));
        } else {
            this.zzcb.setFailedResult(zzb);
        }
    }
}
