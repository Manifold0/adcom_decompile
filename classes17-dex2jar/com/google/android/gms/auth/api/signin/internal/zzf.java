// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.signin.internal;

import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import android.content.Intent;
import com.google.android.gms.common.api.Api$AnyClientKey;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;

public final class zzf implements GoogleSignInApi
{
    private static GoogleSignInOptions zzc(final GoogleApiClient googleApiClient) {
        return ((zzg)googleApiClient.getClient((Api$AnyClientKey)Auth.zzh)).zzg();
    }
    
    @Override
    public final Intent getSignInIntent(final GoogleApiClient googleApiClient) {
        return zzh.zzc(googleApiClient.getContext(), zzc(googleApiClient));
    }
    
    @Override
    public final GoogleSignInResult getSignInResultFromIntent(final Intent intent) {
        return zzh.getSignInResultFromIntent(intent);
    }
    
    @Override
    public final PendingResult<Status> revokeAccess(final GoogleApiClient googleApiClient) {
        return zzh.zzd(googleApiClient, googleApiClient.getContext(), false);
    }
    
    @Override
    public final PendingResult<Status> signOut(final GoogleApiClient googleApiClient) {
        return zzh.zzc(googleApiClient, googleApiClient.getContext(), false);
    }
    
    @Override
    public final OptionalPendingResult<GoogleSignInResult> silentSignIn(final GoogleApiClient googleApiClient) {
        return zzh.zzc(googleApiClient, googleApiClient.getContext(), zzc(googleApiClient), false);
    }
}
