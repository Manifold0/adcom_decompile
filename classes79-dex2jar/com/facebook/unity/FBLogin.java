// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.unity;

import android.app.Activity;
import com.facebook.login.DeviceLoginManager;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.FacebookCallback;
import com.facebook.login.LoginManager;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import android.util.Log;
import com.facebook.FacebookSdk;
import android.text.TextUtils;
import java.io.Serializable;
import com.facebook.AccessToken;

public class FBLogin
{
    public static void addLoginParametersToMessage(final UnityMessage unityMessage, final AccessToken accessToken, final String s) {
        unityMessage.put("key_hash", FB.getKeyHash());
        unityMessage.put("opened", true);
        unityMessage.put("access_token", accessToken.getToken());
        unityMessage.put("expiration_timestamp", Long.valueOf(accessToken.getExpires().getTime() / 1000L).toString());
        unityMessage.put("user_id", accessToken.getUserId());
        unityMessage.put("permissions", TextUtils.join((CharSequence)",", (Iterable)accessToken.getPermissions()));
        unityMessage.put("declined_permissions", TextUtils.join((CharSequence)",", (Iterable)accessToken.getDeclinedPermissions()));
        if (accessToken.getLastRefresh() != null) {
            unityMessage.put("last_refresh", Long.valueOf(accessToken.getLastRefresh().getTime() / 1000L).toString());
        }
        if (s != null && !s.isEmpty()) {
            unityMessage.put("callback_id", s);
        }
    }
    
    private static void login(final String s, final FBUnityLoginActivity fbUnityLoginActivity, final boolean b, final boolean b2) {
        if (!FacebookSdk.isInitialized()) {
            Log.w(FB.TAG, "Facebook SDK not initialized. Call init() before calling login()");
            return;
        }
        final UnityMessage unityMessage = new UnityMessage("OnLoginComplete");
        unityMessage.put("key_hash", FB.getKeyHash());
        final UnityParams parse = UnityParams.parse(s, "couldn't parse login params: " + s);
        Collection collection = null;
        if (parse.hasString("scope")) {
            collection = new ArrayList(Arrays.asList(parse.getString("scope").split(",")));
        }
        String string = null;
        if (parse.has("callback_id")) {
            string = parse.getString("callback_id");
            unityMessage.put("callback_id", string);
        }
        LoginManager.getInstance().registerCallback(fbUnityLoginActivity.getCallbackManager(), (FacebookCallback)new FacebookCallback<LoginResult>() {
            public void onCancel() {
                unityMessage.putCancelled();
                unityMessage.send();
            }
            
            public void onError(final FacebookException ex) {
                unityMessage.sendError(ex.getMessage());
            }
            
            public void onSuccess(final LoginResult loginResult) {
                FBLogin.sendLoginSuccessMessage(loginResult.getAccessToken(), string);
            }
        });
        Object o;
        if (b2) {
            o = DeviceLoginManager.getInstance();
        }
        else {
            o = LoginManager.getInstance();
        }
        if (b) {
            ((LoginManager)o).logInWithPublishPermissions((Activity)fbUnityLoginActivity, collection);
            return;
        }
        ((LoginManager)o).logInWithReadPermissions((Activity)fbUnityLoginActivity, collection);
    }
    
    public static void loginForTVWithPublishPermissions(final String s, final FBUnityLoginActivity fbUnityLoginActivity) {
        login(s, fbUnityLoginActivity, true, true);
    }
    
    public static void loginForTVWithReadPermissions(final String s, final FBUnityLoginActivity fbUnityLoginActivity) {
        login(s, fbUnityLoginActivity, false, true);
    }
    
    public static void loginWithPublishPermissions(final String s, final FBUnityLoginActivity fbUnityLoginActivity) {
        login(s, fbUnityLoginActivity, true, false);
    }
    
    public static void loginWithReadPermissions(final String s, final FBUnityLoginActivity fbUnityLoginActivity) {
        login(s, fbUnityLoginActivity, false, false);
    }
    
    public static void sendLoginSuccessMessage(final AccessToken accessToken, final String s) {
        final UnityMessage unityMessage = new UnityMessage("OnLoginComplete");
        addLoginParametersToMessage(unityMessage, accessToken, s);
        unityMessage.send();
    }
}
