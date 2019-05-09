// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.auth;

import com.google.android.gms.common.internal.Preconditions;
import android.support.annotation.NonNull;
import com.google.firebase.annotations.PublicApi;
import com.google.firebase.FirebaseException;

@PublicApi
public class FirebaseAuthException extends FirebaseException
{
    private final String zza;
    
    @PublicApi
    public FirebaseAuthException(@NonNull final String s, @NonNull final String s2) {
        super(s2);
        this.zza = Preconditions.checkNotEmpty(s);
    }
    
    @NonNull
    @PublicApi
    public String getErrorCode() {
        return this.zza;
    }
}
