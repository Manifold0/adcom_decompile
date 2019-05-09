// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.login;

import android.support.v4.app.FragmentActivity;
import android.app.Dialog;
import com.facebook.internal.FacebookDialogFragment;
import android.content.Context;
import com.facebook.FacebookException;
import android.os.Bundle;
import com.facebook.AccessTokenSource;
import android.os.Parcel;
import com.facebook.internal.WebDialog;
import android.os.Parcelable$Creator;

class WebViewLoginMethodHandler extends WebLoginMethodHandler
{
    public static final Parcelable$Creator<WebViewLoginMethodHandler> CREATOR;
    private String e2e;
    private WebDialog loginDialog;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator() {
            public WebViewLoginMethodHandler createFromParcel(final Parcel parcel) {
                return new WebViewLoginMethodHandler(parcel);
            }
            
            public WebViewLoginMethodHandler[] newArray(final int n) {
                return new WebViewLoginMethodHandler[n];
            }
        };
    }
    
    WebViewLoginMethodHandler(final Parcel parcel) {
        super(parcel);
        this.e2e = parcel.readString();
    }
    
    WebViewLoginMethodHandler(final LoginClient loginClient) {
        super(loginClient);
    }
    
    @Override
    void cancel() {
        if (this.loginDialog != null) {
            this.loginDialog.cancel();
            this.loginDialog = null;
        }
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    String getNameForLogging() {
        return "web_view";
    }
    
    @Override
    AccessTokenSource getTokenSource() {
        return AccessTokenSource.WEB_VIEW;
    }
    
    @Override
    boolean needsInternetPermission() {
        return true;
    }
    
    void onWebDialogComplete(final LoginClient.Request request, final Bundle bundle, final FacebookException ex) {
        super.onComplete(request, bundle, ex);
    }
    
    @Override
    boolean tryAuthorize(final LoginClient.Request request) {
        final Bundle parameters = this.getParameters(request);
        final WebDialog.OnCompleteListener onCompleteListener = new WebDialog.OnCompleteListener() {
            @Override
            public void onComplete(final Bundle bundle, final FacebookException ex) {
                WebViewLoginMethodHandler.this.onWebDialogComplete(request, bundle, ex);
            }
        };
        this.addLoggingExtra("e2e", this.e2e = LoginClient.getE2E());
        final FragmentActivity activity = this.loginClient.getActivity();
        this.loginDialog = ((WebDialog.Builder)new AuthDialogBuilder((Context)activity, request.getApplicationId(), parameters).setE2E(this.e2e).setIsRerequest(request.isRerequest())).setOnCompleteListener(onCompleteListener).build();
        final FacebookDialogFragment facebookDialogFragment = new FacebookDialogFragment();
        facebookDialogFragment.setRetainInstance(true);
        facebookDialogFragment.setDialog(this.loginDialog);
        facebookDialogFragment.show(activity.getSupportFragmentManager(), "FacebookDialogFragment");
        return true;
    }
    
    @Override
    public void writeToParcel(final Parcel parcel, final int n) {
        super.writeToParcel(parcel, n);
        parcel.writeString(this.e2e);
    }
    
    static class AuthDialogBuilder extends Builder
    {
        private static final String OAUTH_DIALOG = "oauth";
        static final String REDIRECT_URI = "fbconnect://success";
        private String e2e;
        private boolean isRerequest;
        
        public AuthDialogBuilder(final Context context, final String s, final Bundle bundle) {
            super(context, s, "oauth", bundle);
        }
        
        @Override
        public WebDialog build() {
            final Bundle parameters = ((WebDialog.Builder)this).getParameters();
            parameters.putString("redirect_uri", "fbconnect://success");
            parameters.putString("client_id", ((WebDialog.Builder)this).getApplicationId());
            parameters.putString("e2e", this.e2e);
            parameters.putString("response_type", "token,signed_request");
            parameters.putString("return_scopes", "true");
            parameters.putString("auth_type", "rerequest");
            return WebDialog.newInstance(((WebDialog.Builder)this).getContext(), "oauth", parameters, ((WebDialog.Builder)this).getTheme(), ((WebDialog.Builder)this).getListener());
        }
        
        public AuthDialogBuilder setE2E(final String e2e) {
            this.e2e = e2e;
            return this;
        }
        
        public AuthDialogBuilder setIsRerequest(final boolean isRerequest) {
            this.isRerequest = isRerequest;
            return this;
        }
    }
}
