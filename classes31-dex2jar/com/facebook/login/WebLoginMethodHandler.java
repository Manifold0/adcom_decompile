// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.login;

import com.facebook.FacebookRequestError;
import java.util.Locale;
import com.facebook.FacebookServiceException;
import com.facebook.FacebookOperationCanceledException;
import android.webkit.CookieSyncManager;
import com.facebook.FacebookException;
import com.facebook.AccessTokenSource;
import android.content.Context;
import com.facebook.AccessToken;
import android.text.TextUtils;
import java.util.Collection;
import com.facebook.internal.Utility;
import android.os.Bundle;
import com.facebook.FacebookSdk;
import android.os.Parcel;

abstract class WebLoginMethodHandler extends LoginMethodHandler
{
    private static final String WEB_VIEW_AUTH_HANDLER_STORE = "com.facebook.login.AuthorizationClient.WebViewAuthHandler.TOKEN_STORE_KEY";
    private static final String WEB_VIEW_AUTH_HANDLER_TOKEN_KEY = "TOKEN";
    private String e2e;
    
    WebLoginMethodHandler(final Parcel parcel) {
        super(parcel);
    }
    
    WebLoginMethodHandler(final LoginClient loginClient) {
        super(loginClient);
    }
    
    private static final String getRedirectUri() {
        return "fb" + FacebookSdk.getApplicationId() + "://authorize";
    }
    
    private String loadCookieToken() {
        return ((Context)this.loginClient.getActivity()).getSharedPreferences("com.facebook.login.AuthorizationClient.WebViewAuthHandler.TOKEN_STORE_KEY", 0).getString("TOKEN", "");
    }
    
    private void saveCookieToken(final String s) {
        ((Context)this.loginClient.getActivity()).getSharedPreferences("com.facebook.login.AuthorizationClient.WebViewAuthHandler.TOKEN_STORE_KEY", 0).edit().putString("TOKEN", s).apply();
    }
    
    protected Bundle addExtraParameters(final Bundle bundle, final LoginClient.Request request) {
        bundle.putString("redirect_uri", getRedirectUri());
        bundle.putString("client_id", request.getApplicationId());
        final LoginClient loginClient = this.loginClient;
        bundle.putString("e2e", LoginClient.getE2E());
        bundle.putString("response_type", "token,signed_request");
        bundle.putString("return_scopes", "true");
        bundle.putString("auth_type", "rerequest");
        if (this.getSSODevice() != null) {
            bundle.putString("sso", this.getSSODevice());
        }
        return bundle;
    }
    
    protected Bundle getParameters(final LoginClient.Request request) {
        final Bundle bundle = new Bundle();
        if (!Utility.isNullOrEmpty((Collection)request.getPermissions())) {
            final String join = TextUtils.join((CharSequence)",", (Iterable)request.getPermissions());
            bundle.putString("scope", join);
            this.addLoggingExtra("scope", join);
        }
        bundle.putString("default_audience", request.getDefaultAudience().getNativeProtocolAudience());
        bundle.putString("state", this.getClientState(request.getAuthId()));
        final AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();
        String token;
        if (currentAccessToken != null) {
            token = currentAccessToken.getToken();
        }
        else {
            token = null;
        }
        if (token != null && token.equals(this.loadCookieToken())) {
            bundle.putString("access_token", token);
            this.addLoggingExtra("access_token", "1");
            return bundle;
        }
        Utility.clearFacebookCookies((Context)this.loginClient.getActivity());
        this.addLoggingExtra("access_token", "0");
        return bundle;
    }
    
    protected String getSSODevice() {
        return null;
    }
    
    abstract AccessTokenSource getTokenSource();
    
    protected void onComplete(final LoginClient.Request request, final Bundle bundle, final FacebookException ex) {
        this.e2e = null;
        while (true) {
            Label_0125: {
                if (bundle == null) {
                    break Label_0125;
                }
                if (bundle.containsKey("e2e")) {
                    this.e2e = bundle.getString("e2e");
                }
                try {
                    final AccessToken accessTokenFromWebBundle = LoginMethodHandler.createAccessTokenFromWebBundle(request.getPermissions(), bundle, this.getTokenSource(), request.getApplicationId());
                    final LoginClient.Result result = LoginClient.Result.createTokenResult(this.loginClient.getPendingRequest(), accessTokenFromWebBundle);
                    CookieSyncManager.createInstance((Context)this.loginClient.getActivity()).sync();
                    this.saveCookieToken(accessTokenFromWebBundle.getToken());
                    if (!Utility.isNullOrEmpty(this.e2e)) {
                        this.logWebLoginCompleted(this.e2e);
                    }
                    this.loginClient.completeAndValidate(result);
                    return;
                }
                catch (FacebookException ex2) {
                    final LoginClient.Result result = LoginClient.Result.createErrorResult(this.loginClient.getPendingRequest(), null, ex2.getMessage());
                    continue;
                }
            }
            if (ex instanceof FacebookOperationCanceledException) {
                final LoginClient.Result result = LoginClient.Result.createCancelResult(this.loginClient.getPendingRequest(), "User canceled log in.");
                continue;
            }
            this.e2e = null;
            String format = null;
            String s = ex.getMessage();
            if (ex instanceof FacebookServiceException) {
                final FacebookRequestError requestError = ((FacebookServiceException)ex).getRequestError();
                format = String.format(Locale.ROOT, "%d", requestError.getErrorCode());
                s = requestError.toString();
            }
            final LoginClient.Result result = LoginClient.Result.createErrorResult(this.loginClient.getPendingRequest(), null, s, format);
            continue;
        }
    }
}
