// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.login;

import android.content.Intent;
import java.util.Collection;
import android.content.Context;
import com.facebook.internal.NativeProtocol;
import android.os.Parcel;
import android.os.Parcelable$Creator;

class FacebookLiteLoginMethodHandler extends NativeAppLoginMethodHandler
{
    public static final Parcelable$Creator<FacebookLiteLoginMethodHandler> CREATOR;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator() {
            public FacebookLiteLoginMethodHandler createFromParcel(final Parcel parcel) {
                return new FacebookLiteLoginMethodHandler(parcel);
            }
            
            public FacebookLiteLoginMethodHandler[] newArray(final int n) {
                return new FacebookLiteLoginMethodHandler[n];
            }
        };
    }
    
    FacebookLiteLoginMethodHandler(final Parcel parcel) {
        super(parcel);
    }
    
    FacebookLiteLoginMethodHandler(final LoginClient loginClient) {
        super(loginClient);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    String getNameForLogging() {
        return "fb_lite_login";
    }
    
    @Override
    boolean tryAuthorize(final LoginClient.Request request) {
        final String e2E = LoginClient.getE2E();
        final Intent facebookLiteIntent = NativeProtocol.createFacebookLiteIntent((Context)this.loginClient.getActivity(), request.getApplicationId(), (Collection)request.getPermissions(), e2E, request.isRerequest(), request.hasPublishPermission(), request.getDefaultAudience(), this.getClientState(request.getAuthId()));
        this.addLoggingExtra("e2e", e2E);
        return this.tryIntent(facebookLiteIntent, LoginClient.getLoginRequestCode());
    }
    
    @Override
    public void writeToParcel(final Parcel parcel, final int n) {
        super.writeToParcel(parcel, n);
    }
}
