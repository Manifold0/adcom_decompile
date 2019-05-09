// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api;

import android.support.annotation.Nullable;
import java.util.concurrent.TimeUnit;
import android.support.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public abstract class PendingResult<R extends Result>
{
    @KeepForSdk
    public void addStatusListener(@NonNull final StatusListener statusListener) {
        throw new UnsupportedOperationException();
    }
    
    @NonNull
    public abstract R await();
    
    @NonNull
    public abstract R await(final long p0, @NonNull final TimeUnit p1);
    
    public abstract void cancel();
    
    public abstract boolean isCanceled();
    
    public abstract void setResultCallback(@NonNull final ResultCallback<? super R> p0);
    
    public abstract void setResultCallback(@NonNull final ResultCallback<? super R> p0, final long p1, @NonNull final TimeUnit p2);
    
    @NonNull
    public <S extends Result> TransformedResult<S> then(@NonNull final ResultTransform<? super R, ? extends S> resultTransform) {
        throw new UnsupportedOperationException();
    }
    
    @Nullable
    public Integer zam() {
        throw new UnsupportedOperationException();
    }
    
    @KeepForSdk
    public interface StatusListener
    {
        @KeepForSdk
        void onComplete(final Status p0);
    }
}
