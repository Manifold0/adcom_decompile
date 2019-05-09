// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.credentials;

import com.google.android.gms.auth.api.Auth.AuthCredentialsOptions;
import com.google.android.gms.auth.api.Auth;

public final class CredentialsOptions extends AuthCredentialsOptions
{
    public static final CredentialsOptions DEFAULT;
    
    static {
        DEFAULT = (CredentialsOptions)((AuthCredentialsOptions.Builder)new Builder()).zzc();
    }
    
    private CredentialsOptions(final Builder builder) {
        super((AuthCredentialsOptions.Builder)builder);
    }
    
    public static final class Builder extends AuthCredentialsOptions.Builder
    {
        public final CredentialsOptions build() {
            return new CredentialsOptions(this, null);
        }
        
        public final Builder forceEnableSaveDialog() {
            this.zzn = true;
            return this;
        }
    }
}
