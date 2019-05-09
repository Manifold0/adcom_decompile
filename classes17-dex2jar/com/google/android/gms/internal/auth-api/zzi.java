// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.auth-api;

import com.google.android.gms.auth.api.credentials.CredentialRequestResult;
import com.google.android.gms.auth.api.credentials.CredentialRequest;
import com.google.android.gms.common.api.Api$AnyClientKey;
import com.google.android.gms.auth.api.Auth;
import android.app.PendingIntent;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.auth.api.credentials.CredentialsApi;

public final class zzi implements CredentialsApi
{
    @Override
    public final PendingResult<Status> delete(final GoogleApiClient googleApiClient, final Credential credential) {
        Preconditions.checkNotNull((Object)googleApiClient, (Object)"client must not be null");
        Preconditions.checkNotNull((Object)credential, (Object)"credential must not be null");
        return (PendingResult<Status>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzm(this, googleApiClient, credential));
    }
    
    @Override
    public final PendingResult<Status> disableAutoSignIn(final GoogleApiClient googleApiClient) {
        Preconditions.checkNotNull((Object)googleApiClient, (Object)"client must not be null");
        return (PendingResult<Status>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzn(this, googleApiClient));
    }
    
    @Override
    public final PendingIntent getHintPickerIntent(final GoogleApiClient googleApiClient, final HintRequest hintRequest) {
        Preconditions.checkNotNull((Object)googleApiClient, (Object)"client must not be null");
        Preconditions.checkNotNull((Object)hintRequest, (Object)"request must not be null");
        return zzq.zzc(googleApiClient.getContext(), ((zzr)googleApiClient.getClient((Api$AnyClientKey)Auth.zzg)).zzd(), hintRequest);
    }
    
    @Override
    public final PendingResult<CredentialRequestResult> request(final GoogleApiClient googleApiClient, final CredentialRequest credentialRequest) {
        Preconditions.checkNotNull((Object)googleApiClient, (Object)"client must not be null");
        Preconditions.checkNotNull((Object)credentialRequest, (Object)"request must not be null");
        return (PendingResult<CredentialRequestResult>)googleApiClient.enqueue((BaseImplementation$ApiMethodImpl)new zzj(this, googleApiClient, credentialRequest));
    }
    
    @Override
    public final PendingResult<Status> save(final GoogleApiClient googleApiClient, final Credential credential) {
        Preconditions.checkNotNull((Object)googleApiClient, (Object)"client must not be null");
        Preconditions.checkNotNull((Object)credential, (Object)"credential must not be null");
        return (PendingResult<Status>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzl(this, googleApiClient, credential));
    }
}
