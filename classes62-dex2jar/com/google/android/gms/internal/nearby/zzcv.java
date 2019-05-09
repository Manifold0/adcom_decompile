// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.nearby.Nearby;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;
import com.google.android.gms.common.api.Result;

abstract class zzcv<R extends Result> extends BaseImplementation$ApiMethodImpl<R, zzx>
{
    public zzcv(final GoogleApiClient googleApiClient) {
        super((Api)Nearby.CONNECTIONS_API, googleApiClient);
    }
}
