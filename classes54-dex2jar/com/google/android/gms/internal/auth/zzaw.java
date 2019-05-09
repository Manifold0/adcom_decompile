// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.auth;

import com.google.android.gms.auth.api.proxy.ProxyResponse;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.auth.api.proxy.ProxyApi;

final class zzaw implements ProxyResult
{
    private Status mStatus;
    private ProxyResponse zzch;
    
    public zzaw(final ProxyResponse zzch) {
        this.zzch = zzch;
        this.mStatus = Status.RESULT_SUCCESS;
    }
    
    public zzaw(final Status mStatus) {
        this.mStatus = mStatus;
    }
    
    @Override
    public final ProxyResponse getResponse() {
        return this.zzch;
    }
    
    public final Status getStatus() {
        return this.mStatus;
    }
}
