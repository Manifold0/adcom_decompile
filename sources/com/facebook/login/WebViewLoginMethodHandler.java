package com.facebook.login;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.app.FragmentActivity;
import com.facebook.AccessTokenSource;
import com.facebook.FacebookException;
import com.facebook.internal.FacebookDialogFragment;
import com.facebook.internal.ServerProtocol;
import com.facebook.internal.WebDialog;
import com.facebook.internal.WebDialog.Builder;
import com.facebook.internal.WebDialog.OnCompleteListener;
import com.facebook.login.LoginClient.Request;

class WebViewLoginMethodHandler extends WebLoginMethodHandler {
    public static final Creator<WebViewLoginMethodHandler> CREATOR = new C03362();
    private String e2e;
    private WebDialog loginDialog;

    /* renamed from: com.facebook.login.WebViewLoginMethodHandler$2 */
    static class C03362 implements Creator {
        C03362() {
        }

        public WebViewLoginMethodHandler createFromParcel(Parcel source) {
            return new WebViewLoginMethodHandler(source);
        }

        public WebViewLoginMethodHandler[] newArray(int size) {
            return new WebViewLoginMethodHandler[size];
        }
    }

    static class AuthDialogBuilder extends Builder {
        private static final String OAUTH_DIALOG = "oauth";
        static final String REDIRECT_URI = "fbconnect://success";
        private String e2e;
        private boolean isRerequest;

        public AuthDialogBuilder(Context context, String applicationId, Bundle parameters) {
            super(context, applicationId, OAUTH_DIALOG, parameters);
        }

        public AuthDialogBuilder setE2E(String e2e) {
            this.e2e = e2e;
            return this;
        }

        public AuthDialogBuilder setIsRerequest(boolean isRerequest) {
            this.isRerequest = isRerequest;
            return this;
        }

        public WebDialog build() {
            Bundle parameters = getParameters();
            parameters.putString(ServerProtocol.DIALOG_PARAM_REDIRECT_URI, "fbconnect://success");
            parameters.putString("client_id", getApplicationId());
            parameters.putString("e2e", this.e2e);
            parameters.putString("response_type", "token,signed_request");
            parameters.putString("return_scopes", "true");
            parameters.putString(ServerProtocol.DIALOG_PARAM_AUTH_TYPE, ServerProtocol.DIALOG_REREQUEST_AUTH_TYPE);
            return WebDialog.newInstance(getContext(), OAUTH_DIALOG, parameters, getTheme(), getListener());
        }
    }

    WebViewLoginMethodHandler(LoginClient loginClient) {
        super(loginClient);
    }

    String getNameForLogging() {
        return "web_view";
    }

    AccessTokenSource getTokenSource() {
        return AccessTokenSource.WEB_VIEW;
    }

    boolean needsInternetPermission() {
        return true;
    }

    void cancel() {
        if (this.loginDialog != null) {
            this.loginDialog.cancel();
            this.loginDialog = null;
        }
    }

    boolean tryAuthorize(final Request request) {
        Bundle parameters = getParameters(request);
        OnCompleteListener listener = new OnCompleteListener() {
            public void onComplete(Bundle values, FacebookException error) {
                WebViewLoginMethodHandler.this.onWebDialogComplete(request, values, error);
            }
        };
        this.e2e = LoginClient.getE2E();
        addLoggingExtra("e2e", this.e2e);
        FragmentActivity fragmentActivity = this.loginClient.getActivity();
        this.loginDialog = new AuthDialogBuilder(fragmentActivity, request.getApplicationId(), parameters).setE2E(this.e2e).setIsRerequest(request.isRerequest()).setOnCompleteListener(listener).build();
        FacebookDialogFragment dialogFragment = new FacebookDialogFragment();
        dialogFragment.setRetainInstance(true);
        dialogFragment.setDialog(this.loginDialog);
        dialogFragment.show(fragmentActivity.getSupportFragmentManager(), FacebookDialogFragment.TAG);
        return true;
    }

    void onWebDialogComplete(Request request, Bundle values, FacebookException error) {
        super.onComplete(request, values, error);
    }

    WebViewLoginMethodHandler(Parcel source) {
        super(source);
        this.e2e = source.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.e2e);
    }
}
