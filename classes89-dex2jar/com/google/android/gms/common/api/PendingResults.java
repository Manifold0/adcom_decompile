// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api;

import com.google.android.gms.common.api.internal.BasePendingResult;
import com.google.android.gms.common.api.internal.OptionalPendingResultImpl;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.api.internal.StatusPendingResult;
import android.os.Looper;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public final class PendingResults
{
    @KeepForSdk
    private PendingResults() {
    }
    
    public static PendingResult<Status> canceledPendingResult() {
        final StatusPendingResult statusPendingResult = new StatusPendingResult(Looper.getMainLooper());
        statusPendingResult.cancel();
        return statusPendingResult;
    }
    
    public static <R extends Result> PendingResult<R> canceledPendingResult(final R r) {
        Preconditions.checkNotNull((Object)r, (Object)"Result must not be null");
        Preconditions.checkArgument(r.getStatus().getStatusCode() == 16, (Object)"Status code must be CommonStatusCodes.CANCELED");
        final zaa zaa = new zaa(r);
        zaa.cancel();
        return (PendingResult<R>)zaa;
    }
    
    @KeepForSdk
    public static <R extends Result> PendingResult<R> immediateFailedResult(final R result, final GoogleApiClient googleApiClient) {
        Preconditions.checkNotNull((Object)result, (Object)"Result must not be null");
        Preconditions.checkArgument(!result.getStatus().isSuccess(), (Object)"Status code must not be SUCCESS");
        final zab<R> zab = new zab<R>(googleApiClient, result);
        zab.setResult(result);
        return zab;
    }
    
    @KeepForSdk
    public static <R extends Result> OptionalPendingResult<R> immediatePendingResult(final R result) {
        Preconditions.checkNotNull((Object)result, (Object)"Result must not be null");
        final zac<Result> zac = new zac<Result>((GoogleApiClient)null);
        zac.setResult(result);
        return new OptionalPendingResultImpl<R>((PendingResult<R>)zac);
    }
    
    @KeepForSdk
    public static <R extends Result> OptionalPendingResult<R> immediatePendingResult(final R result, final GoogleApiClient googleApiClient) {
        Preconditions.checkNotNull((Object)result, (Object)"Result must not be null");
        final zac<Result> zac = new zac<Result>(googleApiClient);
        zac.setResult(result);
        return new OptionalPendingResultImpl<R>((PendingResult<R>)zac);
    }
    
    @KeepForSdk
    public static PendingResult<Status> immediatePendingResult(final Status result) {
        Preconditions.checkNotNull((Object)result, (Object)"Result must not be null");
        final StatusPendingResult statusPendingResult = new StatusPendingResult(Looper.getMainLooper());
        statusPendingResult.setResult(result);
        return statusPendingResult;
    }
    
    @KeepForSdk
    public static PendingResult<Status> immediatePendingResult(final Status result, final GoogleApiClient googleApiClient) {
        Preconditions.checkNotNull((Object)result, (Object)"Result must not be null");
        final StatusPendingResult statusPendingResult = new StatusPendingResult(googleApiClient);
        statusPendingResult.setResult(result);
        return statusPendingResult;
    }
    
    private static final class zaa<R extends Result> extends BasePendingResult<R>
    {
        private final R zaci;
        
        public zaa(final R zaci) {
            super(Looper.getMainLooper());
            this.zaci = zaci;
        }
        
        @Override
        protected final R createFailedResult(final Status status) {
            if (status.getStatusCode() != this.zaci.getStatus().getStatusCode()) {
                throw new UnsupportedOperationException("Creating failed results is not supported");
            }
            return this.zaci;
        }
    }
    
    private static final class zab<R extends Result> extends BasePendingResult<R>
    {
        private final R zacj;
        
        public zab(final GoogleApiClient googleApiClient, final R zacj) {
            super(googleApiClient);
            this.zacj = zacj;
        }
        
        @Override
        protected final R createFailedResult(final Status status) {
            return this.zacj;
        }
    }
    
    private static final class zac<R extends Result> extends BasePendingResult<R>
    {
        public zac(final GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }
        
        @Override
        protected final R createFailedResult(final Status status) {
            throw new UnsupportedOperationException("Creating failed results is not supported");
        }
    }
}
