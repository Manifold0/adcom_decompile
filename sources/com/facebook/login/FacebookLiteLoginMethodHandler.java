package com.facebook.login;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.internal.NativeProtocol;
import com.facebook.login.LoginClient.Request;

class FacebookLiteLoginMethodHandler extends NativeAppLoginMethodHandler {
    public static final Creator<FacebookLiteLoginMethodHandler> CREATOR = new C03211();

    /* renamed from: com.facebook.login.FacebookLiteLoginMethodHandler$1 */
    static class C03211 implements Creator {
        C03211() {
        }

        public FacebookLiteLoginMethodHandler createFromParcel(Parcel source) {
            return new FacebookLiteLoginMethodHandler(source);
        }

        public FacebookLiteLoginMethodHandler[] newArray(int size) {
            return new FacebookLiteLoginMethodHandler[size];
        }
    }

    FacebookLiteLoginMethodHandler(LoginClient loginClient) {
        super(loginClient);
    }

    String getNameForLogging() {
        return "fb_lite_login";
    }

    boolean tryAuthorize(Request request) {
        String e2e = LoginClient.getE2E();
        Intent intent = NativeProtocol.createFacebookLiteIntent(this.loginClient.getActivity(), request.getApplicationId(), request.getPermissions(), e2e, request.isRerequest(), request.hasPublishPermission(), request.getDefaultAudience(), getClientState(request.getAuthId()));
        addLoggingExtra("e2e", e2e);
        return tryIntent(intent, LoginClient.getLoginRequestCode());
    }

    FacebookLiteLoginMethodHandler(Parcel source) {
        super(source);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }
}
