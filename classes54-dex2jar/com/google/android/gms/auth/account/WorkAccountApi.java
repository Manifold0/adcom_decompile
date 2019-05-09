// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.account;

import com.google.android.gms.common.api.Result;
import android.accounts.Account;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;

@Deprecated
public interface WorkAccountApi
{
    @Deprecated
    PendingResult<AddAccountResult> addWorkAccount(final GoogleApiClient p0, final String p1);
    
    @Deprecated
    PendingResult<Result> removeWorkAccount(final GoogleApiClient p0, final Account p1);
    
    @Deprecated
    void setWorkAuthenticatorEnabled(final GoogleApiClient p0, final boolean p1);
    
    @Deprecated
    PendingResult<Result> setWorkAuthenticatorEnabledWithResult(final GoogleApiClient p0, final boolean p1);
    
    @Deprecated
    public interface AddAccountResult extends Result
    {
        Account getAccount();
    }
}
