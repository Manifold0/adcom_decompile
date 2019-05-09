// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal;

import java.util.concurrent.TimeUnit;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.common.api.PendingResult;

final class zaj implements StatusListener
{
    private final /* synthetic */ PendingResult zaov;
    private final /* synthetic */ TaskCompletionSource zaow;
    private final /* synthetic */ PendingResultUtil.ResultConverter zaox;
    private final /* synthetic */ PendingResultUtil.zaa zaoy;
    
    zaj(final PendingResult zaov, final TaskCompletionSource zaow, final PendingResultUtil.ResultConverter zaox, final PendingResultUtil.zaa zaoy) {
        this.zaov = zaov;
        this.zaow = zaow;
        this.zaox = zaox;
        this.zaoy = zaoy;
    }
    
    @Override
    public final void onComplete(final Status status) {
        if (status.isSuccess()) {
            this.zaow.setResult(this.zaox.convert(this.zaov.await(0L, TimeUnit.MILLISECONDS)));
            return;
        }
        this.zaow.setException((Exception)this.zaoy.zaf(status));
    }
}
