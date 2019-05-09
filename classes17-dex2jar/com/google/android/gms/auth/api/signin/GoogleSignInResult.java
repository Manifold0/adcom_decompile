// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.signin;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Result;

public class GoogleSignInResult implements Result
{
    private Status mStatus;
    private GoogleSignInAccount zzaz;
    
    public GoogleSignInResult(@Nullable final GoogleSignInAccount zzaz, @NonNull final Status mStatus) {
        this.zzaz = zzaz;
        this.mStatus = mStatus;
    }
    
    @Nullable
    public GoogleSignInAccount getSignInAccount() {
        return this.zzaz;
    }
    
    @NonNull
    public Status getStatus() {
        return this.mStatus;
    }
    
    public boolean isSuccess() {
        return this.mStatus.isSuccess();
    }
}
