// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.signin;

import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.signin.internal.SignInClientImpl;
import com.google.android.gms.common.api.Api;

public final class zaa
{
    public static final Api<SignInOptions> API;
    private static final Api.ClientKey<SignInClientImpl> CLIENT_KEY;
    public static final Api.AbstractClientBuilder<SignInClientImpl, SignInOptions> zaph;
    private static final Scope zar;
    @ShowFirstParty
    private static final Api.ClientKey<SignInClientImpl> zars;
    private static final Api.AbstractClientBuilder<SignInClientImpl, Object> zart;
    private static final Api<Object> zaru;
    private static final Scope zas;
    
    static {
        CLIENT_KEY = new Api.ClientKey();
        zars = new Api.ClientKey();
        zaph = new zab();
        zart = new zac();
        zar = new Scope("profile");
        zas = new Scope("email");
        API = new Api<SignInOptions>("SignIn.API", (Api.AbstractClientBuilder<C, SignInOptions>)zaa.zaph, (Api.ClientKey<C>)zaa.CLIENT_KEY);
        zaru = new Api<Object>("SignIn.INTERNAL_API", (Api.AbstractClientBuilder<C, Object>)zaa.zart, (Api.ClientKey<C>)zaa.zars);
    }
}
