// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.GoogleApiClient;
import android.os.Looper;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Status;

@KeepForSdk
public class StatusPendingResult extends BasePendingResult<Status>
{
    @Deprecated
    public StatusPendingResult(final Looper looper) {
        super(looper);
    }
    
    public StatusPendingResult(final GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }
}
