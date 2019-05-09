// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import android.app.job.JobParameters;
import android.support.annotation.RequiresApi;
import android.app.job.JobService;

@RequiresApi(api = 21)
abstract class OneSignalJobServiceBase extends JobService
{
    public boolean onStartJob(final JobParameters jobParameters) {
        if (jobParameters.getExtras() == null) {
            return false;
        }
        new Thread(new Runnable() {
            final /* synthetic */ JobService val$jobService;
            
            @Override
            public void run() {
                OneSignalJobServiceBase.this.startProcessing(this.val$jobService, jobParameters);
                OneSignalJobServiceBase.this.jobFinished(jobParameters, false);
            }
        }, "OS_JOBSERVICE_BASE").start();
        return true;
    }
    
    public boolean onStopJob(final JobParameters jobParameters) {
        return true;
    }
    
    abstract void startProcessing(final JobService p0, final JobParameters p1);
}
