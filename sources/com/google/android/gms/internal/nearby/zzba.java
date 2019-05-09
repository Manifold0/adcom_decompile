package com.google.android.gms.internal.nearby;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.common.internal.Preconditions;

final class zzba extends zzea {
    private final ResultHolder<Status> zzcb;

    zzba(ResultHolder<Status> resultHolder) {
        this.zzcb = (ResultHolder) Preconditions.checkNotNull(resultHolder);
    }

    public final void zzc(int i) {
        Status zzb = zzx.zza(i);
        if (zzb.isSuccess()) {
            this.zzcb.setResult(zzb);
        } else {
            this.zzcb.setFailedResult(zzb);
        }
    }
}
