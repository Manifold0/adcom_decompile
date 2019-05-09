// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api;

import com.google.android.gms.internal.auth.zzar;
import com.google.android.gms.common.api.Api$AbstractClientBuilder;
import com.google.android.gms.internal.auth.zzak;
import com.google.android.gms.common.api.Api$ClientKey;
import com.google.android.gms.auth.api.proxy.ProxyApi;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public final class AuthProxy
{
    @KeepForSdk
    public static final Api<AuthProxyOptions> API;
    @KeepForSdk
    public static final ProxyApi ProxyApi;
    private static final Api$ClientKey<zzak> zzah;
    private static final Api$AbstractClientBuilder<zzak, AuthProxyOptions> zzai;
    
    static {
        zzah = new Api$ClientKey();
        zzai = new zza();
        API = new Api("Auth.PROXY_API", (Api$AbstractClientBuilder)AuthProxy.zzai, (Api$ClientKey)AuthProxy.zzah);
        ProxyApi = new zzar();
    }
}
