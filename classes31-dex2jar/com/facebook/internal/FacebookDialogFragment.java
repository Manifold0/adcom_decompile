// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.internal;

import android.os.Message;
import android.support.annotation.NonNull;
import com.facebook.FacebookSdk;
import android.content.Context;
import android.content.res.Configuration;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import com.facebook.FacebookException;
import android.os.Bundle;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;

public class FacebookDialogFragment extends DialogFragment
{
    public static final String TAG = "FacebookDialogFragment";
    private Dialog dialog;
    
    private void onCompleteWebDialog(final Bundle bundle, final FacebookException ex) {
        final FragmentActivity activity = this.getActivity();
        final Intent protocolResultIntent = NativeProtocol.createProtocolResultIntent(activity.getIntent(), bundle, ex);
        int n;
        if (ex == null) {
            n = -1;
        }
        else {
            n = 0;
        }
        activity.setResult(n, protocolResultIntent);
        activity.finish();
    }
    
    private void onCompleteWebFallbackDialog(final Bundle bundle) {
        final FragmentActivity activity = this.getActivity();
        final Intent intent = new Intent();
        Bundle bundle2 = bundle;
        if (bundle == null) {
            bundle2 = new Bundle();
        }
        intent.putExtras(bundle2);
        activity.setResult(-1, intent);
        activity.finish();
    }
    
    public void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.dialog instanceof WebDialog && this.isResumed()) {
            ((WebDialog)this.dialog).resize();
        }
    }
    
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        if (this.dialog == null) {
            final FragmentActivity activity = this.getActivity();
            final Bundle methodArgumentsFromIntent = NativeProtocol.getMethodArgumentsFromIntent(activity.getIntent());
            WebDialog dialog;
            if (!methodArgumentsFromIntent.getBoolean("is_fallback", false)) {
                final String string = methodArgumentsFromIntent.getString("action");
                final Bundle bundle2 = methodArgumentsFromIntent.getBundle("params");
                if (Utility.isNullOrEmpty(string)) {
                    Utility.logd("FacebookDialogFragment", "Cannot start a WebDialog with an empty/missing 'actionName'");
                    activity.finish();
                    return;
                }
                dialog = new WebDialog.Builder((Context)activity, string, bundle2).setOnCompleteListener(new WebDialog.OnCompleteListener() {
                    @Override
                    public void onComplete(final Bundle bundle, final FacebookException ex) {
                        FacebookDialogFragment.this.onCompleteWebDialog(bundle, ex);
                    }
                }).build();
            }
            else {
                final String string2 = methodArgumentsFromIntent.getString("url");
                if (Utility.isNullOrEmpty(string2)) {
                    Utility.logd("FacebookDialogFragment", "Cannot start a fallback WebDialog with an empty/missing 'url'");
                    activity.finish();
                    return;
                }
                dialog = FacebookWebFallbackDialog.newInstance((Context)activity, string2, String.format("fb%s://bridge/", FacebookSdk.getApplicationId()));
                dialog.setOnCompleteListener((WebDialog.OnCompleteListener)new WebDialog.OnCompleteListener() {
                    @Override
                    public void onComplete(final Bundle bundle, final FacebookException ex) {
                        FacebookDialogFragment.this.onCompleteWebFallbackDialog(bundle);
                    }
                });
            }
            this.dialog = dialog;
        }
    }
    
    @NonNull
    public Dialog onCreateDialog(final Bundle bundle) {
        if (this.dialog == null) {
            this.onCompleteWebDialog(null, null);
            this.setShowsDialog(false);
        }
        return this.dialog;
    }
    
    public void onDestroyView() {
        if (this.getDialog() != null && this.getRetainInstance()) {
            this.getDialog().setDismissMessage((Message)null);
        }
        super.onDestroyView();
    }
    
    public void onResume() {
        super.onResume();
        if (this.dialog instanceof WebDialog) {
            ((WebDialog)this.dialog).resize();
        }
    }
    
    public void setDialog(final Dialog dialog) {
        this.dialog = dialog;
    }
}
