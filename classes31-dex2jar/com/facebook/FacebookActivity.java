// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook;

import com.facebook.common.R$layout;
import android.util.Log;
import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.content.Intent;
import com.facebook.common.R$id;
import com.facebook.login.LoginFragment;
import com.facebook.share.model.ShareContent;
import com.facebook.share.internal.DeviceShareDialogFragment;
import com.facebook.internal.FacebookDialogFragment;
import android.os.Bundle;
import com.facebook.internal.NativeProtocol;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

public class FacebookActivity extends FragmentActivity
{
    private static String FRAGMENT_TAG;
    public static String PASS_THROUGH_CANCEL_ACTION;
    private static final String TAG;
    private Fragment singleFragment;
    
    static {
        FacebookActivity.PASS_THROUGH_CANCEL_ACTION = "PassThrough";
        FacebookActivity.FRAGMENT_TAG = "SingleFragment";
        TAG = FacebookActivity.class.getName();
    }
    
    private void handlePassThroughError() {
        this.setResult(0, NativeProtocol.createProtocolResultIntent(this.getIntent(), (Bundle)null, NativeProtocol.getExceptionFromErrorData(NativeProtocol.getMethodArgumentsFromIntent(this.getIntent()))));
        this.finish();
    }
    
    public Fragment getCurrentFragment() {
        return this.singleFragment;
    }
    
    protected Fragment getFragment() {
        final Intent intent = this.getIntent();
        final FragmentManager supportFragmentManager = this.getSupportFragmentManager();
        Object fragmentByTag;
        if ((fragmentByTag = supportFragmentManager.findFragmentByTag(FacebookActivity.FRAGMENT_TAG)) == null) {
            if ("FacebookDialogFragment".equals(intent.getAction())) {
                fragmentByTag = new FacebookDialogFragment();
                ((FacebookDialogFragment)fragmentByTag).setRetainInstance(true);
                ((FacebookDialogFragment)fragmentByTag).show(supportFragmentManager, FacebookActivity.FRAGMENT_TAG);
            }
            else {
                if ("DeviceShareDialogFragment".equals(intent.getAction())) {
                    final DeviceShareDialogFragment deviceShareDialogFragment = new DeviceShareDialogFragment();
                    deviceShareDialogFragment.setRetainInstance(true);
                    deviceShareDialogFragment.setShareContent((ShareContent)intent.getParcelableExtra("content"));
                    deviceShareDialogFragment.show(supportFragmentManager, FacebookActivity.FRAGMENT_TAG);
                    return (Fragment)deviceShareDialogFragment;
                }
                final LoginFragment loginFragment = new LoginFragment();
                loginFragment.setRetainInstance(true);
                supportFragmentManager.beginTransaction().add(R$id.com_facebook_fragment_container, (Fragment)loginFragment, FacebookActivity.FRAGMENT_TAG).commit();
                return loginFragment;
            }
        }
        return (Fragment)fragmentByTag;
    }
    
    public void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.singleFragment != null) {
            this.singleFragment.onConfigurationChanged(configuration);
        }
    }
    
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        final Intent intent = this.getIntent();
        if (!FacebookSdk.isInitialized()) {
            Log.d(FacebookActivity.TAG, "Facebook SDK not initialized. Make sure you call sdkInitialize inside your Application's onCreate method.");
            FacebookSdk.sdkInitialize(this.getApplicationContext());
        }
        this.setContentView(R$layout.com_facebook_activity_layout);
        if (FacebookActivity.PASS_THROUGH_CANCEL_ACTION.equals(intent.getAction())) {
            this.handlePassThroughError();
            return;
        }
        this.singleFragment = this.getFragment();
    }
}
