// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.login;

import com.facebook.AccessToken;
import java.util.Date;
import com.facebook.AccessTokenSource;
import java.util.Collection;
import android.os.Parcel;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import android.os.Parcelable$Creator;

class DeviceAuthMethodHandler extends LoginMethodHandler
{
    public static final Parcelable$Creator<DeviceAuthMethodHandler> CREATOR;
    private static ScheduledThreadPoolExecutor backgroundExecutor;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator() {
            public DeviceAuthMethodHandler createFromParcel(final Parcel parcel) {
                return new DeviceAuthMethodHandler(parcel);
            }
            
            public DeviceAuthMethodHandler[] newArray(final int n) {
                return new DeviceAuthMethodHandler[n];
            }
        };
    }
    
    protected DeviceAuthMethodHandler(final Parcel parcel) {
        super(parcel);
    }
    
    DeviceAuthMethodHandler(final LoginClient loginClient) {
        super(loginClient);
    }
    
    public static ScheduledThreadPoolExecutor getBackgroundExecutor() {
        synchronized (DeviceAuthMethodHandler.class) {
            if (DeviceAuthMethodHandler.backgroundExecutor == null) {
                DeviceAuthMethodHandler.backgroundExecutor = new ScheduledThreadPoolExecutor(1);
            }
            return DeviceAuthMethodHandler.backgroundExecutor;
        }
    }
    
    private void showDialog(final LoginClient.Request request) {
        final DeviceAuthDialog deviceAuthDialog = new DeviceAuthDialog();
        deviceAuthDialog.show(this.loginClient.getActivity().getSupportFragmentManager(), "login_with_facebook");
        deviceAuthDialog.startLogin(request);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    String getNameForLogging() {
        return "device_auth";
    }
    
    public void onCancel() {
        this.loginClient.completeAndValidate(LoginClient.Result.createCancelResult(this.loginClient.getPendingRequest(), "User canceled log in."));
    }
    
    public void onError(final Exception ex) {
        this.loginClient.completeAndValidate(LoginClient.Result.createErrorResult(this.loginClient.getPendingRequest(), null, ex.getMessage()));
    }
    
    public void onSuccess(final String s, final String s2, final String s3, final Collection<String> collection, final Collection<String> collection2, final AccessTokenSource accessTokenSource, final Date date, final Date date2) {
        this.loginClient.completeAndValidate(LoginClient.Result.createTokenResult(this.loginClient.getPendingRequest(), new AccessToken(s, s2, s3, (Collection)collection, (Collection)collection2, accessTokenSource, date, date2)));
    }
    
    @Override
    boolean tryAuthorize(final LoginClient.Request request) {
        this.showDialog(request);
        return true;
    }
    
    @Override
    public void writeToParcel(final Parcel parcel, final int n) {
        super.writeToParcel(parcel, n);
    }
}
