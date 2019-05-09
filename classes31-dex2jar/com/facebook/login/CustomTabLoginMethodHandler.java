// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.login;

import com.facebook.AccessTokenSource;
import org.json.JSONException;
import org.json.JSONObject;
import com.facebook.FacebookServiceException;
import com.facebook.FacebookRequestError;
import com.facebook.FacebookOperationCanceledException;
import android.os.Bundle;
import com.facebook.FacebookException;
import android.net.Uri;
import com.facebook.CustomTabMainActivity;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.internal.FetchedAppSettingsManager;
import android.content.Context;
import com.facebook.internal.Validate;
import com.facebook.FacebookSdk;
import android.content.pm.ServiceInfo;
import java.util.Iterator;
import java.util.List;
import android.content.pm.ResolveInfo;
import java.util.Collection;
import java.util.HashSet;
import java.util.Arrays;
import android.content.Intent;
import com.facebook.internal.Utility;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class CustomTabLoginMethodHandler extends WebLoginMethodHandler
{
    private static final int API_EC_DIALOG_CANCEL = 4201;
    private static final int CHALLENGE_LENGTH = 20;
    private static final String[] CHROME_PACKAGES;
    public static final Parcelable$Creator<CustomTabLoginMethodHandler> CREATOR;
    private static final String CUSTOM_TABS_SERVICE_ACTION = "android.support.customtabs.action.CustomTabsService";
    private static final int CUSTOM_TAB_REQUEST_CODE = 1;
    private String currentPackage;
    private String expectedChallenge;
    
    static {
        CHROME_PACKAGES = new String[] { "com.android.chrome", "com.chrome.beta", "com.chrome.dev" };
        CREATOR = (Parcelable$Creator)new Parcelable$Creator() {
            public CustomTabLoginMethodHandler createFromParcel(final Parcel parcel) {
                return new CustomTabLoginMethodHandler(parcel);
            }
            
            public CustomTabLoginMethodHandler[] newArray(final int n) {
                return new CustomTabLoginMethodHandler[n];
            }
        };
    }
    
    CustomTabLoginMethodHandler(final Parcel parcel) {
        super(parcel);
        this.expectedChallenge = parcel.readString();
    }
    
    CustomTabLoginMethodHandler(final LoginClient loginClient) {
        super(loginClient);
        this.expectedChallenge = Utility.generateRandomString(20);
    }
    
    private String getChromePackage() {
        if (this.currentPackage != null) {
            return this.currentPackage;
        }
        final List queryIntentServices = ((Context)this.loginClient.getActivity()).getPackageManager().queryIntentServices(new Intent("android.support.customtabs.action.CustomTabsService"), 0);
        if (queryIntentServices != null) {
            final HashSet set = new HashSet((Collection<? extends E>)Arrays.asList(CustomTabLoginMethodHandler.CHROME_PACKAGES));
            final Iterator<ResolveInfo> iterator = queryIntentServices.iterator();
            while (iterator.hasNext()) {
                final ServiceInfo serviceInfo = iterator.next().serviceInfo;
                if (serviceInfo != null && set.contains(serviceInfo.packageName)) {
                    return this.currentPackage = serviceInfo.packageName;
                }
            }
        }
        return null;
    }
    
    private boolean isCustomTabsAllowed() {
        return this.isCustomTabsEnabled() && this.getChromePackage() != null && this.isCustomTabsCompatibleWithAutofill() && Validate.hasCustomTabRedirectActivity(FacebookSdk.getApplicationContext());
    }
    
    private boolean isCustomTabsCompatibleWithAutofill() {
        return !Utility.isAutofillAvailable((Context)this.loginClient.getActivity());
    }
    
    private boolean isCustomTabsEnabled() {
        final FetchedAppSettings appSettingsWithoutQuery = FetchedAppSettingsManager.getAppSettingsWithoutQuery(Utility.getMetadataApplicationId((Context)this.loginClient.getActivity()));
        return appSettingsWithoutQuery != null && appSettingsWithoutQuery.getCustomTabsEnabled();
    }
    
    private void onCustomTabComplete(String s, final LoginClient.Request request) {
        if (s != null && s.startsWith(CustomTabMainActivity.getRedirectUrl())) {
            final Uri parse = Uri.parse(s);
            final Bundle urlQueryString = Utility.parseUrlQueryString(parse.getQuery());
            urlQueryString.putAll(Utility.parseUrlQueryString(parse.getFragment()));
            if (!this.validateChallengeParam(urlQueryString)) {
                super.onComplete(request, null, new FacebookException("Invalid state parameter"));
            }
            else {
                s = urlQueryString.getString("error");
                String string;
                if ((string = s) == null) {
                    string = urlQueryString.getString("error_type");
                }
                if ((s = urlQueryString.getString("error_msg")) == null) {
                    s = urlQueryString.getString("error_message");
                }
                String string2;
                if ((string2 = s) == null) {
                    string2 = urlQueryString.getString("error_description");
                }
                s = urlQueryString.getString("error_code");
                int int1 = -1;
                while (true) {
                    if (Utility.isNullOrEmpty(s)) {
                        break Label_0152;
                    }
                    try {
                        int1 = Integer.parseInt(s);
                        if (Utility.isNullOrEmpty(string) && Utility.isNullOrEmpty(string2) && int1 == -1) {
                            super.onComplete(request, urlQueryString, null);
                            return;
                        }
                    }
                    catch (NumberFormatException ex) {
                        int1 = -1;
                        continue;
                    }
                    break;
                }
                if (string != null && (string.equals("access_denied") || string.equals("OAuthAccessDeniedException"))) {
                    super.onComplete(request, null, (FacebookException)new FacebookOperationCanceledException());
                    return;
                }
                if (int1 == 4201) {
                    super.onComplete(request, null, (FacebookException)new FacebookOperationCanceledException());
                    return;
                }
                super.onComplete(request, null, (FacebookException)new FacebookServiceException(new FacebookRequestError(int1, string, string2), string2));
            }
        }
    }
    
    private boolean validateChallengeParam(final Bundle bundle) {
        try {
            final String string = bundle.getString("state");
            return string != null && new JSONObject(string).getString("7_challenge").equals(this.expectedChallenge);
        }
        catch (JSONException ex) {
            return false;
        }
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    String getNameForLogging() {
        return "custom_tab";
    }
    
    @Override
    protected String getSSODevice() {
        return "chrome_custom_tab";
    }
    
    @Override
    AccessTokenSource getTokenSource() {
        return AccessTokenSource.CHROME_CUSTOM_TAB;
    }
    
    @Override
    boolean onActivityResult(final int n, final int n2, final Intent intent) {
        if (n != 1) {
            return super.onActivityResult(n, n2, intent);
        }
        final LoginClient.Request pendingRequest = this.loginClient.getPendingRequest();
        if (n2 == -1) {
            this.onCustomTabComplete(intent.getStringExtra(CustomTabMainActivity.EXTRA_URL), pendingRequest);
            return true;
        }
        super.onComplete(pendingRequest, null, (FacebookException)new FacebookOperationCanceledException());
        return false;
    }
    
    protected void putChallengeParam(final JSONObject jsonObject) throws JSONException {
        jsonObject.put("7_challenge", (Object)this.expectedChallenge);
    }
    
    @Override
    boolean tryAuthorize(final LoginClient.Request request) {
        if (!this.isCustomTabsAllowed()) {
            return false;
        }
        final Bundle addExtraParameters = this.addExtraParameters(this.getParameters(request), request);
        final Intent intent = new Intent((Context)this.loginClient.getActivity(), (Class)CustomTabMainActivity.class);
        intent.putExtra(CustomTabMainActivity.EXTRA_PARAMS, addExtraParameters);
        intent.putExtra(CustomTabMainActivity.EXTRA_CHROME_PACKAGE, this.getChromePackage());
        this.loginClient.getFragment().startActivityForResult(intent, 1);
        return true;
    }
    
    @Override
    public void writeToParcel(final Parcel parcel, final int n) {
        super.writeToParcel(parcel, n);
        parcel.writeString(this.expectedChallenge);
    }
}
