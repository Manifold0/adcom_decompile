// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.account;

import com.google.android.gms.common.internal.PendingResultUtil$ResultConverter;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.PendingResultUtil;
import android.accounts.Account;
import com.google.android.gms.tasks.Task;
import android.content.Context;
import com.google.android.gms.internal.auth.zzh;
import com.google.android.gms.common.api.Api$ApiOptions;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi$Settings;
import android.support.annotation.NonNull;
import android.app.Activity;
import com.google.android.gms.common.api.Api$ApiOptions$NoOptions;
import com.google.android.gms.common.api.GoogleApi;

public class WorkAccountClient extends GoogleApi<Api$ApiOptions$NoOptions>
{
    private final WorkAccountApi zzac;
    
    WorkAccountClient(@NonNull final Activity activity) {
        super(activity, (Api)WorkAccount.API, (Api$ApiOptions)null, GoogleApi$Settings.DEFAULT_SETTINGS);
        this.zzac = new zzh();
    }
    
    WorkAccountClient(@NonNull final Context context) {
        super(context, (Api)WorkAccount.API, (Api$ApiOptions)null, GoogleApi$Settings.DEFAULT_SETTINGS);
        this.zzac = new zzh();
    }
    
    public Task<Account> addWorkAccount(final String s) {
        return (Task<Account>)PendingResultUtil.toTask((PendingResult)this.zzac.addWorkAccount(this.asGoogleApiClient(), s), (PendingResultUtil$ResultConverter)new zzg(this));
    }
    
    public Task<Void> removeWorkAccount(final Account account) {
        return (Task<Void>)PendingResultUtil.toVoidTask((PendingResult)this.zzac.removeWorkAccount(this.asGoogleApiClient(), account));
    }
    
    public Task<Void> setWorkAuthenticatorEnabled(final boolean b) {
        return (Task<Void>)PendingResultUtil.toVoidTask((PendingResult)this.zzac.setWorkAuthenticatorEnabledWithResult(this.asGoogleApiClient(), b));
    }
}
