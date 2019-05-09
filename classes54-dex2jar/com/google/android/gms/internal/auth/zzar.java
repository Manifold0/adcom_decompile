// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.auth;

import com.google.android.gms.auth.api.proxy.ProxyRequest;
import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.auth.api.proxy.ProxyApi;

public final class zzar implements ProxyApi
{
    @Override
    public final PendingResult<SpatulaHeaderResult> getSpatulaHeader(final GoogleApiClient googleApiClient) {
        Preconditions.checkNotNull((Object)googleApiClient);
        return (PendingResult<SpatulaHeaderResult>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzau(this, googleApiClient));
    }
    
    @Override
    public final PendingResult<ProxyResult> performProxyRequest(final GoogleApiClient googleApiClient, final ProxyRequest proxyRequest) {
        Preconditions.checkNotNull((Object)googleApiClient);
        Preconditions.checkNotNull((Object)proxyRequest);
        return (PendingResult<ProxyResult>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzas(this, googleApiClient, proxyRequest));
    }
}
