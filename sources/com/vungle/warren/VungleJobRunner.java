package com.vungle.warren;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.util.Log;
import com.vungle.warren.tasks.JobCreator;
import com.vungle.warren.tasks.JobInfo;
import com.vungle.warren.tasks.JobRunner;
import com.vungle.warren.tasks.runnable.JobRunnable;
import com.vungle.warren.tasks.runnable.PriorityRunnable;
import java.util.concurrent.Executor;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class VungleJobRunner implements JobRunner {
    private static int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
    private static final String TAG = VungleJobRunner.class.getSimpleName();
    private static Handler handler = new Handler(Looper.getMainLooper());
    private JobCreator creator;
    private Executor executor;

    VungleJobRunner(@NonNull JobCreator jobCreator) {
        this(jobCreator, new ThreadPoolExecutor(NUMBER_OF_CORES, NUMBER_OF_CORES, 1, TimeUnit.SECONDS, new PriorityBlockingQueue()));
    }

    VungleJobRunner(@NonNull JobCreator jobCreator, @NonNull Executor executor) {
        this.creator = jobCreator;
        this.executor = executor;
    }

    public void execute(@NonNull JobInfo jobInfo) {
        JobInfo jobInfoCopy = jobInfo.copy();
        String jobTag = jobInfoCopy.getJobTag();
        long delay = jobInfoCopy.getDelay();
        jobInfoCopy.setDelay(0);
        final PriorityRunnable jobRunnable = new JobRunnable(jobInfoCopy, this.creator, this);
        if (delay <= 0) {
            this.executor.execute(jobRunnable);
            return;
        }
        if (jobInfoCopy.getUpdateCurrent()) {
            Log.d(TAG, "replacing pending job with new " + jobTag);
            handler.removeCallbacksAndMessages(jobTag);
        }
        handler.postAtTime(new Runnable() {
            public void run() {
                VungleJobRunner.this.executor.execute(jobRunnable);
            }
        }, jobTag, SystemClock.uptimeMillis() + delay);
    }
}
