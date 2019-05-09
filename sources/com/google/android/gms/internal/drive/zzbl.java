package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

final class zzbl implements ResultCallback<Status> {
    zzbl(zzbi zzbi) {
    }

    public final /* synthetic */ void onResult(Result result) {
        if (!((Status) result).isSuccess()) {
            zzbi.zzbx.efmt("DriveContentsImpl", "Error discarding contents, status: %s", new Object[]{(Status) result});
        }
    }
}
