// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.proxy;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public interface ProxyApi
{
    @KeepForSdk
    PendingResult<SpatulaHeaderResult> getSpatulaHeader(final GoogleApiClient p0);
    
    @KeepForSdk
    PendingResult<ProxyResult> performProxyRequest(final GoogleApiClient p0, final ProxyRequest p1);
    
    @KeepForSdk
    public interface ProxyResult extends Result
    {
        @KeepForSdk
        ProxyResponse getResponse();
    }
    
    @KeepForSdk
    public interface SpatulaHeaderResult extends Result
    {
        @KeepForSdk
        String getSpatulaHeader();
    }
}
