package com.onesignal;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobInfo.Builder;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build.VERSION;
import android.service.notification.StatusBarNotification;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import com.google.android.gms.drive.DriveFile;
import com.onesignal.OneSignal.LOG_LEVEL;
import java.util.ArrayList;

class NotificationRestorer {
    static final String[] COLUMNS_FOR_RESTORE = new String[]{NotificationTable.COLUMN_NAME_ANDROID_NOTIFICATION_ID, NotificationTable.COLUMN_NAME_FULL_DATA, NotificationTable.COLUMN_NAME_CREATED_TIME};
    private static final int DELAY_BETWEEN_NOTIFICATION_RESTORES_MS = 200;
    private static final String MAX_NUMBER_OF_NOTIFICATIONS_TO_RESTORE = "49";
    private static final int RESTORE_KICKOFF_REQUEST_CODE = 2071862120;
    private static final int RESTORE_NOTIFICATIONS_DELAY_MS = 15000;
    public static boolean restored;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.support.annotation.WorkerThread
    public static void restore(android.content.Context r18) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0004 in list [B:12:0x00b7]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:43)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1364497552.run(Unknown Source)
*/
        /*
        r3 = restored;
        if (r3 == 0) goto L_0x0005;
    L_0x0004:
        return;
    L_0x0005:
        r3 = 1;
        restored = r3;
        r3 = com.onesignal.OneSignal.LOG_LEVEL.INFO;
        r4 = "Restoring notifications";
        com.onesignal.OneSignal.Log(r3, r4);
        r14 = com.onesignal.OneSignalDbHelper.getInstance(r18);
        r17 = 0;
        r17 = r14.getWritableDbWithRetries();	 Catch:{ Throwable -> 0x00ce, all -> 0x00eb, Throwable -> 0x00df }
        r17.beginTransaction();	 Catch:{ Throwable -> 0x00ce, all -> 0x00eb, Throwable -> 0x00df }
        com.onesignal.NotificationBundleProcessor.deleteOldNotifications(r17);	 Catch:{ Throwable -> 0x00ce, all -> 0x00eb, Throwable -> 0x00df }
        r17.setTransactionSuccessful();	 Catch:{ Throwable -> 0x00ce, all -> 0x00eb, Throwable -> 0x00df }
        if (r17 == 0) goto L_0x0027;
    L_0x0024:
        r17.endTransaction();	 Catch:{ Throwable -> 0x00c2 }
    L_0x0027:
        r4 = java.lang.System.currentTimeMillis();
        r6 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r4 = r4 / r6;
        r6 = 604800; // 0x93a80 float:8.47505E-40 double:2.98811E-318;
        r12 = r4 - r6;
        r15 = new java.lang.StringBuilder;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "created_time > ";
        r3 = r3.append(r4);
        r3 = r3.append(r12);
        r4 = " AND ";
        r3 = r3.append(r4);
        r4 = "dismissed";
        r3 = r3.append(r4);
        r4 = " = 0 AND ";
        r3 = r3.append(r4);
        r4 = "opened";
        r3 = r3.append(r4);
        r4 = " = 0 AND ";
        r3 = r3.append(r4);
        r4 = "is_summary";
        r3 = r3.append(r4);
        r4 = " = 0";
        r3 = r3.append(r4);
        r3 = r3.toString();
        r15.<init>(r3);
        r0 = r18;
        skipVisibleNotifications(r0, r15);
        r3 = com.onesignal.OneSignal.LOG_LEVEL.INFO;
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "Querying DB for notifs to restore: ";
        r4 = r4.append(r5);
        r5 = r15.toString();
        r4 = r4.append(r5);
        r4 = r4.toString();
        com.onesignal.OneSignal.Log(r3, r4);
        r11 = 0;
        r2 = r14.getReadableDbWithRetries();	 Catch:{ Throwable -> 0x00fd, all -> 0x0114 }
        r3 = "notification";	 Catch:{ Throwable -> 0x00fd, all -> 0x0114 }
        r4 = COLUMNS_FOR_RESTORE;	 Catch:{ Throwable -> 0x00fd, all -> 0x0114 }
        r5 = r15.toString();	 Catch:{ Throwable -> 0x00fd, all -> 0x0114 }
        r6 = 0;	 Catch:{ Throwable -> 0x00fd, all -> 0x0114 }
        r7 = 0;	 Catch:{ Throwable -> 0x00fd, all -> 0x0114 }
        r8 = 0;	 Catch:{ Throwable -> 0x00fd, all -> 0x0114 }
        r9 = "_id DESC";	 Catch:{ Throwable -> 0x00fd, all -> 0x0114 }
        r10 = "49";	 Catch:{ Throwable -> 0x00fd, all -> 0x0114 }
        r11 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10);	 Catch:{ Throwable -> 0x00fd, all -> 0x0114 }
        r3 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;	 Catch:{ Throwable -> 0x00fd, all -> 0x0114 }
        r0 = r18;	 Catch:{ Throwable -> 0x00fd, all -> 0x0114 }
        showNotifications(r0, r11, r3);	 Catch:{ Throwable -> 0x00fd, all -> 0x0114 }
        if (r11 == 0) goto L_0x0004;
    L_0x00b7:
        r3 = r11.isClosed();
        if (r3 != 0) goto L_0x0004;
    L_0x00bd:
        r11.close();
        goto L_0x0004;
    L_0x00c2:
        r16 = move-exception;
        r3 = com.onesignal.OneSignal.LOG_LEVEL.ERROR;
        r4 = "Error closing transaction! ";
        r0 = r16;
        com.onesignal.OneSignal.Log(r3, r4, r0);
        goto L_0x0027;
    L_0x00ce:
        r16 = move-exception;
        r3 = com.onesignal.OneSignal.LOG_LEVEL.ERROR;	 Catch:{ Throwable -> 0x00ce, all -> 0x00eb, Throwable -> 0x00df }
        r4 = "Error deleting old notification records! ";	 Catch:{ Throwable -> 0x00ce, all -> 0x00eb, Throwable -> 0x00df }
        r0 = r16;	 Catch:{ Throwable -> 0x00ce, all -> 0x00eb, Throwable -> 0x00df }
        com.onesignal.OneSignal.Log(r3, r4, r0);	 Catch:{ Throwable -> 0x00ce, all -> 0x00eb, Throwable -> 0x00df }
        if (r17 == 0) goto L_0x0027;
    L_0x00da:
        r17.endTransaction();	 Catch:{ Throwable -> 0x00ce, all -> 0x00eb, Throwable -> 0x00df }
        goto L_0x0027;
    L_0x00df:
        r16 = move-exception;
        r3 = com.onesignal.OneSignal.LOG_LEVEL.ERROR;
        r4 = "Error closing transaction! ";
        r0 = r16;
        com.onesignal.OneSignal.Log(r3, r4, r0);
        goto L_0x0027;
    L_0x00eb:
        r3 = move-exception;
        if (r17 == 0) goto L_0x00f1;
    L_0x00ee:
        r17.endTransaction();	 Catch:{ Throwable -> 0x00f2 }
    L_0x00f1:
        throw r3;
    L_0x00f2:
        r16 = move-exception;
        r4 = com.onesignal.OneSignal.LOG_LEVEL.ERROR;
        r5 = "Error closing transaction! ";
        r0 = r16;
        com.onesignal.OneSignal.Log(r4, r5, r0);
        goto L_0x00f1;
    L_0x00fd:
        r16 = move-exception;
        r3 = com.onesignal.OneSignal.LOG_LEVEL.ERROR;	 Catch:{ Throwable -> 0x00fd, all -> 0x0114 }
        r4 = "Error restoring notification records! ";	 Catch:{ Throwable -> 0x00fd, all -> 0x0114 }
        r0 = r16;	 Catch:{ Throwable -> 0x00fd, all -> 0x0114 }
        com.onesignal.OneSignal.Log(r3, r4, r0);	 Catch:{ Throwable -> 0x00fd, all -> 0x0114 }
        if (r11 == 0) goto L_0x0004;
    L_0x0109:
        r3 = r11.isClosed();
        if (r3 != 0) goto L_0x0004;
    L_0x010f:
        r11.close();
        goto L_0x0004;
    L_0x0114:
        r3 = move-exception;
        if (r11 == 0) goto L_0x0120;
    L_0x0117:
        r4 = r11.isClosed();
        if (r4 != 0) goto L_0x0120;
    L_0x011d:
        r11.close();
    L_0x0120:
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.NotificationRestorer.restore(android.content.Context):void");
    }

    NotificationRestorer() {
    }

    static void asyncRestore(final Context context) {
        new Thread(new Runnable() {
            public void run() {
                Thread.currentThread().setPriority(10);
                NotificationRestorer.restore(context);
            }
        }, "OS_RESTORE_NOTIFS").start();
    }

    private static void skipVisibleNotifications(Context context, StringBuilder dbQuerySelection) {
        if (VERSION.SDK_INT >= 23) {
            NotificationManager notifManager = (NotificationManager) context.getSystemService(NotificationTable.TABLE_NAME);
            if (notifManager != null) {
                try {
                    StatusBarNotification[] activeNotifs = notifManager.getActiveNotifications();
                    if (activeNotifs.length != 0) {
                        ArrayList<Integer> activeNotifIds = new ArrayList();
                        for (StatusBarNotification activeNotif : activeNotifs) {
                            activeNotifIds.add(Integer.valueOf(activeNotif.getId()));
                        }
                        dbQuerySelection.append(" AND android_notification_id NOT IN (").append(TextUtils.join(",", activeNotifIds)).append(")");
                    }
                } catch (Throwable th) {
                }
            }
        }
    }

    static void showNotifications(Context context, Cursor cursor, int delay) {
        if (cursor.moveToFirst()) {
            boolean useExtender;
            if (NotificationExtenderService.getIntent(context) != null) {
                useExtender = true;
            } else {
                useExtender = false;
            }
            do {
                if (useExtender) {
                    Intent intent = NotificationExtenderService.getIntent(context);
                    addRestoreExtras(intent, cursor);
                    JobIntentService.enqueueWork(context, intent.getComponent(), 2071862121, intent, false);
                } else {
                    JobIntentService.enqueueWork(context, new ComponentName(context, RestoreJobService.class), 2071862122, addRestoreExtras(new Intent(), cursor), false);
                }
                if (delay > 0) {
                    OSUtils.sleep(delay);
                }
            } while (cursor.moveToNext());
        }
    }

    private static Intent addRestoreExtras(Intent intent, Cursor cursor) {
        int existingId = cursor.getInt(cursor.getColumnIndex(NotificationTable.COLUMN_NAME_ANDROID_NOTIFICATION_ID));
        String fullData = cursor.getString(cursor.getColumnIndex(NotificationTable.COLUMN_NAME_FULL_DATA));
        intent.putExtra("json_payload", fullData).putExtra("android_notif_id", existingId).putExtra("restoring", true).putExtra("timestamp", Long.valueOf(cursor.getLong(cursor.getColumnIndex(NotificationTable.COLUMN_NAME_CREATED_TIME))));
        return intent;
    }

    static void startDelayedRestoreTaskFromReceiver(Context context) {
        if (VERSION.SDK_INT >= 26) {
            OneSignal.Log(LOG_LEVEL.INFO, "scheduleRestoreKickoffJob");
            ((JobScheduler) context.getSystemService("jobscheduler")).schedule(new Builder(RESTORE_KICKOFF_REQUEST_CODE, new ComponentName(context, RestoreKickoffJobService.class)).setOverrideDeadline(15000).setMinimumLatency(15000).build());
            return;
        }
        OneSignal.Log(LOG_LEVEL.INFO, "scheduleRestoreKickoffAlarmTask");
        Intent intentForService = new Intent();
        intentForService.setComponent(new ComponentName(context.getPackageName(), NotificationRestoreService.class.getName()));
        long scheduleTime = System.currentTimeMillis() + 15000;
        ((AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM)).set(1, scheduleTime, PendingIntent.getService(context, RESTORE_KICKOFF_REQUEST_CODE, intentForService, DriveFile.MODE_READ_ONLY));
    }
}
