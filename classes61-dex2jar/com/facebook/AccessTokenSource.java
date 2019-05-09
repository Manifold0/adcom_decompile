// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook;

public enum AccessTokenSource
{
    CHROME_CUSTOM_TAB(true), 
    CLIENT_TOKEN(true), 
    DEVICE_AUTH(true), 
    FACEBOOK_APPLICATION_NATIVE(true), 
    FACEBOOK_APPLICATION_SERVICE(true), 
    FACEBOOK_APPLICATION_WEB(true), 
    NONE(false), 
    TEST_USER(true), 
    WEB_VIEW(true);
    
    private final boolean canExtendToken;
    
    private AccessTokenSource(final boolean canExtendToken) {
        this.canExtendToken = canExtendToken;
    }
    
    boolean canExtendToken() {
        return this.canExtendToken;
    }
}
