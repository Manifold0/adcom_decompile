// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.login.widget;

import com.facebook.login.LoginBehavior;
import com.facebook.login.DeviceLoginManager;
import com.facebook.login.LoginManager;
import android.util.AttributeSet;
import android.content.Context;
import android.net.Uri;

public class DeviceLoginButton extends LoginButton
{
    private Uri deviceRedirectUri;
    
    public DeviceLoginButton(final Context context) {
        super(context);
    }
    
    public DeviceLoginButton(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    public DeviceLoginButton(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
    }
    
    public Uri getDeviceRedirectUri() {
        return this.deviceRedirectUri;
    }
    
    @Override
    protected LoginClickListener getNewLoginClickListener() {
        return new DeviceLoginClickListener();
    }
    
    public void setDeviceRedirectUri(final Uri deviceRedirectUri) {
        this.deviceRedirectUri = deviceRedirectUri;
    }
    
    private class DeviceLoginClickListener extends LoginClickListener
    {
        @Override
        protected LoginManager getLoginManager() {
            final DeviceLoginManager instance = DeviceLoginManager.getInstance();
            instance.setDefaultAudience(DeviceLoginButton.this.getDefaultAudience());
            instance.setLoginBehavior(LoginBehavior.DEVICE_AUTH);
            instance.setDeviceRedirectUri(DeviceLoginButton.this.getDeviceRedirectUri());
            return instance;
        }
    }
}
