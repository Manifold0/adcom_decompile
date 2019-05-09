// 
// Decompiled by Procyon v0.5.34
// 

package com.vungle.warren.tasks.runnable;

import android.os.Bundle;
import android.util.Log;
import android.support.annotation.NonNull;
import com.vungle.warren.tasks.JobInfo;
import com.vungle.warren.tasks.JobRunner;
import com.vungle.warren.tasks.JobCreator;

public class JobRunnable extends PriorityRunnable
{
    private static final String TAG;
    private final JobCreator creator;
    private final JobRunner jobRunner;
    private final JobInfo jobinfo;
    
    static {
        TAG = JobRunnable.class.getSimpleName();
    }
    
    public JobRunnable(@NonNull final JobInfo jobinfo, @NonNull final JobCreator creator, @NonNull final JobRunner jobRunner) {
        this.jobinfo = jobinfo;
        this.creator = creator;
        this.jobRunner = jobRunner;
    }
    
    @Override
    public Integer getPriority() {
        return this.jobinfo.getPriority();
    }
    
    @Override
    public void run() {
        try {
            final String jobTag = this.jobinfo.getJobTag();
            final Bundle extras = this.jobinfo.getExtras();
            Log.d(JobRunnable.TAG, "Start job " + jobTag + "Thread " + Thread.currentThread().getName());
            final int onRunJob = this.creator.create(jobTag).onRunJob(extras, this.jobRunner);
            Log.d(JobRunnable.TAG, "On job finished " + jobTag + " with result " + onRunJob);
            if (onRunJob == 2) {
                final long nextRescedule = this.jobinfo.makeNextRescedule();
                if (nextRescedule > 0L) {
                    this.jobinfo.setDelay(nextRescedule);
                    this.jobRunner.execute(this.jobinfo);
                    Log.d(JobRunnable.TAG, "Rescheduling " + jobTag + " in " + nextRescedule);
                }
            }
        }
        catch (Throwable t) {
            Log.e(JobRunnable.TAG, "Can't start job", t);
        }
    }
}
