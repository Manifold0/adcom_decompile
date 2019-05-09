// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.login;

import android.content.Context;
import com.facebook.AccessTokenSource;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Set;
import android.text.TextUtils;
import java.util.HashSet;
import java.util.Collection;
import com.facebook.internal.Utility;
import org.json.JSONException;
import org.json.JSONObject;
import com.facebook.FacebookException;
import com.facebook.internal.Utility$GraphMeRequestWithCacheCallback;
import android.os.Bundle;
import com.facebook.internal.PlatformServiceClient;
import android.os.Parcel;
import android.os.Parcelable$Creator;

class GetTokenLoginMethodHandler extends LoginMethodHandler
{
    public static final Parcelable$Creator<GetTokenLoginMethodHandler> CREATOR;
    private GetTokenClient getTokenClient;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator() {
            public GetTokenLoginMethodHandler createFromParcel(final Parcel parcel) {
                return new GetTokenLoginMethodHandler(parcel);
            }
            
            public GetTokenLoginMethodHandler[] newArray(final int n) {
                return new GetTokenLoginMethodHandler[n];
            }
        };
    }
    
    GetTokenLoginMethodHandler(final Parcel parcel) {
        super(parcel);
    }
    
    GetTokenLoginMethodHandler(final LoginClient loginClient) {
        super(loginClient);
    }
    
    @Override
    void cancel() {
        if (this.getTokenClient != null) {
            this.getTokenClient.cancel();
            this.getTokenClient.setCompletedListener(null);
            this.getTokenClient = null;
        }
    }
    
    void complete(final LoginClient.Request request, final Bundle bundle) {
        final String string = bundle.getString("com.facebook.platform.extra.USER_ID");
        if (string == null || string.isEmpty()) {
            this.loginClient.notifyBackgroundProcessingStart();
            Utility.getGraphMeRequestWithCacheAsync(bundle.getString("com.facebook.platform.extra.ACCESS_TOKEN"), (Utility$GraphMeRequestWithCacheCallback)new Utility$GraphMeRequestWithCacheCallback() {
                public void onFailure(final FacebookException ex) {
                    GetTokenLoginMethodHandler.this.loginClient.complete(LoginClient.Result.createErrorResult(GetTokenLoginMethodHandler.this.loginClient.getPendingRequest(), "Caught exception", ex.getMessage()));
                }
                
                public void onSuccess(final JSONObject jsonObject) {
                    try {
                        bundle.putString("com.facebook.platform.extra.USER_ID", jsonObject.getString("id"));
                        GetTokenLoginMethodHandler.this.onComplete(request, bundle);
                    }
                    catch (JSONException ex) {
                        GetTokenLoginMethodHandler.this.loginClient.complete(LoginClient.Result.createErrorResult(GetTokenLoginMethodHandler.this.loginClient.getPendingRequest(), "Caught exception", ex.getMessage()));
                    }
                }
            });
            return;
        }
        this.onComplete(request, bundle);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    String getNameForLogging() {
        return "get_token";
    }
    
    void getTokenCompleted(final LoginClient.Request request, final Bundle bundle) {
        if (this.getTokenClient != null) {
            this.getTokenClient.setCompletedListener(null);
        }
        this.getTokenClient = null;
        this.loginClient.notifyBackgroundProcessingStop();
        if (bundle != null) {
            final ArrayList stringArrayList = bundle.getStringArrayList("com.facebook.platform.extra.PERMISSIONS");
            final Set<String> permissions = request.getPermissions();
            if (stringArrayList != null && (permissions == null || stringArrayList.containsAll(permissions))) {
                this.complete(request, bundle);
                return;
            }
            final HashSet<String> permissions2 = new HashSet<String>();
            for (final String s : permissions) {
                if (!stringArrayList.contains(s)) {
                    permissions2.add(s);
                }
            }
            if (!permissions2.isEmpty()) {
                this.addLoggingExtra("new_permissions", TextUtils.join((CharSequence)",", (Iterable)permissions2));
            }
            request.setPermissions(permissions2);
        }
        this.loginClient.tryNextHandler();
    }
    
    void onComplete(final LoginClient.Request request, final Bundle bundle) {
        this.loginClient.completeAndValidate(LoginClient.Result.createTokenResult(this.loginClient.getPendingRequest(), LoginMethodHandler.createAccessTokenFromNativeLogin(bundle, AccessTokenSource.FACEBOOK_APPLICATION_SERVICE, request.getApplicationId())));
    }
    
    @Override
    boolean tryAuthorize(final LoginClient.Request request) {
        this.getTokenClient = new GetTokenClient((Context)this.loginClient.getActivity(), request.getApplicationId());
        if (!this.getTokenClient.start()) {
            return false;
        }
        this.loginClient.notifyBackgroundProcessingStart();
        this.getTokenClient.setCompletedListener((PlatformServiceClient.CompletedListener)new PlatformServiceClient.CompletedListener() {
            @Override
            public void completed(final Bundle bundle) {
                GetTokenLoginMethodHandler.this.getTokenCompleted(request, bundle);
            }
        });
        return true;
    }
    
    @Override
    public void writeToParcel(final Parcel parcel, final int n) {
        super.writeToParcel(parcel, n);
    }
}
