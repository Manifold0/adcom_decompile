// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.login;

import android.util.Log;
import com.facebook.common.R$id;
import com.facebook.common.R$layout;
import android.view.View;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.support.v4.app.FragmentActivity;
import android.content.Intent;
import android.os.Parcelable;
import android.os.Bundle;
import android.content.ComponentName;
import android.app.Activity;
import android.support.v4.app.Fragment;

public class LoginFragment extends Fragment
{
    static final String EXTRA_REQUEST = "request";
    private static final String NULL_CALLING_PKG_ERROR_MSG = "Cannot call LoginFragment with a null calling package. This can occur if the launchMode of the caller is singleInstance.";
    static final String REQUEST_KEY = "com.facebook.LoginFragment:Request";
    static final String RESULT_KEY = "com.facebook.LoginFragment:Result";
    private static final String SAVED_LOGIN_CLIENT = "loginClient";
    private static final String TAG = "LoginFragment";
    private String callingPackage;
    private LoginClient loginClient;
    private LoginClient.Request request;
    
    private void initializeCallingPackage(final Activity activity) {
        final ComponentName callingActivity = activity.getCallingActivity();
        if (callingActivity == null) {
            return;
        }
        this.callingPackage = callingActivity.getPackageName();
    }
    
    private void onLoginClientCompleted(final LoginClient.Result result) {
        this.request = null;
        int n;
        if (result.code == LoginClient.Result.Code.CANCEL) {
            n = 0;
        }
        else {
            n = -1;
        }
        final Bundle bundle = new Bundle();
        bundle.putParcelable("com.facebook.LoginFragment:Result", (Parcelable)result);
        final Intent intent = new Intent();
        intent.putExtras(bundle);
        if (this.isAdded()) {
            this.getActivity().setResult(n, intent);
            this.getActivity().finish();
        }
    }
    
    protected LoginClient createLoginClient() {
        return new LoginClient(this);
    }
    
    LoginClient getLoginClient() {
        return this.loginClient;
    }
    
    public void onActivityResult(final int n, final int n2, final Intent intent) {
        super.onActivityResult(n, n2, intent);
        this.loginClient.onActivityResult(n, n2, intent);
    }
    
    public void onCreate(Bundle bundleExtra) {
        super.onCreate(bundleExtra);
        if (bundleExtra != null) {
            (this.loginClient = (LoginClient)bundleExtra.getParcelable("loginClient")).setFragment(this);
        }
        else {
            this.loginClient = this.createLoginClient();
        }
        this.loginClient.setOnCompletedListener((LoginClient.OnCompletedListener)new LoginClient.OnCompletedListener() {
            @Override
            public void onCompleted(final Result result) {
                LoginFragment.this.onLoginClientCompleted(result);
            }
        });
        final FragmentActivity activity = this.getActivity();
        if (activity != null) {
            this.initializeCallingPackage((Activity)activity);
            final Intent intent = ((Activity)activity).getIntent();
            if (intent != null) {
                bundleExtra = intent.getBundleExtra("com.facebook.LoginFragment:Request");
                if (bundleExtra != null) {
                    this.request = (LoginClient.Request)bundleExtra.getParcelable("request");
                }
            }
        }
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, @Nullable final Bundle bundle) {
        final View inflate = layoutInflater.inflate(R$layout.com_facebook_login_fragment, viewGroup, false);
        this.loginClient.setBackgroundProcessingListener((LoginClient.BackgroundProcessingListener)new LoginClient.BackgroundProcessingListener() {
            final /* synthetic */ View val$progressBar = inflate.findViewById(R$id.com_facebook_login_fragment_progress_bar);
            
            @Override
            public void onBackgroundProcessingStarted() {
                this.val$progressBar.setVisibility(0);
            }
            
            @Override
            public void onBackgroundProcessingStopped() {
                this.val$progressBar.setVisibility(8);
            }
        });
        return inflate;
    }
    
    public void onDestroy() {
        this.loginClient.cancelCurrentHandler();
        super.onDestroy();
    }
    
    public void onPause() {
        super.onPause();
        View viewById;
        if (this.getView() == null) {
            viewById = null;
        }
        else {
            viewById = this.getView().findViewById(R$id.com_facebook_login_fragment_progress_bar);
        }
        if (viewById != null) {
            viewById.setVisibility(8);
        }
    }
    
    public void onResume() {
        super.onResume();
        if (this.callingPackage == null) {
            Log.e("LoginFragment", "Cannot call LoginFragment with a null calling package. This can occur if the launchMode of the caller is singleInstance.");
            this.getActivity().finish();
            return;
        }
        this.loginClient.startOrContinueAuth(this.request);
    }
    
    public void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("loginClient", (Parcelable)this.loginClient);
    }
}
