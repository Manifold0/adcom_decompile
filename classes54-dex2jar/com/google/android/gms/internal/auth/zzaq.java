// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.auth;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.auth.api.AuthProxy;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.auth.api.proxy.ProxyApi;
import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;

abstract class zzaq extends BaseImplementation$ApiMethodImpl<ProxyApi.SpatulaHeaderResult, zzak>
{
    public zzaq(final GoogleApiClient googleApiClient) {
        super((Api)AuthProxy.API, googleApiClient);
    }
    
    protected static ProxyApi.SpatulaHeaderResult zzc(final Status status) {
        return new zzax(status);
    }
    
    protected abstract void zza(final Context p0, final zzan p1) throws RemoteException;
}
