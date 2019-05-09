// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook;

import org.json.JSONArray;
import android.annotation.SuppressLint;
import android.content.Intent;
import org.json.JSONException;
import org.json.JSONObject;
import com.facebook.internal.Utility;
import android.text.TextUtils;
import android.os.Bundle;
import com.facebook.internal.Validate;
import android.support.annotation.Nullable;
import java.util.Collections;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import android.os.Parcel;
import java.util.Set;
import java.util.Date;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

public final class AccessToken implements Parcelable
{
    public static final String ACCESS_TOKEN_KEY = "access_token";
    private static final String APPLICATION_ID_KEY = "application_id";
    public static final Parcelable$Creator<AccessToken> CREATOR;
    private static final int CURRENT_JSON_FORMAT = 1;
    private static final String DECLINED_PERMISSIONS_KEY = "declined_permissions";
    private static final AccessTokenSource DEFAULT_ACCESS_TOKEN_SOURCE;
    private static final Date DEFAULT_EXPIRATION_TIME;
    private static final Date DEFAULT_LAST_REFRESH_TIME;
    private static final String EXPIRES_AT_KEY = "expires_at";
    public static final String EXPIRES_IN_KEY = "expires_in";
    private static final String LAST_REFRESH_KEY = "last_refresh";
    private static final Date MAX_DATE;
    private static final String PERMISSIONS_KEY = "permissions";
    private static final String SOURCE_KEY = "source";
    private static final String TOKEN_KEY = "token";
    public static final String USER_ID_KEY = "user_id";
    private static final String VERSION_KEY = "version";
    private final String applicationId;
    private final Set<String> declinedPermissions;
    private final Date expires;
    private final Date lastRefresh;
    private final Set<String> permissions;
    private final AccessTokenSource source;
    private final String token;
    private final String userId;
    
    static {
        MAX_DATE = new Date(Long.MAX_VALUE);
        DEFAULT_EXPIRATION_TIME = AccessToken.MAX_DATE;
        DEFAULT_LAST_REFRESH_TIME = new Date();
        DEFAULT_ACCESS_TOKEN_SOURCE = AccessTokenSource.FACEBOOK_APPLICATION_WEB;
        CREATOR = (Parcelable$Creator)new Parcelable$Creator() {
            public AccessToken createFromParcel(final Parcel parcel) {
                return new AccessToken(parcel);
            }
            
            public AccessToken[] newArray(final int n) {
                return new AccessToken[n];
            }
        };
    }
    
    AccessToken(final Parcel parcel) {
        this.expires = new Date(parcel.readLong());
        final ArrayList<String> list = new ArrayList<String>();
        parcel.readStringList((List)list);
        this.permissions = (Set<String>)Collections.unmodifiableSet((Set<?>)new HashSet<Object>(list));
        list.clear();
        parcel.readStringList((List)list);
        this.declinedPermissions = (Set<String>)Collections.unmodifiableSet((Set<?>)new HashSet<Object>(list));
        this.token = parcel.readString();
        this.source = AccessTokenSource.valueOf(parcel.readString());
        this.lastRefresh = new Date(parcel.readLong());
        this.applicationId = parcel.readString();
        this.userId = parcel.readString();
    }
    
    public AccessToken(final String token, final String applicationId, final String userId, @Nullable final Collection<String> collection, @Nullable final Collection<String> collection2, @Nullable AccessTokenSource default_ACCESS_TOKEN_SOURCE, @Nullable Date default_EXPIRATION_TIME, @Nullable Date default_LAST_REFRESH_TIME) {
        Validate.notNullOrEmpty(token, "accessToken");
        Validate.notNullOrEmpty(applicationId, "applicationId");
        Validate.notNullOrEmpty(userId, "userId");
        if (default_EXPIRATION_TIME == null) {
            default_EXPIRATION_TIME = AccessToken.DEFAULT_EXPIRATION_TIME;
        }
        this.expires = default_EXPIRATION_TIME;
        HashSet<String> set;
        if (collection != null) {
            set = new HashSet<String>(collection);
        }
        else {
            set = new HashSet<String>();
        }
        this.permissions = (Set<String>)Collections.unmodifiableSet((Set<?>)set);
        HashSet<String> set2;
        if (collection2 != null) {
            set2 = new HashSet<String>(collection2);
        }
        else {
            set2 = new HashSet<String>();
        }
        this.declinedPermissions = (Set<String>)Collections.unmodifiableSet((Set<?>)set2);
        this.token = token;
        if (default_ACCESS_TOKEN_SOURCE == null) {
            default_ACCESS_TOKEN_SOURCE = AccessToken.DEFAULT_ACCESS_TOKEN_SOURCE;
        }
        this.source = default_ACCESS_TOKEN_SOURCE;
        if (default_LAST_REFRESH_TIME == null) {
            default_LAST_REFRESH_TIME = AccessToken.DEFAULT_LAST_REFRESH_TIME;
        }
        this.lastRefresh = default_LAST_REFRESH_TIME;
        this.applicationId = applicationId;
        this.userId = userId;
    }
    
    private void appendPermissions(final StringBuilder sb) {
        sb.append(" permissions:");
        if (this.permissions == null) {
            sb.append("null");
            return;
        }
        sb.append("[");
        sb.append(TextUtils.join((CharSequence)", ", (Iterable)this.permissions));
        sb.append("]");
    }
    
    static AccessToken createExpired(final AccessToken accessToken) {
        return new AccessToken(accessToken.token, accessToken.applicationId, accessToken.getUserId(), accessToken.getPermissions(), accessToken.getDeclinedPermissions(), accessToken.source, new Date(), new Date());
    }
    
    private static AccessToken createFromBundle(final List<String> list, final Bundle bundle, final AccessTokenSource accessTokenSource, Date bundleLongAsDate, final String s) {
        final String string = bundle.getString("access_token");
        bundleLongAsDate = Utility.getBundleLongAsDate(bundle, "expires_in", bundleLongAsDate);
        final String string2 = bundle.getString("user_id");
        if (Utility.isNullOrEmpty(string) || bundleLongAsDate == null) {
            return null;
        }
        return new AccessToken(string, s, string2, list, null, accessTokenSource, bundleLongAsDate, new Date());
    }
    
    static AccessToken createFromJSONObject(final JSONObject jsonObject) throws JSONException {
        if (jsonObject.getInt("version") > 1) {
            throw new FacebookException("Unknown AccessToken serialization format.");
        }
        return new AccessToken(jsonObject.getString("token"), jsonObject.getString("application_id"), jsonObject.getString("user_id"), Utility.jsonArrayToStringList(jsonObject.getJSONArray("permissions")), Utility.jsonArrayToStringList(jsonObject.getJSONArray("declined_permissions")), AccessTokenSource.valueOf(jsonObject.getString("source")), new Date(jsonObject.getLong("expires_at")), new Date(jsonObject.getLong("last_refresh")));
    }
    
    static AccessToken createFromLegacyCache(final Bundle bundle) {
        final List<String> permissionsFromBundle = getPermissionsFromBundle(bundle, "com.facebook.TokenCachingStrategy.Permissions");
        final List<String> permissionsFromBundle2 = getPermissionsFromBundle(bundle, "com.facebook.TokenCachingStrategy.DeclinedPermissions");
        String s;
        if (Utility.isNullOrEmpty(s = LegacyTokenHelper.getApplicationId(bundle))) {
            s = FacebookSdk.getApplicationId();
        }
        final String token = LegacyTokenHelper.getToken(bundle);
        final JSONObject awaitGetGraphMeRequestWithCache = Utility.awaitGetGraphMeRequestWithCache(token);
        try {
            return new AccessToken(token, s, awaitGetGraphMeRequestWithCache.getString("id"), permissionsFromBundle, permissionsFromBundle2, LegacyTokenHelper.getSource(bundle), LegacyTokenHelper.getDate(bundle, "com.facebook.TokenCachingStrategy.ExpirationDate"), LegacyTokenHelper.getDate(bundle, "com.facebook.TokenCachingStrategy.LastRefreshDate"));
        }
        catch (JSONException ex) {
            return null;
        }
    }
    
    public static void createFromNativeLinkingIntent(final Intent intent, final String s, final AccessTokenCreationCallback accessTokenCreationCallback) {
        Validate.notNull(intent, "intent");
        if (intent.getExtras() == null) {
            accessTokenCreationCallback.onError(new FacebookException("No extras found on intent"));
            return;
        }
        final Bundle bundle = new Bundle(intent.getExtras());
        final String string = bundle.getString("access_token");
        if (string == null || string.isEmpty()) {
            accessTokenCreationCallback.onError(new FacebookException("No access token found on intent"));
            return;
        }
        final String string2 = bundle.getString("user_id");
        if (string2 == null || string2.isEmpty()) {
            Utility.getGraphMeRequestWithCacheAsync(string, (Utility.GraphMeRequestWithCacheCallback)new Utility.GraphMeRequestWithCacheCallback() {
                @Override
                public void onFailure(final FacebookException ex) {
                    accessTokenCreationCallback.onError(ex);
                }
                
                @Override
                public void onSuccess(final JSONObject jsonObject) {
                    try {
                        bundle.putString("user_id", jsonObject.getString("id"));
                        accessTokenCreationCallback.onSuccess(createFromBundle(null, bundle, AccessTokenSource.FACEBOOK_APPLICATION_WEB, new Date(), s));
                    }
                    catch (JSONException ex) {
                        accessTokenCreationCallback.onError(new FacebookException("Unable to generate access token due to missing user id"));
                    }
                }
            });
            return;
        }
        accessTokenCreationCallback.onSuccess(createFromBundle(null, bundle, AccessTokenSource.FACEBOOK_APPLICATION_WEB, new Date(), s));
    }
    
    @SuppressLint({ "FieldGetter" })
    static AccessToken createFromRefresh(final AccessToken accessToken, final Bundle bundle) {
        if (accessToken.source != AccessTokenSource.FACEBOOK_APPLICATION_WEB && accessToken.source != AccessTokenSource.FACEBOOK_APPLICATION_NATIVE && accessToken.source != AccessTokenSource.FACEBOOK_APPLICATION_SERVICE) {
            throw new FacebookException("Invalid token source: " + accessToken.source);
        }
        final Date bundleLongAsDate = Utility.getBundleLongAsDate(bundle, "expires_in", new Date(0L));
        final String string = bundle.getString("access_token");
        if (Utility.isNullOrEmpty(string)) {
            return null;
        }
        return new AccessToken(string, accessToken.applicationId, accessToken.getUserId(), accessToken.getPermissions(), accessToken.getDeclinedPermissions(), accessToken.source, bundleLongAsDate, new Date());
    }
    
    static void expireCurrentAccessToken() {
        final AccessToken currentAccessToken = AccessTokenManager.getInstance().getCurrentAccessToken();
        if (currentAccessToken != null) {
            setCurrentAccessToken(createExpired(currentAccessToken));
        }
    }
    
    public static AccessToken getCurrentAccessToken() {
        return AccessTokenManager.getInstance().getCurrentAccessToken();
    }
    
    static List<String> getPermissionsFromBundle(final Bundle bundle, final String s) {
        final ArrayList stringArrayList = bundle.getStringArrayList(s);
        if (stringArrayList == null) {
            return Collections.emptyList();
        }
        return (List<String>)Collections.unmodifiableList((List<?>)new ArrayList<Object>(stringArrayList));
    }
    
    public static boolean isCurrentAccessTokenActive() {
        final AccessToken currentAccessToken = AccessTokenManager.getInstance().getCurrentAccessToken();
        return currentAccessToken != null && !currentAccessToken.isExpired();
    }
    
    public static void refreshCurrentAccessTokenAsync() {
        AccessTokenManager.getInstance().refreshCurrentAccessToken(null);
    }
    
    public static void refreshCurrentAccessTokenAsync(final AccessTokenRefreshCallback accessTokenRefreshCallback) {
        AccessTokenManager.getInstance().refreshCurrentAccessToken(accessTokenRefreshCallback);
    }
    
    public static void setCurrentAccessToken(final AccessToken currentAccessToken) {
        AccessTokenManager.getInstance().setCurrentAccessToken(currentAccessToken);
    }
    
    private String tokenToString() {
        if (this.token == null) {
            return "null";
        }
        if (FacebookSdk.isLoggingBehaviorEnabled(LoggingBehavior.INCLUDE_ACCESS_TOKENS)) {
            return this.token;
        }
        return "ACCESS_TOKEN_REMOVED";
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof AccessToken)) {
                return false;
            }
            final AccessToken accessToken = (AccessToken)o;
            if (this.expires.equals(accessToken.expires) && this.permissions.equals(accessToken.permissions) && this.declinedPermissions.equals(accessToken.declinedPermissions) && this.token.equals(accessToken.token) && this.source == accessToken.source && this.lastRefresh.equals(accessToken.lastRefresh)) {
                if (this.applicationId == null) {
                    if (accessToken.applicationId != null) {
                        return false;
                    }
                }
                else if (!this.applicationId.equals(accessToken.applicationId)) {
                    return false;
                }
                if (this.userId.equals(accessToken.userId)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }
    
    public String getApplicationId() {
        return this.applicationId;
    }
    
    public Set<String> getDeclinedPermissions() {
        return this.declinedPermissions;
    }
    
    public Date getExpires() {
        return this.expires;
    }
    
    public Date getLastRefresh() {
        return this.lastRefresh;
    }
    
    public Set<String> getPermissions() {
        return this.permissions;
    }
    
    public AccessTokenSource getSource() {
        return this.source;
    }
    
    public String getToken() {
        return this.token;
    }
    
    public String getUserId() {
        return this.userId;
    }
    
    @Override
    public int hashCode() {
        final int hashCode = this.expires.hashCode();
        final int hashCode2 = this.permissions.hashCode();
        final int hashCode3 = this.declinedPermissions.hashCode();
        final int hashCode4 = this.token.hashCode();
        final int hashCode5 = this.source.hashCode();
        final int hashCode6 = this.lastRefresh.hashCode();
        int hashCode7;
        if (this.applicationId == null) {
            hashCode7 = 0;
        }
        else {
            hashCode7 = this.applicationId.hashCode();
        }
        return (((((((hashCode + 527) * 31 + hashCode2) * 31 + hashCode3) * 31 + hashCode4) * 31 + hashCode5) * 31 + hashCode6) * 31 + hashCode7) * 31 + this.userId.hashCode();
    }
    
    public boolean isExpired() {
        return new Date().after(this.expires);
    }
    
    JSONObject toJSONObject() throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("version", 1);
        jsonObject.put("token", (Object)this.token);
        jsonObject.put("expires_at", this.expires.getTime());
        jsonObject.put("permissions", (Object)new JSONArray((Collection)this.permissions));
        jsonObject.put("declined_permissions", (Object)new JSONArray((Collection)this.declinedPermissions));
        jsonObject.put("last_refresh", this.lastRefresh.getTime());
        jsonObject.put("source", (Object)this.source.name());
        jsonObject.put("application_id", (Object)this.applicationId);
        jsonObject.put("user_id", (Object)this.userId);
        return jsonObject;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("{AccessToken");
        sb.append(" token:").append(this.tokenToString());
        this.appendPermissions(sb);
        sb.append("}");
        return sb.toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeLong(this.expires.getTime());
        parcel.writeStringList((List)new ArrayList(this.permissions));
        parcel.writeStringList((List)new ArrayList(this.declinedPermissions));
        parcel.writeString(this.token);
        parcel.writeString(this.source.name());
        parcel.writeLong(this.lastRefresh.getTime());
        parcel.writeString(this.applicationId);
        parcel.writeString(this.userId);
    }
    
    public interface AccessTokenCreationCallback
    {
        void onError(final FacebookException p0);
        
        void onSuccess(final AccessToken p0);
    }
    
    public interface AccessTokenRefreshCallback
    {
        void OnTokenRefreshFailed(final FacebookException p0);
        
        void OnTokenRefreshed(final AccessToken p0);
    }
}
