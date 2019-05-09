// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.auth-api;

import android.os.DeadObjectException;
import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;
import com.google.android.gms.common.api.Result;

abstract class zzp<R extends Result> extends BaseImplementation$ApiMethodImpl<R, zzr>
{
    zzp(final GoogleApiClient googleApiClient) {
        super((Api)Auth.CREDENTIALS_API, googleApiClient);
    }
    
    protected abstract void zzc(final Context p0, final zzw p1) throws DeadObjectException, RemoteException;
}
