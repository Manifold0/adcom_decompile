package com.facebook;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import com.facebook.AccessToken.AccessTokenRefreshCallback;
import com.facebook.GraphRequest.Callback;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import com.ironsource.sdk.constants.Constants.ParametersKeys;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONObject;

public final class AccessTokenManager {
    public static final String ACTION_CURRENT_ACCESS_TOKEN_CHANGED = "com.facebook.sdk.ACTION_CURRENT_ACCESS_TOKEN_CHANGED";
    public static final String EXTRA_NEW_ACCESS_TOKEN = "com.facebook.sdk.EXTRA_NEW_ACCESS_TOKEN";
    public static final String EXTRA_OLD_ACCESS_TOKEN = "com.facebook.sdk.EXTRA_OLD_ACCESS_TOKEN";
    private static final String ME_PERMISSIONS_GRAPH_PATH = "me/permissions";
    public static final String SHARED_PREFERENCES_NAME = "com.facebook.AccessTokenManager.SharedPreferences";
    public static final String TAG = "AccessTokenManager";
    private static final String TOKEN_EXTEND_GRAPH_PATH = "oauth/access_token";
    private static final int TOKEN_EXTEND_RETRY_SECONDS = 3600;
    private static final int TOKEN_EXTEND_THRESHOLD_SECONDS = 86400;
    private static volatile AccessTokenManager instance;
    private final AccessTokenCache accessTokenCache;
    private AccessToken currentAccessToken;
    private Date lastAttemptedTokenExtendDate = new Date(0);
    private final LocalBroadcastManager localBroadcastManager;
    private AtomicBoolean tokenRefreshInProgress = new AtomicBoolean(false);

    private static class RefreshResult {
        public String accessToken;
        public int expiresAt;

        private RefreshResult() {
        }
    }

    AccessTokenManager(LocalBroadcastManager localBroadcastManager, AccessTokenCache accessTokenCache) {
        Validate.notNull(localBroadcastManager, "localBroadcastManager");
        Validate.notNull(accessTokenCache, "accessTokenCache");
        this.localBroadcastManager = localBroadcastManager;
        this.accessTokenCache = accessTokenCache;
    }

    static AccessTokenManager getInstance() {
        if (instance == null) {
            synchronized (AccessTokenManager.class) {
                if (instance == null) {
                    instance = new AccessTokenManager(LocalBroadcastManager.getInstance(FacebookSdk.getApplicationContext()), new AccessTokenCache());
                }
            }
        }
        return instance;
    }

    AccessToken getCurrentAccessToken() {
        return this.currentAccessToken;
    }

    boolean loadCurrentAccessToken() {
        AccessToken accessToken = this.accessTokenCache.load();
        if (accessToken == null) {
            return false;
        }
        setCurrentAccessToken(accessToken, false);
        return true;
    }

    void setCurrentAccessToken(AccessToken currentAccessToken) {
        setCurrentAccessToken(currentAccessToken, true);
    }

    private void setCurrentAccessToken(AccessToken currentAccessToken, boolean saveToCache) {
        AccessToken oldAccessToken = this.currentAccessToken;
        this.currentAccessToken = currentAccessToken;
        this.tokenRefreshInProgress.set(false);
        this.lastAttemptedTokenExtendDate = new Date(0);
        if (saveToCache) {
            if (currentAccessToken != null) {
                this.accessTokenCache.save(currentAccessToken);
            } else {
                this.accessTokenCache.clear();
                Utility.clearFacebookCookies(FacebookSdk.getApplicationContext());
            }
        }
        if (!Utility.areObjectsEqual(oldAccessToken, currentAccessToken)) {
            sendCurrentAccessTokenChangedBroadcastIntent(oldAccessToken, currentAccessToken);
            setTokenExpirationBroadcastAlarm();
        }
    }

    void currentAccessTokenChanged() {
        sendCurrentAccessTokenChangedBroadcastIntent(this.currentAccessToken, this.currentAccessToken);
    }

    private void sendCurrentAccessTokenChangedBroadcastIntent(AccessToken oldAccessToken, AccessToken currentAccessToken) {
        Intent intent = new Intent(FacebookSdk.getApplicationContext(), CurrentAccessTokenExpirationBroadcastReceiver.class);
        intent.setAction(ACTION_CURRENT_ACCESS_TOKEN_CHANGED);
        intent.putExtra(EXTRA_OLD_ACCESS_TOKEN, oldAccessToken);
        intent.putExtra(EXTRA_NEW_ACCESS_TOKEN, currentAccessToken);
        this.localBroadcastManager.sendBroadcast(intent);
    }

    private void setTokenExpirationBroadcastAlarm() {
        Context context = FacebookSdk.getApplicationContext();
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        if (AccessToken.isCurrentAccessTokenActive() && accessToken.getExpires() != null && alarmManager != null) {
            Intent intent = new Intent(context, CurrentAccessTokenExpirationBroadcastReceiver.class);
            intent.setAction(ACTION_CURRENT_ACCESS_TOKEN_CHANGED);
            alarmManager.set(1, accessToken.getExpires().getTime(), PendingIntent.getBroadcast(context, 0, intent, 0));
        }
    }

    void extendAccessTokenIfNeeded() {
        if (shouldExtendAccessToken()) {
            refreshCurrentAccessToken(null);
        }
    }

    private boolean shouldExtendAccessToken() {
        if (this.currentAccessToken == null) {
            return false;
        }
        Long now = Long.valueOf(new Date().getTime());
        if (!this.currentAccessToken.getSource().canExtendToken() || now.longValue() - this.lastAttemptedTokenExtendDate.getTime() <= 3600000 || now.longValue() - this.currentAccessToken.getLastRefresh().getTime() <= 86400000) {
            return false;
        }
        return true;
    }

    private static GraphRequest createGrantedPermissionsRequest(AccessToken accessToken, Callback callback) {
        return new GraphRequest(accessToken, ME_PERMISSIONS_GRAPH_PATH, new Bundle(), HttpMethod.GET, callback);
    }

    private static GraphRequest createExtendAccessTokenRequest(AccessToken accessToken, Callback callback) {
        Bundle parameters = new Bundle();
        parameters.putString("grant_type", "fb_extend_sso_token");
        return new GraphRequest(accessToken, TOKEN_EXTEND_GRAPH_PATH, parameters, HttpMethod.GET, callback);
    }

    void refreshCurrentAccessToken(final AccessTokenRefreshCallback callback) {
        if (Looper.getMainLooper().equals(Looper.myLooper())) {
            refreshCurrentAccessTokenImpl(callback);
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    AccessTokenManager.this.refreshCurrentAccessTokenImpl(callback);
                }
            });
        }
    }

    private void refreshCurrentAccessTokenImpl(AccessTokenRefreshCallback callback) {
        final AccessToken accessToken = this.currentAccessToken;
        if (accessToken == null) {
            if (callback != null) {
                callback.OnTokenRefreshFailed(new FacebookException("No current access token to refresh"));
            }
        } else if (this.tokenRefreshInProgress.compareAndSet(false, true)) {
            this.lastAttemptedTokenExtendDate = new Date();
            final Set<String> permissions = new HashSet();
            final Set<String> declinedPermissions = new HashSet();
            final AtomicBoolean permissionsCallSucceeded = new AtomicBoolean(false);
            final RefreshResult refreshResult = new RefreshResult();
            GraphRequestBatch batch = new GraphRequestBatch(createGrantedPermissionsRequest(accessToken, new Callback() {
                public void onCompleted(GraphResponse response) {
                    JSONObject result = response.getJSONObject();
                    if (result != null) {
                        JSONArray permissionsArray = result.optJSONArray("data");
                        if (permissionsArray != null) {
                            permissionsCallSucceeded.set(true);
                            for (int i = 0; i < permissionsArray.length(); i++) {
                                JSONObject permissionEntry = permissionsArray.optJSONObject(i);
                                if (permissionEntry != null) {
                                    String permission = permissionEntry.optString(ParametersKeys.PERMISSION);
                                    String status = permissionEntry.optString("status");
                                    if (!(Utility.isNullOrEmpty(permission) || Utility.isNullOrEmpty(status))) {
                                        status = status.toLowerCase(Locale.US);
                                        if (status.equals("granted")) {
                                            permissions.add(permission);
                                        } else if (status.equals("declined")) {
                                            declinedPermissions.add(permission);
                                        } else {
                                            Log.w(AccessTokenManager.TAG, "Unexpected status: " + status);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }), createExtendAccessTokenRequest(accessToken, new Callback() {
                public void onCompleted(GraphResponse response) {
                    JSONObject data = response.getJSONObject();
                    if (data != null) {
                        refreshResult.accessToken = data.optString("access_token");
                        refreshResult.expiresAt = data.optInt("expires_at");
                    }
                }
            }));
            final AccessTokenRefreshCallback accessTokenRefreshCallback = callback;
            batch.addCallback(new GraphRequestBatch.Callback() {
                public void onBatchCompleted(GraphRequestBatch batch) {
                    Throwable th;
                    AccessToken newAccessToken;
                    try {
                        if (AccessTokenManager.getInstance().getCurrentAccessToken() == null || AccessTokenManager.getInstance().getCurrentAccessToken().getUserId() != accessToken.getUserId()) {
                            if (accessTokenRefreshCallback != null) {
                                accessTokenRefreshCallback.OnTokenRefreshFailed(new FacebookException("No current access token to refresh"));
                            }
                            AccessTokenManager.this.tokenRefreshInProgress.set(false);
                            if (!(accessTokenRefreshCallback == null || null == null)) {
                                accessTokenRefreshCallback.OnTokenRefreshed(null);
                            }
                            newAccessToken = null;
                        } else if (!permissionsCallSucceeded.get() && refreshResult.accessToken == null && refreshResult.expiresAt == 0) {
                            if (accessTokenRefreshCallback != null) {
                                accessTokenRefreshCallback.OnTokenRefreshFailed(new FacebookException("Failed to refresh access token"));
                            }
                            AccessTokenManager.this.tokenRefreshInProgress.set(false);
                            if (!(accessTokenRefreshCallback == null || null == null)) {
                                accessTokenRefreshCallback.OnTokenRefreshed(null);
                            }
                            newAccessToken = null;
                        } else {
                            String str;
                            Collection collection;
                            Collection collection2;
                            Date date;
                            if (refreshResult.accessToken != null) {
                                str = refreshResult.accessToken;
                            } else {
                                str = accessToken.getToken();
                            }
                            String applicationId = accessToken.getApplicationId();
                            String userId = accessToken.getUserId();
                            if (permissionsCallSucceeded.get()) {
                                collection = permissions;
                            } else {
                                collection = accessToken.getPermissions();
                            }
                            if (permissionsCallSucceeded.get()) {
                                collection2 = declinedPermissions;
                            } else {
                                collection2 = accessToken.getDeclinedPermissions();
                            }
                            AccessTokenSource source = accessToken.getSource();
                            if (refreshResult.expiresAt != 0) {
                                date = new Date(((long) refreshResult.expiresAt) * 1000);
                            } else {
                                date = accessToken.getExpires();
                            }
                            newAccessToken = new AccessToken(str, applicationId, userId, collection, collection2, source, date, new Date());
                            try {
                                AccessTokenManager.getInstance().setCurrentAccessToken(newAccessToken);
                                AccessTokenManager.this.tokenRefreshInProgress.set(false);
                                if (accessTokenRefreshCallback != null && newAccessToken != null) {
                                    accessTokenRefreshCallback.OnTokenRefreshed(newAccessToken);
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                AccessTokenManager.this.tokenRefreshInProgress.set(false);
                                accessTokenRefreshCallback.OnTokenRefreshed(newAccessToken);
                                throw th;
                            }
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        newAccessToken = null;
                        AccessTokenManager.this.tokenRefreshInProgress.set(false);
                        if (!(accessTokenRefreshCallback == null || newAccessToken == null)) {
                            accessTokenRefreshCallback.OnTokenRefreshed(newAccessToken);
                        }
                        throw th;
                    }
                }
            });
            batch.executeAsync();
        } else if (callback != null) {
            callback.OnTokenRefreshFailed(new FacebookException("Refresh already in progress"));
        }
    }
}
