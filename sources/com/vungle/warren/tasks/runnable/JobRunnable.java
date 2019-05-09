package com.vungle.warren.tasks.runnable;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import com.vungle.warren.tasks.JobCreator;
import com.vungle.warren.tasks.JobInfo;
import com.vungle.warren.tasks.JobRunner;

public class JobRunnable extends PriorityRunnable {
    private static final String TAG = JobRunnable.class.getSimpleName();
    private final JobCreator creator;
    private final JobRunner jobRunner;
    private final JobInfo jobinfo;

    public JobRunnable(@NonNull JobInfo jobInfo, @NonNull JobCreator jobCreator, @NonNull JobRunner jobRunner) {
        this.jobinfo = jobInfo;
        this.creator = jobCreator;
        this.jobRunner = jobRunner;
    }

    public Integer getPriority() {
        return Integer.valueOf(this.jobinfo.getPriority());
    }

    public void run() {
        try {
            String jobTag = this.jobinfo.getJobTag();
            Bundle params = this.jobinfo.getExtras();
            Log.d(TAG, "Start job " + jobTag + "Thread " + Thread.currentThread().getName());
            int result = this.creator.create(jobTag).onRunJob(params, this.jobRunner);
            Log.d(TAG, "On job finished " + jobTag + " with result " + result);
            if (result == 2) {
                long nextReschedule = this.jobinfo.makeNextRescedule();
                if (nextReschedule > 0) {
                    this.jobinfo.setDelay(nextReschedule);
                    this.jobRunner.execute(this.jobinfo);
                    Log.d(TAG, "Rescheduling " + jobTag + " in " + nextReschedule);
                }
            }
        } catch (Throwable th) {
            Log.e(TAG, "Can't start job", th);
        }
    }
}
