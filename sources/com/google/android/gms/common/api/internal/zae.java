package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;
import com.google.android.gms.common.api.internal.GoogleApiManager.zaa;

public final class zae<A extends ApiMethodImpl<? extends Result, AnyClient>> extends zab {
    private final A zaco;

    public zae(int i, A a) {
        super(i);
        this.zaco = a;
    }

    public final void zaa(zaa<?> zaa) throws DeadObjectException {
        try {
            this.zaco.run(zaa.zaab());
        } catch (RuntimeException e) {
            zaa(e);
        }
    }

    public final void zaa(@NonNull Status status) {
        this.zaco.setFailedResult(status);
    }

    public final void zaa(@NonNull RuntimeException runtimeException) {
        String simpleName = runtimeException.getClass().getSimpleName();
        String localizedMessage = runtimeException.getLocalizedMessage();
        this.zaco.setFailedResult(new Status(10, new StringBuilder((String.valueOf(simpleName).length() + 2) + String.valueOf(localizedMessage).length()).append(simpleName).append(": ").append(localizedMessage).toString()));
    }

    public final void zaa(@NonNull zaab zaab, boolean z) {
        zaab.zaa(this.zaco, z);
    }
}