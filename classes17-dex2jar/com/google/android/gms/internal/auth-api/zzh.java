// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.auth-api;

import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.auth.api.credentials.CredentialRequestResult;

public final class zzh implements CredentialRequestResult
{
    private final Status mStatus;
    private final Credential zzal;
    
    public zzh(final Status mStatus, final Credential zzal) {
        this.mStatus = mStatus;
        this.zzal = zzal;
    }
    
    public static zzh zzd(final Status status) {
        return new zzh(status, null);
    }
    
    @Override
    public final Credential getCredential() {
        return this.zzal;
    }
    
    public final Status getStatus() {
        return this.mStatus;
    }
}
