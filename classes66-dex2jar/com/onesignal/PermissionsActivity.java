// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import android.support.annotation.NonNull;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.os.Build$VERSION;
import android.app.Activity;

public class PermissionsActivity extends Activity
{
    private static final int REQUEST_LOCATION = 2;
    private static ActivityLifecycleHandler.ActivityAvailableListener activityAvailableListener;
    static boolean answered;
    static boolean waiting;
    
    private void requestPermission() {
        if (Build$VERSION.SDK_INT < 23) {
            this.finish();
        }
        else if (!PermissionsActivity.waiting) {
            PermissionsActivity.waiting = true;
            AndroidSupportV4Compat.ActivityCompat.requestPermissions(this, new String[] { LocationGMS.requestPermission }, 2);
        }
    }
    
    static void startPrompt() {
        if (PermissionsActivity.waiting || PermissionsActivity.answered) {
            return;
        }
        ActivityLifecycleHandler.setActivityAvailableListener(PermissionsActivity.activityAvailableListener = new ActivityLifecycleHandler.ActivityAvailableListener() {
            @Override
            public void available(final Activity activity) {
                if (!activity.getClass().equals(PermissionsActivity.class)) {
                    final Intent intent = new Intent((Context)activity, (Class)PermissionsActivity.class);
                    intent.setFlags(131072);
                    activity.startActivity(intent);
                }
            }
        });
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        OneSignal.setAppContext((Context)this);
        if (bundle != null && bundle.getBoolean("android:hasCurrentPermissionsRequest", false)) {
            PermissionsActivity.waiting = true;
            return;
        }
        this.requestPermission();
    }
    
    protected void onNewIntent(final Intent intent) {
        super.onNewIntent(intent);
        if (OneSignal.initDone) {
            this.requestPermission();
        }
    }
    
    public void onRequestPermissionsResult(final int n, @NonNull final String[] array, @NonNull final int[] array2) {
        PermissionsActivity.answered = true;
        PermissionsActivity.waiting = false;
        if (n == 2) {
            if (array2.length > 0 && array2[0] == 0) {
                LocationGMS.startGetLocation();
            }
            else {
                LocationGMS.fireFailedComplete();
            }
        }
        ActivityLifecycleHandler.removeActivityAvailableListener(PermissionsActivity.activityAvailableListener);
        this.finish();
    }
}
