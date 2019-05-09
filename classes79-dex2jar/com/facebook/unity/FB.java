// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.unity;

import android.annotation.TargetApi;
import android.content.pm.Signature;
import java.security.NoSuchAlgorithmException;
import android.content.pm.PackageManager$NameNotFoundException;
import android.util.Base64;
import java.security.MessageDigest;
import org.json.JSONException;
import com.facebook.internal.BundleJSONConverter;
import com.facebook.internal.InternalSettings;
import com.facebook.FacebookException;
import com.facebook.AccessToken$AccessTokenRefreshCallback;
import com.facebook.login.LoginManager;
import java.util.Locale;
import java.util.Currency;
import java.math.BigDecimal;
import com.facebook.appevents.internal.AutomaticAnalyticsLogger;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk$InitializeCallback;
import com.facebook.internal.Utility;
import com.facebook.FacebookSdk;
import com.facebook.applinks.AppLinkData;
import com.facebook.applinks.AppLinkData$CompletionHandler;
import android.os.Bundle;
import java.io.Serializable;
import android.content.Context;
import com.facebook.appevents.internal.ActivityLifecycleTracker;
import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.content.Intent;
import com.facebook.appevents.AppEventsLogger;
import java.util.concurrent.atomic.AtomicBoolean;
import com.facebook.share.widget.ShareDialog$Mode;

public class FB
{
    static final String FB_UNITY_OBJECT = "UnityFacebookSDKPlugin";
    static ShareDialog$Mode ShareDialogMode;
    static final String TAG;
    private static AtomicBoolean activateAppCalled;
    private static AppEventsLogger appEventsLogger;
    private static Intent intent;
    
    static {
        TAG = FB.class.getName();
        FB.activateAppCalled = new AtomicBoolean();
        FB.ShareDialogMode = ShareDialog$Mode.AUTOMATIC;
    }
    
    private static void ActivateApp(final String s) {
        if (!FB.activateAppCalled.compareAndSet(false, true)) {
            Log.w(FB.TAG, "Activite app only needs to be called once");
            return;
        }
        final Activity unityActivity = getUnityActivity();
        if (s != null) {
            AppEventsLogger.activateApp(unityActivity.getApplication(), s);
        }
        else {
            AppEventsLogger.activateApp(unityActivity.getApplication());
        }
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable() {
            @Override
            public void run() {
                ActivityLifecycleTracker.onActivityCreated(unityActivity);
                ActivityLifecycleTracker.onActivityResumed(unityActivity);
            }
        });
    }
    
    @UnityCallable
    public static void AppInvite(final String s) {
        Log.v(FB.TAG, "AppInvite(" + s + ")");
        final Intent intent = new Intent((Context)getUnityActivity(), (Class)AppInviteDialogActivity.class);
        intent.putExtra("dialog_params", UnityParams.parse(s).getStringParams());
        getUnityActivity().startActivity(intent);
    }
    
    @UnityCallable
    public static void AppRequest(final String s) {
        Log.v(FB.TAG, "AppRequest(" + s + ")");
        final Intent intent = new Intent((Context)getUnityActivity(), (Class)FBUnityGameRequestActivity.class);
        intent.putExtra("game_request_params", UnityParams.parse(s).getStringParams());
        getUnityActivity().startActivity(intent);
    }
    
    @UnityCallable
    public static void FeedShare(final String s) {
        Log.v(FB.TAG, "FeedShare(" + s + ")");
        final Bundle stringParams = UnityParams.parse(s).getStringParams();
        final Intent intent = new Intent((Context)getUnityActivity(), (Class)FBUnityDialogsActivity.class);
        intent.putExtra("dialog_type", (Serializable)ShareDialog$Mode.FEED);
        intent.putExtra("feed_dialog_params", stringParams);
        getUnityActivity().startActivity(intent);
    }
    
    @UnityCallable
    public static void FetchDeferredAppLinkData(final String s) {
        LogMethodCall("FetchDeferredAppLinkData", s);
        final UnityParams parse = UnityParams.parse(s);
        final UnityMessage unityMessage = new UnityMessage("OnFetchDeferredAppLinkComplete");
        if (parse.hasString("callback_id")) {
            unityMessage.put("callback_id", parse.getString("callback_id"));
        }
        AppLinkData.fetchDeferredAppLinkData((Context)getUnityActivity(), (AppLinkData$CompletionHandler)new AppLinkData$CompletionHandler() {
            public void onDeferredAppLinkDataFetched(final AppLinkData appLinkData) {
                addAppLinkToMessage(unityMessage, appLinkData);
                unityMessage.send();
            }
        });
    }
    
    @UnityCallable
    public static void GameGroupCreate(final String s) {
        Log.v(FB.TAG, "GameGroupCreate(" + s + ")");
        final Bundle stringParams = UnityParams.parse(s).getStringParams();
        final Intent intent = new Intent((Context)getUnityActivity(), (Class)FBUnityCreateGameGroupActivity.class);
        intent.putExtra(FBUnityCreateGameGroupActivity.CREATE_GAME_GROUP_PARAMS, stringParams);
        getUnityActivity().startActivity(intent);
    }
    
    @UnityCallable
    public static void GameGroupJoin(final String s) {
        Log.v(FB.TAG, "GameGroupJoin(" + s + ")");
        final Bundle stringParams = UnityParams.parse(s).getStringParams();
        final Intent intent = new Intent((Context)getUnityActivity(), (Class)FBUnityJoinGameGroupActivity.class);
        intent.putExtra(FBUnityJoinGameGroupActivity.JOIN_GAME_GROUP_PARAMS, stringParams);
        getUnityActivity().startActivity(intent);
    }
    
    @UnityCallable
    public static void GetAppLink(final String s) {
        Log.v(FB.TAG, "GetAppLink(" + s + ")");
        final UnityMessage withCallbackFromParams = UnityMessage.createWithCallbackFromParams("OnGetAppLinkComplete", UnityParams.parse(s));
        if (FB.intent == null) {
            withCallbackFromParams.put("did_complete", true);
            withCallbackFromParams.send();
            return;
        }
        final AppLinkData fromAlApplinkData = AppLinkData.createFromAlApplinkData(FB.intent);
        if (fromAlApplinkData != null) {
            addAppLinkToMessage(withCallbackFromParams, fromAlApplinkData);
            withCallbackFromParams.put("url", FB.intent.getDataString());
        }
        else if (FB.intent.getData() != null) {
            withCallbackFromParams.put("url", FB.intent.getDataString());
        }
        else {
            withCallbackFromParams.put("did_complete", true);
        }
        withCallbackFromParams.send();
    }
    
    @UnityCallable
    public static String GetSdkVersion() {
        return FacebookSdk.getSdkVersion();
    }
    
    @UnityCallable
    public static void Init(String applicationId) {
        Log.v(FB.TAG, "Init(" + applicationId + ")");
        final UnityParams parse = UnityParams.parse(applicationId, "couldn't parse init params: " + applicationId);
        if (parse.hasString("appId")) {
            applicationId = parse.getString("appId");
        }
        else {
            applicationId = Utility.getMetadataApplicationId((Context)getUnityActivity());
        }
        FacebookSdk.setApplicationId(applicationId);
        FacebookSdk.sdkInitialize((Context)getUnityActivity(), (FacebookSdk$InitializeCallback)new FacebookSdk$InitializeCallback() {
            public void onInitialized() {
                final UnityMessage unityMessage = new UnityMessage("OnInitComplete");
                final AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();
                if (currentAccessToken != null) {
                    FBLogin.addLoginParametersToMessage(unityMessage, currentAccessToken, null);
                }
                else {
                    unityMessage.put("key_hash", FB.getKeyHash());
                }
                ActivateApp(applicationId);
                unityMessage.send();
            }
        });
    }
    
    @UnityCallable
    public static boolean IsImplicitPurchaseLoggingEnabled() {
        return AutomaticAnalyticsLogger.isImplicitPurchaseLoggingEnabled();
    }
    
    @UnityCallable
    public static void LogAppEvent(final String s) {
        Log.v(FB.TAG, "LogAppEvent(" + s + ")");
        final UnityParams parse = UnityParams.parse(s);
        Bundle stringParams = new Bundle();
        if (parse.has("parameters")) {
            stringParams = parse.getParamsObject("parameters").getStringParams();
        }
        if (parse.has("logPurchase")) {
            getAppEventsLogger().logPurchase(new BigDecimal(parse.getDouble("logPurchase")), Currency.getInstance(parse.getString("currency")), stringParams);
            return;
        }
        if (!parse.hasString("logEvent")) {
            Log.e(FB.TAG, "couldn't logPurchase or logEvent params: " + s);
            return;
        }
        if (parse.has("valueToSum")) {
            getAppEventsLogger().logEvent(parse.getString("logEvent"), parse.getDouble("valueToSum"), stringParams);
            return;
        }
        getAppEventsLogger().logEvent(parse.getString("logEvent"), stringParams);
    }
    
    private static void LogMethodCall(final String s, final String s2) {
        Log.v(FB.TAG, String.format(Locale.ROOT, "%s(%s)", s, s2));
    }
    
    @UnityCallable
    public static void LoginForTVWithPublishPermissions(final String s) {
        Log.v(FB.TAG, "LoginForTVWithPublishPermissions(" + s + ")");
        final Intent intent = new Intent((Context)getUnityActivity(), (Class)FBUnityLoginActivity.class);
        intent.putExtra("login_params", s);
        intent.putExtra("login_type", (Serializable)FBUnityLoginActivity.LoginType.TV_PUBLISH);
        getUnityActivity().startActivity(intent);
    }
    
    @UnityCallable
    public static void LoginWithPublishPermissions(final String s) {
        Log.v(FB.TAG, "LoginWithPublishPermissions(" + s + ")");
        final Intent intent = new Intent((Context)getUnityActivity(), (Class)FBUnityLoginActivity.class);
        intent.putExtra("login_params", s);
        intent.putExtra("login_type", (Serializable)FBUnityLoginActivity.LoginType.PUBLISH);
        getUnityActivity().startActivity(intent);
    }
    
    @UnityCallable
    public static void LoginWithReadPermissions(final String s) {
        Log.v(FB.TAG, "LoginWithReadPermissions(" + s + ")");
        final Intent intent = new Intent((Context)getUnityActivity(), (Class)FBUnityLoginActivity.class);
        intent.putExtra("login_params", s);
        intent.putExtra("login_type", (Serializable)FBUnityLoginActivity.LoginType.READ);
        getUnityActivity().startActivity(intent);
    }
    
    @UnityCallable
    public static void Logout(final String s) {
        Log.v(FB.TAG, "Logout(" + s + ")");
        LoginManager.getInstance().logOut();
        final UnityMessage unityMessage = new UnityMessage("OnLogoutComplete");
        unityMessage.put("did_complete", true);
        unityMessage.send();
    }
    
    @UnityCallable
    public static void RefreshCurrentAccessToken(final String s) {
        LogMethodCall("RefreshCurrentAccessToken", s);
        final UnityParams parse = UnityParams.parse(s);
        final UnityMessage unityMessage = new UnityMessage("OnRefreshCurrentAccessTokenComplete");
        if (parse.hasString("callback_id")) {
            unityMessage.put("callback_id", parse.getString("callback_id"));
        }
        AccessToken.refreshCurrentAccessTokenAsync((AccessToken$AccessTokenRefreshCallback)new AccessToken$AccessTokenRefreshCallback() {
            public void OnTokenRefreshFailed(final FacebookException ex) {
                unityMessage.sendError(ex.getMessage());
            }
            
            public void OnTokenRefreshed(final AccessToken accessToken) {
                FBLogin.addLoginParametersToMessage(unityMessage, accessToken, null);
                unityMessage.send();
            }
        });
    }
    
    public static void SetIntent(final Intent intent) {
        FB.intent = intent;
    }
    
    public static void SetLimitEventUsage(final String s) {
        Log.v(FB.TAG, "SetLimitEventUsage(" + s + ")");
        FacebookSdk.setLimitEventAndDataUsage(getUnityActivity().getApplicationContext(), (boolean)Boolean.valueOf(s));
    }
    
    @UnityCallable
    public static void SetShareDialogMode(final String s) {
        Log.v(FB.TAG, "SetShareDialogMode(" + s + ")");
        if (s.equalsIgnoreCase("NATIVE")) {
            FB.ShareDialogMode = ShareDialog$Mode.NATIVE;
            return;
        }
        if (s.equalsIgnoreCase("WEB")) {
            FB.ShareDialogMode = ShareDialog$Mode.WEB;
            return;
        }
        if (s.equalsIgnoreCase("FEED")) {
            FB.ShareDialogMode = ShareDialog$Mode.FEED;
            return;
        }
        FB.ShareDialogMode = ShareDialog$Mode.AUTOMATIC;
    }
    
    @UnityCallable
    public static void SetUserAgentSuffix(final String customUserAgent) {
        Log.v(FB.TAG, "SetUserAgentSuffix(" + customUserAgent + ")");
        InternalSettings.setCustomUserAgent(customUserAgent);
    }
    
    @UnityCallable
    public static void ShareLink(final String s) {
        Log.v(FB.TAG, "ShareLink(" + s + ")");
        final Bundle stringParams = UnityParams.parse(s).getStringParams();
        final Intent intent = new Intent((Context)getUnityActivity(), (Class)FBUnityDialogsActivity.class);
        intent.putExtra("dialog_type", (Serializable)FB.ShareDialogMode);
        intent.putExtra("share_dialog_params", stringParams);
        getUnityActivity().startActivity(intent);
    }
    
    private static void addAppLinkToMessage(final UnityMessage unityMessage, final AppLinkData appLinkData) {
        if (appLinkData == null) {
            unityMessage.put("did_complete", true);
        }
        else {
            unityMessage.put("ref", appLinkData.getRef());
            unityMessage.put("target_url", appLinkData.getTargetUri().toString());
            try {
                if (appLinkData.getArgumentBundle() != null) {
                    unityMessage.put("extras", BundleJSONConverter.convertToJSON(appLinkData.getArgumentBundle()).toString());
                }
            }
            catch (JSONException ex) {
                Log.e(FB.TAG, ex.getLocalizedMessage());
            }
        }
    }
    
    private static AppEventsLogger getAppEventsLogger() {
        if (FB.appEventsLogger == null) {
            FB.appEventsLogger = AppEventsLogger.newLogger(getUnityActivity().getApplicationContext());
        }
        return FB.appEventsLogger;
    }
    
    @TargetApi(8)
    public static String getKeyHash() {
        try {
            final Activity unityActivity = getUnityActivity();
            if (unityActivity == null) {
                return "";
            }
            final Signature[] signatures = unityActivity.getPackageManager().getPackageInfo(unityActivity.getPackageName(), 64).signatures;
            if (signatures.length < 0) {
                final Signature signature = signatures[0];
                final MessageDigest instance = MessageDigest.getInstance("SHA");
                instance.update(signature.toByteArray());
                final String encodeToString = Base64.encodeToString(instance.digest(), 0);
                Log.d(FB.TAG, "KeyHash: " + encodeToString);
                return encodeToString;
            }
            goto Label_0092;
        }
        catch (PackageManager$NameNotFoundException ex) {}
        catch (NoSuchAlgorithmException ex2) {
            goto Label_0092;
        }
    }
    
    public static Activity getUnityActivity() {
        return UnityReflection.GetUnityActivity();
    }
    
    @UnityCallable
    public static void loginForTVWithReadPermissions(final String s) {
        Log.v(FB.TAG, "loginForTVWithReadPermissions(" + s + ")");
        final Intent intent = new Intent((Context)getUnityActivity(), (Class)FBUnityLoginActivity.class);
        intent.putExtra("login_params", s);
        intent.putExtra("login_type", (Serializable)FBUnityLoginActivity.LoginType.TV_READ);
        getUnityActivity().startActivity(intent);
    }
    
    private static void startActivity(final Class<?> clazz, final String s) {
        final Intent intent = new Intent((Context)getUnityActivity(), (Class)clazz);
        intent.putExtra("activity_params", UnityParams.parse(s).getStringParams());
        getUnityActivity().startActivity(intent);
    }
}
