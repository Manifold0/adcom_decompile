// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.login;

import java.util.Set;
import com.facebook.AccessToken;

public class LoginResult
{
    private final AccessToken accessToken;
    private final Set<String> recentlyDeniedPermissions;
    private final Set<String> recentlyGrantedPermissions;
    
    public LoginResult(final AccessToken accessToken, final Set<String> recentlyGrantedPermissions, final Set<String> recentlyDeniedPermissions) {
        this.accessToken = accessToken;
        this.recentlyGrantedPermissions = recentlyGrantedPermissions;
        this.recentlyDeniedPermissions = recentlyDeniedPermissions;
    }
    
    public AccessToken getAccessToken() {
        return this.accessToken;
    }
    
    public Set<String> getRecentlyDeniedPermissions() {
        return this.recentlyDeniedPermissions;
    }
    
    public Set<String> getRecentlyGrantedPermissions() {
        return this.recentlyGrantedPermissions;
    }
}
