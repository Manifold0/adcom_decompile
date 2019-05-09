// 
// Decompiled by Procyon v0.5.34
// 

package com.vungle.warren;

import android.os.SystemClock;
import com.vungle.warren.tasks.runnable.PriorityRunnable;
import android.util.Log;
import com.vungle.warren.tasks.runnable.JobRunnable;
import com.vungle.warren.tasks.JobInfo;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import android.support.annotation.NonNull;
import android.os.Looper;
import java.util.concurrent.Executor;
import com.vungle.warren.tasks.JobCreator;
import android.os.Handler;
import com.vungle.warren.tasks.JobRunner;

class VungleJobRunner implements JobRunner
{
    private static int NUMBER_OF_CORES;
    private static final String TAG;
    private static Handler handler;
    private JobCreator creator;
    private Executor executor;
    
    static {
        VungleJobRunner.handler = new Handler(Looper.getMainLooper());
        TAG = VungleJobRunner.class.getSimpleName();
        VungleJobRunner.NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
    }
    
    VungleJobRunner(@NonNull final JobCreator jobCreator) {
        this(jobCreator, new ThreadPoolExecutor(VungleJobRunner.NUMBER_OF_CORES, VungleJobRunner.NUMBER_OF_CORES, 1L, TimeUnit.SECONDS, new PriorityBlockingQueue<Runnable>()));
    }
    
    VungleJobRunner(@NonNull final JobCreator creator, @NonNull final Executor executor) {
        this.creator = creator;
        this.executor = executor;
    }
    
    @Override
    public void execute(@NonNull JobInfo copy) {
        copy = copy.copy();
        final String jobTag = copy.getJobTag();
        final long delay = copy.getDelay();
        copy.setDelay(0L);
        final JobRunnable jobRunnable = new JobRunnable(copy, this.creator, this);
        if (delay <= 0L) {
            this.executor.execute(jobRunnable);
            return;
        }
        if (copy.getUpdateCurrent()) {
            Log.d(VungleJobRunner.TAG, "replacing pending job with new " + jobTag);
            VungleJobRunner.handler.removeCallbacksAndMessages((Object)jobTag);
        }
        VungleJobRunner.handler.postAtTime((Runnable)new Runnable() {
            @Override
            public void run() {
                VungleJobRunner.this.executor.execute(jobRunnable);
            }
        }, (Object)jobTag, SystemClock.uptimeMillis() + delay);
    }
}
