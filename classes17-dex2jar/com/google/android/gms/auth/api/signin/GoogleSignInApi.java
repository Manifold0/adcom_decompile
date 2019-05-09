// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.signin;

import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;

public interface GoogleSignInApi
{
    public static final String EXTRA_SIGN_IN_ACCOUNT = "signInAccount";
    
    Intent getSignInIntent(final GoogleApiClient p0);
    
    GoogleSignInResult getSignInResultFromIntent(final Intent p0);
    
    PendingResult<Status> revokeAccess(final GoogleApiClient p0);
    
    PendingResult<Status> signOut(final GoogleApiClient p0);
    
    OptionalPendingResult<GoogleSignInResult> silentSignIn(final GoogleApiClient p0);
}
