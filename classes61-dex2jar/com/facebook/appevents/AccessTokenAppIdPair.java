// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.appevents;

import com.facebook.internal.Utility;
import com.facebook.FacebookSdk;
import com.facebook.AccessToken;
import java.io.Serializable;

class AccessTokenAppIdPair implements Serializable
{
    private static final long serialVersionUID = 1L;
    private final String accessTokenString;
    private final String applicationId;
    
    public AccessTokenAppIdPair(final AccessToken accessToken) {
        this(accessToken.getToken(), FacebookSdk.getApplicationId());
    }
    
    public AccessTokenAppIdPair(final String s, final String applicationId) {
        String accessTokenString = s;
        if (Utility.isNullOrEmpty(s)) {
            accessTokenString = null;
        }
        this.accessTokenString = accessTokenString;
        this.applicationId = applicationId;
    }
    
    private Object writeReplace() {
        return new SerializationProxyV1(this.accessTokenString, this.applicationId);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof AccessTokenAppIdPair) {
            final AccessTokenAppIdPair accessTokenAppIdPair = (AccessTokenAppIdPair)o;
            if (Utility.areObjectsEqual(accessTokenAppIdPair.accessTokenString, this.accessTokenString) && Utility.areObjectsEqual(accessTokenAppIdPair.applicationId, this.applicationId)) {
                return true;
            }
        }
        return false;
    }
    
    public String getAccessTokenString() {
        return this.accessTokenString;
    }
    
    public String getApplicationId() {
        return this.applicationId;
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        int hashCode2;
        if (this.accessTokenString == null) {
            hashCode2 = 0;
        }
        else {
            hashCode2 = this.accessTokenString.hashCode();
        }
        if (this.applicationId != null) {
            hashCode = this.applicationId.hashCode();
        }
        return hashCode2 ^ hashCode;
    }
    
    static class SerializationProxyV1 implements Serializable
    {
        private static final long serialVersionUID = -2488473066578201069L;
        private final String accessTokenString;
        private final String appId;
        
        private SerializationProxyV1(final String accessTokenString, final String appId) {
            this.accessTokenString = accessTokenString;
            this.appId = appId;
        }
        
        private Object readResolve() {
            return new AccessTokenAppIdPair(this.accessTokenString, this.appId);
        }
    }
}
