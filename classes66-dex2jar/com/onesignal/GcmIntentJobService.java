// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import android.content.Context;
import android.app.job.JobService;
import android.app.job.JobParameters;
import android.support.annotation.RequiresApi;

@RequiresApi(api = 22)
public class GcmIntentJobService extends OneSignalJobServiceBase
{
    @Override
    void startProcessing(final JobService jobService, final JobParameters jobParameters) {
        NotificationBundleProcessor.ProcessFromGCMIntentService((Context)jobService, new BundleCompatPersistableBundle(jobParameters.getExtras()), null);
    }
}
