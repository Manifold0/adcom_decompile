// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook;

import android.util.Log;
import android.content.Intent;
import android.content.Context;
import android.content.IntentFilter;
import com.facebook.internal.Validate;
import android.content.BroadcastReceiver;
import android.support.v4.content.LocalBroadcastManager;

public abstract class AccessTokenTracker
{
    private static final String TAG;
    private final LocalBroadcastManager broadcastManager;
    private boolean isTracking;
    private final BroadcastReceiver receiver;
    
    static {
        TAG = AccessTokenTracker.class.getSimpleName();
    }
    
    public AccessTokenTracker() {
        this.isTracking = false;
        Validate.sdkInitialized();
        this.receiver = new CurrentAccessTokenBroadcastReceiver();
        this.broadcastManager = LocalBroadcastManager.getInstance(FacebookSdk.getApplicationContext());
        this.startTracking();
    }
    
    private void addBroadcastReceiver() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.facebook.sdk.ACTION_CURRENT_ACCESS_TOKEN_CHANGED");
        this.broadcastManager.registerReceiver(this.receiver, intentFilter);
    }
    
    public boolean isTracking() {
        return this.isTracking;
    }
    
    protected abstract void onCurrentAccessTokenChanged(final AccessToken p0, final AccessToken p1);
    
    public void startTracking() {
        if (this.isTracking) {
            return;
        }
        this.addBroadcastReceiver();
        this.isTracking = true;
    }
    
    public void stopTracking() {
        if (!this.isTracking) {
            return;
        }
        this.broadcastManager.unregisterReceiver(this.receiver);
        this.isTracking = false;
    }
    
    private class CurrentAccessTokenBroadcastReceiver extends BroadcastReceiver
    {
        public void onReceive(final Context context, final Intent intent) {
            if ("com.facebook.sdk.ACTION_CURRENT_ACCESS_TOKEN_CHANGED".equals(intent.getAction())) {
                Log.d(AccessTokenTracker.TAG, "AccessTokenChanged");
                AccessTokenTracker.this.onCurrentAccessTokenChanged((AccessToken)intent.getParcelableExtra("com.facebook.sdk.EXTRA_OLD_ACCESS_TOKEN"), (AccessToken)intent.getParcelableExtra("com.facebook.sdk.EXTRA_NEW_ACCESS_TOKEN"));
            }
        }
    }
}
