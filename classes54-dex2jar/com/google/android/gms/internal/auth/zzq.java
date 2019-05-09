// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.auth;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Result;

final class zzq implements Result
{
    private final Status mStatus;
    
    public zzq(final Status mStatus) {
        this.mStatus = mStatus;
    }
    
    public final Status getStatus() {
        return this.mStatus;
    }
}
