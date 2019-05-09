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

abstract class zzap extends BaseImplementation$ApiMethodImpl<ProxyApi.ProxyResult, zzak>
{
    public zzap(final GoogleApiClient googleApiClient) {
        super((Api)AuthProxy.API, googleApiClient);
    }
    
    protected abstract void zza(final Context p0, final zzan p1) throws RemoteException;
}
