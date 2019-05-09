// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.auth;

import com.google.android.gms.common.api.Result;
import android.accounts.Account;
import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.auth.account.WorkAccount;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.auth.account.WorkAccountApi;

public final class zzh implements WorkAccountApi
{
    private static final Status zzad;
    
    static {
        zzad = new Status(13);
    }
    
    @Override
    public final PendingResult<AddAccountResult> addWorkAccount(final GoogleApiClient googleApiClient, final String s) {
        return (PendingResult<AddAccountResult>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzj(this, WorkAccount.API, googleApiClient, s));
    }
    
    @Override
    public final PendingResult<Result> removeWorkAccount(final GoogleApiClient googleApiClient, final Account account) {
        return (PendingResult<Result>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzl(this, WorkAccount.API, googleApiClient, account));
    }
    
    @Override
    public final void setWorkAuthenticatorEnabled(final GoogleApiClient googleApiClient, final boolean b) {
        this.setWorkAuthenticatorEnabledWithResult(googleApiClient, b);
    }
    
    @Override
    public final PendingResult<Result> setWorkAuthenticatorEnabledWithResult(final GoogleApiClient googleApiClient, final boolean b) {
        return (PendingResult<Result>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzi(this, WorkAccount.API, googleApiClient, b));
    }
}
