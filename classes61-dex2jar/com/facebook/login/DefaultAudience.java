// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.login;

public enum DefaultAudience
{
    EVERYONE("everyone"), 
    FRIENDS("friends"), 
    NONE((String)null), 
    ONLY_ME("only_me");
    
    private final String nativeProtocolAudience;
    
    private DefaultAudience(final String nativeProtocolAudience) {
        this.nativeProtocolAudience = nativeProtocolAudience;
    }
    
    public String getNativeProtocolAudience() {
        return this.nativeProtocolAudience;
    }
}
