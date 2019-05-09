// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook;

import android.content.Intent;
import android.content.Context;
import android.content.IntentFilter;
import com.facebook.internal.Validate;
import android.content.BroadcastReceiver;
import android.support.v4.content.LocalBroadcastManager;

public abstract class ProfileTracker
{
    private final LocalBroadcastManager broadcastManager;
    private boolean isTracking;
    private final BroadcastReceiver receiver;
    
    public ProfileTracker() {
        this.isTracking = false;
        Validate.sdkInitialized();
        this.receiver = new ProfileBroadcastReceiver();
        this.broadcastManager = LocalBroadcastManager.getInstance(FacebookSdk.getApplicationContext());
        this.startTracking();
    }
    
    private void addBroadcastReceiver() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.facebook.sdk.ACTION_CURRENT_PROFILE_CHANGED");
        this.broadcastManager.registerReceiver(this.receiver, intentFilter);
    }
    
    public boolean isTracking() {
        return this.isTracking;
    }
    
    protected abstract void onCurrentProfileChanged(final Profile p0, final Profile p1);
    
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
    
    private class ProfileBroadcastReceiver extends BroadcastReceiver
    {
        public void onReceive(final Context context, final Intent intent) {
            if ("com.facebook.sdk.ACTION_CURRENT_PROFILE_CHANGED".equals(intent.getAction())) {
                ProfileTracker.this.onCurrentProfileChanged((Profile)intent.getParcelableExtra("com.facebook.sdk.EXTRA_OLD_PROFILE"), (Profile)intent.getParcelableExtra("com.facebook.sdk.EXTRA_NEW_PROFILE"));
            }
        }
    }
}
