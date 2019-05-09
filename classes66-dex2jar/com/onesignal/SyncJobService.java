// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import android.content.Context;
import android.app.job.JobParameters;
import android.support.annotation.RequiresApi;
import android.app.job.JobService;

@RequiresApi(api = 21)
public class SyncJobService extends JobService
{
    public boolean onStartJob(final JobParameters jobParameters) {
        OneSignalSyncServiceUtils.doBackgroundSync((Context)this, (OneSignalSyncServiceUtils.SyncRunnable)new OneSignalSyncServiceUtils.LollipopSyncRunnable(this, jobParameters));
        return true;
    }
    
    public boolean onStopJob(final JobParameters jobParameters) {
        return OneSignalSyncServiceUtils.stopSyncBgThread();
    }
}
