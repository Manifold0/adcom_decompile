// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.login;

import android.content.ActivityNotFoundException;
import com.facebook.internal.ServerProtocol;
import com.facebook.FacebookException;
import java.util.Collection;
import com.facebook.AccessTokenSource;
import com.facebook.internal.Utility;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;

abstract class NativeAppLoginMethodHandler extends LoginMethodHandler
{
    NativeAppLoginMethodHandler(final Parcel parcel) {
        super(parcel);
    }
    
    NativeAppLoginMethodHandler(final LoginClient loginClient) {
        super(loginClient);
    }
    
    private String getError(final Bundle bundle) {
        String s;
        if ((s = bundle.getString("error")) == null) {
            s = bundle.getString("error_type");
        }
        return s;
    }
    
    private String getErrorMessage(final Bundle bundle) {
        String s;
        if ((s = bundle.getString("error_message")) == null) {
            s = bundle.getString("error_description");
        }
        return s;
    }
    
    private LoginClient.Result handleResultCancel(final LoginClient.Request request, final Intent intent) {
        final Bundle extras = intent.getExtras();
        final String error = this.getError(extras);
        String string;
        if (extras.get("error_code") != null) {
            string = extras.get("error_code").toString();
        }
        else {
            string = null;
        }
        if ("CONNECTION_FAILURE".equals(string)) {
            return LoginClient.Result.createErrorResult(request, error, this.getErrorMessage(extras), string);
        }
        return LoginClient.Result.createCancelResult(request, error);
    }
    
    private LoginClient.Result handleResultOk(final LoginClient.Request request, final Intent intent) {
        Object tokenResult = null;
        final Bundle extras = intent.getExtras();
        final String error = this.getError(extras);
        String string = null;
        String errorMessage = null;
        Label_0105: {
            if (extras.get("error_code") == null) {
                break Label_0105;
            }
            string = extras.get("error_code").toString();
            while (true) {
                errorMessage = this.getErrorMessage(extras);
                final String string2 = extras.getString("e2e");
                if (!Utility.isNullOrEmpty(string2)) {
                    this.logWebLoginCompleted(string2);
                }
                if (error != null || string != null || errorMessage != null) {
                    break Label_0105;
                }
                try {
                    tokenResult = LoginClient.Result.createTokenResult(request, LoginMethodHandler.createAccessTokenFromWebBundle(request.getPermissions(), extras, AccessTokenSource.FACEBOOK_APPLICATION_WEB, request.getApplicationId()));
                    return (LoginClient.Result)tokenResult;
                    string = null;
                    continue;
                }
                catch (FacebookException ex) {
                    return LoginClient.Result.createErrorResult(request, null, ex.getMessage());
                }
                break;
            }
        }
        if (ServerProtocol.errorsProxyAuthDisabled.contains(error)) {
            return (LoginClient.Result)tokenResult;
        }
        if (ServerProtocol.errorsUserCanceled.contains(error)) {
            return LoginClient.Result.createCancelResult(request, null);
        }
        return LoginClient.Result.createErrorResult(request, error, errorMessage, string);
    }
    
    @Override
    boolean onActivityResult(final int n, final int n2, final Intent intent) {
        final LoginClient.Request pendingRequest = this.loginClient.getPendingRequest();
        Object o;
        if (intent == null) {
            o = LoginClient.Result.createCancelResult(pendingRequest, "Operation canceled");
        }
        else if (n2 == 0) {
            o = this.handleResultCancel(pendingRequest, intent);
        }
        else if (n2 != -1) {
            o = LoginClient.Result.createErrorResult(pendingRequest, "Unexpected resultCode from authorization.", null);
        }
        else {
            o = this.handleResultOk(pendingRequest, intent);
        }
        if (o != null) {
            this.loginClient.completeAndValidate((LoginClient.Result)o);
        }
        else {
            this.loginClient.tryNextHandler();
        }
        return true;
    }
    
    @Override
    abstract boolean tryAuthorize(final LoginClient.Request p0);
    
    protected boolean tryIntent(final Intent intent, final int n) {
        if (intent == null) {
            return false;
        }
        try {
            this.loginClient.getFragment().startActivityForResult(intent, n);
            return true;
        }
        catch (ActivityNotFoundException ex) {
            return false;
        }
    }
}
