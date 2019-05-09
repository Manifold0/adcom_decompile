// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class StatusCallback extends Stub
{
    @KeepForSdk
    private final BaseImplementation.ResultHolder<Status> mResultHolder;
    
    @KeepForSdk
    public StatusCallback(final BaseImplementation.ResultHolder<Status> mResultHolder) {
        this.mResultHolder = mResultHolder;
    }
    
    @KeepForSdk
    @Override
    public void onResult(final Status result) {
        this.mResultHolder.setResult(result);
    }
}
