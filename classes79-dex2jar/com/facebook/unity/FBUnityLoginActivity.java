// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.unity;

import android.os.Bundle;
import com.facebook.CallbackManager;

public class FBUnityLoginActivity extends BaseActivity
{
    public static final String LOGIN_PARAMS = "login_params";
    public static final String LOGIN_TYPE = "login_type";
    
    public CallbackManager getCallbackManager() {
        return this.mCallbackManager;
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        final LoginType loginType = (LoginType)this.getIntent().getSerializableExtra("login_type");
        final String stringExtra = this.getIntent().getStringExtra("login_params");
        switch (loginType) {
            default: {}
            case READ: {
                FBLogin.loginWithReadPermissions(stringExtra, this);
            }
            case PUBLISH: {
                FBLogin.loginWithPublishPermissions(stringExtra, this);
            }
            case TV_READ: {
                FBLogin.loginForTVWithReadPermissions(stringExtra, this);
            }
            case TV_PUBLISH: {
                FBLogin.loginForTVWithPublishPermissions(stringExtra, this);
            }
        }
    }
    
    public enum LoginType
    {
        PUBLISH, 
        READ, 
        TV_PUBLISH, 
        TV_READ;
    }
}
