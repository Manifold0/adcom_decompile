// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.signin.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;
import com.google.android.gms.common.api.Result;

abstract class zzo<R extends Result> extends BaseImplementation$ApiMethodImpl<R, zzg>
{
    public zzo(final GoogleApiClient googleApiClient) {
        super((Api)Auth.GOOGLE_SIGN_IN_API, googleApiClient);
    }
}
