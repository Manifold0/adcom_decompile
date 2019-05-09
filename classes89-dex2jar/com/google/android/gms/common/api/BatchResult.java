// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api;

import java.util.concurrent.TimeUnit;
import com.google.android.gms.common.internal.Preconditions;

public final class BatchResult implements Result
{
    private final Status mStatus;
    private final PendingResult<?>[] zabc;
    
    BatchResult(final Status mStatus, final PendingResult<?>[] zabc) {
        this.mStatus = mStatus;
        this.zabc = zabc;
    }
    
    public final Status getStatus() {
        return this.mStatus;
    }
    
    public final <R extends Result> R take(final BatchResultToken<R> batchResultToken) {
        Preconditions.checkArgument(batchResultToken.mId < this.zabc.length, (Object)"The result token does not belong to this batch");
        return (R)this.zabc[batchResultToken.mId].await(0L, TimeUnit.MILLISECONDS);
    }
}
