// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook;

import com.facebook.internal.Validate;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.SharedPreferences;

final class ProfileCache
{
    static final String CACHED_PROFILE_KEY = "com.facebook.ProfileManager.CachedProfile";
    static final String SHARED_PREFERENCES_NAME = "com.facebook.AccessTokenManager.SharedPreferences";
    private final SharedPreferences sharedPreferences;
    
    ProfileCache() {
        this.sharedPreferences = FacebookSdk.getApplicationContext().getSharedPreferences("com.facebook.AccessTokenManager.SharedPreferences", 0);
    }
    
    void clear() {
        this.sharedPreferences.edit().remove("com.facebook.ProfileManager.CachedProfile").apply();
    }
    
    Profile load() {
        final String string = this.sharedPreferences.getString("com.facebook.ProfileManager.CachedProfile", (String)null);
        if (string != null) {
            try {
                return new Profile(new JSONObject(string));
            }
            catch (JSONException ex) {}
        }
        return null;
    }
    
    void save(final Profile profile) {
        Validate.notNull(profile, "profile");
        final JSONObject jsonObject = profile.toJSONObject();
        if (jsonObject != null) {
            this.sharedPreferences.edit().putString("com.facebook.ProfileManager.CachedProfile", jsonObject.toString()).apply();
        }
    }
}
