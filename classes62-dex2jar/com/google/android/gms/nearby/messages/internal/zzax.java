// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;

final class zzax implements BaseImplementation$ResultHolder<Status>
{
    private final /* synthetic */ TaskCompletionSource zzib;
    
    zzax(final zzak zzak, final TaskCompletionSource zzib) {
        this.zzib = zzib;
    }
    
    public final void setFailedResult(final Status status) {
        this.zzib.setException((Exception)new ApiException(status));
    }
}
