// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.TransformedResult;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.ResultCallback;
import java.util.concurrent.TimeUnit;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.Result;

@KeepForSdk
public final class OptionalPendingResultImpl<R extends Result> extends OptionalPendingResult<R>
{
    private final BasePendingResult<R> zajq;
    
    public OptionalPendingResultImpl(final PendingResult<R> pendingResult) {
        this.zajq = (BasePendingResult<R>)pendingResult;
    }
    
    @Override
    public final void addStatusListener(final StatusListener statusListener) {
        this.zajq.addStatusListener(statusListener);
    }
    
    @Override
    public final R await() {
        return this.zajq.await();
    }
    
    @Override
    public final R await(final long n, final TimeUnit timeUnit) {
        return this.zajq.await(n, timeUnit);
    }
    
    @Override
    public final void cancel() {
        this.zajq.cancel();
    }
    
    @Override
    public final R get() {
        if (this.isDone()) {
            return this.await(0L, TimeUnit.MILLISECONDS);
        }
        throw new IllegalStateException("Result is not available. Check that isDone() returns true before calling get().");
    }
    
    @Override
    public final boolean isCanceled() {
        return this.zajq.isCanceled();
    }
    
    @Override
    public final boolean isDone() {
        return this.zajq.isReady();
    }
    
    @Override
    public final void setResultCallback(final ResultCallback<? super R> resultCallback) {
        this.zajq.setResultCallback(resultCallback);
    }
    
    @Override
    public final void setResultCallback(final ResultCallback<? super R> resultCallback, final long n, final TimeUnit timeUnit) {
        this.zajq.setResultCallback(resultCallback, n, timeUnit);
    }
    
    @NonNull
    @Override
    public final <S extends Result> TransformedResult<S> then(@NonNull final ResultTransform<? super R, ? extends S> resultTransform) {
        return this.zajq.then((ResultTransform<? super Result, ? extends S>)resultTransform);
    }
    
    @Override
    public final Integer zam() {
        return this.zajq.zam();
    }
}
