// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.login;

public enum LoginBehavior
{
    DEVICE_AUTH(false, false, false, true, false, false), 
    KATANA_ONLY(false, true, false, false, false, false), 
    NATIVE_ONLY(true, true, false, false, false, true), 
    NATIVE_WITH_FALLBACK(true, true, true, false, true, true), 
    WEB_ONLY(false, false, true, false, true, false), 
    WEB_VIEW_ONLY(false, false, true, false, false, false);
    
    private final boolean allowsCustomTabAuth;
    private final boolean allowsDeviceAuth;
    private final boolean allowsFacebookLiteAuth;
    private final boolean allowsGetTokenAuth;
    private final boolean allowsKatanaAuth;
    private final boolean allowsWebViewAuth;
    
    private LoginBehavior(final boolean allowsGetTokenAuth, final boolean allowsKatanaAuth, final boolean allowsWebViewAuth, final boolean allowsDeviceAuth, final boolean allowsCustomTabAuth, final boolean allowsFacebookLiteAuth) {
        this.allowsGetTokenAuth = allowsGetTokenAuth;
        this.allowsKatanaAuth = allowsKatanaAuth;
        this.allowsWebViewAuth = allowsWebViewAuth;
        this.allowsDeviceAuth = allowsDeviceAuth;
        this.allowsCustomTabAuth = allowsCustomTabAuth;
        this.allowsFacebookLiteAuth = allowsFacebookLiteAuth;
    }
    
    boolean allowsCustomTabAuth() {
        return this.allowsCustomTabAuth;
    }
    
    boolean allowsDeviceAuth() {
        return this.allowsDeviceAuth;
    }
    
    boolean allowsFacebookLiteAuth() {
        return this.allowsFacebookLiteAuth;
    }
    
    boolean allowsGetTokenAuth() {
        return this.allowsGetTokenAuth;
    }
    
    boolean allowsKatanaAuth() {
        return this.allowsKatanaAuth;
    }
    
    boolean allowsWebViewAuth() {
        return this.allowsWebViewAuth;
    }
}
