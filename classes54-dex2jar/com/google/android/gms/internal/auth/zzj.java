// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.auth;

import android.os.RemoteException;
import com.google.android.gms.auth.account.zza;
import com.google.android.gms.auth.account.zzc;
import com.google.android.gms.common.api.Api$AnyClient;
import android.accounts.Account;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.auth.account.WorkAccountApi;
import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;

final class zzj extends BaseImplementation$ApiMethodImpl<WorkAccountApi.AddAccountResult, zzr>
{
    private final /* synthetic */ String zzq;
    
    zzj(final zzh zzh, final Api api, final GoogleApiClient googleApiClient, final String zzq) {
        this.zzq = zzq;
        super(api, googleApiClient);
    }
}
