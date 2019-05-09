// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.login;

import android.content.Intent;
import android.content.Context;
import com.facebook.appevents.AppEventsLogger;
import android.util.Log;
import java.util.HashMap;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Base64;
import com.facebook.FacebookException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import com.facebook.AccessToken;
import com.facebook.AccessTokenSource;
import android.os.Bundle;
import com.facebook.internal.Utility;
import android.os.Parcel;
import java.util.Map;
import android.os.Parcelable;

abstract class LoginMethodHandler implements Parcelable
{
    protected LoginClient loginClient;
    Map<String, String> methodLoggingExtras;
    
    LoginMethodHandler(final Parcel parcel) {
        this.methodLoggingExtras = (Map<String, String>)Utility.readStringMapFromParcel(parcel);
    }
    
    LoginMethodHandler(final LoginClient loginClient) {
        this.loginClient = loginClient;
    }
    
    static AccessToken createAccessTokenFromNativeLogin(final Bundle bundle, final AccessTokenSource accessTokenSource, final String s) {
        final Date bundleLongAsDate = Utility.getBundleLongAsDate(bundle, "com.facebook.platform.extra.EXPIRES_SECONDS_SINCE_EPOCH", new Date(0L));
        final ArrayList stringArrayList = bundle.getStringArrayList("com.facebook.platform.extra.PERMISSIONS");
        final String string = bundle.getString("com.facebook.platform.extra.ACCESS_TOKEN");
        if (Utility.isNullOrEmpty(string)) {
            return null;
        }
        return new AccessToken(string, s, bundle.getString("com.facebook.platform.extra.USER_ID"), (Collection)stringArrayList, (Collection)null, accessTokenSource, bundleLongAsDate, new Date());
    }
    
    public static AccessToken createAccessTokenFromWebBundle(Collection<String> list, final Bundle bundle, final AccessTokenSource accessTokenSource, final String s) throws FacebookException {
        final Date bundleLongAsDate = Utility.getBundleLongAsDate(bundle, "expires_in", new Date());
        final String string = bundle.getString("access_token");
        final String string2 = bundle.getString("granted_scopes");
        if (!Utility.isNullOrEmpty(string2)) {
            list = new ArrayList<String>(Arrays.asList(string2.split(",")));
        }
        final String string3 = bundle.getString("denied_scopes");
        Collection collection = null;
        if (!Utility.isNullOrEmpty(string3)) {
            collection = new ArrayList(Arrays.asList(string3.split(",")));
        }
        if (Utility.isNullOrEmpty(string)) {
            return null;
        }
        return new AccessToken(string, s, getUserIDFromSignedRequest(bundle.getString("signed_request")), (Collection)list, collection, accessTokenSource, bundleLongAsDate, new Date());
    }
    
    static String getUserIDFromSignedRequest(String string) throws FacebookException {
        if (string == null || string.isEmpty()) {
            throw new FacebookException("Authorization response does not contain the signed_request");
        }
        try {
            final String[] split = string.split("\\.");
            if (split.length == 2) {
                string = new JSONObject(new String(Base64.decode(split[1], 0), "UTF-8")).getString("user_id");
                return string;
            }
            goto Label_0066;
        }
        catch (JSONException ex) {}
        catch (UnsupportedEncodingException ex2) {
            goto Label_0066;
        }
    }
    
    protected void addLoggingExtra(final String s, final Object o) {
        if (this.methodLoggingExtras == null) {
            this.methodLoggingExtras = new HashMap<String, String>();
        }
        final Map<String, String> methodLoggingExtras = this.methodLoggingExtras;
        String string;
        if (o == null) {
            string = null;
        }
        else {
            string = o.toString();
        }
        methodLoggingExtras.put(s, string);
    }
    
    void cancel() {
    }
    
    protected String getClientState(final String s) {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("0_auth_logger_id", (Object)s);
            jsonObject.put("3_method", (Object)this.getNameForLogging());
            this.putChallengeParam(jsonObject);
            return jsonObject.toString();
        }
        catch (JSONException ex) {
            Log.w("LoginMethodHandler", "Error creating client state json: " + ex.getMessage());
            return jsonObject.toString();
        }
    }
    
    abstract String getNameForLogging();
    
    protected void logWebLoginCompleted(final String s) {
        final String applicationId = this.loginClient.getPendingRequest().getApplicationId();
        final AppEventsLogger logger = AppEventsLogger.newLogger((Context)this.loginClient.getActivity(), applicationId);
        final Bundle bundle = new Bundle();
        bundle.putString("fb_web_login_e2e", s);
        bundle.putLong("fb_web_login_switchback_time", System.currentTimeMillis());
        bundle.putString("app_id", applicationId);
        logger.logSdkEvent("fb_dialogs_web_login_dialog_complete", (Double)null, bundle);
    }
    
    boolean needsInternetPermission() {
        return false;
    }
    
    boolean onActivityResult(final int n, final int n2, final Intent intent) {
        return false;
    }
    
    void putChallengeParam(final JSONObject jsonObject) throws JSONException {
    }
    
    void setLoginClient(final LoginClient loginClient) {
        if (this.loginClient != null) {
            throw new FacebookException("Can't set LoginClient if it is already set.");
        }
        this.loginClient = loginClient;
    }
    
    abstract boolean tryAuthorize(final LoginClient.Request p0);
    
    public void writeToParcel(final Parcel parcel, final int n) {
        Utility.writeStringMapToParcel(parcel, (Map)this.methodLoggingExtras);
    }
}
