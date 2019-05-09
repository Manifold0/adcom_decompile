// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.app.IntentService;

public class GcmIntentService extends IntentService
{
    public GcmIntentService() {
        super("GcmIntentService");
        this.setIntentRedelivery(true);
    }
    
    protected void onHandleIntent(final Intent intent) {
        final Bundle extras = intent.getExtras();
        if (extras == null) {
            return;
        }
        NotificationBundleProcessor.ProcessFromGCMIntentService((Context)this, new BundleCompatBundle(extras), null);
        GcmBroadcastReceiver.completeWakefulIntent(intent);
    }
}
