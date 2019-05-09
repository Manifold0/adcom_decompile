// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import android.app.PendingIntent;
import android.app.AlarmManager;
import android.app.job.JobInfo$Builder;
import android.app.job.JobScheduler;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import java.util.ArrayList;
import android.app.NotificationManager;
import android.os.Build$VERSION;
import android.content.ComponentName;
import android.support.annotation.WorkerThread;
import android.content.Context;
import java.io.Serializable;
import android.database.Cursor;
import android.content.Intent;

class NotificationRestorer
{
    static final String[] COLUMNS_FOR_RESTORE;
    private static final int DELAY_BETWEEN_NOTIFICATION_RESTORES_MS = 200;
    private static final String MAX_NUMBER_OF_NOTIFICATIONS_TO_RESTORE = "49";
    private static final int RESTORE_KICKOFF_REQUEST_CODE = 2071862120;
    private static final int RESTORE_NOTIFICATIONS_DELAY_MS = 15000;
    public static boolean restored;
    
    static {
        COLUMNS_FOR_RESTORE = new String[] { "android_notification_id", "full_data", "created_time" };
    }
    
    private static Intent addRestoreExtras(final Intent intent, final Cursor cursor) {
        intent.putExtra("json_payload", cursor.getString(cursor.getColumnIndex("full_data"))).putExtra("android_notif_id", cursor.getInt(cursor.getColumnIndex("android_notification_id"))).putExtra("restoring", true).putExtra("timestamp", (Serializable)cursor.getLong(cursor.getColumnIndex("created_time")));
        return intent;
    }
    
    static void asyncRestore(final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Thread.currentThread().setPriority(10);
                NotificationRestorer.restore(context);
            }
        }, "OS_RESTORE_NOTIFS").start();
    }
    
    @WorkerThread
    public static void restore(final Context p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: ifeq            7
        //     6: return         
        //     7: iconst_1       
        //     8: putstatic       com/onesignal/NotificationRestorer.restored:Z
        //    11: getstatic       com/onesignal/OneSignal$LOG_LEVEL.INFO:Lcom/onesignal/OneSignal$LOG_LEVEL;
        //    14: ldc             "Restoring notifications"
        //    16: invokestatic    com/onesignal/OneSignal.Log:(Lcom/onesignal/OneSignal$LOG_LEVEL;Ljava/lang/String;)V
        //    19: aload_0        
        //    20: invokestatic    com/onesignal/OneSignalDbHelper.getInstance:(Landroid/content/Context;)Lcom/onesignal/OneSignalDbHelper;
        //    23: astore          6
        //    25: aconst_null    
        //    26: astore          4
        //    28: aconst_null    
        //    29: astore_3       
        //    30: aload           6
        //    32: invokevirtual   com/onesignal/OneSignalDbHelper.getWritableDbWithRetries:()Landroid/database/sqlite/SQLiteDatabase;
        //    35: astore          5
        //    37: aload           5
        //    39: astore_3       
        //    40: aload           5
        //    42: astore          4
        //    44: aload           5
        //    46: invokevirtual   android/database/sqlite/SQLiteDatabase.beginTransaction:()V
        //    49: aload           5
        //    51: astore_3       
        //    52: aload           5
        //    54: astore          4
        //    56: aload           5
        //    58: invokestatic    com/onesignal/NotificationBundleProcessor.deleteOldNotifications:(Landroid/database/sqlite/SQLiteDatabase;)V
        //    61: aload           5
        //    63: astore_3       
        //    64: aload           5
        //    66: astore          4
        //    68: aload           5
        //    70: invokevirtual   android/database/sqlite/SQLiteDatabase.setTransactionSuccessful:()V
        //    73: aload           5
        //    75: ifnull          83
        //    78: aload           5
        //    80: invokevirtual   android/database/sqlite/SQLiteDatabase.endTransaction:()V
        //    83: invokestatic    java/lang/System.currentTimeMillis:()J
        //    86: ldc2_w          1000
        //    89: ldiv           
        //    90: lstore_1       
        //    91: new             Ljava/lang/StringBuilder;
        //    94: dup            
        //    95: new             Ljava/lang/StringBuilder;
        //    98: dup            
        //    99: invokespecial   java/lang/StringBuilder.<init>:()V
        //   102: ldc             "created_time > "
        //   104: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   107: lload_1        
        //   108: ldc2_w          604800
        //   111: lsub           
        //   112: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   115: ldc             " AND "
        //   117: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   120: ldc             "dismissed"
        //   122: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   125: ldc             " = 0 AND "
        //   127: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   130: ldc             "opened"
        //   132: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   135: ldc             " = 0 AND "
        //   137: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   140: ldc             "is_summary"
        //   142: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   145: ldc             " = 0"
        //   147: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   150: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   153: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   156: astore          5
        //   158: aload_0        
        //   159: aload           5
        //   161: invokestatic    com/onesignal/NotificationRestorer.skipVisibleNotifications:(Landroid/content/Context;Ljava/lang/StringBuilder;)V
        //   164: getstatic       com/onesignal/OneSignal$LOG_LEVEL.INFO:Lcom/onesignal/OneSignal$LOG_LEVEL;
        //   167: new             Ljava/lang/StringBuilder;
        //   170: dup            
        //   171: invokespecial   java/lang/StringBuilder.<init>:()V
        //   174: ldc             "Querying DB for notifs to restore: "
        //   176: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   179: aload           5
        //   181: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   184: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   187: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   190: invokestatic    com/onesignal/OneSignal.Log:(Lcom/onesignal/OneSignal$LOG_LEVEL;Ljava/lang/String;)V
        //   193: aconst_null    
        //   194: astore          4
        //   196: aconst_null    
        //   197: astore_3       
        //   198: aload           6
        //   200: invokevirtual   com/onesignal/OneSignalDbHelper.getReadableDbWithRetries:()Landroid/database/sqlite/SQLiteDatabase;
        //   203: ldc             "notification"
        //   205: getstatic       com/onesignal/NotificationRestorer.COLUMNS_FOR_RESTORE:[Ljava/lang/String;
        //   208: aload           5
        //   210: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   213: aconst_null    
        //   214: aconst_null    
        //   215: aconst_null    
        //   216: ldc             "_id DESC"
        //   218: ldc             "49"
        //   220: invokevirtual   android/database/sqlite/SQLiteDatabase.query:(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //   223: astore          5
        //   225: aload           5
        //   227: astore_3       
        //   228: aload           5
        //   230: astore          4
        //   232: aload_0        
        //   233: aload           5
        //   235: sipush          200
        //   238: invokestatic    com/onesignal/NotificationRestorer.showNotifications:(Landroid/content/Context;Landroid/database/Cursor;I)V
        //   241: aload           5
        //   243: ifnull          6
        //   246: aload           5
        //   248: invokeinterface android/database/Cursor.isClosed:()Z
        //   253: ifne            6
        //   256: aload           5
        //   258: invokeinterface android/database/Cursor.close:()V
        //   263: return         
        //   264: astore_3       
        //   265: getstatic       com/onesignal/OneSignal$LOG_LEVEL.ERROR:Lcom/onesignal/OneSignal$LOG_LEVEL;
        //   268: ldc             "Error closing transaction! "
        //   270: aload_3        
        //   271: invokestatic    com/onesignal/OneSignal.Log:(Lcom/onesignal/OneSignal$LOG_LEVEL;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   274: goto            83
        //   277: astore          5
        //   279: aload_3        
        //   280: astore          4
        //   282: getstatic       com/onesignal/OneSignal$LOG_LEVEL.ERROR:Lcom/onesignal/OneSignal$LOG_LEVEL;
        //   285: ldc             "Error deleting old notification records! "
        //   287: aload           5
        //   289: invokestatic    com/onesignal/OneSignal.Log:(Lcom/onesignal/OneSignal$LOG_LEVEL;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   292: aload_3        
        //   293: ifnull          83
        //   296: aload_3        
        //   297: invokevirtual   android/database/sqlite/SQLiteDatabase.endTransaction:()V
        //   300: goto            83
        //   303: astore_3       
        //   304: getstatic       com/onesignal/OneSignal$LOG_LEVEL.ERROR:Lcom/onesignal/OneSignal$LOG_LEVEL;
        //   307: ldc             "Error closing transaction! "
        //   309: aload_3        
        //   310: invokestatic    com/onesignal/OneSignal.Log:(Lcom/onesignal/OneSignal$LOG_LEVEL;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   313: goto            83
        //   316: astore_0       
        //   317: aload           4
        //   319: ifnull          327
        //   322: aload           4
        //   324: invokevirtual   android/database/sqlite/SQLiteDatabase.endTransaction:()V
        //   327: aload_0        
        //   328: athrow         
        //   329: astore_3       
        //   330: getstatic       com/onesignal/OneSignal$LOG_LEVEL.ERROR:Lcom/onesignal/OneSignal$LOG_LEVEL;
        //   333: ldc             "Error closing transaction! "
        //   335: aload_3        
        //   336: invokestatic    com/onesignal/OneSignal.Log:(Lcom/onesignal/OneSignal$LOG_LEVEL;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   339: goto            327
        //   342: astore_0       
        //   343: aload_3        
        //   344: astore          4
        //   346: getstatic       com/onesignal/OneSignal$LOG_LEVEL.ERROR:Lcom/onesignal/OneSignal$LOG_LEVEL;
        //   349: ldc             "Error restoring notification records! "
        //   351: aload_0        
        //   352: invokestatic    com/onesignal/OneSignal.Log:(Lcom/onesignal/OneSignal$LOG_LEVEL;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   355: aload_3        
        //   356: ifnull          6
        //   359: aload_3        
        //   360: invokeinterface android/database/Cursor.isClosed:()Z
        //   365: ifne            6
        //   368: aload_3        
        //   369: invokeinterface android/database/Cursor.close:()V
        //   374: return         
        //   375: astore_0       
        //   376: aload           4
        //   378: ifnull          398
        //   381: aload           4
        //   383: invokeinterface android/database/Cursor.isClosed:()Z
        //   388: ifne            398
        //   391: aload           4
        //   393: invokeinterface android/database/Cursor.close:()V
        //   398: aload_0        
        //   399: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  30     37     277    316    Ljava/lang/Throwable;
        //  30     37     316    342    Any
        //  44     49     277    316    Ljava/lang/Throwable;
        //  44     49     316    342    Any
        //  56     61     277    316    Ljava/lang/Throwable;
        //  56     61     316    342    Any
        //  68     73     277    316    Ljava/lang/Throwable;
        //  68     73     316    342    Any
        //  78     83     264    277    Ljava/lang/Throwable;
        //  198    225    342    375    Ljava/lang/Throwable;
        //  198    225    375    400    Any
        //  232    241    342    375    Ljava/lang/Throwable;
        //  232    241    375    400    Any
        //  282    292    316    342    Any
        //  296    300    303    316    Ljava/lang/Throwable;
        //  322    327    329    342    Ljava/lang/Throwable;
        //  346    355    375    400    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0327:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2596)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
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
    
    static void showNotifications(final Context context, final Cursor cursor, final int n) {
        if (!cursor.moveToFirst()) {
            return;
        }
        int n2;
        if (NotificationExtenderService.getIntent(context) != null) {
            n2 = 1;
        }
        else {
            n2 = 0;
        }
        do {
            if (n2 != 0) {
                final Intent intent = NotificationExtenderService.getIntent(context);
                addRestoreExtras(intent, cursor);
                JobIntentService.enqueueWork(context, intent.getComponent(), 2071862121, intent, false);
            }
            else {
                JobIntentService.enqueueWork(context, new ComponentName(context, (Class)RestoreJobService.class), 2071862122, addRestoreExtras(new Intent(), cursor), false);
            }
            if (n > 0) {
                OSUtils.sleep(n);
            }
        } while (cursor.moveToNext());
    }
    
    private static void skipVisibleNotifications(final Context context, final StringBuilder sb) {
        if (Build$VERSION.SDK_INT >= 23) {
            final NotificationManager notificationManager = (NotificationManager)context.getSystemService("notification");
            if (notificationManager != null) {
                try {
                    final StatusBarNotification[] activeNotifications = notificationManager.getActiveNotifications();
                    if (activeNotifications.length != 0) {
                        final ArrayList<Integer> list = new ArrayList<Integer>();
                        for (int length = activeNotifications.length, i = 0; i < length; ++i) {
                            list.add(activeNotifications[i].getId());
                        }
                        sb.append(" AND android_notification_id NOT IN (").append(TextUtils.join((CharSequence)",", (Iterable)list)).append(")");
                    }
                }
                catch (Throwable t) {}
            }
        }
    }
    
    static void startDelayedRestoreTaskFromReceiver(final Context context) {
        if (Build$VERSION.SDK_INT >= 26) {
            OneSignal.Log(OneSignal.LOG_LEVEL.INFO, "scheduleRestoreKickoffJob");
            ((JobScheduler)context.getSystemService("jobscheduler")).schedule(new JobInfo$Builder(2071862120, new ComponentName(context, (Class)RestoreKickoffJobService.class)).setOverrideDeadline(15000L).setMinimumLatency(15000L).build());
            return;
        }
        OneSignal.Log(OneSignal.LOG_LEVEL.INFO, "scheduleRestoreKickoffAlarmTask");
        final Intent intent = new Intent();
        intent.setComponent(new ComponentName(context.getPackageName(), NotificationRestoreService.class.getName()));
        ((AlarmManager)context.getSystemService("alarm")).set(1, System.currentTimeMillis() + 15000L, PendingIntent.getService(context, 2071862120, intent, 268435456));
    }
}
