// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.credentials;

import com.google.android.gms.common.api.Response;
import com.google.android.gms.internal.auth-api.zzq;
import android.app.PendingIntent;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.tasks.Task;
import android.content.Context;
import com.google.android.gms.common.api.internal.StatusExceptionMapper;
import com.google.android.gms.common.api.Api$ApiOptions;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.ApiExceptionMapper;
import android.support.annotation.NonNull;
import android.app.Activity;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApi;

public class CredentialsClient extends GoogleApi<Auth.AuthCredentialsOptions>
{
    CredentialsClient(@NonNull final Activity activity, @NonNull final Auth.AuthCredentialsOptions authCredentialsOptions) {
        super(activity, (Api)Auth.CREDENTIALS_API, (Api$ApiOptions)authCredentialsOptions, (StatusExceptionMapper)new ApiExceptionMapper());
    }
    
    CredentialsClient(@NonNull final Context context, @NonNull final Auth.AuthCredentialsOptions authCredentialsOptions) {
        super(context, (Api)Auth.CREDENTIALS_API, (Api$ApiOptions)authCredentialsOptions, (StatusExceptionMapper)new ApiExceptionMapper());
    }
    
    public Task<Void> delete(@NonNull final Credential credential) {
        return (Task<Void>)PendingResultUtil.toVoidTask((PendingResult)Auth.CredentialsApi.delete(this.asGoogleApiClient(), credential));
    }
    
    public Task<Void> disableAutoSignIn() {
        return (Task<Void>)PendingResultUtil.toVoidTask((PendingResult)Auth.CredentialsApi.disableAutoSignIn(this.asGoogleApiClient()));
    }
    
    public PendingIntent getHintPickerIntent(@NonNull final HintRequest hintRequest) {
        return zzq.zzc(this.getApplicationContext(), (Auth.AuthCredentialsOptions)this.getApiOptions(), hintRequest);
    }
    
    public Task<CredentialRequestResponse> request(@NonNull final CredentialRequest credentialRequest) {
        return (Task<CredentialRequestResponse>)PendingResultUtil.toResponseTask((PendingResult)Auth.CredentialsApi.request(this.asGoogleApiClient(), credentialRequest), (Response)new CredentialRequestResponse());
    }
    
    public Task<Void> save(@NonNull final Credential credential) {
        return (Task<Void>)PendingResultUtil.toVoidTask((PendingResult)Auth.CredentialsApi.save(this.asGoogleApiClient(), credential));
    }
}
