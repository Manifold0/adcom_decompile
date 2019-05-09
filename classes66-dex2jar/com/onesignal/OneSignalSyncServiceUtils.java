// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import android.app.job.JobService;
import android.app.job.JobParameters;
import android.app.Service;
import android.os.Build$VERSION;
import android.content.Intent;
import android.app.PendingIntent;
import android.support.annotation.RequiresApi;
import android.app.job.JobInfo$Builder;
import android.content.ComponentName;
import android.app.AlarmManager;
import android.app.job.JobScheduler;
import android.content.Context;
import java.util.concurrent.atomic.AtomicBoolean;

class OneSignalSyncServiceUtils
{
    private static final int SYNC_AFTER_BG_DELAY_MS = 120000;
    private static final int SYNC_TASK_ID = 2071862118;
    private static Long nextScheduledSyncTime;
    private static AtomicBoolean runningOnFocusTime;
    private static Thread syncBgThread;
    
    static {
        OneSignalSyncServiceUtils.nextScheduledSyncTime = 0L;
        OneSignalSyncServiceUtils.runningOnFocusTime = new AtomicBoolean();
    }
    
    static void cancelSyncTask(final Context context) {
        while (true) {
            synchronized (OneSignalSyncServiceUtils.nextScheduledSyncTime) {
                OneSignalSyncServiceUtils.nextScheduledSyncTime = 0L;
                if (LocationGMS.scheduleUpdate(context)) {
                    return;
                }
                if (useJob()) {
                    ((JobScheduler)context.getSystemService("jobscheduler")).cancel(2071862118);
                    return;
                }
            }
            final Context context2;
            ((AlarmManager)context2.getSystemService("alarm")).cancel(syncServicePendingIntent(context2));
        }
    }
    
    static void doBackgroundSync(final Context appContext, final SyncRunnable syncRunnable) {
        OneSignal.setAppContext(appContext);
        (OneSignalSyncServiceUtils.syncBgThread = new Thread(syncRunnable, "OS_SYNCSRV_BG_SYNC")).start();
    }
    
    private static boolean hasBootPermission(final Context context) {
        return AndroidSupportV4Compat.ContextCompat.checkSelfPermission(context, "android.permission.RECEIVE_BOOT_COMPLETED") == 0;
    }
    
    private static void internalSyncOnFocusTime() {
        final long getUnsentActiveTime = OneSignal.GetUnsentActiveTime();
        if (getUnsentActiveTime < 60L) {
            return;
        }
        OneSignal.sendOnFocus(getUnsentActiveTime, true);
    }
    
    static void scheduleLocationUpdateTask(final Context context, final long n) {
        OneSignal.Log(OneSignal.LOG_LEVEL.VERBOSE, "scheduleLocationUpdateTask:delayMs: " + n);
        scheduleSyncTask(context, n);
    }
    
    private static void scheduleSyncServiceAsAlarm(final Context context, final long n) {
        OneSignal.Log(OneSignal.LOG_LEVEL.VERBOSE, "scheduleServiceSyncTask:atTime: " + n);
        ((AlarmManager)context.getSystemService("alarm")).set(0, System.currentTimeMillis() + n + n, syncServicePendingIntent(context));
    }
    
    @RequiresApi(21)
    private static void scheduleSyncServiceAsJob(final Context context, final long minimumLatency) {
        OneSignal.Log(OneSignal.LOG_LEVEL.VERBOSE, "scheduleSyncServiceAsJob:atTime: " + minimumLatency);
        final JobInfo$Builder jobInfo$Builder = new JobInfo$Builder(2071862118, new ComponentName(context, (Class)SyncJobService.class));
        jobInfo$Builder.setMinimumLatency(minimumLatency).setRequiredNetworkType(1);
        if (hasBootPermission(context)) {
            jobInfo$Builder.setPersisted(true);
        }
        final JobScheduler jobScheduler = (JobScheduler)context.getSystemService("jobscheduler");
        try {
            OneSignal.Log(OneSignal.LOG_LEVEL.INFO, "scheduleSyncServiceAsJob:result: " + jobScheduler.schedule(jobInfo$Builder.build()));
        }
        catch (NullPointerException ex) {
            OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "scheduleSyncServiceAsJob called JobScheduler.jobScheduler which triggered an internal null Android error. Skipping job.", ex);
        }
    }
    
    static void scheduleSyncTask(final Context context) {
        OneSignal.Log(OneSignal.LOG_LEVEL.VERBOSE, "scheduleSyncTask:SYNC_AFTER_BG_DELAY_MS: 120000");
        scheduleSyncTask(context, 120000L);
    }
    
    private static void scheduleSyncTask(final Context context, final long n) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: astore          5
        //     5: aload           5
        //     7: monitorenter   
        //     8: getstatic       com/onesignal/OneSignalSyncServiceUtils.nextScheduledSyncTime:Ljava/lang/Long;
        //    11: invokevirtual   java/lang/Long.longValue:()J
        //    14: lconst_0       
        //    15: lcmp           
        //    16: ifeq            78
        //    19: invokestatic    java/lang/System.currentTimeMillis:()J
        //    22: lload_1        
        //    23: ladd           
        //    24: getstatic       com/onesignal/OneSignalSyncServiceUtils.nextScheduledSyncTime:Ljava/lang/Long;
        //    27: invokevirtual   java/lang/Long.longValue:()J
        //    30: lcmp           
        //    31: ifle            78
        //    34: aload           5
        //    36: monitorexit    
        //    37: return         
        //    38: invokestatic    com/onesignal/OneSignalSyncServiceUtils.useJob:()Z
        //    41: ifeq            70
        //    44: aload_0        
        //    45: lload_3        
        //    46: invokestatic    com/onesignal/OneSignalSyncServiceUtils.scheduleSyncServiceAsJob:(Landroid/content/Context;J)V
        //    49: invokestatic    java/lang/System.currentTimeMillis:()J
        //    52: lload_3        
        //    53: ladd           
        //    54: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //    57: putstatic       com/onesignal/OneSignalSyncServiceUtils.nextScheduledSyncTime:Ljava/lang/Long;
        //    60: aload           5
        //    62: monitorexit    
        //    63: return         
        //    64: astore_0       
        //    65: aload           5
        //    67: monitorexit    
        //    68: aload_0        
        //    69: athrow         
        //    70: aload_0        
        //    71: lload_3        
        //    72: invokestatic    com/onesignal/OneSignalSyncServiceUtils.scheduleSyncServiceAsAlarm:(Landroid/content/Context;J)V
        //    75: goto            49
        //    78: lload_1        
        //    79: lstore_3       
        //    80: lload_1        
        //    81: ldc2_w          5000
        //    84: lcmp           
        //    85: ifge            38
        //    88: ldc2_w          5000
        //    91: lstore_3       
        //    92: goto            38
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  8      37     64     70     Any
        //  38     49     64     70     Any
        //  49     63     64     70     Any
        //  65     68     64     70     Any
        //  70     75     64     70     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 2, Size: 2
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.assembler.Collection.get(Collection.java:43)
        //     at java.util.Collections$UnmodifiableList.get(Collections.java:1309)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.adjustArgumentsForMethodCallCore(AstMethodBodyBuilder.java:1313)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.adjustArgumentsForMethodCall(AstMethodBodyBuilder.java:1286)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1197)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:715)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:425)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    static boolean stopSyncBgThread() {
        if (OneSignalSyncServiceUtils.syncBgThread != null && OneSignalSyncServiceUtils.syncBgThread.isAlive()) {
            OneSignalSyncServiceUtils.syncBgThread.interrupt();
            return true;
        }
        return false;
    }
    
    static void syncOnFocusTime() {
        if (OneSignalSyncServiceUtils.runningOnFocusTime.get()) {
            return;
        }
        synchronized (OneSignalSyncServiceUtils.runningOnFocusTime) {
            OneSignalSyncServiceUtils.runningOnFocusTime.set(true);
            internalSyncOnFocusTime();
            OneSignalSyncServiceUtils.runningOnFocusTime.set(false);
        }
    }
    
    private static PendingIntent syncServicePendingIntent(final Context context) {
        return PendingIntent.getService(context, 2071862118, new Intent(context, (Class)SyncService.class), 134217728);
    }
    
    private static boolean useJob() {
        return Build$VERSION.SDK_INT >= 21;
    }
    
    static class LegacySyncRunnable extends SyncRunnable
    {
        Service callerService;
        
        LegacySyncRunnable(final Service callerService) {
            this.callerService = callerService;
        }
        
        @Override
        protected void stopSync() {
            OneSignal.Log(OneSignal.LOG_LEVEL.DEBUG, "LegacySyncRunnable:Stopped");
            this.callerService.stopSelf();
        }
    }
    
    @RequiresApi(api = 21)
    static class LollipopSyncRunnable extends SyncRunnable
    {
        private JobParameters jobParameters;
        private JobService jobService;
        
        LollipopSyncRunnable(final JobService jobService, final JobParameters jobParameters) {
            this.jobService = jobService;
            this.jobParameters = jobParameters;
        }
        
        @Override
        protected void stopSync() {
            OneSignal.Log(OneSignal.LOG_LEVEL.DEBUG, "LollipopSyncRunnable:JobFinished");
            this.jobService.jobFinished(this.jobParameters, false);
        }
    }
    
    abstract static class SyncRunnable implements Runnable
    {
        @Override
        public final void run() {
            synchronized (OneSignalSyncServiceUtils.nextScheduledSyncTime) {
                OneSignalSyncServiceUtils.nextScheduledSyncTime = 0L;
                // monitorexit(OneSignalSyncServiceUtils.access$000())
                if (OneSignal.getUserId() == null) {
                    this.stopSync();
                    return;
                }
            }
            OneSignal.appId = OneSignal.getSavedAppId();
            OneSignalStateSynchronizer.initUserState();
            LocationGMS.getLocation(OneSignal.appContext, false, (LocationGMS.LocationHandler)new LocationGMS.LocationHandler() {
                @Override
                public void complete(final LocationPoint locationPoint) {
                    if (locationPoint != null) {
                        OneSignalStateSynchronizer.updateLocation(locationPoint);
                    }
                    OneSignalStateSynchronizer.syncUserState(true);
                    OneSignalSyncServiceUtils.syncOnFocusTime();
                    SyncRunnable.this.stopSync();
                }
                
                @Override
                public CALLBACK_TYPE getType() {
                    return CALLBACK_TYPE.SYNC_SERVICE;
                }
            });
        }
        
        protected abstract void stopSync();
    }
}
