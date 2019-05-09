// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.unity;

import com.facebook.FacebookSdk;
import com.facebook.CallbackManager$Factory;
import android.os.Bundle;
import android.content.Intent;
import com.facebook.CallbackManager;
import android.app.Activity;

public abstract class BaseActivity extends Activity
{
    public static final String ACTIVITY_PARAMS = "activity_params";
    protected CallbackManager mCallbackManager;
    
    public void onActivityResult(final int n, final int n2, final Intent intent) {
        super.onActivityResult(n, n2, intent);
        this.mCallbackManager.onActivityResult(n, n2, intent);
        this.finish();
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.mCallbackManager = CallbackManager$Factory.create();
        if (!FacebookSdk.isInitialized()) {
            FacebookSdk.sdkInitialize(this.getApplicationContext());
        }
    }
}
