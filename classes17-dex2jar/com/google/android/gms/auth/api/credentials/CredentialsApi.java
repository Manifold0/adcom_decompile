// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.credentials;

import android.app.PendingIntent;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;

public interface CredentialsApi
{
    public static final int ACTIVITY_RESULT_ADD_ACCOUNT = 1000;
    public static final int ACTIVITY_RESULT_NO_HINTS_AVAILABLE = 1002;
    public static final int ACTIVITY_RESULT_OTHER_ACCOUNT = 1001;
    public static final int CREDENTIAL_PICKER_REQUEST_CODE = 2000;
    
    PendingResult<Status> delete(final GoogleApiClient p0, final Credential p1);
    
    PendingResult<Status> disableAutoSignIn(final GoogleApiClient p0);
    
    PendingIntent getHintPickerIntent(final GoogleApiClient p0, final HintRequest p1);
    
    PendingResult<CredentialRequestResult> request(final GoogleApiClient p0, final CredentialRequest p1);
    
    PendingResult<Status> save(final GoogleApiClient p0, final Credential p1);
}
