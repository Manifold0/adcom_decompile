// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api;

import android.os.Bundle;
import com.google.android.gms.common.api.Api$ApiOptions$Optional;
import com.google.android.gms.auth.api.signin.internal.zzf;
import com.google.android.gms.internal.auth-api.zzi;
import com.google.android.gms.common.api.Api$AbstractClientBuilder;
import com.google.android.gms.auth.api.signin.internal.zzg;
import com.google.android.gms.internal.auth-api.zzr;
import com.google.android.gms.common.api.Api$ClientKey;
import com.google.android.gms.auth.api.proxy.ProxyApi;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.common.api.Api;

public final class Auth
{
    public static final Api<AuthCredentialsOptions> CREDENTIALS_API;
    public static final CredentialsApi CredentialsApi;
    public static final Api<GoogleSignInOptions> GOOGLE_SIGN_IN_API;
    public static final GoogleSignInApi GoogleSignInApi;
    @Deprecated
    @KeepForSdk
    public static final Api<AuthProxyOptions> PROXY_API;
    @Deprecated
    @KeepForSdk
    public static final ProxyApi ProxyApi;
    public static final Api$ClientKey<zzr> zzg;
    public static final Api$ClientKey<zzg> zzh;
    private static final Api$AbstractClientBuilder<zzr, AuthCredentialsOptions> zzi;
    private static final Api$AbstractClientBuilder<zzg, GoogleSignInOptions> zzj;
    
    static {
        zzg = new Api$ClientKey();
        zzh = new Api$ClientKey();
        zzi = new zzc();
        zzj = new zzd();
        PROXY_API = AuthProxy.API;
        CREDENTIALS_API = new Api("Auth.CREDENTIALS_API", (Api$AbstractClientBuilder)Auth.zzi, (Api$ClientKey)Auth.zzg);
        GOOGLE_SIGN_IN_API = new Api("Auth.GOOGLE_SIGN_IN_API", (Api$AbstractClientBuilder)Auth.zzj, (Api$ClientKey)Auth.zzh);
        ProxyApi = AuthProxy.ProxyApi;
        CredentialsApi = new zzi();
        GoogleSignInApi = new zzf();
    }
    
    private Auth() {
    }
    
    @Deprecated
    public static class AuthCredentialsOptions implements Api$ApiOptions$Optional
    {
        private static final AuthCredentialsOptions zzk;
        private final String zzl;
        private final boolean zzm;
        
        static {
            zzk = new Builder().zzc();
        }
        
        public AuthCredentialsOptions(final Builder builder) {
            this.zzl = null;
            this.zzm = builder.zzn;
        }
        
        public final Bundle toBundle() {
            final Bundle bundle = new Bundle();
            bundle.putString("consumer_package", (String)null);
            bundle.putBoolean("force_save_dialog", this.zzm);
            return bundle;
        }
        
        @Deprecated
        public static class Builder
        {
            protected Boolean zzn;
            
            public Builder() {
                this.zzn = false;
            }
            
            public Builder forceEnableSaveDialog() {
                this.zzn = true;
                return this;
            }
            
            public AuthCredentialsOptions zzc() {
                return new AuthCredentialsOptions(this);
            }
        }
    }
}
