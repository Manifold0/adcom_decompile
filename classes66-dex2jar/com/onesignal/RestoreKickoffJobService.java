// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import android.content.Context;
import android.app.job.JobService;
import android.app.job.JobParameters;
import android.support.annotation.RequiresApi;

@RequiresApi(api = 21)
public class RestoreKickoffJobService extends OneSignalJobServiceBase
{
    @Override
    void startProcessing(final JobService jobService, final JobParameters jobParameters) {
        Thread.currentThread().setPriority(10);
        OneSignal.setAppContext((Context)this);
        NotificationRestorer.restore(this.getApplicationContext());
    }
}
