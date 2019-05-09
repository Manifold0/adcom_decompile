// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import android.support.annotation.Nullable;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.content.Intent;

public class RestoreJobService extends JobIntentService
{
    static final int RESTORE_SERVICE_JOB_ID = 2071862122;
    
    @Override
    protected final void onHandleWork(final Intent intent) {
        if (intent != null) {
            final Bundle extras = intent.getExtras();
            if (extras != null) {
                NotificationBundleProcessor.ProcessFromGCMIntentService(this.getApplicationContext(), new BundleCompatBundle(extras), null);
            }
        }
    }
}
