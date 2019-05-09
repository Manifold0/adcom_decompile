// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.signin;

import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Api;

public final class SignInOptions implements Optional
{
    public static final SignInOptions DEFAULT;
    private final boolean zaaa;
    private final String zaab;
    private final String zaac;
    private final boolean zarv;
    private final boolean zarw;
    private final Long zarx;
    private final Long zary;
    private final boolean zay;
    
    static {
        new zaa();
        DEFAULT = new SignInOptions(false, false, null, false, null, false, null, null);
    }
    
    private SignInOptions(final boolean b, final boolean b2, final String s, final boolean b3, final String s2, final boolean b4, final Long n, final Long n2) {
        this.zarv = false;
        this.zay = false;
        this.zaab = null;
        this.zaaa = false;
        this.zarw = false;
        this.zaac = null;
        this.zarx = null;
        this.zary = null;
    }
    
    @Nullable
    public final Long getAuthApiSignInModuleVersion() {
        return this.zarx;
    }
    
    @Nullable
    public final String getHostedDomain() {
        return this.zaac;
    }
    
    @Nullable
    public final Long getRealClientLibraryVersion() {
        return this.zary;
    }
    
    public final String getServerClientId() {
        return this.zaab;
    }
    
    public final boolean isForceCodeForRefreshToken() {
        return this.zaaa;
    }
    
    public final boolean isIdTokenRequested() {
        return this.zay;
    }
    
    public final boolean isOfflineAccessRequested() {
        return this.zarv;
    }
    
    public final boolean waitForAccessTokenRefresh() {
        return this.zarw;
    }
    
    public static final class zaa
    {
    }
}
