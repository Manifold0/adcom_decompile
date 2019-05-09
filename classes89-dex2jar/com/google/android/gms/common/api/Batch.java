// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api;

import java.util.ArrayList;
import java.util.List;
import com.google.android.gms.common.api.internal.BasePendingResult;

public final class Batch extends BasePendingResult<BatchResult>
{
    private final Object mLock;
    private int zaaz;
    private boolean zaba;
    private boolean zabb;
    private final PendingResult<?>[] zabc;
    
    private Batch(final List<PendingResult<?>> list, final GoogleApiClient googleApiClient) {
        super(googleApiClient);
        this.mLock = new Object();
        this.zaaz = list.size();
        this.zabc = (PendingResult<?>[])new PendingResult[this.zaaz];
        if (list.isEmpty()) {
            this.setResult(new BatchResult(Status.RESULT_SUCCESS, this.zabc));
        }
        else {
            for (int i = 0; i < list.size(); ++i) {
                (this.zabc[i] = list.get(i)).addStatusListener((StatusListener)new com.google.android.gms.common.api.zaa(this));
            }
        }
    }
    
    @Override
    public final void cancel() {
        super.cancel();
        final PendingResult<?>[] zabc = this.zabc;
        for (int length = zabc.length, i = 0; i < length; ++i) {
            zabc[i].cancel();
        }
    }
    
    public final BatchResult createFailedResult(final Status status) {
        return new BatchResult(status, this.zabc);
    }
    
    public static final class Builder
    {
        private List<PendingResult<?>> zabe;
        private GoogleApiClient zabf;
        
        public Builder(final GoogleApiClient zabf) {
            this.zabe = new ArrayList<PendingResult<?>>();
            this.zabf = zabf;
        }
        
        public final <R extends Result> BatchResultToken<R> add(final PendingResult<R> pendingResult) {
            final BatchResultToken<R> batchResultToken = new BatchResultToken<R>(this.zabe.size());
            this.zabe.add(pendingResult);
            return batchResultToken;
        }
        
        public final Batch build() {
            return new Batch(this.zabe, this.zabf, null);
        }
    }
}
