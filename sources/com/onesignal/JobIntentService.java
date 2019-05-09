package com.onesignal;

import android.app.Service;
import android.app.job.JobInfo;
import android.app.job.JobInfo.Builder;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobServiceEngine;
import android.app.job.JobWorkItem;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import java.util.ArrayList;
import java.util.HashMap;

abstract class JobIntentService extends Service {
    static final boolean DEBUG = false;
    static final String TAG = "JobIntentService";
    static final HashMap<ComponentNameWithWakeful, WorkEnqueuer> sClassWorkEnqueuer = new HashMap();
    static final Object sLock = new Object();
    final ArrayList<CompatWorkItem> mCompatQueue = new ArrayList();
    WorkEnqueuer mCompatWorkEnqueuer;
    CommandProcessor mCurProcessor;
    boolean mDestroyed = false;
    boolean mInterruptIfStopped = false;
    CompatJobEngine mJobImpl;
    boolean mStopped = false;

    final class CommandProcessor extends AsyncTask<Void, Void, Void> {
        CommandProcessor() {
        }

        protected Void doInBackground(Void... params) {
            while (true) {
                GenericWorkItem work = JobIntentService.this.dequeueWork();
                if (work == null) {
                    return null;
                }
                JobIntentService.this.onHandleWork(work.getIntent());
                work.complete();
            }
        }

        protected void onCancelled(Void aVoid) {
            JobIntentService.this.processorFinished();
        }

        protected void onPostExecute(Void aVoid) {
            JobIntentService.this.processorFinished();
        }
    }

    interface CompatJobEngine {
        IBinder compatGetBinder();

        GenericWorkItem dequeueWork();
    }

    static abstract class WorkEnqueuer {
        final ComponentName mComponentName;
        boolean mHasJobId;
        int mJobId;

        abstract void enqueueWork(Intent intent);

        WorkEnqueuer(ComponentName cn) {
            this.mComponentName = cn;
        }

        void ensureJobId(int jobId) {
            if (!this.mHasJobId) {
                this.mHasJobId = true;
                this.mJobId = jobId;
            } else if (this.mJobId != jobId) {
                throw new IllegalArgumentException("Given job ID " + jobId + " is different than previous " + this.mJobId);
            }
        }

        public void serviceStartReceived() {
        }

        public void serviceProcessingStarted() {
        }

        public void serviceProcessingFinished() {
        }
    }

    static final class CompatWorkEnqueuer extends WorkEnqueuer {
        private final Context mContext;
        private final WakeLock mLaunchWakeLock;
        boolean mLaunchingService;
        private final WakeLock mRunWakeLock;
        boolean mServiceProcessing;

        CompatWorkEnqueuer(Context context, ComponentName cn) {
            super(cn);
            this.mContext = context.getApplicationContext();
            PowerManager pm = (PowerManager) context.getSystemService("power");
            this.mLaunchWakeLock = pm.newWakeLock(1, cn.getClassName() + ":launch");
            this.mLaunchWakeLock.setReferenceCounted(false);
            this.mRunWakeLock = pm.newWakeLock(1, cn.getClassName() + ":run");
            this.mRunWakeLock.setReferenceCounted(false);
        }

        void enqueueWork(Intent work) {
            Intent intent = new Intent(work);
            intent.setComponent(this.mComponentName);
            if (this.mContext.startService(intent) != null) {
                synchronized (this) {
                    if (!this.mLaunchingService) {
                        this.mLaunchingService = true;
                        if (!this.mServiceProcessing) {
                            this.mLaunchWakeLock.acquire(60000);
                        }
                    }
                }
            }
        }

        public void serviceStartReceived() {
            synchronized (this) {
                this.mLaunchingService = false;
            }
        }

        public void serviceProcessingStarted() {
            synchronized (this) {
                if (!this.mServiceProcessing) {
                    this.mServiceProcessing = true;
                    this.mRunWakeLock.acquire(600000);
                    this.mLaunchWakeLock.release();
                }
            }
        }

        public void serviceProcessingFinished() {
            synchronized (this) {
                if (this.mServiceProcessing) {
                    if (this.mLaunchingService) {
                        this.mLaunchWakeLock.acquire(60000);
                    }
                    this.mServiceProcessing = false;
                    this.mRunWakeLock.release();
                }
            }
        }
    }

    interface GenericWorkItem {
        void complete();

        Intent getIntent();
    }

    final class CompatWorkItem implements GenericWorkItem {
        final Intent mIntent;
        final int mStartId;

        CompatWorkItem(Intent intent, int startId) {
            this.mIntent = intent;
            this.mStartId = startId;
        }

        public Intent getIntent() {
            return this.mIntent;
        }

        public void complete() {
            JobIntentService.this.stopSelf(this.mStartId);
        }
    }

    private static class ComponentNameWithWakeful {
        private ComponentName componentName;
        private boolean useWakefulService;

        ComponentNameWithWakeful(ComponentName componentName, boolean useWakefulService) {
            this.componentName = componentName;
            this.useWakefulService = useWakefulService;
        }
    }

    @RequiresApi(26)
    static final class JobServiceEngineImpl extends JobServiceEngine implements CompatJobEngine {
        static final boolean DEBUG = false;
        static final String TAG = "JobServiceEngineImpl";
        final Object mLock = new Object();
        JobParameters mParams;
        final JobIntentService mService;

        final class WrapperWorkItem implements GenericWorkItem {
            final JobWorkItem mJobWork;

            WrapperWorkItem(JobWorkItem jobWork) {
                this.mJobWork = jobWork;
            }

            public Intent getIntent() {
                return this.mJobWork.getIntent();
            }

            public void complete() {
                synchronized (JobServiceEngineImpl.this.mLock) {
                    if (JobServiceEngineImpl.this.mParams != null) {
                        JobServiceEngineImpl.this.mParams.completeWork(this.mJobWork);
                    }
                }
            }
        }

        JobServiceEngineImpl(JobIntentService service) {
            super(service);
            this.mService = service;
        }

        public IBinder compatGetBinder() {
            return getBinder();
        }

        public boolean onStartJob(JobParameters params) {
            this.mParams = params;
            this.mService.ensureProcessorRunningLocked(false);
            return true;
        }

        public boolean onStopJob(JobParameters params) {
            boolean result = this.mService.doStopCurrentWork();
            synchronized (this.mLock) {
                this.mParams = null;
            }
            return result;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.onesignal.JobIntentService.GenericWorkItem dequeueWork() {
            /*
            r6 = this;
            r2 = 0;
            r3 = r6.mLock;
            monitor-enter(r3);
            r4 = r6.mParams;	 Catch:{ all -> 0x0030 }
            if (r4 != 0) goto L_0x000a;
        L_0x0008:
            monitor-exit(r3);	 Catch:{ all -> 0x0030 }
        L_0x0009:
            return r2;
        L_0x000a:
            r4 = r6.mParams;	 Catch:{ SecurityException -> 0x0026 }
            r1 = r4.dequeueWork();	 Catch:{ SecurityException -> 0x0026 }
            monitor-exit(r3);	 Catch:{ all -> 0x0030 }
            if (r1 == 0) goto L_0x0009;
        L_0x0013:
            r2 = r1.getIntent();
            r3 = r6.mService;
            r3 = r3.getClassLoader();
            r2.setExtrasClassLoader(r3);
            r2 = new com.onesignal.JobIntentService$JobServiceEngineImpl$WrapperWorkItem;
            r2.<init>(r1);
            goto L_0x0009;
        L_0x0026:
            r0 = move-exception;
            r4 = "JobServiceEngineImpl";
            r5 = "Failed to run mParams.dequeueWork()!";
            android.util.Log.e(r4, r5, r0);	 Catch:{ all -> 0x0030 }
            monitor-exit(r3);	 Catch:{ all -> 0x0030 }
            goto L_0x0009;
        L_0x0030:
            r2 = move-exception;
            monitor-exit(r3);	 Catch:{ all -> 0x0030 }
            throw r2;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.onesignal.JobIntentService.JobServiceEngineImpl.dequeueWork():com.onesignal.JobIntentService$GenericWorkItem");
        }
    }

    @RequiresApi(26)
    static final class JobWorkEnqueuer extends WorkEnqueuer {
        private final JobInfo mJobInfo;
        private final JobScheduler mJobScheduler;

        JobWorkEnqueuer(Context context, ComponentName cn, int jobId) {
            super(cn);
            ensureJobId(jobId);
            this.mJobInfo = new Builder(jobId, this.mComponentName).setOverrideDeadline(0).build();
            this.mJobScheduler = (JobScheduler) context.getApplicationContext().getSystemService("jobscheduler");
        }

        void enqueueWork(Intent work) {
            this.mJobScheduler.enqueue(this.mJobInfo, new JobWorkItem(work));
        }
    }

    protected abstract void onHandleWork(@NonNull Intent intent);

    public void onCreate() {
        super.onCreate();
        if (VERSION.SDK_INT >= 26) {
            this.mJobImpl = new JobServiceEngineImpl(this);
            this.mCompatWorkEnqueuer = null;
        }
        this.mCompatWorkEnqueuer = getWorkEnqueuer(this, new ComponentName(this, getClass()), false, 0, true);
    }

    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        this.mCompatWorkEnqueuer.serviceStartReceived();
        synchronized (this.mCompatQueue) {
            ArrayList arrayList = this.mCompatQueue;
            if (intent == null) {
                intent = new Intent();
            }
            arrayList.add(new CompatWorkItem(intent, startId));
            ensureProcessorRunningLocked(true);
        }
        return 3;
    }

    public IBinder onBind(@NonNull Intent intent) {
        if (this.mJobImpl != null) {
            return this.mJobImpl.compatGetBinder();
        }
        return null;
    }

    public void onDestroy() {
        super.onDestroy();
        doStopCurrentWork();
        synchronized (this.mCompatQueue) {
            this.mDestroyed = true;
            this.mCompatWorkEnqueuer.serviceProcessingFinished();
        }
    }

    public static void enqueueWork(@NonNull Context context, @NonNull Class cls, int jobId, @NonNull Intent work, boolean useWakefulService) {
        enqueueWork(context, new ComponentName(context, cls), jobId, work, useWakefulService);
    }

    public static void enqueueWork(@NonNull Context context, @NonNull ComponentName component, int jobId, @NonNull Intent work, boolean useWakefulService) {
        if (work == null) {
            throw new IllegalArgumentException("work must not be null");
        }
        synchronized (sLock) {
            WorkEnqueuer we = getWorkEnqueuer(context, component, true, jobId, useWakefulService);
            we.ensureJobId(jobId);
            try {
                we.enqueueWork(work);
            } catch (IllegalStateException e) {
                if (useWakefulService) {
                    getWorkEnqueuer(context, component, true, jobId, false).enqueueWork(work);
                } else {
                    throw e;
                }
            }
        }
    }

    static WorkEnqueuer getWorkEnqueuer(Context context, ComponentName cn, boolean hasJobId, int jobId, boolean useWakefulService) {
        ComponentNameWithWakeful key = new ComponentNameWithWakeful(cn, useWakefulService);
        WorkEnqueuer we = (WorkEnqueuer) sClassWorkEnqueuer.get(key);
        if (we == null) {
            if (VERSION.SDK_INT < 26 || useWakefulService) {
                we = new CompatWorkEnqueuer(context, cn);
            } else if (hasJobId) {
                we = new JobWorkEnqueuer(context, cn, jobId);
            } else {
                throw new IllegalArgumentException("Can't be here without a job id");
            }
            sClassWorkEnqueuer.put(key, we);
        }
        return we;
    }

    public void setInterruptIfStopped(boolean interruptIfStopped) {
        this.mInterruptIfStopped = interruptIfStopped;
    }

    public boolean isStopped() {
        return this.mStopped;
    }

    public boolean onStopCurrentWork() {
        return true;
    }

    boolean doStopCurrentWork() {
        if (this.mCurProcessor != null) {
            this.mCurProcessor.cancel(this.mInterruptIfStopped);
        }
        this.mStopped = true;
        return onStopCurrentWork();
    }

    void ensureProcessorRunningLocked(boolean reportStarted) {
        if (this.mCurProcessor == null) {
            this.mCurProcessor = new CommandProcessor();
            if (this.mCompatWorkEnqueuer != null && reportStarted) {
                this.mCompatWorkEnqueuer.serviceProcessingStarted();
            }
            this.mCurProcessor.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    void processorFinished() {
        if (this.mCompatQueue != null) {
            synchronized (this.mCompatQueue) {
                this.mCurProcessor = null;
                if (this.mCompatQueue != null && this.mCompatQueue.size() > 0) {
                    ensureProcessorRunningLocked(false);
                } else if (!this.mDestroyed) {
                    this.mCompatWorkEnqueuer.serviceProcessingFinished();
                }
            }
        }
    }

    GenericWorkItem dequeueWork() {
        if (this.mJobImpl != null) {
            GenericWorkItem jobWork = this.mJobImpl.dequeueWork();
            if (jobWork != null) {
                return jobWork;
            }
        }
        synchronized (this.mCompatQueue) {
            if (this.mCompatQueue.size() > 0) {
                GenericWorkItem genericWorkItem = (GenericWorkItem) this.mCompatQueue.remove(0);
                return genericWorkItem;
            }
            return null;
        }
    }
}
