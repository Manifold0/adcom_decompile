// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Releasable;

@KeepForSdk
public class DataHolderResult implements Releasable, Result
{
    @KeepForSdk
    protected final DataHolder mDataHolder;
    @KeepForSdk
    protected final Status mStatus;
    
    @KeepForSdk
    protected DataHolderResult(final DataHolder dataHolder) {
        this(dataHolder, new Status(dataHolder.getStatusCode()));
    }
    
    @KeepForSdk
    protected DataHolderResult(final DataHolder mDataHolder, final Status mStatus) {
        this.mStatus = mStatus;
        this.mDataHolder = mDataHolder;
    }
    
    @KeepForSdk
    public Status getStatus() {
        return this.mStatus;
    }
    
    @KeepForSdk
    public void release() {
        if (this.mDataHolder != null) {
            this.mDataHolder.close();
        }
    }
}
