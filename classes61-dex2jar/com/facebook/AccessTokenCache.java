// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook;

import com.facebook.internal.Validate;
import android.os.Bundle;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.SharedPreferences;

class AccessTokenCache
{
    static final String CACHED_ACCESS_TOKEN_KEY = "com.facebook.AccessTokenManager.CachedAccessToken";
    private final SharedPreferences sharedPreferences;
    private LegacyTokenHelper tokenCachingStrategy;
    private final SharedPreferencesTokenCachingStrategyFactory tokenCachingStrategyFactory;
    
    public AccessTokenCache() {
        this(FacebookSdk.getApplicationContext().getSharedPreferences("com.facebook.AccessTokenManager.SharedPreferences", 0), new SharedPreferencesTokenCachingStrategyFactory());
    }
    
    AccessTokenCache(final SharedPreferences sharedPreferences, final SharedPreferencesTokenCachingStrategyFactory tokenCachingStrategyFactory) {
        this.sharedPreferences = sharedPreferences;
        this.tokenCachingStrategyFactory = tokenCachingStrategyFactory;
    }
    
    private AccessToken getCachedAccessToken() {
        AccessToken fromJSONObject = null;
        final String string = this.sharedPreferences.getString("com.facebook.AccessTokenManager.CachedAccessToken", (String)null);
        if (string == null) {
            return fromJSONObject;
        }
        try {
            fromJSONObject = AccessToken.createFromJSONObject(new JSONObject(string));
            return fromJSONObject;
        }
        catch (JSONException ex) {
            return null;
        }
    }
    
    private AccessToken getLegacyAccessToken() {
        final AccessToken accessToken = null;
        final Bundle load = this.getTokenCachingStrategy().load();
        AccessToken fromLegacyCache = accessToken;
        if (load != null) {
            fromLegacyCache = accessToken;
            if (LegacyTokenHelper.hasTokenInformation(load)) {
                fromLegacyCache = AccessToken.createFromLegacyCache(load);
            }
        }
        return fromLegacyCache;
    }
    
    private LegacyTokenHelper getTokenCachingStrategy() {
        Label_0029: {
            if (this.tokenCachingStrategy != null) {
                break Label_0029;
            }
            synchronized (this) {
                if (this.tokenCachingStrategy == null) {
                    this.tokenCachingStrategy = this.tokenCachingStrategyFactory.create();
                }
                // monitorexit(this)
                return this.tokenCachingStrategy;
            }
        }
    }
    
    private boolean hasCachedAccessToken() {
        return this.sharedPreferences.contains("com.facebook.AccessTokenManager.CachedAccessToken");
    }
    
    private boolean shouldCheckLegacyToken() {
        return FacebookSdk.isLegacyTokenUpgradeSupported();
    }
    
    public void clear() {
        this.sharedPreferences.edit().remove("com.facebook.AccessTokenManager.CachedAccessToken").apply();
        if (this.shouldCheckLegacyToken()) {
            this.getTokenCachingStrategy().clear();
        }
    }
    
    public AccessToken load() {
        AccessToken cachedAccessToken = null;
        if (this.hasCachedAccessToken()) {
            cachedAccessToken = this.getCachedAccessToken();
        }
        else if (this.shouldCheckLegacyToken()) {
            final AccessToken legacyAccessToken = this.getLegacyAccessToken();
            if ((cachedAccessToken = legacyAccessToken) != null) {
                this.save(legacyAccessToken);
                this.getTokenCachingStrategy().clear();
                return legacyAccessToken;
            }
        }
        return cachedAccessToken;
    }
    
    public void save(final AccessToken accessToken) {
        Validate.notNull(accessToken, "accessToken");
        try {
            this.sharedPreferences.edit().putString("com.facebook.AccessTokenManager.CachedAccessToken", accessToken.toJSONObject().toString()).apply();
        }
        catch (JSONException ex) {}
    }
    
    static class SharedPreferencesTokenCachingStrategyFactory
    {
        public LegacyTokenHelper create() {
            return new LegacyTokenHelper(FacebookSdk.getApplicationContext());
        }
    }
}
