// 
// Decompiled by Procyon v0.5.34
// 

package com.deltadna.android.sdk.notifications;

import android.content.Context;
import android.util.Log;
import com.google.firebase.iid.FirebaseInstanceIdService;

public final class InstanceIdListenerService extends FirebaseInstanceIdService
{
    private static final String TAG;
    
    static {
        TAG = "deltaDNA " + InstanceIdListenerService.class.getSimpleName();
    }
    
    public void onTokenRefresh() {
        Log.d(InstanceIdListenerService.TAG, "Registration token has been refreshed");
        RegistrationTokenFetcher.fetch((Context)this);
    }
}
