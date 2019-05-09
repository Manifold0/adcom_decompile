// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.login;

import java.util.Collection;
import android.net.Uri;

public class DeviceLoginManager extends LoginManager
{
    private static volatile DeviceLoginManager instance;
    private Uri deviceRedirectUri;
    
    public static DeviceLoginManager getInstance() {
        Label_0028: {
            if (DeviceLoginManager.instance != null) {
                break Label_0028;
            }
            synchronized (DeviceLoginManager.class) {
                if (DeviceLoginManager.instance == null) {
                    DeviceLoginManager.instance = new DeviceLoginManager();
                }
                return DeviceLoginManager.instance;
            }
        }
    }
    
    protected LoginClient$Request createLoginRequest(final Collection<String> collection) {
        final LoginClient$Request loginRequest = super.createLoginRequest((Collection)collection);
        final Uri deviceRedirectUri = this.getDeviceRedirectUri();
        if (deviceRedirectUri != null) {
            loginRequest.setDeviceRedirectUriString(deviceRedirectUri.toString());
        }
        return loginRequest;
    }
    
    public Uri getDeviceRedirectUri() {
        return this.deviceRedirectUri;
    }
    
    public void setDeviceRedirectUri(final Uri deviceRedirectUri) {
        this.deviceRedirectUri = deviceRedirectUri;
    }
}
