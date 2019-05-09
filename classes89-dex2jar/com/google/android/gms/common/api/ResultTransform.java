// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api;

import android.support.annotation.WorkerThread;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.internal.zacd;
import android.support.annotation.NonNull;

public abstract class ResultTransform<R extends Result, S extends Result>
{
    @NonNull
    public final PendingResult<S> createFailedResult(@NonNull final Status status) {
        return new zacd<S>(status);
    }
    
    @NonNull
    public Status onFailure(@NonNull final Status status) {
        return status;
    }
    
    @Nullable
    @WorkerThread
    public abstract PendingResult<S> onSuccess(@NonNull final R p0);
}
