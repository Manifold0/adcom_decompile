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

class KatanaProxyLoginMethodHandler extends NativeAppLoginMethodHandler
{
    public static final Parcelable$Creator<KatanaProxyLoginMethodHandler> CREATOR;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator() {
            public KatanaProxyLoginMethodHandler createFromParcel(final Parcel parcel) {
                return new KatanaProxyLoginMethodHandler(parcel);
            }
            
            public KatanaProxyLoginMethodHandler[] newArray(final int n) {
                return new KatanaProxyLoginMethodHandler[n];
            }
        };
    }
    
    KatanaProxyLoginMethodHandler(final Parcel parcel) {
        super(parcel);
    }
    
    KatanaProxyLoginMethodHandler(final LoginClient loginClient) {
        super(loginClient);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    String getNameForLogging() {
        return "katana_proxy_auth";
    }
    
    @Override
    boolean tryAuthorize(final LoginClient.Request request) {
        final String e2E = LoginClient.getE2E();
        final Intent proxyAuthIntent = NativeProtocol.createProxyAuthIntent((Context)this.loginClient.getActivity(), request.getApplicationId(), (Collection)request.getPermissions(), e2E, request.isRerequest(), request.hasPublishPermission(), request.getDefaultAudience(), this.getClientState(request.getAuthId()));
        this.addLoggingExtra("e2e", e2E);
        return this.tryIntent(proxyAuthIntent, LoginClient.getLoginRequestCode());
    }
    
    @Override
    public void writeToParcel(final Parcel parcel, final int n) {
        super.writeToParcel(parcel, n);
    }
}
