// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;

final class zzby<T> implements BaseImplementation$ResultHolder<T>
{
    private final TaskCompletionSource<Void> zzcu;
    
    zzby(final zzbd zzbd, final TaskCompletionSource<Void> zzcu) {
        this.zzcu = zzcu;
    }
    
    public final void setFailedResult(final Status status) {
        this.zzcu.setException((Exception)new ApiException(status));
    }
    
    public final void setResult(final T t) {
        this.zzcu.setResult((Object)null);
    }
}
