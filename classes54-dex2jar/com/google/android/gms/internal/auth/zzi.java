// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.auth;

import android.os.RemoteException;
import com.google.android.gms.auth.account.zzc;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;

final class zzi extends BaseImplementation$ApiMethodImpl<Result, zzr>
{
    private final /* synthetic */ boolean zzae;
    
    zzi(final zzh zzh, final Api api, final GoogleApiClient googleApiClient, final boolean zzae) {
        this.zzae = zzae;
        super(api, googleApiClient);
    }
    
    protected final Result createFailedResult(final Status status) {
        return (Result)new zzp(status);
    }
}
