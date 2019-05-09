// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.login;

import com.facebook.CallbackManager;
import com.facebook.FacebookAuthorizationException;
import android.app.Fragment;
import android.app.Activity;
import android.os.Parcelable;
import com.facebook.FacebookActivity;
import java.util.Iterator;
import android.content.ActivityNotFoundException;
import com.facebook.internal.CallbackManagerImpl;
import com.facebook.internal.CallbackManagerImpl$Callback;
import com.facebook.internal.CallbackManagerImpl$RequestCodeOffset;
import android.content.SharedPreferences$Editor;
import java.util.ArrayList;
import com.facebook.AccessTokenSource;
import java.util.Date;
import com.facebook.internal.Utility;
import com.facebook.internal.PlatformServiceClient;
import java.util.UUID;
import android.content.Intent;
import com.facebook.internal.FragmentWrapper;
import java.util.HashMap;
import java.util.Map;
import android.content.Context;
import android.support.annotation.Nullable;
import android.net.Uri;
import java.util.Collections;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphResponse;
import java.util.Collection;
import java.util.HashSet;
import com.facebook.AccessToken;
import com.facebook.Profile;
import android.os.Bundle;
import com.facebook.LoginStatusCallback;
import com.facebook.FacebookSdk;
import com.facebook.internal.Validate;
import android.content.SharedPreferences;
import java.util.Set;

public class LoginManager
{
    private static final String EXPRESS_LOGIN_ALLOWED = "express_login_allowed";
    private static final String MANAGE_PERMISSION_PREFIX = "manage";
    private static final Set<String> OTHER_PUBLISH_PERMISSIONS;
    private static final String PREFERENCE_LOGIN_MANAGER = "com.facebook.loginManager";
    private static final String PUBLISH_PERMISSION_PREFIX = "publish";
    private static volatile LoginManager instance;
    private DefaultAudience defaultAudience;
    private LoginBehavior loginBehavior;
    private final SharedPreferences sharedPreferences;
    
    static {
        OTHER_PUBLISH_PERMISSIONS = getOtherPublishPermissions();
    }
    
    LoginManager() {
        this.loginBehavior = LoginBehavior.NATIVE_WITH_FALLBACK;
        this.defaultAudience = DefaultAudience.FRIENDS;
        Validate.sdkInitialized();
        this.sharedPreferences = FacebookSdk.getApplicationContext().getSharedPreferences("com.facebook.loginManager", 0);
    }
    
    static LoginResult computeLoginResult(final LoginClient.Request request, final AccessToken accessToken) {
        final Set<String> permissions = request.getPermissions();
        final HashSet set = new HashSet<String>(accessToken.getPermissions());
        if (request.isRerequest()) {
            set.retainAll(permissions);
        }
        final HashSet set2 = new HashSet<String>(permissions);
        set2.removeAll(set);
        return new LoginResult(accessToken, set, (Set<String>)set2);
    }
    
    private LoginClient.Request createLoginRequestFromResponse(final GraphResponse graphResponse) {
        Validate.notNull((Object)graphResponse, "response");
        final AccessToken accessToken = graphResponse.getRequest().getAccessToken();
        Set<String> permissions;
        if (accessToken != null) {
            permissions = (Set<String>)accessToken.getPermissions();
        }
        else {
            permissions = null;
        }
        return this.createLoginRequest(permissions);
    }
    
    private void finishLogin(final AccessToken currentAccessToken, final LoginClient.Request request, final FacebookException ex, final boolean b, final FacebookCallback<LoginResult> facebookCallback) {
        if (currentAccessToken != null) {
            AccessToken.setCurrentAccessToken(currentAccessToken);
            Profile.fetchProfileForCurrentAccessToken();
        }
        if (facebookCallback != null) {
            LoginResult computeLoginResult;
            if (currentAccessToken != null) {
                computeLoginResult = computeLoginResult(request, currentAccessToken);
            }
            else {
                computeLoginResult = null;
            }
            if (b || (computeLoginResult != null && computeLoginResult.getRecentlyGrantedPermissions().size() == 0)) {
                facebookCallback.onCancel();
            }
            else {
                if (ex != null) {
                    facebookCallback.onError(ex);
                    return;
                }
                if (currentAccessToken != null) {
                    this.setExpressLoginStatus(true);
                    facebookCallback.onSuccess(computeLoginResult);
                }
            }
        }
    }
    
    public static LoginManager getInstance() {
        Label_0028: {
            if (LoginManager.instance != null) {
                break Label_0028;
            }
            synchronized (LoginManager.class) {
                if (LoginManager.instance == null) {
                    LoginManager.instance = new LoginManager();
                }
                return LoginManager.instance;
            }
        }
    }
    
    private static Set<String> getOtherPublishPermissions() {
        return Collections.unmodifiableSet((Set<? extends String>)new HashSet<String>() {
            {
                this.add("ads_management");
                this.add("create_event");
                this.add("rsvp_event");
            }
        });
    }
    
    @Nullable
    private static Profile getProfileFromBundle(final Bundle bundle) {
        final String string = bundle.getString("com.facebook.platform.extra.PROFILE_NAME");
        final String string2 = bundle.getString("com.facebook.platform.extra.PROFILE_FIRST_NAME");
        final String string3 = bundle.getString("com.facebook.platform.extra.PROFILE_MIDDLE_NAME");
        final String string4 = bundle.getString("com.facebook.platform.extra.PROFILE_LAST_NAME");
        final String string5 = bundle.getString("com.facebook.platform.extra.PROFILE_LINK");
        final String string6 = bundle.getString("com.facebook.platform.extra.PROFILE_USER_ID");
        if (string != null && string2 != null && string3 != null && string4 != null && string5 != null && string6 != null) {
            return new Profile(string6, string2, string3, string4, string, Uri.parse(string5));
        }
        return null;
    }
    
    private static void handleLoginStatusError(final String s, final String s2, final String s3, final LoginLogger loginLogger, final LoginStatusCallback loginStatusCallback) {
        final FacebookException ex = new FacebookException(s + ": " + s2);
        loginLogger.logLoginStatusError(s3, (Exception)ex);
        loginStatusCallback.onError((Exception)ex);
    }
    
    private boolean isExpressLoginAllowed() {
        return this.sharedPreferences.getBoolean("express_login_allowed", true);
    }
    
    static boolean isPublishPermission(final String s) {
        return s != null && (s.startsWith("publish") || s.startsWith("manage") || LoginManager.OTHER_PUBLISH_PERMISSIONS.contains(s));
    }
    
    private void logCompleteLogin(final Context context, final LoginClient.Result.Code code, final Map<String, String> map, final Exception ex, final boolean b, final LoginClient.Request request) {
        final LoginLogger access$000 = getLogger(context);
        if (access$000 == null) {
            return;
        }
        if (request == null) {
            access$000.logUnexpectedError("fb_mobile_login_complete", "Unexpected call to logCompleteLogin with null pendingAuthorizationRequest.");
            return;
        }
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        String s;
        if (b) {
            s = "1";
        }
        else {
            s = "0";
        }
        hashMap.put("try_login_activity", s);
        access$000.logCompleteLogin(request.getAuthId(), hashMap, code, map, ex);
    }
    
    private void logInWithPublishPermissions(final FragmentWrapper fragmentWrapper, final Collection<String> collection) {
        this.validatePublishPermissions(collection);
        this.startLogin(new FragmentStartActivityDelegate(fragmentWrapper), this.createLoginRequest(collection));
    }
    
    private void logInWithReadPermissions(final FragmentWrapper fragmentWrapper, final Collection<String> collection) {
        this.validateReadPermissions(collection);
        this.startLogin(new FragmentStartActivityDelegate(fragmentWrapper), this.createLoginRequest(collection));
    }
    
    private void logStartLogin(final Context context, final LoginClient.Request request) {
        final LoginLogger access$000 = getLogger(context);
        if (access$000 != null && request != null) {
            access$000.logStartLogin(request);
        }
    }
    
    private void resolveError(final FragmentWrapper fragmentWrapper, final GraphResponse graphResponse) {
        this.startLogin(new FragmentStartActivityDelegate(fragmentWrapper), this.createLoginRequestFromResponse(graphResponse));
    }
    
    private boolean resolveIntent(final Intent intent) {
        boolean b = false;
        if (FacebookSdk.getApplicationContext().getPackageManager().resolveActivity(intent, 0) != null) {
            b = true;
        }
        return b;
    }
    
    private void retrieveLoginStatusImpl(final Context context, final LoginStatusCallback loginStatusCallback, final long n) {
        final String applicationId = FacebookSdk.getApplicationId();
        final String string = UUID.randomUUID().toString();
        final LoginLogger loginLogger = new LoginLogger(context, applicationId);
        if (!this.isExpressLoginAllowed()) {
            loginLogger.logLoginStatusFailure(string);
            loginStatusCallback.onFailure();
        }
        else {
            final LoginStatusClient loginStatusClient = new LoginStatusClient(context, applicationId, string, FacebookSdk.getGraphApiVersion(), n);
            loginStatusClient.setCompletedListener((PlatformServiceClient.CompletedListener)new PlatformServiceClient.CompletedListener() {
                @Override
                public void completed(final Bundle bundle) {
                    if (bundle == null) {
                        loginLogger.logLoginStatusFailure(string);
                        loginStatusCallback.onFailure();
                        return;
                    }
                    final String string = bundle.getString("com.facebook.platform.status.ERROR_TYPE");
                    final String string2 = bundle.getString("com.facebook.platform.status.ERROR_DESCRIPTION");
                    if (string != null) {
                        handleLoginStatusError(string, string2, string, loginLogger, loginStatusCallback);
                        return;
                    }
                    final String string3 = bundle.getString("com.facebook.platform.extra.ACCESS_TOKEN");
                    final long long1 = bundle.getLong("com.facebook.platform.extra.EXPIRES_SECONDS_SINCE_EPOCH");
                    final ArrayList stringArrayList = bundle.getStringArrayList("com.facebook.platform.extra.PERMISSIONS");
                    final String string4 = bundle.getString("signed request");
                    String userIDFromSignedRequest = null;
                    if (!Utility.isNullOrEmpty(string4)) {
                        userIDFromSignedRequest = LoginMethodHandler.getUserIDFromSignedRequest(string4);
                    }
                    if (!Utility.isNullOrEmpty(string3) && stringArrayList != null && !stringArrayList.isEmpty() && !Utility.isNullOrEmpty(userIDFromSignedRequest)) {
                        final AccessToken currentAccessToken = new AccessToken(string3, applicationId, userIDFromSignedRequest, (Collection)stringArrayList, (Collection)null, (AccessTokenSource)null, new Date(long1), (Date)null);
                        AccessToken.setCurrentAccessToken(currentAccessToken);
                        final Profile access$200 = getProfileFromBundle(bundle);
                        if (access$200 != null) {
                            Profile.setCurrentProfile(access$200);
                        }
                        else {
                            Profile.fetchProfileForCurrentAccessToken();
                        }
                        loginLogger.logLoginStatusSuccess(string);
                        loginStatusCallback.onCompleted(currentAccessToken);
                        return;
                    }
                    loginLogger.logLoginStatusFailure(string);
                    loginStatusCallback.onFailure();
                }
            });
            loginLogger.logLoginStatusStart(string);
            if (!loginStatusClient.start()) {
                loginLogger.logLoginStatusFailure(string);
                loginStatusCallback.onFailure();
            }
        }
    }
    
    private void setExpressLoginStatus(final boolean b) {
        final SharedPreferences$Editor edit = this.sharedPreferences.edit();
        edit.putBoolean("express_login_allowed", b);
        edit.apply();
    }
    
    private void startLogin(final StartActivityDelegate startActivityDelegate, final LoginClient.Request request) throws FacebookException {
        this.logStartLogin((Context)startActivityDelegate.getActivityContext(), request);
        CallbackManagerImpl.registerStaticCallback(CallbackManagerImpl$RequestCodeOffset.Login.toRequestCode(), (CallbackManagerImpl$Callback)new CallbackManagerImpl$Callback() {
            public boolean onActivityResult(final int n, final Intent intent) {
                return LoginManager.this.onActivityResult(n, intent);
            }
        });
        if (!this.tryFacebookActivity(startActivityDelegate, request)) {
            final FacebookException ex = new FacebookException("Log in attempt failed: FacebookActivity could not be started. Please make sure you added FacebookActivity to the AndroidManifest.");
            this.logCompleteLogin((Context)startActivityDelegate.getActivityContext(), LoginClient.Result.Code.ERROR, null, (Exception)ex, false, request);
            throw ex;
        }
    }
    
    private boolean tryFacebookActivity(final StartActivityDelegate startActivityDelegate, final LoginClient.Request request) {
        final Intent facebookActivityIntent = this.getFacebookActivityIntent(request);
        if (!this.resolveIntent(facebookActivityIntent)) {
            return false;
        }
        try {
            startActivityDelegate.startActivityForResult(facebookActivityIntent, LoginClient.getLoginRequestCode());
            return true;
        }
        catch (ActivityNotFoundException ex) {
            return false;
        }
    }
    
    private void validatePublishPermissions(final Collection<String> collection) {
        if (collection != null) {
            for (final String s : collection) {
                if (!isPublishPermission(s)) {
                    throw new FacebookException(String.format("Cannot pass a read permission (%s) to a request for publish authorization", s));
                }
            }
        }
    }
    
    private void validateReadPermissions(final Collection<String> collection) {
        if (collection != null) {
            for (final String s : collection) {
                if (isPublishPermission(s)) {
                    throw new FacebookException(String.format("Cannot pass a publish or manage permission (%s) to a request for read authorization", s));
                }
            }
        }
    }
    
    protected LoginClient.Request createLoginRequest(final Collection<String> collection) {
        final LoginBehavior loginBehavior = this.loginBehavior;
        HashSet<String> set;
        if (collection != null) {
            set = new HashSet<String>(collection);
        }
        else {
            set = new HashSet<String>();
        }
        final LoginClient.Request request = new LoginClient.Request(loginBehavior, Collections.unmodifiableSet((Set<? extends String>)set), this.defaultAudience, FacebookSdk.getApplicationId(), UUID.randomUUID().toString());
        request.setRerequest(AccessToken.isCurrentAccessTokenActive());
        return request;
    }
    
    public DefaultAudience getDefaultAudience() {
        return this.defaultAudience;
    }
    
    protected Intent getFacebookActivityIntent(final LoginClient.Request request) {
        final Intent intent = new Intent();
        intent.setClass(FacebookSdk.getApplicationContext(), (Class)FacebookActivity.class);
        intent.setAction(request.getLoginBehavior().toString());
        final Bundle bundle = new Bundle();
        bundle.putParcelable("request", (Parcelable)request);
        intent.putExtra("com.facebook.LoginFragment:Request", bundle);
        return intent;
    }
    
    public LoginBehavior getLoginBehavior() {
        return this.loginBehavior;
    }
    
    public void logInWithPublishPermissions(final Activity activity, final Collection<String> collection) {
        this.validatePublishPermissions(collection);
        this.startLogin(new ActivityStartActivityDelegate(activity), this.createLoginRequest(collection));
    }
    
    public void logInWithPublishPermissions(final Fragment fragment, final Collection<String> collection) {
        this.logInWithPublishPermissions(new FragmentWrapper(fragment), collection);
    }
    
    public void logInWithPublishPermissions(final android.support.v4.app.Fragment fragment, final Collection<String> collection) {
        this.logInWithPublishPermissions(new FragmentWrapper(fragment), collection);
    }
    
    public void logInWithReadPermissions(final Activity activity, final Collection<String> collection) {
        this.validateReadPermissions(collection);
        this.startLogin(new ActivityStartActivityDelegate(activity), this.createLoginRequest(collection));
    }
    
    public void logInWithReadPermissions(final Fragment fragment, final Collection<String> collection) {
        this.logInWithReadPermissions(new FragmentWrapper(fragment), collection);
    }
    
    public void logInWithReadPermissions(final android.support.v4.app.Fragment fragment, final Collection<String> collection) {
        this.logInWithReadPermissions(new FragmentWrapper(fragment), collection);
    }
    
    public void logOut() {
        AccessToken.setCurrentAccessToken((AccessToken)null);
        Profile.setCurrentProfile((Profile)null);
        this.setExpressLoginStatus(false);
    }
    
    boolean onActivityResult(final int n, final Intent intent) {
        return this.onActivityResult(n, intent, null);
    }
    
    boolean onActivityResult(final int n, final Intent intent, final FacebookCallback<LoginResult> facebookCallback) {
        final FacebookException ex = null;
        final FacebookException ex2 = null;
        final AccessToken accessToken = null;
        final AccessToken accessToken2 = null;
        Enum<LoginClient.Result.Code> enum1 = LoginClient.Result.Code.ERROR;
        final Map<String, String> map = null;
        final LoginClient.Request request = null;
        boolean b = false;
        final boolean b2 = false;
        Map<String, String> loggingExtras;
        FacebookException ex3;
        LoginClient.Request request2;
        AccessToken accessToken3;
        if (intent != null) {
            final LoginClient.Result result = (LoginClient.Result)intent.getParcelableExtra("com.facebook.LoginFragment:Result");
            loggingExtras = map;
            ex3 = ex;
            request2 = request;
            accessToken3 = accessToken;
            if (result != null) {
                request2 = result.request;
                final LoginClient.Result.Code code = result.code;
                AccessToken token;
                if (n == -1) {
                    if (result.code == LoginClient.Result.Code.SUCCESS) {
                        token = result.token;
                        b = b2;
                        ex3 = ex2;
                    }
                    else {
                        ex3 = new FacebookAuthorizationException(result.errorMessage);
                        token = accessToken2;
                        b = b2;
                    }
                }
                else {
                    ex3 = ex2;
                    token = accessToken2;
                    b = b2;
                    if (n == 0) {
                        b = true;
                        ex3 = ex2;
                        token = accessToken2;
                    }
                }
                loggingExtras = result.loggingExtras;
                accessToken3 = token;
                enum1 = code;
            }
        }
        else {
            loggingExtras = map;
            ex3 = ex;
            request2 = request;
            accessToken3 = accessToken;
            if (n == 0) {
                b = true;
                enum1 = LoginClient.Result.Code.CANCEL;
                loggingExtras = map;
                ex3 = ex;
                request2 = request;
                accessToken3 = accessToken;
            }
        }
        Object o;
        if ((o = ex3) == null) {
            o = ex3;
            if (accessToken3 == null) {
                o = ex3;
                if (!b) {
                    o = new FacebookException("Unexpected call to LoginManager.onActivityResult");
                }
            }
        }
        this.logCompleteLogin(null, (LoginClient.Result.Code)enum1, loggingExtras, (Exception)o, true, request2);
        this.finishLogin(accessToken3, request2, (FacebookException)o, b, facebookCallback);
        return true;
    }
    
    public void registerCallback(final CallbackManager callbackManager, final FacebookCallback<LoginResult> facebookCallback) {
        if (!(callbackManager instanceof CallbackManagerImpl)) {
            throw new FacebookException("Unexpected CallbackManager, please use the provided Factory.");
        }
        ((CallbackManagerImpl)callbackManager).registerCallback(CallbackManagerImpl$RequestCodeOffset.Login.toRequestCode(), (CallbackManagerImpl$Callback)new CallbackManagerImpl$Callback() {
            public boolean onActivityResult(final int n, final Intent intent) {
                return LoginManager.this.onActivityResult(n, intent, facebookCallback);
            }
        });
    }
    
    public void resolveError(final Activity activity, final GraphResponse graphResponse) {
        this.startLogin(new ActivityStartActivityDelegate(activity), this.createLoginRequestFromResponse(graphResponse));
    }
    
    public void resolveError(final Fragment fragment, final GraphResponse graphResponse) {
        this.resolveError(new FragmentWrapper(fragment), graphResponse);
    }
    
    public void resolveError(final android.support.v4.app.Fragment fragment, final GraphResponse graphResponse) {
        this.resolveError(new FragmentWrapper(fragment), graphResponse);
    }
    
    public void retrieveLoginStatus(final Context context, final long n, final LoginStatusCallback loginStatusCallback) {
        this.retrieveLoginStatusImpl(context, loginStatusCallback, n);
    }
    
    public void retrieveLoginStatus(final Context context, final LoginStatusCallback loginStatusCallback) {
        this.retrieveLoginStatus(context, 5000L, loginStatusCallback);
    }
    
    public LoginManager setDefaultAudience(final DefaultAudience defaultAudience) {
        this.defaultAudience = defaultAudience;
        return this;
    }
    
    public LoginManager setLoginBehavior(final LoginBehavior loginBehavior) {
        this.loginBehavior = loginBehavior;
        return this;
    }
    
    public void unregisterCallback(final CallbackManager callbackManager) {
        if (!(callbackManager instanceof CallbackManagerImpl)) {
            throw new FacebookException("Unexpected CallbackManager, please use the provided Factory.");
        }
        ((CallbackManagerImpl)callbackManager).unregisterCallback(CallbackManagerImpl$RequestCodeOffset.Login.toRequestCode());
    }
    
    private static class ActivityStartActivityDelegate implements StartActivityDelegate
    {
        private final Activity activity;
        
        ActivityStartActivityDelegate(final Activity activity) {
            Validate.notNull((Object)activity, "activity");
            this.activity = activity;
        }
        
        @Override
        public Activity getActivityContext() {
            return this.activity;
        }
        
        @Override
        public void startActivityForResult(final Intent intent, final int n) {
            this.activity.startActivityForResult(intent, n);
        }
    }
    
    private static class FragmentStartActivityDelegate implements StartActivityDelegate
    {
        private final FragmentWrapper fragment;
        
        FragmentStartActivityDelegate(final FragmentWrapper fragment) {
            Validate.notNull((Object)fragment, "fragment");
            this.fragment = fragment;
        }
        
        @Override
        public Activity getActivityContext() {
            return this.fragment.getActivity();
        }
        
        @Override
        public void startActivityForResult(final Intent intent, final int n) {
            this.fragment.startActivityForResult(intent, n);
        }
    }
    
    private static class LoginLoggerHolder
    {
        private static LoginLogger logger;
        
        private static LoginLogger getLogger(Context applicationContext) {
            // monitorenter(LoginLoggerHolder.class)
            Label_0018: {
                if (applicationContext == null) {
                    break Label_0018;
                }
            Label_0013_Outer:
                while (true) {
                    Label_0025: {
                        if (applicationContext != null) {
                            break Label_0025;
                        }
                        applicationContext = null;
                    Block_3_Outer:
                        while (true) {
                            return (LoginLogger)applicationContext;
                            try {
                                applicationContext = FacebookSdk.getApplicationContext();
                                continue Label_0013_Outer;
                                while (true) {
                                    LoginLoggerHolder.logger = new LoginLogger(applicationContext, FacebookSdk.getApplicationId());
                                    Label_0045: {
                                        final LoginLogger logger = LoginLoggerHolder.logger;
                                    }
                                    continue Block_3_Outer;
                                    continue;
                                }
                            }
                            // iftrue(Label_0045:, LoginLoggerHolder.logger != null)
                            finally {
                            }
                            // monitorexit(LoginLoggerHolder.class)
                            break;
                        }
                    }
                    break;
                }
            }
        }
        // monitorexit(LoginLoggerHolder.class)
    }
}
