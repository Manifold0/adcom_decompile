// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal.service;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;

public final class Common
{
    @KeepForSdk
    public static final Api<Api.ApiOptions.NoOptions> API;
    @KeepForSdk
    public static final Api.ClientKey<zai> CLIENT_KEY;
    private static final Api.AbstractClientBuilder<zai, Api.ApiOptions.NoOptions> zaph;
    public static final zac zapi;
    
    static {
        CLIENT_KEY = new Api.ClientKey();
        zaph = new zab();
        API = new Api<Api.ApiOptions.NoOptions>("Common.API", (Api.AbstractClientBuilder<C, Api.ApiOptions.NoOptions>)Common.zaph, (Api.ClientKey<C>)Common.CLIENT_KEY);
        zapi = new zad();
    }
}
