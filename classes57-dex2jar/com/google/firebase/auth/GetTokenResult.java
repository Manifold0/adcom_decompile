// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.auth;

import android.support.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.Map;
import com.google.firebase.annotations.PublicApi;

@PublicApi
public class GetTokenResult
{
    private String zza;
    private Map<String, Object> zzb;
    
    @KeepForSdk
    public GetTokenResult(final String zza, final Map<String, Object> zzb) {
        this.zza = zza;
        this.zzb = zzb;
    }
    
    private long zza(final String s) {
        final Integer n = this.zzb.get(s);
        if (n == null) {
            return 0L;
        }
        return n;
    }
    
    @PublicApi
    public long getAuthTimestamp() {
        return this.zza("auth_time");
    }
    
    @PublicApi
    public Map<String, Object> getClaims() {
        return this.zzb;
    }
    
    @PublicApi
    public long getExpirationTimestamp() {
        return this.zza("exp");
    }
    
    @PublicApi
    public long getIssuedAtTimestamp() {
        return this.zza("iat");
    }
    
    @Nullable
    @PublicApi
    public String getSignInProvider() {
        final Map<String, String> map = this.zzb.get("firebase");
        if (map != null) {
            return map.get("sign_in_provider");
        }
        return null;
    }
    
    @Nullable
    @PublicApi
    public String getToken() {
        return this.zza;
    }
}
