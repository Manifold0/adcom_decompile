package com.google.android.gms.internal.nearby;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;

final class zzgz extends zzha<ResultHolder<Status>> {
    private final /* synthetic */ Status zzbj;

    zzgz(zzgy zzgy, Status status) {
        this.zzbj = status;
    }

    public final /* synthetic */ void notifyListener(Object obj) {
        ResultHolder resultHolder = (ResultHolder) obj;
        if (this.zzbj.isSuccess()) {
            resultHolder.setResult(this.zzbj);
        } else {
            resultHolder.setFailedResult(this.zzbj);
        }
    }
}
